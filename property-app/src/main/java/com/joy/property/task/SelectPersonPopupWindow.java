package com.joy.property.task;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.home.ServiceResponseParam;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2016/8/4.
 */
public class SelectPersonPopupWindow extends BaseActivity implements View.OnClickListener {
    private LinearLayout btn_add_execution, btn_add_attention;
    private Button btn_confirm_single;
    private LinearLayout layout;
    private ServiceMainExpandTo mainExpandTo;
    private int MAX_LENGTH = 100;
    private TextView chCounterText;
    private EditText statusEdit;
    private String apartmentSid = "";
    private UserInfoTo mAssistant;
    private TextView text_execute_man;
    private TextView text_carry_man;
    private MainApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        application = (MainApplication) this.getApplicationContext();
        btn_add_execution = (LinearLayout) this.findViewById(R.id.add_execution);
        btn_add_attention = (LinearLayout) this.findViewById(R.id.add_attention);
        btn_confirm_single = (Button) this.findViewById(R.id.btn_confirm_single);
        text_execute_man = (TextView) findViewById(R.id.text_execute_man);
        text_carry_man = (TextView) findViewById(R.id.text_carry_man);
        initIntentDatas();
        setTextMan();
        init();
        layout = (LinearLayout) findViewById(R.id.pop_layout);

        //添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity
        layout.setOnClickListener((View v) -> {
            // TODO Auto-generated method stub
            Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                    Toast.LENGTH_SHORT).show();

        });
        //添加按钮监听
        btn_confirm_single.setOnClickListener(this);
        btn_add_execution.setOnClickListener(this);
        btn_add_attention.setOnClickListener(this);
    }

    //实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    private void initIntentDatas() {
        mainExpandTo = (ServiceMainExpandTo) getIntent().getSerializableExtra("mode");
        apartmentSid = getIntent().getStringExtra("aSid");
        mAssistant = (UserInfoTo) getIntent().getSerializableExtra("assist");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_execution:
                Intent intent = new Intent(this, ExecuteActivity.class);
                intent.putExtra("center", "center");
                intent.putExtra("mode", mainExpandTo);
                startActivity(intent);
                break;
            case R.id.add_attention:
                Intent intent2 = new Intent(this, CareActivity.class);
                intent2.putExtra("center", "center");
                intent2.putExtra("toSid", apartmentSid);
                startActivity(intent2);
                break;
            case R.id.btn_confirm_single:
                if (checking())
                    return;
                determineBill();
                break;
            default:
                break;
        }
        finish();
    }


    private void setTextMan() {
        if (application.getApp_execute_man() != null) {
            text_execute_man.setText(application.getApp_execute_man().getName());
        }
        if (application.getApp_care_man() != null) {
            List<UserInfoTo> _messageItems = application.getApp_care_man();
            listToString(_messageItems, 1, "，");
            System.out.println("搞不出来" + listToString(_messageItems, 1, "，"));
            text_carry_man.setText(listToString(_messageItems, 1, "，"));
        }
    }

    private boolean checking() {
        if (TextUtils.isEmpty(text_execute_man.getText())) {
            text_execute_man.setHint("添加执行人*必填");
            text_execute_man.setHintTextColor(Color.RED);
            return true;
        }
        return false;
    }

    /**
     * 派单完成弹窗
     **/
    private void determineBill() {
        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceResponseParam param = new ServiceResponseParam();
        if (application.getApp_execute_man() != null) {
            param.setGroupUserSid(application.getApp_execute_man().getSid());
        }
        if (application.getApp_ServiceMainExpandTo() != null) {
            param.setServiceSid(application.getApp_ServiceMainExpandTo().getServiceSid());
        }
        param.setAssignType(1);
        param.setAssignDesc(statusEdit.getText().toString());
        if (application.getApp_execute_man() != null) {
            List<UserInfoTo> messageItems = application.getApp_care_man();
            listToString(messageItems, 2, "#");
//            System.out.println("做不到啊" + listToString(messageItems, 2, "#"));
            param.setCareUserSid(listToString(messageItems, 2, "#"));
        } else {
            param.setCareUserSid("");
        }
        param.setUserSid(mUserHelper.getSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        System.out.println(param+"提交param");
        api.distributionService(param, new HttpCallback<MessageTo<ServiceMainExpandTo>>(this) {
            @Override
            public void success(MessageTo<ServiceMainExpandTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Intent intent = new Intent(getThisContext(), ReceiveTaskDetailActivity.class);
                    intent.putExtra("Done2Back", "DoneToBack");
                    intent.putExtra("sid", application.getApp_ServiceMainExpandTo().getServiceSid());
                    intent.putExtra("apartmentSid", application.getApp_ServiceMainExpandTo().getApartmentSid());
                    startActivity(intent);
                    application.setApp_execute_man(new UserInfoTo());
                    application.setApp_care_man(new ArrayList<>());
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });
    }

    /**
     * 一个把List<T>转化为以"/"隔开的字符串的方法
     **/
    public static String listToString(List<UserInfoTo> list, int no, String sep) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (UserInfoTo userInfoTo : list) {

            if (flag) {
                result.append(sep == null ? "" : sep);
            } else {
                flag = true;
            }
            if (1 == no) {
                result.append(userInfoTo.getName().toString());
            }
            if (2 == no) {
                result.append(userInfoTo.getSid().toString());
            }

        }
        return result.toString();
    }

    /**
     * 监听字数的
     */
    public void init() {
        chCounterText = (TextView) findViewById(R.id.sdk_status_ch_counter);
        statusEdit = (EditText) findViewById(R.id.edit_note);
        statusEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String content = statusEdit.getText().toString();
                chCounterText.setText(content.length() + "/"
                        + MAX_LENGTH);
            }

        });
    }
}
