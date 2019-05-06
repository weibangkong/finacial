package com.kwb.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@ComponentScan
public class SwaggerConfig {

    @Autowired
    private SwaggerParam swaggerParam;
    @Bean
    public Docket getApiConfig(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName("理财系统Api文档").apiInfo(apiInfo());
        ApiSelectorBuilder builder = docket.select();
        if (!StringUtils.isEmpty(swaggerParam.getBasePackage())) {
            builder.apis(RequestHandlerSelectors.basePackage(swaggerParam.getBasePackage()));
        }
        if(!StringUtils.isEmpty(swaggerParam.antPath)) {
            builder.paths(PathSelectors.ant(swaggerParam.antPath));
        }
        return builder.build();
    }

    /**
     * 文档描述相关
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("理财系统-管理端Api文档").description("管理端请求相关文档")
                .contact("Weibang Kong").license("Apache License Version 2.0").termsOfServiceUrl("http://springfox.io")
                .build();
    }
}
