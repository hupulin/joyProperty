package com.joy.property.visit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.visitor.VerifyCardResultTo;
import com.jinyi.ihome.module.visitor.VerifyPast;
import com.jinyi.ihome.module.visitor.VisitorCardTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VisitorApi;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class VerifyResultActivity extends BaseActivity
        implements OnClickListener {

    private TextView mResult;
    private TextView mTip;
    private LinearLayout mItem01;
    private LinearLayout mItem02;
    private LinearLayout mItem03;
    private LinearLayout mItem04;
    private LinearLayout mItem07;
    private LinearLayout mItem08;
    private LinearLayout mItem09;
    private TextView mApartment;
    private TextView mGuest;
    private TextView mTelephone;
    private TextView mHouse;
    private TextView mCarNo;
    private TextView mVisitingTime;
    private TextView mLeavingTime;
    private ImageButton mBtnArrive;
    private ImageButton mBtnLeave;
    private ImageButton mBtnApartment;
    private VerifyCardResultTo cardResultTo;
    private Button mPass;
    private Button mNoPass;
     VerifyPast param=new VerifyPast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_result);
        findById();
        initIntentDatas();
        initDatas();
    }


    private void findById() {
        findViewById(R.id.back).setOnClickListener(this);
        mResult = (TextView) findViewById(R.id.result);
        //mResult.setOnClickListener(this);
         mPass =(Button) findViewById(R.id.btn_pass);
        mPass.setOnClickListener(this);
         mNoPass =(Button) findViewById(R.id.btn_no_pass);
        mNoPass.setOnClickListener(this);
        mTip = (TextView) findViewById(R.id.tip);
        mItem01 = (LinearLayout) findViewById(R.id.ll_item01);
        mItem01.setVisibility(View.GONE);
        mItem02 = (LinearLayout) findViewById(R.id.ll_item02);
        mItem02.setVisibility(View.GONE);
        mItem03 = (LinearLayout) findViewById(R.id.ll_item03);
        mItem03.setVisibility(View.GONE);
        mItem04 = (LinearLayout) findViewById(R.id.ll_item04);
        mItem04.setVisibility(View.GONE);
        mItem07 = (LinearLayout) findViewById(R.id.ll_item07);
        mItem07.setVisibility(View.GONE);
        mItem08 = (LinearLayout) findViewById(R.id.ll_item08);
        mItem08.setVisibility(View.GONE);
        mItem09 = (LinearLayout) findViewById(R.id.ll_item09);
        mItem09.setVisibility(View.GONE);
        mApartment = (TextView) findViewById(R.id.apartment);
        mGuest = (TextView) findViewById(R.id.guest);
        mCarNo = (TextView) findViewById(R.id.car_no);
        mTelephone = (TextView) findViewById(R.id.telephone);
        mHouse = (TextView) findViewById(R.id.house);
        mVisitingTime = (TextView) findViewById(R.id.arrive_time);
        mLeavingTime = (TextView) findViewById(R.id.leave_time);
        findViewById(R.id.phone).setOnClickListener(this);
        mBtnArrive = (ImageButton) findViewById(R.id.btn_arrive);
        mBtnLeave = (ImageButton) findViewById(R.id.btn_leave);
        mBtnApartment = (ImageButton) findViewById(R.id.btn_apartment);
    }

    private void initIntentDatas() {
        cardResultTo = (VerifyCardResultTo) getIntent().getSerializableExtra("mode");
    }

    private void initDatas() {
        if (cardResultTo.isResult()) {

            mResult.setText(getString(R.string.verify_pass));
            mResult.setTextColor(0xffa5db50);
            mTip.setText(getString(R.string.verify_pass_tip));
            mTip.setTextColor(0xffa5db50);
            if (!cardResultTo.isApartmentPass()) {
                mResult.setText("未通过");
                mResult.setTextColor(0xfff18f1c);
                mTip.setText("部分信息核对出错");
                mTip.setTextColor(0xfff18f1c);

            } else if (!cardResultTo.isDatePass()) {
                mResult.setText(getString(R.string.verify_unpass_date));
                mResult.setTextColor(0xfff18f1c);
                mTip.setText(getString(R.string.verify_unpass_date_tip));
                mTip.setTextColor(0xfff18f1c);
            }
        } else {
            mResult.setText(getString(R.string.verify_unpass));
            mResult.setTextColor(0xfff18f1c);
            mPass.setVisibility(View.GONE);
            mNoPass.setVisibility(View.GONE);
            mTip.setText(getString(R.string.verify_unpass_tip));
            mTip.setTextColor(0xfff18f1c);

            return;
        }


        if (cardResultTo.isApartmentPass()) {
            mItem01.setBackgroundResource(R.drawable.verify_item_ic);
            mBtnApartment.setBackgroundResource(R.drawable.check_bg);
        } else {
            mItem01.setBackgroundResource(R.drawable.unverify_item_ic);
            mBtnApartment.setBackgroundResource(R.drawable.uncheck_bg);
        }

        if (!TextUtils.isEmpty(cardResultTo.getApartmentName())) {
            mItem01.setVisibility(View.VISIBLE);
            mApartment.setText(cardResultTo.getApartmentName());
        }
        if (!TextUtils.isEmpty(cardResultTo.getVisitorName())) {
            mItem02.setVisibility(View.VISIBLE);
            mGuest.setText(cardResultTo.getVisitorName());
        }
        if (!TextUtils.isEmpty(cardResultTo.getCarNo())) {
            mItem07.setVisibility(View.VISIBLE);
            mCarNo.setText(cardResultTo.getCarNo());
          // param.setCardNo(cardResultTo.getCarNo());
        }
        if (!TextUtils.isEmpty(cardResultTo.getOwnerPhone())) {
            mItem03.setVisibility(View.VISIBLE);
            String phone = cardResultTo.getOwnerPhone();
            mTelephone.setText(phone.substring(0, 3) + "****" + phone.substring(7, 11));
        }

        if (!TextUtils.isEmpty(cardResultTo.getOwnerNo())) {
            mItem04.setVisibility(View.VISIBLE);
            mHouse.setText(cardResultTo.getOwnerNo());
        }


        if (cardResultTo.isVisitTimePass()) {
            mItem08.setBackgroundResource(R.drawable.verify_item_ic);
            mBtnArrive.setBackgroundResource(R.drawable.check_bg);
        } else {

            mItem08.setBackgroundResource(R.drawable.unverify_item_ic);
            mBtnArrive.setBackgroundResource(R.drawable.uncheck_bg);
        }

        if (cardResultTo.isLeaveTimePass()) {
            mItem09.setBackgroundResource(R.drawable.verify_item_ic);
            mBtnLeave.setBackgroundResource(R.drawable.check_bg);
        } else {
            mItem09.setBackgroundResource(R.drawable.unverify_item_ic);
            mBtnLeave.setBackgroundResource(R.drawable.uncheck_bg);
        }
        if (!TextUtils.isEmpty(cardResultTo.getVisitTime())) {

            mItem08.setVisibility(View.VISIBLE);
            mVisitingTime.setText(cardResultTo.getVisitTime());
        }
        if (!TextUtils.isEmpty(cardResultTo.getLeaveTime())) {
            mItem09.setVisibility(View.VISIBLE);
            mLeavingTime.setText(cardResultTo.getLeaveTime());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            case R.id.phone:

                mobileShowDialog();
                break;
            case R .id.btn_pass:
                 param.setPassType(0);
                 onResult();
                break;
            case R.id.btn_no_pass:
                param.setPassType(1);
                onResult();
                break;
        }
    }

    public void onResult(){
        VisitorApi api = ApiClient.create(VisitorApi.class);
        param.setCardNo(getIntent().getStringExtra("carNo"));
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        System.out.println(param+"param");
        api.verifyPastCard(param,new HttpCallback<MessageTo<VisitorCardTo>>(this) {
            @Override
            public void success(MessageTo<VisitorCardTo> msg, Response response) {
                dialogFragment.dismiss();
                if ( msg.getSuccess()== 0) {
                    Toast.makeText(getThisContext(), "业主已收到推送", Toast.LENGTH_LONG).show();
                    mPass.setEnabled(false);
                    mNoPass.setEnabled(false);
                  }else{
                    Toast.makeText(getThisContext(),msg.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            public void failure(RetrofitError error) {
              dialogFragment.dismiss();
                super.failure(error);
            }
        });
    }

    private void mobileShowDialog() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_detele_msg, R.style.myDialogTheme);
        TextView mTip = (TextView) dialog.findViewById(R.id.tip);
        mTip.setText("确定拨打此业主电话？");
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + cardResultTo.getOwnerPhone()));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                    if (ActivityCompat.checkSelfPermission(getThisContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                }
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }
}
