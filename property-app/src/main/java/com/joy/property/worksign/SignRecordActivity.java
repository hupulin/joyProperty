package com.joy.property.worksign;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.TextView;

import com.Util.signencode.SXHttpUtils;
import com.Util.signencode.aes.WLHSecurityUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.module.worksign.SignJsonTo;
import com.jinyi.ihome.module.worksign.SignMessageTo;
import com.jinyi.ihome.module.worksign.SignRecordInfoTo;
import com.jinyi.ihome.module.worksign.SignRecordTo;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.worksign.adapter.SignBaseParam;
import com.joy.property.worksign.adapter.SignRecordAdapter;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xzz on 2017/12/28.
 **/
public class SignRecordActivity extends BaseActivity {

    private PullToRefreshListView refreshListView;
    private ListView listView;
    private SignRecordAdapter mAdapter;
    private List<SignRecordTo.SignListTo> signList = new ArrayList<>();
    private SignRecordInfoTo recordInfoTo;
    private int pageIndex=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_record);
        initView();
        getData(pageIndex);
        addHeadView();
    }


    private void initView() {
        refreshListView = (PullToRefreshListView) findViewById(R.id.listView);
        findViewById(R.id.back).setOnClickListener(v -> {
            finish();
            goToAnimation(2);
        });

       refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex=1;
                getData(pageIndex);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                getData(pageIndex);
            }
        });
    }

    private void addHeadView() {
        listView = refreshListView.getRefreshableView();
        TextView textView = new TextView(getThisContext());
        textView.setMinWidth(getScreenWidth());
        textView.setHeight((int) (getScreenWidth() * 100.0 / 720));
        textView.setText(recordInfoTo.getUserName());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (getScreenWidth() * 28.0 / 720));
        textView.setTextColor(Color.parseColor("#333333"));
        textView.setBackgroundColor(Color.parseColor("#e3fefd"));
        textView.setGravity(Gravity.CENTER);
        listView.addHeaderView(textView);
        listView.setDividerHeight(0);

        mAdapter = new SignRecordAdapter(this);
        mAdapter.setList(signList);
        listView.setAdapter(mAdapter);

    }

    private void getData(int page) {
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
        recordInfoTo = (SignRecordInfoTo) getIntent().getSerializableExtra("SignRecordInfo");
        SignJsonTo jsonTo = new SignJsonTo();
        jsonTo.setTradeType("GetReportDetail");
        jsonTo.setReportType(recordInfoTo.getFootprintType());
        jsonTo.setStartDate(recordInfoTo.getStartDate());
        jsonTo.setEndDate(recordInfoTo.getEndDate());
        jsonTo.setUniqueStr(getDeviceUid());
        jsonTo.setOpenId(mUserHelper.getSid());
        jsonTo.setPage(page);

        SXHttpUtils.requestPostData(SignRecordActivity.this,  jsonTo, new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                dialogFragment.dismiss();
                refreshListView.onRefreshComplete();
                SignMessageTo<SignRecordTo> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);
                SignRecordTo myPrintTo = new Gson().fromJson(new Gson().toJson(msg.getResultContent()), SignRecordTo.class);
                if (myPrintTo == null)
                    return;

                if (pageIndex==1) {
                    signList.clear();
                    signList.addAll(myPrintTo.getSignlist());

                }
                else
                    signList.addAll(myPrintTo.getSignlist());

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadError() {
                refreshListView.onRefreshComplete();
                dialogFragment.dismiss();
                showSignNetError();
            }
        });
    }
}
