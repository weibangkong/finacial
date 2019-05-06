package com.kwb.saller.service;

import com.kwb.api.ProductRpc;
import com.kwb.entity.Order;
import com.kwb.entity.Product;
import com.kwb.enums.OrderStatus;
import com.kwb.enums.OrderType;
import com.kwb.enums.ProductStatus;
import com.kwb.saller.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;


/**
 *订单服务
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRpc productRpc;


    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


    public Order apply(Order order){
        //数据脚丫和
        checkOrder(order);
        completeOrder(order);
        return order;
    }

    /**
     * 校验数据，必填字段，产品是否存在，金额是否符合要求
     * @return
     */
    public void checkOrder(Order order) {
        Assert.notNull(order.getOuterOrderId(),"需要外部订单编号");
        Assert.notNull(order.getChanId(), "需要渠道编号");
        Assert.notNull(order.getChanUserId(),"需要用户编号");
        Assert.notNull(order.getProductId(),"需要产品编号");
        Assert.notNull(order.getAmount(), "需要购买金额");
        Assert.notNull(order.getCreateAt(),"需要订单时间");

        Product product = productRpc.findOne(order.getProductId());
        Assert.notNull(product, "产品不存在");
        //金额要满足起头金额要求，投资步长同理
        Assert.isTrue(order.getAmount().compareTo(product.getThresholdAmount())>0,"起投金额不满足要求");
        Assert.isTrue(product.getStatus().equals(ProductStatus.IN_SELL),"所选产品应是在售");
        return;
    }

    /**
     * 完成订单
     * @param order
     */
    public void completeOrder(Order order) {
        //设定订单号
        order.setOrderId(UUID.randomUUID().toString().replace("-",""));
        order.setCreateAt(new Date());
        order.setOrderType(OrderType.APPLY.name());
        order.setOrderStatuss(OrderStatus.SUCCESS.name());
        orderRepository.save(order);
        orderRepository.flush();
        logger.info("save the order entity");
    }
}
