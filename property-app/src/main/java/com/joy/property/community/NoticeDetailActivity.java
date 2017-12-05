package com.joy.property.community;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.notice.HomeNoticeCommentTo;
import com.jinyi.ihome.module.notice.NoticeCommentParam;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NoticeApi;
import com.joy.property.R;
import com.joy.property.MainApp;
import com.joy.property.base.BaseActivity;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2014-11-13.
 * 通知详情页
 */

public class NoticeDetailActivity extends BaseActivity
        implements OnClickListener{

    private WebView webView;
    private EditText mContent;
    private String mNoticeSid = "";
    private Button mSend;
    private RelativeLayout mLayoutSend;
    private static String EXTRA_VIDEO_U = "0";
    private RelativeLayout relativeLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(getThisContext());
        }
        setContentView(R.layout.activity_notice_detail);
        findById();

        initIntentDatas();
        //init();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initDatas();
            }
        });
    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.webView);
//        Button   mComment = (Button) findViewById(R.id.comment);
//        mComment.setOnClickListener(this);
        mContent = (EditText) findViewById(R.id.content);
//        mSend = (Button) findViewById(R.id.btn_send);
//        mSend.setOnClickListener(this);
//        mLayoutSend = (RelativeLayout) findViewById(R.id.ll_send);
//        mLayoutSend.setVisibility(View.GONE);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
    }


    private void initIntentDatas() {
        mNoticeSid = getIntent().getStringExtra("sid");

    }

    /**
     * EditText 内容进行监听
     */
//    private void init() {
//        mContent.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() > 0) {
//                    mSend.setBackgroundResource(R.drawable.selector_send_button);
//                } else {
//                    mSend.setBackgroundResource(R.drawable.null_send_ic);
//                }
//            }

//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//
//
//        });
//
//    }




    @JavascriptInterface
    @SuppressLint({"setJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface"})
    public void initDatas() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setBlockNetworkImage(true);
        String  url = MainApp.DefaultValue.NOTICE_URL + mNoticeSid;
        System.out.println(url+"url");
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");
        webView.addJavascriptInterface(new Object() {
            public void videoPlay(String url) {
                Log.d("detailWebActivity", "----------------> videoPlay, url"
                        + url);
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(getThisContext(), "播放失败：播放地不正确", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getThisContext(), VideoPlayActivity.class);
                intent.putExtra(EXTRA_VIDEO_U, url);
                startActivity(intent);
            }
        }, "android");



        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                customDialog.dismiss();
                webView.getSettings().setBlockNetworkImage(false);
                webView.loadUrl("javascript:var videos = document.getElementsByTagName('video');for (i = 0; i < videos.length; i++) {var video = videos[i]; var src = video.src; video.src=''; video.onclick = function() {android.videoPlay(src);}}");

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
//            case R.id.comment:
//                if (!mUserHelper.isLogin()) {
//                    //startActivity(new Intent(getThisContext(), LoginUserActivity.class));
//                } else {
//                    mLayoutSend.setVisibility(View.VISIBLE);
//                }
//                break;
//            case R.id.btn_send:
//                submitComment();
//                break;
            default:
                break;
        }
    }


    /**
     * 提交评论数据
     */
    private void submitComment() {
        if (TextUtils.isEmpty(mContent.getText().toString())) {
            Toast.makeText(getThisContext(), "您还没写评论内容哦，请输入", Toast.LENGTH_LONG).show();
            return;
        }
        NoticeCommentParam mParam = new NoticeCommentParam();
        mParam.setCommentUserSid(mUserHelper.getSid());
        mParam.setContent(mContent.getText().toString());
        mParam.setNoticeSid(mNoticeSid);
        NoticeApi api = ApiClient.create(NoticeApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
        api.addComment(mParam, new HttpCallback<MessageTo<HomeNoticeCommentTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<HomeNoticeCommentTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    mLayoutSend.setVisibility(View.GONE);
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(mContent.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    mContent.setText("");
                    webView.reload();


                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.getInstance().startSync();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.getInstance().sync();
        }else {
            CookieManager.getInstance().flush();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.getInstance().sync();
        }
        webView.stopLoading();
    }

    @Override
    protected Context getThisContext() {
        return NoticeDetailActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        relativeLayout.removeView(webView);
        webView.removeAllViews();
        webView.destroy();
    }

    @Override
    protected String toPageName() {
        return "通知详情";
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        }
        return super.onKeyDown(keyCode, event);
    }
}
