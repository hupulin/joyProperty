package com.jinyi.ihome.module.newshop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xz on 2017/1/9.
 */
public class SubmitMerchant {

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
    private String customerComments="";
    private List<SubmitGoodsInfo>merchantGoodslist;


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

    public List<SubmitGoodsInfo> getMerchantGoodslist() {
        return merchantGoodslist;
    }

    public void setMerchantGoodslist(List<SubmitGoodsInfo> merchantGoodslist) {
        this.merchantGoodslist = merchantGoodslist;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    public void setCustomerComments(String customerComments) {
        this.customerComments = customerComments;
    }

    @Override
    public String toString() {
        return "SubmitMerchant{" +
                "merchantId='" + merchantId + '\'' +
                ", couponId='" + couponId + '\'' +
                ", distributionMode='" + distributionMode + '\'' +
                ", distributionCost='" + distributionCost + '\'' +
                ", goodsTotalAmount='" + goodsTotalAmount + '\'' +
                ", actualPayAmount='" + actualPayAmount + '\'' +
                ", customerComments='" + customerComments + '\'' +
                ", merchantGoodslist=" + merchantGoodslist +
                '}';
    }
}
