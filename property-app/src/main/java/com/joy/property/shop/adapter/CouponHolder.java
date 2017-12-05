package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by xz on 2016/7/22.
 */
public class CouponHolder {
    private View view;
    private TextView couponPrice;
    private TextView useCondition;
    private TextView containTraffic;
    private TextView useTime;
    private TextView receive;

    public CouponHolder(View view) {
        this.view = view;
    }
public TextView getCouponPrice(){
    if(couponPrice==null)
        couponPrice= (TextView) view.findViewById(R.id.couponPrice);
    return couponPrice;
}
    public TextView getUseCondition(){
        if(useCondition==null)
            useCondition= (TextView) view.findViewById(R.id.useCondition);
        return useCondition;
    }
    public TextView getContainTraffic(){
        if(containTraffic==null)
            containTraffic= (TextView) view.findViewById(R.id.containTraffic);
        return containTraffic;
    }
    public TextView getUseTime(){
        if(useTime==null)
            useTime= (TextView) view.findViewById(R.id.useTime);
        return useTime;
    }
    public TextView getReceive(){
        if(receive==null)
            receive= (TextView) view.findViewById(R.id.receive);
        return receive;
    }
}
