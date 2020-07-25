/**
 * Copyright ${license.git.copyrightYears} the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mytest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.executor.BatchResult;

import mytest.entity.User;

@Mapper
public interface SonUserMapper {
  @Select("select * from user_0")
  public List<User> findAll2();

  @Update("update  user_0  set age = #{value} where id = 1")
  public int update(int age);

  /**
   * @Flush 与 ExecutorType.BATCH 配合使用时有效 ExecutorType.BATCH 只对第一次插入操作执行了sql编译操作, 对其它插入操作仅执行了设置参数操作, 最后统一执行. 类似 DEBUG
   *        [main] - ==> Preparing: insert into user_0 (name,age) values(?,?) DEBUG [main] - ==> Parameters:
   *        477(String), 477(String) DEBUG [main] - ==> Parameters: 478(String), 478(String)
   *
   *        而不像 ExecutorType.SIMPLE 会每次先编译后替换数据 DEBUG [main] - ==> Preparing: insert into user_0 (name,age) values(?,?)
   *        DEBUG [main] - ==> Parameters: 477(String), 477(String) DEBUG [main] - ==> Preparing: insert into user_0
   *        (name,age) values(?,?) DEBUG [main] - ==> Parameters: 478(String), 478(String) DEBUG [main] - ==> Preparing:
   *        insert into user_0 (name,age) values(?,?)
   *
   *        但是在 ExecutorType.BATCH 过程中调用了@Flush的方法,之后再发送update语句,就会在编译一遍sql DEBUG [main] - ==> Preparing: insert into
   *        user_0 (name,age) values(?,?) DEBUG [main] - ==> Parameters: 473(String), 473(String) DEBUG [main] - ==>
   *        Parameters: 474(String), 474(String) DEBUG [main] - ==> Parameters: 475(String), 475(String) 此处调用flush方法
   *        DEBUG [main] - ==> Preparing: insert into user_0 (name,age) values(?,?) DEBUG [main] - ==> Parameters:
   *        476(String), 476(String) DEBUG [main] - ==> Parameters: 477(String), 477(String)
   */
  @Flush
  public List<BatchResult> flush();
}
