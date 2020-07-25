package mytest.mapper;

import mytest.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface UserMapper {
  List<User> findAll();


  int insert(User user);

}
