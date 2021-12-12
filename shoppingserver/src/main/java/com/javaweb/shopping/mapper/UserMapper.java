package com.javaweb.shopping.mapper;

import com.javaweb.shopping.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper  extends GeneralDao<User>{

}
