package com.joy.property.myservice.fragment;

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
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.inspection.CallDetailActivity;
import com.joy.property.inspection.adapter.MySubmissionAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-02-02
 */
public class MyInspectFragment extends BaseFragment {
    private PullToRefreshListView mPullToRefreshListView;
    private int pageIndex = 0;
    private ListView mList;
    private MySubmissionAdapter mAdapter =null;
    private List<ServiceMainExpandTo> mainExpandToList = new ArrayList<>();
    private static MyInspectFragment instance = null;
    private CustomDialogFragment dialogFragment = null;
    public static MyInspectFragment getInstance() {
        if (instance == null) {
            instance = new MyInspectFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mRootView = inflater.inflate(R.layout.activity_list, container, false);
        findById(mRootView);
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mAdapter = new MySubmissionAdapter(getThisContext());
        mAdapter.setList(mainExpandToList);
        mList.setAdapter(mAdapter);
        setList(0);
         initDatas();
        return mRootView;
    }

    protected Context getThisContext() {
        return getActivity();
    }


    private void findById(View view) {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.list);
    }

    private void initDatas() {
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

                pageIndex ++ ;
                setList(pageIndex);
            }
        });
    }

    private void setList(final int index) {
        HomeApi api = ApiClient.create(HomeApi.class);
        if (dialogFragment == null) {
            dialogFragment = new CustomDialogFragment();
            dialogFragment.show(getFragmentManager(), "");
        }

        api.getMyInspectList(mUserHelper.getSid(),"3", index, new HttpCallback<MessageTo<List<ServiceMainExpandTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<ServiceMainExpandTo>> msg, Response response) {
                dialogFragment.dismiss();

                if (msg==null)
                    return;
                if (msg.getSuccess() == 0) {
                    if (index == 0) {
                        mainExpandToList.clear();
                    }
                    if (msg.getData() != null) {
                        mainExpandToList.addAll(msg.getData());
                        mAdapter.notifyDataSetChanged();

                    }


                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ServiceMainExpandTo main = mainExpandToList.get(position - 1);
                            Intent intent = new Intent(getThisContext(), CallDetailActivity.class);
                            intent.putExtra("sid", main.getServiceSid());
                            intent.putExtra("InlegalType", "5CFB60A1-C1FC-4751-B123-05157F02C70D".equals(main.getServiceCategory()));
                            intent.putExtra("type" ,"1");
                            startActivity(intent);
                            goToAnimation(1);
                        }
                    });
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        setList(pageIndex);
    }
}
