package com.joy.property.host;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceReportGradeTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.host.adapter.OwnDetailAdapter;
import com.joy.property.host.adapter.OwnerMouthAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2016/5/25.
 */
public class OwnerMouthDetail extends BaseActivity implements View.OnClickListener {

    private PullToRefreshListView mPullToRefreshListView;
    private OwnerMouthAdapter mAdapter;
    private List<ServiceReportGradeTo> gradeToList = new ArrayList<>();
    private ListView mList;
    private TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownermouth);
        findById();
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mAdapter = new OwnerMouthAdapter(getThisContext());
        mAdapter.setList(gradeToList);
        mList.setAdapter(mAdapter);
        setList();
        initData();

    }

    private void findById() {
        mTitle = (TextView) findViewById(R.id.title2);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        findViewById(R.id.sideBar).setVisibility(View.GONE);
    }
    private void initData() {
        Intent intent = getIntent();
        // 获取该Intent所携带的数据
        Bundle bundle = intent.getExtras();
        // 从bundle数据包中取出数据
        ServiceReportGradeTo flowTo = (ServiceReportGradeTo) bundle.getSerializable("flowTo");
        mTitle.setText("满意度(" + flowTo.getApartmentName() + ")");

    }
    private void setList() {
        HomeApi api = ApiClient.create(HomeApi.class);
        final CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "");
        api.reportGradeNew(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ServiceReportGradeTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<ServiceReportGradeTo>> msg, Response response) {
                dialog.dismiss();
                if (msg.getSuccess() == 0) {
                    if (msg.getData() != null) {
                        gradeToList.clear();
                        gradeToList.addAll(msg.getData());
                        List<ServiceReportGradeTo> reportGradeTos = new ArrayList<ServiceReportGradeTo>();
                        // 获取启动该ResultActivity的Intent
                        Intent intent = getIntent();
                        // 获取该Intent所携带的数据
                        Bundle bundle = intent.getExtras();
                        // 从bundle数据包中取出数据
                        ServiceReportGradeTo flowTo = (ServiceReportGradeTo) bundle.getSerializable("flowTo");
                        for (ServiceReportGradeTo reportGradeTo : gradeToList) {
                            if (reportGradeTo.getApartmentSid().equals(flowTo.getApartmentSid())) {
                                reportGradeTos.clear();
                                reportGradeTos.add(reportGradeTo);
                            }
                        }
                        gradeToList.clear();
                        gradeToList.addAll(setGradeList(reportGradeTos.get(0)));
                        mAdapter.notifyDataSetChanged();
                    }
                    mPullToRefreshListView.onRefreshComplete();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    @Override
    protected Context getThisContext() {
        return OwnerMouthDetail.this;
    }

    @Override
    protected String toPageName() {
        return "邻里口碑";
    }
    public List<ServiceReportGradeTo> setGradeList(ServiceReportGradeTo listGradeTo){
        List<ServiceReportGradeTo>gradeToList=new ArrayList<>();
        ServiceReportGradeTo gradeTo=new ServiceReportGradeTo();
        gradeTo.setApartmentName("入室维修");
        gradeTo.setValueService(listGradeTo.getValueRSWXService());
        gradeTo.setValueSolve(listGradeTo.getValueRSWXSolve());
        gradeTo.setValueSatisfied(listGradeTo.getValueRSWXSatisfied());
        gradeToList.add(gradeTo);

        gradeTo=new ServiceReportGradeTo();
        gradeTo.setApartmentName("公共维修");
        gradeTo.setValueService(listGradeTo.getValueGGWXService());
        gradeTo.setValueSolve(listGradeTo.getValueGGWXSolve());
        gradeTo.setValueSatisfied(listGradeTo.getValueGGWXSatisfied());
        gradeToList.add(gradeTo);

        gradeTo=new ServiceReportGradeTo();
        gradeTo.setApartmentName("投诉管理");
        gradeTo.setValueService(listGradeTo.getValueTSService());
        gradeTo.setValueSolve(listGradeTo.getValueTSSolve());
        gradeTo.setValueSatisfied(listGradeTo.getValueTSSatisfied());
        gradeToList.add(gradeTo);

        gradeTo=new ServiceReportGradeTo();
        gradeTo.setApartmentName("家政管理");
        gradeTo.setValueService(listGradeTo.getValueJZService());
        gradeTo.setValueSolve(listGradeTo.getValueJZSolve());
        gradeTo.setValueSatisfied(listGradeTo.getValueJZSatisfied());
        gradeToList.add(gradeTo);
        return gradeToList;
    }
}
