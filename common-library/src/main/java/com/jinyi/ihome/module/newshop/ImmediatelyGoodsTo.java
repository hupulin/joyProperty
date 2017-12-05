package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2017/1/13.
 */
public class ImmediatelyGoodsTo implements Serializable{


    /**
     * goodsId : 81
     * goodsName : mock
     * goodsPic : mock
     * merchantId : 72
     * merchantName : mock
     * storesName : mock
     * distributionMode : mock
     * distributionCost : 44
     * goodsTotalAmount : 59
     * actualPayAmount : 17
     * goodsNum : mock
     * retailPrice : mock
     * goodsType : mock
     * specificationsId : mock
     * specificationsName : mock
     */

    private String goodsId;
    private String goodsName;
    private String goodsPic;
    private String merchantId;
    private String merchantName;
    private String storesName;
    private String distributionMode;
    private double distributionCost;
    private double goodsTotalAmount;
    private double actualPayAmount;
    private int goodsNum;
    private double retailPrice;
    private String goodsType;
    private String specificationsId;
    private String specificationsName;
    private String couponId;
    private double couponCost;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getStoresName() {
        return storesName;
    }

    public void setStoresName(String storesName) {
        this.storesName = storesName;
    }

    public String getDistributionMode() {
        return distributionMode;
    }

    public void setDistributionMode(String distributionMode) {
        this.distributionMode = distributionMode;
    }

    public double getDistributionCost() {
        return distributionCost;
    }

    public void setDistributionCost(double distributionCost) {
        this.distributionCost = distributionCost;
    }

    public double getGoodsTotalAmount() {
        return goodsTotalAmount;
    }

    public void setGoodsTotalAmount(double goodsTotalAmount) {
        this.goodsTotalAmount = goodsTotalAmount;
    }

    public double getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(double actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getSpecificationsId() {
        return specificationsId;
    }

    public void setSpecificationsId(String specificationsId) {
        this.specificationsId = specificationsId;
    }

    public String getSpecificationsName() {
        return specificationsName;
    }

    public void setSpecificationsName(String specificationsName) {
        this.specificationsName = specificationsName;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public double getCouponCost() {
        return couponCost;
    }

    public void setCouponCost(double couponCost) {
        this.couponCost = couponCost;
    }

    @Override
    public String toString() {
        return "ImmediatelyGoodsTo{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPic='" + goodsPic + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", storesName='" + storesName + '\'' +
                ", distributionMode='" + distributionMode + '\'' +
                ", distributionCost=" + distributionCost +
                ", goodsTotalAmount=" + goodsTotalAmount +
                ", actualPayAmount=" + actualPayAmount +
                ", goodsNum=" + goodsNum +
                ", retailPrice=" + retailPrice +
                ", goodsType='" + goodsType + '\'' +
                ", specificationsId='" + specificationsId + '\'' +
                ", specificationsName='" + specificationsName + '\'' +
                ", couponId='" + couponId + '\'' +
                ", couponCost=" + couponCost +
                '}';
    }
}
