# hashcode和equals方法

## hashcode 根源

- hashcode是对象的内存地址经过处理后的结构,由于每个对象的内存地址都不一样,所以哈希码也不一样
- 这个hashcode怎么得到呢,需要调用 `System.identityHashCode()` 方法

  ```java
    public static void main(String[] args) {
      Object a = new Object();
      System.out.println(System.identityHashCode(a));
      System.out.println(a.hashCode());
      // 输出
      // 1836019240
      // 1836019240
    }
  ```

  > 可以看到 Object的hashcode方法是无污染的方法,效果和 System.identityHashCode 一致
  > System.identityHashCode 底层是 native 方法,已经不是java写的了,所以我说这才是根

## equals 根源

- 这个方法用来比较两个对象是否`相同`
- 这里的相同指的是内存地址相同,也就是说同一个对象才真正相同,代码中使用 == 来比较
- Object的equals 用的就是 == ,返回结果

## 为什么要重写equals

- 其实上面两个方法足够用了(因为我们平时也不去重写这两个方法,想想自己每声明一个类就会重写这两个方法么?)
- 但是有时候啊,比如我们用set做去重的时候,希望他把我们给定的8个对象去重成为2个对象,也就是8个不同的内存地址,我们不想让他用 == 来判断了,而是能够比较对象中的内容,根据内容来去重
- 于是就有了重写equals方法,比如Stirng,就重写了equals方法,用来做值比较
- 这时我们的set去重就可以使用了,值相同则去重

## 为什么要重写hashcode

- 如果你没重写equals,完全可以不重写hashcode
- 但是你重写了equals,说明我们要对两个不同地址的对象进行比较了,那么每次比较这两个对象的内容,是不是比较麻烦,毕竟相等的情况比较少,不同的情况比较多
- 所以我们需要利用hashcode方法,在equals方法调用前,先看看两个对象的hashcode是否相同,先排除一大半的数据(提高效率),map中同key的判断就是如此 `hashcode == hashcode && equals`,直接用Object的hashcode返回的code一定不同,所以,我们也需要重写hashcode方法
- 所以有规定,equals相等,hashcode也必须相等

  ```java
    public static void main(String[] args) {
      Character a = 'a';
      String str = "a";
      System.out.println(a.hashCode());
      System.out.println(str.hashCode());
      // 输出
      // 97
      // 97
    }
  ```

  > 侧面证明,重写的hashcode肯定是根据内容推算出来的,这个 a -> 97 是不是很熟悉

- 上面例子也证明了,hashcode相同,equals不一定相同
- equals不同,hashcode不一定不同
  
## 结论

- 啥时候重写两个方法呢,一般是配合集合类使用时,需要重写这两个方法
- 一般的是因为要用equals,而用equals又希望提高效率,所以是先重写equals紧跟着重写hashcode
- 两个就都得重写了

- hashcode可以不重写么?
- 不可以,否则set map这种集合的去重用不了,他们内部的代码写死了先判断hashcode

  ```java
    public static void main(String[] args) {
        Object o = new Object();
        Object o2 = new Object();

        HashSet set = new HashSet();
        set.add(o);
        set.add(o2);
        System.out.println(set.size()); // 2
    }
  ```
