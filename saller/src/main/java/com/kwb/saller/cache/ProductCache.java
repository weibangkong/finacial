package com.kwb.saller.cache;

import com.hazelcast.core.HazelcastInstance;
import com.kwb.api.ProductRpc;
import com.kwb.api.domain.ProductRpcRequest;
import com.kwb.entity.Product;
import com.kwb.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductCache {
    static final String CACHE_NAME = "sall_product_";

    Logger logger = LoggerFactory.getLogger(ProductCache.class);

    @Autowired
    private ProductRpc productRpc;

    @Autowired
    private HazelcastInstance hazelcastInstance;
    /**
     *
     * 在本类内调用所有有关缓存的方法，不在具有缓存效果
     *
     */


    /**
     * 读取缓存
     * @param id
     * @return
     */
    @Cacheable(cacheNames = CACHE_NAME)
    public Product readCache(String id) {
        logger.info("查询单个产品详情,id={}", id);
        Product result = productRpc.findOne(id);
        logger.info("查询单个产品详情，结果:{}", result);
        return null == result ? null : result;
    }

    /**
     * @CachePut 更新缓存中对应数据
     * @param product
     * @return
     */
    @CachePut(cacheNames = CACHE_NAME,key = "#product.id")
    public Product putCache(Product product) {
        return product;
    }

    /**
     * @CacheEvict 清除该id对应的缓存
     * @param id
     */
    @CacheEvict(cacheNames = CACHE_NAME)
    public void removeCache(String id) {
    }

    public List<Product> readAllCache(){
        /*
        * 在缓存中有数据的情况下将缓存中所存放的所有与product相关的数据返回
        * */
        Map map = hazelcastInstance.getMap(CACHE_NAME);
        List<Product> products = null;
        if (map.size() > 0) {
            products = new ArrayList<Product>();
            products.addAll(map.values());
        } else {
            products = findAll();
        }
        return products;
    }

    public List<Product> findAll() {
        ProductRpcRequest req = new ProductRpcRequest();
        List<String> status = new ArrayList<>();
        List<String> idList = new ArrayList<>();
        status.add(ProductStatus.AUDITING.name());
        idList.add("001");
        idList.add("002");
        idList.add("003");
        idList.add("004");
        req.setIdList(idList);
        req.setStatusList(status);
        req.setPage(0);
        req.setPageSize(1000);
        req.setMinRewardRate(new BigDecimal(0));
        req.setMaxRewardRate(new BigDecimal(30));
        req.setDirection(Sort.Direction.DESC);
        req.setOrderBy("rewardRate");
        logger.info("Rpc查询全部产品，请求：{}", req);
        List<Product> result =  productRpc.queryPage(req);
        System.err.println(result);
        logger.info("Rpc查询全部产品结果，{}", result);
        return result;
    }
}
