package com.joy.property.shop;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.newshop.PicTextTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.htmlText.HtmlTextView;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2017/1/15.
 */
public class PictureTextActivity extends BaseActivity implements View.OnClickListener {

    private HtmlTextView htmlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_and_text);
        initView();
        getData();
    }



    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView)findViewById(R.id.titleText)).setText(TextUtils.isEmpty(getIntent().getStringExtra("title"))?"图文详情":getIntent().getStringExtra("title"));
        htmlText = (HtmlTextView) findViewById(R.id.htmlText);
    }
    private void getData() {
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getPicText(getIntent().getStringExtra("id"), new HttpCallback<MessageTo<PicTextTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<PicTextTo> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getSuccess() == 0 && msg.getData() != null && msg.getData().getGraphicDetails() != null)
                    htmlText.setHtmlFromString(msg.getData().getGraphicDetails(), false);
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                ToastShowLong(getThisContext(), error.toString());
            }
        });
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
