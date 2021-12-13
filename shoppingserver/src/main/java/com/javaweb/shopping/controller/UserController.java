package com.javaweb.shopping.controller;

import com.javaweb.shopping.service.UserService;
import com.javaweb.shopping.service.impl.UserServiceImpl;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@Controller
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping ("user/login")
    public ResultVO login(@RequestParam("username") String name,
                          @RequestParam(value = "password") String pwd){
        ResultVO resultVO = userService.checkLogin(name, pwd);
        return resultVO;
    }


    @RequestMapping ("user/regist")
    public ResultVO regist(@RequestParam User user){
        ResultVO resultVO = userService.userResgit(user.getUsername(), user.getPassword());
        return resultVO;
    }


    @GetMapping("/check")
    public ResultVO userTokencheck(@RequestHeader("token") String token){
        return new ResultVO(ResStatus.OK,"success",null);
    }

    @GetMapping("/becomevip")
    public ResultVO becomeVIP(@RequestParam("username") String name){
        return userService.becomeVIP(name);
    }
}