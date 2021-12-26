package com.javaweb.shopping;

import com.javaweb.shopping.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.TimeZone;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
@MapperScan(basePackages = "com.javaweb.shopping.mapper")
public class ShoppingApplication {
    public static void main(String[] args) {
        //转换时区
        TimeZone time = TimeZone.getTimeZone("GMT-8");  //转换为中国时区
        TimeZone.setDefault(time);
        SpringApplication.run(ShoppingApplication.class, args);
    }

}
