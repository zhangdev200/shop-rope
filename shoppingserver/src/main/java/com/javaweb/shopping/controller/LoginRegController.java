package com.javaweb.shopping.controller;

import com.javaweb.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRegController {
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public void login(){

    }
}