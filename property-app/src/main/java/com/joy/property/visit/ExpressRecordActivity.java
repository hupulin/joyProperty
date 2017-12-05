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
import com.jinyi.ihome.module.express.ExpressRecordParam;
import com.jinyi.ihome.module.express.ExpressToNew;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.visit.adapter.ExpressRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2017/6/6.
 */

public class ExpressRecordActivity extends BaseActivity implements View.OnClickListener {
    private PullToRefreshListView listView;
    private ExpressRecordAdapter adapter;
    private int                pageIndex     = 0;
    private List<ExpressNewTo> expressToList = new ArrayList<>();
    private CustomDialogFragment dialogFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_record);
        initView();
        initData(0);

        refresh();
    }

    private void initData(final int index) {
        ApartmentApi api = ApiClient.create(ApartmentApi.class);
//        ExpressRecordParam param=new ExpressRecordParam();
//        param.setPageIndex(index);
//        param.setUserSid(mUserHelper.getSid());
        if (dialogFragment == null) {

            dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"");
        }
        Log.i("数据", "initData: "+(mUserHelper.getSid()));
        api.findExpressRecord(mUserHelper.getSid(),index,new HttpCallback<MessageTo<List<ExpressNewTo>>>(this) {
                    @Override
                    public void success(MessageTo<List<ExpressNewTo>> msg, Response response) {
                        dialogFragment.dismissAllowingStateLoss();
                        if (msg.getSuccess() == 0) {
                            if (index == 0) {
                                expressToList.clear();
                            }
                            if (msg.getData() != null && msg.getData().size() > 0) {
                                expressToList.addAll(msg.getData());
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
                        dialogFragment.dismissAllowingStateLoss();
                        super.failure(error);
                    }
                }
        );
    }


    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new ExpressRecordAdapter(getThisContext());
        listView.setAdapter(adapter);
        adapter.setList(expressToList);
        findViewById(R.id.iv_back).setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getThisContext(),ExpressInputDetailActivity.class);
                intent.putExtra("mode", expressToList.get(position-1));
                intent.putExtra("type", "1");
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                goToAnimation(2);
                break;
        }
    }
    private void refresh() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex = 0;
                initData(0);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                initData(pageIndex);
            }
        });
    }
}
