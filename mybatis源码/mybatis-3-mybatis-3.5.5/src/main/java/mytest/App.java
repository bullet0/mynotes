package mytest;

import com.alibaba.druid.pool.DruidDataSource;
import mytest.entity.User;
import mytest.mapper.SonUserMapper;
import mytest.mapper.UserMapper;
import mytest.plugins.TestInterupt;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: mytest.App
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-07-17 11:20
 */
/**
 * P.S.: mybatis一级缓存默认开启,但是和spring整合后一级缓存失效,因为一级缓存是共用同一个session,而spring嵌套事务执行完之后会将session关闭
 *       mybatis二级缓存默认关闭,一般不需要开启,即便开启也只适用于单机部署,多集群情况下,开启二级缓存,A机器查询数据3条,B机器删除一条,
 *       但是A机器并不知道,导致A机器缓存无法失效,如果非要缓存,可以使用第三方缓存工具,如redis
 *
 *       mybatis的insert update delete 返回的都是修改的条数,但是不是受影响的条数,比如数据库原来age=80,修改age=80
 *       这时返回的int值为1,表示修改了1条值为80,但是不是受影响的条数,受影响的条数为0,因为没改动内容
 *       如果想要返回受影响的条数,需要在连接驱动时配置 useAffectedRows=true
 *       核心原理,首先明确这个事是mysql驱动在干的,不是mybatis在干的,所以mybatis只是获取了一下驱动设置到指定位置的数据,不管这个数据表示什么意思
 *       所以我们修改了mysql驱动参数,就会导致mysql在处理完sql语句后,将什么值放入对应的位置,这样从而改变了mybatis的返回值
 *       insert返回的主键值也是这么个逻辑,再执行完insert后,mysql驱动会将主键值放入一个指定位置,然后mybatis只是取出来这个主键值,然后设置到对象中去
 *       mybatis只是干了这么个事,没有重新发送查询语句(针对主键自增的情况)
 *          主键自增返回id可以这么配置xml
 *          <insert id="insert" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
 *             insert into user_0 (name,age)
 * 	        	 values(1,1)
 *           </insert>
 *
 *       证明 : 可以在驱动包中的ClientPreparedStatement类的lastInsertId属性上打断点,在execute()方法中
 *       有这么一句代码this.lastInsertId = rs.getUpdateID(); 大概256行 就会将主键设置到这个lastInsertId属性上
 *       这个属性是long类型,说明只支持主键自增的情况
 *
 *       如果是uuid的主键,配置应当如下,驱动无法帮忙获取到主键信息,只能交由mybatis主动发送一条查询sql,去查询id,并且将id
 *       设置到对象中去
 *        <insert id="insert">
 *            insert into user_0 (name,age)
 * 		        values(1,1)
 *            <selectKey keyColumn="id" resultType="string" keyProperty="id" order="AFTER">
 *                SELECT LAST_INSERT_ID()
 *            </selectKey>
 *        </insert>
 *
 *        二级缓存必须等到第一次事务commit之后才能用,因为存放方法是在commit中
 */
public class App {

  public static void xml方式构建SqlSessionFactory() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    // 核心对象 , 通过构建者模式获取对象,屏蔽底层细节
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  }
  public static void 一级缓存(SqlSessionFactory sqlSessionFactory) throws IOException {
    SqlSession session = sqlSessionFactory.openSession();
    //当调用到session.getMapper方法时,会通过工厂构建Mapper对象的代理类,使用jdk动态代理
    UserMapper mapper = session.getMapper(UserMapper.class);
    List<User> all = mapper.findAll();
    System.out.println(all);
    System.out.println("==============第二次调用=================");
    List<User> all2 = mapper.findAll();
    System.out.println(all2);
    session.close();
  }
  public static void 二级缓存(SqlSessionFactory sqlSessionFactory) throws IOException {
    // 二级缓存开启,如果有mapper.xml,只能在mapper.xml中配置<cache>标签开启
    // 如果没有mapper.xml,可以在Mapper接口上注解@CacheNamespace
    // 两个方法一一对应,不能混用,即如果有mapper.xml时使用@CacheNamespace不生效
    SqlSession session = sqlSessionFactory.openSession();
    //当调用到session.getMapper方法时,会通过工厂构建Mapper对象的代理类,使用jdk动态代理
    UserMapper mapper = session.getMapper(UserMapper.class);
    List<User> all = mapper.findAll();
    System.out.println(all);
    session.close();
    System.out.println("==============第二次调用=================");
    SqlSession session2 = sqlSessionFactory.openSession();
    //当调用到session.getMapper方法时,会通过工厂构建Mapper对象的代理类,使用jdk动态代理
    UserMapper mapper2 = session2.getMapper(UserMapper.class);
    List<User> all2 = mapper2.findAll();
    System.out.println(all2);
    session.close();
  }



  public static void main(String[] args) throws Exception {
//    xml方式构建SqlSessionFactory();

    // java代码配置方式
    DataSource dataSource = getBlogDataSource();
    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration configuration = new Configuration(environment);
    // 这里addMapper,会将Mapper类对象作为键,一个持有mapper类对象的工厂作为值放入map集合中,将来会用这个工厂创建代理对象
    // 这里解析注解的同时会去解析同路径下同名的.xml文件,如果有,就解析,如果没有xml也无所谓,因为xml不是必须的
    configuration.addMapper(SonUserMapper.class);
    configuration.addMapper(UserMapper.class);
    TestInterupt testInterupt = new TestInterupt();
    configuration.addInterceptor(testInterupt);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    System.out.println(sqlSessionFactory);
    SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
    //当调用到session.getMapper方法时,会通过工厂构建Mapper对象的代理类,使用jdk动态代理
    SonUserMapper mapper = session.getMapper(SonUserMapper.class);
    // 执行查询语句时,先看二级缓存,再看一级缓存,没有再发SQL查询
    List<User> all = mapper.findAll();
    session.commit();
//    System.out.println(all);
//    List<User> all2 = mapper.findAll2();
//    System.out.println(all2);
//    List<BatchResult> flush = mapper.flush();
//    System.out.println("f:   "+flush);
//    List<User> all3 = mapper.findAll2();
//    System.out.println(all3);

    for (int i = 0; i < 500; i++) {
      User user = new User();
      user.setName(i+"");
      user.setAge(i+"");
      int update = mapper.insert(user);
      System.out.println(user.getId());
      List<BatchResult> flush = mapper.flush();
      System.out.println(flush.get(0).getParameterObjects().get(0));
      System.out.println(user.getId());

    }



//    System.out.println(update);
//    System.out.println(user.getId());
    session.commit();
    session.close();


  }

  private static DataSource getBlogDataSource() {
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setUrl("jdbc:mysql:///ds0?characterEncoding=utf8&serverTimezone=UTC&useAffectedRows=true");
    druidDataSource.setPassword("123456");
    druidDataSource.setUsername("root");
    druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    return druidDataSource;
  }


}
