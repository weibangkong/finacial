package com.kwb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "订单", description = "订单模型")
@Entity(name = "t_order")
public class Order {
    @Id
    private String orderId;
    private String chanId;
    private String orderType;
    private String productId;
    private BigDecimal amount;
    private String outerOrderId;
    private String chanUserId;
    @ApiModelProperty(notes = "订单状态",dataType = "com.kwb.manage.enums.OrderStatus")
    private String orderStatus;
    private String memo;
    @JsonFormat(pattern = "yyyy-MM-dd hh24:mm:ss")
    private Date createAt;
    @JsonFormat(pattern = "yyyy-MM-dd hh24:mm:ss")
    private Date updateAt;

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

    public String getOrderStatuss() {
        return orderStatus;
    }

    public void setOrderStatuss(String orderStatuss) {
        this.orderStatus = orderStatuss;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }


    public String getChanUserId() {
        return chanUserId;
    }

    public void setChanUserId(String chanUserId) {
        this.chanUserId = chanUserId;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
