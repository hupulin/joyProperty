package com.joy.property.neighborhood;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.common.widget.UploadImage;

import com.joy.property.R;
import com.joy.property.neighborhood.fragment.NeighborFragment;
import com.joy.property.utils.CustomDialog;
import com.joy.property.base.BaseActivity;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * Created by xz on 2016/9/26.
 **/

public class MyNeighborCircleActivity extends BaseActivity implements View.OnClickListener {
    private List<NeighborFragment> fragmentList=new ArrayList<>();
    private View moveLine;
    private View moveLayout;
    private PopupWindow mPopupWindow;
    private RelativeLayout neighborShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_neighbor_circle);
        initData();
        initView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pageChangeListener);
        moveLine = findViewById(R.id.moveLine);
        moveLayout = findViewById(R.id.moveLayout);
        neighborShare = (RelativeLayout) findViewById(R.id.neighborShare);
        neighborShare.setOnClickListener(this);
    }
    private void initData(){
        for (int i=0;i<4;i++){
            NeighborFragment fragment=new NeighborFragment(new TextView(getThisContext()),null,getThisContext());
            fragment.setOnContinuePayClickListener(moveLayout::setY);
            fragmentList.add(fragment);
        }
//        fragmentList.add(new NeighborFragment());
//        fragmentList.add(new NeighborFragment());
//        fragmentList.add(new NeighborFragment());
//        fragmentList.add(new NeighborFragment());

    }

    private FragmentPagerAdapter adapter =new FragmentPagerAdapter(getSupportFragmentManager()){




        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {

            return 4;
        }
    };
    private ViewPager.OnPageChangeListener pageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(positionOffsetPixels / 4!=0)
        moveLine.setX((float) ((float) (getScreenWidth() * 0.0958)+position*(getScreenWidth()*0.202) + positionOffsetPixels *0.202));
            System.out.println(positionOffsetPixels/4);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    public void showPopupWindow(){
        View popupView = getLayoutInflater().inflate(R.layout.popup_neighbor_share, null);
        mPopupWindow = new PopupWindow(popupView, (int) (getScreenWidth() * 0.2277), LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.expand);

        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));


        mPopupWindow.showAsDropDown(neighborShare, (int) (getScreenWidth() * 0.7611), 0);
        TextView share = (TextView) popupView.findViewById(R.id.share);
     //   TextView report = (TextView) popupView.findViewById(R.id.report);
        share.setOnClickListener(v -> {
            share.setBackgroundColor(Color.parseColor("#f5f5f5"));
        //    report.setBackgroundColor(Color.parseColor("#ffffff"));
mPopupWindow.dismiss();
            shareShow();
        });

//report.setOnClickListener(v -> {
//    report.setBackgroundColor(Color.parseColor("#f5f5f5"));
//    share.setBackgroundColor(Color.parseColor("#ffffff"));
//    mPopupWindow.dismiss();
//    startActivity(new Intent(getThisContext(),NeighborReportActivity.class));
//});
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.neighborShare:

            showPopupWindow();
                break;
        }
    }
    private void shareShow() {

//        final CustomDialog dialog = new CustomDialog(getThisContext(),
//                R.layout.share_apk, R.style.myDialogTheme);

//        ImageView ivCancel = (ImageView) dialog.findViewById(R.id.iv_cancel);
//        TextView mWeChat = (TextView) dialog.findViewById(R.id.wechat);
//        TextView mQQ = (TextView) dialog.findViewById(R.id.QQ);
//        final TextView mMsg = (TextView) dialog.findViewById(R.id.short_msg);
//        final TextView mMoment = (TextView) dialog.findViewById(R.id.moment);
//        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.share_ic);
//        ivCancel.setOnClickListener(view -> dialog.dismiss());
//        ShareSDK.initSDK(getThisContext());
//        mWeChat.setOnClickListener(v -> {
//            Wechat.ShareParams wsp = new Wechat.ShareParams();
//            wsp.setShareType(Platform.SHARE_IMAGE);
//            wsp.setImageData(bitmap);
//            wsp.setTitle("悦嘉家，等你回家");
//            Platform plat = ShareSDK.getPlatform(getThisContext(), Wechat.NAME);
//            plat.setPlatformActionListener(NavigationFragment.getInstance());
//            plat.share(wsp);
//        });
//        mQQ.setOnClickListener(v -> {
//            QQ.ShareParams shareParams = new QQ.ShareParams();
//            shareParams.setShareType(Platform.SHARE_IMAGE);
//            shareParams.setTitle("悦嘉家，等你回家");
//            shareParams.setImagePath(UploadImage.getInstance(getThisContext()).saveBitmap(bitmap));
//            Platform qq = ShareSDK.getPlatform(MyNeighborCircleActivity.this, QQ.NAME);
//            qq.share(shareParams);
//        });
//
//        mMsg.setOnClickListener(v -> {
//            ShortMessage.ShareParams  shareParams = new ShortMessage.ShareParams();
//            shareParams.setShareType(Platform.SHARE_TEXT);
//            shareParams.setText("悦嘉家，等你回家！http://dwz.cn/11N1DJ");
//            Platform msg = ShareSDK.getPlatform(getThisContext(), ShortMessage.NAME);
//            msg.setPlatformActionListener(MyNeighborCircleActivity.this);
//            msg.share(shareParams);
//        });
//        mMoment.setOnClickListener(v -> {
//            WechatMoments.ShareParams shareParams = new WechatMoments.ShareParams();
//            shareParams.setShareType(Platform.SHARE_IMAGE);
//            shareParams.setImageData(bitmap);
//            shareParams.setTitle("悦嘉家，等你回家");
//            Platform moment = ShareSDK.getPlatform(getThisContext(), WechatMoments.NAME);
//            moment.setPlatformActionListener(MyNeighborCircleActivity.this);
//            moment.share(shareParams);
//        });
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
    }

//    @Override
//    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//
//    }
//
//    @Override
//    public void onError(Platform platform, int i, Throwable throwable) {
//
//    }
//
//    @Override
//    public void onCancel(Platform platform, int i) {

//    }
}
