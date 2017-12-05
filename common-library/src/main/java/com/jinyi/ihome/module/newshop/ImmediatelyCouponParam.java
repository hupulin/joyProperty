package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/20.
 */
public class ImmediatelyCouponParam {

    /**
     * userId :
     * goodsId :
     * goodsNum :
     * currentPrice :
     */

    private String userId;
    private String goodsId;
    private String goodsNum;
    private String retailPrice;
   private String goodsType;
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "ImmediatelyCouponParam{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsNum='" + goodsNum + '\'' +
                ", retailPrice='" + retailPrice + '\'' +
                ", goodsType='" + goodsType + '\'' +
                '}';
    }
}
