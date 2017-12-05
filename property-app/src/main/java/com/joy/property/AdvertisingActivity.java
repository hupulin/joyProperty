package com.joy.property;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Util.ActivityBarColorUtil;
import com.jinyi.ihome.module.common.AdInfoTo;
import com.joy.library.utils.HtmlUtil;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.StatuBarUtil;
import com.joyhome.nacity.app.util.CustomDialogFragment;


public class AdvertisingActivity extends BaseActivity {

    private AdInfoTo adInfoTo;
    private WebView webView;
    private TextView mTitle;
    private RelativeLayout _layoutAdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising_show);
        findById();
        intIntentDatas();

        intDatas();
    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        webView = (WebView) findViewById(R.id.webView);
        mTitle = (TextView) findViewById(R.id.show_title);
        _layoutAdv = (RelativeLayout) findViewById(R.id.layout_adv);
    }

    private void intIntentDatas() {

        adInfoTo = (AdInfoTo) getIntent().getSerializableExtra("AdInfoTo");

    }

    @SuppressLint("setJavaScriptEnabled")
    private void intDatas() {
        mTitle.setText(adInfoTo.getAdTitle());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if ( !TextUtils.isEmpty(adInfoTo.getAdUrl())) {
                    webView.setWebViewClient(new WebViewClient() {

                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            webView.loadUrl(url);
                            return true;
                        }
                    });
                    webView.loadUrl(adInfoTo.getAdUrl());
                }
//                else {
//                    String  html= String.format("<html><head><style>img{max-width:100%%;height:auto !important;width:auto !important;};</style></head><body style='margin:0; padding:0;'>%s</body></html>",
//                            adInfoTo.getAdContent());
//
//                    webView.loadDataWithBaseURL(null, HtmlUtil.formatHtml(html), "text/html", "utf-8", null);
//
//                }
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        customDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _layoutAdv.removeView(webView);
        webView.removeAllViews();
        webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected String toPageName() {
        return "广告页";
    }
}
