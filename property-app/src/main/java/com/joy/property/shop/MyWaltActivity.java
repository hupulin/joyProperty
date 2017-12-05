package com.joy.property.shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.shop.MyCouponActivity;


// Created by xz on 2016/5/13.

public class MyWaltActivity extends BaseActivity implements View.OnClickListener {
    private ImageView headImage;

    private TextView nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_walt_bulk);
        findId();
        initData();
    }

    private void initData() {
        if (mUserHelper.getUserInfoTo() != null) {
            displayImage(headImage, mUserHelper.getUserInfoTo().getImage(), R.drawable.guest_head_image);

        }
        if (mUserHelper.getUserInfoTo() !=null && !TextUtils.isEmpty(mUserHelper.getUserInfoTo().getFamilyName())) {
            nickName.setText(mUserHelper.getUserInfoTo().getName());
        }
    }

    private void findId() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        headImage= (ImageView) findViewById(R.id.iv_head);
        findViewById(R.id.myCouponLayout).setOnClickListener(this);
        nickName= (TextView) findViewById(R.id.tv_name);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:

                onBackPressed();
                break;
            case R.id.myCouponLayout:
                Intent intent = new Intent(getThisContext(), MyCouponActivity.class);
                getThisContext().startActivity(intent);
                goToAnimation(1);
                break;
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }



    private void init() {
        nickName.setText("");
        headImage.setImageResource(R.drawable.guest_head_image);
    }
    public void initTitle(String name){
        TextView mTextView= (TextView) findViewById(R.id.title_name);
        mTextView.setText(name);
    }
}
