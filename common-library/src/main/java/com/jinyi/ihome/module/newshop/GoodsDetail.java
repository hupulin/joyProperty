package com.jinyi.ihome.module.newshop;

import com.jinyi.ihome.module.shop.GoodsLabelTo;
import com.jinyi.ihome.module.shop.GoodsParameterTo;
import com.jinyi.ihome.module.shop.GoodsSpecificationsTo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 2017/1/10.
 */
public class GoodsDetail implements Serializable{

    /**
     * goodsId : 15555120369042432
     * goodsName : 时间
     * retailPrice : 3
     * goodsLabel : null
     * labelColor : null
     * categoryId : 14214114562343936
     * categoryName : 3333
     * merchantId : 14119730665998336
     * storesName : 南都物业
     * salesType : 自营商品
     * picUrl : null
     * discountPercentage :
     * currentPrice : 3.00
     * isGroup : 非团购
     * num : null
     * minSalesNum : 33
     * maxSalesNum : 33
     * activityStartTime : null
     * activityEndTime : null
     * salesNum : 17
     * goodsDetailsStr : <p>测试</p>
     * goodsDetails : PHA+suLK1DwvcD4=
     */

    private String goodsId;
    private String goodsName;
    private double retailPrice;
    private String goodsLabel;
    private String labelColor;
    private long categoryId;
    private String categoryName;
    private String merchantId;
    private String storesName;
    private String salesType;
    private String picUrl;
    private double discountPercentage;
    private double currentPrice;
    private String isGroup;
    private int num;
    private int minSalesNum;
    private int maxSalesNum;
    private String activityStartTime;
    private String activityEndTime;
    private String salesNum;
    private String goodsDetailsStr;
    private String goodsDetails;
    private String fakeSalesNum;
    private String goodsType;
    private List<GoodsParameterTo> goodsParameterList;
    private List<GoodsSpecificationsTo>goodsSpecificationsList;
    private List<GoodsLabelTo>goodsLableList;
    private String parameterDescription;
    private String inventoryNum;
    private String isInvalid;
    private String activityId;
    public Double getDistributionCost() {
        return distributionCost;
    }

    public void setDistributionCost(Double distributionCost) {
        this.distributionCost = distributionCost;
    }

    private String distributionMode;
    private Double distributionCost;
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

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
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

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
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

    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public String getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(String salesNum) {
        this.salesNum = salesNum;
    }

    public String getGoodsDetailsStr() {
        return goodsDetailsStr;
    }

    public void setGoodsDetailsStr(String goodsDetailsStr) {
        this.goodsDetailsStr = goodsDetailsStr;
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

    public String getDistributionMode() {
        return distributionMode;
    }

    public void setDistributionMode(String distributionMode) {
        this.distributionMode = distributionMode;
    }

    public String getFakeSalesNum() {
        return fakeSalesNum;
    }

    public void setFakeSalesNum(String fakeSalesNum) {
        this.fakeSalesNum = fakeSalesNum;
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

    public String getParameterDescription() {
        return parameterDescription;
    }

    public void setParameterDescription(String parameterDescription) {
        this.parameterDescription = parameterDescription;
    }

    public String getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(String inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(String isInvalid) {
        this.isInvalid = isInvalid;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "GoodsDetail{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", retailPrice=" + retailPrice +
                ", goodsLabel='" + goodsLabel + '\'' +
                ", labelColor='" + labelColor + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", storesName='" + storesName + '\'' +
                ", salesType='" + salesType + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", currentPrice=" + currentPrice +
                ", isGroup='" + isGroup + '\'' +
                ", num=" + num +
                ", minSalesNum=" + minSalesNum +
                ", maxSalesNum=" + maxSalesNum +
                ", activityStartTime='" + activityStartTime + '\'' +
                ", activityEndTime='" + activityEndTime + '\'' +
                ", salesNum='" + salesNum + '\'' +
                ", goodsDetailsStr='" + goodsDetailsStr + '\'' +
                ", goodsDetails='" + goodsDetails + '\'' +
                ", fakeSalesNum='" + fakeSalesNum + '\'' +
                ", goodsParameterList=" + goodsParameterList +
                ", goodsSpecificationsList=" + goodsSpecificationsList +
                ", goodsLableList=" + goodsLableList +
                ", distributionMode='" + distributionMode + '\'' +
                ", distributionCost=" + distributionCost +
                '}';
    }
}
