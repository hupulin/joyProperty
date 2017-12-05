package com.joy.property.host;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceReportGradeTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.host.adapter.OwnerMouthAdapter;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-02-09
 */
public class SatisfactionActivity extends BaseActivity
        implements OnClickListener {


    private PullToRefreshListView mPullToRefreshListView;
    private OwnerMouthAdapter mAdapter;
    private List<ServiceReportGradeTo> gradeToList = new ArrayList<>();
    private ListView mList;
    private TextView mTitle;
    private ImageView changePark;
    private boolean firstChange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownermouth);
        findById();
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(Mode.BOTH);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mAdapter = new OwnerMouthAdapter(getThisContext());
        mAdapter.setList(gradeToList);
        mList.setAdapter(mAdapter);
      setLists();
        initData();
    }


    private void findById() {
        mTitle = (TextView) findViewById(R.id.title2);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        changePark = (ImageView) findViewById(R.id.changePark);
        changePark.setOnClickListener(this);
    }
    private void initData() {
        mTitle.setText("满意度");
        if (SpUtil.getString(getThisContext(), "limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A004"))
        {
            changePark.setVisibility(View.VISIBLE);
            mTitle.setText("满意度(住宅)");
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
             // setList();
             setLists();
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

             //setList();
             setLists();
         }
     });
    }




    private void setLists() {
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
                     mAdapter.notifyDataSetChanged();
                        Log.i("msg", "success:" + msg.getData().toString());
                    }


                    mList.setOnItemClickListener((parent, view, position, id) -> {
                        ServiceReportGradeTo flowTo = gradeToList.get(position - 1);
                        Intent intent = new Intent(getThisContext(), SatisfactionDetail.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("flowTo", flowTo);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    });
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
                mPullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                dialog.dismiss();

            }
        });
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.changePark:
                changePark();
                break;
        }
    }

    @Override
    protected Context getThisContext() {
        return SatisfactionActivity.this;
    }

    @Override
    protected String toPageName() {
        return "邻里口碑";
    }
    private void changePark() {
        if (!firstChange){
            ChangeParkUtil.changeToPark(getThisContext(),mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_home);
            firstChange=true;
            setLists();
            mTitle.setText("满意度(园区)");
        }else {
            ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_park);
            firstChange=false;
            setLists();
            mTitle.setText("满意度(住宅)");
        }
    }

}
