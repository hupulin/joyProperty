package com.joy.property.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.owner.LostPwdParam;
import com.jinyi.ihome.module.owner.NewPwdParam;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.UserApi;
import com.joy.common.helper.UserInfoHelper;
import com.joy.library.utils.MD5;
import com.joy.property.R;
import com.joy.property.utils.StatuBarUtil;
import com.joyhome.nacity.app.base.BaseActivity;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2016/6/21.
 */
public class ResetPwdActivity extends BaseActivity
        implements View.OnClickListener {

    private EditText etPhone;
    private EditText etPassWord;
    private EditText reetPassWord;
    private Button btnNextStep;
    private EditText etVerifyCode;
    private Button mReSend;
    private Boolean firstSend = false;
    private NewPwdParam param = null;
    private InputMethodManager manager;
    private Thread mThread;
    private String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PublicWay.activityList.add(this);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setContentView(R.layout.activity_resetpwd);
        StatuBarUtil.setStatueBarTransparent(getWindow());
        findById();
        //ActivityBarColorUtil.initWindowState(this, R.color.transparent);
        /**
         * ----------------------------------失去焦点----------------------------------
        **/
        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    if (etPhone.getText().toString().trim().length() < 4) {
                        Toast.makeText(ResetPwdActivity.this, "用户名不能小于4个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        etPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(etPassWord.getText().toString().trim().length()<6){
                        Toast.makeText(ResetPwdActivity.this, "密码不能小于6个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        reetPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    if (!reetPassWord.getText().toString().trim().equals(etPassWord.getText().toString().trim())) {
                        Toast.makeText(ResetPwdActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

    }


    private void findById() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        if("true".equals(getIntent().getStringExtra("changePassword"))){
            TextView tvTitle = (TextView)findViewById(R.id.titel);
            tvTitle.setText("修改密码");
        }
        etPhone = (EditText) findViewById(R.id.phone);
        etPassWord = (EditText) findViewById(R.id.password);
        reetPassWord = (EditText) findViewById(R.id.confirm);
        btnNextStep = (Button) findViewById(R.id.complete);
        btnNextStep.setOnClickListener(this);

        etVerifyCode = (EditText) findViewById(R.id.code);

        mReSend = (Button) findViewById(R.id.send);
        mReSend.setOnClickListener(this);

        SmsContent content = new SmsContent(ResetPwdActivity.this, new Handler(), etVerifyCode);
        // 注册短信变化监听

        this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
//                if("true".equals(getIntent().getStringExtra("changePassword"))){
//                    onBackPressed();
//                }else{
//                    startActivity(new Intent(getThisContext(), LoginActivity.class));
//                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//                    finish();
//                }
                onBackPressed();
                break;


            case R.id.complete:

                if (checking())
                    return;

                reset();

                break;


            case R.id.send:


                if (!firstSend) {
                    nextStep();

                } else {

                    ReSendVerifyCode();
                }
                break;

        }
    }


    @Override
    public void onBackPressed() {

            finish();
            for (Activity activity : PublicWay.activityList) {
                if (activity == ResetPwdActivity.this) {
                    activity.finish();
                    break;
                }
            }
            goToAnimation(2);


    }


    /**
     * 验证
     */

    private boolean checking() {
        if (TextUtils.isEmpty(etPhone.getText())) {
            Toast.makeText(this, "似乎您忘记输入手机号码了", Toast.LENGTH_SHORT).show();
            return true;
        } else if (etPhone.getText().toString().trim().length() != 11) {
            Toast.makeText(this, "糟糕！重置失败。请检查您的手机号码输入是否正确", Toast.LENGTH_LONG).show();
            return true;
        } else if (!etPhone.getText().toString().trim()
                .matches("^(1[3|4|5|7|8]\\d{9})$")) {
            Toast.makeText(this,
                    getResources().getString(R.string.register_remind_02),
                    Toast.LENGTH_LONG).show();
            return true;

        } else if (TextUtils.isEmpty(etVerifyCode.getText().toString())) {
            Toast.makeText(getThisContext(), "请先输入验证码", Toast.LENGTH_LONG).show();
            return true;
        } else if (!etVerifyCode.getText().toString().matches(code)) {
            Toast.makeText(this, "验证码似乎有问题，请检查您输入的验证码是否正确", Toast.LENGTH_LONG).show();
            return true;
        } else if (TextUtils.isEmpty(etPassWord.getText())) {

            Toast.makeText(this, "似乎您忘记输入密码了", Toast.LENGTH_SHORT).show();
            return true;
        } else if (etPassWord.getText().length() < 6) {
            Toast.makeText(this, "嘘...密码应该大于6个字符哦", Toast.LENGTH_LONG).show();
            return true;
        }else if(!etPassWord.getText().toString().trim().equals(reetPassWord.getText().toString().trim())) {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();

            return true;
        }

        return false;
    }

    private void nextStep() {


        String phoneNo = etPhone.getText().toString().trim();
        LostPwdParam mParam = new LostPwdParam();
        mParam.setPhoneNo(phoneNo);
        UserApi api = ApiClient.create(UserApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findWokerPasswordWithcode(mParam, new HttpCallback<MessageTo<String>>(this) {
            @Override
            public void success(MessageTo<String> msg, retrofit.client.Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    smsTime();
                    code = msg.getData();




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
        return ResetPwdActivity.this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ("true".equals(getIntent().getStringExtra("changePassword"))) {
                return super.onKeyDown(keyCode, event);
            } else {
                startActivity(new Intent(getThisContext(), LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        new Handler().removeCallbacks(mThread);
    }

    private void ReSendVerifyCode() {

        LostPwdParam mParam = new LostPwdParam();
        mParam.setPhoneNo(param.getPhoneNo());
        UserApi api = ApiClient.create(UserApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findWokerPasswordWithcode(mParam, new HttpCallback<MessageTo<String>>(this) {
            @Override
            public void success(MessageTo<String> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    code = msg.getData();


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

    public class SmsContent extends ContentObserver {


        public static final String SMS_URI_INBOX = "content://sms/inbox";
        private Activity activity = null;
        private String smsContent = "";
        private EditText verifyText = null;

        public SmsContent(Activity activity, Handler handler, EditText verifyText) {
            super(handler);
            this.activity = activity;
            this.verifyText = verifyText;

        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            if (Integer.valueOf(android.os.Build.VERSION.SDK) >= 23)
                return;
            Cursor cursor = null;// 光标
            // 读取收件箱中指定号码的短信

            cursor = activity.managedQuery(Uri.parse(SMS_URI_INBOX), new String[]{"_id", "body", "read"}, "read=?",
                    new String[]{"0"}, "date desc");
            if (cursor != null) {// 如果短信为未读模式
                cursor.moveToFirst();
                if (cursor.moveToFirst()) {
                    String smsbody = cursor.getString(cursor.getColumnIndex("body"));
                    System.out.println("smsbody=======================" + smsbody);
                    String regEx = "[^0-9]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(smsbody.toString());
                    smsContent = m.replaceAll("").trim().toString();
                    verifyText.setText(smsContent.substring(0,6));
                }
            }


        }
    }


    private void reset() {
        if(etPhone.getText().toString()==null){
            Toast.makeText(getThisContext(),"请先输入号码",Toast.LENGTH_LONG).show();
            return;
        }

        UserApi api = ApiClient.create(UserApi.class);
        String  pwd= etPassWord.getText().toString().trim();
        param = new NewPwdParam();
        param.setPhoneNo(etPhone.getText().toString().trim());
        param.setPwd(MD5.getMD5(pwd));
        System.out.print(etPhone.getText().toString().trim()+MD5.getMD5(pwd));
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.resetPwd(param, new HttpCallback<MessageTo<UserInfoTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<UserInfoTo> msg, Response response) {

                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {

                    UserInfoHelper.getInstance(getThisContext()).updateUser(msg.getData(),getThisContext());
                    mUserHelper.updateLogin(true);

                    startActivity(new Intent(getThisContext(), LoginActivity.class));
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    public void smsTime() {

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 60; i >= 0; i--) {
                    SystemClock.sleep(1000);
                    final int finalI = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (finalI == 0) {
                                mReSend.setText("再发一次");
                                mReSend.setEnabled(true);
                                return;
                            }
                            mReSend.setText(finalI + "秒后重发");
                            mReSend.setEnabled(false);
                        }
                    });
                }
            }
        });
        mThread.start();
    }


}
