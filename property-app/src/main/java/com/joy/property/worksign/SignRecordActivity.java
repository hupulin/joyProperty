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
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.module.worksign.SignJsonTo;
import com.jinyi.ihome.module.worksign.SignMessageTo;
import com.jinyi.ihome.module.worksign.SignRecordInfoTo;
import com.jinyi.ihome.module.worksign.SignRecordTo;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.worksign.adapter.SignBaseParam;
import com.joy.property.worksign.adapter.SignRecordAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_record);
        initView();
        getData();
        addHeadView();
    }


    private void initView() {
        refreshListView = (PullToRefreshListView) findViewById(R.id.listView);
        findViewById(R.id.back).setOnClickListener(v -> {finish();goToAnimation(2);});


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



    }

    private void getData() {

        recordInfoTo = (SignRecordInfoTo) getIntent().getSerializableExtra("SignRecordInfo");
        SignJsonTo jsonTo = new SignJsonTo();

        jsonTo.setTradeType("GetReportDetail");

        jsonTo.setReportType(recordInfoTo.getFootprintType());
        System.out.println(recordInfoTo +"recordTo");
        jsonTo.setStartDate(recordInfoTo.getStartDate());
        jsonTo.setEndDate(recordInfoTo.getEndDate());
        jsonTo.setUniqueStr(((TelephonyManager) getThisContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
       jsonTo.setOpenId(mUserHelper.getSid());
        System.out.println(new Gson().toJson(jsonTo)+"json");

        SignBaseParam param = new SignBaseParam();
        param.setParamData(WLHSecurityUtils.toURLDecoded(WLHSecurityUtils.encrypt(new Gson().toJson(jsonTo))));

        Map<String, String> params = new HashMap<>();
        params.put("ParamData", param.getParamData());
        SXHttpUtils.requestPostData(SignRecordActivity.this, "http://nd.alipayer.cn/index.php/backend/api.html", params, "UTF-8", new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                SignMessageTo<SignRecordTo> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);
                System.out.println(msg + "msg====");

                SignRecordTo myPrintTo = new Gson().fromJson(new Gson().toJson(msg.getResultContent()), SignRecordTo.class);
                if (myPrintTo==null)
                    return;
                signList = myPrintTo.getSignlist();

                System.out.println(signList + "myPrintTo");
                mAdapter.setList(signList);
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                listView.setOnItemClickListener((parent, view, position, id) -> {

//                    Intent intent=new Intent(getThisContext(),SignDetailActivity.class);
//                    intent.putExtra("SignSid",(new Gson().fromJson(new Gson().toJson(signList.get(position-1)),SignRecordTo.SignListTo.class)).getId());
//                    startActivity(intent);
                    goToAnimation(1);
                });
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onLoadError() {

            }
        });
    }
}