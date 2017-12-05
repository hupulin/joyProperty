package com.joy.property.shop;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.AddressTo;
import com.jinyi.ihome.module.newshop.DefaultAddressParam;
import com.jinyi.ihome.module.newshop.DeleteAddressParam;
import com.jinyi.ihome.module.newshop.GetAddressListParam;
import com.jinyi.ihome.module.newshop.AddressListParam;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.common.application.KeyValue;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.shop.adapter.MyAddressAdapter;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;
import rx.Observable;

/**
 * Created by xz on 2016/7/25.
 */
public class MyAddressActivity extends com.joyhome.nacity.app.base.BaseActivity implements View.OnClickListener {
    private TextView addAddress;
    private RelativeLayout no_address;
    private ListView listView;
    private List<AddressTo>addressList=new ArrayList<>();
    private MyAddressAdapter adapter;
    private  int location;
    private  boolean setDefault;
    private AddressTo addressJson;
    private boolean contianSelectAddress;
    private AddressTo backAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_address);
        getIntentData();
        initView();
        initData();
        setAdapterListener();
    }

    private void getIntentData() {
        addressJson= (AddressTo) getIntent().getSerializableExtra("addressJson");


    }

    private void setAdapterListener() {
        //删除地址
        adapter.setMyAddressDeleteListener(new MyAddressAdapter.MyAddressDeleteListener() {
            @Override
            public void deleteAddress(int position) {
                cancelOlderDilog(position);

            }
        });
        //编辑地址
        adapter.setMyAddressEditListener(new MyAddressAdapter.MyAddressEditListener() {
            @Override
            public void editAddress(AddressTo mode, final int position) {
                Intent intent = new Intent(getThisContext(), AddAddressActivity.class);
                intent.putExtra("addressTo", mode);

                location = position;
                startActivity(intent);
                goToAnimation(1);
//                AddressListParam param = new AddressListParam();
//                param.setId(mode.getId());
//                NewShopApi api = ApiClientBulk.create(NewShopApi.class);
//                final CustomDialogFragment dialogFragment = new CustomDialogFragment();
//                dialogFragment.show(getSupportFragmentManager(), "");
//                api.editReceiveAddress(param, new HttpCallback<MessageToBulk<AddressTo>>(getThisContext()) {
//                    @Override
//                    public void success(MessageToBulk<AddressTo> msg, Response response) {
//                        dialogFragment.dismiss();
//                        if (msg.getCode() == 0) {
//                            addressList.add(msg.getReceiverAddressVo());
//                            //  adapter.notifyDataSetChanged();
//                        }
//                    }
//                });
            }
        });
        // 设置默认
        adapter.setMyAddressSetDefaultListener(new MyAddressAdapter.MyAddressSetDefaultListener() {
            @Override
            public void setDefaultAddress(AddressTo mode, final int position) {
                if (addressList.get(position).getIsDefaultAddress().equals("N")) {
                    DefaultAddressParam param = new DefaultAddressParam();
                    param.setId(mode.getId());
                    param.setUserId(mUserHelperBulk.getSid());
                    NewShopApi api = ApiClientBulk.create(NewShopApi.class);
                    final CustomDialogFragment dialogFragment = new CustomDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), "");
                    api.designDefaultAddress(param, new HttpCallback<MessageToBulk>(getThisContext()) {
                        @Override
                        public void success(MessageToBulk msg, Response response) {
                            dialogFragment.dismiss();
                            if (msg.getCode() == 0) {
                                addressList.clear();

                                initData();

                            }
                        }
                    });

                }
            }
        });

    }
    private void initView() {
        findViewById(R.id.back).setOnClickListener(this);
        addAddress = (TextView) findViewById(R.id.addAddress);
        addAddress.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        no_address = (RelativeLayout) findViewById(R.id.no_address);
        adapter = new MyAddressAdapter(getThisContext());
        adapter.setList(addressList);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        if(addressList==null){
            no_address.setVisibility(View.VISIBLE);
        }else{
            no_address.setVisibility(View.GONE);
        }
    }
    private void initData() {
        addressList.clear();
        GetAddressListParam param=new GetAddressListParam();
        param.setUserId(mUserHelperBulk.getSid());
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        api.getAddress(param, new HttpCallback<MessageToBulk<List<AddressTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<AddressTo>> msg, Response response) {

                if (msg.getCode() == 0) {
                    addressList.addAll(msg.getReceiverAddressList());
                    if(addressList==null||addressList.size()==0){
                        no_address.setVisibility(View.VISIBLE);

                    }else{
                        no_address.setVisibility(View.GONE);
                    }
                    adapter.notifyDataSetChanged();
                    if (addressList==null||addressList.size()==0)
                    {
                        setDefault=false;
                        SpUtil.put(getThisContext(), "NoIdCard", true);

                    }
//                    if (addressList==null||addressList.size()==0||addressList.size()==1)
//                    {
//
//
//                    }

                    if(setDefault){
                        DefaultAddressParam param = new DefaultAddressParam();
                        param.setId(addressList.get(0).getId());
                        ConfigUtil.saveString(PreferenceManager.getDefaultSharedPreferences(getThisContext()),
                                "SelectAddress", JSON.toJSONString(addressList.get(0)));
                        param.setUserId(mUserHelperBulk.getSid());
                        NewShopApi api = ApiClientBulk.create(NewShopApi.class);
                        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
                        dialogFragment.show(getSupportFragmentManager(), "");
                        api.designDefaultAddress(param, new HttpCallback<MessageToBulk>(getThisContext()) {
                            @Override
                            public void success(MessageToBulk msg, Response response) {
                                dialogFragment.dismiss();
                                if (msg.getCode() == 0) {
                                    initData();
                                    setDefault=false;
                                    SpUtil.put(getThisContext(), "NoIdCard", true);

                                }
                            }
                        });

                    }
                    listView.setOnItemClickListener((parent, view, position, id) -> {

                        Intent intent =new Intent();
                        AddressTo addressToNew =addressList.get(position);

//                        AddressTo addressTo=addressList.get(position);
//                        addressToNew.setId(addressTo.getId());
//                        addressToNew.setIdentityCardNo(addressTo.getIdentityCardNo());
//                        addressToNew.setIsDefaultAddress(addressTo.getIsDefaultAddress());
//                        addressToNew.setReceiverPhone(phone.getText().toString());
//                        addressToNew.setReceiverArea(addressArea.getText().toString());
//                        addressToNew.setReceiverDetailAddress(addressDetail.getText().toString());
//                        addressToNew.setReceiverName(name.getText().toString());
                        SpUtil.put(getThisContext(), "NoIdCard", false);
                        intent.putExtra("addressTo", addressToNew);
                        setResult(15, intent);
                        finish();
                        goToAnimation(2);
                    });
                }
            }
        });
