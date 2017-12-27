package com.joy.property.visit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jinyi.ihome.module.express.ExpressNewTo;
import com.jinyi.ihome.module.express.ExpressRecordTo;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.common.LongPictureShowActivity;
import com.joy.property.common.PictureShowActivity;
import com.joy.property.utils.flowlayout.TagFlowLayout;
import com.joy.property.views.ShangshabanChangeTextSpaceView;
import com.joy.property.visit.view.MyImageView;
import com.squareup.picasso.Picasso;
import com.wefika.flowlayout.FlowLayout;

/**
 *  Created by usb on 2017/8/1.
 */

public class ExpressInputDetailActivity extends BaseActivity implements View.OnClickListener {
    private ExpressNewTo mExpressRecordTo;
    private FlowLayout   flowLayoutInput;
    private View   divideLine;
    private FlowLayout   flowLayoutReceive;
    private ImageView    expressIcon;
    private TextView     apartmentName;
    private TextView     phoneNo;

    private TextView expressName;
    private TextView tvReceive;
    private TextView expressNo;
    private TextView inputTime;
    private TextView receiveTime;
    private TextView remark;
    private TextView name;
    private String type;
    private LinearLayout llName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_input_detail);
        findById();
        initIntentDatas();
        initDatas();
    }
    private void initIntentDatas() {
        mExpressRecordTo = (ExpressNewTo) getIntent().getSerializableExtra("mode");
        type=getIntent().getStringExtra("type");
        if(TextUtils.isEmpty(mExpressRecordTo.getExpressImages())){
            divideLine.setVisibility(View.GONE);
        }if("1".equals(type)){
            llName.setVisibility(View.GONE);
        }else if("2".equals(type)){
            llName.setVisibility(View.VISIBLE);
        }if("1".equals(type)){
            remark.setVisibility(View.GONE);
        }else if("2".equals(type)){
            remark.setVisibility(View.VISIBLE);
            remark.setText("取件密码："+mExpressRecordTo.getExpressRemark());
        }if(mExpressRecordTo.getModifiedOn()==null||mExpressRecordTo.getModifiedOn().equals(mExpressRecordTo.getCreatedOn())){
            Log.i("数据", "还没领取时间: ");
            receiveTime.setVisibility(View.GONE);
        }else{
            Log.i("数据", "有领取时间: ");
            receiveTime.setVisibility(View.VISIBLE);
        }if(TextUtils.isEmpty(mExpressRecordTo.getReceivePhoto())){
            tvReceive.setVisibility(View.GONE);
            flowLayoutReceive.setVisibility(View.GONE);
        }
      //判断为空字段来显示view
    }
    private void initDatas() {
        if(mExpressRecordTo.getExpressIcon()!=null){
            Picasso.with(this).load(MainApp.getPicassoImagePath(mExpressRecordTo.getExpressIcon()+".png")).placeholder(R.drawable.bg_express).into(expressIcon);
        }
        if(mExpressRecordTo.getExpressNo()!=null){
            expressNo.setText("快递单号："+mExpressRecordTo.getExpressNo());
        }if(mExpressRecordTo.getCreatedOn()!=null){
            inputTime.setText("代收时间："+mExpressRecordTo.getCreatedOn());
        }if(mExpressRecordTo.getExpressPhone()!=null){
            phoneNo.setText("手机号码："+mExpressRecordTo.getExpressPhone());
        }if(mExpressRecordTo.getExpressCompany()!=null){
            expressName.setText(mExpressRecordTo.getExpressCompany());
        }if(mExpressRecordTo.getApartmentName()!=null){
            apartmentName.setText(mExpressRecordTo.getApartmentName());
        }if(TextUtils.isEmpty(mExpressRecordTo.getUserName())){
            Log.i("数据", "暂无: ");
            name.setText("(暂无)");
        }else{
            Log.i("数据", "名字: "+mExpressRecordTo.getUserName());
            name.setText(mExpressRecordTo.getUserName());
        }

        receiveTime.setText("领取时间："+mExpressRecordTo.getModifiedOn());
        //显示领取图片
        if (!TextUtils.isEmpty(mExpressRecordTo.getReceivePhoto())) {

            String[] path = mExpressRecordTo.getReceivePhoto().replaceAll(",",";").split(";");
            double W = getScreenWidthPixels(this)/720.0;
            double m= 28*W;
            double w= 136*W;
            int margin= (int)m;
            int width=(int) w;
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(width, width);
            params.leftMargin =margin;
            params.topMargin = margin;
            for (int i = 0; i <path.length ; i++) {
                MyImageView image = new MyImageView(this);
                image.setScaleType(ImageView.ScaleType.FIT_XY);

//                displayImage(image ,path[i]);
                Glide.with(getThisContext()).load(MainApp.getPicassoImagePath(path[i])).placeholder(R.drawable.bg_express).into(image);

                image.setTag(R.id.tag_first2,mExpressRecordTo.getReceivePhoto().replaceAll(",",";"));
                image.setTag(R.id.tag_second2,i);
//                flowLayoutReceive.addView(image, flowLayoutReceive.getChildCount());
                flowLayoutReceive.addView(image, flowLayoutReceive.getChildCount(), params);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getThisContext(),LongPictureShowActivity.class);
                        intent.putExtra("path",(String)v.getTag(R.id.tag_first2));
                        intent.putExtra("index", (int)v.getTag(R.id.tag_second2));
                        startActivity(intent);
                    }
                });

            }
            }

        //显示录入图片
        if (!TextUtils.isEmpty(mExpressRecordTo.getExpressImages())) {
            String[] path = mExpressRecordTo.getExpressImages().split(";");
            double W = getScreenWidthPixels(this)/720.0;
            double m= 28*W;
            double w= 136*W;
            int margin= (int)m;
            int width=(int) w;
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(width, width);
            params.leftMargin =margin;
            params.topMargin = margin;
//            int W = getScreenWidthPixels(this) - 100;
//            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(W / 4, W / 4);
//            params.leftMargin = 10;
//            params.topMargin = 10;
            for (int i = 0; i <path.length ; i++) {
                MyImageView image = new MyImageView(this);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(getThisContext()).load(MainApp.getPicassoImagePath(path[i])).placeholder(R.drawable.bg_express).into(image);

                image.setTag(R.id.tag_first2,mExpressRecordTo.getExpressImages().replaceAll(",",";"));
                image.setTag(R.id.tag_second2,i);
                flowLayoutInput.addView(image, flowLayoutInput.getChildCount(), params);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getThisContext(),LongPictureShowActivity.class);
                        intent.putExtra("path",(String)v.getTag(R.id.tag_first2));
                        intent.putExtra("index", (int)v.getTag(R.id.tag_second2));
                        startActivity(intent);
                    }
                });
            }for (String  p : path) {


            }
        }
    }
    private void findById() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        flowLayoutInput = (FlowLayout)findViewById(R.id.flowLayout);
        divideLine = findViewById(R.id.divideLine);
        flowLayoutReceive = (FlowLayout) findViewById(R.id.flowLayout_other);
        expressIcon = (ImageView) findViewById(R.id.express_icon);
        expressName = (TextView) findViewById(R.id.express_company);
        remark = (TextView) findViewById(R.id.remark);
        tvReceive = (TextView) findViewById(R.id.tv_receive);
        apartmentName = (TextView) findViewById(R.id.express_apartment);
        expressNo = (TextView) findViewById(R.id.expressNo);
        name = (TextView) findViewById(R.id.name);
        llName = (LinearLayout) findViewById(R.id.ll_name);
        phoneNo = (TextView) findViewById(R.id.phoneNo);
        inputTime = (TextView) findViewById(R.id.inputTime);
        receiveTime = (TextView) findViewById(R.id.receiveTime);
        ShangshabanChangeTextSpaceView tv=  (ShangshabanChangeTextSpaceView)findViewById(R.id.tv_name);
        tv.setSpacing(12.5f);
        tv.setText("领取人：");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                onBackPressed();
                goToAnimation(2);
                break;
    }
    }
}
