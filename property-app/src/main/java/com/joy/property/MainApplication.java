package com.joy.property;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;

import com.Util.SpUtil;
import com.igexin.sdk.PushManager;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.api.ApiClient;
import com.joy.common.helper.UserInfoHelper;
import com.joy.library.utils.FileUtil;
import com.joy.property.neighborhood.RefreshEvent;
import com.joy.property.utils.StatisticsUtil;
import com.tendcloud.tenddata.TCAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.jpush.android.api.JPushInterface;


public class MainApplication extends Application {

    public static Context mContext;
    public static SharedPreferences mPref;
    public String offlineDate ;
    public Date getNumber() {
        return number;
    }

    public void setNumber(Date number) {
        this.number = number;
    }

    private Date number;

    private int mCount=0;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public Date getDigital() {
        return digital;
    }

    public void setDigital(Date digital) {
        this.digital = digital;
    }

    private Date digital;

    public Date getAmount() {
        return amount;
    }

    public void setAmount(Date amount) {
        this.amount = amount;
    }

    private Date amount;

    public Date getIsCompetence() {
        return isCompetence;
    }

    public void setIsCompetence(Date isCompetence) {
        this.isCompetence = isCompetence;
    }

    private Date isCompetence;

    public String getTDapartmentSid() {
        return TDapartmentSid;
    }

    public void setTDapartmentSid(String TDapartmentSid) {
        this.TDapartmentSid = TDapartmentSid;
    }

    private String TDapartmentSid;

    public ServiceMainExpandTo getApp_ServiceMainExpandTo() {
        return app_ServiceMainExpandTo;
    }

    public void setApp_ServiceMainExpandTo(ServiceMainExpandTo app_ServiceMainExpandTo) {
        this.app_ServiceMainExpandTo = app_ServiceMainExpandTo;
    }

    private ServiceMainExpandTo app_ServiceMainExpandTo;

    private UserInfoTo app_execute_man;


    public UserInfoTo getApp_execute_man() {
        return app_execute_man;
    }

    public void setApp_execute_man(UserInfoTo app_execute_man) {
        this.app_execute_man = app_execute_man;
    }

    public List<UserInfoTo> getApp_care_man() {
        return app_care_man;
    }

    public void setApp_care_man(List<UserInfoTo> app_care_man) {
        this.app_care_man = app_care_man;
    }

    private List<UserInfoTo> app_care_man = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this.getApplicationContext();
        mPref = PreferenceManager.getDefaultSharedPreferences(this);
        new ApiClient().setmContext(mContext);
        JPushInterface.init(getApplicationContext());
        PushManager.getInstance().initialize(this);
        SpUtil.put(mContext,"IsSplashActivity",true);
        TCAgent.LOG_ON=true;
        // App ID: 在TalkingData创建应用后，进入数据报表页中，在“系统设置”-“编辑应用”页面里查看App ID。
        // 渠道 ID: 是渠道标识符，可通过不同渠道单独追踪数据。
        TCAgent.init(this, "00370512296343B392A7FE2E75CFBC21", "YueService");
        // 如果已经在AndroidManifest.xml配置了App ID和渠道ID，调用TCAgent.init(this)即可；或与AndroidManifest.xml中的对应参数保持一致。
        TCAgent.setReportUncaughtExceptions(true);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mCount++;
                //如果mCount==1，说明是从后台到前台
                if (mCount == 1) {

                    UserInfoHelper userInfoHelper = UserInfoHelper.getInstance(getApplicationContext());
                    if (userInfoHelper != null && userInfoHelper.getSid() != null)
                        StatisticsUtil.sendStatistics(userInfoHelper.getSid(), "登入",getApplicationContext());
                    EventBus.getDefault().post(new RefreshEvent("OffLine"));
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mCount--;
                //如果mCount==0，说明是前台到后台

                if (mCount == 0) {
                    UserInfoHelper userInfoHelper = UserInfoHelper.getInstance(getApplicationContext());
                    if (userInfoHelper != null && userInfoHelper.getSid() != null)
                        StatisticsUtil.sendStatistics(userInfoHelper.getSid(), "登出",getApplicationContext());

                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                System.out.println(mCount + "count-----3");
            }
        });

    }


    public static String getCacheImagePath() {
        return FileUtil.getSdCardPath();
    }

    public static class KeyValue {
        public final static String KEY_LOGIN_SHORTCUT = "SHORTCUT";
        public final static String KEY_HOME_DATA = "datas";

    }

    public static class DefaultValue {
        //192.168.17.109:9009
        public final static String IMAGE_URI = "http://7xk6y7.com2.z0.glb.qiniucdn.com/";
        public final static String CHAT_URL = "http://s1.joyhomenet.com:9040/web/chat/";
        public final static String CHAT_URL_Park ="http://s0.joyhomenet.com:9040/web/chat/";
        public final static String ABOUT_URL = "http://s1.joyhomenet.com:9040/static/html/property-about.html?version=";
        public final static String PROTOCOL_URL = "http://s1.joyhomenet.com:9040/static/html/property-protocol.html";
    }


    public static String getImagePath(String path) {
        if (path == null)
            return "";
        return DefaultValue.IMAGE_URI + path;
    }

}
