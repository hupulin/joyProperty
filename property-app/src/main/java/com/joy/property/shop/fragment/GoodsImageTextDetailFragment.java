package com.joy.property.shop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jinyi.ihome.module.newshop.GoodsDetail;
import com.jinyi.ihome.module.shop.GoodsParameterTo;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joyhome.nacity.app.util.htmlText.HtmlTextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@SuppressLint("ValidFragment")
public class GoodsImageTextDetailFragment extends BaseFragment {

    private WebView webView;
    private ScrollView scrollView;
    private GoodsDetail goodsDetail;
    private GridLayout gridView;
    public HtmlTextView htmlText;
    private boolean init;

    public GoodsImageTextDetailFragment(GoodsDetail goodsDetail){
       this.goodsDetail=goodsDetail;
   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
         View mRootView   = inflater.inflate(R.layout.fragment_goods_detail_image_text, container, false);

         findById(mRootView);



        return mRootView;


    }


    private void setGoodsParameter() {
        if (goodsDetail.getGoodsParameterList()==null)
            return;
        List<GoodsParameterTo> parameterList=new ArrayList<>();
        parameterList=goodsDetail.getGoodsParameterList();
             Collections.reverse(parameterList);
    for (GoodsParameterTo parameterTo:parameterList){


        View mView=View.inflate(getThisContext(),R.layout.goods_parameter_item,null);
        ((TextView)(mView.findViewById(R.id.parameterName))).setText(parameterTo.getParameterName());
        ((TextView)(mView.findViewById(R.id.parameterValue))).setText(parameterTo.getParameterValue());

        gridView.addView(mView);

    }

    }
    private void setGoodsImageText() {
        if (goodsDetail.getGoodsDetailsStr()!=null)
        htmlText.setHtmlFromString(goodsDetail.getGoodsDetailsStr(),false);

    }


    private void findById(View view) {
        gridView = (GridLayout) view.findViewById(R.id.gridView);
        htmlText = (HtmlTextView) view.findViewById(R.id.htmlText);
    }



public void setView(){
    if (!init) {
        setGoodsParameter();
        setGoodsImageText();
        init=true;
    }
}





    @Override
    protected Context getThisContext() {
        return getActivity();
    }
}
