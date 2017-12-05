package com.joy.property.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.module.home.ServiceResponseParam;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceForwardParam;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.property.base.BaseActivity;
import com.joy.property.myservice.MyServiceOrderActivity;
import com.joy.property.utils.CustomDialog;
import com.joyhome.nacity.app.util.SpUtil;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-01-27
 */
public class ForwardDescActivity extends BaseActivity implements OnClickListener {

    private EditText mContent;

    private TextView mTip;
    private UserInfoTo mAssistant;
    private ServiceMainExpandTo mainExpandTo;
    private String left;
    private String rainbow;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward_desc);

        findById();
        initIntentDatas();
        initDatas();

    }

    private void initIntentDatas() {
        mAssistant = (UserInfoTo) getIntent().getSerializableExtra("assist");
        mainExpandTo = (ServiceMainExpandTo) getIntent().getSerializableExtra("mode");
        left = getIntent().getStringExtra("left");
        rainbow = getIntent().getStringExtra("rainbow");
    }


    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.title);
        mContent = (EditText) findViewById(R.id.content);
        Button mSubmit = (Button) findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);
        mTip = (TextView) findViewById(R.id.tip);
    }

    private void initDatas() {
        if(!TextUtils.isEmpty(left)){
            if (left.equals("left")) {
                mTitle.setText("派单描述");
            }
        }
        if(!TextUtils.isEmpty(rainbow)){
            if (rainbow.equals("rainbow")) {
                mTitle.setText("转发描述");
            }
        }

        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTip.setText("还可以输入" + (200 - s.length()) + "字");
                if (s.length() > 200) {
                    Toast.makeText(getThisContext(), "描述最多只能输入200字哦", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.submit:
                if (checking())
                    return;
                if(!TextUtils.isEmpty(left)){
                    if (left.equals("left")) {
                        outgiving();
                    }
                }
                if(!TextUtils.isEmpty(rainbow)){
                    if (rainbow.equals("rainbow")) {
                        submit();
                    }
                }


                break;
        }
    }

    private boolean checking() {
        if (TextUtils.isEmpty(mContent.getText())) {
            Toast.makeText(this, "描述不能为空哦,请输入", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private void submit() {
        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceForwardParam param = new ServiceForwardParam();
        param.setServiceSid(mainExpandTo.getServiceSid());
        param.setUserSid(mUserHelper.getSid());
        param.setAssistDesc(mContent.getText().toString());
        param.setAssistTo(mAssistant.getSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.forwardService(param, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Intent intent;
                    if (SpUtil.getBoolean(getThisContext(),"IsMyServiceJump")) {
                        intent = new Intent(getThisContext(), MyServiceOrderActivity.class);
                        intent.putExtra("IsForward",true);
                    }
                    else
                        intent = new Intent(getThisContext(), TaskHallActivity.class);
                    intent.putExtra("value", "0");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });
    }

    private void outgiving() {
        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceResponseParam param = new ServiceResponseParam();
        param.setGroupUserSid(mAssistant.getSid());
        param.setServiceSid(mainExpandTo.getServiceSid());
        param.setAssignType(1);
        param.setAssignDesc(mContent.getText().toString());
        param.setCareUserSid("");
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.distributionService(param, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Intent intent = new Intent(getThisContext(), TaskHallActivity.class);
                    intent.putExtra("value", "0");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });
    }

    @Override
    protected Context getThisContext() {
        return ForwardDescActivity.this;
    }

    @Override
    protected String toPageName() {

        return "转发描述";
    }
}
