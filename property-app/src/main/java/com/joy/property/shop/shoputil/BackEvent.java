package com.joy.property.shop.shoputil;

import com.jinyi.ihome.module.newshop.CouponTo;

/**
 * Created by xz on 2016/4/6.
 */
public class BackEvent {
    private String mMsg;
    private CouponTo mCouponTo;
    public BackEvent(String msg,CouponTo couponTo) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
        mCouponTo=couponTo;
    }
    public String getMsg(){
        return mMsg;
    }
    public CouponTo getmCouponTo(){
        return mCouponTo;
    }
}
