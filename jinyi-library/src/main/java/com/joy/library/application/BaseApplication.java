package com.joy.library.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;


public class BaseApplication extends Application {
    public static final String TAG = "GCoderApplication";
    public static SharedPreferences mPref;
    private Context mContext;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    private static BaseApplication mInstance = null;

    public static BaseApplication getmInstance() {
        if (mInstance == null) {
            mInstance = new BaseApplication();

        }
        return mInstance;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mContext = this.getApplicationContext();
        mPref = PreferenceManager.getDefaultSharedPreferences(this);

    }

    public static String getImagePath(String path) {
        if (path == null)
            return "";

        return DefaultValue.IMAGE_URI + path;

    }

    public static class DefaultValue {
        public static final String IMAGE_URI = "http://ihome-image.qiniudn.com/";
    }

    public String getVersion() {
        try {
            PackageManager manager = this.getApplicationContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getApplicationContext().getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
