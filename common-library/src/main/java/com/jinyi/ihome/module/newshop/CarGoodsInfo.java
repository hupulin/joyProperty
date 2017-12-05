package com.jinyi.ihome.module.newshop;

import com.jinyi.ihome.module.shop.GoodsLabelTo;
import com.jinyi.ihome.module.shop.GoodsParameterTo;
import com.jinyi.ihome.module.shop.GoodsSpecificationsTo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 2017/1/6.
 */
public class CarGoodsInfo implements Serializable {


 /**
     * goodsId : 15403711943214080
     * goodsName : 火龙果
     * retailPrice : 22
     * goodsLabel : null
     * categoryId : 14214114562343936
     * categoryName : 3333
     * merchantId : 14119730665998336
     * merchantName : 南都物业
     * storesName : 南都物业
     * salesType : 商户商品
     * picUrl : web_4b5ddfd9-604b-41f0-8b10-f65dbffef390
     * discountPercentage : 1
     * currentPrice : 22.00
     * isGroup : 非团购
     * num : null
     * minSalesNum : null
     * maxSalesNum : null
     * goodsNum : 1
     * isInvalid : 正常
     * distributionMode : 物业配送
     * distributionCost : 10
     */

    private String goodsId;
    private String goodsName;

    private String goodsLabel;
    private String categoryId="";
    private String categoryName="";
    private String merchantId="";
    private String merchantName="";
    private String storesName="";
    private String salesType="";
    private String picUrl;
    private double discountPercentage;
    private double retailPrice;
    private String isGroup;
    private int num;
    private int minSalesNum;
    private int maxSalesNum;
    private int goodsNum;
    private String isInvalid;
    private String distributionMode;
    private double distributionCost;
    private String merchantIdTop;
    private String merchantNameTop;
    private String storesNameTop;
    private String cartIdTop;
    private String goodsType;
    private String specificationsId;
    private String specificationsName;
    private String activityId;
    private List<GoodsParameterTo> goodsParameterList;
    private List<GoodsSpecificationsTo>goodsSpecificationsList;
    private List<GoodsLabelTo>goodsLableList;

    private boolean isSelect;
    private boolean isShopSelect;
    private boolean noLine;
    private boolean isEdit;
    private boolean isActivity;
    private String newSpecificationsId="";
    private String newSpecificationsName="";

    private String lastNewSpecificationsId="";
    private String lastNewSpecificationsName="";
    private double lastRetailPrice;
    private boolean isBottom;


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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
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

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
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

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(String isInvalid) {
        this.isInvalid = isInvalid;
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

    public String getMerchantIdTop() {
        return merchantIdTop;
    }

    public void setMerchantIdTop(String merchantIdTop) {
        this.merchantIdTop = merchantIdTop;
    }

    public String getMerchantNameTop() {
        return merchantNameTop;
    }

    public void setMerchantNameTop(String merchantNameTop) {
        this.merchantNameTop = merchantNameTop;
    }

    public boolean isNoLine() {
        return noLine;
    }

    public void setNoLine(boolean noLine) {
        this.noLine = noLine;
    }

    public String getStoresNameTop() {
        return storesNameTop;
    }

    public void setStoresNameTop(String storesNameTop) {
        this.storesNameTop = storesNameTop;
    }

    public String getCartIdTop() {
        return cartIdTop;
    }

    public void setCartIdTop(String cartIdTop) {
        this.cartIdTop = cartIdTop;
    }

    public boolean isShopSelect() {
        return isShopSelect;
    }

    public void setIsShopSelect(boolean isShopSelect) {
        this.isShopSelect = isShopSelect;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
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

    public boolean isActivity() {
        return isActivity;
    }

    public void setIsActivity(boolean isActivity) {
        this.isActivity = isActivity;
    }

    public String getNewSpecificationsId() {
        return newSpecificationsId;
    }

    public void setNewSpecificationsId(String newSpecificationsId) {
        this.newSpecificationsId = newSpecificationsId;
    }

    public String getNewSpecificationsName() {
        return newSpecificationsName;
    }

    public void setNewSpecificationsName(String newSpecificationsName) {
        this.newSpecificationsName = newSpecificationsName;
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

    public String getLastNewSpecificationsId() {
        return lastNewSpecificationsId;
    }

    public void setLastNewSpecificationsId(String lastNewSpecificationsId) {
        this.lastNewSpecificationsId = lastNewSpecificationsId;
    }

    public String getLastNewSpecificationsName() {
        return lastNewSpecificationsName;
    }

    public void setLastNewSpecificationsName(String lastNewSpecificationsName) {
        this.lastNewSpecificationsName = lastNewSpecificationsName;
    }

    public double getLastRetailPrice() {
        return lastRetailPrice;
    }

    public void setLastRetailPrice(double lastRetailPrice) {
        this.lastRetailPrice = lastRetailPrice;
    }

    public boolean isBottom() {
        return isBottom;
    }

    public void setIsBottom(boolean isBottom) {
        this.isBottom = isBottom;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "CarGoodsInfo{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsLabel='" + goodsLabel + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", storesName='" + storesName + '\'' +
                ", salesType='" + salesType + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", retailPrice=" + retailPrice +
                ", isGroup='" + isGroup + '\'' +
                ", num=" + num +
                ", minSalesNum=" + minSalesNum +
                ", maxSalesNum=" + maxSalesNum +
                ", goodsNum=" + goodsNum +
                ", isInvalid='" + isInvalid + '\'' +
                ", distributionMode='" + distributionMode + '\'' +
                ", distributionCost=" + distributionCost +
                ", merchantIdTop='" + merchantIdTop + '\'' +
                ", merchantNameTop='" + merchantNameTop + '\'' +
                ", storesNameTop='" + storesNameTop + '\'' +
                ", cartIdTop='" + cartIdTop + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", specificationsId='" + specificationsId + '\'' +
                ", specificationsName='" + specificationsName + '\'' +
                ", activityId='" + activityId + '\'' +
                ", goodsParameterList=" + goodsParameterList +
                ", goodsSpecificationsList=" + goodsSpecificationsList +
                ", goodsLableList=" + goodsLableList +
                ", isSelect=" + isSelect +
                ", isShopSelect=" + isShopSelect +
                ", noLine=" + noLine +
                ", isEdit=" + isEdit +
                ", isActivity=" + isActivity +
                ", newSpecificationsId='" + newSpecificationsId + '\'' +
                ", newSpecificationsName='" + newSpecificationsName + '\'' +
                ", isBottom=" + isBottom +
                '}';
    }
}
