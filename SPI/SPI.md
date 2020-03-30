# SPI

- service provider interface
- 首先明白最简单的事情,SPI这个机制和new是等价的,都是为你创建对象用的
- 而SPI并不是特别复杂的实现方式,究其底层还是反射创建对象
- 所以学习SPI并没有打破你原来对于创建对象的认知,new对象和反射创建对象

[toc]

## 使用场景

- 我对象可以自己new , 也可以自己反射 , 为啥要让 SPI 再套一层?
- 因为你想要new 对象或者自己反射,最基础的就是你能引用到这个对象,比如你在类路径下写了Hello类,你当然可以随便创建Hello对象,但如果我没有Hello类呢?
- 没有自然就不能创建,SPI就是为你创建你没有的类的
- 比如你用java开发代码,要连接mysql数据库,这个数据库的连接驱动当如可以JDK团队自己写,自己new,但是谁知道数据库将来会有多少种啊,总不能出来一种数据库,JDK团队不干别的了,马上开始研发一种驱动对接吧,所以JDK提供了SPI这种接口,就是想要创建JDK本身没有提供的类的,比如运行时给我把Mysql驱动里的Driver加载进来
- 这就是SPI的使用场景

- 现在开始想象我们需要准备点什么
- SPI底层是反射,类路径得准备出来
- 你随便提供个类让JDK加载,JDK也不是神仙,所以总要告知JDK应该加载哪些类

> SPI作用是让你的对象被JDK加载,所以规定是JDK定的,JDK首先知道要有这么一批类会被加载进来(JDK如果自己都不知道要有一批类加载进来,JDK肯定不会加载他的),所以JDK自己写了接口(服务发现者提供接口),然后Mysql团队自己写实现(服务提供者提供实现),JDK规定将自己的实现类路径写在resources/META-INF/services/目录下(这个目录自己建立),目录下文件名为JDK接口的类路径,内容为mysql实现类的类路径(完美将接口和实现类连接起来了),然后在编写代码的时候,肯定是以JDK的类为编程主体,所以JDK在写接口的时候会将初始化逻辑写在JDK这边,mysql不用管,mysql就等着被加载就完了

## 简单使用

- 首先模拟JDK开放了接口
  - 以下代码虽然在一个包里,但我们想象他们不在一个包里,否则还得真实的模拟2 3个jar,反正我们也不直接 new 对象

  ```java
    package com.bullet;
    // 我是JDK这边的代码
    public interface Hello {
        void sayHi();
    }
  ```

- 接着模拟Mysql/Oracle提供了各自的实现

  ```java
    package com.bullet;
    // 这是mysql自己写的实现类
    public class Man implements Hello {
        @Override
        public void sayHi() {
            System.out.println("Man");
        }
    }
  ```

  ```java
    package com.bullet;
    // 这是Oracle自己写的实现类
    public class Women implements Hello {
        @Override
        public void sayHi() {
            System.out.println("Women");
        }
    }
  ```

- 然后我们把关系配置一下
  - 以下2个类路径本来应该是分别配置的,但是同一个项目,同文件名不能同时存在2个,所以就都配置在一个文件中了
  - 在resources/下创建META-INF/services/父子目录
  - 在services目录下创建一个文件,文件名为com.bullet.Hello(接口类路径)
  - 打开com.bullet.Hello文件,配置内容为Hello接口的实现类类路径,可以配置多个,一行一个

  ```shell
    # com.bullet.Hello 文件内容
    com.bullet.Man
    com.bullet.Women
  ```

- 最后一步,用SPI加载对象进来
  
  ```java
    public class Test {
        public static void main(String[] args) throws SQLException {
            // 这个 ServiceLoader.load(Class<?>); 就是SPI加载对象的方法
            // 类似于Class.forName(String); 就是反射加载类对象的方法
            ServiceLoader<Hello> serviceLoader = ServiceLoader.load(Hello.class);
            serviceLoader.forEach(Hello::sayHi); // 这里相当于调用所有实现类的sayHi方法,会分别输出Man和Women
        }
    }
  ```

