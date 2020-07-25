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
package mytest.entity;

import java.io.Serializable;

/**
 * @program: mytest.User
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-07-21 12:13
 */

public class User implements Serializable {
  private String name;
  private String age;
  private String id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "User{" + "name='" + name + '\'' + ", age='" + age + '\'' + ", id='" + id + '\'' + '}';
  }

  public void setId(String id) {

    this.id = id;
  }
}
