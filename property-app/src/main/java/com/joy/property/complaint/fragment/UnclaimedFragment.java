package com.joy.property.complaint.fragment;

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

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.property.base.BaseFragment;
import com.joy.property.task.ReceiveTaskDetailActivity;
import com.joy.property.R;

import com.joy.property.complaint.fragment.adapter.UnclaimedAdapter;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceFindParam;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.library.fragment.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-02-10
 * //处理中
 */
public class UnclaimedFragment extends BaseFragment {

    private PullToRefreshListView mPullToRefreshListView;
    private ListView mList;
    private int pageIndex = 0;
    private UnclaimedAdapter mAdapter;
    private String apartmentSid;
    private List<ServiceMainExpandTo> expandToList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rooView = inflater.inflate(R.layout.activity_list, container, false);
        findById(rooView);
        apartmentSid = getActivity().getIntent().getStringExtra("mode");
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mAdapter = new UnclaimedAdapter(getActivity());
        mAdapter.setList(expandToList);
        mList.setAdapter(mAdapter);
        initData();
        return rooView;
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
                listViewPullToRefreshBase.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                pageIndex =  0 ;
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
        param.setPageIndex(index);
        param.setCategory("7D2B996C-12EC-4CD4-8499-B453E96AF11F");
        param.setApartmentSid(apartmentSid);
        param.setUsid(mUserHelper.getSid());
        param.setFlowState("1");
        final CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getFragmentManager(), "");
        api.findServiceMain(param, new HttpCallback<MessageTo<List<ServiceMainExpandTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<ServiceMainExpandTo>> msg, Response response) {
                dialog.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    if (index == 0) {
                        expandToList.clear();
                       }
                    if (msg.getData() != null) {
                        expandToList.addAll(msg.getData());
                        mAdapter.notifyDataSetChanged();
                    }
                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ServiceMainExpandTo  mainExpandTo = expandToList.get(position-1);
                            Intent intent = new Intent(getThisContext(), ReceiveTaskDetailActivity.class);
                            intent.putExtra("sid",mainExpandTo.getServiceSid());
                            intent.putExtra("value",2);
                            startActivity(intent);
                        }
                    });


                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialog.dismissAllowingStateLoss();
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
        pageIndex = 0 ;
        setList(0);
        super.onResume();
    }
}
