package com.joy.property.vehicle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.common.application.KeyValue;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.adapter.CommonFragmentPagerAdapter;

import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.SpUtil;
import com.joy.property.vehicle.fragment.MySubmissionFragment;
import com.joy.property.vehicle.fragment.SubmissionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usb on 2017/2/26.
 */


    public class VehicleInspectionActivity extends BaseActivity
            implements View.OnClickListener, ViewPager.OnPageChangeListener {


        private TextView mInspectionLeft;
        private TextView mInspectionRight;
        private ImageView mCursor;
        private ViewPager mViewPager;
        private int one;
        private int pre;
        private ImageView changePark;
        private boolean firstChange;
        private TextView mTitle;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_task_ha);
            findById();
            initData(0);
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
            mTitle = (TextView) findViewById(R.id.tv_title);
            mTitle.setText("车辆巡检");
           changePark = (ImageView) findViewById(R.id.changePark);
           changePark.setOnClickListener(this);
        }

        private void initData(Integer position) {
            if (SpUtil.getString(getThisContext(), "limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A003"))
            {
                changePark.setVisibility(View.VISIBLE);
                mTitle.setText("违停管理(住宅)");
                if (ConfigUtil.getString(sp, KeyValue.KEY_USER_INFO, "").equals(SpUtil.getString(getThisContext(),"HomeInfo"))) {
                    changePark.setBackgroundResource(R.drawable.selector_park);
                    ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
                    mTitle.setText("违停管理(住宅)");
                    firstChange=false;
                }
                else {
                    changePark.setBackgroundResource(R.drawable.selector_home);
                    ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                    mTitle.setText("违停管理(园区)");
                    firstChange=true;
                }
            }
            mInspectionLeft.setText("我要提报");
            mInspectionRight.setText("提报记录");

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
            FragmentManager fm = getSupportFragmentManager();
            List<Fragment> fragments = new ArrayList<>();
            SubmissionFragment sf =  new SubmissionFragment();
            MySubmissionFragment msf = new  MySubmissionFragment();
            fragments.add(sf);
            fragments.add(msf);
            CommonFragmentPagerAdapter mAdapter = new CommonFragmentPagerAdapter(fm, fragments);
            mViewPager.setAdapter(mAdapter);
            mViewPager.setOffscreenPageLimit(2);
            mViewPager.setCurrentItem(position);
//            if ("0".equals(getIntent().getStringExtra("value"))) {
//                mViewPager.setCurrentItem(1);
//            }else {
//                mViewPager.setCurrentItem(0);
//            }
            if(getIntent().getStringExtra("value")!=null) {
                if ("0".equals(getIntent().getStringExtra("value"))) {
                    mViewPager.setCurrentItem(1);
                } else {
                    mViewPager.setCurrentItem(0);
                }
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
                case R.id.changePark:
                    changePark();
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
            return "巡检";
        }
        private void changePark() {
            int position = mViewPager.getCurrentItem();

            if (!firstChange){
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                changePark.setBackgroundResource(R.drawable.selector_home);
                firstChange=true;
                initData(position);
                mTitle.setText("违停管理(园区)");
            }else {
                ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
                changePark.setBackgroundResource(R.drawable.selector_park);
                firstChange=false;
                initData(position);
                mTitle.setText("违停管理(住宅)");
            }
        }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (ConfigUtil.getString(sp, KeyValue.KEY_USER_INFO, "").equals(SpUtil.getString(getThisContext(),"HomeInfo"))) {
            changePark.setBackgroundResource(R.drawable.selector_park);
            ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
            mTitle.setText("巡检(住宅)");
            firstChange=false;
        } else {
            changePark.setBackgroundResource(R.drawable.selector_home);
            ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
            mTitle.setText("巡检(园区)");
            firstChange=true;
        }
    }
}

