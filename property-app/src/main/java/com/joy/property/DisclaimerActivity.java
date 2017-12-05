package com.joy.property;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.base.BaseActivity;

public class DisclaimerActivity extends BaseActivity
        implements OnClickListener {

    private WebView mWebView;
    private RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        findById();
        initDatas();
    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mWebView = (WebView) findViewById(R.id.webView);
        layout = (RelativeLayout) findViewById(R.id.layout_disclaimer);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initDatas() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        final String url = MainApplication.DefaultValue.PROTOCOL_URL;
        mWebView.getSettings().setJavaScriptEnabled(true);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(url);
                mWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        customDialog.dismiss();
                    }
                });
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        layout.removeView(mWebView);
        mWebView.removeAllViews();
        mWebView.destroy();
    }

    @Override
    protected Context getThisContext() {
        return DisclaimerActivity.this;
    }

    @Override
    protected String toPageName() {
        return "免责声明";
    }
}
