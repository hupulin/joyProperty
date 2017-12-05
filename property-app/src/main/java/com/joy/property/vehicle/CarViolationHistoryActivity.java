package com.joy.property.vehicle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.joy.common.api.ApiClient;
import com.joy.common.api.CarApi;
import com.joy.common.api.HttpCallback;
import com.joy.property.R;

import com.joy.property.base.BaseActivity;
import com.joy.property.vehicle.adapter.CarViolationHistoryAdapter;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.car.CarViolationHistoryParam;
import com.jinyi.ihome.module.car.CarViolationTo;
import com.joy.library.fragment.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class CarViolationHistoryActivity extends BaseActivity
        implements OnClickListener {


    private String mCarNo = "";
    private TextView mTitle;
    private PullToRefreshListView mPullToRefreshListView;
    private List<CarViolationTo> violationToList = new ArrayList<>();
    private CarViolationHistoryAdapter mAdapter;
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        findById();
        mAdapter = new CarViolationHistoryAdapter(this);
        mList = mPullToRefreshListView.getRefreshableView();
        mList.setItemsCanFocus(true);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        registerForContextMenu(mList);
        mAdapter.setList(violationToList);
        mList.setAdapter(mAdapter);
        getIntentData();
        setList();
        InitializeData();

    }


    private void findById() {
        mBack = (android.widget.ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.title);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
    }

    private void getIntentData() {
        mCarNo = getIntent().getStringExtra("carNo");

    }

    private void InitializeData() {
        mTitle.setText("车辆管理");
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
        CarApi api = ApiClient.create(CarApi.class);
        CarViolationHistoryParam param = new CarViolationHistoryParam();
        param.setCarNo(mCarNo);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.violationHistory(param, new HttpCallback<MessageTo<List<CarViolationTo>>>(this) {
            @Override
            public void success(MessageTo<List<CarViolationTo>> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    violationToList.clear();
                    violationToList.addAll(msg.getData());
                    mAdapter.notifyDataSetChanged();
                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            CarViolationTo violationTo = violationToList.get(position-1);
                            Intent intent = new Intent(getThisContext(), CarViolationDetailActivity.class);
                            intent.putExtra("CarViolationTo", violationTo);
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    protected Context getThisContext() {

        return CarViolationHistoryActivity.this;
    }

    @Override
    protected String toPageName() {
         super.toPageName();
        return "车辆违停列表";
    }
}
