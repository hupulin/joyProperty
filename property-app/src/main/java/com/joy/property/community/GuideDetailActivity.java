package com.joy.property.community;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jinyi.ihome.module.guide.ServiceGuideTo;
import com.joy.property.R;
import com.joyhome.nacity.app.base.BaseActivity;
import com.joyhome.nacity.app.util.CustomDialogFragment;


/**
 * Created by Admin on 2015-01-07
 * 办事指南详情
 */
public class GuideDetailActivity extends BaseActivity
        implements OnClickListener {
    private ServiceGuideTo serviceGuideTo;
    private WebView webView;
    private RelativeLayout layoutGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);
        findById();
        initIntentDatas();
        initDatas();

    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.webView);
        layoutGuide = (RelativeLayout) findViewById(R.id.layout_guide);

    }

    private void initIntentDatas() {

        serviceGuideTo = (ServiceGuideTo) getIntent().getSerializableExtra("mode");
    }


    @SuppressLint("setJavaScriptEnabled")
    private void initDatas() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String data = String.format("<html><head><style>img{max-width:100%%;height:auto " +
                        "!important;width:auto !important;};</style>" +
                        "</head><body style='margin:0; padding:0;'>" +
                        "<h3 style='padding-top:20px;text-align:center'>%s</h3><hr />" +
                        "<div style='padding:10px'>%s</div></body></html>",
                serviceGuideTo.getGuideTitle(), serviceGuideTo.getGuideContent());

        final CustomDialogFragment customDialog= new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(),"");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                customDialog.dismiss();
            }
        });

        webView.loadDataWithBaseURL("", data, "text/html", "utf-8", "");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        layoutGuide.removeView(webView);
        webView.removeAllViews();
        webView.destroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
               onBackPressed();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
        }
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
         super.toPageName();
        return "指南详情页";
    }
}
