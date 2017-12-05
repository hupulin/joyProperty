package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/9.
 */
public class SubmitGoodsInfo {


    /**
     * goodsId :
     * goodsName :
     * goodsNum :
     * retailPrice :
     * goodsType :
     * specificationsId :
     * specificationsName :
     */

    private String goodsId;
    private String goodsName;
    private String goodsNum;
    private String retailPrice;
    private String goodsType;
    private String specificationsId;
    private String specificationsName;
    private String activityId;

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
        return "SubmitGoodsInfo{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNum='" + goodsNum + '\'' +
                ", retailPrice='" + retailPrice + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", specificationsId='" + specificationsId + '\'' +
                ", specificationsName='" + specificationsName + '\'' +
                '}';
    }
}
