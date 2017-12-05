package com.jinyi.ihome.module.newshop;

import com.jinyi.ihome.module.shop.GoodsLabelTo;
import com.jinyi.ihome.module.shop.GoodsParameterTo;
import com.jinyi.ihome.module.shop.GoodsSpecificationsTo;

import java.util.Date;
import java.util.List;

/**
 * Created by xz on 2016/7/14.
 **/
public class ShopListDetailTo {






    /**
     * goodsId : 15407474123801600
     * goodsName : 水果
     * retailPrice : 2
     * goodsLabel : null
     * categoryId : 14214114562343936
     * categoryName : 3333
     * merchantId : 14119730665998336
     * storesName : 南都物业
     * salesType : 自营商品
     * picUrl : web_f090f955-3ebf-4e00-916a-54b76bf7aa12
     * discountPercentage :
     * currentPrice : 2.00
     * isGroup : 非团购
     * num : null
     * minSalesNum : null
     * maxSalesNum : null
     * activityStartTime : null
     * activityEndTime : null
     */

    private String goodsId;
    private String goodsName;
    private double retailPrice;
    private String goodsLabel;
    private long categoryId;
    private String categoryName;
    private String merchantId;
    private String storesName;
    private String salesType;
    private String picUrl;
    private String discountPercentage;
    private String currentPrice;
    private String isGroup;
    private int num;
    private int salesNum;
    private int minSalesNum;
    private int maxSalesNum;
    private Date activityStartTime;
    private Date activityEndTime;
    private String labelColor;
    private String distributionMode;
    private String goodsType;
    private List<GoodsParameterTo>goodsParameterList;
    private List<GoodsSpecificationsTo>goodsSpecificationsList;
    private List<GoodsLabelTo>goodsLableList;


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getGoodsLabel() {
        return goodsLabel;
    }

    public void setGoodsLabel(String goodsLabel) {
        this.goodsLabel = goodsLabel;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getStoresName() {
        return storesName;
    }

    public void setStoresName(String storesName) {
        this.storesName = storesName;
    }

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(int salesNum) {
        this.salesNum = salesNum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getMinSalesNum() {
        return minSalesNum;
    }

    public void setMinSalesNum(int minSalesNum) {
        this.minSalesNum = minSalesNum;
    }

    public int getMaxSalesNum() {
        return maxSalesNum;
    }

    public void setMaxSalesNum(int maxSalesNum) {
        this.maxSalesNum = maxSalesNum;
    }

    public String getDistributionMode() {
        return distributionMode;
    }

    public void setDistributionMode(String distributionMode) {
        this.distributionMode = distributionMode;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public List<GoodsParameterTo> getGoodsParameterList() {
        return goodsParameterList;
    }

    public void setGoodsParameterList(List<GoodsParameterTo> goodsParameterList) {
        this.goodsParameterList = goodsParameterList;
    }

    public List<GoodsSpecificationsTo> getGoodsSpecificationsList() {
        return goodsSpecificationsList;
    }

    public void setGoodsSpecificationsList(List<GoodsSpecificationsTo> goodsSpecificationsList) {
        this.goodsSpecificationsList = goodsSpecificationsList;
    }

    public List<GoodsLabelTo> getGoodsLableList() {
        return goodsLableList;
    }

    public void setGoodsLableList(List<GoodsLabelTo> goodsLableList) {
        this.goodsLableList = goodsLableList;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "ShopListDetailTo{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", retailPrice=" + retailPrice +
                ", goodsLabel='" + goodsLabel + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", storesName='" + storesName + '\'' +
                ", salesType='" + salesType + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", discountPercentage='" + discountPercentage + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                ", isGroup='" + isGroup + '\'' +
                ", num=" + num +
                ", salesNum=" + salesNum +
                ", minSalesNum=" + minSalesNum +
                ", maxSalesNum=" + maxSalesNum +
                ", activityStartTime=" + activityStartTime +
                ", activityEndTime=" + activityEndTime +
                ", labelColor='" + labelColor + '\'' +
                ", distributionMode='" + distributionMode + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsParameterList=" + goodsParameterList +
                ", goodsSpecificationsList=" + goodsSpecificationsList +
                ", goodsLableList=" + goodsLableList +
                '}';
    }
}
