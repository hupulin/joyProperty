package com.joy.property.vehicle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.joy.property.R;

import com.jinyi.ihome.module.car.CarViolationTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.PictureShowActivity;

import java.util.ArrayList;
import java.util.List;


public class CarViolationDetailActivity extends BaseActivity
        implements OnClickListener, OnPageChangeListener {


    private TextView mCarPlace;
    private TextView mApartmentName;
    private TextView mCarViolationTime;
    private TextView mCarViolationDesc;
    private CarViolationTo carViolationTo;
    private ViewPager mViewPager;
    private List<ImageView> list = new ArrayList<>();
    private TextView mTotal;
    private TextView mTipNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_violation_detail);
        findById();
        getIntentData();
        initializeData();
    }


    // 初始化控件
    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        mCarPlace = (TextView) findViewById(R.id.place);
        mApartmentName = (TextView) findViewById(R.id.apartment);
        mCarViolationTime = (TextView) findViewById(R.id.time);
        mCarViolationDesc = (TextView) findViewById(R.id.content);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(this);
        mTotal = (TextView) findViewById(R.id.total);
        mTipNo = (TextView) findViewById(R.id.tip_no);

    }

    //获取传递数据
    private void getIntentData() {
        carViolationTo = (CarViolationTo) getIntent().getSerializableExtra("CarViolationTo");


    }

    //初始数据
    private void initializeData() {

        if (!TextUtils.isEmpty(carViolationTo.getViolationsImages())) {
            String[] path = carViolationTo.getViolationsImages().split(";");
            mTotal.setText("/" + path.length);
            mTipNo.setText("1");
            for (String s : path) {
                ImageView imageView = new ImageView(this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                displayImage(imageView, s, R.drawable.car_01);
                imageView.setTag(carViolationTo.getViolationsImages());
                list.add(imageView);
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getThisContext(), PictureShowActivity.class);
                        intent.putExtra("path", (String) v.getTag());
                        startActivity(intent);
                    }
                });
            }
            mViewPager.setAdapter(new CarViolationAdapter(list));
            mViewPager.setCurrentItem(0);
        } else {
            mTotal.setVisibility(View.GONE);
            mTipNo.setVisibility(View.GONE);
            mViewPager.setBackgroundResource(R.drawable.car_01);
        }


        if (!TextUtils.isEmpty(carViolationTo.getPackingLocation())) {
            mCarPlace.setText(carViolationTo.getPackingLocation());
        } else {
            mCarPlace.setText("未记录违停地点");
        }

        if (carViolationTo.getCreatedOn() != null) {
            mCarViolationTime.setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString,
                    carViolationTo.getCreatedOn()));
        }

        if (!TextUtils.isEmpty(carViolationTo.getViolationsRemark())) {
            mCarViolationDesc.setText(carViolationTo.getViolationsRemark());
        } else {
            mCarViolationDesc.setText("暂无描述");
        }


        if (!TextUtils.isEmpty(carViolationTo.getApartmentName())) {
            mApartmentName.setText(carViolationTo.getApartmentName());

        } else {
            mApartmentName.setText("未记录违停小区");
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        mTipNo.setText(String.valueOf(position + 1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class CarViolationAdapter extends PagerAdapter {
        private List<ImageView> mList;

        private CarViolationAdapter(List<ImageView> list) {
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position % mList.size()));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position % mList.size()), 0);
            return mList.get(position % mList.size());
        }
    }

    @Override
    protected Context getThisContext() {
        return CarViolationDetailActivity.this;
    }

    @Override
    protected String toPageName() {

        return "车辆违停详情页";
    }
}
