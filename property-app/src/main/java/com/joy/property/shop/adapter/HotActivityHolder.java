package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;
import com.joy.property.utils.flowlayout.TagFlowLayout;

/**
 * Created by usb on 2017/4/5.
 */
public class HotActivityHolder {
    private View view;
    private TextView goodsName;
    private TextView goodsDescription;
    private TextView goodsPrice;
    private ImageView goodsImage;
    private TextView marketPrice;
    private TextView currentPriceName;

    private TagFlowLayout tagFlowLayout;
    public HotActivityHolder(View view){
        this.view=view;
    }
    public ImageView getGoodsImage(){
        if(goodsImage==null)
            goodsImage= (ImageView) view.findViewById(R.id.goodsImage);
        return goodsImage;
    }
    public TextView getGoodsName(){
        if(goodsName==null)
            goodsName= (TextView) view.findViewById(R.id.goodsName);
        return goodsName;
    }

public TextView getGoodsPrice(){
        if(goodsPrice==null)
            goodsPrice= (TextView) view.findViewById(R.id.goodsPrice);
        return goodsPrice;
    }

    public TextView getMarketPrice(){
        if(marketPrice==null)
            marketPrice= (TextView) view.findViewById(R.id.marketPrice);
        return marketPrice;
    }
public TextView getGoodsDescription(){
    if (goodsDescription==null)
        goodsDescription= (TextView) view.findViewById(R.id.goods_description);
    return goodsDescription;
}
    public TagFlowLayout getTagFlowLayout(){
        if (tagFlowLayout==null)
            tagFlowLayout= (TagFlowLayout) view.findViewById(R.id.flow_layout_label);
        return tagFlowLayout;
    }
public TextView getCurrentPriceName(){
    if (currentPriceName==null)
        currentPriceName= (TextView) view.findViewById(R.id.current_price_name);
      return currentPriceName;
}

}
