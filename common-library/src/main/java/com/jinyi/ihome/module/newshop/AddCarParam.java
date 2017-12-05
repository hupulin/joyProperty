package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2017/1/6.
 */
public class AddCarParam implements Serializable {

    /**
     * userId :
     * goodsId :
     */

    private String userId;
    private String goodsId;
    private String communityId;
    private String num;
    private String specificationsId;
    private String specificationsName;
    private String goodsType;
    private String activityId;
    private String merchantId;
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

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "AddCarParam{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", communityId='" + communityId + '\'' +
                ", num='" + num + '\'' +
                ", specificationsId='" + specificationsId + '\'' +
                ", specificationsName='" + specificationsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                '}';
    }
}
