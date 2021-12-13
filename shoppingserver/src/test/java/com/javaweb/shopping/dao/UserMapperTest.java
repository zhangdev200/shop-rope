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
        User user = new User();

        user.setUserAddress("南昌大学");
        user.setUsername("hqf5");
        user.setNickname("hquuanfu");

        user.setUsername("zxf2");
        user.setPassword("123456");
        user.setUserAddress("南昌");
        userMapper.insert(user);

    }
}
