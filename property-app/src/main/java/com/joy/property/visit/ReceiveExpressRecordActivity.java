package com.joy.property.visit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.visit.adapter.ReceiveExpressRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2017/8/1.
 */

public class ReceiveExpressRecordActivity extends BaseActivity implements View.OnClickListener {
    private PullToRefreshListView listView;
    private ReceiveExpressRecordAdapter adapter;
    private String      apartmentSid    ;
    private int                pageIndex     = 1;

    private List<ExpressNewTo> expressToList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_express_record);
        initView();
        initData(1);
        refresh();
    }

    private void initView() {
        apartmentSid=getIntent().getStringExtra("apartmentSid");

        listView = (PullToRefreshListView) findViewById(R.id.listView);
        adapter = new ReceiveExpressRecordAdapter(getThisContext());
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setAdapter(adapter);

        adapter.setList(expressToList);
        findViewById(R.id.back).setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getThisContext(),ExpressInputDetailActivity.class);
                intent.putExtra("mode", expressToList.get(position-1));
                intent.putExtra("type", "2");
                startActivity(intent);
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
        param.setExpressStatusStr("3");
//        ExpressRecordParam param=new ExpressRecordParam();
//        param.setPageIndex(index);
        Log.i("2222", "initData: "+apartmentSid);
        if(apartmentSid!=null){
        param.setApartmentSid(apartmentSid);}
        param.setHandleUserSid(mUserHelper.getSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        Log.i("param", "initData: "+param.toString());
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
                        Log.i("222", "failure: "+error.toString());
                        dialogFragment.dismiss();
                        super.failure(error);
                    }
                }
        );
    }
}
