package com.joy.property.repairs;

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

import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.adapter.CommonFragmentPagerAdapter;
import com.joy.property.myservice.ScreenActivity;
import com.joy.property.repairs.fragment.RepairsCompleteFragment;
import com.joy.property.repairs.fragment.RepairsProcessingFragment;
import com.joy.property.repairs.fragment.RepairsUnclaimedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共维修
 * Created by Admin on 2015-02-11
 */
public class RepairsTableActivity extends BaseActivity
        implements OnClickListener, OnPageChangeListener {


    private TextView mUnclaimed;
    private TextView mProcessing;
    private TextView mComplete;
    private ImageView mCursor;
    private ViewPager mViewPager;
    private TextView mTitle ;
    private int one;
    private int pre;
    private  List<ApartmentInfoTo>apartmentInfoTos=new ArrayList<ApartmentInfoTo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        findById();
        initData();
    }


    private void findById() {
        mTitle = (TextView) findViewById(R.id.title2);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
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
        mTitle.setText("公共维修("+flowTo.getApartmentName()+")");
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
        List<Fragment>      fragments  =  new ArrayList<>();
        RepairsUnclaimedFragment ruf  =  new  RepairsUnclaimedFragment();
        RepairsProcessingFragment rpf  =  new RepairsProcessingFragment();
        RepairsCompleteFragment rcf  = new  RepairsCompleteFragment();
        fragments.add(ruf);
        fragments.add(rpf);
        fragments.add(rcf);
        FragmentManager fm = getSupportFragmentManager();
        CommonFragmentPagerAdapter mAdapter = new CommonFragmentPagerAdapter(fm, fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(0);
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
                intent.putExtra("CategorySid", "C733AA3D-32FA-4F5B-B299-061044661DC0");
                startActivity(intent);
                goToAnimation(1);

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
        return RepairsTableActivity.this;
    }
    @Override
    protected String toPageName() {
        return " 报修管理列表";
    }
}
