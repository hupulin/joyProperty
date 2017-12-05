package com.jinyi.ihome.module.newshop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xz on 2017/1/9.
 */
public class ActivitySubmitMerchant {

    /**
     * merchantId :
     * couponId :
     * distributionMode :
     * distributionCost :
     * goodsTotalAmount :
     * actualPayAmount :
     */


    private String couponId;
    private String distributionMode;
    private String distributionCost;
    private String goodsTotalAmount;
    private String actualPayAmount;
    private String customerComments="";
    private List<SubmitGoodsInfo>activityGoodslist=new ArrayList<>();


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



    public List<SubmitGoodsInfo> getActivityGoodslist() {
        return activityGoodslist;
    }

    public void setActivityGoodslist(List<SubmitGoodsInfo> activityGoodslist) {
        this.activityGoodslist = activityGoodslist;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    public void setCustomerComments(String customerComments) {
        this.customerComments = customerComments;
    }

    @Override
    public String toString() {
        return "ActivitySubmitMerchant{" +
                "couponId='" + couponId + '\'' +
                ", distributionMode='" + distributionMode + '\'' +
                ", distributionCost='" + distributionCost + '\'' +
                ", goodsTotalAmount='" + goodsTotalAmount + '\'' +
                ", actualPayAmount='" + actualPayAmount + '\'' +
                ", customerComments='" + customerComments + '\'' +
                ", activityGoodslist=" + activityGoodslist +
                '}';
    }
}
