package com.joy.property.complaint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.adapter.CommonFragmentPagerAdapter;
import com.joy.property.complaint.fragment.CompleteFragment;
import com.joy.property.complaint.fragment.ProcessingFragment;
import com.joy.property.complaint.fragment.UnclaimedFragment;
import com.joy.property.myservice.ScreenActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2015-02-09
 */
public class ComplaintTablesActivity extends BaseActivity
        implements OnClickListener, OnPageChangeListener {


    private TextView mUnclaimed;
    private TextView mProcessing;
    private TextView mTitle;
    private TextView mComplete;
    private ImageView mCursor;
    private ViewPager mViewPager;
    private int one;
    private int pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        findById();
        initData();
    }
    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.title2);
        mUnclaimed = (TextView) findViewById(R.id.unclaimed);
        mUnclaimed.setOnClickListener(this);
        mProcessing = (TextView) findViewById(R.id.processing);
        mProcessing.setOnClickListener(this);
        mComplete = (TextView) findViewById(R.id.complete);
        mComplete.setOnClickListener(this);
        mCursor = (ImageView) findViewById(R.id.cursor);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.addOnPageChangeListener(this);
        findViewById(R.id.screen).setOnClickListener(this);

    }

    private void initData() {
        Intent intent = getIntent();
        // 获取该Intent所携带的数据
        Bundle bundle = intent.getExtras();
        // 从bundle数据包中取出数据
        ServiceReportFlowTo flowTo = (ServiceReportFlowTo) bundle.getSerializable("flowTo");
        mTitle.setText("投诉管理("+flowTo.getApartmentName()+")");
        // 设置图片的默认位置
        Matrix matrix = new Matrix();
        // 获得图片的宽度
        Bitmap lineBm = BitmapFactory.decodeResource(getResources(),
                R.drawable.course_ic);
        int imgWidth = lineBm.getWidth();
        // 获得屏幕的宽度
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int screenWidth = outMetrics.widthPixels;
        // 可用进行设置图片位置
        int dx = (screenWidth / 3 - imgWidth) / 2;
        matrix.postTranslate(dx, 0);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mCursor
                .getLayoutParams();
        params.leftMargin = dx;
        mCursor.setImageMatrix(matrix);
        one = screenWidth / 3;
        List<Fragment> fragments = new ArrayList<>();
        //处理中
        UnclaimedFragment uf = new  UnclaimedFragment();
        //带评价
        ProcessingFragment pf = new ProcessingFragment();
        //已结束
        CompleteFragment cf = new CompleteFragment();
        fragments.add(uf);
        fragments.add(pf);
        fragments.add(cf);
        FragmentManager  fm = getSupportFragmentManager();
        CommonFragmentPagerAdapter mAdapter = new CommonFragmentPagerAdapter(fm, fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        if ("0".equals(getIntent().getStringExtra("value"))) {
            mViewPager.setCurrentItem(1);
        } else {
            mViewPager.setCurrentItem(0);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.unclaimed:
                mUnclaimed.setTextColor(0xff4fb2d6);
                mProcessing.setTextColor(0xffbebebe);
                mComplete.setTextColor(0xffbebebe);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.processing:
                mUnclaimed.setTextColor(0xffbebebe);
                mProcessing.setTextColor(0xff4fb2d6);
                mComplete.setTextColor(0xffbebebe);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.complete:
                mUnclaimed.setTextColor(0xffbebebe);
                mProcessing.setTextColor(0xffbebebe);
                mComplete.setTextColor(0xff4fb2d6);
                mViewPager.setCurrentItem(2);
                break;

            case R.id.screen:
                Intent intent=new Intent(getThisContext(), ScreenActivity.class);
                intent.putExtra("IsMyService",false);
                intent.putExtra("CurrentPosition",10);

                intent.putExtra("ApartmentSid", getIntent().getStringExtra("mode"));
                intent.putExtra("CategorySid", "7D2B996C-12EC-4CD4-8499-B453E96AF11F");
                startActivity(intent);
                goToAnimation(1);

                break;

            default:
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            mUnclaimed.setTextColor(0xff4fb2d6);
            mProcessing.setTextColor(0xffbebebe);
            mComplete.setTextColor(0xffbebebe);
        } else if (position == 1) {
            mUnclaimed.setTextColor(0xffbebebe);
            mProcessing.setTextColor(0xff4fb2d6);
            mComplete.setTextColor(0xffbebebe);
        } else if (position == 2) {
            mUnclaimed.setTextColor(0xffbebebe);
            mProcessing.setTextColor(0xffbebebe);
            mComplete.setTextColor(0xff4fb2d6);
        }
        TranslateAnimation anim = new TranslateAnimation(one * pre, one * position, 0, 0);
        pre = position;
        anim.setDuration(300);
        anim.setFillAfter(true);
        mCursor.startAnimation(anim);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected Context getThisContext() {
        return ComplaintTablesActivity.this;
    }


    @Override
    protected String toPageName() {
        return "投诉管理列表";
    }

}
