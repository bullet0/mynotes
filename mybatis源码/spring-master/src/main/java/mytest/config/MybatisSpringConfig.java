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
package mytest.config;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @program: mytest.config.MybatisSpringConfig
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-07-25 12:41
 */
@Configuration
public class MybatisSpringConfig {
  // 事务交给spring管理
  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

  @Bean
  public DataSource dataSource() {
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setUrl("jdbc:mysql:///ds0?characterEncoding=utf8&serverTimezone=UTC&useAffectedRows=true");
    druidDataSource.setPassword("123456");
    druidDataSource.setUsername("root");
    druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    return druidDataSource;
  }

  // 使用mybatis-spring,就不在创建SqlSessionFactory了,而是转为SqlSessionFactoryBean
  // 但其实不是不使用SqlSessionFactory,而是SqlSessionFactoryBean会在IOC生命周期最后调用afterPropertiesSet方法
  // 将SqlSessionFactory创建出来,也就是间接将SqlSessionFactory交给了spring管理
  // spring中依然还是那个 DefaultSqlSessionFactory 对象
  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean() {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource());
    PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
    // 配置mapper.xml文件位置
//    sqlSessionFactoryBean
//        .setMapperLocations(pathMatchingResourcePatternResolver.getResource("classpath*:**/*.xml"));
    return sqlSessionFactoryBean;
  }

//  // 所以最终注入的还是 SqlSessionFactory
  // 这个也可以不要,会自动装配到Mapper中
//  @Bean
//  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
//    return new SqlSessionTemplate(sqlSessionFactory);
//  }

}
