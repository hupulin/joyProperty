package com.joy.property.receiver;


import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.MainApplication;
import com.joy.property.utils.SpUtil;


public class PushMessageReceiver extends BroadcastReceiver {
    private static final String TAG = "PushMessageReceiver";
    private int count=0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive -1111 " + intent.getAction());
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//                    context.getApplicationContext().startService(new Intent(context.getApplicationContext(), ForceOfflineService.class));

       String offlineData= ConfigUtil.getString(mSharedPreferences, MainApplication.KeyValue.KEY_HOME_DATA, "");
        if (!TextUtils.isEmpty(offlineData)&&100 ==  Integer.parseInt(JSONObject.parseObject(offlineData).getString("type"))&&isRunningForeground(context)) {
        return;
        }

        Bundle bundle = intent.getExtras();

        switch (bundle.getInt(PushConsts.CMD_ACTION)) {

            case PushConsts.GET_MSG_DATA:

                // 获取透传数据                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             9
                String appid = bundle.getString("appid");
                byte[] payload = bundle.getByteArray("payload");

                String taskid = bundle.getString("taskid");
                String messageid = bundle.getString("messageid");
                System.out.println(appid + "appid" + taskid + "taskid" + payload + "payload" + messageid + "messag");
                // smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
                boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
                System.out.println("第三方回执接口调用" + (result ? "成功" : "失败"));

                if (payload != null) {
                    String data = new String(payload);
                    /**
                     * 1.
                     */

                    Log.d("data--------", Integer.parseInt(JSONObject.parseObject(data).getString("type"))+"type--------");
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
//                    context.getApplicationContext().startService(new Intent(context.getApplicationContext(), ForceOfflineService.class));

                    ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, data);
                    if (100 ==  Integer.parseInt(JSONObject.parseObject(data).getString("type"))&&isRunningForeground(context)) {
                        Log.i("222", "onReceive:type=100 ");
                       if (!SpUtil.getBoolean(context,"IsStart")) {
                           Intent intent1 = new Intent(context, ForceOfflineActivity.class);
                           intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           context.startActivity(intent1);

                       }
                        SpUtil.put(context,"IsStart",true);
                    }

                }
                break;

            case PushConsts.GET_CLIENTID:
                // 获取ClientID(CID)
                // 第三方应用需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送
                String clientId = bundle.getString("clientid");
                break;

        }
    }

    private boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        return !TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName());
    }
}
