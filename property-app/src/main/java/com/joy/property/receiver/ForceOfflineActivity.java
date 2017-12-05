package com.joy.property.receiver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.joy.common.helper.UserInfoHelper;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.login.LoginActivity;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.StatuBarUtil;
import com.joyhome.nacity.app.photo.util.PublicWay;

import cn.jpush.android.api.JPushInterface;

/**
 *  Created by xzz on 2017/7/26.
 **/
public class ForceOfflineActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatuBarUtil.setStatueBarTransparent(getWindow());
        setContentView(R.layout.activity_push_jump);
        PublicWay.forceOfflineActivity=this;
        offlineDialog();
    }

    public void offlineDialog(){
      if ( PublicWay.pushJumpActivity!=null) {
          PublicWay.pushJumpActivity.finish();
          PublicWay.pushJumpActivity=null;
      }
//
        JPushInterface.clearAllNotifications(this);
        CustomDialog alertDialog = new CustomDialog(getThisContext(), R.layout.dialog_forceoffline, R.style.myDialogTheme);
            alertDialog.show();
            alertDialog.setCancelable(false);
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getThisContext());

            alertDialog.findViewById(R.id.btn_go).setOnClickListener(v -> {
                UserInfoHelper mUserHelper = UserInfoHelper.getInstance(MainApplication.mContext);
                mUserHelper.updateLogin(false);
                mUserHelper.updateUser(null, getThisContext());
                com.Util.SpUtil.put(this, "IsOffLine", false);
                alertDialog.dismiss();
                Intent intent_01 = new Intent(getThisContext(), LoginActivity.class);
                intent_01.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ConfigUtil.saveString(sp, MainApplication.KeyValue.KEY_HOME_DATA, "");
                startActivity(intent_01);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                PublicWay.forceOfflineActivity=null;
            });
        }


}
