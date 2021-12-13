package com.javaweb.shopping.dao;

import com.javaweb.shopping.ShoppingApplication;
import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.mapper.UserMapper;
import com.javaweb.shopping.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        System.out.println(userMapper);
        User user = new User();
        user.setNickname("zxf1");
        user.setPassword("123456");
        userMapper.insert(user);
//        System.out.println(userMapper.selectAll());;
    }
}
