package com.joy.property.visit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.inspection.adapter.ApartmentAdapter;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.KeyboardUtilExpress;
import com.joy.property.visit.view.*;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 *  Created by usb on 2017/7/31.
 */

public class ReceiveExpressActivity extends BaseActivity implements View.OnClickListener {
    private EditText        mCodeNo;
    private TextView        apartmentName;
    private ApartmentInfoTo apartment;
    private RelativeLayout  llApartment;
    private ImageView       search;
    private String                           codeNo;

    private KeyboardUtilExpress keyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_express);
        initView();

        setApartment();
    }
    private void setApartment() {
        Log.i("1111111", "success: "+1);

        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findByUserSid(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                dialogFragment.dismiss();
                Log.i("1111111", "success: "+2);

                if (msg.getSuccess() == 0) {
                    Log.i("1111111", "success: "+3);
                    if (msg.getData().size()==1){
                        Log.i("1111111", "success: "+4);

                        apartment = msg.getData().get(0);
                        apartmentName.setText(msg.getData().get(0).getApartmentName());
                        llApartment.setClickable(false);
                    }
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                Log.i("1111111", "error: "+error.toString());

                super.failure(error);
            }
        });
    }
    private void initView() {

        llApartment=(RelativeLayout)findViewById(R.id.layout_01);
        search=(ImageView) findViewById(R.id.search);
        search.setOnClickListener(this);
        llApartment.setOnClickListener(this);
        mCodeNo=(EditText) findViewById(R.id.codeNo);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.search_input).setOnClickListener(this);
        findViewById(R.id.receive_history).setOnClickListener(this);
        apartmentName=(TextView) findViewById(R.id.tv_apartment_name);
        keyboardUtil = new KeyboardUtilExpress(this, mCodeNo);
        mCodeNo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("2222", "onTouch: 左边");
                mCodeNo.requestFocus();
                keyboardUtil.hideSoftInputMethod();
                keyboardUtil.showKeyboard();
                return false;
            }
        });
    }
    private static final int    Mars    = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_01:
                if(keyboardUtil.isShow())
                    keyboardUtil.hideKeyboard();
                selectApartmentDialog();
                break;
            case R.id.search:
                if(keyboardUtil.isShow())
                    keyboardUtil.hideKeyboard();
//                selectApartmentDialog();
                Intent intent1=new Intent(getThisContext(), com.joy.property.visit.view.CaptureActivity.class);
                intent1.putExtra("type","2");
//                sta Intent intent = new Intent(OnActivityResultActivity.this, thirdActivity.class);
//                String content = "地球来的消息:你好，我是来自地球上的小老鼠。我好想去你们月球";
                startActivityForResult(intent1, Mars);
                break;
            case R.id.back:

                onBackPressed();
                goToAnimation(2);
                break;
            case R.id.receive_history:
                if(keyboardUtil.isShow())
                    keyboardUtil.hideKeyboard();
                startActivity(new Intent(this,ReceiveExpressRecordActivity.class));
                break;
            case R.id.search_input:
                if(keyboardUtil.isShow())
                    keyboardUtil.hideKeyboard();
                if (checking())
                    return;
                Intent intent=new Intent(this,ReceiveExpressListActivity.class);
                intent.putExtra("apartmentSid",apartment.getApartmentSid());
                if(!TextUtils.isEmpty(mCodeNo.getText().toString())){
                    intent.putExtra("mCodeNo",mCodeNo.getText().toString());
                    intent.putExtra("no",mCodeNo.getText().length()+"");
                    Log.i("333333", "length1111——————————"+mCodeNo.getText().toString().length());
                }
                Log.i("333333", "length2222——————————"+mCodeNo.getText().toString().length());
                startActivity(intent);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case Mars:
                if(resultCode==RESULT_OK){
                    Bundle MarsBuddle = data.getExtras();
                    String MarsMessage = MarsBuddle.getString("codeNo");
                    mCodeNo.setText(MarsMessage);
                    mCodeNo.setSelection(MarsMessage.length());
                }

                break;

        }
    }

    private boolean checking() {
        if(TextUtils.isEmpty(apartmentName.getText())){
            ToastShowLong(getThisContext(),"请选择项目");
            return true;
        }
        return false;
    }

    private void selectApartmentDialog() {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_apartment, R.style.myDialogTheme);
        TextView mCancel = (TextView) dialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final ListView mList = (ListView) dialog.findViewById(R.id.list);
        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
        api.findByUserSid(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(getThisContext()) {
                    @Override
                    public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                        dialogFragment.dismiss();
                        if (msg.getSuccess() == 0) {
                            final List<ApartmentInfoTo> infoList = new ArrayList<>();
                            infoList.addAll(msg.getData());
                            ApartmentAdapter mAdapter = new ApartmentAdapter(getThisContext());
                            mAdapter.setList(infoList);
                            mList.setAdapter(mAdapter);
                            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    apartment = infoList.get(position);
                                    apartmentName.setText(infoList.get(position).getApartmentName());
                                    apartment=infoList.get(position);
                                    dialog.dismiss();
                                }
                            });
                        } else {
                            Toast.makeText(getThisContext(),
                                    msg.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        dialogFragment.dismissAllowingStateLoss();
                        super.failure(error);
                    }
                }
        );

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

}
