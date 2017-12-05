package com.joyhome.nacity.app.photo;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.joy.property.R;
import com.joyhome.nacity.app.base.BaseActivity;
import com.joyhome.nacity.app.photo.adapter.AlbumGridViewAdapter;
import com.joyhome.nacity.app.photo.util.AlbumHelper;
import com.joyhome.nacity.app.photo.util.Bimp;
import com.joyhome.nacity.app.photo.util.ImageBucket;
import com.joyhome.nacity.app.photo.util.ImageItem;
import com.joyhome.nacity.app.photo.util.PublicWay;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("SetTextI18n")
public class AlbumActivity extends BaseActivity {
    //显示手机里的所有图片的列表控件
    private GridView gridView;
    //当手机里没有图片时，提示用户没有图片的控件
    private TextView tv;
    //gridView的adapter
    private AlbumGridViewAdapter gridImageAdapter;
    //完成按钮
    private Button okButton;
    // 返回按钮
    private Button back;
    // 取消按钮
    private Button cancel;
    private Intent intent;
    // 预览按钮
    private Button preview;
    private Context mContext;
    private ArrayList<ImageItem> dataList;
    private AlbumHelper helper =null;
    public static List<ImageBucket> contentList;
    public static Bitmap bitmap = null;
    private int num = 0;
    private String flag = "";
    private String type = "";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_album);
        PublicWay.activityList.add(this);
        mContext = this;
        //注册一个广播，这个广播主要是用于在GalleryActivity进行预览时，
        // 防止当所有图片都删除完后，再回到该页面时被取消选中的图片仍处于选中状态
        IntentFilter filter = new IntentFilter("data.broadcast.action");
        registerReceiver(broadcastReceiver, filter);
       bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plugin_camera_no_pictures);
        num = getIntent().getIntExtra("number", 0);
        flag = getIntent().getStringExtra("flag");
        type = getIntent().getStringExtra("type");

        init();
        initListener();
        //这个函数主要用来控制预览和完成按钮的状态
        isShowOkBt();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            gridImageAdapter.notifyDataSetChanged();
        }
    };

    // 预览按钮的监听
    private class PreviewListener implements OnClickListener {
        public void onClick(View v) {
            if (Bimp.tempSelectBitmap.size() > 0) {
                intent.putExtra("position", "1");
                intent.setClass(AlbumActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
        }

    }

    // 完成按钮的监听
    private class AlbumSendListener implements OnClickListener {
        public void onClick(View v) {
            intent = new Intent();
            setResult(RESULT_OK);
            PublicWay.activityList.clear();
            finish();

        }

    }

    // 返回按钮监听
    private class BackListener implements OnClickListener {
        public void onClick(View v) {
            intent = new Intent(AlbumActivity.this, ImageFile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("number", num);
            intent.putExtra("flag", flag);
            intent.putExtra("type", type);
            startActivity(intent);
        }
    }

    // 取消按钮的监听
    private class CancelListener implements OnClickListener {
        public void onClick(View v) {
            // Bimp.tempSelectBitmap.clear();
//            if (TextUtils.equals(flag, "chat")) {
//                intent = new Intent(AlbumActivity.this, PostActivity.class);
//
//            } else if (TextUtils.equals(flag, "whole")) {
//                intent = new Intent(AlbumActivity.this, PublishRentalHouseActivity.class);
//
//            } else if (TextUtils.equals(flag, "sale")) {
//                intent = new Intent(AlbumActivity.this, PublishSaleHouseActivity.class);
//
//            } else if (TextUtils.equals(flag, "roommate")) {
//                intent = new Intent(AlbumActivity.this, PublishRequirementsActivity.class);
//
//            } else if (TextUtils.equals(flag, "repair")) {
//                intent = new Intent(AlbumActivity.this, HomeServiceListActivity.class);
//            } else if (TextUtils.equals(flag, "praise")) {
//                intent = new Intent(AlbumActivity.this, PraiseListActivity.class);
//
//            }else if (TextUtils.equals(flag ,"complaints")) {
//                intent = new Intent(AlbumActivity.this, CallPropertyActivity.class);
//
//            }
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
            intent = new Intent();
            setResult(RESULT_OK);
            PublicWay.activityList.clear();
            finish();
        }
    }


    // 初始化，给一些对象赋值

    private void init() {
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        contentList = helper.getImagesBucketList(false);
        dataList = new ArrayList<>();
        for (int i = 0; i < contentList.size(); i++) {
            dataList.addAll(contentList.get(i).imageList);
        }

        back = (Button) findViewById(R.id.back);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new CancelListener());
        back.setOnClickListener(new BackListener());
        preview = (Button) findViewById(R.id.preview);
        preview.setOnClickListener(new PreviewListener());
        preview.setVisibility(View.GONE);
        intent = getIntent();
        //Bundle bundle = intent.getExtras();
        gridView = (GridView) findViewById(R.id.myGrid);
        gridImageAdapter = new AlbumGridViewAdapter(this, dataList, Bimp.tempSelectBitmap);
        gridView.setAdapter(gridImageAdapter);
        tv = (TextView) findViewById(R.id.myText);
        gridView.setEmptyView(tv);
        okButton = (Button) findViewById(R.id.ok_button);
        okButton.setText(getString(R.string.finish) + "(" + Bimp.tempSelectBitmap.size() + "/" + num + ")");
    }

    private void initListener() {

        gridImageAdapter
                .setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(final ToggleButton toggleButton,
                                            int position, boolean isChecked, Button chooseBt) {
                        if (Bimp.tempSelectBitmap.size() >= num) {
                            toggleButton.setChecked(false);
                            chooseBt.setVisibility(View.GONE);
                            if (!removeOneData(dataList.get(position))) {
                                Toast.makeText(AlbumActivity.this, "最多能上传" + num + "张图片",
                                        Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                        if (isChecked) {
                            chooseBt.setVisibility(View.VISIBLE);
                            Bimp.tempSelectBitmap.add(dataList.get(position));
                            okButton.setText(getString(R.string.finish) + "(" + Bimp.tempSelectBitmap.size() + "/" + num + ")");
                        } else {
                            Bimp.tempSelectBitmap.remove(dataList.get(position));
                            chooseBt.setVisibility(View.GONE);
                            okButton.setText(getString(R.string.finish) + "(" + Bimp.tempSelectBitmap.size() + "/" + num + ")");
                        }
                        isShowOkBt();
                    }
                });

        okButton.setOnClickListener(new AlbumSendListener());

    }

    private boolean removeOneData(ImageItem imageItem) {
        if (Bimp.tempSelectBitmap.contains(imageItem)) {
            Bimp.tempSelectBitmap.remove(imageItem);
            okButton.setText(getString(R.string.finish) + "(" + Bimp.tempSelectBitmap.size() + "/" + num + ")");
            return true;
        }
        return false;
    }

    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            okButton.setText(getString(R.string.finish) + "(" + Bimp.tempSelectBitmap.size() + "/" + num + ")");
            preview.setPressed(true);
            okButton.setPressed(true);
            preview.setClickable(true);
            okButton.setClickable(true);
            okButton.setTextColor(Color.WHITE);
            preview.setTextColor(Color.WHITE);
        } else {
            okButton.setText(getString(R.string.finish) + "(" + Bimp.tempSelectBitmap.size() + "/" + num + ")");
            preview.setPressed(false);
            preview.setClickable(false);
            okButton.setPressed(false);
            okButton.setClickable(false);
            okButton.setTextColor(Color.parseColor("#E1E0DE"));
            preview.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//			intent.setClass(AlbumActivity.this, ImageFile.class);
//			startActivity(intent);
            finish();
        }
        return false;

    }

    @Override
    protected void onPause() {
        super.onPause();
        IntentFilter filter = new IntentFilter("data.broadcast.action");
        registerReceiver(broadcastReceiver, filter);
        mContext.unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onRestart() {
        isShowOkBt();
        super.onRestart();
    }
}
