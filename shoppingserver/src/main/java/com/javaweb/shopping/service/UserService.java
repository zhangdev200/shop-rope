package com.javaweb.shopping.service;
<<<<<<< HEAD
=======

import com.javaweb.shopping.vo.ResultVO;
>>>>>>> d3a7ceeac86ae2f0096f5e030a36371d061c0421

import com.javaweb.shopping.vo.ResultVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
<<<<<<< HEAD

    //用户注册
    ResultVO userResgit(String name, String pwd);

    //用户登录
    ResultVO checkLogin(String name, String pwd);
=======
    //用户注册
    public ResultVO userResgit(String name, String pwd);

    //用户登录
    public ResultVO checkLogin(String name, String pwd);
>>>>>>> d3a7ceeac86ae2f0096f5e030a36371d061c0421
}
