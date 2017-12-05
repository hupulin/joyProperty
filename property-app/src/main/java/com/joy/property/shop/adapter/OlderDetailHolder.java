package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by xz on 2016/7/25.
 */
public class OlderDetailHolder {
    private View view;
    private TextView expressFee;
    private TextView expressName;
    private GridLayout gridView;
    private TextView goodsNumber;
    private TextView expressCompany;

    public OlderDetailHolder(View view) {
        this.view = view;
    }
public TextView getExpressFee(){
    if(expressFee==null)
        expressFee= (TextView) view.findViewById(R.id.expressFee);
    return expressFee;
}
    public TextView getExpressName(){
        if(expressName==null)
            expressName= (TextView) view.findViewById(R.id.expressName);
        return expressName;
    }
    public TextView getGoodsNumber(){
        if(goodsNumber==null)
            goodsNumber= (TextView) view.findViewById(R.id.goodsNumber);
        return goodsNumber;
    }
    public TextView getExpressCompany(){
        if(expressCompany==null)
            expressCompany= (TextView) view.findViewById(R.id.expressCompany);
        return expressCompany;
    }
   public GridLayout getGridView(){
       if(gridView==null)
           gridView= (GridLayout) view.findViewById(R.id.gridView);
       return gridView;
   }

}
