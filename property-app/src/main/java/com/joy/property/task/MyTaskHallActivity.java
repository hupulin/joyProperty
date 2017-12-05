package com.joy.property.task;


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
import com.joy.property.task.fragment.MyTaskFragment;
import com.joy.property.task.fragment.TaskHallFragment;

import java.util.ArrayList;
import java.util.List;


public class MyTaskHallActivity extends BaseActivity implements
        OnPageChangeListener, OnClickListener {

    private TextView mTaskLeft;
    private TextView mTaskRight;
    private ViewPager mViewPager;
    private int one, pre;
    private ImageView mCursor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_hall);
        findById();
        initDatas();

    }




    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.isQuery).setOnClickListener(this);
        mTaskLeft = (TextView) findViewById(R.id.task_left);
        mTaskLeft.setOnClickListener(this);
        mTaskRight = (TextView) findViewById(R.id.task_right);
        mTaskRight.setOnClickListener(this);
        mCursor = (ImageView) findViewById(R.id.cursor);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.addOnPageChangeListener(this);
    }

    private void initDatas() {
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
        int dx = (screenWidth / 2 - imgWidth) / 2;
        matrix.postTranslate(dx, 0);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mCursor
                .getLayoutParams();
        params.leftMargin = dx;
        mCursor.setImageMatrix(matrix);
        one = screenWidth / 2;
        List<Fragment> fragments = new ArrayList<>();
        TaskHallFragment thf = TaskHallFragment.getInstance();
        MyTaskFragment mtf = MyTaskFragment.getInstance();
        fragments.add(mtf);
        fragments.add(thf);

        FragmentManager fm = getSupportFragmentManager();
        CommonFragmentPagerAdapter mAdapter = new CommonFragmentPagerAdapter(fm, fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(0);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            mTaskLeft.setTextColor(0xff1c388c);
            mTaskRight.setTextColor(0xffbebebe);

        } else if (position == 1) {
            mTaskLeft.setTextColor(0xffbebebe);
            mTaskRight.setTextColor(0xff1c388c);

        }

        TranslateAnimation anim = new TranslateAnimation(one * pre, one * position, 0, 0);
        pre = position;
        anim.setDuration(300);
        anim.setFillAfter(true);
        mCursor.startAnimation(anim);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.task_left:
                mTaskLeft.setTextColor(0xff1c388c);
                mTaskRight.setTextColor(0xffbebebe);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.task_right:
                mTaskLeft.setTextColor(0xffbebebe);
                mTaskRight.setTextColor(0xff1c388c);
                mViewPager.setCurrentItem(1);
                break;
            default:
                break;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected String toPageName() {

        return "任务大厅";
    }


}
