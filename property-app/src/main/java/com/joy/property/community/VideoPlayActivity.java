package com.joy.property.community;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.MediaController;

import com.joy.library.utils.FullScreenVideoView;
import com.joy.property.R;
import com.joyhome.nacity.app.base.BaseActivity;

/**
 * Created by Admin on 2015-02-10.
 * 视频播放
 */
public class VideoPlayActivity extends BaseActivity {

    private FullScreenVideoView mVideoView;
    private String mPath = "";
    public static final String EXTRA_VIDEO_U = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);

        findById();
        initIntentData();
        initData();
    }


    private void findById() {

        mVideoView = (FullScreenVideoView) findViewById(R.id.video);


    }


    private void initIntentData() {
        mPath = getIntent().getStringExtra(EXTRA_VIDEO_U);
    }

    private void initData() {
        MediaController mc = new MediaController(VideoPlayActivity.this);

        mc.setEnabled(true);
        if (!TextUtils.isEmpty(mPath)) {
            Uri uri = Uri.parse(mPath);
            mVideoView.setVideoURI(uri);

            mVideoView.setMediaController(mc);
            mVideoView.requestFocus();
            mVideoView.start();

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.destroyDrawingCache();
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
