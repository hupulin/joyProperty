package com.joy.property.visit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VisitorApi;
import com.joy.property.R;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.visitor.VerifyCardParam;
import com.jinyi.ihome.module.visitor.VerifyCardResultTo;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.SpUtil;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class SerialNumberActivity extends BaseActivity
        implements OnClickListener {

    private EditText mContent;
    private TextView mTitle;
    private ImageView changePark;
    private boolean firstChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_number);
        findById();

    }

    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mContent = (EditText) findViewById(R.id.et_no);
        findViewById(R.id.btn_verify).setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.mTitle);
        changePark = (ImageView) findViewById(R.id.changePark);
        changePark.setOnClickListener(this);
        if (SpUtil.getString(getThisContext(), "limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A014"))
        {
            changePark.setVisibility(View.VISIBLE);
            mTitle.setText("编号认证(住宅)");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.btn_verify:
                if (checking()) {
                    return;
                }

                verifyNo();

                break;
            case R.id.changePark:
                changePark();
                break;
            default:
                break;
        }
    }

    private void verifyNo() {

        VerifyCardParam param = new VerifyCardParam();
        param.setCardData("");
        param.setCardNo(mContent.getText().toString());
        param.setUserSid(mUserHelper.getSid());

        VisitorApi api = ApiClient.create(VisitorApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.verifyCardWithNo(param, new HttpCallback<MessageTo<VerifyCardResultTo>>(this) {
            @Override
            public void success(MessageTo<VerifyCardResultTo> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getSuccess() == 0) {
                    Intent intent = new Intent(getThisContext(), VerifyResultActivity.class);
                    intent.putExtra("mode", msg.getData());
                    intent.putExtra("carNo",mContent.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                super.failure(error);
            }
        });


    }

    private boolean checking() {
        if (TextUtils.isEmpty(mContent.getText().toString())) {

            Toast.makeText(this, "访客证编号不能为空哦", Toast.LENGTH_LONG).show();

            return true;
        }
        return false;
    }

    @Override
    protected Context getThisContext() {
        return SerialNumberActivity.this;
    }

    @Override
    protected String toPageName() {
        return "访客证ID验证";
    }
    private void changePark() {
        if (!firstChange){
            ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_home);
            firstChange=true;

            mTitle.setText("编号认证(园区)");
        }else {
            ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_park);
            firstChange=false;

            mTitle.setText("编号认证(住宅)");
        }
    }
}
