package com.joy.property.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.home.ServiceMessageParam;
import com.jinyi.ihome.module.home.ServiceMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.application.KeyValue;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.MainApp;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class LeaveMessageActivity extends BaseActivity
        implements OnClickListener {

    private WebView mWebView;

    private ServiceMainExpandTo mainExpandTo;
    private Button mSend;
    private EditText mContent;
    private LinearLayout _layoutMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_message);
        findById();
        initIntentDatas();
        initDatas();
    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mWebView = (WebView) findViewById(R.id.webView);
        mContent = (EditText) findViewById(R.id.content);
        mSend = (Button) findViewById(R.id.btn_send);
        mSend.setOnClickListener(this);
        _layoutMessage = (LinearLayout) findViewById(R.id.layout_message);

    }

    private void initIntentDatas() {
        mainExpandTo = (ServiceMainExpandTo) getIntent().getSerializableExtra("mode");


    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initDatas() {

        String    url =(!ConfigUtil.getString(sp, KeyValue.KEY_USER_INFO, "").equals(SpUtil.getString(getThisContext(), "HomeInfo"))? MainApplication.DefaultValue.CHAT_URL_Park: MainApplication.DefaultValue.CHAT_URL )+ mUserHelper.getSid() + "/" + mainExpandTo.getServiceSid();

        System.out.println(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialogFragment.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                super.shouldOverrideUrlLoading(view, url);

                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(url);


        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    mSend.setBackgroundResource(R.drawable.login_out_press_ic);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.btn_send:
                if (checking()) {
                    return;
                }
                send();
                break;
            default:
                break;
        }
    }

    private boolean checking() {
        if (TextUtils.isEmpty(mContent.getText())) {
            Toast.makeText(this, "请输入您想要留言的内容", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void send() {
        mSend.setEnabled(false);
        mSend.setBackgroundResource(R.drawable.property_null_msg_bg);
        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceMessageParam  param = new ServiceMessageParam();
        param.setServiceSid(mainExpandTo.getServiceSid());
        param.setCreatedBy(mUserHelper.getSid());
        param.setServiceMessage(mContent.getText().toString());
        api.addServiceMessage(param, new HttpCallback<MessageTo<ServiceMessageTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMessageTo> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    if (getCurrentFocus()!=null) {
                        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(getCurrentFocus()
                                                .getWindowToken(),
                                        InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    mContent.setText("");
                    mSend.setEnabled(true);
                    mWebView.reload();
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        _layoutMessage.removeView(mWebView);
        mWebView.removeAllViews();
        mWebView.destroy();
    }

    @Override
    protected Context getThisContext() {
        return LeaveMessageActivity.this;
    }

    @Override
    protected String toPageName() {

        return "留言页";
    }
}
