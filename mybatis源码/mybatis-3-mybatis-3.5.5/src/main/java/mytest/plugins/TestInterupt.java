package mytest.plugins;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @program: mytest.plugins.TestInterupt
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-07-25 10:19
 */
@Intercepts({@Signature(  // 一个Signature代表一个拦截点,内部的描述就是描述这个拦截点的位置
  type= Executor.class, //表示此拦截器拦截Executor这个类中的方法,能拦截哪些方法上面写了
  method = "query",//想要拦截的方法
  args = {MappedStatement.class, Object.class, RowBounds.class,ResultHandler.class , CacheKey.class, BoundSql.class} //方法的入参类型,在类中声明了照着抄就行
)})
public class TestInterupt implements Interceptor {
  //拦截方法，执行拦截器逻辑
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    System.out.println("拦截了");
//    return invocation.proceed();
    return null;
  }
  //为目标对象创建代理并返回，通过调用Plugin.wrap(target, this)实现
  @Override
  public Object plugin(Object target) {
    // 只有返回代理类才会反射调用到上面的intercept方法呀,因为上面的intercept是jdk动态代理中的那个方法
    return Plugin.wrap(target, this);
  }

  //设置属性
  @Override
  public void setProperties(Properties properties) {
    // 这个方法在解析xml配置时会被调用
    // 也就是说,如果你使用的spring java config配置的方式配置拦截器,这里不会调用到
    // pageHelper 工具为了防止这种情况,在上面intercept中主动调用了一下这个方法,
    // 保证这个方法不管使用xml还是java config方式配置,在拦截器真正生效前,一定被调用了一个,可以借鉴
    // 注意:这个方法如果在intercept中主动调用,一定要加锁,保证只被调用一次
    System.out.println(properties);
  }
}
