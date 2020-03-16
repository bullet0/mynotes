[toc]

# ClassLoader

## 相关JVM参数
- -XX:+TraceClassLoading 打印加载类字节码的信息

## 类型的加载/连接/初始化过程都是在程序运行期间完成的
- 加载:查找并加载类的二进制数据(将class文件加载到内存中)
> 将类的二进制文件读入内存中,并将其放在运行时数据区的**方法区**(方法区在JDK1.8后被元空间取代{原来方法区内存储2部分内容,1.静态变量 2.元数据 ,现在元数据进入元空间,静态变量进入堆;方法区是JVM内存结构的定义名称,永久代是hotspot虚拟机对其的实现,所以又说永久代是方法区})内,然后在堆中创建一个java.lang.Class对象(规范并未说明Class对象放在哪个位置,HotSpot虚拟机将其放在了方法区中,这个对象中封装类的结构)
  类加载测最终产物就是在堆中创建了Class对象
- 链接:
    > 加载阶段和链接阶段可以交叉进行提高效率,比如A加载完毕,开始加载B,这个时候A可以开始链接
    - 验证:确保被加载类的正确性
        > 类文件结构检查
        语义检查
        字节码检查 : 声明int但是赋值为long
        符号引用验证:是否调用了一个不存在的方法
        二进制兼容性的验证
            魔数是否正确,大小版本号是否匹配当前的JVM版本,常量池中类型是否支持
            元数据验证: 是否有父类,父类是否允许继承,是否覆盖父类final定义的变量或者方法...
    - 准备:伪类的**静态变量**分配内存,并将其初始化为默认值(比如 `public **static** int a = 1`,这时a被赋值为0)
        > JVM为类的静态变量分配内存(比如int分配 4个字节 , long分配8个字节),并设置为默认的初始值(比如 0 , null , false , \u0000 )
    - 解析:把类中的**符号引用**转为**直接引用**
        > 比如代码中this.a,这里的this就是符号引用,要转为直接地址
        
- 初始化:为类的静态变量赋予正确的初始化值(比如 `public **static** int a = 1`,这时a被赋值为1)
    > 静态变量初始化有两种方式 : 
    1.在静态变量声明处直接初始化 `public static int a = 4;`
    2.在静态代码块中进行初始化 `static { a = 20; }`
    3.初始化步骤:加入这个类还没有被加载和连接,则先进行加载和连接;加入该类存在直接父类,并且这个父类还未初始化,则先初始化其直接父类;加入类中存在初始化语句,那就依次执行这些初始化语句
    4.类加载的最后一步,初始化阶段是执行构造函数\<clinit\>()的过程;\<clinit\>()方法是有编译器自动收集类中所有变量的赋值动作和静态语句块中的语句合并产生的,静态语句块中只能访问到定义静态语句块之前的变量,定义在他之后的变量,只能赋值,不能访问;\<clinit\>()方法不是构造函数,他不需要显示的调用父类构造函数,虚拟机会保证在子类\<clinit\>()方法调用时,先执行父类的\<clinit\>()方法,因此在JVM中最先被调用的就是Object的\<clinit\>()方法;由于父类\<clinit\>()方法会被先调用到,所以导致父类静态块会先于子类静态块执行;\<clinit\>()方法不是必须的;接口中也会存在\<clinit\>()方法;虚拟机有义务保证\<clinit\>()方法的线程安全
- 使用:程序运行过程中使用
- 卸载:从内存中卸载掉这个class字节码





## 类的使用方式
- **主动使用**:所有JVM实现必须在每个类或者接口被程序**首次主动使用**时才初始化类
    - 创建类的实例( `new Object()` )
    - 访问(`get`)某个类/接口的**静态变量**,或者对该静态变量赋值(`set`)
    >  关于 static [MyTest1](./code/MyTest1.java) 
    >  关于 final 常量 [MyTest2](./code/MyTest2.java) 
    >  关于 final 非常量 [MyTest3](./code/MyTest3.java) 
    >  关于 数组 [MyTest4](./code/MyTest4.java) 
    >  关于 接口 [MyTest5](./code/MyTest5.java) 
    >  关于 初始化代码块执行顺序 [MyTest6](./code/MyTest6.java) 

    - 调用(`invoke`)了类的**静态方法**
    - 反射(`Class.forName("java.lang.Object")`)
    - 初始化一个类的子类(因为初始化子类会先初始化其父类,所以父类会被初始化)
    - main方法所在类(main方法跑起来需要其所在类先被初始化)
    - JDK提供的动态语言:如果通过java.lang.invoke.MethodHandle实例的解析结果REF\_getStatic,REF\_putStatic,REF\_invokeStatic句柄对应的类没有初始化,则初始化
