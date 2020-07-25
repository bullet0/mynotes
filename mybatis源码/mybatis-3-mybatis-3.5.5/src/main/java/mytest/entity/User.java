package mytest.entity;

import org.apache.ibatis.type.Alias;

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
    return "User{" +
      "name='" + name + '\'' +
      ", age='" + age + '\'' +
      ", id='" + id + '\'' +
      '}';
  }

  public void setId(String id) {

    this.id = id;
  }
}
