package com.kwb.saller.service;

import com.kwb.api.ProductRpc;
import com.kwb.api.event.ProductStatusEvent;
import com.kwb.entity.Product;
import com.kwb.enums.ProductStatus;

import com.kwb.saller.cache.ProductCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;
/*
* 产品服务
* */

@Service
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {

    /*
    * 虚拟主题消费者一定要以Consumer开头，以VirualTopic.XXX结尾
    * */
    private static final String MQ_DESTINATION = "Consumer.cache.VirtualTopic.PRODUCT_STATUS";

    @Autowired
    private  ProductRpc productRpc;

    @Autowired
    private ProductCache productCache;

    private static Logger logger = LoggerFactory.getLogger(ProductRpcService.class);

    public List<Product> findAll() {
        return productCache.readAllCache();
    }

    public Product findOne(String id){
        logger.info("查询单个产品详情,id={}", id);
//        Product result = productRpc.findOne(id);
        Product result = productCache.readCache(id);
        if (null == result) {
            productCache.removeCache(id);
        }
        logger.info("查询单个产品详情，结果:{}", result);
        return result;
    }

    @JmsListener(destination = MQ_DESTINATION)
    void updateCache(ProductStatusEvent event) {
        //先删除缓存，然后读取，达到更新缓存的目的
        productCache.removeCache(event.getId());
        logger.info("已收到消息");
        if (ProductStatus.IN_SELL.equals(event.getStatus())) {
            productCache.readCache(event.getId());
            logger.info("已更新产品缓存");
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //查询全部产品
        List<Product> products = findAll();
        //将产品便利放入缓存当中
        products.forEach(product -> {
            productCache.putCache(product);
        });
    }

//    @PostConstruct
//    public void testFindAll(){
//        logger.warn("开始测试 findAll 方法");
//        List<Product> list = findAll();
//        logger.debug("result 的长度为:{}",list.size());
//        logger.warn("findAll 方法执行完毕");
//    }
}
