package com.joy.property.shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;

import com.joy.common.api.ApiUrlUtil;
import com.joy.property.R;
import com.joyhome.nacity.app.base.BaseActivity;

/**
 * Created by Admin on 2014-11-13.
 * 通知详情页
 */

public class ThirdPartActivity extends BaseActivity {
    private WebView webView;
    private TextView showTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hy_market);

        findById();

        initDatas();
    }

    private void findById() {
        webView = (WebView) findViewById(R.id.webView);
        ((TextView) findViewById(R.id.show_title)).setText(TextUtils.isEmpty(getIntent().getStringExtra("LayoutTitle")) ? "花样菜场" : getIntent().getStringExtra("LayoutTitle"));
        findViewById(R.id.iv_back).setOnClickListener(v -> {
            onBackPressed();
        });


    }


    @SuppressLint("setJavaScriptEnabled")
    private void initDatas() {

        String url = ApiUrlUtil.BULK_BASE_URL + "/api/thirdPartyAccessApp/loginApp?userId=" + mUserHelperBulk.getSid() + "&thirdPartyAccessId=" + getIntent().getStringExtra("thirdPartyAccessId");
        System.out.println(url + "url===");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view,url);

            }
        });

        webView.loadUrl(url);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
