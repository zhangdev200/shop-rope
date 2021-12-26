//package com.javaweb.shopping.config;
//
//import com.javaweb.shopping.interceptor.AdminInterceptor;
//import com.javaweb.shopping.interceptor.ShopKeeperInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////添加商家权限对应的接口访问路径
//@Configuration
//public class ShopInterConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private ShopKeeperInterceptor shopKeeperInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(shopKeeperInterceptor).addPathPatterns("/shop/add")
//                .addPathPatterns("/shop/delete")
//                .addPathPatterns("/shop/updateproduct")
//                .addPathPatterns("/shop/addproductparam")
//                .addPathPatterns("/shop/updateproductparam")
//                .addPathPatterns("/shop/deleteproduct");
//    }
//}
