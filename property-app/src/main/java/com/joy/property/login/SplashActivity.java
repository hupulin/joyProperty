package com.joy.property.login;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.common.AdInfoTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.CommonApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.MainActivity;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.StatuBarUtil;
import com.joyhome.nacity.app.MainApp;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class SplashActivity extends BaseActivity {
    private BitmapUtils mUtils;
    String  adPath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
        StatuBarUtil.setStatueBarTransparent(getWindow());
        splashAd();
        com.Util.SpUtil.put(getThisContext(),"IsSplashActivity",true);//防止推送在splash出现
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

                if (!ConfigUtil.getBoolean(sp, MainApplication.KeyValue.KEY_LOGIN_SHORTCUT)) {
                    createShortCut();
                    ConfigUtil.saveBoolean(sp,MainApplication.KeyValue.KEY_LOGIN_SHORTCUT,true);
                }

                if (!checkNetworkState()) {
                    if(!TextUtils.isEmpty(ApartmentInfoHelper.getInstance(getApplication()).getSid()))
                    {

                    }
                    if (mUserHelper.isLogin()) {
                        Intent intent = new Intent(getThisContext(),MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        finish();
                    }else {
                        startActivity(new Intent(getThisContext(),LoginActivity.class));
                        finish();
                    }



                }else {
                    Toast.makeText(getThisContext(), "请检查网络或稍后再试", Toast.LENGTH_LONG).show();
                }

			}
		}, 3000);
		
		
	}
    private void splashAd() {
        ImageView splash = (ImageView) findViewById(R.id.splash);
        String apartmentSid = ApartmentInfoHelper.getInstance(getApplication()).getSid();
        System.out.println(apartmentSid);
        CommonApi api = ApiClient.create(CommonApi.class);
        api.findAdListInfoByApartmentSid("b4a87080-5b85-4438-9b39-44aa456cdf00", 12, new HttpCallback<MessageTo<List<AdInfoTo>>>(this) {
            @Override
            public void success(MessageTo<List<AdInfoTo>> msg, Response response) {

                if (msg.getSuccess() == 0) {

                    if (msg.getData() != null && msg.getData().size() > 0) {
                        adPath = MainApp.getImagePath(msg.getData().get(0).getAdImage());
                        Glide
                                .with(getApplicationContext())
                                .load(adPath)

                                .crossFade(1000) //设置淡入淡出效果，默认300ms，可以传参
                                        //.dontAnimate() //不显示动画效果
                                .into(splash);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //  super.failure(error);
            }
        });


    }
    private boolean checkNetworkState() {
        boolean flag = true;
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        flag = false;
                    }
            }
        }

        return flag;

    }

    private void createShortCut() {
        // 创建快捷方式的Intent
        Intent shortCutIntent = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重复创建
        shortCutIntent.putExtra("duplicate", false);
        // 需要实现的名称
        shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                getString(R.string.app_name));
        // 快捷图片
        Parcelable icon = Intent.ShortcutIconResource.fromContext(
                getApplicationContext(), R.drawable.ic_launcher);
        shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        Intent appIntent = new Intent();
        //下面两个属性是为了当应用程序卸载时桌面上的快捷方式会被删除
        appIntent.setAction("android.intent.action.MAIN");
        appIntent.addCategory("android.intent.category.LAUNCHER");
        String appClass = getPackageName() + ".login.SplashActivity";// 启动的Activity名称
        ComponentName comp = new ComponentName(getPackageName(), appClass);
        appIntent.setComponent(comp);
        //点击快捷图片，运行的主程序入口
        shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, appIntent);
        // 发送广播
        sendBroadcast(shortCutIntent);
    }


    public static boolean hasShortcut(Context cx){
        boolean result = false;
        // 获取当前应用名称
        String title = null;
        try {
            final PackageManager pm = cx.getPackageManager();
            title = pm.getApplicationLabel(
                    pm.getApplicationInfo(cx.getPackageName(),
                            PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }

        final String uriStr;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            uriStr = "content://com.android.launcher.settings/favorites?notify=true";
        } else {
            uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
        }
        final Uri CONTENT_URI = Uri.parse(uriStr);
        final Cursor c = cx.getContentResolver().query(CONTENT_URI, null,
                "title=?", new String[] { title }, null);
        if (c != null && c.getCount() > 0) {
            result = true;
        }
        return result;

    }


}
