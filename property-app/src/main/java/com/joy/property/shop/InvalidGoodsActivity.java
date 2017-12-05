package com.joy.property.shop;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.joy.property.R;
import com.joyhome.nacity.app.base.BaseActivity;

/**
 * Created by xz on 2017/1/14.
 */
public class InvalidGoodsActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_invalid_goods);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
}
