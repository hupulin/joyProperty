package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/9.
 */
public class ImmediatelySubmitMerchant {

    /**
     * merchantId :
     * couponId :
     * distributionMode :
     * distributionCost :
     * goodsTotalAmount :
     * actualPayAmount :
     */

    private String merchantId;
    private String couponId;
    private String distributionMode;
    private String distributionCost;
    private String goodsTotalAmount;
    private String actualPayAmount;
    private SubmitGoodsInfo merchantGoods;
    private SubmitGoodsInfo activityGoods;
    private String customerComments;
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getDistributionMode() {
        return distributionMode;
    }

    public void setDistributionMode(String distributionMode) {
        this.distributionMode = distributionMode;
    }

    public String getDistributionCost() {
        return distributionCost;
    }

    public void setDistributionCost(String distributionCost) {
        this.distributionCost = distributionCost;
    }

    public String getGoodsTotalAmount() {
        return goodsTotalAmount;
    }

    public void setGoodsTotalAmount(String goodsTotalAmount) {
        this.goodsTotalAmount = goodsTotalAmount;
    }

    public String getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(String actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public SubmitGoodsInfo getMerchantGoods() {
        return merchantGoods;
    }

    public void setMerchantGoods(SubmitGoodsInfo merchantGoods) {
        this.merchantGoods = merchantGoods;
    }

    public SubmitGoodsInfo getActivityGoods() {
        return activityGoods;
    }

    public void setActivityGoods(SubmitGoodsInfo activityGoods) {
        this.activityGoods = activityGoods;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    public void setCustomerComments(String customerComments) {
        this.customerComments = customerComments;
    }

    @Override
    public String toString() {
        return "ImmediatelySubmitMerchant{" +
                "merchantId='" + merchantId + '\'' +
                ", couponId='" + couponId + '\'' +
                ", distributionMode='" + distributionMode + '\'' +
                ", distributionCost='" + distributionCost + '\'' +
                ", goodsTotalAmount='" + goodsTotalAmount + '\'' +
                ", actualPayAmount='" + actualPayAmount + '\'' +
                ", merchantGoods=" + merchantGoods +
                '}';
    }
}
