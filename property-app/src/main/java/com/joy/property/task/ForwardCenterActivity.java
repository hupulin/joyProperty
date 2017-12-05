package com.joy.property.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.property.base.BaseActivity;
import com.joy.property.task.adapter.ForwardCenterAdapter;
import com.joy.property.R;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceAssignParam;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.library.fragment.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-01-27
 */
public class ForwardCenterActivity extends BaseActivity
        implements OnClickListener {

    private TextView mTitle;
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mList;
    private ServiceMainExpandTo mainExpandTo;
    private ForwardCenterAdapter mAdapter;
    private List<UserInfoTo> userInfoToList = new ArrayList<>();
    private String center;
    private String heart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        findById();
        initIntentDatas();
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mAdapter = new ForwardCenterAdapter(this);
        mAdapter.setList(userInfoToList);
        mList.setAdapter(mAdapter);
        setList();
        initDatas();
    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.title);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
    }

    private void initIntentDatas() {
        mainExpandTo = (ServiceMainExpandTo) getIntent().getSerializableExtra("mode");
        center = getIntent().getStringExtra("center");
        heart = getIntent().getStringExtra("heart");
    }

    private void initDatas() {
        if(!TextUtils.isEmpty(center)){
            if (center.equals("center")) {
                mTitle.setText("派单中心");
            }
        }

        if(!TextUtils.isEmpty(heart)) {
            if (heart.equals("heart")) {
                mTitle.setText("转发中心");
            }
        }
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

    private void setList() {

        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceAssignParam param = new ServiceAssignParam();
        param.setGroupUserSid(mUserHelper.getSid());
        param.setTypeSid(mainExpandTo.getTypeSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.assignService(param, new HttpCallback<MessageTo<List<UserInfoTo>>>(this) {
            @Override
            public void success(MessageTo<List<UserInfoTo>> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getSuccess() == 0) {
                    userInfoToList.clear();
                    userInfoToList.addAll(msg.getData());
                    mAdapter.notifyDataSetChanged();
                    mPullToRefreshListView.onRefreshComplete();

                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            UserInfoTo userInfoTo = userInfoToList.get(position - 1);
                            Intent intent = new Intent(getThisContext(), ForwardDescActivity.class);
                            if(!TextUtils.isEmpty(center)){
                                if (center.equals("center")) {
                                    intent.putExtra("left", "left");
                                }
                                }
                            if(!TextUtils.isEmpty(heart)) {
                                if (heart.equals("heart")) {
                                    intent.putExtra("rainbow", "rainbow");
                                }
                                }

                            intent.putExtra("assist", userInfoTo);
                            intent.putExtra("mode", mainExpandTo);
                            startActivity(intent);
                        }
                    });

                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                super.failure(error);
            }
        });

    }
////////////////////////////////////////////////////////



    @Override
    protected Context getThisContext() {
        return ForwardCenterActivity.this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    @Override
    protected String toPageName() {

        return "转发中心";
    }
}
