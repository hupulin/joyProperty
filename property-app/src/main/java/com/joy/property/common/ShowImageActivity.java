package com.joy.property.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.joy.property.R;

import com.joy.library.utils.ZoomImageView;
import com.joy.property.base.BaseActivity;


/**
 * Created by Admin on 2015-01-28
 */
public class ShowImageActivity extends BaseActivity implements OnClickListener {


    private ZoomImageView mZoomImageView;
    private String mPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        findById();
        initIntentDatas();
        initDatas();

    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        ImageButton  mDelete = (ImageButton) findViewById(R.id.image_delete);
        mDelete.setOnClickListener(this);
        mZoomImageView = (ZoomImageView) findViewById(R.id.zoomImage);
    }

    private void initIntentDatas() {
        mPath = getIntent().getStringExtra("path");

    }

    private void initDatas() {
        if (mPath.contains("storage")) {
            Bitmap bitmap = BitmapFactory.decodeFile(mPath);
            mZoomImageView.setImageBitmap(bitmap);

        }else {
           displayImage(mZoomImageView ,mPath);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.image_delete:
                Intent intent = new Intent();
                intent.putExtra("path",mPath);
                setResult(1014,intent);
                finish();
                break;
        }
    }
}