- **被动使用**:其他情况为被动使用,不会导致类的**初始化**,但并不表示此类是否已经被**加载,连接**
- 如果一个类缺失或者存在错误,并不会在初始化阶段就抛出异常,而是等到首次调用时才会抛出异常,也就是说,如果一直没有使用过这个类,则不会抛出异常
- 类的初始化时机
>当JVM初始化一个雷达时候,要求它的所有父类都已经被初始化,但不适用与接口,
1.初始化一个类的时候,不会初始化他所实现的接口
2.初始化一个接口的时候,并不会初始化他的父接口


## 类的加载方式
- 从本地系统中直接加载
- 从网络下载.class文件加载
- 从zip,jar等归档文件中加载.class文件
- 从转悠数据库中提取.class文件
- 将java源文件动态编译的.class文件(代理类字节码)


## 何时JVM生命周期结束
- 执行了System.exit()
- 程序正常结束
- 在执行过程中抛出异常/错误终止
- 由于操作系统错误导致JVM进程终止




## 类加载器
- BootstrapClassLoader
    - 无父加载器,不实现ClassLoader接口,是底层操作系统实现的,属于虚拟机实现的一部分
    - 负责加载虚拟机核心类库,从系统属性 sun.boot.class.path 路径下加载,默认就是你配置的JAVA_HOME下加载
    - 可以使用System.out.println(System.getProperty("sun.boot.class.path")); 获取路径
- ExtClassLoader
    - 父加载器是BootstrapClassLoader,实现ClassLoader接口
    - 从系统属性 java.ext.dirs 路径下加载
    - 可以使用System.out.println(System.getProperty("java.ext.dirs")); 获取路径
- AppClassLoader
    - 父加载器是ExtClassLoader,实现ClassLoader接口
    - 从当前项目classpath下或者系统属性 java.class.path 指定的路径下加载
    - 可以使用System.out.println(System.getProperty("java.class.path")); 获取路径
    - 可以使用 System.out.println(MyTest6.class.getClassLoader().getResource("")); 获取classpath路径
- 自定义类加载器
    - 一般继承自AppClassLoader
    

## 双亲委派机制


## 定义类加载器 和 初始类加载器
- 如果一个类加载器能够成功加载某个类,被称为此类的定义类加载器
- 所有能返回某个类的Class对象引用的类加载器,都被称为初始类加载器
```java
public class MyClassLoader extends ClassLoader{
    private static final String DEFAULT_DIR = "C:\\Users\\helloworld\\Desktop\\tread";
    private String dir;

    private String className;

    public MyClassLoader(String className) {
        this(className,DEFAULT_DIR);
        this.className = className;
    }

    public MyClassLoader( String className,String dir) {
        this.dir = dir;
        this.className = className;
    }
    //重写findClass,但是调用loadClass方法
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String s = name.replaceAll("\\.", "/");
        File file = new File(dir,s+".class");
        if (!file.exists()) {
            throw  new ClassNotFoundException(name + " not found!!");
        }
        byte[] b = loadClassBytes(file);
        if(b==null || b.length == 0){
            throw new ClassNotFoundException(name + " load failed!!");
        }

        return this.defineClass(name,b,0,b.length);
    }

    private byte[] loadClassBytes(File file) {
        try(ByteArrayOutputStream bao = new ByteArrayOutputStream();
            FileInputStream fi = new FileInputStream(file);
        ){
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len=fi.read(buffer)) != -1){
                bao.write(buffer,0,len);
            }
            bao.flush();
            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public static void main(String[] args) {
        MyClassLoader loader = new MyClassLoader("MyObject");
        try {
            Class<?> aClass = loader.loadClass("com.MyObject");
            System.out.println(aClass);
            System.out.println(aClass.getClassLoader());
            System.out.println(aClass.getClassLoader().getParent());
            aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
```

# GC 垃圾回收
# CMS
# G1