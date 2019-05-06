package com.kwb.saller.controller;

import com.kwb.entity.Order;
import com.kwb.saller.entity.OrderParam;
import com.kwb.saller.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "订单接口", description="订单接口相关说明文档")
@Controller
@RequestMapping(value = "order/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 申购订单
     * @param authId
     * @param sign
     * @param orderParam
     * @return
     */
    @ApiOperation(value = "申购订单")
    @RequestMapping(value="apply", method = RequestMethod.POST)
    @ResponseBody
    public Order apply(@RequestHeader String authId, @RequestHeader String sign, OrderParam orderParam) {
        Order order = new Order();
        BeanUtils.copyProperties(orderParam,order);
        order = orderService.apply(order);
        return order;
    }
}
