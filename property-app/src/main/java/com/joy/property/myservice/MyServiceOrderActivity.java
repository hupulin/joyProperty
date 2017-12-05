package com.joy.property.myservice;


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
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.ApiClientPark;
import com.joy.common.api.HttpCallback;
import com.joy.common.application.KeyValue;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.adapter.CommonFragmentPagerAdapter;
import com.joy.property.myservice.fragment.MyInspectFragment;
import com.joy.property.task.ResultActivity;
import com.joy.property.task.TimePickerDialog.TimePickerDialog;
import com.joy.property.task.TimePickerDialog.data.Type;
import com.joy.property.task.TimePickerDialog.listener.OnDateSetListener;
import com.joy.property.task.fragment.MyInfoFragment;
import com.joy.property.task.fragment.MyTaskFragment;
import com.joy.property.task.fragment.MyWorkFragment;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.photo.util.PublicWay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyServiceOrderActivity extends BaseActivity implements
        OnPageChangeListener, OnClickListener, OnDateSetListener {

    private TextView mTaskLeft;
    private TextView mTaskRight;
    private TextView myWork;

    private TextView mTaskAttention;
    private ImageView mCursor;
    private ViewPager mViewPager;
    private int one, pre;
    Intent intent;
    TimePickerDialog mDialogYearMonthDay;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private String date;
    private List<ApartmentInfoTo> apartmentInfoTos = new ArrayList<ApartmentInfoTo>();
    private int mValue = 0;
    public final static int RESULT_CODE = 1;
    private ImageView changePark;
    private boolean firstChange;
    private TextView title;
    private int currentSelect;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        findById();
        initIntentDatas();
        initDatas(getIntent().getIntExtra("Index", 0));

        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("选择派单日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setCyclic(false)
                .build();
        setLists("杭州市");
        setListsPark("");
        if (PublicWay.myServiceOrderActivity!=null)
            PublicWay.myServiceOrderActivity.finish();
        PublicWay.myServiceOrderActivity=this;
    }

    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.screen).setOnClickListener(this);
        mTaskLeft = (TextView) findViewById(R.id.task_left);
        mTaskLeft.setOnClickListener(this);
        myWork = (TextView) findViewById(R.id.my_work);
        myWork.setOnClickListener(this);
        mTaskRight = (TextView) findViewById(R.id.task_right);
        mTaskRight.setOnClickListener(this);
        mTaskAttention = (TextView) findViewById(R.id.tack_attention);
        mTaskAttention.setOnClickListener(this);
        mCursor = (ImageView) findViewById(R.id.cursor);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.addOnPageChangeListener(this);
        title = (TextView) findViewById(R.id.tv_title);
        changePark = (ImageView) findViewById(R.id.changePark);
        changePark.setOnClickListener(this);

    }

    private void initIntentDatas() {
        intent = this.getIntent();
        mValue = intent.getIntExtra("page",0);
        if (SpUtil.getString(getThisContext(),"limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A001")) {
            changePark.setVisibility(View.VISIBLE);
            title.setText("服务订单(住宅)");
            if (ConfigUtil.getString(sp, KeyValue.KEY_USER_INFO, "").equals(SpUtil.getString(getThisContext(),"HomeInfo"))) {
                changePark.setBackgroundResource(R.drawable.selector_park);
                ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
                title.setText("服务订单(住宅)");
                firstChange=false;
            }
            else {
                changePark.setBackgroundResource(R.drawable.selector_home);
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                title.setText("服务订单(园区)");
                firstChange=true;
            }
        }
      SpUtil.put(getThisContext(),"IsMyServiceJump",true);
    }

    public void initDatas(int index) {

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
        one = screenWidth / 4;
       fragments.clear();
        MyWorkFragment mwf =new  MyWorkFragment();
        MyInspectFragment thf = new MyInspectFragment();
        MyTaskFragment mtf =new  MyTaskFragment();
        MyInfoFragment tif =new  MyInfoFragment();
        fragments.add(mwf);
        fragments.add(thf);
        fragments.add(mtf);
        fragments.add(tif);
        FragmentManager fm = getSupportFragmentManager();
        CommonFragmentPagerAdapter mAdapter = new CommonFragmentPagerAdapter(fm, fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(mValue == 0 ? 0 : mValue);
        mViewPager.setCurrentItem(index);
if (getIntent().getBooleanExtra("HallTask",false)||getIntent().getBooleanExtra("IsForward",false))
    mViewPager.setCurrentItem(1);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

    }

    //点击事件
    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            myWork.setTextColor(0xff4fb2d6);
            mTaskLeft.setTextColor(0xffbebebe);
            mTaskRight.setTextColor(0xffbebebe);
            mTaskAttention.setTextColor(0xffbebebe);

        } else if (position == 1) {
            myWork.setTextColor(0xffbebebe);
            mTaskLeft.setTextColor(0xff4fb2d6);
            mTaskRight.setTextColor(0xffbebebe);
            mTaskAttention.setTextColor(0xffbebebe);
        } else if (position == 2) {
            myWork.setTextColor(0xffbebebe);
            mTaskLeft.setTextColor(0xffbebebe);
            mTaskRight.setTextColor(0xff4fb2d6);
            mTaskAttention.setTextColor(0xffbebebe);
        } else if (position == 3) {
            myWork.setTextColor(0xffbebebe);
            mTaskLeft.setTextColor(0xffbebebe);
            mTaskRight.setTextColor(0xffbebebe);
            mTaskAttention.setTextColor(0xff4fb2d6);
        }


        TranslateAnimation anim = new TranslateAnimation(one * pre, one * position, 0, 0);
        pre = position;
        anim.setDuration(300);
        anim.setFillAfter(true);
        mCursor.startAnimation(anim);
         currentSelect=position;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:

                onBackPressed();

                break;
            case R.id.screen:
              Intent intent=new Intent(getThisContext(),ScreenActivity.class);
                intent.putExtra("IsMyService",true);
                intent.putExtra("CurrentPosition",currentSelect);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.my_work:
                mTaskLeft.setTextColor(0xff6b6b6b);
                myWork.setTextColor(0xff4fb2d6);
                mTaskRight.setTextColor(0xff6b6b6b);
                mTaskAttention.setTextColor(0xff6b6b6b);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.task_left:
                myWork.setTextColor(0xffbebebe);
                mTaskLeft.setTextColor(0xff4fb2d6);
                mTaskRight.setTextColor(0xffbebebe);
                mTaskAttention.setTextColor(0xffbebebe);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.task_right:
                myWork.setTextColor(0xffbebebe);
                mTaskLeft.setTextColor(0xffbebebe);
                mTaskRight.setTextColor(0xff4fb2d6);
                mTaskAttention.setTextColor(0xffbebebe);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.tack_attention:
                myWork.setTextColor(0xffbebebe);
                mTaskLeft.setTextColor(0xffbebebe);
                mTaskRight.setTextColor(0xffbebebe);
                mTaskAttention.setTextColor(0xff4fb2d6);
                mViewPager.setCurrentItem(3);
                break;
            case R.id.changePark:
