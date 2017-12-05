package com.joy.common.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.application.KeyValue;
import com.joy.library.utils.ConfigUtil;

/**
 * Created by Admin on 2015-03-05
 */
public class UserInfoParkHelper {

    protected static UserInfoParkHelper instance;
    private UserInfoTo userInfoTo;
    private Context mContext;

    private UserInfoParkHelper(Context context) {
         mContext = context;
        load(context);
    }


    public static UserInfoParkHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (UserInfoParkHelper.class) {
                if (instance == null) {
                    instance = new UserInfoParkHelper(context);
                }
            }
        }
        instance.mContext = context;
        return instance;
    }

    public UserInfoTo getUserInfoTo() {
        return userInfoTo;
    }

    public String getSid() {

        return userInfoTo == null ? "" : userInfoTo.getSid();
    }

    public String getPhone() {
        if (userInfoTo == null) return "";
        if (!TextUtils.isEmpty(userInfoTo.getPhone())) {

            return userInfoTo.getPhone();
        }


        return "";
    }

    public void updateUser(UserInfoTo infoTo) {
        userInfoTo = infoTo;
        save();
    }

    private void save() {
        ConfigUtil.saveString(PreferenceManager.getDefaultSharedPreferences(mContext),
                KeyValue.KEY_USER_INFO_PARK, JSON.toJSONString(userInfoTo));
    }

    public void load(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String userJson = ConfigUtil.getString(sp, KeyValue.KEY_USER_INFO_PARK, "");
        Log.d("KeyValue-----", userJson);
        if (!TextUtils.isEmpty(userJson)&&!"kong".equals(userJson)) {

            userInfoTo = JSON.parseObject(userJson, UserInfoTo.class);
        }
    }

    public boolean isLogin(){
     SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
      return  ConfigUtil.getBoolean(sp,KeyValue.KEY_IS_LOGIN_INFO);
    }
    public void updateLogin(boolean flag){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        ConfigUtil.saveBoolean(sp ,KeyValue.KEY_IS_LOGIN_INFO,flag);
    }
}
