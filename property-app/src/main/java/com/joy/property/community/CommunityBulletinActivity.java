package com.joy.property.community;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.vote.VoteFindParam;
import com.jinyi.ihome.module.vote.VoteTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VoteApi;
import com.joy.property.R;
import com.joy.property.common.adapter.CommonFragmentPagerAdapter;
import com.joy.property.community.fragment.NoticeFragment;
import com.joy.property.community.fragment.VoteListFragment;
import com.joy.property.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2014-11-13.
 * 小区公告
 */
public class CommunityBulletinActivity extends BaseActivity
        implements OnClickListener, OnPageChangeListener {
        private ImageView cursor;
        private TextView  tvHeadLine;
        //private TextView  tvGuide;
        private TextView  mTitle;
        private ViewPager mViewPager;
        //private TextView mVote;
        // private ImageView mLine;
        private int one;
        private int pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_property);
        List<VoteTo> voteToList = LoaderVoteData.getInstance(getApplication()).getVoteToList();

        findById();
    //    setList(0);
        if (voteToList!=null && voteToList.size()>0) {
          //  Toast.makeText(this,voteToList.toString()+"",Toast.LENGTH_LONG).show();
         //   mLine.setVisibility(View.VISIBLE);
          //  mVote.setVisibility(View.VISIBLE);
            initData(true);
        }else {
          //  mLine.setVisibility(View.GONE);
           // mVote.setVisibility(View.GONE);
            initData(false);
        }
    }
    private void findById() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        //tvHeadLine = (TextView) findViewById(R.id.tv_my_submission);
        //tvHeadLine.setOnClickListener(this);
        //tvGuide = (TextView) findViewById(R.id.tv_call_detail);
       //tvGuide.setOnClickListener(this);
        //cursor = (ImageView) findViewById(R.id.iv_cursor);
        mViewPager = (ViewPager) findViewById(R.id.property_viewpager);
        mViewPager.addOnPageChangeListener(this);
        mTitle = (TextView) findViewById(R.id.tv_title);
       // mVote = (TextView) findViewById(R.id.vote);
      // mLine = (ImageView) findViewById(R.id.image_line);
      // mVote.setVisibility(View.GONE);
      //  mLine.setVisibility(View.GONE);
      // mVote.setOnClickListener(this);
        findViewById(R.id.layout).setVisibility(View.GONE);
        findViewById(R.id.iv_cursor).setVisibility(View.GONE);
        findViewById(R.id.line).setVisibility(View.GONE);
    }

    private void setList(int index) {
        VoteApi api = ApiClient.create(VoteApi.class);
        VoteFindParam param = new VoteFindParam();
        param.setIndex(index);
        param.setApartmentSid(mHelper.getSid());
        api.findVoteList(param, new HttpCallback<MessageTo<List<VoteTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<VoteTo>> msg, Response response) {
                if (msg == null)
                    return;
                if (msg.getSuccess() == 0) {

                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void failure(RetrofitError error) {

                super.failure(error);
            }
        });
    }

    private void initData(boolean flag) {
        mTitle.setText("通知");
//        tvHeadLine.setText(getString(R.string.channel_village_headlines));
//        tvHeadLine.setBackgroundColor(0xfff8f8f8);
      // tvGuide.setText(getString(R.string.channel_village_guide));
     //   tvGuide.setBackgroundColor(0xfff8f8f8);
//        mVote.setText("业主调查");
//        mVote.setBackgroundColor(0xfff8f8f8);
//        // 设置图片的默认位置
          Matrix matrix = new Matrix();
        // 获得图片的宽度
//        Bitmap lineBm = BitmapFactory.decodeResource(getResources(),
//                R.drawable.navigation_ic);
     //   int imgWidth = lineBm.getWidth();
        // 获得屏幕的宽度
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int screenWidth = outMetrics.widthPixels;
        // 可用进行设置图片位置
        int dx = 0 ;
//        if (flag) {
//            dx= (screenWidth / 3 - imgWidth) / 2;
//            one = screenWidth / 3;
//        }else {
//            dx= (screenWidth / 2 - imgWidth) / 2;
//            one = screenWidth / 2;
//        }
        matrix.postTranslate(dx, 0);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cursor.getLayoutParams();
//        params.leftMargin=dx;
      //  cursor.setImageMatrix(matrix);

        List<Fragment> fragments = new ArrayList<>();
        NoticeFragment hlf = new  NoticeFragment();//

      //  GuideFragment gf = new  GuideFragment();
        fragments.add(hlf);
       // fragments.add(gf);
        System.out.println(hlf.toString()+"aaaaaa");
        if (flag) {
            VoteListFragment vlf = new VoteListFragment();
            fragments.add(vlf);

        }
        //翻页用的。
        CommonFragmentPagerAdapter mAdapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        if (flag) {
            mViewPager.setOffscreenPageLimit(3);
            mAdapter.notifyDataSetChanged();
        }else {
            mViewPager.setOffscreenPageLimit(2);
        }
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;

//            case R.id.tv_my_submission:
//                tvHeadLine.setTextColor(0xffea5514);
//                //tvGuide.setTextColor(0xffb5b2ac);
//                mVote.setTextColor(0xffb5b2ac);
//                mViewPager.setCurrentItem(0);
//                break;
//            case R.id.tv_call_detail:
//                tvHeadLine.setTextColor(0xffb5b2ac);
//                tvGuide.setTextColor(0xffea5514);
//                mVote.setTextColor(0xffb5b2ac);
//                mViewPager.setCurrentItem(1);
//
//                break;
//            case R.id.vote:
//                tvHeadLine.setTextColor(0xffb5b2ac);
//                //tvGuide.setTextColor(0xffb5b2ac);
//                mVote.setTextColor(0xffea5514);
//                mViewPager.setCurrentItem(2);
//                break;

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
            tvHeadLine.setTextColor(0xffea5514);
            //tvGuide.setTextColor(0xffb5b2ac);
          //  mVote.setTextColor(0xffb5b2ac);
        } else if (position == 1) {
            tvHeadLine.setTextColor(0xffb5b2ac);
          //  tvGuide.setTextColor(0xffea5514);
         //   mVote.setTextColor(0xffb5b2ac);
        }else if (position == 2) {
            tvHeadLine.setTextColor(0xffb5b2ac);
          //  tvGuide.setTextColor(0xffb5b2ac);
         //   mVote.setTextColor(0xffea5514);
        }
        //动画提示条
        TranslateAnimation anim = new TranslateAnimation(one * pre, one * position, 0, 0);
        pre = position;
        anim.setDuration(300);
        anim.setFillAfter(true);
        cursor.startAnimation(anim);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.clearOnPageChangeListeners();
    }

    @Override
    protected Context getThisContext() {
        return CommunityBulletinActivity.this;
    }

    @Override
    protected String toPageName() {
        return  "小区公告";
    }
}
