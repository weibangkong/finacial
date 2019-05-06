package com.kwb.api;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.kwb.api.domain.ProductRpcRequest;
import com.kwb.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 产品相关Rpc服务d
 */
@JsonRpcService("rpc/products")
@Component
public interface ProductRpc {

    /*
    * 查询多个产品
    * */
    List<Product> queryPage(ProductRpcRequest productRpcReq);

    /*
    * 查询单个产品
    * */
    Product findOne(String id);
}
