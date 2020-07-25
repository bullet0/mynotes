插件其实是拦截器,通常可以作用在如下方法位置
Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
ParameterHandler (getParameterObject, setParameters)
ResultSetHandler (handleResultSets, handleOutputParameters)
StatementHandler (prepare, parameterize, batch, update, query)

即
拦截执行器的方法
拦截参数的处理
拦截结果集的处理
拦截Sql语法构建的处理


示例

```java
@Intercepts({@Signature(  // 一个Signature代表一个拦截点,内部的描述就是描述这个拦截点的位置
  type= Executor.class, //表示此拦截器拦截Executor这个类中的方法,能拦截哪些方法上面写了
  method = "update",//想要拦截的方法
  args = {MappedStatement.class,Object.class} //方法的入参类型,在类中声明了照着抄就行
)})
//比如这个Executor中有个update方法是这么声明的
//int update(MappedStatement ms, Object parameter) throws SQLException;
//所以上面就这么配置
public class ExamplePlugin implements Interceptor {  // 1.这个类会在扫描到后被反射实例化,添加到拦截器链中
  public Object intercept(Invocation invocation) throws Throwable {
    return invocation.proceed();
  }
  public Object plugin(Object target) {
    // 只有返回代理类才会反射调用到上面的intercept方法呀,因为上面的intercept是jdk动态代理中的那个方法
    // 这里的warp方法,内部已经实现了判断,会判断上面注解中的信息和当前拦截点的接口是否匹配
    // 匹配才会返回代理对象,否则返回当前传入对象,所以我们不用判断
    return Plugin.wrap(target, this); // 默认返回jdk动态代理,一般都这么写
  }
  public void setProperties(Properties properties) {
  }
}
```
