package mytest.mapper;

import mytest.entity.User;
import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.executor.BatchResult;

import java.util.List;

public interface SonUserMapper extends UserMapper {
  @Select("select * from user_0")
  public List<User> findAll2();
  @Update("update  user_0  set age = #{value} where id = 1")
  public int update(int age);

  /**
   * @Flush 与 ExecutorType.BATCH 配合使用时有效
   * ExecutorType.BATCH 只对第一次插入操作执行了sql编译操作, 对其它插入操作仅执行了设置参数操作, 最后统一执行.
   * 类似
   * DEBUG [main] - ==>  Preparing: insert into user_0 (name,age) values(?,?)
   * DEBUG [main] - ==> Parameters: 477(String), 477(String)
   * DEBUG [main] - ==> Parameters: 478(String), 478(String)
   *
   * 而不像 ExecutorType.SIMPLE 会每次先编译后替换数据
   * DEBUG [main] - ==>  Preparing: insert into user_0 (name,age) values(?,?)
   * DEBUG [main] - ==> Parameters: 477(String), 477(String)
   * DEBUG [main] - ==>  Preparing: insert into user_0 (name,age) values(?,?)
   * DEBUG [main] - ==> Parameters: 478(String), 478(String)
   * DEBUG [main] - ==>  Preparing: insert into user_0 (name,age) values(?,?)
   *
   * 但是在 ExecutorType.BATCH 过程中调用了@Flush的方法,之后再发送update语句,就会在编译一遍sql
   * DEBUG [main] - ==>  Preparing: insert into user_0 (name,age) values(?,?)
   * DEBUG [main] - ==> Parameters: 473(String), 473(String)
   * DEBUG [main] - ==> Parameters: 474(String), 474(String)
   * DEBUG [main] - ==> Parameters: 475(String), 475(String)
   * 此处调用flush方法
   * DEBUG [main] - ==>  Preparing: insert into user_0 (name,age) values(?,?)
   * DEBUG [main] - ==> Parameters: 476(String), 476(String)
   * DEBUG [main] - ==> Parameters: 477(String), 477(String)
   */
  @Flush
  public List<BatchResult> flush();
}
