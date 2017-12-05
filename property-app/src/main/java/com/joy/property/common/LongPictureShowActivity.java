package com.joy.property.common;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.ActivityBarColorUtil;

import com.joy.property.R;
import com.joy.property.common.adapter.LongImagePagerAdapter;
import com.joy.property.utils.ZoomViewPager;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.base.BaseActivity;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Admin on 2014-12-18
 */


public class LongPictureShowActivity extends BaseActivity
        implements OnPageChangeListener, OnClickListener {
    private ZoomViewPager mViewPager;
    private String path = "";
    private TextView mPicNo;
    private TextView mTotalPic;
    private List<String> list = new ArrayList<>();
    private int index = 0;
    private String uri = "";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(getThisContext(),"图片保存在"+msg.obj,Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_picture_show);
        findById();
        initIntentDatas();
        initDatas();

    }


    private void findById() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        mViewPager = (ZoomViewPager) findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(this);
        mPicNo = (TextView) findViewById(R.id.pic_no);
        mTotalPic = (TextView) findViewById(R.id.total_pic);
  findViewById(R.id.save_ic).setOnClickListener(this);
    }


    private void initIntentDatas() {
        index = getIntent().getIntExtra("index", 0);
        path = getIntent().getStringExtra("path");
    }


    private void initDatas() {
      String[]  mImagePath = path.split(";");
        mTotalPic.setText( String.valueOf(mImagePath.length));
        for (String p : mImagePath) {

            list.add(p);
        }
        mViewPager.setAdapter(new LongImagePagerAdapter(list));
        mViewPager.setCurrentItem(index);
       uri = mImagePath[mViewPager.getCurrentItem()];

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        mPicNo.setText(String.valueOf(position + 1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.clearOnPageChangeListeners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:

                onBackPressed();
                overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_right);
                break;
            case R.id.save_ic:
                savePicture(uri);
                break;
        }
    }

    private void savePicture(final String url) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                String  urlPath = MainApp.DefaultValue.IMAGE_URI + url;
                try {
                    URL  mURL = new URL(urlPath);
                    HttpURLConnection httpUrlConnection = (HttpURLConnection) mURL.openConnection();
                    httpUrlConnection.setConnectTimeout(5000);
                    httpUrlConnection.setRequestMethod("POST");
                    httpUrlConnection.setDoOutput(true);
                    httpUrlConnection.setDoInput(true);
                    httpUrlConnection.setUseCaches(false);
                    httpUrlConnection.connect();
                    InputStream inputStream = null;
                    if (httpUrlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                         inputStream = httpUrlConnection.getInputStream();
                        if (inputStream != null) {
                            String fileName = String.valueOf(System.currentTimeMillis());

                                File f = new File(Environment.getExternalStorageDirectory() + "/" + "joyhome/",fileName +".jpg");
                            if (!f.getParentFile().exists()) {
                                f.getParentFile().mkdirs();
                            }

                            File file = new File(Environment.getExternalStorageDirectory() + "/" + "joyhome/",
                                     fileName + ".jpg");
                            String savePath = file.getAbsolutePath();
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            byte[] buffer = new byte[1024 * 8];
                            int ch = 0;
                            while ((ch = inputStream.read(buffer, 0, 1024 * 8)) != -1) {
                                fileOutputStream.write(buffer, 0, ch);
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            inputStream.close();
                            httpUrlConnection.disconnect();
                            Message msg = new Message();
                            msg.obj = savePath;
                            mHandler.sendMessage(msg);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();
//        new Thread() {
//            public void run() {
//               String  urlPath = MainApp.DefaultValue.IMAGE_URI + url;
//
//
//                HttpClient httpClient = new DefaultHttpClient();
//                HttpGet httpGet = new HttpGet(urlPath);
//                HttpResponse response;
//                try {
//                    response = httpClient.execute(httpGet);
//                    HttpEntity httpEntity = response.getEntity();
//                    InputStream inputStream = httpEntity.getContent();
//                    FileOutputStream fileOutputStream = null;
//                    if (inputStream != null) {
//                        File file = new File(Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DCIM,
//                                    DateUtil.getDateString("yyyyMMddHHmmss") + ".jpg");
//                        String savePath = file.getAbsolutePath();
//                        fileOutputStream = new FileOutputStream(file);
//                        byte[] buffer = new byte[1024*8];
//                        int ch = 0;
//                        while ((ch = inputStream.read(buffer,0,1024*8)) != -1) {
//                            fileOutputStream.write(buffer, 0, ch);
//                        }
//                        fileOutputStream.flush();
//                        if (fileOutputStream != null) {
//                            fileOutputStream.close();
//                        }
//                        if (inputStream != null) {
//                            inputStream.close();
//                        }
//
//                        Message msg = new Message();
//                        msg.obj = savePath;
//                         mHandler.sendMessage(msg);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

     }

}
