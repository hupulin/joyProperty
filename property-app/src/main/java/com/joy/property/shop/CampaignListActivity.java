package com.joy.property.shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.ActivityGoodsVoListTo;
import com.jinyi.ihome.module.newshop.ActivityListParam;
import com.jinyi.ihome.module.newshop.ActivityTimeTo;
import com.jinyi.ihome.module.newshop.ActivityTo;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.library.utils.ConfigUtil;
import com.joy.library.utils.DateUtil;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.shop.adapter.HotActivityAdapter;
import com.joyhome.nacity.app.shop.adapter.ScrollViewWithListView;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2017/4/12.
 **/
public class CampaignListActivity extends BaseActivity implements View.OnClickListener {

    private ScrollViewWithListView activityListView;
    private ImageView activityImage;
    private TextView hour;
    private TextView minute;
    private TextView second;
    private long leaveTime;
    private TextView millSecond;
    private TextView titleName;
    private Thread threadTime;
    private RelativeLayout adLayout;
    private ActivityTimeTo activityTimeInfo=new ActivityTimeTo();
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_list);
        findView();
        getActivityData();
        ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, "");
    }

    private void findView() {
        activityListView = (ScrollViewWithListView) findViewById(R.id.listView);
        activityListView.setDividerHeight(0);
        activityListView.setFocusable(false);

        activityImage = (ImageView) findViewById(R.id.iv_ad);
        hour = (TextView) findViewById(R.id.hour);
        minute = (TextView) findViewById(R.id.minute);
        second = (TextView) findViewById(R.id.second);
        millSecond = (TextView) findViewById(R.id.millSecond);
        titleName = (TextView) findViewById(R.id.title_name);
        adLayout = (RelativeLayout) findViewById(R.id.adLayout);
        title = (TextView) findViewById(R.id.title);
        findViewById(R.id.iv_back).setOnClickListener(this);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.post(() -> scrollView.scrollTo(0, 0));
    }

    private void getActivityData(){
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        ActivityListParam param=new ActivityListParam();
        param.setCommunityId(mUserHelperBulk.getUserInfoTo().getApartmentSid());
        api.getActivityList(param, new HttpCallback<MessageToBulk<List<ActivityTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<ActivityTo>> msg, Response response) {
                if (msg.getCode() == 0) {
                    if (msg.getActivityList() != null && msg.getActivityList().size() > 0) {
                        setActivityTitle(msg.getActivityList().get(0).getActivityVo());
                        setActivityView(msg.getActivityList().get(0).getActivityVo().getActivityGoodsVoList());
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void setActivityView(List<ActivityGoodsVoListTo> activityList){
        HotActivityAdapter adapter=new HotActivityAdapter(getThisContext(),true);
        adapter.setList(activityList);
        activityListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        activityListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getThisContext(), SideGoodsDetailActivity.class);
            intent.putExtra("GoodsSid", activityList.get(position).getActivityGoodsId());
            intent.putExtra("ActivityGoods", true);
            intent.putExtra("ActivityTimeInfo", activityTimeInfo);

            startActivity(intent);

            goToAnimation(1);


        });
    }

    public void setActivityTitle(ActivityTo.ActivityVoTo activityTo){
        activityTimeInfo.setActivityEndTime(activityTo.getActivityEndTime());
        activityTimeInfo.setActivityStartTime(activityTo.getActivityStartTime());
        activityTimeInfo.setWarmupStartTime(activityTo.getWarmupStartTime());
        activityTimeInfo.setActivityId(activityTo.getActivityId());
        Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(activityTo.getActivityUrl())).into(activityImage);
        titleName.setText(activityTo.getActivityName());
        title.setText(activityTo.getActivityName());
        if (DateUtil.cureentMillis()<DateUtil.DateMillis(activityTo.getWarmupStartTime(), DateUtil.mDateTimeFormatString)||DateUtil.cureentMillis()>DateUtil.DateMillis(activityTo.getActivityEndTime(), DateUtil.mDateTimeFormatString)){
            adLayout.setVisibility(View.GONE);

        }else {
            adLayout.setVisibility(View.VISIBLE);
            if (DateUtil.cureentMillis()>DateUtil.DateMillis(activityTo.getWarmupStartTime(),DateUtil.mDateTimeFormatString)&&DateUtil.cureentMillis()<DateUtil.DateMillis(activityTo.getActivityStartTime(),DateUtil.mDateTimeFormatString)){
                countTime(DateUtil.DateMillis(activityTo.getActivityStartTime(),DateUtil.mDateTimeFormatString),DateUtil.DateMillis(activityTo.getActivityEndTime(),DateUtil.mDateTimeFormatString));
            }else if (DateUtil.cureentMillis()>DateUtil.DateMillis(activityTo.getActivityStartTime(),DateUtil.mDateTimeFormatString)&&DateUtil.cureentMillis()<DateUtil.DateMillis(activityTo.getActivityEndTime(),DateUtil.mDateTimeFormatString)){
                countTime(DateUtil.DateMillis(activityTo.getActivityEndTime(),DateUtil.mDateTimeFormatString),0);
            }
        }


    }
    public void countTime(long startTime,long endTime ){
        leaveTime=startTime-DateUtil.cureentMillis();
        leaveTime=leaveTime*1000;
        threadTime = new Thread(() -> {
            for (;leaveTime>0;) {
                SystemClock.sleep(100);
                leaveTime = leaveTime - 100;
                runOnUiThread(() -> {
                    hour.setText(leaveTime/1000/3600+"");
                    minute.setText((leaveTime/1000/60-leaveTime/1000/3600*60)+"");
                    second.setText((leaveTime / 1000 - leaveTime / 1000 / 60 * 60) + "");


                    millSecond.setText(leaveTime / 100%10 + "");

                });
                if (leaveTime<=0) {
                    if (endTime != 0) {
                        leaveTime = endTime - DateUtil.cureentMillis();
                        leaveTime = leaveTime * 1000;
                        for (; leaveTime > 0; ) {

                            SystemClock.sleep(100);
                            leaveTime = leaveTime - 100;

                            runOnUiThread(() -> {
                                hour.setText(leaveTime / 1000 / 3600 + "");
                                minute.setText((leaveTime / 1000 / 60 - leaveTime / 1000 / 3600 * 60) + "");
                                second.setText((leaveTime / 1000 - leaveTime / 1000 / 60 * 60) + "");


                                millSecond.setText(leaveTime / 100 % 10 + "");



                            });


                        }
                    }else {
                        runOnUiThread(() -> {
                            adLayout.setVisibility(View.GONE);
                        });
                    }
                }

            }
        });
        threadTime.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                goToAnimation(2);
                break;
        }
    }
}
