/*
package com.javaweb.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2//开启Swagger2
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("商城后端接口说明")
                .description("此文档详细说明了商城项目后端接口规范")
                .version("v 2.0.2")
                .contact(new Contact("张小锋",null,"2311997861@qq.com"));
        ApiInfo apiInfo = apiInfoBuilder.build();
        docket.apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.javaweb.shopping.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}*/
