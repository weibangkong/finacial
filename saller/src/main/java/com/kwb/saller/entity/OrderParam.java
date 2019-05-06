package com.kwb.saller.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kwb.saller.sign.SignText;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;

public class OrderParam implements SignText {
    private String chanId;
    private String productId;
    private BigDecimal amount;
    private String outerOrderId;
    private String chanUserId;
    @ApiModelProperty(notes = "订单状态",dataType = "com.kwb.manage.enums.OrderStatus")
    private String memo;
    @JsonFormat(pattern = "yyyy-MM-dd hh24:mm:ss")
    private Date createAt;

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
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

    public OrderParam(String chanId, String productId, BigDecimal amount, String outerOrderId, String chanUserId, String memo, Date createAt) {
        this.chanId = chanId;
        this.productId = productId;
        this.amount = amount;
        this.outerOrderId = outerOrderId;
        this.chanUserId = chanUserId;
        this.memo = memo;
        this.createAt = createAt;
    }

    public OrderParam() {
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).build();
    }
}
