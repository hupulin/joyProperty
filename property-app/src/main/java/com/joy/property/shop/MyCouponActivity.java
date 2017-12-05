package com.joy.property.shop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.module.newshop.ConfirmOrderTo;
import com.jinyi.ihome.module.newshop.CouponTo;
import com.joy.library.utils.DateUtil;


import com.joy.property.neighborhood.RefreshEvent;
import com.joy.property.neighborhood.fragment.InteractFragment;
import com.joy.property.shop.adapter.CouponHolder;
import com.joy.property.shop.adapter.MyCouponHolder;
import com.joy.property.shop.fragment.MyCouponOutFragment;
import com.joy.property.base.BaseActivity;
import com.joy.property.shop.fragment.MyCouponUnUseFragment;
import com.joy.property.shop.fragment.MyCouponUseFragment;
import com.joy.property.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by xz on 2016/7/24.
 **/
public class MyCouponActivity extends BaseActivity implements View.OnClickListener {

    private View moveLine;
    private View moveLayout;
    private List<Fragment> fragmentList=new ArrayList<>();
    private RelativeLayout neighborShare;
    private TextView topic;
    private TextView investigation;
    private TextView activityTest;
    private ViewPager viewPager;
    private LinearLayout tab;
    private TextView titleText;
    private boolean isFirstRegist;
    private MyCouponUnUseFragment myCouponUnUseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        initData();
        initView();
        if (!isFirstRegist)
            EventBus.getDefault().register(this);
        isFirstRegist=true;
    }
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pageChangeListener);
        moveLine = findViewById(R.id.moveLine);
        moveLayout = findViewById(R.id.moveLayout);
        findViewById(R.id.activityLayout).setOnClickListener(this);
        findViewById(R.id.investigationLayout).setOnClickListener(this);
        findViewById(R.id.topicLayout).setOnClickListener(this);
        topic = (TextView) findViewById(R.id.topic);

        investigation = (TextView) findViewById(R.id.investigation);
        activityTest = (TextView) findViewById(R.id.activityText);

        tab = (LinearLayout) findViewById(R.id.tab);
        findViewById(R.id.iv_back).setOnClickListener(this);
        if (getIntent().getIntExtra("allType",0)!=0) {
            tab.setVisibility(View.GONE);
            findViewById(R.id.hideLine).setVisibility(View.GONE);
            moveLine.setVisibility(View.GONE);


        }
    }

    private void initData() {
        titleText = (TextView) findViewById(R.id.titleText);
        myCouponUnUseFragment = new MyCouponUnUseFragment();
        fragmentList.add(myCouponUnUseFragment);
        fragmentList.add(new MyCouponUseFragment());
        fragmentList.add(new MyCouponOutFragment());
        myCouponUnUseFragment.setGetCoupon(new MyCouponUnUseFragment.CouponListener() {
            @Override
            public void getCoupon(CouponTo coupon) {
               Intent intent=new Intent();
                intent.putExtra("coupon",coupon);
                setResult(RESULT_OK,intent);
                finish();
                goToAnimation(2);
            }
        });

    }

    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {

            return fragmentList.size();
        }
    };
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (positionOffsetPixels / 3 != 0)
                moveLine.setX((float) ((float) (getScreenWidth() * 0.075) + position * (getScreenWidth() * 0.283) + positionOffsetPixels * 0.283));

        }

        @Override
        public void onPageSelected(int position) {
            if (position==0){
                activityTest.setTextColor(Color.parseColor("#4fb2d6"));
                investigation.setTextColor(Color.parseColor("#999999"));
                topic.setTextColor(Color.parseColor("#999999"));
            }else if (position==1){
                activityTest.setTextColor(Color.parseColor("#999999"));
                investigation.setTextColor(Color.parseColor("#999999"));
                topic.setTextColor(Color.parseColor("#4fb2d6"));
            }
            else {
                activityTest.setTextColor(Color.parseColor("#999999"));
                investigation.setTextColor(Color.parseColor("#4fb2d6"));
                topic.setTextColor(Color.parseColor("#999999"));
            }
            //moveLine.setX((float) ((float) (getScreenWidth() * 0.075) + position * (getScreenWidth() * 0.283)));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };




    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activityLayout:
                viewPager.setCurrentItem(0);
                break;
            case R.id.investigationLayout:
                viewPager.setCurrentItem(2);
                break;
            case R.id.topicLayout:
                viewPager.setCurrentItem(1);
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(RefreshEvent event) {
//        Toast.makeText(getThisContext(), "点击了", Toast.LENGTH_LONG).show();
//        if ("couponBack".equals(event.getMsg()))
//        {
//
//            Intent intent = new Intent();
//           // intent.putExtra("MyCoupon", coupon);
//            setResult(10, intent);
//
//            finish();
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
