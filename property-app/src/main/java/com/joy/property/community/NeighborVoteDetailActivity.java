package com.joy.property.community;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Util.ActivityBarColorUtil;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborPostParam;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;

import com.joy.property.R;
import com.joy.property.neighborhood.InvestigateActivity;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.base.BaseActivity;

import com.joyhome.nacity.app.util.CustomDialogFragment;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-07-09
 */
public class NeighborVoteDetailActivity extends BaseActivity {

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
        mTitle.setText("邻居圈调查");
        findViewById(R.id.iv_back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private void getIntentData() {

        mVoteSid = getIntent().getStringExtra("voteSid");
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initData() {
        mTitle.setText("邻居圈调查");
        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBlockNetworkImage(true);
        String webURl = (SpUtil.getBoolean(getThisContext(), "ParkNeighbor")?MainApp.DefaultValue.VOTE_URL_NEIGHBOR_PARK: MainApp.DefaultValue.VOTE_URL_NEIGHBOR) + mUserHelper.getSid() + "/" + mVoteSid;
        System.out.println(webURl+"rul");
        final CustomDialogFragment customDialog= new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(),"");
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                customDialog.dismissAllowingStateLoss();
              if (url.contains("vote_submit")){
                  joinCampaign();
              }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                if (url.contains("vote_submit")){
                    joinCampaign();
                }
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
    private void postSubmit() {
        NeighborPostParam param = (NeighborPostParam) getIntent().getSerializableExtra("postParam");

        NeighborApi api = ApiClient.create(NeighborApi.class);

        if (TextUtils.isEmpty(mHelper.getSid()))
            param.setApartmentSid("30e1baa2-877f-48c8-92be-67d026752039");
        api.addNeighborPost(param, new HttpCallback<MessageTo<NeighborPostTo>>(this) {
            @Override
            public void success(MessageTo<NeighborPostTo> msg, Response response) {

                if (msg.getSuccess() == 0) {


                } else {

                }
            }

            @Override
            public void failure(RetrofitError error) {

                super.failure(error);
            }
        });


    }
    public void joinCampaign(){
        NeighborApi api= ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");
        NeighborhoodLogParam param=new NeighborhoodLogParam();
        param.setOwnerSid(mUserHelper.getSid());
        param.setApartmentSid(mHelper.getSid());
        if (TextUtils.isEmpty(mHelper.getSid()))
            param.setApartmentSid("30e1baa2-877f-48c8-92be-67d026752039");
        param.setRefId(getIntent().getStringExtra("interactionSid"));
        param.setType(3);
        param.setStatus(2);
        api.JoinCampaign(param, new HttpCallback<MessageTo<NeighborhoodLogTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborhoodLogTo> msg, Response response) {
                customDialog.dismiss();
                if (msg.getSuccess() == 0) {
postSubmit();
                    new Thread(() -> {
                        SystemClock.sleep(1500);
                        Intent intent=new Intent(getThisContext(),InvestigateActivity.class);
                        intent.putExtra("interactionSid",getIntent().getStringExtra("interactionSid"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        finish();
                    }).start();
                } else {
                    ToastShowLong(getThisContext(), msg.getMessage());
//                    new Thread(() -> {
//                        SystemClock.sleep(1500);
//                        Intent intent=new Intent(getThisContext(),InvestigateActivity.class);
//                        intent.putExtra("interactionSid",getIntent().getStringExtra("interactionSid"));
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//
//                        finish();
//                    }).start();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                customDialog.dismiss();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getThisContext(), InvestigateActivity.class);
        intent.putExtra("interactionSid",getIntent().getStringExtra("interactionSid"));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
