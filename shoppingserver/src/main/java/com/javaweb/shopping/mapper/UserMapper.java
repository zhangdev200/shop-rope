package com.javaweb.shopping.mapper;

import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.general.GeneralDAO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface UserMapper  extends GeneralDAO<User> {

}
