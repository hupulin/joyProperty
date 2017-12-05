package com.joy.property.shop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.newshop.GoodsCommentTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;

import com.joy.property.shop.adapter.GoodsCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;


//Created by xz on 2016/5/23.

@SuppressLint("ValidFragment")
public class GoodsCommentFragment extends BaseFragment {

    private PullToRefreshListView mPullToRefreshListView;
    private int pageIndex = 0;
    private ListView mList;
    private List<GoodsCommentTo> smtList = new ArrayList<>();
    private GoodsCommentAdapter mAdapter = null;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mRootView = inflater.inflate(R.layout.fragment_goods_comment, container, false);
        findById(mRootView);

        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mList.setItemsCanFocus(true);
        mList.requestFocus();
        registerForContextMenu(mList);
        mAdapter = new GoodsCommentAdapter(getThisContext());
        mAdapter.setList(smtList);
        mList.setAdapter(mAdapter);

        initData();
        setList(0);
        return mRootView;
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
                pageIndex = 0;
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
             //   setList(pageIndex);


            }
        });


    }


    private void findById(View view) {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.listView);
        RelativeLayout unListView = (RelativeLayout) view.findViewById(R.id.un_listView);
        unListView.setFocusable(true);
        unListView.setFocusableInTouchMode(true);
         mPullToRefreshListView.requestFocus();
    }


    private void setList(final int index) {
        if (!TextUtils.isEmpty(mHelper.getSid())) {
            NewShopApi api = ApiClient.create(NewShopApi.class);
            api.getComment(new HttpCallback<MessageTo<List<GoodsCommentTo>>>(getThisContext()) {
                @Override
                public void success(MessageTo<List<GoodsCommentTo>> msg, Response response) {
                  if(msg.getSuccess()==0){
                      smtList.addAll(msg.getData());
                      System.out.println(smtList + "smtList------------------------------");
                          mAdapter.notifyDataSetChanged();
                  }
                 mPullToRefreshListView.onRefreshComplete();
                  //  mAdapter.notifyDataSetChanged();

                }
            });
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected Context getThisContext() {
        return getActivity();
    }

}