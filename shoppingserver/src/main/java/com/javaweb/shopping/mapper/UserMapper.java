package com.javaweb.shopping.mapper;


import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper  extends GeneralDAO<User> {
    /*通过use_Id 删除该用户，修改user表*/
    void deleteUser(int userId);
    /*找出所有的用户*/
    List<User> listAllUser();
}
