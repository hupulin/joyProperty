package com.joy.property.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.Util.SpUtil;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.PaySuccessParam;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2017/1/12.
 **/
public class FinishPayActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_finish);
        initView();

        initData();

    }

    private void initView() {
        Button lookOlder = (Button) findViewById(R.id.lookOlder);
        lookOlder.setOnClickListener(this);
        findViewById(R.id.backMainPage).setOnClickListener(this);
        if (getIntent().getBooleanExtra("IsMyShopPage",false))
            lookOlder.setText("我的悦购");
        else
            lookOlder.setText("查看订单");
    }

    private void initData() {
        System.out.println(getIntent().getStringExtra("PayOrderId")+"payId");
        if (TextUtils.isEmpty(getIntent().getStringExtra("PayOrderId")))
            return;
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        PaySuccessParam param=new PaySuccessParam();
        param.setPayOrderId(getIntent().getStringExtra("PayOrderId"));
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
        api.paySuccess(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
            dialogFragment.dismiss();
                System.out.println(msg+"msg---");
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                System.out.println(error+"error");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backMainPage:
           onBackPressed();
                break;
            case R.id.lookOlder:

                for (Activity activity: PublicWay.activityList)
                    activity.finish();
                if (getIntent().getBooleanExtra("IsMyShopPage",false)){
                    Intent intent = new Intent(getThisContext(), MyShoppingActivity.class);
                    intent.putExtra("IsFinishPay", true);
                    startActivity(intent);
                    goToAnimation(1);
                }else {
                    Intent intent = new Intent(getThisContext(), OlderDetailActivity.class);
                    intent.putExtra("olderSid", getIntent().getStringExtra("olderSid"));
                    intent.putExtra("type", 2);
                    intent.putExtra("MyBulk", getIntent().getBooleanExtra("MyBulk", false));
                    startActivity(intent);


                    goToAnimation(2);
                }
              finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getIntent().getBooleanExtra("MyBulk",false)){
            Intent intent=new Intent(getThisContext(),MyShoppingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SpUtil.put(getThisContext(), "ShopCurrentPage", 2);
            startActivity(intent);
            finish();
            goToAnimation(2);
        }else {
            for (Activity activity: PublicWay.activityList)
                activity.finish();
            startActivity(new Intent(getThisContext(),ShoppingActivity.class));
        }

        finish();
        goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
}
