package com.joy.property.neighborhood;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.Util.ActivityBarColorUtil;

import com.joy.property.R;
import com.joy.property.base.BaseActivity;

import com.joyhome.nacity.app.util.CustomDialogFragment;


public class NeighborLinkShowActivity extends BaseActivity
        implements OnClickListener {

    private WebView webView;
    private TextView showTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_show);

        findById();

        initDatas();
    }

    private void findById() {
        webView = (WebView) findViewById(R.id.webView);
        showTitle = (TextView) findViewById(R.id.show_title);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }





    @SuppressLint("setJavaScriptEnabled")
    private void initDatas() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
       String urlTitle= getIntent().getStringExtra("urlTitle");
        if (urlTitle.length()>=3)
            urlTitle=urlTitle.substring(1,urlTitle.length()-2);
        showTitle.setText(urlTitle);
        webView.getSettings().setJavaScriptEnabled(true);


        webView.loadUrl(getIntent().getStringExtra("neighborUrl"));
       final CustomDialogFragment customDialog = new CustomDialogFragment();
 customDialog.show(getSupportFragmentManager(), "");
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                customDialog.dismissAllowingStateLoss();
                super.onPageFinished(view, url);

            }

//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//                view.loadUrl(url); // 在当前的webview中跳转到新的url
//
//                return true;
//            }
        });



    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    @Override
    protected String toPageName() {
        return "邻居圈";
    }

    public static void clearCookies(Context context) {
        // Edge case: an illegal state exception is thrown if an instance of
        // CookieSyncManager has not be created.  CookieSyncManager is normally
        // created by a WebKit view, but this might happen if you start the
        // app, restore saved state, and click logout before running a UI
        // dialog in a WebView -- in which case the app crashes
        @SuppressWarnings("unused")
        CookieSyncManager cookieSyncMngr =
                CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
