package com.joy.property.base;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.common.helper.UserInfoBulkHelper;
import com.joy.common.helper.UserInfoHelper;

import com.joy.property.MainApplication;
import com.joy.property.R;
import com.lidroid.xutils.BitmapUtils;
import com.umeng.analytics.MobclickAgent;

public class BaseFragment extends Fragment {
    protected BitmapUtils mBitmapUtils;
    protected ApartmentInfoHelper mHelper;
    protected UserInfoBulkHelper mUserHelperBulk;
    protected UserInfoHelper mUserHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBitmapUtils = new BitmapUtils(getThisContext());
        mHelper = ApartmentInfoHelper.getInstance(MainApplication.mContext);
        mUserHelper = UserInfoHelper.getInstance(MainApplication.mContext);
        mUserHelperBulk = UserInfoBulkHelper.getInstance(MainApplication.mContext);
        return super.onCreateView(inflater, container, savedInstanceState);
    }



 protected   Context getThisContext(){

     return getActivity();
 }
    protected void displayImage(ImageView iv, String imgId) {
        if(iv == null) return;
        if (TextUtils.isEmpty(imgId)) {
            return;
        }
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        mBitmapUtils.configDefaultShowOriginal(false);
        mBitmapUtils.configDefaultAutoRotation(true);
        mBitmapUtils.display(iv , MainApplication.getImagePath(imgId));
    }

    protected void displayImage(ImageView iv, String imgId, int defImageId) {
        if(iv == null) return;

        if (TextUtils.isEmpty(imgId)) {
            iv.setImageResource(defImageId);
            return;
        }
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        mBitmapUtils.configDefaultShowOriginal(false);
        mBitmapUtils.configDefaultAutoRotation(true);
        mBitmapUtils.display(iv , MainApplication.getImagePath(imgId));
    }
    @Override
    public void onResume() {
        super.onResume();

        MobclickAgent.onPageStart("PageStart");

    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageStart("PageEnd");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (mBitmapUtils != null) {
//            mBitmapUtils.clearCache();
//            mBitmapUtils.clearMemoryCache();
//            mBitmapUtils.clearDiskCache();
//        }
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
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case 2:
                // 按返回
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case 3:
                // 从下往上
                getActivity().overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top);

                break;
            case 4:
                getActivity().overridePendingTransition(R.anim.slide_in_from_top, R.anim.slide_out_to_bottom);
                break;
            case 5:
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }
}
