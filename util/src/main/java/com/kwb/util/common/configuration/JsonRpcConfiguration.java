package com.kwb.util.common.configuration;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * JsonRpc 自动化配置-------------------单独模块化
 */

@Configuration
public class JsonRpcConfiguration {

    private static Logger logger = LoggerFactory.getLogger(JsonRpcConfiguration.class);

    /**
     * 服务端配置
     * @return
     */
    @Bean
    public AutoJsonRpcServiceImplExporter rpcServiceImplExporter(){
        return new AutoJsonRpcServiceImplExporter();
    }

    /**
     * 客户端配置 --------------当配置文件中有包扫描路径和服务端url时才注册生效
     */
    @Bean
    @ConditionalOnProperty(value={"rpc.client.url","rpc.basePackage.path"})//根据配置文件参数判断是否生效 使用@ConditionalOnProperty
    public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.client.url}") String rpcServiceUrl, @Value("${rpc.basePackage.path}") String basePackagePath) {

        //创建代理
        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();

        //赋值URL
        try {
            creator.setBaseUrl(new URL(rpcServiceUrl));
        } catch (MalformedURLException e) {
            logger.error("创建Rpc服务地址出错",e);
        }
        logger.info("创建Rpc服务地址成功");

        // 赋值扫描包路径
        creator.setScanPackage(basePackagePath);

        return creator;
    }
}
