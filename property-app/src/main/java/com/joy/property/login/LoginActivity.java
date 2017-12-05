package com.joy.property.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.igexin.sdk.PushManager;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.common.DeviceParam;
import com.jinyi.ihome.module.common.DeviceTo;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.jinyi.ihome.module.owner.UserLoginParam;
import com.joy.common.api.ApiClient;
import com.joy.common.api.CommonApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.UserApi;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.MD5;
import com.joy.property.ActivityCollector;
import com.joy.property.MainActivity;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.SpUtil;

import cn.jpush.android.api.JPushInterface;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends BaseActivity
        implements OnClickListener, OnFocusChangeListener {

    private EditText mAccount;
    private EditText mPassword;
    private boolean passwordVisible;
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        findById();
        SpUtil.put(this, "IsStart", false);

    }

    private void findById() {
        mAccount = (EditText) findViewById(R.id.login_no);
        mAccount.setOnFocusChangeListener(this);
        mPassword = (EditText) findViewById(R.id.password);
        mPassword.setOnFocusChangeListener(this);
        Button mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        LinearLayout mItem01 = (LinearLayout) findViewById(R.id.llItem01);
        LinearLayout mItem02 = (LinearLayout) findViewById(R.id.llItem02);
        TextView tvForgetPwd = (TextView) findViewById(R.id.tv_forget_password);
        tvForgetPwd.setOnClickListener(this);
        findViewById(R.id.eyeLayout).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                if (checking())
                    return;
                login();
                break;
            case R.id.tv_forget_password:
                Intent intent = new Intent(this, ResetPwdActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.eyeLayout:

                if (!passwordVisible) {
                    mPassword.setInputType(InputType.TYPE_CLASS_DATETIME);
                    passwordVisible = true;
                } else {
                    mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordVisible = false;
                }
                break;
        }

    }

    private boolean checking() {
        if (TextUtils.isEmpty(mAccount.getText())) {

            Toast.makeText(this, "似乎您忘记输入账号", Toast.LENGTH_LONG).show();
            return true;
        } else if (TextUtils.isEmpty(mPassword.getText().toString().trim())) {
            Toast.makeText(this, "似乎您忘记输入密码了", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private void login() {
        UserApi api = ApiClient.create(UserApi.class);

        UserLoginParam param = new UserLoginParam();
        String pwd = mPassword.getText().toString().trim();
        param.setUserNo(mAccount.getText().toString().trim());
        param.setUserPwd(MD5.getMD5(pwd.getBytes()));
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.groupLogin(param, new HttpCallback<MessageTo<UserInfoTo>>(this) {
            @Override
            public void success(MessageTo<UserInfoTo> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getSuccess() == 0) {
                    mUserHelper.updateUser(msg.getData(), getThisContext());
                    System.out.println(msg.getData().getPhone() + "==========" + msg.getData().getName());
                    mUserHelper.updateLogin(true);
                    Intent intent = new Intent(getThisContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    SpUtil.put(getThisContext(), "HomeInfo", JSON.toJSONString(msg.getData()));
                    startActivity(intent);
                    finish();
                    upLoadDevice(mUserHelper.getSid());
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

    @Override
    protected Context getThisContext() {
        return LoginActivity.this;
    }

    /**
     * 通过焦点改变 变换背景图片
     *
     * @param view
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (!hasFocus) {
            switch (view.getId()) {
                case R.id.login_no:
//                    mItem01.setBackgroundResource(R.drawable.edit_02);
//                    mItem02.setBackgroundResource(R.drawable.edit_01);
                    break;
                case R.id.password:
//                    mItem01.setBackgroundResource(R.drawable.edit_01);
//                    mItem02.setBackgroundResource(R.drawable.edit_02);
                    break;


            }
        }
    }

    public void upLoadDevice(String sid) {
     String  client = PushManager.getInstance().getClientid(this);
        if (!TextUtils.isEmpty(client)) {
            DeviceParam param = new DeviceParam();
            CommonApi api = ApiClient.create(CommonApi.class);
            param.setDeviceToken(client);
            param.setDeviceApp(11);
            param.setOwnerSid(sid);
            param.setDeviceType("android");
            param.setType(1);
            param.setRegistrationId(JPushInterface.getRegistrationID(getThisContext()));
            Log.i("2222", "upLoadDevice: " + param.toString());
            Log.i("2222", "upLoadDevice: " + JPushInterface.getRegistrationID(getThisContext()));
            api.updateOwnerDeviceInfo(param, new HttpCallback<MessageTo<DeviceTo>>(this) {
                @Override
                public void success(MessageTo<DeviceTo> msg, Response response) {
                    if (msg.getSuccess() == 0) {
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                }
            });

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (ActivityCollector.mMainActivity!=null)
        {
            ActivityCollector.mMainActivity.finish();

        }
        ActivityCollector.finishAll();
        finish();
    }
}