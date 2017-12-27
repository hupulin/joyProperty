package com.joy.property.shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joyhome.nacity.app.base.BaseActivity;

/**
 * Created by Admin on 2014-11-13.
 * 通知详情页
 */

public class EasyToHomeActivity extends BaseActivity  {
    private WebView webView;
    private TextView showTitle;
    private String backUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hy_market);

        findById();

        initDatas();
    }

    private void findById() {
        webView = (WebView) findViewById(R.id.webView);
        ((TextView)findViewById(R.id.show_title)).setText(TextUtils.isEmpty(getIntent().getStringExtra("LayoutTitle"))?"花样菜场":getIntent().getStringExtra("LayoutTitle"));
        findViewById(R.id.iv_back).setOnClickListener(v -> {
        onBackPressed();
      });


    }





    @SuppressLint("setJavaScriptEnabled")
    private void initDatas() {
//        String url="https://api.uyess.com/gzh/index.php?uyes_qd_no=uyes_hz_yjj_jd";
        String url="http://proshop.joyhomenet.com:8081/ihome/api/thirdPartyAccessApp/loginApp?userId="+mUserHelperBulk.getSid()+"&thirdPartyAccessId="+getIntent().getStringExtra("thirdPartyAccessId");

//     String url="https://api.uyess.com/gzh/index.php?uyes_qd_no=uyes_hz_yjj_jd";

        System.out.println(url+"url---");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println(url+"url========");
                backUrl=url;
                if (!(url.startsWith("http") || url.startsWith("https"))) {
                    return true;
                }

                final PayTask task = new PayTask(EasyToHomeActivity.this);
//                final String ex = task.fetchOrderInfoFromH5PayUrl(url);
//                if (!TextUtils.isEmpty(ex)) {
//                    System.out.println("paytask:::::" + url);
//                    new Thread(new Runnable() {
//                        public void run() {
//                            System.out.println("payTask:::" + ex);
//                            final H5PayResultModel result = task.h5Pay(ex, true);
//                            if (!TextUtils.isEmpty(result.getReturnUrl())) {
//                                EasyToHomeActivity.this.runOnUiThread(new Runnable() {
//
//                                    @Override
//                                    public void run() {
//                                        view.loadUrl(result.getReturnUrl());
//                                    }
//                                });
//                            }
//                        }
//                    }).start();
//                } else {
//                    view.loadUrl(url);
//                }
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(url);


    }



//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode==KeyEvent.KEYCODE_BACK&&webView.canGoBack()) {
//            webView.goBack();
//
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            if (backUrl == null || !backUrl.contains("service/index")) {
                webView.goBack();

                return true;
            }
        }
        onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
}
