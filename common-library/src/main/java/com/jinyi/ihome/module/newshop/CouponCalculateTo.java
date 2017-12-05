package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/4/17.
 */
public class CouponCalculateTo {

    /**
     * goodsId :
     * goodsNum :
     * specificationsId :
     * goodsType :
     * retailPrice :
     */

    private String goodsId;
    private String goodsNum;
    private String specificationsId;
    private String goodsType;
    private String retailPrice;

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

    public String getSpecificationsId() {
        return specificationsId;
    }

    public void setSpecificationsId(String specificationsId) {
        this.specificationsId = specificationsId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Override
    public String toString() {
        return "CouponCalculateTo{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsNum='" + goodsNum + '\'' +
                ", specificationsId='" + specificationsId + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", retailPrice='" + retailPrice + '\'' +
                '}';
    }
}
