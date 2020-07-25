# 锁

- synchronized是在软件层面依赖JVM
- j.u.c.Lock是在硬件层面依赖特殊的CPU指令

[toc]

## 底层锁

### synchronized

- 当synchronized作用在实例方法时,监视器锁（monitor）便是对象实例（this）
- 当synchronized作用在静态方法时,监视器锁（monitor）便是对象的Class实例,因为Class数据存在于永久代,因此静态方法锁相当于该类的一个全局锁
- 当synchronized作用在某一个对象实例时,监视器锁（monitor）便是括号括起来的对象实例

#### 从对象头讲起

- 锁的标记在对象头中,标记了当前对象上的锁,首先我们先来看看对象头长什么样子
  - 引入openjdk的jar,用来打印对象头

    ```xml
      <dependency>
          <groupId>org.openjdk.jol</groupId>
          <artifactId>jol-core</artifactId>
          <version>0.9</version>
      </dependency>
    ```

    ```java
      public static void main(String[] args) {
          Object a = new Object();
          System.out.println(ClassLayout.parseInstance(a).toPrintable());
      }
      //默认开启指针压缩
      //java.lang.Object object internals:
      // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      //      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      //      8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
      //     12     4        (loss due to the next object alignment)
      //Instance size: 16 bytes
      //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
      //-------------------------------------------------华丽分割-------------------------------------------------------------------//
      //关闭指针压缩 -XX:-UseCompressedOops
      //java.lang.Object object internals:
      // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      //      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      //      8     4        (object header)                           00 1c 17 1c (00000000 00011100 00010111 00011100) (471276544)
      //     12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      //Instance size: 16 bytes
      //Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
    ```

  - Java对象的对象头由 mark word 和  klass pointer 两部分组成,由于数据对齐,由于虚拟机要求对象起始地址必须是8字节的整数倍.填充数据不是必须存在的,仅仅是为了字节对齐
    - mark word存储了同步状态. 标识. hashcode. GC状态等等.mark word的位长度为JVM的一个Word大小,也就是说32位JVM的Mark word为32位,64位JVM为64位
    - klass pointer存储对象的类型指针,该指针指向它的类元数据
      > 值得注意的是,如果应用的对象过多,使用64位的指针将浪费大量内存.64位的JVM比32位的JVM多耗费50%的内存.
      > 我们现在使用的64位 JVM会默认使用选项 -XX:+UseCompressedOops 开启指针压缩,将指针压缩至32位.

  - 32位JVM对象头示意图

    ```none
      |-------------------------------------------------------|--------------------|
      |            Mark Word (32 bits / 4 byte)               |       State        |
      |-------------------------------------------------------|--------------------|
      | identity_hashcode:25 | age:4 | biased_lock:1 | lock:2 |       Normal       |
      |-------------------------------------------------------|--------------------|
      |  thread:23 | epoch:2 | age:4 | biased_lock:1 | lock:2 |       Biased       |
      |-------------------------------------------------------|--------------------|
      |               ptr_to_lock_record:30          | lock:2 | Lightweight Locked |
      |-------------------------------------------------------|--------------------|
      |               ptr_to_heavyweight_monitor:30  | lock:2 | Heavyweight Locked |
      |-------------------------------------------------------|--------------------|
      |                                              | lock:2 |    Marked for GC   |
      |-------------------------------------------------------|--------------------|
    ```

  - 64位JVM对象头示意图

    ```NONE
      |--------------------------------------------------------------------------------------------------------------|
      |                                    Object Header (128 bits / 16 byte)                                        |
      |--------------------------------------------------------------------------------------------------------------|
      |                        Mark Word (64 bits / 8 byte)                           |      Klass Word (64 bits)    |
      |--------------------------------------------------------------------------------------------------------------|
      |  unused:25 | identity_hashcode:31 | unused:1 | age:4 | biased_lock:1 | lock:2 |     OOP to metadata object   |  无锁
      |----------------------------------------------------------------------|--------|------------------------------|
      |  thread:54 |         epoch:2      | unused:1 | age:4 | biased_lock:1 | lock:2 |     OOP to metadata object   |  偏向锁
      |----------------------------------------------------------------------|--------|------------------------------|
      |                     ptr_to_lock_record:62                            | lock:2 |     OOP to metadata object   |  轻量锁
      |----------------------------------------------------------------------|--------|------------------------------|
      |                     ptr_to_heavyweight_monitor:62                    | lock:2 |     OOP to metadata object   |  重量锁
      |----------------------------------------------------------------------|--------|------------------------------|
      |                                                                      | lock:2 |     OOP to metadata object   |    GC
      |--------------------------------------------------------------------------------------------------------------|
      thread : 持有偏向锁的线程ID
      epoch : 偏向时间戳
      ptr_to_lock_record : 指向栈中锁记录的指针
      ptr_to_heavyweight_monitor : 指向管程Monitor的指针
    ```

  - 我是64位系统,不管指针压不压缩,前8byte就是我的mark word

    ```none
      01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    ```
  
  - 这里没有我们要找的hashcode,因为我们hashcode不可能全为0
    - 原因 : 25位的对象标识Hash码,采用延迟加载技术.调用方法System.identityHashCode()计算,并会将结果写到该对象头中.当对象被锁定时,该值会移动到管程Monitor中.所以既然是延迟加载,我们得先触发一下
      > 在windows操作系统是小端存储,所以hashcode是逆序的,反过来存
  
    ```java
      public static void main(String[] args) {
        Object a = new Object();
        System.out.println(Integer.toHexString(a.hashCode()));
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
      }
      // 6d6f6e28
      // java.lang.Object object internals:
      // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //       0     4        (object header)                           01 28 6e 6f (00000001 00101000 01101110 01101111) (1869490177)
      //       4     4        (object header)                           6d 00 00 00 (01101101 00000000 00000000 00000000) (109)
      //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
      //     12     4        (loss due to the next object alignment)
      // Instance size: 16 bytes
      // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    ```

  - 我们可以看出来,我们的hashcode就在这56位上

    ```none
       01 `28` `6e` `6f` (00000001 00101000 01101110 01101111) (1869490177)
      `6d``00` `00` `00` (01101101 00000000 00000000 00000000) (109)
    ```

  - 解释
    > 无锁状态的mark_word :
    > unused : 25 无锁状态下mark wod中前25位没有被使用
    > identity_hashcode: 31 用于存储对象hashCode,包括前面的25个都是用来存储对象的hashCode,所以mark word前56为是用于存储无锁对象的hashCode
    > unused: 1 无锁状态下没有使用
    > age: 4 GC分代年龄,对象幸存的垃圾收集数量,每次在年轻一代中复制对象时,它都会递增,当年龄字段达到max-tenuring-threshold的值时,该对象将被晋升为老年代
    > biased_lock: 1 偏向锁标识 , 上面 00000001 , 说明不是偏向锁
    > lock: 2 对象状态 , 上面 00000001 , 说明是无锁

  - 锁一共占2位,最多表示4中情况,即 00(轻量级锁) 01(无锁) 10(重量级锁) 11(GC),但我们要表示的状态是5中,还有个偏向锁
    - 所以JVM中又规定了1位用来标示偏向锁,如果是1 : 则是偏向锁,0 : 不是偏向锁
      > 五种状态
      > 001 无锁
      > 101 偏向锁
      > 000  轻量级锁
      > 010  重量级锁
      > 011  GC标记

  - 补充:epoch的含义
    - 我们先从偏向锁的撤销讲起。当请求加锁的线程和锁对象标记字段保持的线程地址不匹配时（而且 epoch 值相等,如若不等,那么当前线程可以将该锁重偏向至自己）,Java 虚拟机需要撤销该偏向锁。这个撤销过程非常麻烦,它要求持有偏向锁的线程到达安全点,再将偏向锁替换成轻量级锁如果某一类锁对象的总撤销数超过了一个阈值（对应 jvm参数 -XX:BiasedLockingBulkRebiasThreshold,默认为 20）,那么 Java 虚拟机会宣布这个类的偏向锁失效（这里说的就是批量重偏向）
    - 具体的做法便是在每个类中维护一个 epoch 值,你可以理解为第几代偏向锁。当设置偏向锁时,Java 虚拟机需要将该 epoch 值复制到锁对象的标记字段中在宣布某个类的偏向锁失效时,Java 虚拟机实则将该类的 epoch 值加 1,表示之前那一代的偏向锁已经失效。而新设置的偏向锁则需要复制新的 epoch 值为了保证当前持有偏向锁并且已加锁的线程不至于因此丢锁,Java 虚拟机需要遍历所有线程的 Java 栈,找出该类已加锁的实例,并且将它们标记字段中的 epoch 值加 1。该操作需要所有线程处于安全点状态如果总撤销数超过另一个阈值（对应 jvm 参数 -XX:BiasedLockingBulkRevokeThreshold,默认值为 40）,那么 Java 虚拟机会认为这个类已经不再适合偏向锁。此时,Java 虚拟机会撤销该类实例的偏向锁,并且在之后的加锁过程中直接为该类实例设置轻量级锁(这里说的就是偏向批量撤销)

