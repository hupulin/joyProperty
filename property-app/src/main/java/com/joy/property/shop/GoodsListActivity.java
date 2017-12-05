package com.joy.property.shop;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.AddCarParam;
import com.jinyi.ihome.module.newshop.CarGoodsInfo;
import com.jinyi.ihome.module.newshop.CartSettleGoodsTo;
import com.jinyi.ihome.module.newshop.ChangeCarParam;
import com.jinyi.ihome.module.newshop.CouponParam;
import com.jinyi.ihome.module.newshop.CouponTo;
import com.jinyi.ihome.module.newshop.DeleteGoodsParam;
import com.jinyi.ihome.module.newshop.MakeOrderParam;
import com.jinyi.ihome.module.newshop.MyShopCarTo;
import com.jinyi.ihome.module.newshop.SaveCouponParam;
import com.jinyi.ihome.module.newshop.ShoppingCarTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.base.BaseActivity;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joy.property.shop.adapter.CouponAdapter;
import com.joy.property.shop.adapter.GoodsOlderListAdapter;
import com.joy.property.shop.adapter.ShoppingCarAdapter;
import com.joy.property.shop.shoputil.DoubleUtil;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by xz on 2016/7/21.
 **/
public class GoodsListActivity extends BaseActivity implements View.OnClickListener {


    private ListView listView;
    private List<CarGoodsInfo> infoList=new ArrayList<>();
    private GoodsOlderListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        initView();
        initData();

    }
    private void initView() {
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.bulkLayout).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.title)).setText("商品清单");
        listView = (ListView) findViewById(R.id.listView);
        listView.setDividerHeight(0);
        adapter = new GoodsOlderListAdapter(getThisContext());
        adapter.setList(infoList);
        listView.setAdapter(adapter);
    }
    private void initData() {
        infoList= (List<CarGoodsInfo>) getIntent().getSerializableExtra("goodsList");
        adapter.setList(infoList);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
        }
    }








    public void setView(List<MyShopCarTo> shopCarToList){



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SpUtil.put(getThisContext(),"IsGoodsListActivityBack",true);
        goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
}
