package com.joy.property.base;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.common.helper.UserInfoBulkHelper;
import com.joy.common.helper.UserInfoHelper;

import com.joy.property.ActivityCollector;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.receiver.ForceOfflineActivity;
import com.joy.property.utils.SpUtil;
import com.joy.property.utils.StatuBarUtil;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.lidroid.xutils.BitmapUtils;
import com.umeng.analytics.MobclickAgent;

import java.text.DecimalFormat;

public class BaseActivity extends FragmentActivity {
    private static String LAG = "BaseActivity";
    protected BitmapUtils mBitmapUtils;
    protected ApartmentInfoHelper mHelper;
    protected UserInfoBulkHelper mUserHelperBulk;
    protected UserInfoHelper mUserHelper ;
    protected ImageView mBack;
    protected SharedPreferences sp;
    protected String apartmentSid ;
    protected DecimalFormat df = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mBitmapUtils = new BitmapUtils(getThisContext());
        mHelper = ApartmentInfoHelper.getInstance(getApplication());
        mUserHelper = UserInfoHelper.getInstance(getApplication());
        mUserHelperBulk = UserInfoBulkHelper.getInstance(getApplication());
        sp = PreferenceManager.getDefaultSharedPreferences(getThisContext());
        apartmentSid= SpUtil.getString(getThisContext(), "NeighborApartmentSid");
        MobclickAgent.setDebugMode(true);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setCheckDevice(false);
        ActivityCollector.addActivity(this);
        StatuBarUtil.setStatueBarBlueColor(getWindow());

    }

    protected Context getThisContext() {
        return BaseActivity.this;
    }


    public void displayImage(ImageView imageView, String uri) {

        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(uri)) {
            return;
        }
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        mBitmapUtils.configDefaultShowOriginal(false);
        mBitmapUtils.configDefaultAutoRotation(true);
        mBitmapUtils.display(imageView, MainApplication.getImagePath(uri));
    }


    public void displayImage(ImageView imageView, String uri, int StringId) {

        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(uri)) {
            imageView.setImageResource(StringId);
            return;
        }
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        mBitmapUtils.configDefaultShowOriginal(false);
        mBitmapUtils.configDefaultAutoRotation(true);
        mBitmapUtils.display(imageView, MainApplication.getImagePath(uri));
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(toPageName());
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(toPageName());
        MobclickAgent.onPause(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d(LAG, "-----onTrimMemory-----");
        super.onTrimMemory(level);


    }

    @Override
    public void onLowMemory() {
        Log.d(LAG, "-----onLowMemory-----");
        super.onLowMemory();
    }

    @Override
    public void onBackPressed() {

        finish();
        super.onBackPressed();

    }

    protected String toPageName() {
        return getClass().getSimpleName();
    }
    public int getScreenWidthPixels(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
    public void goToAnimation(int type){

        switch (type){
            case 1:
                // 跳转到下一个界面
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
                break;
            case 2:
                // 按返回
                overridePendingTransition(R.anim.slide_in_left ,R.anim.slide_out_right);
                break;
            case 3:
                // 从下往上
                overridePendingTransition(R.anim.slide_in_from_bottom ,R.anim.slide_out_to_top);

                break;
            case 4:
                overridePendingTransition(R.anim.slide_in_from_top ,R.anim.slide_out_to_bottom);
                break;
            case 5:
                overridePendingTransition(R.anim.fade_in ,R.anim.fade_out);
                break;
        }
    }
    public void ToastShowLong(Context context , String showContext){
        Toast.makeText(context, showContext, Toast.LENGTH_LONG).show();
    }
    public int getScreenWidth(){
        WindowManager wm = (WindowManager)getThisContext().getSystemService(WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }

    @Override
    protected void onRestart() {
        if (com.Util.SpUtil.getBoolean(getThisContext(),"IsOffLine")){
            if (PublicWay.forceOfflineActivity==null) {
                Intent intent1 = new Intent(getThisContext(), ForceOfflineActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
//                goToAnimation(2);
            }
        }
        super.onRestart();
    }

    protected void showSignNetError(){
        Toast.makeText(getThisContext(), "网络异常", Toast.LENGTH_LONG).show();
    }

    protected String getDeviceUid(){
        return ((TelephonyManager) getThisContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }
}
