package com.joy.property.person;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.library.utils.ConfigUtil;
import com.joy.property.R;
import com.joy.property.common.adapter.CommonFragmentPagerAdapter;
import com.joy.property.person.fragment.MyNeighborJoinFragment;
import com.joy.property.person.fragment.MyPostFragment;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.base.BaseActivity;

import com.joyhome.nacity.app.photo.util.Bimp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2014-11-04.
 * 呼叫物业
 */
public class MyNeighborActivity extends BaseActivity
        implements OnClickListener, OnPageChangeListener {

    private TextView tvMySubmission;
    private TextView tvCallDetail;
    private ImageView ivCursor;
    private ViewPager mViewPager;
    private int one;
    private int pre;
    private TextView mTitle;
    public static Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a_s_add);
        setContentView(R.layout.activity_call_property);
        findById();
        getIntentData();
        initDatas();


    }

    private void getIntentData() {
   mTitle.setText("我的邻居圈");

    }

    private void findById() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        tvMySubmission = (TextView) findViewById(R.id.tv_my_submission);
        tvMySubmission.setOnClickListener(this);
        tvCallDetail = (TextView) findViewById(R.id.tv_call_detail);
        tvCallDetail.setOnClickListener(this);
        ivCursor = (ImageView) findViewById(R.id.iv_cursor);
        mViewPager = (ViewPager) findViewById(R.id.property_viewpager);
        mViewPager.addOnPageChangeListener(this);
        mTitle = (TextView) findViewById(R.id.tv_title);


    }

    private void initDatas() {
        tvMySubmission.setText("我的帖子");
        tvCallDetail.setText("我参与的");
        // 设置图片的默认位置
        Matrix matrix = new Matrix();
        // 获得图片的宽度
        Bitmap lineBm = BitmapFactory.decodeResource(getResources(),
                R.drawable.navigation_ic);
        int imgWidth = lineBm.getWidth();
        // 获得屏幕的宽度
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int screenWidth = outMetrics.widthPixels;
        // 可用进行设置图片位置
        int dx = (screenWidth / 2 - imgWidth) / 2;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivCursor.getLayoutParams();
        params.leftMargin = dx;
        matrix.postTranslate(dx, 0);
        ivCursor.setImageMatrix(matrix);
        one = screenWidth / 2;
        List<Fragment> fragments = new ArrayList<>();
        MyPostFragment msf = new MyPostFragment();
        MyNeighborJoinFragment cdf = new MyNeighborJoinFragment();
        fragments.add(msf);
        fragments.add(cdf);
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        if ("0".equals(getIntent().getStringExtra("value"))) {
            mViewPager.setCurrentItem(1);
        } else {
            mViewPager.setCurrentItem(0);
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:

                onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.tv_my_submission:
                tvMySubmission.setTextColor(0xff4fb2d6);
                tvCallDetail.setTextColor(0xffb5b2ac);
                mViewPager.setCurrentItem(0);
                break;

            case R.id.tv_call_detail:
                tvMySubmission.setTextColor(0xffb5b2ac);
                tvCallDetail.setTextColor(0xff4fb2d6);
                mViewPager.setCurrentItem(1);
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            tvMySubmission.setTextColor(0xff4fb2d6);
            tvCallDetail.setTextColor(0xffb5b2ac);
        } else if (position == 1) {
            tvMySubmission.setTextColor(0xffb5b2ac);
            tvCallDetail.setTextColor(0xff4fb2d6);
        }
        TranslateAnimation anim = new TranslateAnimation(one * pre, one * position, 0, 0);
        pre = position;
        anim.setDuration(300);
        anim.setFillAfter(true);
        ivCursor.startAnimation(anim);

    }

    @Override
    protected void onDestroy() {
        mViewPager.clearOnPageChangeListeners();
        super.onDestroy();

    }

    @Override
    protected String toPageName() {

        return "呼叫物业";
    }

    @Override
    public void onBackPressed() {
        Bimp.tempSelectBitmap.clear();
        ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_SERVICE_SID, "");
        ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_SERVICE_TO, "");
        ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_SERVICE_REQUEST_PARAM, "");
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
