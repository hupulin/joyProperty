package com.joy.property.neighborhood;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.joy.property.R;
import com.joy.property.neighborhood.fragment.InteractFragment;
import com.joy.property.base.BaseActivity;



import java.util.ArrayList;
import java.util.List;


/**
 * Created by xz on 2016/10/31.
 */
public class InteractActivity extends BaseActivity implements View.OnClickListener {
    private View moveLine;
    private View moveLayout;
    private List<InteractFragment> fragmentList=new ArrayList<>();
    private RelativeLayout neighborShare;
    private TextView topic;
    private TextView investigation;
    private TextView activityTest;
    private ViewPager viewPager;
    private LinearLayout tab;
    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interact);
        initData();
        initView();
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
        if (getIntent().getIntExtra("allType",0)==1)
            titleText.setText("活动");
        else if (getIntent().getIntExtra("allType",0)==2)
            titleText.setText("话题");
        else if (getIntent().getIntExtra("allType",0)==3)
            titleText.setText("调查");
        for (int i = 0; i < (getIntent().getIntExtra("allType",0)==0?3:1); i++) {
         InteractFragment fragment = new InteractFragment(getIntent().getIntExtra("allType",0)==0?(i+1):getIntent().getIntExtra("allType",0));

            fragmentList.add(fragment);
        }


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
}