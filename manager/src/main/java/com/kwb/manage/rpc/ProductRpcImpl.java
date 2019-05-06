package com.kwb.manage.rpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.kwb.api.ProductRpc;
import com.kwb.api.domain.ProductRpcRequest;
import com.kwb.entity.Product;
import com.kwb.manage.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Rpc服务实现类
 *
 */
@AutoJsonRpcServiceImpl//标记该类为jsonrpc服务实现类
@Service
public class ProductRpcImpl implements ProductRpc {

    private static Logger logger = LoggerFactory.getLogger(ProductRpcImpl.class);

    @Autowired
    private ProductService productService;

    @Override
    public List<Product> queryPage(ProductRpcRequest productRpcReq) {
        logger.info("查询多个产品开始，请求:{}",productRpcReq);
        //分页参数对象
        Pageable pageable = new PageRequest(productRpcReq.getPage(), productRpcReq.getPageSize(), productRpcReq.getDirection()
                , productRpcReq.getOrderBy());
        Page<Product> result = productService.listPageProduct(productRpcReq.getIdList(), productRpcReq.getMinRewardRate()
                , productRpcReq.getMaxRewardRate(), productRpcReq.getStatusList(), pageable);//不适用复杂对象，不适用复杂数据类型
        logger.info("查询多个产品完成，结果:{}",result);
        return result.getContent();
    }

    @Override
    public Product findOne(String id) {
        logger.info("查询产品详情，请求:{}",id);
        Product result = null;
        if (!StringUtils.isEmpty(id)) {
            result = productService.getOneProduct(id);
        }
        logger.info("查询产品详情，结果:{}",result);
        return result;
    }
}
