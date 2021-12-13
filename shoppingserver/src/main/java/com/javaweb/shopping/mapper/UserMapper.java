package com.javaweb.shopping.mapper;

import com.javaweb.shopping.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper  extends GeneralDAO<User>{

}