## ServiceLoader.load()方法源码

  ```java
    public static <S> ServiceLoader<S> load(Class<S> service) {
        //这里把调用load方法的线程中使用的类加载器传下去,一般是AppClassloader
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        return ServiceLoader.load(service, cl);
    }
  ```

  ```java
    public static <S> ServiceLoader<S> load(Class<S> service,
                                            ClassLoader loader)
    {
        return new ServiceLoader<>(service, loader);
    }
  ```

  ```java
    private ServiceLoader(Class<S> svc, ClassLoader cl) {
        service = Objects.requireNonNull(svc, "Service interface cannot be null");
        // 又判断了一下,如果ClassLoader为null,则使用AppClassLoader
        loader = (cl == null) ? ClassLoader.getSystemClassLoader() : cl;
        acc = (System.getSecurityManager() != null) ? AccessController.getContext() : null;
        reload();
    }
  ```

  ```java
    public void reload() {
        // 这个providers 是个 LinkedHashMap 对象,用来存所有的 接口 和 实现类服务对应关系
        // 先清空一下
        providers.clear();
        // 然后创建了一个LazyIterator对象,这里就是普通的赋值,啥也没干,也就是到此我们没加载实现类,但是方法走完了
        // 原因,这个是个懒加载,而且是个迭代器,具体的实现在hasNext和next方法中
        lookupIterator = new LazyIterator(service, loader);
    }
  ```

- hasNext方法
  
  ```java
    // LazyIterator 的 hasNext 方法
    public boolean hasNext() {
        if (acc == null) {
            return hasNextService();
        } else {
            PrivilegedAction<Boolean> action = new PrivilegedAction<Boolean>() {
                public Boolean run() { return hasNextService(); }
            };
            return AccessController.doPrivileged(action, acc);
        }
    }
  ```

  ```java
    public boolean hasNext() {
        if (acc == null) {
            // 会进这个方法
            return hasNextService();
        } else {
            PrivilegedAction<Boolean> action = new PrivilegedAction<Boolean>() {
                public Boolean run() { return hasNextService(); }
            };
            return AccessController.doPrivileged(action, acc);
        }
    }
  ```

  ```java
    private boolean hasNextService() {
        if (nextName != null) {
            return true;
        }
        if (configs == null) {
            try {
                // PREFIX = "META-INF/services/" 写死了的,这就是为啥要把配置放在这个目录下
                // service是全局变量,就是你 new ServiceLoader<>(service, loader); 时传入的service类对象,这里就是Hello.class对象,获取到的name就是Hello类路径
                // fullName = "META-INF/services/com.bullet.Hello" 正好对应你的配置文件名字
                String fullName = PREFIX + service.getName();
                if (loader == null)
                    configs = ClassLoader.getSystemResources(fullName); // 如果没设置类加载器,就用系统类加载器加载文件
                else
                    configs = loader.getResources(fullName); // 如果设置了类加载器,就用设置的加载器加载文件
            } catch (IOException x) {
                fail(service, "Error locating configuration files", x);
            }
        }
        // 加载完毕后只是将实现类路径放入了configs对象,是个URL对象,这个实现类还没有被解析
        while ((pending == null) || !pending.hasNext()) {
            if (!configs.hasMoreElements()) {
                return false;
            }
            // 如果里面有对象,就解析configs
            pending = parse(service, configs.nextElement());
        }
        nextName = pending.next();
        return true;
    }
  ```

  ```java
    // 通过URL对象中解析出来的类路径,放入ArrayList中,放的是String了
    private Iterator<String> parse(Class<?> service, URL u)
          throws ServiceConfigurationError
    {
        InputStream in = null;
        BufferedReader r = null;
        ArrayList<String> names = new ArrayList<>();
        try {
            in = u.openStream();
            r = new BufferedReader(new InputStreamReader(in, "utf-8"));
            int lc = 1;
            while ((lc = parseLine(service, u, r, lc, names)) >= 0);
        } catch (IOException x) {
            fail(service, "Error reading configuration file", x);
        } finally {
            try {
                if (r != null) r.close();
                if (in != null) in.close();
            } catch (IOException y) {
                fail(service, "Error closing configuration file", y);
            }
        }
        return names.iterator();
    }
  ```

