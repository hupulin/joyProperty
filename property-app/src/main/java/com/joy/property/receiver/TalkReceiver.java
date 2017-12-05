package com.joy.property.receiver;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.Util.SpUtil;
import com.alibaba.fastjson.JSONObject;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.MainApplication;
import com.joy.property.login.SplashActivity;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.photo.util.PublicWay;

import cn.jpush.android.api.JPushInterface;

/**
 *  Created by xzz on 2017/7/19.
 **/
public class TalkReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver ";
    private Handler handler=new Handler();
    private NotificationManager nm;
    private String data;


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - 222" + intent.getAction());
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "收到了自定义消息。消息内容是："+  bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            // 在这里可以做些统计，或者做些其他工作
            Log.d(TAG, "收到了通知" + JPushInterface.EXTRA_MSG_ID + "通知id");
            data = bundle.getString(JPushInterface.EXTRA_EXTRA);

            if (data == null)
                return;
            data = data.replaceAll("\\\\", "");
            if (data.contains("category")) {
                data = data.substring(0, data.indexOf(":") + 1) + data.substring(data.indexOf(":") + 2, data.length() - 2) + "}";
                data = JSONObject.parseObject(data).getString("category");


                if (100 == Integer.parseInt(JSONObject.parseObject(data).getString("type"))) {
                    SpUtil.put(context, "IsOffLine", true);
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
                    Log.i("99999", "onReceive: 存了通知数据"+data);
                    ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, data);
                    if (isRunningForeground(context)) {
                        handler.postDelayed(() -> {
                            if (PublicWay.forceOfflineActivity==null) {
                            Intent intent1 = new Intent(context, ForceOfflineActivity.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent1);
                            SpUtil.put(context, "IsSplashActivity", false);
                            }
                        }, SpUtil.getBoolean(context, "IsSplashActivity") ? 3500 : 0);
                    }
                } else {
                    if (isRunningForeground(context)) {
                        Intent intent1 = new Intent(context, PushJumpActivity.class);
                        SpUtil.put(context, "ReceiverNotificationId", bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID));
                        SpUtil.put(context, "ReceiverData", data);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (PublicWay.pushJumpActivity != null)
                            PublicWay.pushJumpActivity.finish();
                        context.startActivity(intent1);
                    }
                }

            }
//            // 在这里可以做些统计，或者做些其他工作
//            Log.d(TAG, "收到了通知" + JPushInterface.EXTRA_MSG_ID + "通知id");
//            data = bundle.getString(JPushInterface.EXTRA_EXTRA);
//
//            if (data == null )
//                return;
//            data = data.replaceAll("\\\\", "");
//            System.out.println(data + "data");
//            if (data.contains("category")) {
//            data = data.substring(0, data.indexOf(":") + 1) + data.substring(data.indexOf(":") + 2, data.length() - 2) + "}";
//            data = JSONObject.parseObject(data).getString("category");
////
//            if (100 == Integer.parseInt(JSONObject.parseObject(data).getString("type"))) {
////                    CloudIntercomLibrary.getInstance().logout(context,null);
//                SpUtil.put(context, "IsOffLine", true);
//                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
//                Log.d(TAG, "存了数据"+data);
//                ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_HOME_DATA, data);
//                if (isRunningForeground(context)) {
//                    handler.postDelayed(() -> {
//                        Intent intent1 = new Intent(context, ForceOfflineActivity.class);
//                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent1);
//                        SpUtil.put(context, "IsSplashActivity", false);
//                    }, SpUtil.getBoolean(context, "IsSplashActivity") ? 3500 : 0);
//
//                }
//            } else {
//                if (isRunningForeground(context)) {
//                    Intent intent1 = new Intent(context, PushJumpActivity.class);
//                    SpUtil.put(context, "ReceiverNotificationId", bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID));
//                    SpUtil.put(context, "ReceiverData", data);
//                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    if (PublicWay.pushJumpActivity != null)
//                        PublicWay.pushJumpActivity.finish();
//                    context.startActivity(intent1);
//                }
//            }
//        }
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            Intent intent2=new Intent(context, SplashActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2   );
            String data=bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println(isRunningForeground(context)+"run"+data + "点开data");

            if (data==null)
                return;
            data=data.replaceAll("\\\\","");
            System.out.println(data + "点开data");
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
            if (data.contains("category")) {
                data = data.substring(0, data.indexOf(":") + 1) + data.substring(data.indexOf(":") + 2, data.length() - 2) + "}";
                data = JSONObject.parseObject(data).getString("category");


                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, data);

            }

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    @SuppressWarnings("deprecation")
    private boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        return !TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName());
    }

}
