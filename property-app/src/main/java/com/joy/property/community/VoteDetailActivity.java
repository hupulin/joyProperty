package com.joy.property.community;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.base.BaseActivity;
import com.joyhome.nacity.app.util.CustomDialogFragment;

/**
 * Created by Admin on 2015-07-09
 */
public class VoteDetailActivity extends BaseActivity {

    private String mVoteSid = "";
    private WebView mWebView;
    private TextView mTitle;
    private RelativeLayout layoutVote ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);
        findById();
        getIntentData();
        initData();
    }


    private void findById() {
        mTitle = (TextView) findViewById(R.id.title);
        mWebView = (WebView) findViewById(R.id.webView);
        layoutVote = (RelativeLayout) findViewById(R.id.layout_guide);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    private void getIntentData() {

        mVoteSid = getIntent().getStringExtra("voteSid");
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initData() {
        mTitle.setText("业主调查");
        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBlockNetworkImage(true);
        String webURl = MainApp.DefaultValue.VOTE_URL + mUserHelper.getSid() + "/" + mVoteSid;
        final CustomDialogFragment customDialog= new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(),"");
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                customDialog.dismissAllowingStateLoss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(webURl);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        layoutVote.removeView(mWebView);
        mWebView.removeAllViews();
        mWebView.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT <=Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.getInstance().startSync();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.getInstance().stopSync();
        }
        mWebView.stopLoading();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected String toPageName() {
        return "业主调查页";
    }
}
