package com.joy.property.visit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.express.ExpressNewTo;
import com.jinyi.ihome.module.express.ExpressRecordTo;
import com.jinyi.ihome.module.express.FindExpressParam;
import com.jinyi.ihome.module.express.FindExpressRecordTo;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.SystemApi;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.CustomDialog;
import com.joy.property.visit.adapter.ExpressReceiveAdapter;
import com.joy.property.visit.adapter.PhotoReveciveExpressAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/** 拍照领取的快递列表
 * Created by usb on 2017/8/2.
 */

public class PhotoReceiveExpressActivity extends BaseActivity implements View.OnClickListener {
    private PullToRefreshListView listView;
    private String      apartmentSid    ;
    private String      mCodeNo    ;
    private PhotoReveciveExpressAdapter adapter;
    private int                pageIndex     = 1;

    private List<ExpressNewTo>    expressToList       = new ArrayList<>();
    private List<ExpressNewTo> expressToListselect = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_receive_expres);
        initView();
        initIntentData();

        initData(1);
        getPhotoLimit();

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
                    Log.i("222222222", "success:2 "+msg.toString());
                    if(!msg.getData()){
                        onBackPressed();
                        goToAnimation(2);
                       Toast.makeText(getThisContext(), "该权限已关闭" , Toast.LENGTH_LONG).show();
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
        Log.i("数据", "apartmentSid"+apartmentSid+"mCodeNo"+mCodeNo);
    }

    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new PhotoReveciveExpressAdapter(getThisContext());
        listView.setAdapter(adapter);
        adapter.setList(expressToList);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.confirm).setOnClickListener(this);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 //                intent.putExtra("mode", expressToList.get(position-1));
//                startActivity(intent);
//            }
//        });
        adapter.setSelectExpressListener(new PhotoReveciveExpressAdapter.SelectExpressListener() {
            @Override
            public void OnSelectExpress(boolean select, int position) {
                if (select) {
                    expressToList.get(position).setIsSelect(true);
                } else{
                   expressToList.get(position).setIsSelect(false);

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                onBackPressed();
                goToAnimation(2);
                break;
            case R.id.confirm:
                expressToListselect.clear();
                for (ExpressNewTo i:expressToList){
                    if(i.isSelect()){
                        expressToListselect.add(i);
                    }
                }
                if(expressToListselect==null||expressToListselect.size()==0){
                    ToastShowLong(this,"请选择快递");
                    return;
                }
               Intent intent =new Intent(this,PhotoReceiveDetailActivity.class);
                intent.putExtra("apartmentSid",apartmentSid);
                intent.putExtra("infoList", (Serializable) expressToListselect);
                startActivity(intent);
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
        {
            param.setApartmentSid(apartmentSid);}
        param.setExpressStatusStr("2");
        final com.joyhome.nacity.app.util.CustomDialogFragment dialogFragment = new com.joyhome.nacity.app.util.CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findExpressReceiveRecord(param,new HttpCallback<MessageTo<FindExpressRecordTo>>(this) {
                    @Override
                    public void success(MessageTo<FindExpressRecordTo> msg, Response response) {
                        dialogFragment.dismiss();
                        if (msg.getSuccess() == 0) {
                            if (index == 1) {
                                expressToList.clear();
                            }
                            if (msg.getData().getList() != null && msg.getData().getList().size() > 0) {
                                expressToList.addAll(msg.getData().getList());
                                adapter.setList(expressToList);
                                adapter.notifyDataSetChanged();
                            }
                            listView.onRefreshComplete();

                        }
                        Log.i("222", "success: "+msg.toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        dialogFragment.dismiss();
                        Log.i("222", "failure: "+error.toString());
                        super.failure(error);
                    }
                }
        );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}