//        AddressTo addressTo=new AddressTo();
//        AddressTo addressTo1=new AddressTo();
//        AddressTo addressTo2=new AddressTo();
//        addressTo.setReceiverDetailAddress("大别山lalala");
//        addressTo.setReceiverArea("湖北省武汉市新洲小镇");
//        addressTo.setReceiverName("胡普林");
//        addressTo.setReceiverPhone("18758168587");
//        addressTo.setIsDefaultAddress("Y");
//        addressTo1.setReceiverDetailAddress("木兰山hahaha");
//        addressTo1.setReceiverArea("湖北省武汉市黄陂小镇");
//        addressTo1.setReceiverName("熊壮");
//        addressTo1.setReceiverPhone("15168234205");
//        addressTo1.setIsDefaultAddress("N");
//        addressTo2.setReceiverDetailAddress("没有山ininin");
//        addressTo2.setReceiverArea("湖北省武汉市纸坊小镇");
//        addressTo2.setReceiverName("建雄");
//        addressTo2.setReceiverPhone("12345678");
//        addressTo2.setIsDefaultAddress("N");
//        addressList.add(addressTo);
//        addressList.add(addressTo1);
//        addressList.add(addressTo2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.addAddress:
                if(addressList==null&&addressList.size()==0) {
                    setDefault=true;
                }
                startActivity(new Intent(getThisContext(), AddAddressActivity.class));
                //   Intent intent=new Intent(getThisContext(),AddAddressActivity.class);


                goToAnimation(1);

                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //删除弹窗
    private void cancelOlderDilog(int position) {
        System.out.println(addressList+"-----------------");
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_delete_msg, R.style.myDialogTheme);
        TextView dialogText = (TextView) dialog.findViewById(R.id.dialogText);
        Button cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button confirm = (Button) dialog.findViewById(R.id.btn_add);
        dialogText.setText("确认要删除这个地址?");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteAddressParam param = new DeleteAddressParam();
                param.setId(addressList.get(position).getId());
                NewShopApi api = ApiClientBulk.create(NewShopApi.class);
                final CustomDialogFragment dialogFragment = new CustomDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "");
                api.deleteReceiveAddress(param, new HttpCallback<MessageToBulk>(getThisContext()) {
                    @Override
                    public void success(MessageToBulk msg, Response response) {
                        dialogFragment.dismiss();
                        if (msg.getCode() == 0) {

                            if ("Y".equals(addressList.get(position).getIsDefaultAddress()))
                                setDefault=true;
                            initData();
                        }
                    }
                });
//                addressList.remove(position);
//                adapter.notifyDataSetChanged();
//                if(addressList==null||addressList.size()==0){
//                    no_address.setVisibility(View.VISIBLE);
//                }else{
//                    no_address.setVisibility(View.GONE);
//                }
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.show();

    }

    @Override
    protected void onRestart() {
        initData();
        super.onRestart();
    }

    @Override
    public void onBackPressed() {

        if (addressList==null||addressList.size()==0){
            Intent intent=new Intent();
            intent.putExtra("setDefaultAddress", true);
            setResult(16, intent);
        }
        if (addressJson!=null&&addressList!=null&&addressList.size()>0){
            backAddress=null;
            Observable.from(addressList).filter(addressTo -> addressTo.getId().equals(addressJson.getId())).subscribe(addressTo1 -> {
                backAddress=addressTo1;
            });
            Intent intent = new Intent();
            if (backAddress!=null) {

                intent.putExtra("addressTo", backAddress);
                setResult(15, intent);
            }else
                setResult(16, intent);

        }

        finish();
        goToAnimation(2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }

}
