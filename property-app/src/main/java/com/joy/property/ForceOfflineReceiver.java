package com.joy.property;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.joy.common.helper.UserInfoHelper;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.login.LoginActivity;

/**
 * Created by usb on 2016/7/27.
 **/
public class ForceOfflineReceiver extends BroadcastReceiver {

    private AlertDialog alertDialog;

    /**
    * (non-Javadoc)
    *
    * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
    * android.content.Intent)
    */
    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.i("222", "收到广播: ");

        // TODO Auto-generated method stub

//        final CustomDialog dialog = new CustomDialog(context, R.layout.dialog_forceoffline, R.style.myDialogTheme);
//        Button btn_go = (Button) dialog.findViewById(R.id.btn_go);
//        btn_go.setOnClickListener((View v) -> {
//            TODO Auto-generated method stub
//                /**
//                 * 以下三行自己添的
//                 **/
//                UserInfoHelper mUserHelper = UserInfoHelper.getInstance(MainApplication.mContext);
//                mUserHelper.updateLogin(false);
//                mUserHelper.updateUser(null,context);
//                ActivityCollector.finishAll();
//                Intent intent_01 = new Intent(context, LoginActivity.class);
//            intent_01.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent_01);
//            dialog.dismiss();
//        });
//
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//
//        dialog.show();
        if (alertDialog == null) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context, R.style.myDialogThemeOffline);

//        dialogBuilder.setTitle("已下线");
//        dialogBuilder.setMessage("您的账户已在另一个设备登录,请尝试重新登陆");
            dialogBuilder.setCancelable(false);
//        dialogBuilder.setPositiveButton("登   录", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface arg0, int arg1) {
//                // TODO Auto-generated method stub
//                /**
//                 * 以下三行自己添的
//                 **/
//                UserInfoHelper mUserHelper = UserInfoHelper.getInstance(MainApplication.mContext);
//                mUserHelper.updateLogin(false);
//                mUserHelper.updateUser(null,context);
//                ActivityCollector.finishAll();
//                Log.i("222", "onClick: ");
//                Intent intent1 = new Intent(context, ForceOfflineService.class);
//                context.stopService(intent1);
//                Intent intent = new Intent(context, LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            }
//        });

            alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);

            alertDialog.show();

            alertDialog.getWindow().setContentView(R.layout.dialog_forceoffline);
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            String data = ConfigUtil.getString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
//        Log.i("2222", "onReceive: "+data);
            String message = JSONObject.parseObject(data).getString("message");
            TextView messageText = (TextView) alertDialog.getWindow().findViewById(R.id.text);
            messageText.setText(message);
            alertDialog.getWindow().findViewById(R.id.btn_go).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserInfoHelper mUserHelper = UserInfoHelper.getInstance(MainApplication.mContext);
                    mUserHelper.updateLogin(false);
                    mUserHelper.updateUser(null, context);
                    ActivityCollector.finishAll();
                    Intent intent_01 = new Intent(context, LoginActivity.class);
                    intent_01.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                    context.startActivity(intent_01);
                    alertDialog.dismiss();
                }
            });
        }

    }
}
