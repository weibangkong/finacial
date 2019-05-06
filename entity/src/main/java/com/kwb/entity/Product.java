package com.kwb.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "产品",description = "产品模型")//用于对model层进行整体说明
public class Product implements Serializable{
    @Id
    private String id;
    private String name;
    @ApiModelProperty(value = "产品状态", dataType = "com.kwb.manager.enums.ProductStatus")
    private String status;
    //起头金额
    private BigDecimal thresholdAmount;
    //投资步长
    private BigDecimal stepAmount;
    //锁定期
    private Integer lockTerm;
    //收益率
    private BigDecimal rewardRate;
    private String memo;
    private Date createAt;
    private Date updateAt;
    private String createUser;
    private String updateUser;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    public BigDecimal getStepAmount() {
        return stepAmount;
    }

    public Integer getLockTerm() {
        return lockTerm;
    }

    public BigDecimal getRewardRate() {
        return rewardRate;
    }

    public String getMemo() {
        return memo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public String getCreateUser() {
        return createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public void setStepAmount(BigDecimal stepAmount) {
        this.stepAmount = stepAmount;
    }

    public void setLockTerm(Integer lockTerm) {
        this.lockTerm = lockTerm;
    }

    public void setRewardRate(BigDecimal rewardRate) {
        this.rewardRate = rewardRate;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Product() {
    }

//    public String toString() {
//        //性能并不如自己重写
//        return ReflectionToStringBuilder.toString(this,ToStringStyle.DEFAULT_STYLE);
//    }
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", thresholdAmount=" + thresholdAmount +
                ", stepAmount=" + stepAmount +
                ", lockTerm=" + lockTerm +
                ", rewardRate=" + rewardRate +
                ", memo='" + memo + '\'' +
                ", createat=" + createAt +
                ", updateat=" + updateAt +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }

    public Product(String id, String name, String status, BigDecimal stepAmount, Integer lockTerm, BigDecimal rewardRate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.stepAmount = stepAmount;
        this.lockTerm = lockTerm;
        this.rewardRate = rewardRate;
    }

    public Product(String id, String name, String status, BigDecimal thresholdAmount, BigDecimal stepAmount, Integer lockTerm, BigDecimal rewardRate, String memo, Date createAt, Date updateAt, String createUser, String updateUser) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.thresholdAmount = thresholdAmount;
        this.stepAmount = stepAmount;
        this.lockTerm = lockTerm;
        this.rewardRate = rewardRate;
        this.memo = memo;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
}
