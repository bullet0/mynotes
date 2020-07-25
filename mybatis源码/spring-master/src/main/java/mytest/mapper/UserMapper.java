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

import org.apache.ibatis.annotations.Mapper;

import mytest.entity.User;

// 这个注解的作用是让spring自动扫描,注入到serviceImpl,写上service引用就不报红了,不写service引用会报红
// 一般可以写可以不写的原因是,要么你用了MapperScan,要么你在sqlSessionFactoryBean设置中配置了扫描位置
// @Mapper
public interface UserMapper {
  List<User> findAll();

  int insert(User user);

}
