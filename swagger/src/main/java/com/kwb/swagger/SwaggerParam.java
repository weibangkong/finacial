package com.kwb.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="swagger")
public class SwaggerParam {
    String groupName;
    String basePackage;
    String antPath;
    String title = "Api接口文档";
    String description = "接口文档";
    String license;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getAntPath() {
        return antPath;
    }

    public void setAntPath(String antPath) {
        this.antPath = antPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public SwaggerParam() {
    }

    public SwaggerParam(String groupName, String basePackage, String antPath, String title, String description, String license) {
        this.groupName = groupName;
        this.basePackage = basePackage;
        this.antPath = antPath;
        this.title = title;
        this.description = description;
        this.license = license;
    }
}
