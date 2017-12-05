package com.joy.property.common;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;

import com.joy.property.base.BaseActivity;
import com.joy.property.utils.ZoomViewPager;
import com.joy.library.utils.ZoomImageView;

import java.util.ArrayList;
import java.util.List;


public class PictureShowActivity extends BaseActivity
        implements OnPageChangeListener, OnClickListener {
    private ZoomViewPager mViewPager;
    private String path = "";
    private TextView mPicNo;
    private TextView mTotalPic;
    private List<ImageView> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_show);
        findById();
        initIntentDatas();
        initDatas();
    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        mViewPager = (ZoomViewPager) findViewById(R.id.viewpager);
        mViewPager.setOnPageChangeListener(this);
        mPicNo = (TextView) findViewById(R.id.pic_no);
        mTotalPic = (TextView) findViewById(R.id.total_pic);
    }


    private void initIntentDatas() {
        path = getIntent().getStringExtra("path");
    }

    private void initDatas() {
        String[] mImagePath = path.split(";");
        mPicNo.setText("1");
        mTotalPic.setText("/" + String.valueOf(mImagePath.length));
        for (String str : mImagePath) {
            ZoomImageView   image = new ZoomImageView(this);
            displayImage(image, str);
            list.add(image);
        }

        mViewPager.setAdapter(new pictureShowAdapter(list));
        mViewPager.setCurrentItem(0);

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        mPicNo.setText(String.valueOf(position + 1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    public class pictureShowAdapter extends PagerAdapter {

        List<ImageView> lists;

        public pictureShowAdapter(List<ImageView> list) {
            this.lists = list;
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(lists.get(position % lists.size()));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            try {
                container.addView(lists.get(position % lists.size()), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list.get(position % list.size());
        }
    }

}
