package com.joy.property.worksign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.signencode.SXHttpUtils;
import com.Util.signencode.aes.WLHSecurityUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jinyi.ihome.module.worksign.SignJsonTo;
import com.jinyi.ihome.module.worksign.SignMessageTo;
import com.jinyi.ihome.module.worksign.SignRecordTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.worksign.adapter.SignBaseParam;
import com.joyhome.nacity.app.MainApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xzz on 2017/12/29.
 */

public class SignDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_detail_record);
        setBack();
        getData();


    }

    private void setBack() {

        findViewById(R.id.back).setOnClickListener(v -> {finish();goToAnimation(2);});
    }

    private void getData() {

        SignJsonTo jsonTo = new SignJsonTo();

        jsonTo.setTradeType("GetSignDetail");
        jsonTo.setSignId(getIntent().getIntExtra("SignSid",0));
        jsonTo.setOpenId(mUserHelper.getSid());
        jsonTo.setUniqueStr(getDeviceUid());

        SXHttpUtils.requestPostData(SignDetailActivity.this, jsonTo, new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                SignMessageTo<SignRecordTo.SignListTo> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);
                System.out.println(msg + "msg====");
                SignRecordTo.SignListTo singTo = new Gson().fromJson(new Gson().toJson(msg.getResultContent()), SignRecordTo.SignListTo.class);

                initView(singTo);


            }

            @Override
            public void onLoadError() {
                showSignNetError();
            }
        });
    }

    private void initView(SignRecordTo.SignListTo recordTo) {

        ((TextView) findViewById(R.id.sign_time)).setText("签到时间：" + DateUtil.getDateString(DateUtil.getFormatDateLongTime(recordTo.getCreatetime()), DateUtil.mDateTimeFormatStringNoSecond));
        ((TextView) findViewById(R.id.work_content)).setText("工作内容：" + recordTo.getWork_cont());
        ((TextView) findViewById(R.id.sign_position)).setText(recordTo.getAddress());
        ((TextView) findViewById(R.id.remark_content)).setText(recordTo.getRemark());
        ((TextView) findViewById(R.id.worker_name)).setText(recordTo.getBranch_name() + "  " + recordTo.getUser_name());
        GridLayout girdView = (GridLayout) findViewById(R.id.grid_view);

        if (!TextUtils.isEmpty(recordTo.getWork_img())) {
            String[] imageList = recordTo.getWork_img().split(",");
            for (int i = 0; i < imageList.length; i++) {

                ImageView imageView = new ImageView(getThisContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = (int) (getScreenWidth() * (170.5 / 720));
                params.height = (int) (getScreenWidth() * (170.5 / 720));
                if (i % 3 == 0)
                    params.leftMargin = 0;
                else
                    params.leftMargin = (int) (getScreenWidth() * 50.0 / 720);
                params.bottomMargin = (int) (getScreenWidth() * 30.0 / 720);
                imageView.setLayoutParams(params);

                Glide.with(getThisContext()).load(MainApp.getImagePath(imageList[i])).into(imageView);
                imageView.setTag(imageList[i]);
                imageView.setOnClickListener(v -> {
                    Intent intent = new Intent(this, PostImageDetailActivity.class);
                    intent.putExtra("CurrentPath", (String) v.getTag());
                    intent.putExtra("PathList", recordTo.getWork_img());
                    startActivity(intent);
                });
                girdView.addView(imageView);
            }
        }
    }
}
