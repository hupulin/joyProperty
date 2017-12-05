package com.joyhome.nacity.app.base;


import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
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
import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

import java.text.DecimalFormat;


/**
 * Created by Admin on 2014-12-23
 */
public class BaseActivity extends FragmentActivity implements
        ComponentCallbacks2 {

    protected BitmapUtils mBitmapUtils;
    protected ApartmentInfoHelper mHelper;
    protected UserInfoHelper mUserHelper;
    protected UserInfoBulkHelper mUserHelperBulk;

    protected SharedPreferences sp;
    protected ImageView mBack;
    protected DecimalFormat df = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        mHelper = ApartmentInfoHelper.getInstance(this.getApplication());
        mUserHelper = UserInfoHelper.getInstance(this.getApplication());
        mUserHelperBulk = UserInfoBulkHelper.getInstance(this.getApplication());



        MobclickAgent.setDebugMode(true);
        MobclickAgent.openActivityDurationTrack(false);
       // MobclickAgent.updateOnlineConfig(this);
        MobclickAgent.setCheckDevice(false);
    }

    protected void displayImage(ImageView iv, String imgId) {
        if (iv == null) return;
        if (TextUtils.isEmpty(imgId)) {
            return;
        }
        mBitmapUtils = new BitmapUtils(this.getApplication());
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        mBitmapUtils.configDefaultShowOriginal(false);
        mBitmapUtils.configDefaultAutoRotation(true);
        mBitmapUtils.display(iv, MainApp.getImagePath(imgId));
    }

    protected Context getThisContext() {
        return BaseActivity.this;
    }

    protected void displayImage(ImageView iv, String imgId, int defImageId) {
        if (iv == null) return;

        if (TextUtils.isEmpty(imgId)) {
            iv.setImageResource(defImageId);
            iv.setBackgroundResource(defImageId);
            return;
        }
        iv.setBackgroundResource(0);
        mBitmapUtils = new BitmapUtils(this);
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        mBitmapUtils.configDefaultShowOriginal(false);
        mBitmapUtils.configDefaultAutoRotation(true);
        mBitmapUtils.display(iv, MainApp.getImagePath(imgId));
    }


    @Override
    protected void onResume() {

        MobclickAgent.onPageStart(toPageName());
        MobclickAgent.onResume(this.getApplication());
        super.onResume();

    }

    @Override
    protected void onPause() {

        MobclickAgent.onPageEnd(toPageName());
        MobclickAgent.onPause(this.getApplication());
        super.onPause();

    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:

                ImageLoader.getInstance().clearMemoryCache();
                Log.d("-onTrimMemory-20--", "do release memory");
                break;
            case TRIM_MEMORY_COMPLETE:
                Log.d("-onTrimMemory-80--", "do release memory");
                ImageLoader.getInstance().clearMemoryCache();
                break;
            case TRIM_MEMORY_BACKGROUND:
                Log.d("-onTrimMemory-40--", "do release memory");
                ImageLoader.getInstance().clearMemoryCache();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }



    protected String toPageName() {
        return getClass().getSimpleName();
    }


    public void ToastShowLong(Context context , String showContext){
        Toast.makeText(context , showContext,Toast.LENGTH_LONG).show();
    }

    public void goToAnimation(int type){

      switch (type){
          case 1:
              // 跳转到下一个界面
              overridePendingTransition(R.anim.slide_in_right ,R.anim.slide_out_left);
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
    public int getScreenWidthPixels(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
    public int getScreenWidth(){
        WindowManager wm = (WindowManager)getThisContext().getSystemService(WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }
}
