package com.kwb.swagger;

import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@Documented
@EnableSwagger2         //添上这个就是一个组合注解
@Import(com.kwb.swagger.SwaggerConfig.class)
public @interface EnableMySwagger {
}
