package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by xz on 2016/7/24.
 **/
public class MyCouponHolder {
    private View view;
    private TextView useCondition;
    private TextView useRange;
    private TextView useTime;
    private TextView couponAmount;
    private RelativeLayout layout;
    private RelativeLayout itemCoupon;
private RelativeLayout amountLayout;
    public MyCouponHolder(View view) {
        this.view = view;
    }
    public TextView getUseCondition(){
        if(useCondition==null)
            useCondition= (TextView) view.findViewById(R.id.useCondition);
        return useCondition;
    }
    public TextView getUseRange(){
        if(useRange==null)
            useRange= (TextView) view.findViewById(R.id.useRange);
        return useRange;
    }
    public TextView getUseTime(){
        if(useTime==null)
            useTime= (TextView) view.findViewById(R.id.useTime);
        return useTime;
    }
    public TextView getCouponAmount(){
        if(couponAmount==null)
            couponAmount= (TextView) view.findViewById(R.id.couponAmount);
        return couponAmount;
    }
    public RelativeLayout getAmountLayout(){
        if(amountLayout==null)
            amountLayout= (RelativeLayout) view.findViewById(R.id.amount_layout);
        return amountLayout;
    }
    public RelativeLayout getLayout(){
        if(layout==null)
            layout= (RelativeLayout) view.findViewById(R.id.layout);
        return layout;
    }
    public RelativeLayout getItemCoupon(){
        if(itemCoupon==null)
            itemCoupon= (RelativeLayout) view.findViewById(R.id.item_coupon);
        return itemCoupon;
    }
}
