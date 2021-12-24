package com.javaweb.shopping.config;

import com.javaweb.shopping.controller.CheckTokenInterceptor;
import com.javaweb.shopping.interceptor.AdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//添加管理员权限对应的接口访问路径
@Configuration
public class AdminInterConfig implements WebMvcConfigurer {

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor).addPathPatterns("/shop/delete")
                .addPathPatterns("/shop/updateproduct")
                .addPathPatterns("/shop/addproductparam")
                .addPathPatterns("/shop/updateproductparam")
                .addPathPatterns("/shop/deleteproduct");
    }
}
