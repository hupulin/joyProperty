package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by usb on 2017/4/12.
 */

public class CouponCalculateParam {

    /**
     * cartId : 
     * couponId : 
     * userId : 
     * goodsType : 
     * merchantId : 
     * merchantGoodslist : [{"goodsId":"","goodsNum":"","specificationsId":"","goodsType":"","retailPrice":""}]
     * activityGoodsList : [{"goodsId":"","goodsNum":"","specificationsId":"","goodsType":"","retailPrice":""}]
     */

    private String cartId;
    private String couponId;
    private String userId;
    private String goodsType;
    private String merchantId;
    private List<CouponCalculateTo> merchantGoodslist;
    private List<CouponCalculateTo> activityGoodsList;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public List<CouponCalculateTo> getMerchantGoodslist() {
        return merchantGoodslist;
    }

    public void setMerchantGoodslist(List<CouponCalculateTo> merchantGoodslist) {
        this.merchantGoodslist = merchantGoodslist;
    }

    public List<CouponCalculateTo> getActivityGoodsList() {
        return activityGoodsList;
    }

    public void setActivityGoodsList(List<CouponCalculateTo> activityGoodsList) {
        this.activityGoodsList = activityGoodsList;
    }

    @Override
    public String toString() {
        return "CouponCalculateParam{" +
                "cartId='" + cartId + '\'' +
                ", couponId='" + couponId + '\'' +
                ", userId='" + userId + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", merchantGoodslist=" + merchantGoodslist +
                ", activityGoodsList=" + activityGoodsList +
                '}';
    }
}
