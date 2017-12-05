package com.joy.property.person;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.neighborhood.NeighborActivity;
import com.joy.property.person.adapter.NeighborListAdapter;
import com.joy.property.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class MyNeighbor extends BaseActivity
        implements OnClickListener {

    private PullToRefreshListView mPullToRefreshListView;
    private List<ServiceReportFlowTo> flowToList = new ArrayList<>();
    private ListView mList;
    private TextView mTitle;
    private NeighborListAdapter mAdapter;
    private   ApartmentInfoTo info = new ApartmentInfoTo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbor);
        findById();
        mList = mPullToRefreshListView.getRefreshableView();
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mPullToRefreshListView.setMode(Mode.BOTH);
        mAdapter = new NeighborListAdapter(this);
        mAdapter.setList(flowToList);
        mList.setAdapter(mAdapter);

//        if(SpUtil.getBoolean(getThisContext(),"ApartmentList_First_Load"))
//            setList2();
//        else
        setList();
        initData();
    }


    private void findById() {
        mTitle = (TextView) findViewById(R.id.title2);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);

    }

    private void initData() {
        mTitle.setText("邻居圈");
        mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {

                String label = DateUtils.formatDateTime(
                        getThisContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                listViewPullToRefreshBase.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                setList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                String label = DateUtils.formatDateTime(
                        getThisContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                listViewPullToRefreshBase.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                setList();
            }
        });

    }

    /*
     *  拉取数据
     */
    private void setList() {

        NeighborApi api=ApiClient.create(NeighborApi.class);
        final CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "");
        api.getApartmentList(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ServiceReportFlowTo>>>(this) {

            @Override
            public void success(MessageTo<List<ServiceReportFlowTo>> msg, Response response) {
                dialog.dismiss();

                if (msg.getSuccess() == 0) {

                    if (msg.getData() != null) {
                        flowToList.clear();
                        flowToList.addAll(msg.getData());
                        Map<String, List<ServiceReportFlowTo>> map = new HashMap<>();
                        map.put("Neighborhood_Cache", flowToList);
                        String json = JSON.toJSONString(map);
                        SpUtil.put(getThisContext(), "Neighborhood_Apartment_Cache", json);
                        SpUtil.put(getThisContext(), "ApartmentList_First_Load", true);
                        mAdapter.notifyDataSetChanged();
                    }
                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            ServiceReportFlowTo flowTo = flowToList.get(position - 1);
                            String SrflowTo = flowTo.getApartmentName();
                            SpUtil.put(MyNeighbor.this, "xf_title", SrflowTo);
                            Intent intent = new Intent(getThisContext(), MyNeighborActivity.class);
                            intent.putExtra("fto", flowTo);
                            info.setApartmentSid(flowTo.getApartmentSid());
                            mHelper.updateApartment(info,getThisContext());
                            startActivity(intent);


                        }
                    });
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialog.dismiss();
                super.failure(error);
            }
        });

    }

    @Override
    protected Context getThisContext() {
        return MyNeighbor.this;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    @Override
    protected String toPageName() {
        super.toPageName();
        return "邻居圈";
    }
    public void getList(){
        String json = SpUtil.getString(getThisContext(), "Neighborhood_Apartment_Cache");
        if (json!= null) {
            try {
                JSONObject obj = new JSONObject(json);
                String js = obj.getString("Neighborhood_Cache");
                List<ServiceReportFlowTo> homeNoticeToList1 = JSON.parseArray(js, ServiceReportFlowTo.class);
                flowToList.addAll(homeNoticeToList1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void setList2() {
        getList();
        NeighborApi api=ApiClient.create(NeighborApi.class);

        api.getApartmentList(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ServiceReportFlowTo>>>(this) {
            @Override
            public void success(MessageTo<List<ServiceReportFlowTo>> msg, Response response) {
                if (msg.getSuccess() == 0) {

                    if (msg.getData() != null) {
                        flowToList.clear();
                        flowToList.addAll(msg.getData());
                        Map<String, List<ServiceReportFlowTo>> map = new HashMap<>();
                        map.put("Neighborhood_Cache", flowToList);
                        String json = JSON.toJSONString(map);

                        SpUtil.put(getThisContext(), "Neighborhood_Apartment_Cache", json);
                        mAdapter.notifyDataSetChanged();
                    }
                    mPullToRefreshListView.onRefreshComplete();

                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(flowToList==null)
                                getList();
                            ServiceReportFlowTo flowTo = flowToList.get(position - 1);

                            Intent intent = new Intent(getThisContext(), NeighborActivity.class);

                            info.setApartmentSid(flowTo.getApartmentSid());

                            mHelper.updateApartment(info,getThisContext());
                            startActivity(intent);

                        }
                    });

                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });

    }
}
