package com.javaweb.shopping.controller;

import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.service.UserService;
import com.javaweb.shopping.service.impl.UserServiceImpl;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(value = "提供用户的登录和注册接口",tags = "用户管理")
@CrossOrigin
@Controller
public class UserController {

    @Resource
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username", value = "用户登录账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "password", value = "用户登录密码",required = true)
    })
    @RequestMapping ("user/login")
    public String login(){
       /* ResultVO resultVO = userService.checkLogin(name, pwd);
  //      logger.info(resultVO.getMsg());
        return resultVO;*/
       System.out.println("hello");
       return "hello";

    }



    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username", value = "用户注册账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "password", value = "用户注册密码",required = true)
    })
    @PostMapping("user/regist")
    public ResultVO regist(@RequestBody User user){
        ResultVO resultVO = userService.userResgit(user.getUsername(), user.getPassword());
        return resultVO;
    }

    @ApiOperation("校验token是否过期接口")
    @GetMapping("/check")
    public ResultVO userTokencheck(@RequestHeader("token") String token){
        return new ResultVO(ResStatus.OK,"success",null);
    }

}