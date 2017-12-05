package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/13.
 */
public class ImmediateBuyParam {

    /**
     * userId :
     * goodsId :
     * goodsNum :
     * currentPrice :
     * merchantId :
     */

    private String userId;
    private String goodsId;
    private String goodsNum;
    private String retailPrice;
    private String merchantId;
    private String discountPercentage;
    private String goodsType;
    private String specificationsId;
    private String specificationsName;
    private String activityId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
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

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "ImmediateBuyParam{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsNum='" + goodsNum + '\'' +
                ", retailPrice='" + retailPrice + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", discountPercentage='" + discountPercentage + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", specificationsId='" + specificationsId + '\'' +
                ", specificationsName='" + specificationsName + '\'' +
                '}';
    }
}