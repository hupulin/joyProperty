package com.joy.property.worksign.photo.zoom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;
import com.joy.property.base.EventBusEvent;
import com.joy.property.neighborhood.RefreshEvent;
import com.joyhome.nacity.app.base.BaseActivity;
import com.joyhome.nacity.app.photo.AlbumActivity;
import com.joyhome.nacity.app.photo.ImageFile;
import com.joyhome.nacity.app.photo.ShowAllPhoto;
import com.joyhome.nacity.app.photo.util.Bimp;
import com.joyhome.nacity.app.photo.util.ImageItem;
import com.joyhome.nacity.app.photo.util.PublicWay;
import com.joyhome.nacity.app.photo.zoom.ViewPagerFixed;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

@SuppressLint("SetTextI18n")
public class SignGalleryActivity extends BaseActivity {
    private Intent intent;
    // 返回按钮
    private Button back_bt;
    // 发送按钮
    private Button send_bt;
    //删除按钮
    private Button del_bt;
    //顶部显示预览图片位置的textview
    private TextView positionTextView;
    //获取前一个activity传过来的position
    private int position;
    //当前的位置
    private int location = 0;
    private TextView  mNoPic;
    private TextView  mTotalPic;


    private ArrayList<View> listViews = null;
    private ViewPagerFixed pager;
    private MyPageAdapter adapter;
    private Context mContext;
   private ImageButton mBack;
    private int num = -1;
    private String  flag = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_gallery);// 切屏到主界面
        PublicWay.activityList.add(this);
        mContext = this;
        back_bt = (Button) findViewById(R.id.gallery_back);
        send_bt = (Button) findViewById(R.id.send_button);
        del_bt = (Button) findViewById(R.id.gallery_del);
        mBack = (ImageButton) findViewById(R.id.back);
        mNoPic = (TextView) findViewById(R.id.no_pic);
        mTotalPic = (TextView) findViewById(R.id.total_pic);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.bottom_layout);
        mBack.setOnClickListener(new BackListener2());
        back_bt.setOnClickListener(new BackListener());
        send_bt.setOnClickListener(new GallerySendListener());
        del_bt.setOnClickListener(new DelListener());
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = Integer.parseInt(intent.getStringExtra("position"));
        if (position ==3) {
            back_bt.setVisibility(View.GONE);
            mBack.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        }

        num = getIntent().getIntExtra("number", 0);
        flag = getIntent().getStringExtra("flag");
        isShowOkBt();
        if (Bimp.tempSelectBitmap.size() >0) {
            mTotalPic.setText(Bimp.tempSelectBitmap.size()+"");
            mNoPic.setText("1");
        }

        
        // 为发送按钮设置文字
        pager = (ViewPagerFixed) findViewById(R.id.gallery01);
        pager.addOnPageChangeListener(pageChangeListener);
        for (ImageItem item : Bimp.tempSelectBitmap) {
            initListViews(item);
        }

        adapter = new MyPageAdapter(listViews);
        pager.setAdapter(adapter);
        pager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.ui_10_dip));
        int id = intent.getIntExtra("ID", 0);
        pager.setCurrentItem(id);
    }

    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

        public void onPageSelected(int arg0) {
            location = arg0;
            mNoPic.setText((arg0+1)+"");
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void initListViews(ImageItem item) {
        if (listViews == null)
            listViews = new ArrayList<>();
        PhotoView img = new PhotoView(this);
        img.setBackgroundColor(0xff000000);
        if (item.getImagePath().contains("storage")) {
            img.setImageBitmap(item.getBitmap());
        }else {
            displayImage(img,item.getImagePath());
        }

        img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        listViews.add(img);
    }
    private class BackListener2 implements OnClickListener {
        @Override
        public void onClick(View v) {
//            if (TextUtils.equals(flag,"chat")) {
//                intent = new Intent(GalleryActivity.this, PostActivity.class);
//            }else if (TextUtils.equals(flag,"sale")) {
//                intent = new Intent(GalleryActivity.this, PublishSaleHouseActivity.class);
//            }else if (TextUtils.equals(flag,"whole")) {
//                intent = new Intent(GalleryActivity.this, PublishRentalHouseActivity.class);
//            }else if (TextUtils.equals(flag,"roommate")) {
//                intent = new Intent(GalleryActivity.this, PublishRequirementsActivity.class);
//            }else if (TextUtils.equals(flag,"repair")) {
//                intent = new Intent(GalleryActivity.this, HomeServiceListActivity.class);
//            }else if (TextUtils.equals(flag,"praise")) {
//                intent = new Intent(GalleryActivity.this, PraiseListActivity.class);
//            }else if (TextUtils.equals(flag ,"complaints")) {
//                intent = new Intent(GalleryActivity.this, CallPropertyActivity.class);
//            }
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
            intent = new Intent();
            setResult(Activity.RESULT_OK);
            PublicWay.activityList.clear();
            finish();
        }
    }

    // 返回按钮添加的监听器
    private class BackListener implements OnClickListener {

        public void onClick(View v) {
            intent = new Intent(SignGalleryActivity.this, ImageFile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    // 删除按钮添加的监听器
    private class DelListener implements OnClickListener {

        public void onClick(View v) {
            if (listViews.size() == 1) {
                Bimp.tempSelectBitmap.clear();
                Bimp.max = 0;
                send_bt.setText(getString(R.string.finish) + "(" + Bimp.tempSelectBitmap.size() + "/" + num + ")");
                Intent intent1 = new Intent("data.broadcast.action");
                sendBroadcast(intent1);
//                if (TextUtils.equals(flag,"chat")) {
//                    intent = new Intent(GalleryActivity.this, PostActivity.class);
//                }else if (TextUtils.equals(flag,"sale")) {
//                    intent = new Intent(GalleryActivity.this, PublishSaleHouseActivity.class);
//                }else if (TextUtils.equals(flag,"whole")) {
//                    intent = new Intent(GalleryActivity.this, PublishRentalHouseActivity.class);
//                }else if (TextUtils.equals(flag,"roommate")) {
//                    intent = new Intent(GalleryActivity.this, PublishRequirementsActivity.class);
//                }else if (TextUtils.equals(flag,"praise")) {
//                    intent = new Intent(GalleryActivity.this, PraiseListActivity.class);
//                }else if (TextUtils.equals(flag,"praise")) {
//                    intent = new Intent(GalleryActivity.this, PraiseListActivity.class);
//                }else if (TextUtils.equals(flag ,"complaints")) {
//                    intent = new Intent(GalleryActivity.this, CallPropertyActivity.class);
//                }
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK);
                PublicWay.activityList.clear();
                EventBus.getDefault().post(new EventBusEvent<>("DeleteAllPicture",location));
                finish();
            } else {
                EventBus.getDefault().post(new EventBusEvent<>("DeletePosition",location));
                Bimp.tempSelectBitmap.remove(location);
                Bimp.max--;
                pager.removeAllViews();
                listViews.remove(location);
                adapter.setListViews(listViews);
                mTotalPic.setText(Bimp.tempSelectBitmap.size()+"");
                send_bt.setText(getString(R.string.finish) + "(" + Bimp.tempSelectBitmap.size() + "/" + num + ")");
                adapter.notifyDataSetChanged();
            }
        }
    }

    // 完成按钮的监听
    private class GallerySendListener implements OnClickListener {
        public void onClick(View v) {
            finish();
//			intent.setClass(mContext,MainActivity.class);
//			startActivity(intent);
        }

    }

    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            send_bt.setText(getString(R.string.finish) + "(" + Bimp.tempSelectBitmap.size() + "/" + num + ")");
            send_bt.setPressed(true);
            send_bt.setClickable(true);
            send_bt.setTextColor(Color.WHITE);
        } else {
            send_bt.setPressed(false);
            send_bt.setClickable(false);
            send_bt.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }




    class MyPageAdapter extends PagerAdapter {

        private ArrayList<View> listViews;

        private int size;

        public MyPageAdapter(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public void setListViews(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public int getCount() {
            return size;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(listViews.get(position % size));
        }


        @Override
        public void finishUpdate(ViewGroup container) {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            try {
                container.addView(listViews.get(position % size), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listViews.get(position % size);
        }


        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode()==KeyEvent.KEYCODE_BACK){
            intent = new Intent();
            setResult(Activity.RESULT_OK);
            PublicWay.activityList.clear();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