//                if(1==mViewPager.getCurrentItem()){
//                    if(!change){
//                        EventBus.getDefault().post(new RefreshEventTask("task",true));
//                        changePark.setBackgroundResource(R.drawable.selector_home);
//                        title.setText("服务订单(园区)");
//                        change=true;
//                    }else{
//                        changePark.setBackgroundResource(R.drawable.selector_park);
//                        title.setText("服务订单(住宅)");
//                        EventBus.getDefault().post(new RefreshEventTask("task",false));
//                        change=false;
//                    }
//                }else{
                    changePark();

//                }
                break;
            default:
                break;
        }

    }

    private  boolean change;

    @Override
    public void onDateSet(TimePickerDialog timePickerDialog, long millseconds) {
        date = getDateToString(millseconds);
        Intent intent = new Intent();
        intent.setClass(MyServiceOrderActivity.this, ResultActivity.class);
        intent.putExtra("date", date);
        startActivity(intent);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected String toPageName() {

        return "服务订单";
    }

    private void setLists(final String city) {

        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        final com.joyhome.nacity.app.util.CustomDialogFragment dialogFragment = new com.joyhome.nacity.app.util.CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findByAll(new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(MainApp.mContext) {
            @Override
            public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    if (msg.getData() != null) {
                        apartmentInfoTos.clear();
                        apartmentInfoTos.addAll(msg.getData());
                        Map<String, List<ApartmentInfoTo>> map = new HashMap<String, List<ApartmentInfoTo>>();
                        map.put("cache", apartmentInfoTos);
                        String json = JSON.toJSONString(map);
                        SpUtil.put(getThisContext(), "TaskCache", json);
                    }
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {


            }
        });

    }

    private void setListsPark(final String city) {

        ApartmentApi api = ApiClientPark.create(ApartmentApi.class);
        final com.joyhome.nacity.app.util.CustomDialogFragment dialogFragment = new com.joyhome.nacity.app.util.CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findByAll(new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(MainApp.mContext) {
            @Override
            public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    if (msg.getData() != null) {
                        apartmentInfoTos.clear();
                        apartmentInfoTos.addAll(msg.getData());
                        Map<String, List<ApartmentInfoTo>> map = new HashMap<String, List<ApartmentInfoTo>>();
                        map.put("cache", apartmentInfoTos);
                        String json = JSON.toJSONString(map);
                        SpUtil.put(getThisContext(), "TaskCachePark", json);
                    }
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {


            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
    private void changePark() {
        if (!firstChange){

            ApiClient.setParkUrl();
            changePark.setBackgroundResource(R.drawable.selector_home);
            title.setText("服务订单(园区)");

            UserInfoTo userInfoTo = JSON.parseObject(SpUtil.getString(getThisContext(),"ParkInfo"), UserInfoTo.class);
           mUserHelper.updateUser(userInfoTo,getThisContext());
            mUserHelper.setUserInfoTo(userInfoTo);
            firstChange=true;
        initDatas(getIntent().getIntExtra("Index",0));
        }else {
            ApiClient.setHomeUrl();
            firstChange=false;
            UserInfoTo userInfoTo = JSON.parseObject(SpUtil.getString(getThisContext(),"HomeInfo"), UserInfoTo.class);
            mUserHelper.updateUser(userInfoTo,getThisContext());
            mUserHelper.setUserInfoTo(userInfoTo);
            initDatas(getIntent().getIntExtra("Index",0));
            changePark.setBackgroundResource(R.drawable.selector_park);

            title.setText("服务订单(住宅)");
        }
        mViewPager.setCurrentItem(currentSelect);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
