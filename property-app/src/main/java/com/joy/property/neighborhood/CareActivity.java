package com.joy.property.neighborhood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborJoinMessageParam;
import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.neighborhood.adapter.CareAdapter;
import com.joy.property.utils.SpUtil;
import com.joy.property.base.BaseActivity;

import com.joyhome.nacity.app.util.CustomDialogFragment;


import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/11/2.
 **/
public class CareActivity extends BaseActivity implements View.OnClickListener {

    private PullToRefreshListView listView;
private List<OwnerMessageTo>careList=new ArrayList<>();
    private CareAdapter adapter;
private int pagerIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbor_join);
        initView();
        initData(0);
        refresh();
        careOrCancel();
        showPoint();
    }



    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        adapter = new CareAdapter(getThisContext());
        adapter.setList(careList);
        listView.setAdapter(adapter);
        TextView titleText = (TextView) findViewById(R.id.titleText);
        titleText.setText("关注");
        findViewById(R.id.iv_back).setOnClickListener(this);
    }
    private void initData(int index) {
        NeighborApi api= ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");
        api.getCareList(mUserHelper.getSid(), index, new HttpCallback<MessageTo<List<OwnerMessageTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<OwnerMessageTo>> msg, Response response) {
                customDialog.dismiss();
                if (msg.getSuccess() == 0) {
                    if (index == 0)
                        careList.clear();
                    careList.addAll(msg.getData());
                    adapter.notifyDataSetChanged();

//                    listView.setOnItemClickListener((parent, view, position, id) -> {
//
//                        Intent intent = new Intent(getThisContext(), MyNeighborActivity.class);
//                        intent.putExtra("otherMainPage", true);
//                        intent.putExtra("messageTo", careList.get(position - 1));
//                        intent.putExtra("otherSid", careList.get(position-1).getOwnerSid());
//
//                        startActivity(intent);
//                    });
                } else
                    ToastShowLong(getThisContext(), msg.getMessage());
                listView.onRefreshComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                customDialog.dismiss();
                listView.onRefreshComplete();
            }
        });


    }
    private void refresh() {
listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pagerIndex = 0;
        initData(0);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pagerIndex++;
        initData(pagerIndex);
    }
});
    }

    private void careOrCancel() {
        adapter.setOnCancelCareListener(messageTo -> {
            cancelCare(messageTo);
        });
    }
    public void showPoint(){
        adapter.setshowPointListener((showPoint, messageTo) -> {
getCarePoint(showPoint,messageTo.getOwnerSid());
        });
    }
    public void cancelCare(OwnerMessageTo messageTo){
        NeighborApi api= ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");
        System.out.println(mUserHelper.getSid() + "/" +messageTo.getOwnerSid());
        api.deleteCare(mUserHelper.getSid(), messageTo.getOwnerSid(), new HttpCallback<MessageTo<Boolean>>(getThisContext()) {
            @Override
            public void success(MessageTo<Boolean> msg, Response response) {
                customDialog.dismiss();
                if (msg.getSuccess() == 0) {
                    careList.remove(messageTo);
                    initData(pagerIndex);
                    ToastShowLong(getThisContext(), "取消关注成功");
                } else
                    ToastShowLong(getThisContext(), msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());
                customDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
    public void getCarePoint(TextView redPoint,String ownerSid){
        new Thread(() -> {
            NeighborApi api= ApiClient.create(NeighborApi.class);
            NeighborJoinMessageParam param=new NeighborJoinMessageParam();
            param.setPageIndex(0);
            param.setApartmentSid(mHelper.getSid());
            param.setPostOwner(ownerSid);

            if (SpUtil.getString(getThisContext(), "neighborCareTimeOther")==null)
                param.setLastTime(DateUtil.getDate());
            else
                param.setLastTime(DateUtil.getFormatDateLongTime(SpUtil.getString(getThisContext(), "neighborCareTimeOther")));
            System.out.println(param+"param");
            api.getCareRedPoint(param, new HttpCallback<MessageTo<Integer>>(getThisContext()) {
                @Override
                public void success(MessageTo<Integer> msg, Response response) {
                    SpUtil.put(getThisContext(), "neighborCareTimeOther", DateUtil.getDateString());
                    System.out.println(msg+"total");
                    runOnUiThread(() -> {
                        if (msg.getTotal() > 0) {
                            redPoint.setVisibility(View.VISIBLE);
                        }else
                            redPoint.setVisibility(View.GONE);
                    });
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }).start();


    }
}
