package com.kwb.saller.controller;

import com.kwb.entity.Product;
import com.kwb.saller.service.ProductRpcService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "产品Rpc接口", description = "产品远程过程调用接口文档")
@RestController
@RequestMapping(value = "products/")
public class ProductRpcController {

    @Autowired
    private ProductRpcService productRpcService;

    @GetMapping(value = "")
    public List getAllProduct(){
        return productRpcService.findAll();
    }

    @GetMapping(value = "{id}")
    public Product getProductDetail(@PathVariable("id") String id){
        return productRpcService.findOne(id);
    }
}
