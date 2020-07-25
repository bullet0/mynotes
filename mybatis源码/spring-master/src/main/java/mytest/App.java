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
package mytest;

import java.util.List;

import mytest.mapper.SonUserMapper;
import org.apache.ibatis.session.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import mytest.entity.User;
import mytest.mapper.UserMapper;

/**
 * @program: mytest.App
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-07-17 11:20
 */
/**
 * P.S.: 在基础的 MyBatis 用法中，是通过 SqlSessionFactoryBuilder 来创建 SqlSessionFactory 的。 而在 MyBatis-Spring 中，则使用
 * SqlSessionFactoryBean 来创建
 */
@ComponentScan("mytest.config")
@MapperScan("mytest.mapper")
public class App {

  public static void main(String[] args) throws Exception {
    // // java代码配置方式
    // DataSource dataSource = getBlogDataSource();
    // TransactionFactory transactionFactory = new JdbcTransactionFactory();
    // Environment environment = new Environment("development", transactionFactory, dataSource);
    // Configuration configuration = new Configuration(environment);
    // // 这里addMapper,会将Mapper类对象作为键,一个持有mapper类对象的工厂作为值放入map集合中,将来会用这个工厂创建代理对象
    // // 这里解析注解的同时会去解析同路径下同名的.xml文件,如果有,就解析,如果没有xml也无所谓,因为xml不是必须的
    //
    // SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    // System.out.println(sqlSessionFactory);
    // SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
    // session.commit();
    // session.commit();
    // session.close();

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
    SonUserMapper bean = context.getBean(SonUserMapper.class);
    List<User> all2 = bean.findAll2();
    System.out.println(all2);

  }

}
