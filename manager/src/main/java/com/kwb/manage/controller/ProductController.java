package com.kwb.manage.controller;

import com.kwb.entity.Product;
import com.kwb.manage.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


@Api(tags = "产品相关",description = "产品相关")//用于创建标签及其标题
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

/*    swagger 注解@ApiOperation为方法添加说明及备注*/

    @ApiOperation(value = "创建产品", notes = "id不能为空以及部分验证规则")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Product addProduct(Product product){
        logger.info("add a new product={}",product);
        Product result = productService.addPorduct(product);
        logger.info("add product success");
        return result;
    }

//    @ApiOperation(value = "查询单个产品", notes = "传递一个产品id")
    @RequestMapping( value = "/{id}",method = RequestMethod.GET)
    public Product queryOneProduct(@PathVariable("id") String id){
        logger.info(" query one record that id={}",id);
        if(null == id && "".equals(id)){
            return null;
        }else{
            return productService.getOneProduct(id);
        }
    }


    @RequestMapping(value="/{id}",method = RequestMethod.POST)
    public void updateOneProduct(Product product) {

    }


    @ApiOperation(value = "查询多个产品", notes = "单个或多个条件" )
    @GetMapping(value = "")
    public Page queryProductPage(String ids, BigDecimal minRewardRate, BigDecimal maxRewardRate,
                                 String status,
                                 @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        logger.info("query many record that match follow condition,id in{},minRewardRate={},minRewardRat={}" +
                "status in{},pageNum={},pageSize={}", ids, minRewardRate, maxRewardRate, status, pageNum, pageSize);
        List<String> idList = null,statusList = null;
        if(!StringUtils.isEmpty(ids)){
            idList = Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)) {
            statusList = Arrays.asList(status.split(","));
        }
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<Product> page = productService.listPageProduct(idList, minRewardRate, maxRewardRate, statusList, pageable);
        logger.info("The result of query that page product : {}", page);
        return page;
    }

}
