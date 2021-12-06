package com.javaweb.shopping.mapper;

import com.javaweb.shopping.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //根据用户名登入该用户
    User loadUserByName(@Param("name") String name);
    //根据用户id获取用户
    User getUserByID(@Param("id") int id);
    //用户更新email，成功为1，失败为0
    int updateUserEmail(@Param("email") String email);
    //根据用户的nickname(不唯一)获取用户，并且存入到链表中
    List<User> getUserByNickName(@Param("nickname") String nickname);
    //根据用户uid注销用户，成功返回1，失败返回0
    int deleteUserByID(long uid);

}
