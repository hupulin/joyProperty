package com.joy.property.manage.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.home.ServiceFindParam;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.complaint.fragment.adapter.ProcessingAdapter;
import com.joy.property.task.ReceiveTaskDetailActivity;
import com.joy.property.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * //带评价
 * Created by Admin on 2015-02-10
 */
public class EvaluateFragment extends BaseFragment {
    private ListView mList;
    private PullToRefreshListView mPullToRefreshListView;
    private List<ServiceMainExpandTo> expandToList = new ArrayList<>();
    private ProcessingAdapter mAdapter;
    private int pageIndex = 0;
    private String mApartmentSid;
    List<ApartmentInfoTo>apartmentInfoTos=new ArrayList<ApartmentInfoTo>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_list, container, false);
        findById(rootView);
        mApartmentSid = getActivity().getIntent().getStringExtra("mode");
        mList = mPullToRefreshListView.getRefreshableView();
        mList.setItemsCanFocus(true);
        mPullToRefreshListView.setMode(Mode.BOTH);
        registerForContextMenu(mList);
        mAdapter = new ProcessingAdapter(getThisContext());
        mAdapter.setList(expandToList);
        mList.setAdapter(mAdapter);
        initData();

        return rootView;

    }


    private void findById(View view) {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.list);
    }

    private void initData() {
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
                pageIndex = 0 ;
                setList(0);
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


                pageIndex++;
                setList(pageIndex);
            }
        });
    }

    private void setList(final int index) {
        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceFindParam param = new ServiceFindParam();
        param.setApartmentSid(mApartmentSid);
        param.setPageIndex(index);
        param.setUsid(mUserHelper.getSid());
        param.setCategory("51979B62-10E6-44C7-88B9-4B239B1CE02F");
        param.setFlowState("2,20,21,22");
        final CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getFragmentManager(), "");
        api.findServiceMain(param, new HttpCallback<MessageTo<List<ServiceMainExpandTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<ServiceMainExpandTo>> msg, Response response) {
                dialog.dismiss();
                if (msg.getSuccess() == 0) {
                    if (index == 0) {
                        expandToList.clear();
                    }
                    if (msg.getData() != null) {
                        expandToList.addAll(msg.getData());
                        mAdapter.setList(expandToList);
                        mAdapter.notifyDataSetChanged();
                        getTime();
                        for (ApartmentInfoTo apartmentInfoTo: apartmentInfoTos){
                            if (SpUtil.getString(getThisContext(), "apart").equals(apartmentInfoTo.getApartmentName())){
                                for (ServiceMainExpandTo serviceMainExpandTo: expandToList){
                                    serviceMainExpandTo.setStartTime(apartmentInfoTo.getStartTime());
                                    serviceMainExpandTo.setEndTime(apartmentInfoTo.getEndTime());
                                }
                                break;
                            }
                        }
                    }
                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ServiceMainExpandTo mainExpandTo = expandToList.get(position - 1);
                            Intent intent = new Intent(getThisContext(), ReceiveTaskDetailActivity.class);
                            intent.putExtra("sid", mainExpandTo.getServiceSid());
                            intent.putExtra("value", 2);
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
        return getActivity();
    }

    @Override
    public void onResume() {
        pageIndex = 0;
        setList(0);
        super.onResume();
    }
    public void getTime(){
        String json= SpUtil.getString(getThisContext(), "TaskCache");
        if(json!=null) {
            try {
                JSONObject obj=new JSONObject(json);
                String js = obj.getString("cache");
                List<ApartmentInfoTo> homeNoticeToList1 = JSON.parseArray(js, ApartmentInfoTo.class);
                apartmentInfoTos.addAll(homeNoticeToList1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
