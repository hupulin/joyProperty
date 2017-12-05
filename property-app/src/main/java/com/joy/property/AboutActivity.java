package com.joy.property;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.base.BaseActivity;

public class AboutActivity extends BaseActivity implements OnClickListener {

    private WebView mWebView;
    private RelativeLayout _layoutAbout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		findById();
        initDatas();
	}
    @SuppressLint("setJavaScriptEnabled")
    private void initDatas() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        String uri = MainApplication.DefaultValue.ABOUT_URL +getVersion();
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(),"");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                customDialog.dismiss();
            }
        });
        mWebView.loadUrl(uri);


    }

    private String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void findById() {
		mBack = (ImageView) findViewById(R.id.back);
		mBack.setOnClickListener(this);
        mWebView = (WebView) findViewById(R.id.webView);
        _layoutAbout = (RelativeLayout) findViewById(R.id.layout_about);
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
        _layoutAbout.removeView(mWebView);
        mWebView.removeAllViews();
        mWebView.destroy();
    }

    @Override
    protected Context getThisContext() {
        return AboutActivity.this;
    }

    @Override
    protected String toPageName() {

        return "关于我们";
    }
}
