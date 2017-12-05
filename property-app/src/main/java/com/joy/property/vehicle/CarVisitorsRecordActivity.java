package com.joy.property.vehicle;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.module.visitor.VisitorCardTo;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.vehicle.adapter.CarVisitorsRecordAdapter;

import java.util.ArrayList;

/**
 * Created by usb on 2016/6/29.
 */
public class CarVisitorsRecordActivity extends BaseActivity
        implements View.OnClickListener {
    private ArrayList<VisitorCardTo> mVisitorCardTo;
    private TextView mTitle;

    private CarVisitorsRecordAdapter mAdapter;
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitrecord);
        findById();
        getIntentData();
        mAdapter = new CarVisitorsRecordAdapter(this);
        mList = (ListView) this.findViewById(R.id.listView);
        if(mVisitorCardTo!=null&&mVisitorCardTo.size()>0){
            mAdapter.setList(mVisitorCardTo);
            mList.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

        }

        mTitle.setText("来访纪录");

    }


    private void findById() {
        mBack = (android.widget.ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.title);

    }

    private void getIntentData() {
        mVisitorCardTo = (ArrayList<VisitorCardTo>) getIntent().getSerializableExtra("carRecords");
        System.out.println("么么哒："+mVisitorCardTo.get(0).getLeaveTime());
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

        return CarVisitorsRecordActivity.this;
    }

    @Override
    protected String toPageName() {
        super.toPageName();
        return "车辆来访列表";
    }
}