- 到这里又走不下去了,实现类还是没被加载出来
- 接着看next方法

  ```java
    public S next() {
        if (acc == null) {
            // 进入这个方法
            return nextService();
        } else {
            PrivilegedAction<S> action = new PrivilegedAction<S>() {
                public S run() { return nextService(); }
            };
            return AccessController.doPrivileged(action, acc);
        }
    }
  ```

  ```java
    private S nextService() {
        if (!hasNextService())
            throw new NoSuchElementException();
        String cn = nextName;
        nextName = null;
        Class<?> c = null;
        try {
            // 终于看到了反射
            // 获取类对象
            c = Class.forName(cn, false, loader);
        } catch (ClassNotFoundException x) {
            fail(service,
                  "Provider " + cn + " not found");
        }
        if (!service.isAssignableFrom(c)) {
            fail(service,
                  "Provider " + cn  + " not a subtype");
        }
        try {
            // 反射实例化对象
            S p = service.cast(c.newInstance());
            // 对象出来之后,就放到了providers这个对象中,这个是个LinkedHashMap
            providers.put(cn, p);
            // 同时会返回给上层next方法当前实例化好的实现类
            return p;
        } catch (Throwable x) {
            fail(service,
                  "Provider " + cn + " could not be instantiated",
                  x);
        }
        throw new Error();          // This cannot happen
    }
  ```

- 到此,我们的实现类就进入了JVM中,由于对象放在了迭代器中,我们使用就只得从迭代其中获取了

## mysql中的真实使用案例

- 首先你可以自己找到mysql驱动jar位置,看到他META-INF/services下有个java.sql.Driver文件
- 也就是mysql的实现类实现了java.sql.Driver(这个接口是JDK自己的)
- 里面写了自己的实现类,也就是你配置文件中配置的那个类,也就是说你配置的那个类其实就是这个类,mysql 5 和 8 版本类路径不同也是由于这里不同

- 找到SPI的影子,我们接着找这个实现类是怎么进入到JVM中的
- 一般的,我们使用JDBC都会先调用这句话获取连接,就从这里看起

  ```java
    Connection connection = DriverManager.getConnection("jdbc:mysql:///test","root","123456");
  ```

  ```java
    public static Connection getConnection(String url,
        String user, String password) throws SQLException {
        java.util.Properties info = new java.util.Properties();

        if (user != null) {
            info.put("user", user);
        }
        if (password != null) {
            info.put("password", password);
        }
        // Reflection.getCallerClass() 就是获取是哪个类调用的当前方法
        return (getConnection(url, info, Reflection.getCallerClass()));
    }
  ```

  ```java
    private static Connection getConnection(
      String url, java.util.Properties info, Class<?> caller) throws SQLException {
      /*
        * When callerCl is null, we should check the application's
        * (which is invoking this class indirectly)
        * classloader, so that the JDBC driver class outside rt.jar
        * can be loaded from here.
        */
      // 设置类加载器
      ClassLoader callerCL = caller != null ? caller.getClassLoader() : null;
      synchronized(DriverManager.class) {
          // synchronize loading of the correct classloader.
          if (callerCL == null) {
              callerCL = Thread.currentThread().getContextClassLoader();
          }
      }

      if(url == null) {
          throw new SQLException("The url cannot be null", "08001");
      }

      println("DriverManager.getConnection(\"" + url + "\")");

      // Walk through the loaded registeredDrivers attempting to make a connection.
      // Remember the first exception that gets raised so we can reraise it.
      SQLException reason = null;

      // 哎,我擦,他怎么啥也没干就开始遍历上了呢?
      // 还在下面公然获取了connection?
      // 关键点就是这个 registeredDrivers 啥时候有的数据
      for(DriverInfo aDriver : registeredDrivers) {
          // If the caller does not have permission to load the driver then
          // skip it.
          if(isDriverAllowed(aDriver.driver, callerCL)) {
              try {
                  println("    trying " + aDriver.driver.getClass().getName());
                  Connection con = aDriver.driver.connect(url, info);
                  if (con != null) {
                      // Success!
                      println("getConnection returning " + aDriver.driver.getClass().getName());
                      return (con);
                  }
              } catch (SQLException ex) {
                  if (reason == null) {
                      reason = ex;
                  }
              }

          } else {
              println("    skipping: " + aDriver.getClass().getName());
          }

      }

      // if we got here nobody could connect.
      if (reason != null)    {
          println("getConnection failed: " + reason);
          throw reason;
      }

      println("getConnection: no suitable driver found for "+ url);
      throw new SQLException("No suitable driver found for "+ url, "08001");
    }
  ```

