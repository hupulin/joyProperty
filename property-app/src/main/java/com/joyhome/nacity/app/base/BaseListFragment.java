package com.joyhome.nacity.app.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;


/**
 * Created by Admin on 2015-07-09
 */
public abstract class BaseListFragment<T> extends BaseFragment {

    public PullToRefreshListView mPullToRefreshListView;
    public ListView mList;
    public int mPageIndex = 0 ;
    public abstract void setList(int index);
    public abstract void refreshList(int key);
    public abstract void moreList(int key);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mPullToRefreshListView = new PullToRefreshListView(getThisContext());
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mList.setItemsCanFocus(true);
        mList.setDividerHeight(2);
        mList.setCacheColorHint(0x00000000);
        mList.setFastScrollEnabled(false);
        mList.setFooterDividersEnabled(false);
        mList.setHeaderDividersEnabled(false);
        mList.setSelector(R.color.transparent);
        mList.setSmoothScrollbarEnabled(true);
        mList.setVerticalScrollBarEnabled(false);
        registerForContextMenu(mList);
        init();
        if (!TextUtils.isEmpty(mHelper.getSid())) {

            initData();
        }

        return mPullToRefreshListView;
    }

    public void init() {

    }

    private void initData() {

        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                String label = DateUtils.formatDateTime(
                        getThisContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                pullToRefreshBase.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                mPageIndex = 0;
                refreshList(0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                String label = DateUtils.formatDateTime(
                        getThisContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                pullToRefreshBase.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                mPageIndex++;
                moreList(mPageIndex);
            }
        });
    }
    public void setListMode(int i) {
        switch (i) {
            case 0:
                mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
                break;
            case 1:
                mPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                break;
            case 2:
                mPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
                break;
            case 3:
                mPullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
                break;
        }

    }


}
