package com.kwb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class VerificationOrder {
    @Id
    private String orderId;
    private String chanId;
    private String orderType;
    private String productId;
    private BigDecimal amount;
    private String outerOrderId;
    private String chanUserId;
    @JsonFormat(pattern = "yyyy-MM-dd hh24:mm:ss")
    private Date createAt;

    public VerificationOrder(String orderId, String chanId, String orderType, String productId, BigDecimal amount, String outerOrderId, String chanUserId, Date createAt) {
        this.orderId = orderId;
        this.chanId = chanId;
        this.orderType = orderType;
        this.productId = productId;
        this.amount = amount;
        this.outerOrderId = outerOrderId;
        this.chanUserId = chanUserId;
        this.createAt = createAt;
    }

    public VerificationOrder() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOuterOrderId() {
        return outerOrderId;
    }

    public void setOuterOrderId(String outerOrderId) {
        this.outerOrderId = outerOrderId;
    }

    public String getChanUserId() {
        return chanUserId;
    }

    public void setChanUserId(String chanUserId) {
        this.chanUserId = chanUserId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).build();
    }
}
