package com.joy.property.inspection;


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

import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.adapter.CommonFragmentPagerAdapter;
import com.joy.property.inspection.fragment.MySubmissionFragment;
import com.joy.property.inspection.fragment.SubmissionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2015-02-02
 */
public class PropertyInspectionActivity extends BaseActivity
        implements OnClickListener, OnPageChangeListener {


    private TextView mInspectionLeft;
    private TextView mInspectionRight;
    private ImageView mCursor;
    private ViewPager mViewPager;
    private int one;
    private int pre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_ha);
        findById();
        initData();
    }
    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        mInspectionLeft = (TextView) findViewById(R.id.task_left);
        mInspectionLeft.setOnClickListener(this);
        mInspectionRight = (TextView) findViewById(R.id.task_right);
        mInspectionRight.setOnClickListener(this);
        mCursor = (ImageView) findViewById(R.id.cursor);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.addOnPageChangeListener(this);
        TextView mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText("小区巡检");
    }

    private void initData() {
        mInspectionLeft.setText("我要提报");
        mInspectionRight.setText("巡检记录");

        /**
         * set ImageView default position
         */
        Matrix matrix = new Matrix();
        /**
         *  get the width of Cursor
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.course_ic);
        int imageWidth = bitmap.getWidth();
        /**
         * get the width of Mobile phone screen
         */
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int screenWidth = outMetrics.widthPixels;

        /**
         * set the position of Cursor;
         */

        int dx = (screenWidth / 2 - imageWidth) / 2;
        matrix.setTranslate(dx, 0);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mCursor
                .getLayoutParams();
        params.leftMargin = dx;
        mCursor.setImageMatrix(matrix);
        one = screenWidth / 2;
        FragmentManager        fm = getSupportFragmentManager();
        List<Fragment>  fragments = new ArrayList<>();
        SubmissionFragment sf =  new SubmissionFragment(1);
        MySubmissionFragment msf = new  MySubmissionFragment();
        fragments.add(sf);
        fragments.add(msf);
        CommonFragmentPagerAdapter mAdapter = new CommonFragmentPagerAdapter(fm, fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2);
        if ("0".equals(getIntent().getStringExtra("value"))) {
            mViewPager.setCurrentItem(1);
        }else {
            mViewPager.setCurrentItem(0);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
               onBackPressed();
                break;
            case R.id.task_left:
                mInspectionLeft.setTextColor(0xff4fb2d6);
                mInspectionRight.setTextColor(0xffbebebe);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.task_right:
                mInspectionLeft.setTextColor(0xffbebebe);
                mInspectionRight.setTextColor(0xff4fb2d6);
                mViewPager.setCurrentItem(1);
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
            mInspectionLeft.setTextColor(0xff4fb2d6);
            mInspectionRight.setTextColor(0xffbebebe);
        } else if (position == 1) {
            mInspectionLeft.setTextColor(0xffbebebe);
            mInspectionRight.setTextColor(0xff4fb2d6);
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
    protected String toPageName() {
         super.toPageName();
        return "小区巡检";
    }
}
