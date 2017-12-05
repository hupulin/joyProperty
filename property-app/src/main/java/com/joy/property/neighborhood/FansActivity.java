package com.joy.property.neighborhood;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodUserConnectParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodUserConnectTo;
import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;

import com.joy.property.R;
import com.joy.property.neighborhood.adapter.FansAdapter;
import com.joy.property.base.BaseActivity;

import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/11/2.
 **/
public class FansActivity extends BaseActivity implements View.OnClickListener {

    private PullToRefreshListView listView;
    private List<OwnerMessageTo> fansList=new ArrayList<>();
    private FansAdapter adapter;
    private int pagerIndex;
    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbor_join);
        fansList= (List<OwnerMessageTo>) getIntent().getSerializableExtra("fansList");
        initView();


        if (fansList==null||fansList.size()==0) {
            fansList=new ArrayList<>();
            initData(0);

        }
        refresh();
    }



    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        adapter = new FansAdapter(getThisContext(),getIntent().getBooleanExtra("JoinCampaign",false));
        adapter.setList(fansList);
        listView.setAdapter(adapter);
        titleText = (TextView) findViewById(R.id.titleText);
        titleText.setText("粉丝");
        if (fansList!=null&&fansList.size()>0) {
            titleText.setText("更多");
            listView.setMode(PullToRefreshBase.Mode.DISABLED);
        }
        findViewById(R.id.iv_back).setOnClickListener(this);

    }
    private void initData(int index) {
        NeighborApi api= ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");
        Log.i("33333", "onPullDownToRefresh:下拉了");

        api.getFansList(mUserHelper.getSid(), index, new HttpCallback<MessageTo<List<OwnerMessageTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<OwnerMessageTo>> msg, Response response) {
                customDialog.dismiss();
                if (msg.getSuccess() == 0) {
                    if (index == 0)
                        fansList.clear();
                    fansList.addAll(msg.getData());
                    adapter.setList(fansList);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    listView.setOnItemClickListener((parent, view, position, id) -> {
                        if ("粉丝".equals(titleText.getText().toString()))
                            return;

                        Intent intent = new Intent(getThisContext(), MyNeighborActivity.class);
                        if (mUserHelper.getSid().equals( fansList.get(position - 1).getOwnerSid()))
                            intent.putExtra("otherMainPage", false);
                        else
                        intent.putExtra("otherMainPage", true);

                        intent.putExtra("otherSid", fansList.get(position - 1).getOwnerSid());

                        intent.putExtra("messageTo", fansList.get(position - 1));
                        startActivity(intent);

                        goToAnimation(1);
                    });
                    setCare(adapter);

                } else {
                    if ("用户不存在".equals(msg.getMessage()))
                        ToastShowLong(getThisContext(), "暂无粉丝");
                        else
                    ToastShowLong(getThisContext(), msg.getMessage());
                }
                listView.onRefreshComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                customDialog.dismiss();
                System.out.println(error.toString());
                listView.onRefreshComplete();
            }
        });


    }
    private void refresh() {
listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        initData(0);
        Log.i("33333", "onPullDownToRefresh:下拉了");
        pagerIndex = 0;
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pagerIndex++;
        initData(pagerIndex);
    }
});
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                goToAnimation(2);
                break;
        }
    }
    public void setCare(FansAdapter fansAdapter){
        fansAdapter.setOnCancelCareListener((ownerMessageTo, care) -> {
            if (ownerMessageTo.getFlag()==0)
            getCare(care,ownerMessageTo);
        });

    }
    public void getCare(TextView care,OwnerMessageTo ownerMessageTo){
        NeighborApi api= ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");
        NeighborhoodUserConnectParam param=new NeighborhoodUserConnectParam();
        param.setOwnerSid(mUserHelper.getSid());
        param.setToOwnerSid(ownerMessageTo.getOwnerSid());
        System.out.println(getIntent().getStringExtra("otherSid")+"------------");
        api.getCare(param, new HttpCallback<MessageTo<NeighborhoodUserConnectTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborhoodUserConnectTo> msg, Response response) {
                customDialog.dismiss();
    //              System.out.println(msg.toString());
                care.setText("已关注");
                care.setTextColor(Color.parseColor("#d9d9d9"));
                ToastShowLong(getThisContext(), "关注成功");

            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
                System.out.println(error.toString());
            }
        });
    }
}
