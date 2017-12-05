package com.jinyi.ihome.module.newshop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 2017/1/7.
 */
public class CouponParam implements Serializable{


    private String cartId;
    private String merchantId;
    private String userId;
    private String goodsType;
    /**
     * goodsId : 15555120369042432
     * goodsNum : 18
     * currentPrice : 3.00
     */

    private List<CouponParamTo> merchantGoodslist;

    private List<CouponParamTo> activityGoodslist;
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CouponParamTo> getMerchantGoodslist() {
        return merchantGoodslist;
    }

    public void setMerchantGoodslist(List<CouponParamTo> merchantGoodslist) {
        this.merchantGoodslist = merchantGoodslist;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public static class CouponParamTo {
        private String goodsId;
        private String goodsNum;
        private String retailPrice;
        private String specificationsId;


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

        public String getSpecificationsId() {
            return specificationsId;
        }

        public void setSpecificationsId(String specificationsId) {
            this.specificationsId = specificationsId;
        }

        @Override
        public String toString() {
            return "CouponParamTo{" +
                    "goodsId='" + goodsId + '\'' +
                    ", goodsNum='" + goodsNum + '\'' +
                    ", retailPrice='" + retailPrice + '\'' +
                    ", specificationsId='" + specificationsId + '\'' +
                    '}';
        }
    }

    public List<CouponParamTo> getActivityGoodslist() {
        return activityGoodslist;
    }

    public void setActivityGoodslist(List<CouponParamTo> activityGoodslist) {
        this.activityGoodslist = activityGoodslist;
    }

    @Override
    public String toString() {
        return "CouponParam{" +
                "cartId='" + cartId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", userId='" + userId + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", merchantGoodslist=" + merchantGoodslist +
                ", activityGoodslist=" + activityGoodslist +
                '}';
    }
}
