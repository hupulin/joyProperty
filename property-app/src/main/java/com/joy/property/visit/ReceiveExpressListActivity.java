package com.joy.property.visit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.express.ExpressNewTo;
import com.jinyi.ihome.module.express.ExpressRecordParam;
import com.jinyi.ihome.module.express.ExpressRecordTo;
import com.jinyi.ihome.module.express.FindExpressParam;
import com.jinyi.ihome.module.express.FindExpressRecordTo;
import com.jinyi.ihome.module.express.ReceiveExpressParam;
import com.jinyi.ihome.module.system.GroupMenuParam;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.SystemApi;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.KeyboardUtilExpress;
import com.joy.property.utils.KeyboardUtilGetExpress;
import com.joy.property.visit.adapter.ExpressReceiveAdapter;
import com.joy.property.visit.adapter.ExpressRecordAdapter;
import com.lidong.photopicker.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * 领取快递
 * Created by usb on 2017/7/31.
 */

public class ReceiveExpressListActivity extends BaseActivity implements View.OnClickListener {
    private PullToRefreshListView listView;
    private ExpressReceiveAdapter adapter;
    private int                pageIndex     = 1;
    private String      apartmentSid    ;
    private String      mCodeNo    ;
    private String      length    ;
    private List<ExpressNewTo> expressToList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_record_list);
        initIntentData();

        initView();
        getPhotoLimit(); 

        initData(1);
        refresh();
    }

    private void getPhotoLimit() {
        SystemApi api = ApiClient.create(SystemApi.class);
//        final GroupMenuParam menuParam = new GroupMenuParam();
//        menuParam.setId(mUserHelper.getSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findPhotoLimit(mUserHelper.getSid(), new HttpCallback<MessageTo<Boolean>>(getThisContext()) {
            @Override
            public void success(MessageTo<Boolean> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    Log.i("222222222", "success:1 "+msg.toString());
                    if(msg.getData()){
                        Log.i("222222222", "success:2 "+msg.toString());
                        camera.setVisibility(View.VISIBLE);
                        cameraClick.setClickable(true);

                    }else{
                        Log.i("222222222", "success:3 "+msg.toString());

                        camera.setVisibility(View.GONE);
                        cameraClick.setClickable(false);
                    }
                } else {
                      Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                Log.i("222222222", "error: "+error.toString());
                super.failure(error);
            }
        });
    }

    private void initIntentData() {
        apartmentSid=getIntent().getStringExtra("apartmentSid");
        mCodeNo=getIntent().getStringExtra("mCodeNo");
        length=getIntent().getStringExtra("no");
        Log.i("333333", "length——————————3333"+getIntent().getStringExtra("no"));
    }
    private ImageView camera;
    private TextView noData;
    private ImageView cameraClick;
    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new ExpressReceiveAdapter(getThisContext(),length);
        listView.setAdapter(adapter);
        adapter.setList(expressToList);
        findViewById(R.id.back).setOnClickListener(this);
        cameraClick= (ImageView) findViewById(R.id.camera_receive);
        cameraClick.setOnClickListener(this);
        camera =(ImageView)findViewById(R.id.camera);
        noData =(TextView)findViewById(R.id.noData);
        findViewById(R.id.receive_history).setOnClickListener(this);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(getThisContext(),ExpressInputDetailActivity.class);
