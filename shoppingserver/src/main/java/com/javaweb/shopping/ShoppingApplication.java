package com.javaweb.shopping;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import tk.mybatis.spring.annotation.MapperScan;

@Controller
@SpringBootApplication
@EnableScheduling
public class ShoppingApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShoppingApplication.class, args);
    }

}
