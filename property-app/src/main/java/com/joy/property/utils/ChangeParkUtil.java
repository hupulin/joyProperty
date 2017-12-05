package com.joy.property.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.api.ApiClient;
import com.joy.common.helper.UserInfoHelper;

/**
 * Created by xz on 2016/12/22.
 **/
public class ChangeParkUtil {
    public static void changeToPark(Context context,UserInfoHelper mUserHelper){
        ApiClient.setParkUrl();

        UserInfoTo userInfoTo = JSON.parseObject(SpUtil.getString(context, "ParkInfo"), UserInfoTo.class);
        mUserHelper.updateUser(userInfoTo,context);
        mUserHelper.setUserInfoTo(userInfoTo);
    }
    public static void changeToHome(Context context,UserInfoHelper mUserHelper){
        ApiClient.setHomeUrl();
        UserInfoTo userInfoTo = JSON.parseObject(SpUtil.getString(context,"HomeInfo"), UserInfoTo.class);
        mUserHelper.updateUser(userInfoTo,context);
        mUserHelper.setUserInfoTo(userInfoTo);
    }
}