//                intent.putExtra("mode", expressToList.get(position-1));
//               intent.putExtra("type", "1");
//                startActivity(intent);
//            }
//        });
        adapter.setReSentCodeListener(new ExpressReceiveAdapter.ReSentCodeListener() {
            @Override
            public void OnReSentCodeListener(String expressSid) {
                reSentCodeDialogShow(expressSid);
            }


        });
        adapter.SetGetExpressListener(new ExpressReceiveAdapter.GetExpressListener() {
            @Override
            public void OnGetExpressListener(String expressSid, String remark) {
                if("6".equals(length)){
                    //显示取件码
                    receiveExpressDialogShow(expressSid,remark);
                }else{
                    receiveExpressDialogShow(expressSid);

                }

            }

        });
    }

    private void reSentCodeDialogShow(String sid) {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_resend_code, R.style.myDialogTheme);
        Button btn_go = (Button) dialog.findViewById(R.id.btn_go);
        Button btn_close = (Button) dialog.findViewById(R.id.btn_close);
        dialog.findViewById(R.id.parent).setOnClickListener(v -> dialog.dismiss());
        btn_go.setOnClickListener((View v) -> {
            ApartmentApi api = ApiClient.create(ApartmentApi.class);
            final CustomDialogFragment dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"");
            api.resendExpressRemark(sid, new HttpCallback<MessageTo>(getThisContext()) {
                        @Override
                        public void success(MessageTo msg, Response response) {
                            dialogFragment.dismiss();
                            if (msg.getSuccess() == 0) {
                                Log.i("数据", "success: ");
//
                                Toast.makeText(getThisContext(),
                                        msg.getMsg(), Toast.LENGTH_LONG).show();
                                initData(1);
                            } else {
                                Toast.makeText(getThisContext(),
                                        msg.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            dialogFragment.dismissAllowingStateLoss();
                            Log.i("数据", "failure: "+error.toString());
                            super.failure(error);
                        }
                    }
            );

            dialog.dismiss();
        });
        btn_close.setOnClickListener((View v) -> {
            dialog.dismiss();

        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
    }

    private void receiveExpressDialogShow(String sid,String remark) {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_receive_express, R.style.myDialogTheme);
        Button btn_go = (Button) dialog.findViewById(R.id.btn_go);
//        EditText codeNo = (EditText) dialog.findViewById(R.id.codeNo);
//        codeNo.setFocusable(true);
//        codeNo.setFocusableInTouchMode(true);
//        codeNo.requestFocus();
        dialog.findViewById(R.id.parent).setOnClickListener(v -> dialog.dismiss());
        Button btn_close = (Button) dialog.findViewById(R.id.btn_close);
        btn_go.setOnClickListener((View v) -> {
//            if(TextUtils.isEmpty(codeNo.getText().toString())){
//                ToastShowLong(ReceiveExpressListActivity.this,"请输入取件码"+codeNo.getText());
//                return;
//            }
            ApartmentApi api = ApiClient.create(ApartmentApi.class);
            final CustomDialogFragment dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"");
            ReceiveExpressParam param=new ReceiveExpressParam();
            param.setHandleUserSid(mUserHelper.getSid());
            param.setExpressSids(sid);
            param.setExpressRemark(remark);
            Log.i("数据", "param: "+param.toString());
            api.receiveExpress(param, new HttpCallback<MessageTo>(getThisContext()) {
                        @Override
                        public void success(MessageTo msg, Response response) {
                            Log.i("数据", "success: "+msg.toString());

                            dialogFragment.dismiss();
                            if (msg.getSuccess() == 0) {
                                Log.i("数据", "success: ");
                                ToastShowLong(ReceiveExpressListActivity.this,"领取成功！");
                                initData(1);
                            } else {
                                Toast.makeText(getThisContext(),
                                        msg.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            dialogFragment.dismissAllowingStateLoss();
                            Log.i("数据", "failure: "+error.toString());
                            super.failure(error);
                        }
                    }
            );

            dialog.dismiss();
        });
        btn_close.setOnClickListener((View v) -> {
            dialog.dismiss();

        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                onBackPressed();
                goToAnimation(2);
                break;
            case R.id.receive_history:
                Intent intentone=new Intent(this,ReceiveExpressRecordActivity.class);
                intentone.putExtra("apartmentSid",apartmentSid);
                startActivity(intentone);
                goToAnimation(1);

                break;
            case R.id.camera_receive:
                Intent intent=new Intent(this,PhotoReceiveExpressActivity.class);
                intent.putExtra("apartmentSid",apartmentSid);
                intent.putExtra("mCodeNo",mCodeNo);
                startActivity(intent);
                goToAnimation(1);

                break;
        }
    }
    private void refresh() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex = 1;
                initData(1);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                initData(pageIndex);
            }
        });
    }
    private void initData(final int index) {
        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        FindExpressParam param=new FindExpressParam();
        param.setNextPage(index);
        param.setLimit("20");
        if(mCodeNo!=null){
            param.setSearch(mCodeNo);
        }if (apartmentSid!=null)
        {   param.setApartmentSid(apartmentSid);}
        param.setExpressStatusStr("2");
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        Log.i("数据", "initData: "+param.toString());
        api.findExpressReceiveRecord(param,new HttpCallback<MessageTo<FindExpressRecordTo>>(this) {
                    @Override
                    public void success(MessageTo<FindExpressRecordTo> msg, Response response) {
                        dialogFragment.dismissAllowingStateLoss();
                        if (msg.getSuccess() == 0) {
                            if (index == 1) {
                                expressToList.clear();
                            }
                            if (msg.getData().getList() != null && msg.getData().getList().size() > 0) {
                                expressToList.addAll(msg.getData().getList());
                                adapter.setList(expressToList);
                            }
                            if(expressToList.size()==0){
                                noData.setVisibility(View.VISIBLE);
                            }
                            adapter.notifyDataSetChanged();
                            listView.onRefreshComplete();

                        }
                        Log.i("222", "success: "+msg.toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        dialogFragment.dismissAllowingStateLoss();
                        Log.i("222", "failure: "+error.toString());
                        super.failure(error);
                    }
                }
        );
    }
    private void receiveExpressDialogShow(String sid) {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_receive_express_code, R.style.myDialogTheme);
        Button btn_go = (Button) dialog.findViewById(R.id.btn_go);
        EditText codeNo = (EditText) dialog.findViewById(R.id.codeNo);
        codeNo.setFocusable(true);
        codeNo.setFocusableInTouchMode(true);
        codeNo.requestFocus();
        dialog.findViewById(R.id.parent).setOnClickListener(v -> dialog.dismiss());
        Button btn_close = (Button) dialog.findViewById(R.id.btn_close);
        KeyboardUtilGetExpress keyboardUtil = new KeyboardUtilGetExpress(ReceiveExpressListActivity.this,dialog, codeNo);
        codeNo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("2222", "onTouch: 左边");
                keyboardUtil.hideSoftInputMethod();
                keyboardUtil.showKeyboard();
                return false;
            }
        });
        btn_go.setOnClickListener((View v) -> {
            if(TextUtils.isEmpty(codeNo.getText().toString())){
                ToastShowLong(ReceiveExpressListActivity.this,"请输入取件码"+codeNo.getText());
                return;
            }
            if(keyboardUtil.isShow())
                keyboardUtil.hideKeyboard();
            ApartmentApi api = ApiClient.create(ApartmentApi.class);
            final CustomDialogFragment dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"");
            ReceiveExpressParam param=new ReceiveExpressParam();
            param.setHandleUserSid(mUserHelper.getSid());
            param.setExpressSids(sid);
            param.setExpressRemark(codeNo.getText().toString());
            Log.i("数据", "param: "+param.toString());
            api.receiveExpress(param, new HttpCallback<MessageTo>(getThisContext()) {
                        @Override
                        public void success(MessageTo msg, Response response) {
                            Log.i("数据", "success: "+msg.toString());

                            dialogFragment.dismiss();
                            if (msg.getSuccess() == 0) {
                                Log.i("数据", "success: ");
                                ToastShowLong(ReceiveExpressListActivity.this,"领取成功！");
                                initData(1);
                            } else {
                                Toast.makeText(getThisContext(),
                                        msg.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            dialogFragment.dismissAllowingStateLoss();
                            Log.i("数据", "failure: "+error.toString());
                            super.failure(error);
                        }
                    }
            );

            dialog.dismiss();
        });
        btn_close.setOnClickListener((View v) -> {
            if(keyboardUtil.isShow())
                keyboardUtil.hideKeyboard();
            dialog.dismiss();

        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
    }
    @Override
    protected void onResume() {
        Log.i("0000", "onResume: ");
        initData(1);
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("0000", "onRestart: ");

        super.onRestart();
    }
}
