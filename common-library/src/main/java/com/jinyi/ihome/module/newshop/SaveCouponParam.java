package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2017/1/8.
 **/
public class SaveCouponParam implements Serializable{

    /**
     * cartId :
     * merchantId :
     * couponId :
     */

    private String cartId;
    private String merchantId;
    private String couponId;

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

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
}
