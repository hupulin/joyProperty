package com.joy.property.vehicle.fragment;

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
import com.jinyi.ihome.module.home.ServiceFindParam;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.task.ReceiveTaskDetailActivity;
import com.joy.property.task.adapter.RepairsUnClaimedAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by usb on 2017/3/1.
 */


    public class VehicleUnclaimedFragment extends BaseFragment {

        private PullToRefreshListView mPullToRefreshListView;
        private ListView mList;
        private int pageIndex = 0;
        private RepairsUnClaimedAdapter mAdapter = null;
        private String apartmentSid ="";
        private List<ServiceMainExpandTo> expandToList = new ArrayList<>();

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View rootView = inflater.inflate(R.layout.activity_list, container, false);

            findById(rootView);
            apartmentSid = getActivity().getIntent().getStringExtra("mode");
            mList = mPullToRefreshListView.getRefreshableView();
            mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
            mList.setItemsCanFocus(true);
            registerForContextMenu(mList);
            mAdapter = new RepairsUnClaimedAdapter(getThisContext());
            mAdapter.setList(expandToList);
            mList.setAdapter(mAdapter);
            setList(0);
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

                    pageIndex ++;
                    setList(pageIndex);
                }
            });
        }

        private void setList(final int index) {

            HomeApi api = ApiClient.create(HomeApi.class);
            ServiceFindParam param = new ServiceFindParam();
            param.setCategory("5CFB60A1-C1FC-4751-B123-05157F02C70D");
            param.setUsid(mUserHelper.getSid());
            param.setFlowState("1");
            param.setPageIndex(index);
            param.setApartmentSid(apartmentSid);
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
                        expandToList.addAll(msg.getData());
                        mAdapter.notifyDataSetChanged();
                        mPullToRefreshListView.onRefreshComplete();
                        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ServiceMainExpandTo expandTo = expandToList.get(position-1);
                                Intent intent = new Intent(getThisContext(), ReceiveTaskDetailActivity.class);
                                intent.putExtra("sid" ,expandTo.getServiceSid());
                                intent.putExtra("value" ,2);
                                startActivity(intent);
                            }
                        });

                    }else {
                        Toast.makeText(getThisContext(),
                                msg.getMessage(),Toast.LENGTH_SHORT).show();
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
            super.onResume();
        }
    }
