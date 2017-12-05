package com.joy.property.community.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.notice.HomeNoticeTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NoticeApi;
import com.joy.property.R;
import com.joy.property.community.NoticeDetailActivity;
import com.joy.property.community.adapter.NoticeAdapter;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.base.BaseListFragment;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class NoticeFragment extends BaseListFragment<HomeNoticeTo> {

    private NoticeAdapter mAdapter = null;
    private List<HomeNoticeTo> homeNoticeToList = new ArrayList<>();
    private int  pageIndex = 0;
    @Override
    public void init() {
        super.init();
        mList.setDividerHeight(0);
        // mList.setDivider(getResources().getDrawable(R.drawable.divider_block_line));
        //mList.setBackgroundColor(0xffebebeb);
        String json= SpUtil.getString(getThisContext(), "spcache");
        if(json!=null) {
            try {
                JSONObject obj=new JSONObject(json);
                String js = obj.getString("cache");
                List<HomeNoticeTo> homeNoticeToList1 = JSON.parseArray(js, HomeNoticeTo.class);
                homeNoticeToList.addAll(homeNoticeToList1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mAdapter = new NoticeAdapter(getThisContext());
        mAdapter.setList(homeNoticeToList);
        mList.setAdapter(mAdapter);
        if(SpUtil.getBoolean(getThisContext(),"FIRSTLOADNOTICE"))
            setList2(0);
        else {
            setList(0);
        }
        refreshData();
    }



    public void setList(final int i) {

        NoticeApi api = ApiClient.create(NoticeApi.class);

        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getFragmentManager(), "");
        System.out.println(mHelper.getSid() + "aaaaaaa");
        api.findNoticePageBySid(mUserHelper.getSid(), i, new HttpCallback<MessageTo<List<HomeNoticeTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<HomeNoticeTo>> msg, Response response) {

                customDialog.dismissAllowingStateLoss();
                mPullToRefreshListView.onRefreshComplete();
                if (msg == null) return;
                if (msg.getSuccess() == 0) {
                    if (i == 0) {
                        homeNoticeToList.clear();
                    }

                    if (msg.getData() != null) {
                        homeNoticeToList.addAll(msg.getData());
                    }
                    Map<String, List<HomeNoticeTo>> map = new HashMap<String, List<HomeNoticeTo>>();
                    map.put("cache", homeNoticeToList);
                    System.out.println(homeNoticeToList.toString() + "homeNoticeTolist");
                    String json = JSON.toJSONString(map);
                    SpUtil.put(getThisContext(), "spcache", json);
                    SpUtil.put(getThisContext(), "FIRSTLOADNOTICE", true);
                    mAdapter.notifyDataSetChanged();

                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            HomeNoticeTo homeTo = homeNoticeToList.get(position - 1);
                            Log.i("222", "111");
                            Intent intent = new Intent(getThisContext(), NoticeDetailActivity.class);
                            intent.putExtra("sid", homeTo.getNoticeSid());
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    });
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                customDialog.dismissAllowingStateLoss();
                mPullToRefreshListView.onRefreshComplete();
                super.failure(error);
            }
        });

    }

    public void setList2(final int i) {

        NoticeApi api = ApiClient.create(NoticeApi.class);
       // mUserHelper.getSid()
        api.findNoticePageBySid(mUserHelper.getSid(), i, new HttpCallback<MessageTo<List<HomeNoticeTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<HomeNoticeTo>> msg, Response response) {

                mPullToRefreshListView.onRefreshComplete();
                if (msg == null) return;
                if (msg.getSuccess() == 0) {
                    if (i == 0) {
                        homeNoticeToList.clear();
                    }

                    homeNoticeToList.addAll(msg.getData());
                    Map<String, List<HomeNoticeTo>> map = new HashMap<String, List<HomeNoticeTo>>();
                    map.put("cache", homeNoticeToList);
                    String json = JSON.toJSONString(map);
                    SpUtil.put(getThisContext(), "spcache", json);
                    System.out.println(homeNoticeToList.toString() + "homeNoticeTolist");
                    SpUtil.put(getThisContext(), "FIRSTLOADNOTICE", true);
                    mAdapter.notifyDataSetChanged();

                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            HomeNoticeTo homeTo = homeNoticeToList.get(position - 1);
                            Log.i("222", "222");

                            Intent intent = new Intent(getThisContext(), NoticeDetailActivity.class);
                            intent.putExtra("sid", homeTo.getNoticeSid());
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    });
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {

                mPullToRefreshListView.onRefreshComplete();
                super.failure(error);
            }
        });

    }


    @Override
    public void refreshList(int key) {
        setList(1);

    }

    // 上下拉刷新
    private void refreshData() {
        /**
         * 刷新监听
         */
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                setList(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
//                String label = DateUtils.formatDateTime(
//                        getThisContext(),
//                        System.currentTimeMillis(),
//                        DateUtils.FORMAT_SHOW_TIME
//                                | DateUtils.FORMAT_SHOW_DATE
//                                | DateUtils.FORMAT_ABBREV_ALL);
//                // Update the LastUpdatedLabel
//                listViewPullToRefreshBase.getLoadingLayoutProxy()
//                        .setLastUpdatedLabel(label);
                pageIndex++;
                setList(pageIndex);

            }
        });
    }



        @Override
    public void moreList(int key) {
        setList(key);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mList.destroyDrawingCache();
    }

    @Override
    protected Context getThisContext() {
        return getActivity();
    }
}