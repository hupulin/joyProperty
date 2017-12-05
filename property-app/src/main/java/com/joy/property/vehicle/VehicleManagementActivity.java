package com.joy.property.vehicle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.car.CarFindParam;
import com.jinyi.ihome.module.car.CarTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.CarApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.CustomDialog;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.KeyboardUtil;
import com.joy.property.utils.SpUtil;
import com.joy.property.utils.StatisticsUtil;
import com.joy.property.vehicle.adapter.VehicleBrandAreaAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit.RetrofitError;
import retrofit.client.Response;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


/**
 * Created by Admin on 2015-03-23
 * 车辆管理
 * 车辆信息查询
 */
public class VehicleManagementActivity extends BaseActivity
        implements OnClickListener,VehicleBrandAreaAdapter.IBrandItemTo {

    private EditText mLicenseLeft;
    private EditText mLicenseRight;
    private CustomDialog dialog;
    private ImageView changePark;
    private boolean firstChange;
    private TextView mTitle;
    private Activity mActivity;
    private KeyboardUtil keyboardUtilLeft;
    private KeyboardUtil keyboardUtilRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_management);
        mActivity=this;
        findById();
    }

    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mLicenseLeft = (EditText) findViewById(R.id.license_left);
        mLicenseRight = (EditText) findViewById(R.id.license_right);
        mLicenseLeft.clearFocus();
        mLicenseRight.requestFocus();

        mLicenseLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()>=2){
                        keyboardUtilRight = new KeyboardUtil(mActivity, mLicenseRight);
                        mLicenseLeft.clearFocus();
                        mLicenseRight.requestFocus();
                        keyboardUtilRight.hideSoftInputMethod();
                        if(keyboardUtilLeft!=null){
                            keyboardUtilLeft.hideKeyboard();
                        }
                        keyboardUtilRight.showKeyboard();
                        keyboardUtilRight.changeKeyboard(true);
                    }else if(s.length()==0){
                        keyboardUtilLeft.changeKeyboard(false);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
       // mLicenseLeft.setOnClickListener(this);
        mLicenseLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                    keyboardUtilLeft = new KeyboardUtil(mActivity, mLicenseLeft);

                Log.i("2222", "onTouch: 左边");
                mLicenseRight.clearFocus();
                mLicenseLeft.requestFocus();
                keyboardUtilLeft.hideSoftInputMethod();
                if(keyboardUtilRight!=null){
                    keyboardUtilRight.hideKeyboard();
                }
                keyboardUtilLeft.showKeyboard();
                keyboardUtilLeft.changeKeyboard(true);

                return false;
            }
        });
        mLicenseRight.setOnTouchListener((v, event) -> {

            keyboardUtilRight = new KeyboardUtil(mActivity, mLicenseRight);

            Log.i("2222", "onTouch: 右边");
            mLicenseLeft.clearFocus();
            mLicenseRight.requestFocus();
            keyboardUtilRight.hideSoftInputMethod();

            if(keyboardUtilLeft!=null){
                keyboardUtilLeft.hideKeyboard();
            }
            keyboardUtilRight.showKeyboard();
            keyboardUtilRight.changeKeyboard(true);
            return false;
        });
        Button mFindInformation = (Button) findViewById(R.id.btn_find);
        mFindInformation.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.tv_title);
        changePark = (ImageView) findViewById(R.id.changePark);
        changePark.setOnClickListener(this);
        if (SpUtil.getString(getThisContext(), "limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A007"))
        {
            changePark.setVisibility(View.VISIBLE);
            mTitle.setText("车辆管理(住宅)");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
//            case R.id.license_left:
//                findLicenseArea();
//                break;
            case R.id.btn_find:
                findVehicleInformation();
                break;
            case R.id.changePark:
                changePark();
                break;
            default:
                break;
        }
    }

    //选择车牌归属地
    private void findLicenseArea() {
        dialog  = new CustomDialog(this, R.layout.dialog_license_left, R.style.myDialogTheme);
        TextView mLicenseCancel = (TextView) dialog.findViewById(R.id.license_cancel);
        StickyListHeadersListView stickyList  = (StickyListHeadersListView) dialog.findViewById(R.id.list);
        mLicenseCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final List<String[][]> list = new ArrayList<>();
        list.add(new String[][]{{"浙"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L"}} );
        list.add(new String[][]{{"京"}, {"A", "B", "C", "E", "F", "G", "H"}});
        list.add(new String[][]{{"沪"}, {"A", "B", "C", "D", "E", "F","G", "H", "J", "K", "L","M", "N", "R"}});
        list.add(new String[][]{{"津"}, {"A", "B", "C", "E"}});
        list.add(new String[][]{{"渝"}, {"A", "B", "C", "F", "G", "H"}});
        list.add(new String[][]{{"冀"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "R", "T"}});
        list.add(new String[][]{{"晋"}, {"A", "B", "C", "D", "E", "F", "H", "J", "K", "L", "M"}} );
        list.add(new String[][]{{"蒙"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M"}} );
        list.add(new String[][]{{"辽"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "V"}} );
        list.add(new String[][]{{"吉"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J"}} );
        list.add(new String[][]{{"黑"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R"}} );
        list.add(new String[][]{{"苏"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N"}} );
        list.add(new String[][]{{"皖"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S"}});
        list.add(new String[][]{{"闽"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K"}} );
        list.add(new String[][]{{"赣"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M"}} );
        list.add(new String[][]{{"鲁"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "U", "V"}} );
        list.add(new String[][]{{"豫"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "U"}} );
        list.add(new String[][]{{"鄂"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S"}});
        list.add(new String[][]{{"湘"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "U"}});
        list.add(new String[][]{{"粤"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}} );
        list.add(new String[][]{{"桂"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R"}} );
        list.add(new String[][]{{"琼"}, {"A", "B", "C", "D", "E"}} );
        list.add(new String[][]{{"川"}, {"A", "B", "C", "D", "E", "F", "H", "J", "K", "L", "M", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}} );
        list.add(new String[][]{{"贵"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J"}} );
        list.add(new String[][]{{"云"}, {"A", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S"}} );
        list.add(new String[][]{{"藏"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J"}} );

        list.add(new String[][]{{"陕"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "U", "V"}} );
        list.add(new String[][]{{"甘"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P"}} );
        list.add(new String[][]{{"青"}, {"A", "B", "C", "D", "E", "F", "G", "H"}});
        list.add(new String[][]{{"宁"}, {"A", "B", "C", "D"}});
        list.add(new String[][]{{"新"}, {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R"}});

        VehicleBrandAreaAdapter  mAdapter = new VehicleBrandAreaAdapter(getThisContext());
        mAdapter.setBrandItemTo(this);
        mAdapter.setList(list);
        stickyList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        dialog.setCancelable(true);
        dialog.show();

    }

    /**
      查询车辆信息
     **/
    private void findVehicleInformation() {
        StatisticsUtil.sendStatistics(mUserHelper.getSid(), "车辆管理-查询车辆信息", getThisContext());
        String  regex= "[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼港澳台使领]{1}[A-Za-z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
        String mContent=mLicenseLeft.getText().toString()+mLicenseRight.getText().toString()+"";
        Log.i("22222", "mContent: "+mContent);
        if(!mContent.matches(regex)){
            Toast.makeText(this, "请输入正确的车牌", Toast.LENGTH_LONG).show();
            return;
        }
        CarApi api = ApiClient.create(CarApi.class);
        CarFindParam findParam = new CarFindParam();
        findParam.setUserSid(mUserHelper.getSid());
        findParam.setCarNo(mLicenseLeft.getText().toString().trim() + mLicenseRight.getText().toString().trim().toUpperCase(Locale.getDefault()));
        final CustomDialogFragment  dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
        System.out.println(findParam.toString()+"findParam");
        api.findCar(findParam, new HttpCallback<MessageTo<CarTo>>(this) {
            @Override
            public void success(MessageTo<CarTo> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getSuccess() == 0) {
                    Intent intent = new Intent(getThisContext(), VehicleInformationActivity.class);
                    System.out.println(msg.getData());
                    intent.putExtra("carInfo", msg.getData());
                    Log.i("2222", "carInfo: "+msg.getData().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                super.failure(error);
            }
        });

    }

    @Override
    protected Context getThisContext() {
        return VehicleManagementActivity.this;
    }

    @Override
    public void brandLetter(String brand, String letter) {
        dialog.dismiss();
        mLicenseLeft.setText(brand +letter);
    }
    private void changePark() {
        if (!firstChange){
            ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_home);
            firstChange=true;
            mTitle.setText("车辆管理(园区)");
        }else {
            ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_park);
            firstChange=false;
            mTitle.setText("车辆管理(住宅)");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            if(keyboardUtilRight!=null){
//                Log.i("111", "onKeyDown: 1");
//                if(keyboardUtilRight.isShow()){
//                keyboardUtilRight.hideKeyboard();}
//            }else if(keyboardUtilLeft!=null){
//                Log.i("111", "onKeyDown: 2");
//
//                if(keyboardUtilLeft.isShow())
//                {
//                    keyboardUtilLeft.hideKeyboard();}
//            }else{
//                finish();
//            }
//        }
//        return false;
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(keyboardUtilRight!=null&&keyboardUtilRight.isShow()){
                keyboardUtilRight.hideKeyboard();
                Log.i("111", "onKeyDown: 1");

            }
           else if(keyboardUtilLeft!=null&&keyboardUtilLeft.isShow()){
                keyboardUtilLeft.hideKeyboard();
                Log.i("111", "onKeyDown: 2");

            } else{
                Log.i("111", "onKeyDown: 3");
                finish();

            }
        }
        return false;


    }
}
