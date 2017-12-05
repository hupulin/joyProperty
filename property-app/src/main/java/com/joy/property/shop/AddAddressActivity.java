package com.joy.property.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.AddAddressParam;
import com.jinyi.ihome.module.newshop.AddressTo;
import com.jinyi.ihome.module.newshop.DefaultAddressParam;
import com.jinyi.ihome.module.newshop.EditAddressSaveParam;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.MainActivity;
import com.joy.property.base.BaseActivity;
import com.joy.property.shop.shoputil.ChangeAddressPopwindow;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joy.property.utils.IdCardUtil;
import com.joyhome.nacity.app.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2017/1/8.
 */
public class AddAddressActivity extends BaseActivity implements View.OnClickListener {
    private EditText name;
    private EditText phone;
    private EditText idCard;
    private TextView addressArea;
    private EditText addressDetail;
    private RelativeLayout idCardLayout;
    private RelativeLayout selectArea;
    private TextView save;
    private TextView title;
    private RelativeLayout setDefaultLayout;
    private AddressTo addressTo;
    private boolean newOne;
    private String isSeaBuy ;
    private List<AddressTo>addressList=new ArrayList<>();
    private AddressTo addressJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initView();
        checkIntent();
        addressJson= (AddressTo) getIntent().getSerializableExtra("addressJson");

    }
    private void checkIntent() {
        addressTo = (AddressTo) getIntent().getSerializableExtra("addressTo");
        if(addressTo!=null){
            title.setText("编辑收货地址");
            isSeaBuy = (String) getIntent().getSerializableExtra("IsSeaBuy");
            if(isSeaBuy!=null){
                if(isSeaBuy.equals("no")){
                    idCardLayout.setVisibility(View.GONE);
                }
            }
            Log.i("2222", "编辑");
            setView();
        }else {
            title.setText("新增收货地址");
            Log.i("2222", "new一个");
            isSeaBuy = (String) getIntent().getSerializableExtra("IsSeaBuy");
            if(isSeaBuy!=null){
                if(isSeaBuy.equals("no")){
                    idCardLayout.setVisibility(View.GONE);
                }
            }
            newOne=true;
        }
    }
    private void setView() {
        name.setText(addressTo.getReceiverName());
        phone.setText(addressTo.getReceiverPhone());
        addressDetail.setText(addressTo.getReceiverDetailAddress());
        idCard.setText(addressTo.getIdentityCardNo());
        addressArea.setText(addressTo.getReceiverArea());
    }

    private void initView() {
        selectArea=(RelativeLayout) findViewById(R.id.select_area);
        idCardLayout=(RelativeLayout) findViewById(R.id.idCard_layout);
        selectArea.setOnClickListener(this);
        name = (EditText) findViewById(R.id.name);
        idCard = (EditText) findViewById(R.id.idCard);
        phone = (EditText) findViewById(R.id.phone);
        title = (TextView) findViewById(R.id.title);
        addressArea = (TextView) findViewById(R.id.address_area);
        addressDetail = (EditText) findViewById(R.id.addressDetail);
        addressArea = (TextView) findViewById(R.id.address_area);
        findViewById(R.id.back).setOnClickListener(this);
        save = (TextView) findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    private void showPopwindow() {
        ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(getThisContext());
        mChangeAddressPopwindow.setAddress("浙江", "杭州", "西湖区");
        mChangeAddressPopwindow.showAtLocation(selectArea, Gravity.BOTTOM, 0, 0);
        mChangeAddressPopwindow
                .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

                    @Override
                    public void onClick(String province, String city, String area) {
                        addressArea.setText(province + city + area);
                    }
                });

    }

    public boolean check(){
        if (TextUtils.isEmpty(name.getText().toString())){
            ToastShowLong(getThisContext(),"请先填写收货人姓名");
            return true;
        }
//        else if (TextUtils.isEmpty(idCard.getText().toString())){
//            ToastShowLong(getThisContext(),"请先填写身份证号码");
//            return true;
//        }
//        else if (!checkIdCard(idCard.getText().toString() )) {
//            Toast.makeText(this, "您填的身份证格式不正确", Toast.LENGTH_LONG).show();
//            return true;
//        }
        else if (TextUtils.isEmpty(phone.getText().toString())){
            ToastShowLong(getThisContext(),"请先填写收货人联系方式");
            return true;
        } else if (TextUtils.isEmpty(addressArea.getText().toString())){
            ToastShowLong(getThisContext(),"请先填写收货人地区");
            return true;
        }else if (TextUtils.isEmpty(addressDetail.getText().toString())){
            ToastShowLong(getThisContext(), "请先填写收货人详细地址");
            return true;
        }else if (phone.getText().toString().trim().length() != 11 ) {
            Toast.makeText(this, "请检查您的手机号码输入是否正确", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
    //判断身份证号合法
    public boolean checkIdCard(String cardNumber){
        IdCardUtil iv = new IdCardUtil();
        return iv.isValidatedAllIdcard(cardNumber);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.select_area:
                InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                showPopwindow();
                break;
            case R.id.save:
                if (check()) {
                    Log.i("222", "11111" + newOne);
                    return;
                }
                if(isSeaBuy != null && isSeaBuy.length() != 0) {
                    if(newOne){
                        if (TextUtils.isEmpty(idCard.getText().toString())) {
                            //保存地址上传无身份证
                            addAddress(" ",false);

                        } else {
                            if (checkIdCard(idCard.getText().toString())) {
                                //保存地址上传
                                addAddress(idCard.getText().toString(),false);

                            } else {
                                ToastShowLong(getThisContext(), "身份证号格式不正确");
                            }
                        }
                    }else{
                        if (TextUtils.isEmpty(idCard.getText().toString())) {
                            //保存地址上传无身份证
                            editLoadAddress(" ",false);

                        } else {
                            if (checkIdCard(idCard.getText().toString())) {
                                //保存地址上传
                                editLoadAddress(idCard.getText().toString(),false);

                            } else {
                                ToastShowLong(getThisContext(), "身份证号格式不正确");
                            }
                        }
                    }
                }
                else{

                    if (TextUtils.isEmpty(idCard.getText().toString())) {
                        if (newOne) {
                            Log.i("222", "2222" + newOne);
                            addAddress(" ",true);
                        } else {
                            //保存地址上传
                            editLoadAddress(" ",true);
                        }
                    } else {
                        if (checkIdCard(idCard.getText().toString())) {
                            if (newOne) {
                                Log.i("222", "33333" + newOne);
                                addAddress(idCard.getText().toString(),true);
                            } else {
                                //保存地址上传
                                editLoadAddress(idCard.getText().toString(),true);
                            }
                        } else {
                            ToastShowLong(getThisContext(), "身份证号格式不正确");
                        }
                    }
                }
                break;
        }
    }



    //增加新地址
    private void addAddress(String idNum,boolean toMyAddress) {
        AddAddressParam param = new AddAddressParam();
        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
        param.setUserId(mUserHelperBulk.getSid());
        param.setReceiverName(name.getText().toString());
        param.setReceiverPhone(phone.getText().toString());
        param.setReceiverArea(addressArea.getText().toString());
        param.setReceiverDetailAddress(addressDetail.getText().toString());
        param.setIdentityCardNo(idNum);
        System.out.println(param);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.addAddress(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                System.out.println(msg+"msg");
                if (msg.getCode() == 0) {
                    //         Intent intent=new Intent(getThisContext(),MyAddressActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
                    finish();
                    goToAnimation(2);
                }
                else {
                    Log.i("111222", msg.getMessage());
                    Toast.makeText(getThisContext(),msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //编辑地址
    private void editLoadAddress(String idNum,boolean toMyAddress) {
        EditAddressSaveParam param =new EditAddressSaveParam();
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        param.setId(addressTo.getId());
        param.setReceiverDetailAddress(addressDetail.getText().toString());
        param.setReceiverArea(addressArea.getText().toString());
        param.setReceiverName(name.getText().toString());
        param.setReceiverPhone(phone.getText().toString());
        param.setIdentityCardNo(idNum);
        param.setIsDefaultAddress(addressTo.getIsDefaultAddress());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.editAddressSave(param, new HttpCallback<MessageToBulk>(getThisContext()) {
            @Override
            public void success(MessageToBulk msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getCode() == 0) {
                    if (toMyAddress) {

//                        Intent intent = new Intent();
//
//                        setResult(15, intent);
                        finish();
                    }else {
//                        Intent intent =new Intent();
//                        AddressTo addressToNew =new AddressTo();
//                        addressToNew.setId(addressTo.getId());
//                        addressToNew.setIdentityCardNo(idCard.getText().toString());
//                        addressToNew.setIsDefaultAddress(addressTo.getIsDefaultAddress());
//                        addressToNew.setReceiverPhone(phone.getText().toString());
//                        addressToNew.setReceiverArea(addressArea.getText().toString());
//                        addressToNew.setReceiverDetailAddress(addressDetail.getText().toString());
//                        addressToNew.setReceiverName(name.getText().toString());
//                        intent.putExtra("addressTo", addressToNew);
//
//                        setResult(15, intent);
                        finish();
                    }
                }
            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }
}
