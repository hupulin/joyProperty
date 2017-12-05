package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by xz on 2016/7/25.
 */
public class MyShoppingHolder {
    private View view;
    private GridLayout gridView;
    private TextView olderStatus;
    private TextView goodsNumber;
    private TextView price;
    private TextView contactCustomService;
    private TextView payOlder;
    private TextView deleteOlder;
    private TextView olderTime;
    private LinearLayout expressLayout;
    public MyShoppingHolder(View view) {
        this.view = view;
    }
    private RelativeLayout payLayout;
    public TextView getOlderStatus() {
        if (olderStatus == null)
            olderStatus = (TextView) view.findViewById(R.id.olderStatus);
        return olderStatus;
    }

    public TextView getGoodsNumber() {
        if (goodsNumber == null)
            goodsNumber = (TextView) view.findViewById(R.id.goodsNumber);
        return goodsNumber;

    }

    public TextView getPrice() {
        if (price == null)
            price = (TextView) view.findViewById(R.id.price);
        return price;
    }

    public GridLayout getGridView() {
        if (gridView == null)
            gridView = (GridLayout) view.findViewById(R.id.gridView);
        return gridView;
    }
    public TextView getContactCustomService() {
        if (contactCustomService == null)
            contactCustomService = (TextView) view.findViewById(R.id.contactCustomService);
        return contactCustomService;
    }
    public TextView getPayOlder() {
        if (payOlder == null)
            payOlder = (TextView) view.findViewById(R.id.payOlder);
        return payOlder;
    }
    public TextView getDeleteOlder() {
        if (deleteOlder == null)
            deleteOlder = (TextView) view.findViewById(R.id.deleteOlder);
        return deleteOlder;
    }
    public TextView getOlderTime() {
        if (olderTime == null)
            olderTime = (TextView) view.findViewById(R.id.olderTime);
        return olderTime;
    }
    public LinearLayout getExpressLayout(){
        if (expressLayout==null)
            expressLayout= (LinearLayout) view.findViewById(R.id.expressLayout);
        return expressLayout;
    }
    public RelativeLayout getPayLayout(){
        if (payLayout==null)
            payLayout= (RelativeLayout) view.findViewById(R.id.payLayout);
        return payLayout;
    }
}
