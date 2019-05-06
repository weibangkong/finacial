package com.kwb.manage.configurations;

import com.kwb.manage.controller.ProductController;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
使用swagger：
1.添加依赖
2.添加配置类（使用默认属性即可）
*/


public class SwaggerConfiguration {

//    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("controller")
                .apiInfo(apiInfo())
                //指定生成某个包路径下，如不需要可去除.apis()方法
                .select().apis(RequestHandlerSelectors.basePackage(ProductController.class.getPackage().getName()))
                //指定生成某个请求路径的,如不需要可去除.paths()方法
                .paths(PathSelectors.ant("/products/*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Http API")
                .description("管理端接口")
                .termsOfServiceUrl("http://springfox.io")
                .license("Apache License Version 2.0")
                .version("2.0")
                .build();
    }

//    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("controller")
//                .apiInfo(apiInfo())
                //指定生成某个包路径下，如不需要可去除.apis()方法
                .select().apis(RequestHandlerSelectors.basePackage(BasicErrorController.class.getPackage().getName()))
                //指定生成某个请求路径的,如不需要可去除.paths()方法
//                .paths(PathSelectors.ant("/products/*"))
                .build();
    }
}
