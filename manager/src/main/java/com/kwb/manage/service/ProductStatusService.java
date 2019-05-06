package com.kwb.manage.service;

import com.kwb.api.event.ProductStatusEvent;
import com.kwb.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProductStatusService {

    private static final String MQ_DESTINATION = "VirtualTopic.PRODUCT_STATUS";
    private static final Logger logger = LoggerFactory.getLogger(ProductStatusService.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    public void changeStatus(String id, ProductStatus productStatus) {
        ProductStatusEvent event = new ProductStatusEvent(id, productStatus);
        jmsTemplate.convertAndSend(MQ_DESTINATION, event);
        logger.info("消息已发送：修改产品状态");
    }

    @PostConstruct
    public void init(){
        changeStatus("001",ProductStatus.FINISHED);
    }
}