- 啥也没干,他就开始遍历,唯一解释,就是DriverManager在调用connection方法前,还有操作
- 我们调用DriverManager时没有实例化,直接静态调用,唯一解释就是他有静态代码块

  ```java
    static {
        loadInitialDrivers();
        println("JDBC DriverManager initialized");
    }
  ```

  ```java
    private static void loadInitialDrivers() {
        String drivers;
        try {
            drivers = AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty("jdbc.drivers");
                }
            });
        } catch (Exception ex) {
            drivers = null;
        }
        // If the driver is packaged as a Service Provider, load it.
        // Get all the drivers through the classloader
        // exposed as a java.sql.Driver.class service.
        // ServiceLoader.load() replaces the sun.misc.Providers()

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                // 终于在这里看到了SPI => ServiceLoader.load(Driver.class);
                // 这句代码完毕后,我们的配置文件就被解析了,但是还没有实例化
                ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
                Iterator<Driver> driversIterator = loadedDrivers.iterator();

                /* Load these drivers, so that they can be instantiated.
                 * It may be the case that the driver class may not be there
                 * i.e. there may be a packaged driver with the service class
                 * as implementation of java.sql.Driver but the actual class
                 * may be missing. In that case a java.util.ServiceConfigurationError
                 * will be thrown at runtime by the VM trying to locate
                 * and load the service.
                 *
                 * Adding a try catch block to catch those runtime errors
                 * if driver not available in classpath but it's
                 * packaged as service and that service is there in classpath.
                 */
                try{
                    while(driversIterator.hasNext()) {
                        // 到这,实例化了,放入了providers这个对象中,这个是个LinkedHashMap
                        // 本来next有返回当前遍历的实现类,但这里没接值
                        driversIterator.next();
                    }
                } catch(Throwable t) {
                // Do nothing
                }
                return null;
            }
        });
        //到这里,实现类被加载完了
        println("DriverManager.initialize: jdbc.drivers = " + drivers);
        // 下面这些代码跟drivers有关,需要设置了System.getProperty("jdbc.drivers")这个环境变量才有用
        // 所以对我们来讲,下面的操作也就没用了
        if (drivers == null || drivers.equals("")) {
            return;
        }
        String[] driversList = drivers.split(":");
        println("number of Drivers:" + driversList.length);
        for (String aDriver : driversList) {
            try {
                println("DriverManager.Initialize: loading " + aDriver);
                Class.forName(aDriver, true,
                        ClassLoader.getSystemClassLoader());
            } catch (Exception ex) {
                println("DriverManager.Initialize: load failed: " + ex);
            }
        }
    }
  ```

- 但是tm的我还是没看到他往registeredDrivers中加东西啊,到底遍历啥!

- 最后我们看下我们被实例化的实现类,他是被反射创建的,看下他构造时有没有特殊处理

  ```java
    public class Driver extends NonRegisteringDriver implements java.sql.Driver {
        public Driver() throws SQLException {
        }
        // 虽然构造器中没有处理,但是有静态代码块
        static {
            try {
                // 这里可以清晰的看到,当Driver类被加载时,就会将自己new出来放入registeredDrivers中
                DriverManager.registerDriver(new Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
        }
    }
  ```
  
- 最后,也就是DriverManager中用到的驱动类,不是SPI加载进来的那个,而是自己new出来设置进去的,也就是内存中有2个com.mysql.jdbc.Driver对象
- 一个是通过DriverManager静态代码块中SPI加载的,没人用他,一个是Driver静态代码块中自己new出来的,用的是这个
- 在JDBC这个加载实例中,SPI只是用来帮我触发实例化的工具,而并非真正使用SPI中创建出来的对象
- 测试可以在Driver构造器上打断点,会进入2次,自己看调用栈
- 还可以用jvisualvm,可以看到内存中真的有2个Driver对象