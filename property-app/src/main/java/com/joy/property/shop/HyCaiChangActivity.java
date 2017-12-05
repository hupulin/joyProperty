package com.joy.property.shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.joy.property.R;
import com.joyhome.nacity.app.base.BaseActivity;

/**
 * Created by Admin on 2014-11-13.
 * 通知详情页
 */

public class HyCaiChangActivity extends BaseActivity  {
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
        ((TextView)findViewById(R.id.show_title)).setText(TextUtils.isEmpty(getIntent().getStringExtra("LayoutTitle"))?"花样菜场":getIntent().getStringExtra("LayoutTitle"));
        findViewById(R.id.iv_back).setOnClickListener(v -> {
        onBackPressed();
      });


    }





    @SuppressLint("setJavaScriptEnabled")
    private void initDatas() {
   /*http://s1.joyhomenet.com:9040/shop
   *http://proshop.joyhomenet.com:8081/ihome
   * */
        String url="http://proshop.joyhomenet.com:8081/ihome/api/thirdPartyAccessApp/loginApp?userId="+mUserHelperBulk.getSid()+"&thirdPartyAccessId="+getIntent().getStringExtra("thirdPartyAccessId");

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
                if (!(url.startsWith("http") || url.startsWith("https"))) {
                    return true;
                }

                    view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(url);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode()==KeyEvent.KEYCODE_BACK){
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