#### 观察各种锁状态

- 当没有竞争出现时,默认会使用偏向锁.JVM 会利用 CAS 操作(compare and swap),在对象头上的 Mark Word 部分设置线程 ID,以表示这个对象偏向于当前线程,所以并不涉及真正的互斥锁.这样做的假设是基于在很多应用场景中,大部分对象生命周期中最多会被一个线程锁定,使用偏向锁可以降低无竞争开销
- 如果有另外的线程试图锁定某个已经被偏向过的对象,JVM 就需要撤销(revoke)偏向锁,并切换到轻量级锁实现.轻量级锁依赖 CAS 操作 Mark Word 来试图获取锁,如果重试成功,就使用轻量级锁;否则,进一步升级为重量级锁

  - 观察轻量锁和偏向锁
    - 首先JVM在当一个线程进入到被Synchronized修饰的代码时(官方话叫临界区时-只允许一个线程进去执行操作的区域),就根据CAS(Compare and Swap)的操作将线程的id插入到Mark Work中指定区域(上图中54bit区域),并且将当前偏向锁状态改为1(上述的操作实际上就是一个偏向锁上锁的过程).那么代表当前线程已经获取执行同步代码块的权利了.当后续该线程进来时,如果该锁没有被其他锁获取到或者没发生锁竞争,那么就会再有任何的同步措施,即加锁或者解锁的措施了,只会进行Load-and-test,也就是简单判断一下当前线程id是否与Markword当中的线程id是否一致
    - 如果当前偏向锁出现了锁竞争的话,当前线程也会判断之前拥有锁的线程是否存在或者存在但没拥有该锁状态,就进行重置该偏向锁,并重新进行上锁过程,若仍然存在,此时该偏向锁就会升级为轻量级锁.在这个升级的过程中就会涉及到锁撤销的过程,锁撤销的过程也是满复杂的,资源的消耗也挺大的.所以如果我们的应用中大量都是存在多线程锁竞争的关系,那么不断的进行锁升级,其实是一个没必要的事情,此时我们可以在启动的时候设置-XX:-UseBiasedLocking,即该应用就不存在偏向锁了
      > JVM默认开启偏向锁,即 -XX:+UseBiasedLocking ,可以使用jinfo查看,默认,JVM启动前4秒内会加载大量系统类,而JVM认为这些类竞争性较强,所以这4s内这个偏向锁是关闭的,等到系统类加载完毕后,再开启偏向锁
      > 相关JVM运行时参数
      > -XX:+UseBiasedLocking 开启偏向锁
      > -XX:BiasedLockingStartupDelay=0 偏向锁立即生效
      > -XX:-UseBiasedLocking 禁用偏向锁

    ```java
      public static void main(String[] args) throws InterruptedException {
          Object a = new Object();
          System.out.println("==================加锁前=====================");
          synchronized (a){
              System.out.println("==================加锁后=====================");
              System.out.println(ClassLayout.parseInstance(a).toPrintable());
          }
          System.out.println("==================解锁后=====================");
      }
      // ==================加锁前=====================
      // ==================加锁后=====================
      // java.lang.Object object internals:
      //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //       0     4        (object header)                           78 f8 00 03 (01111000 11111000 00000000 00000011) (50395256)
      //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
      //      12     4        (loss due to the next object alignment)
      // Instance size: 16 bytes
      // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
      // ==================解锁后=====================
    ```

    > 可以看到当前锁状态为 01111000 -> 000,也就是轻量级锁,验证5s内偏向锁是关闭的

    ```java
      public static void main(String[] args) throws InterruptedException {
        //让程序延迟5s后在跑
        TimeUnit.SECONDS.sleep(5);
        Object a = new Object();
        System.out.println("==================加锁前=====================");
        synchronized (a){
            System.out.println("==================加锁后=====================");
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
        System.out.println("==================解锁后=====================");
      }
      // ==================加锁前=====================
      // ==================加锁后=====================
      // java.lang.Object object internals:
      //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //       0     4        (object header)                           05 38 2b 03 (00000101 00111000 00101011 00000011) (53164037)
      //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
      //      12     4        (loss due to the next object alignment)
      // Instance size: 16 bytes
      // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
      // ==================解锁后=====================
    ```

    > 可以看到当前锁状态为 00000101 -> 101,也就是偏向锁,验证5s后偏向锁开始生效(也可以设置 -XX:BiasedLockingStartupDelay=0 ,让偏向锁立即生效,则上面的不sleep的程序也会出现 101 )

  - 观察重量级锁

    ```java
      public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        // 开两个线程争抢 a 这个对象锁,使其变成重量级锁
        new Thread(()->{
            while (true)
                synchronized (a){
                    System.out.println("==================加锁后=====================");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                }
        }).start();
        new Thread(()->{
            while (true)
                synchronized (a){
                    System.out.println("==================加锁后=====================");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                }
        }).start();
        // ==================加锁后=====================
        // java.lang.Object object internals:
        //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //       0     4        (object header)                           8a 22 7f 1c (10001010 00100010 01111111 00011100) (478093962)
        //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
        //      12     4        (loss due to the next object alignment)
        // Instance size: 16 bytes
        // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
      }
    ```

    > 可以看到当前锁状态为 10001010 -> 010,也就是重量级锁

  - 观察锁降级

    ```java
      public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        new Thread(()->{
            int i = 0;
            while (i<5) {
                synchronized (a) {
                    System.out.println("==================加锁后=====================");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                }
                i++;
            }
        }).start();
        new Thread(()->{
            int i = 0;
            while (i<5) {
                synchronized (a) {
                    System.out.println("==================加锁后=====================");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                }
                i++;
            }
        }).start();

        TimeUnit.SECONDS.sleep(20);

        System.out.println("==================出来了=====================");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        TimeUnit.SECONDS.sleep(20);
        synchronized (a) {
            System.out.println("==================又加锁=====================");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
      }
      // ==================加锁后=====================  : 重量级锁
      // java.lang.Object object internals:
      // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //       0     4        (object header)                           ca 14 c2 1f (11001010 00010100 11000010 00011111) (532813002)
      //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
      //     12     4        (loss due to the next object alignment)
      // Instance size: 16 bytes
      // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
      // ==================出来了===================== : 出来后 无锁
      // java.lang.Object object internals:
      // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //       0     4        (object header)                           ca 14 c2 1f (11001010 00010100 11000010 00011111) (532813002)
      //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
      //     12     4        (loss due to the next object alignment)
      // Instance size: 16 bytes
      // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
      // ==================又加锁===================== : 重量级锁
      // java.lang.Object object internals:
      // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //       0     4        (object header)                           ca 14 c2 1f (11001010 00010100 11000010 00011111) (532813002)
      //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
      //     12     4        (loss due to the next object alignment)
      // Instance size: 16 bytes
      // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    ```

    > 上面测试的`又加锁`的情况可能会有不通,因为在垃圾回收的时候会产生锁降级,所以我在等待`又加锁`的过程中,使用jvisualVM工具不停执行垃圾回收,得到如下结果

    ```java
      // ==================加锁后=====================  : 重量级锁
      // java.lang.Object object internals:
      // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //       0     4        (object header)                           aa 36 5e 1c (10101010 00110110 01011110 00011100) (475936426)
      //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
      //     12     4        (loss due to the next object alignment)
      // Instance size: 16 bytes
      // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
      // ==================出来了===================== : 出来后 无锁
      // java.lang.Object object internals:
      // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //       0     4        (object header)                           09 2f e7 1c (00001001 00101111 11100111 00011100) (484912905)
      //       4     4        (object header)                           3e 00 00 00 (00111110 00000000 00000000 00000000) (62)
      //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
      //     12     4        (loss due to the next object alignment)
      // Instance size: 16 bytes
      // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
      // ==================又加锁===================== : 降级为轻量级锁
      // java.lang.Object object internals:
      // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      //       0     4        (object header)                           50 f6 8f 02 (01010000 11110110 10001111 00000010) (42989136)
      //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
      //     12     4        (loss due to the next object alignment)
      // Instance size: 16 bytes
      // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    ```

    > 具体细节可以看这篇博客 ![synchronized(三) 锁的膨胀过程(锁的升级过程)深入剖析](https://www.cnblogs.com/JonaLin/p/11571482.html)

  - 锁膨胀过程

    1. 首先判断是否开启了偏向锁
    2. 如果开启了偏向锁,执行步骤3,如果没开启偏向锁,执行步骤6
    3. 执行CAS获取偏向锁(fast_enter)
    4. 如果成功获取到了偏向锁,返回 return
    5. 如果获取失败,说明无法偏向vm的线程,则在safepoint消除偏向锁,接着执行步骤6
    6. 开始进行轻量级锁的获取逻辑(slow_enter)
    7. 判断mark->is_neutral(),true 执行步骤8,false 执行步骤10
    8. 将 mark word 复制到 Displaced Header
    9. 利用CAS将mark word重设,成功说明获取到了轻量锁,返回 return,失败执行步骤11
    10. 判断是否是自己持有锁,如果是,清除Displaced Header,返回,return,如果不是,执行步骤11
    11. 开始进行锁膨胀
    12. 整个膨胀过程在自旋下完成
    13. mark->has_monitor()方法判断当前是否为重量级锁,即Mark Word的锁标识位为 10,如果当前状态为重量级锁,执行步骤(14),否则执行步骤(15).
    14. mark->monitor()方法获取指向ObjectMonitor的指针,并返回,说明膨胀过程已经完成.
    15. 如果当前锁处于膨胀中,说明该锁正在被其它线程执行膨胀操作,则当前线程就进行自旋等待锁膨胀完成,这里需要注意一点,虽然是自旋操作,但不会一直占用cpu资源,每隔一段时间会通过os::NakedYield方法放弃cpu资源,或通过park方法挂起.如果其他线程完成锁的膨胀操作,则退出自旋并返回.
    16. 如果当前是轻量级锁状态,即锁标识位为 00,膨胀过程如下：
        通过omAlloc方法,获取一个可用的ObjectMonitor monitor,并重置monitor数据.
        通过CAS尝试将Mark Word设置为markOopDesc:INFLATING,标识当前锁正在膨胀中,如果CAS失败,说明同一时刻其它线程已经将Mark Word设置为markOopDesc:INFLATING,当前线程进行自旋等待膨胀完成.
        如果CAS成功,设置monitor的各个字段：_header. _owner和_object等,并返回.
    17. 如果是无锁,重置监视器值

  - 源码

  ```java
    //偏向锁
    void ObjectSynchronizer::fast_enter(Handle obj, BasicLock* lock,
                                        bool attempt_rebias, TRAPS) {
      if (UseBiasedLocking) { // 如果开启了偏向锁
        if (!SafepointSynchronize::is_at_safepoint()) {
          BiasedLocking::Condition cond = BiasedLocking::revoke_and_rebias(obj, attempt_rebias, THREAD);
          if (cond == BiasedLocking::BIAS_REVOKED_AND_REBIASED) {
            return;
          }
        } else {
          assert(!attempt_rebias, "can not rebias toward VM thread");
          BiasedLocking::revoke_at_safepoint(obj);
        }
        assert(!obj->mark()->has_bias_pattern(), "biases should be revoked by now");
      }

      slow_enter(obj, lock, THREAD);
    }
    //轻量级锁
    void ObjectSynchronizer::slow_enter(Handle obj, BasicLock* lock, TRAPS) {
      markOop mark = obj->mark();
      assert(!mark->has_bias_pattern(), "should not see bias pattern here");

      if (mark->is_neutral()) {
        // Anticipate successful CAS -- the ST of the displaced mark must
        // be visible <= the ST performed by the CAS.
        lock->set_displaced_header(mark);
        if (mark == obj()->cas_set_mark((markOop) lock, mark)) {
          return;
        }
        // Fall through to inflate() ...
      } else if (mark->has_locker() &&
                THREAD->is_lock_owned((address)mark->locker())) {
        assert(lock != mark->locker(), "must not re-lock the same lock");
        assert(lock != (BasicLock*)obj->mark(), "don't relock with same BasicLock");
        lock->set_displaced_header(NULL);
        return;
      }

      // The object header will never be displaced to this lock,
      // so it does not matter what the value is, except that it
      // must be non-zero to avoid looking like a re-entrant lock,
      // and must not look locked either.
      lock->set_displaced_header(markOopDesc::unused_mark());
      ObjectSynchronizer::inflate(THREAD,
                                  obj(),
                                  inflate_cause_monitor_enter)->enter(THREAD);
    }
    //锁膨胀
    ObjectMonitor* ObjectSynchronizer::inflate(Thread * Self,
                                                        oop object,
                                                        const InflateCause cause) {
      // Inflate mutates the heap ...
      // Relaxing assertion for bug 6320749.
      assert(Universe::verify_in_progress() ||
            !SafepointSynchronize::is_at_safepoint(), "invariant");

      EventJavaMonitorInflate event;

      for (;;) {
        const markOop mark = object->mark();
        assert(!mark->has_bias_pattern(), "invariant");

        // The mark can be in one of the following states:
        // *  Inflated     - just return
        // *  Stack-locked - coerce it to inflated
        // *  INFLATING    - busy wait for conversion to complete
        // *  Neutral      - aggressively inflate the object.
        // *  BIASED       - Illegal.  We should never see this

        // CASE: inflated
        if (mark->has_monitor()) {
          ObjectMonitor * inf = mark->monitor();
          assert(inf->header()->is_neutral(), "invariant");
          assert(oopDesc::equals((oop) inf->object(), object), "invariant");
          assert(ObjectSynchronizer::verify_objmon_isinpool(inf), "monitor is invalid");
          return inf;
        }

        // CASE: inflation in progress - inflating over a stack-lock.
        // Some other thread is converting from stack-locked to inflated.
        // Only that thread can complete inflation -- other threads must wait.
        // The INFLATING value is transient.
        // Currently, we spin/yield/park and poll the markword, waiting for inflation to finish.
        // We could always eliminate polling by parking the thread on some auxiliary list.
        if (mark == markOopDesc::INFLATING()) {
          ReadStableMark(object);                               // 这里会调用park将线程阻塞住
          continue;
        }

        // CASE: stack-locked
        // Could be stack-locked either by this thread or by some other thread.
        //
        // Note that we allocate the objectmonitor speculatively, _before_ attempting
        // to install INFLATING into the mark word.  We originally installed INFLATING,
        // allocated the objectmonitor, and then finally STed the address of the
        // objectmonitor into the mark.  This was correct, but artificially lengthened
        // the interval in which INFLATED appeared in the mark, thus increasing
        // the odds of inflation contention.
        //
        // We now use per-thread private objectmonitor free lists.
        // These list are reprovisioned from the global free list outside the
        // critical INFLATING...ST interval.  A thread can transfer
        // multiple objectmonitors en-mass from the global free list to its local free list.
        // This reduces coherency traffic and lock contention on the global free list.
        // Using such local free lists, it doesn't matter if the omAlloc() call appears
        // before or after the CAS(INFLATING) operation.
        // See the comments in omAlloc().

        if (mark->has_locker()) {
          ObjectMonitor * m = omAlloc(Self);
          // Optimistically prepare the objectmonitor - anticipate successful CAS
          // We do this before the CAS in order to minimize the length of time
          // in which INFLATING appears in the mark.
          m->Recycle();
          m->_Responsible  = NULL;
          m->_recursions   = 0;
          m->_SpinDuration = ObjectMonitor::Knob_SpinLimit;   // Consider: maintain by type/class

          markOop cmp = object->cas_set_mark(markOopDesc::INFLATING(), mark);
          if (cmp != mark) {
            omRelease(Self, m, true);
            continue;       // Interference -- just retry
          }

          // We've successfully installed INFLATING (0) into the mark-word.
          // This is the only case where 0 will appear in a mark-word.
          // Only the singular thread that successfully swings the mark-word
          // to 0 can perform (or more precisely, complete) inflation.
          //
          // Why do we CAS a 0 into the mark-word instead of just CASing the
          // mark-word from the stack-locked value directly to the new inflated state?
          // Consider what happens when a thread unlocks a stack-locked object.
          // It attempts to use CAS to swing the displaced header value from the
          // on-stack basiclock back into the object header.  Recall also that the
          // header value (hashcode, etc) can reside in (a) the object header, or
          // (b) a displaced header associated with the stack-lock, or (c) a displaced
          // header in an objectMonitor.  The inflate() routine must copy the header
          // value from the basiclock on the owner's stack to the objectMonitor, all
          // the while preserving the hashCode stability invariants.  If the owner
          // decides to release the lock while the value is 0, the unlock will fail
          // and control will eventually pass from slow_exit() to inflate.  The owner
          // will then spin, waiting for the 0 value to disappear.   Put another way,
          // the 0 causes the owner to stall if the owner happens to try to
          // drop the lock (restoring the header from the basiclock to the object)
          // while inflation is in-progress.  This protocol avoids races that might
          // would otherwise permit hashCode values to change or "flicker" for an object.
          // Critically, while object->mark is 0 mark->displaced_mark_helper() is stable.
          // 0 serves as a "BUSY" inflate-in-progress indicator.


          // fetch the displaced mark from the owner's stack.
          // The owner can't die or unwind past the lock while our INFLATING
          // object is in the mark.  Furthermore the owner can't complete
          // an unlock on the object, either.
          markOop dmw = mark->displaced_mark_helper();
          assert(dmw->is_neutral(), "invariant");

          // Setup monitor fields to proper values -- prepare the monitor
          m->set_header(dmw);

          // Optimization: if the mark->locker stack address is associated
          // with this thread we could simply set m->_owner = Self.
          // Note that a thread can inflate an object
          // that it has stack-locked -- as might happen in wait() -- directly
          // with CAS.  That is, we can avoid the xchg-NULL .... ST idiom.
          m->set_owner(mark->locker());
          m->set_object(object);
          // TODO-FIXME: assert BasicLock->dhw != 0.

          // Must preserve store ordering. The monitor state must
          // be stable at the time of publishing the monitor address.
          guarantee(object->mark() == markOopDesc::INFLATING(), "invariant");
          object->release_set_mark(markOopDesc::encode(m));

          // Hopefully the performance counters are allocated on distinct cache lines
          // to avoid false sharing on MP systems ...
          OM_PERFDATA_OP(Inflations, inc());
          if (log_is_enabled(Debug, monitorinflation)) {
            if (object->is_instance()) {
              ResourceMark rm;
              log_debug(monitorinflation)("Inflating object " INTPTR_FORMAT " , mark " INTPTR_FORMAT " , type %s",
                                          p2i(object), p2i(object->mark()),
                                          object->klass()->external_name());
            }
          }
          if (event.should_commit()) {
            post_monitor_inflate_event(&event, object, cause);
          }
          return m;
        }

        // CASE: neutral
        // TODO-FIXME: for entry we currently inflate and then try to CAS _owner.
        // If we know we're inflating for entry it's better to inflate by swinging a
        // pre-locked objectMonitor pointer into the object header.   A successful
        // CAS inflates the object *and* confers ownership to the inflating thread.
        // In the current implementation we use a 2-step mechanism where we CAS()
        // to inflate and then CAS() again to try to swing _owner from NULL to Self.
        // An inflateTry() method that we could call from fast_enter() and slow_enter()
        // would be useful.

        assert(mark->is_neutral(), "invariant");
        ObjectMonitor * m = omAlloc(Self);
        // prepare m for installation - set monitor to initial state
        m->Recycle();
        m->set_header(mark);
        m->set_owner(NULL);
        m->set_object(object);
        m->_recursions   = 0;
        m->_Responsible  = NULL;
        m->_SpinDuration = ObjectMonitor::Knob_SpinLimit;       // consider: keep metastats by type/class

        if (object->cas_set_mark(markOopDesc::encode(m), mark) != mark) {
          m->set_object(NULL);
          m->set_owner(NULL);
          m->Recycle();
          omRelease(Self, m, true);
          m = NULL;
          continue;
          // interference - the markword changed - just retry.
          // The state-transitions are one-way, so there's no chance of
          // live-lock -- "Inflated" is an absorbing state.
        }

        // Hopefully the performance counters are allocated on distinct
        // cache lines to avoid false sharing on MP systems ...
        OM_PERFDATA_OP(Inflations, inc());
        if (log_is_enabled(Debug, monitorinflation)) {
          if (object->is_instance()) {
            ResourceMark rm;
            log_debug(monitorinflation)("Inflating object " INTPTR_FORMAT " , mark " INTPTR_FORMAT " , type %s",
                                        p2i(object), p2i(object->mark()),
                                        object->klass()->external_name());
          }
        }
        if (event.should_commit()) {
          post_monitor_inflate_event(&event, object, cause);
        }
        return m;
      }
    }


  ```

#### 偏向锁

- 偏向锁是指一段同步代码一直被一个线程所访问,那么该线程会自动获取锁,降低获取锁的代价。
- 在大多数情况下,锁总是由同一线程多次获得,不存在多线程竞争,所以出现了偏向锁。其目标就是在**只有一个线程执行同步代码块**时能够提高性能。
- 当一个线程访问同步代码块并获取锁时,会在Mark Word里存储锁偏向的线程ID。在线程进入和退出同步块时不再通过CAS操作来加锁和解锁,而是检测Mark Word里是否存储着指向当前线程的偏向锁。引入偏向锁是为了在无多线程竞争的情况下尽量减少不必要的轻量级锁执行路径,**因为轻量级锁的获取及释放依赖多次CAS原子指令,而偏向锁只需要在置换ThreadID的时候依赖一次CAS原子指令即可**。
- 偏向锁只有遇到其他线程尝试竞争偏向锁时,持有偏向锁的线程才会释放锁,线程不会主动释放偏向锁。偏向锁的撤销,需要等待全局安全点（在这个时间点上没有字节码正在执行）,它会首先暂停拥有偏向锁的线程,判断锁对象是否处于被锁定状态。撤销偏向锁后恢复到无锁（标志位为“01”）或轻量级锁（标志位为“00”）的状态。
- 偏向锁在JDK 6及以后的JVM里是默认启用的。可以通过JVM参数关闭偏向锁：-XX:-UseBiasedLocking=false,关闭之后程序默认会进入轻量级锁状态。

- 偏向锁升级为轻量级锁逻辑
  > 线程1持有偏向锁,线程2来竞争锁对象;
  > 判断当前对象头是否是偏向锁;
  > 判断拥有偏向锁的线程1是否还存在;
  > 线程1不存在,直接设置偏向锁标识为0(线程1执行完毕后,不会主动去释放偏向锁);
  > 使用cas替换偏向锁线程ID为线程2,锁不升级,仍为偏向锁;
  > 线程1仍然存在,暂停线程1
  > 设置锁标志位为00(变为轻量级锁),偏向锁为0;
  > 从线程1的空闲monitor record中读取一条,放至线程1的当前monitor record中;
  > 更新mark word,将mark word指向线程1中monitor record的指针;
  > 继续执行线程1的代码;
  > 锁升级为轻量级锁;
  > 线程2自旋来获取锁对象;

  ```java
    //不确定线程1是否停止
    public static void main(String[] args) throws InterruptedException {
      Object a = new Object();
      int i = 0;
      while (true) {
          i++;
          System.out.println("================== "+i+" ======================");
          Thread t1 = new Thread(() -> {
              synchronized (a) {
                  System.out.println("==================t1加锁后=====================");
                  System.out.println(Thread.currentThread().getName());
                  System.out.println(ClassLayout.parseInstance(a).toPrintable());
              }
              try {
                  TimeUnit.SECONDS.sleep(5);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              synchronized (a) {
                  System.out.println("==================t1加锁后=====================");
                  System.out.println(Thread.currentThread().getName());
                  System.out.println(ClassLayout.parseInstance(a).toPrintable());
              }
          }, "t1");
          t1.start();
          try {
              TimeUnit.SECONDS.sleep(20);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          Thread t2 = new Thread(() -> {

              synchronized (a) {
                  System.out.println("==================t2加锁后=====================");
                  System.out.println(Thread.currentThread().getName());
                  System.out.println(ClassLayout.parseInstance(a).toPrintable());
              }
          }, "t2");
          t2.start();
          try {
              TimeUnit.SECONDS.sleep(10);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
    }
    // ================== 1 ======================
    // ==================t1加锁后=====================
    // t1
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           05 c8 dc 1f (00000101 11001000 11011100 00011111) (534562821)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    // ==================t1加锁后=====================
    // t1
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           05 c8 dc 1f (00000101 11001000 11011100 00011111) (534562821)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    // ==================t2加锁后=====================   : 由于t1线程还存活,所以锁变成轻量级锁
    // t2
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           40 f5 66 20 (01000000 11110101 01100110 00100000) (543618368)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
  ```

  ```java
    //确定线程1一定停止
    public static void main(String[] args) throws InterruptedException {
      Object a = new Object();
      int i = 0;
      while (true) {
          i++;
          System.out.println("================== "+i+" ======================");
          Thread t1 = new Thread(() -> {
              synchronized (a) {
                  System.out.println("==================t1加锁后=====================");
                  System.out.println(Thread.currentThread().getName());
                  System.out.println(ClassLayout.parseInstance(a).toPrintable());
              }
              try {
                  TimeUnit.SECONDS.sleep(5);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              synchronized (a) {
                  System.out.println("==================t1加锁后=====================");
                  System.out.println(Thread.currentThread().getName());
                  System.out.println(ClassLayout.parseInstance(a).toPrintable());
              }
          }, "t1");
          t1.start();
          try {
              TimeUnit.SECONDS.sleep(20);
              t1.stop(); //保证线程1停止
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          Thread t2 = new Thread(() -> {

              synchronized (a) {
                  System.out.println("==================t2加锁后=====================");
                  System.out.println(Thread.currentThread().getName());
                  System.out.println(ClassLayout.parseInstance(a).toPrintable());
              }
          }, "t2");
          t2.start();
          try {
              TimeUnit.SECONDS.sleep(10);
              t2.stop(); // 保证线程2停止
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
    }
    // ================== 1 ======================
    // ==================t1加锁后=====================
    // t1
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           05 68 c9 1f (00000101 01101000 11001001 00011111) (533293061)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    // ==================t1加锁后=====================
    // t1
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           05 68 c9 1f (00000101 01101000 11001001 00011111) (533293061)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    // ==================t2加锁后===================== : 由于线程1停止,所以线程2能够抢到偏向锁
    // t2
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           05 68 c9 1f (00000101 01101000 11001001 00011111) (533293061)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

    // ================== 2 ======================
    // ==================t1加锁后=====================
    // t1
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           05 68 c9 1f (00000101 01101000 11001001 00011111) (533293061)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    // ==================t1加锁后=====================  : 前几次对这个锁的争抢因为是交替的,所以2个线程获取的都是偏向锁
    // t1
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           05 68 c9 1f (00000101 01101000 11001001 00011111) (533293061)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    // ==================t2加锁后===================== : 但是多次竞争后,jvm会将锁变成轻量级锁,不确定这里争抢的次数,大概在4-6次之间
    // t2
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           50 ee 50 20 (01010000 11101110 01010000 00100000) (542174800)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

    // ================== 3 ======================
    // ==================t1加锁后=====================
    // t1
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           98 f3 27 01 (10011000 11110011 00100111 00000001) (19395480)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    // ==================t1加锁后=====================
    // t1
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           98 f3 27 01 (10011000 11110011 00100111 00000001) (19395480)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
  ```

  > 上面的偏向锁认真看的话,会发现t1 t2,所拿到的锁的标志都是同样的 `533293061`,原因应该是threadid是存放在markword中的,而markword被封装为一个markOop对象,这里存的是markOop对象的引用,修改是修改指定位置的markOop对象中的treadid,而不是直接创建新的markop对象
  > 当分配一个对象并且这个对象能够执行偏向的时候并且还没有偏向时,会执行CAS是的当前线程ID放入到mark word的线程ID区域。
  > 如果成功,对象本身就会被偏向到当前线程,当前线程会成为偏向所有者,即线程ID直接指向JVM内部表示的线程
  > 如果CAS失败了,即另一个线程已经成为偏向的所有者,这意味着这个线程的偏向必须撤销。对象的状态会变成轻量锁的模式,为了达到这一点,尝试把对象偏向于自己的线程必须能
  > 够操作偏向所有者的栈,为此需要全局安全点已经触达（没有线程在执行字节码）。此时偏向拥有者会像轻量级锁操作那样,它的堆栈会填入锁记录,然后对象本身的mark word会
  > 被更新成指向栈上最老的锁记录,然后线程本身在安全点的阻塞会被释放
  > 下一个获取锁的操作会与检测对象的mark word,如果对象是可偏向的,并且偏向的所有者是当前那线程,会没有任何额外操作而立马获取锁。这个时候偏向锁的持有者的栈不会初始化锁记录,因为对象偏向的时候,是永远不会检验锁记录的
  > unlock的时候,会测试mark word的状态,看是否仍然有偏向模式。如果有,就不会再做其它的测试,甚至不需要管线程ID是不是当前线程ID
  > 这里通过解释器的保证monitorexit操作只会在当前线程执行,所以这也是一个不需要检查的理由
  > 所以两个线程看到的锁是一个
  > 参考 ![偏向锁状态转移原理](https://juejin.im/post/5c17964df265da6157056588)

#### 轻量级锁

- 当锁是偏向锁的时候,被另外的线程所访问,偏向锁就会升级为轻量级锁,其他线程会通过自旋的形式尝试获取锁,不会阻塞,从而提高性能。
- 在代码进入同步块的时候,如果同步对象锁状态为无锁状态（锁标志位为“01”状态,是否为偏向锁为“0”）,虚拟机首先将在当前线程的栈帧中建立一个名为锁记录（Lock Record）的空间,用于存储锁对象目前的Mark Word的拷贝,然后拷贝对象头中的Mark Word复制到锁记录中。
- 拷贝成功后,虚拟机将使用CAS操作尝试将对象的Mark Word更新为指向Lock Record的指针,并将Lock Record里的owner指针指向对象的Mark Word。
- 如果这个更新动作成功了,那么这个线程就拥有了该对象的锁,并且对象Mark Word的锁标志位设置为“00”,表示此对象处于轻量级锁定状态。
- 如果轻量级锁的更新操作失败了,虚拟机首先会检查对象的Mark Word是否指向当前线程的栈帧,如果是就说明当前线程已经拥有了这个对象的锁,那就可以直接进入同步块继续执行,否则说明多个线程竞争锁。
- 若当前只有一个等待线程,则该线程通过自旋进行等待。但是当自旋超过一定的次数,或者一个线程在持有锁,一个在自旋,又有第三个来访时,轻量级锁升级为重量级锁。
- 多个线程在不同的时间段请求同一把锁,也就是说没有锁竞争。针对这种情形,Java 虚拟机采用了轻量级锁,来避免重量级锁的阻塞以及唤醒
- 在没有锁竞争的前提下,减少传统锁使用OS互斥量产生的性能损耗
- 在竞争激烈时,轻量级锁会多做很多额外操作,导致性能下降
- 可以认为两个线程交替执行的情况下请求同一把锁

#### 重量级锁

- 2个以上的线程争抢锁时,会升级为重量级锁

  ```java
    public static void main(String[] args) throws InterruptedException {
      Object a = new Object();
      Thread t1 = new Thread(() -> {
          try {
              TimeUnit.SECONDS.sleep(5);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          synchronized (a) {
              System.out.println("==================t1加锁后=====================");
              System.out.println(Thread.currentThread().getName());
              System.out.println(ClassLayout.parseInstance(a).toPrintable());
          }
      }, "t1");
      t1.start();

      Thread t2 = new Thread(() -> {
          try {
              TimeUnit.SECONDS.sleep(15);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          synchronized (a) {
              System.out.println("==================t2加锁后=====================");
              System.out.println(Thread.currentThread().getName());
              System.out.println(ClassLayout.parseInstance(a).toPrintable());
          }
      }, "t2");
      t2.start();

      Thread t3 = new Thread(() -> {
          try {
              TimeUnit.SECONDS.sleep(15);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          synchronized (a) {
              System.out.println("==================t3加锁后=====================");
              System.out.println(Thread.currentThread().getName());
              System.out.println(ClassLayout.parseInstance(a).toPrintable());
          }
      }, "t3");
      t3.start();
    }
    // ==================t1加锁后=====================
    // t1
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           05 48 af 1f (00000101 01001000 10101111 00011111) (531580933)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    // ==================t3加锁后===================== : t2 t3 争抢锁时,t1线程还没死掉,也就是3根线程同时抢锁,锁变成重量级锁
    // t3
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           2a 34 df 1c (00101010 00110100 11011111 00011100) (484389930)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    // ==================t2加锁后=====================
    // t2
    // java.lang.Object object internals:
    //  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
    //       0     4        (object header)                           2a 34 df 1c (00101010 00110100 11011111 00011100) (484389930)
    //       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    //      12     4        (loss due to the next object alignment)
    // Instance size: 16 bytes
    // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
  ```

#### 总结

- 一共有无锁,偏向锁,轻量级锁,重量级锁四个锁状态,另外一个是GC标志
- 无锁(001):对象没有进行中的同步
- 偏向锁:当前有且仅有一条线程在操作这个对象,为了提高性能,标记一下持有锁的线程,方便重入即可,CAS一次即可(而偏向锁只需要在置换ThreadID的时候依赖一次CAS原子指令)
- 轻量级锁:当前有两个线程在争抢同一把锁(侧重于交替执行),需要将锁置成轻量级锁(00),同时没有抢到的线程就自旋等待,其中涉及到多次CAS操作(轻量级锁的加锁解锁操作是需要依赖多次CAS原子指令的)
- 重量级锁:当前2条及其以上线程在抢同一把锁,这时JVM认为竞争严重,将锁提升为重量级锁,没有获取到执行权的线程会被挂起,获取到执行权的线程释放锁的时候需要唤醒其他线程,性能较低,故AQS诞生了
- 锁降级:锁降级1.6之后确实是会发生的,当 JVM 进入安全点（SafePoint）的时候,会检查是否有闲置的 Monitor,然后试图进行降级
  > 有兴趣的大佬可以在 https://hg.openjdk.java.net/jdk/jdk/file/896e80158d35/src/hotspot/share/runtime/synchronizer.cpp 链接中
  > 研究一下deflate_idle_monitors是分析锁降级逻辑的入口,这部分行为还在进行持续改进,因为其逻辑是在安全点内运行,处理不当可能拖长 JVM 停顿（STW,stop-the-world）的时间。

#### wait 和 notify

- 准备进入sync方法的线程,会被封装为ObjectWaiter对象,会在 _EntryList 中等待
- 如果获取到了执行权,就将当前线程转移到_owner
- 如果在_owner中,调用了wait方法,则会将当前线程放入wait set,同时释放锁,owner变量恢复为null，count自减1
- wait
  - 会将当前线程放入对象的wait set 休息室
- notify
  - 唤醒wait set中的某个线程
- notifyAll
  - 唤醒wait set中全部线程

### ReentrantLock

#### AQS

##### 基础 : LockSupport

- park
  - 效果:让线程进入阻塞,效果与sync中的wait方法相同
- parkNanos
  - 效果:让线程阻塞指定时间后,自动唤醒,tryXXX方法都是依赖这个方法实现的
- unPark
  - 效果:唤醒线程,效果和notify效果相同,只能唤醒一条指定线程

```java
  public static void main(String[] args) {
     new Thread(()->{
       //情况1
  //           try {
  //               TimeUnit.MINUTES.sleep(1);// TIMED_WAITING (sleeping)
  //           } catch (InterruptedException e) {
  //               e.printStackTrace();
  //           }
       //情况2
  //           LockSupport.park(); // WAITING (parking)
       //情况3
       Object o = new Object();
       synchronized (o){
         try {
           o.wait(); // WAITING (on object monitor)
         } catch (InterruptedException e) {
           e.printStackTrace();
         }
       }
     },"t1").start();
  
    }
```

#### 从lock方法开始

- 公平锁

```java
  public void lock() {
      sync.lock(); //这个sync就是AQS的实现,后续调用的都是AQS的代码
  }
  //公平锁
  //AQS.lock
  final void lock() {
      acquire(1);
  }
  //AQS.acquire
  public final void acquire(int arg) {
      // 1.调用tryAcquire尝试获取锁,如果成功则方法不再继续执行,如果失败继续执行步骤2
      // 2.调用AQS的addWaiter(Node.EXCLUSIVE)方法
      if (!tryAcquire(arg) && //tryAcquire(arg)返回true,整个结果直接false,说明获取到了锁,这个方法就过去了,如果返回false,说明尝试获锁失败,则!tryAcquire(arg)为true,继续走 acquireQueued(addWaiter(Node.EXCLUSIVE), arg) 逻辑
          acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) // Node.EXCLUSIVE -> null , addWaiter(Node.EXCLUSIVE)最后返回新添加到队列的节点
                                                         // acquireQueued(addWaiter(Node.EXCLUSIVE), arg),如果返回fasle,说明没被打断,正常唤醒;如果返回true,说明被打断了
          selfInterrupt(); // 调用 Thread.currentThread().interrupt(); 由于acquireQueued中的打断是使用静态方法打断的,线程打断状态现在变成了false,所以再重新标记一下打断状态,interrupt()实例方法,只能修改状态,不能打断线程
  }
  protected final boolean tryAcquire(int acquires) {
      final Thread current = Thread.currentThread();
      //第一次进来,这里状态是0,有线程获取到锁后,这个状态会被改变
      int c = getState();
      if (c == 0) {
          if (!hasQueuedPredecessors() &&         // 看下当前有没有排队的节点,由于是公平锁,所以只要有排队,我就去排队,没人排队我才尝试直接获取锁,这个过程不入队
              compareAndSetState(0, acquires)) {  // 尝试CAS修改state,成功说明获取到锁
              setExclusiveOwnerThread(current);   // 设置当前线程为持锁线程
              return true;                        // 成功获取到锁
          }
      }
      else if (current == getExclusiveOwnerThread()) { // 可重入
          int nextc = c + acquires;                    // 如果是自己,就给state+1
          if (nextc < 0)                               // 只有int最大值+1后为负数,所以重入次数有限制
              throw new Error("Maximum lock count exceeded");
          setState(nextc);
          return true;                                 // 成功获取到重入锁
      }
      return false;                                 // 没有获取到锁
  }
  public final boolean hasQueuedPredecessors() {
      // The correctness of this depends on head being initialized
      // before tail and on head.next being accurate if the current
      // thread is first in queue.
      Node t = tail; // Read fields in reverse initialization order    // 这里的Node包括本身,头,尾,当前线程,组成双向链表
      Node h = head;
      Node s;
      return h != t &&  // 如果当前头!=尾 && (头的下一个节点 == null 或者 头的下一个节点中的线程对象不是当前的线程)
          ((s = h.next) == null || s.thread != Thread.currentThread()); //  (s = h.next) == null 和上一句不是原子操作,所以这里还得判断一次
      // 如果头 == 尾,则h != t 直接 返回 false
      // 如果头 != 尾,则接着判断第二个节点 == null,如果==null,则返回true
      // 如果头 != 尾,则接着判断第二个节点 == null,如果!=null,则接着判断第二个节点的线程是否为当前线程,如果是返回false
      // 如果头 != 尾,则接着判断第二个节点 == null,如果!=null,则接着判断第二个节点的线程是否为当前线程,如果不是返回true
      // 调用出调用方法目的是返回一个boolean值,如果得到的结果是true,则会尝试获取锁绑定当前线程,但要注意前面还有个!取非,所以这里返回false,才会进一步获取锁
      // 结论 : 也就是如果队列没有节点,或者第二个节点中绑定的线程是当前自己线程本身,就返回false,也就是可以进一步获取锁
      // 这里判断第二个节点是因为第一个节点中不存线程,只是一个占位的节点,所以第二个节点相当于是队列中第一个等待的线程
      // 头等于尾有两种情况,1头尾均为null,说明队列还未初始化;2队列中只有一个节点,头尾均为它,这时返回false
  }
  private Node addWaiter(Node mode) {  // 节点状态
      Node node = new Node(Thread.currentThread(), mode); //根据状态不同新建一个节点
      // Try the fast path of enq; backup to full enq on failure
      Node pred = tail;
      if (pred != null) { //尾节点不为null,说明队列中有节点
          node.prev = pred; //新建节点前向关联尾节点
          if (compareAndSetTail(pred, node)) { //CAS原子替换尾节点为新new节点
              pred.next = node; //成功才让之前尾节点向后关联当前节点,双向链表建立关系
              return node; //返回新加入的节点
          }
      }
      enq(node); // 队列没数据,这个方法不仅要初始化队列,还要添加新节点
      return node; //到此新node已经被添加到队列中
  }
  private Node enq(final Node node) {
      for (;;) {
          Node t = tail;
          if (t == null) { // Must initialize  尾节点为null , 开始初始化
              if (compareAndSetHead(new Node())) //尝试创建一个新空节点设置到队列头
                  tail = head;  // 成功就把尾和头设置成一样的 : 这里说明了队列初始化后头尾指向同一个节点,且这个节点是空节点,占位用
          } else { //接着自旋回来以后
              node.prev = t; //还是先将新节点前一个关联上队列尾
              if (compareAndSetTail(t, node)) { // cas设置尾节点为新节点
                  t.next = node; //成功在将旧尾节点的下一个设置为新节点
                  return t; //返回旧尾节点
              }
          }
      }
  }
  final boolean acquireQueued(final Node node, int arg) {
      boolean failed = true;
      try {
          boolean interrupted = false;
          for (;;) {
              final Node p = node.predecessor(); //获取当前节点的前一个节点,如果前一个节点为null,会抛出异常,一般不会出现这种情况,节点都是先设置前一个,才将自己入队
              if (p == head && tryAcquire(arg)) { //如果前一个节点为head节点,说明我们是第二个,也就是第一个排队的节点,尝试获取锁
                  setHead(node); //获取成功,将自己设置成head节点
                  p.next = null; // help GC //将前一个节点和自己的关系断开
                  failed = false; //修改状态为false,finally中判断,由于已经获取到了锁,不需要执行cancelAcquire方法
                  return interrupted; //默认false,如果被下面打断过就是true
              }
              //不是能获取锁的节点
              if (shouldParkAfterFailedAcquire(p, node) && //这里第一次进来时,传入的p的状态应该是0,然后CAS改成SIGNAL,返回false,然后自旋回来以后,在调用这个方法,返回就是true了,后一个改前一个状态,由于自己状态自己不改,总要让后一个来改,所以这里肯定自旋2次,也就是上面的方法会判断两次,第二次还没获取上锁,才会来到这里
                  parkAndCheckInterrupt()) // 进来就park了,等待唤醒,返回Thread.interrupted()的值
                  interrupted = true; // 能进来说明上面 parkAndCheckInterrupt() 返回的是true,是被打断的,否则进不来
                  // lock 和 lockInterruptibly两个方法这里的实现不同,不能打断的只是传了个标记出去,线程继续执行,能打断的这里抛了个异常
          }
      } finally {
          if (failed) //其他异常为true,如果没有其他异常,一定会被改成false
              cancelAcquire(node);
      }
  }
  private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
      int ws = pred.waitStatus;
      if (ws == Node.SIGNAL)  // Node.SIGNAL = -1
          /*
            * This node has already set status asking a release
            * to signal it, so it can safely park.
            */
          return true;
      if (ws > 0) { // 只有CANCELLED状态大于0
          /*
            * Predecessor was cancelled. Skip over predecessors and
            * indicate retry.
            */
          do {
              node.prev = pred = pred.prev;
          } while (pred.waitStatus > 0);
          pred.next = node;
      } else { //一般的我们调用lock进来创建的节点都没设置过这个状态 , 也就是默认 0 ,进入这个分支
          /*
            * waitStatus must be 0 or PROPAGATE.  Indicate that we
            * need a signal, but don't park yet.  Caller will need to
            * retry to make sure it cannot acquire before parking.
            */
          compareAndSetWaitStatus(pred, ws, Node.SIGNAL); // 将p这个节点的状态设置为 SIGNAL
      }
      return false;
  }
  private final boolean parkAndCheckInterrupt() {
      LockSupport.park(this); //进来就park了
      return Thread.interrupted(); //如果是被打断的,就返回true,由于调用的是静态方法,线程的打断状态又变成了false,但返回的值为true
  }



  //解锁 公平与非公平锁都是一样的
  public void unlock() {
      sync.release(1);
  }
  public final boolean release(int arg) {
    if (tryRelease(arg)) { // 返回true表示锁被完全释放,false只是释放了一部分(重入锁)
        Node h = head; //锁全部释放,当然要唤醒其他线程来执行操作
        if (h != null && h.waitStatus != 0) // waitStatus被后一根线程置为-1(SIGNAL),如果没有后一根线程,则还是0,没有后一根线程自然不用唤醒了
            unparkSuccessor(h);
        return true; //锁全部释放了
    }
    return false; //没全释放掉,返回false,但是上面没接
  }
  protected final boolean tryRelease(int releases) {
      int c = getState() - releases; // 当前状态 - 1
      if (Thread.currentThread() != getExclusiveOwnerThread()) //判断操作线程是否为当前持锁线程,不是就抛出异常
                                                                //所以这里抛出异常并不是类似sync中那种没有持锁,而是固定抛出异常,整个ReentrantLock中其实没
                                                                //有锁,只是你来了能CAS获取锁,你就正常执行,不行我这边给你返回false,或者park阻塞你,不让你继
                                                                //续执行,和锁无关,所以显示锁是在代码层面给做的处理,效率要高于sync
          throw new IllegalMonitorStateException();
      boolean free = false; //锁的释放状态,重入锁释放一部分并不能完全释放锁
      if (c == 0) { // 说明锁全部释放了,重入锁也释放了
          free = true; //所被释放了
          setExclusiveOwnerThread(null); // 持锁线程清空
      }
      setState(c); // 重新设置state
      return free;
  }
  private void unparkSuccessor(Node node) {
      /*
        * If status is negative (i.e., possibly needing signal) try
        * to clear in anticipation of signalling.  It is OK if this
        * fails or if status is changed by waiting thread.
        */
      int ws = node.waitStatus;
      if (ws < 0) // 说明是SIGNAL
          compareAndSetWaitStatus(node, ws, 0); //将其状态置为0,这个方法和shouldParkAfterFailedAcquire方法中的修改操作互斥,所以能保证唤醒在

      /*
        * Thread to unpark is held in successor, which is normally
        * just the next node.  But if cancelled or apparently null,
        * traverse backwards from tail to find the actual
        * non-cancelled successor.
        */
      Node s = node.next; // 队列中第一个排队的
      if (s == null || s.waitStatus > 0) { //只有CANCEL > 0
          s = null;
          for (Node t = tail; t != null && t != node; t = t.prev) //当前链的状态应该是 0(头刚被置为0) <- -1(被后面的线程置为-1) <- -1 <- 0(最后没有人改他)
              if (t.waitStatus <= 0) //倒着查,最后一个状态小于=0的,且不为头节点的 : 就是第二个节点嘛
                  s = t;
      }
      if (s != null) // 发现节点就给它唤醒
          LockSupport.unpark(s.thread);
  }
```

> 总结 :
> 第一次获取锁,先看有没有队列,有就去排队(体现公平锁机制),没有就直接不通过入队,直接获取到锁,如果是重入,也直接获取到了锁
> 第二次获取锁,无法直接获取锁,state被第一个线程改了,尝试入队
> 如果队列为null,则先创建一个空节点,head和tail都指向他,然后再将新节点加入到尾部,组成双向链表(链表中第二个节点才是排队中的第一个节点)
> 节点入队后,还未park,接着进入自旋,第一次先看自己是不是第二个,如果是就尝试获取锁,获取成功就返回,不成功就将前一个节点的ws状态改成SIGNAL(-1)
> 然后接着自旋第二次,再看自己是不是第二个节点,如果是就尝试获取锁,获取成功就返回,获取不成功,就将自己park掉
> 然后等待被打断或者唤醒,如果唤醒就接着自旋,直到获取到锁(公平锁肯定是他获取了,因为他是第二个,而且一次就唤醒一条线程,没人跟他抢)
> 如果被打断(lock方法)只是传回去一个打断状态,并无实际意义,(lockInterruptibly)会直接抛出异常,打断正在执行的线程

> 释放锁就简单了
> 先看看是否为当前持锁线程来释放,如果是就state-1,重新设置state(单线程不用加锁),如果不是就抛出异常
> 同时判断释放完毕后的状态是否为0,如果是说明锁被完全释放,准备唤醒下一个线程去执行任务,如果不为0,说明只是重入锁释放了一部分,不需要唤醒其他线程
> 锁释放完毕

- 非公平锁

```java
  public void lock() {
      sync.lock();
  }
  //非公平锁
  final void lock() {
      if (compareAndSetState(0, 1)) // 进来就先尝试抢锁,抢成功,就将持锁线程设置为自己
          setExclusiveOwnerThread(Thread.currentThread());
      else
          acquire(1);
  }
  public final void acquire(int arg) {
      if (!tryAcquire(arg) &&
          acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) //后半段逻辑与公平锁一致
          selfInterrupt();
  }
  protected final boolean tryAcquire(int acquires) { //就这个方法是不一样的逻辑
      return nonfairTryAcquire(acquires);
  }
  final boolean nonfairTryAcquire(int acquires) {
      final Thread current = Thread.currentThread();
      int c = getState();
      if (c == 0) { // 如果当前状态是无锁状态
          if (compareAndSetState(0, acquires)) { //就先尝试抢锁  公平锁在这里先判断是否要排队,非公平锁直接就抢锁
              setExclusiveOwnerThread(current); // 成功就将持锁线程设置为自己
              return true;
          }
      }
      else if (current == getExclusiveOwnerThread()) { //可重入
          int nextc = c + acquires;
          if (nextc < 0) // overflow
              throw new Error("Maximum lock count exceeded");
          setState(nextc);
          return true;
      }
      return false;
  }
```

> 总结
> 非公平锁不去判断排队状态直接抢锁,进入方法直接抢锁,就这两个地方体现不公平,其他逻辑和公平锁一致
