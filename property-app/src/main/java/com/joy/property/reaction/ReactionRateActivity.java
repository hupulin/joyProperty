package com.joy.property.reaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.Util.EasySlidingTabsReaction;
import com.Util.MyViewPager;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.ReactionApi;
import com.joy.property.R;
import com.joyhome.nacity.app.base.BaseActivity;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.SpUtil;
import com.joy.property.utils.StatisticsUtil;
import com.joy.property.utils.StatuBarUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/**
 * Created by xz on 2016/5/28.
 **/
public class ReactionRateActivity extends BaseActivity implements View.OnClickListener {
    private EasySlidingTabsReaction easySlidingTabs;
    private MyViewPager easyVP;
    private List<Fragment> fragments= new ArrayList<>();
    private List<ApartmentInfoTo>apartmentInfoTos=new ArrayList<>();
    private ImageView mBack;
    private ImageView changePark;
    private ImageView animationImageViewOne;
    private ImageView animationImageViewTwo;
    private ImageView ImageLeft;
    private ImageView imageRight;
    private int location;

    private boolean firstChange;
    private TextView mTitle;
    private boolean isFirstinit;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reactiontime);
        StatuBarUtil.setStatueBarTransparent(getWindow());
        this.initViews();
        this.getTitleName();
        gestureDetector = new GestureDetector(ogl);
    }
    private void getTitleName() {
        ChangeParkUtil.changeToHome(getThisContext(),mUserHelper);
        ReactionApi api= ApiClient.create(ReactionApi.class);
        api.getReactionTitle(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(getThisContext()) {
           @Override
           public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {

               ApartmentInfoTo apartmentInfoTo = new ApartmentInfoTo();
               apartmentInfoTo.setApartmentSid("");
               apartmentInfoTo.setApartmentName("              ");
               apartmentInfoTos.clear();

               apartmentInfoTos.add(apartmentInfoTo);
               apartmentInfoTos.addAll(msg.getData());
               apartmentInfoTos.add(apartmentInfoTo);
               initData();

           }
       });
    }


    private void initViews(){
        animationImageViewOne=(ImageView)findViewById(R.id.animation_image_01);
        animationImageViewTwo=(ImageView)findViewById(R.id.animation_image_02);
        imageRight=(ImageView)findViewById(R.id.image_right);
        ImageLeft=(ImageView)findViewById(R.id.image_left);
        this.easySlidingTabs = (EasySlidingTabsReaction) this.findViewById(R.id.tpi);
        easySlidingTabs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                Log.e("chen", "ScrollView-onTouch");
                //不能滑动
                return true;
                //可以滑动
                //return false;
            }
        });
        mBack = (ImageView) findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.title2);
        TextView  mList = (TextView) findViewById(R.id.list);
        mList.setOnClickListener(this);
        changePark = (ImageView) findViewById(R.id.changePark);
        changePark.setOnClickListener(this);
        changePark.setBackgroundResource(R.drawable.selector_park);
        if (SpUtil.getString(getThisContext(), "limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A014"))
        {
            changePark.setVisibility(View.VISIBLE);
            mTitle.setText("响应速度(住宅)");
        }
        this.easyVP = (MyViewPager) this.findViewById(R.id.viewPager);
    }

    private void initData() {

       fragments.clear();


    for (int i = 0; i < apartmentInfoTos.size(); i++) {
        this.fragments.add(new ReactionRateFragment(apartmentInfoTos.get(i).getApartmentSid()));
    }
        this.easyVP.setAdapter(this.adapter);
        this.easySlidingTabs.setViewPager(this.easyVP);
        this.easySlidingTabs.setIndicatorColor(Color.parseColor("#00000000"));
        adapter.notifyDataSetChanged();
        easySlidingTabs.notifyDataSetChanged();

        easyVP.setCurrentItem(1);
        location=easyVP.getCurrentItem();

        easyVP.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                StatisticsUtil.sendStatistics(mUserHelper.getSid(), "响应速度-" + apartmentInfoTos.get(position).getApartmentName(), getThisContext());
                if(location<position){
                    Animation animationOne = AnimationUtils.loadAnimation(getThisContext(),
                            R.anim.set_animation_left_one);
                    Animation animationTwo = AnimationUtils.loadAnimation(getThisContext(),
                            R.anim.set_animation_left_two);
                    animationImageViewOne.startAnimation(animationOne);
                    animationImageViewTwo.startAnimation(animationTwo);
                    Animation alpha = new AlphaAnimation(0.0f, 0.0f);
                    alpha.setDuration(200);
                    ImageLeft.startAnimation(alpha);
                    imageRight.startAnimation(alpha);

                }else {
//
                    Animation animationOne = AnimationUtils.loadAnimation(getThisContext(),
                            R.anim.set_animation_right_one);
                    Animation animationTwo = AnimationUtils.loadAnimation(getThisContext(),
                           R.anim.set_animation_right_two);

                    Animation alpha = new AlphaAnimation(0.0f, 0.0f);
                    animationImageViewOne.startAnimation(animationOne);
                    animationImageViewTwo.startAnimation(animationTwo);
//                    int width =(int)(getScreenWidth()/3.5);
//                    Log.i("width", "width: "+width);
//                    Animation translateOne = new TranslateAnimation(-width,0,0,0);
//                   Animation scaleOne = new ScaleAnimation(0.25f,1.0f,0.5f,1.0f,Animation.ABSOLUTE,0.5f,Animation.ABSOLUTE,0.5f);
//                    Animation translateTwo = new TranslateAnimation(0,width,0,0);
//                    Animation scaleTwo = new ScaleAnimation(1.0f,0.25f,1.0f,0.5f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//                    AnimationSet setOne =new AnimationSet(false);
//                    AnimationSet setTwo =new AnimationSet(false);
//                    setOne.setDuration(3000);
//                    setOne.addAnimation(translateOne);
//                    setOne.addAnimation(scaleOne);
//                    setTwo.setDuration(3000);
//                    setTwo.addAnimation(translateTwo);
//                    setTwo.addAnimation(scaleTwo);
                    alpha.setDuration(200);
                    ImageLeft.startAnimation(alpha);
                    imageRight.startAnimation(alpha);

                }
                location=position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        easyVP.setOnTouchListener(new View.OnTouchListener() {
                float startX = 0;

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:
                            startX = event.getX();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (event.getX() - startX > 0 && easyVP.getCurrentItem() == 1) {
                                easyVP.setScrollble(false);
                                break;
                            } else if(event.getX() - startX <0 && easyVP.getCurrentItem() ==apartmentInfoTos.size()-2){
                                easyVP.setScrollble(false);
                                break;
                            }
                            else {
                                easyVP.setScrollble(true);
                            }

                    }
                    return false;
                }
            });
        }




    private FragmentPagerAdapter adapter =new FragmentPagerAdapter(getSupportFragmentManager()){

        @Override
        public CharSequence getPageTitle(int position) {

            return apartmentInfoTos.get(position).getApartmentName();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {

            return fragments==null?0:fragments.size();
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.changePark:
                changePark();
                break;
            case R.id.list:
                Intent intent = new Intent(getThisContext(), SelectComunityActivity.class);
                startActivityForResult(intent,20);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 20) {
            switch (requestCode) {
                case 20:

                    String sid = data.getStringExtra("sid");
            //        mCarNum.setText(carNum);
                    for (int i = 0; i < apartmentInfoTos.size(); i++) {
                        if(sid.equals(apartmentInfoTos.get(i).getApartmentSid())){
                            Log.i("22响应速度", apartmentInfoTos.get(i).toString());
                            easyVP.setCurrentItem(i);
                        }
                    }
                    break;
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private GestureDetector gestureDetector;
    private OnGestureListener ogl = new OnGestureListener() {

        // 表示命中了手势
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
//			Toast.makeText(getContext(), "onSingleTapUp", 0).show();

            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                                float distanceY) {
            if(easyVP.getCurrentItem()==1){
                if(e2.getY()-e1.getX()< 0) {
                    easyVP.setScrollble(false);
                    //Toast.makeText(getThisContext(),"点击了",Toast.LENGTH_LONG).show();
                }
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            System.out.println(e1 + "-------------" + e2);


            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }
    };
    private void changePark() {

      startActivity(new Intent(getThisContext(),ReactionRateActivitypark.class));
        finish();
      overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
