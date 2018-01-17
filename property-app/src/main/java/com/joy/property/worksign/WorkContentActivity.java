package com.joy.property.worksign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ListView;

import com.Util.signencode.SXHttpUtils;
import com.Util.signencode.aes.WLHSecurityUtils;
import com.google.gson.Gson;
import com.jinyi.ihome.module.worksign.SignContentTo;
import com.jinyi.ihome.module.worksign.SignJsonTo;
import com.jinyi.ihome.module.worksign.SignMessageTo;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.worksign.adapter.SignBaseParam;
import com.joy.property.worksign.adapter.WorkContentAdapter;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xzz on 2017/12/29.
 */

public class WorkContentActivity extends BaseActivity {

    private ListView listView;
    private List<SignContentTo> contentList = new ArrayList<>();
    private WorkContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_content);
        initView();
        getData();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        adapter = new WorkContentAdapter(getThisContext());
        adapter.setList(contentList);
        listView.setAdapter(adapter);
        listView.setDividerHeight(0);
        Gson gson = new Gson();
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent();
            intent.putExtra("WorkContent", (gson.fromJson(gson.toJson(contentList.get(position)), SignContentTo.class)).getTitle());
            setResult(10, intent);
            finish();
            goToAnimation(2);
        });
        findViewById(R.id.back).setOnClickListener(v -> {finish();goToAnimation(2);});

    }

    private void getData() {
        SignJsonTo jsonTo = new SignJsonTo();
        jsonTo.setDeviceId("1909DCFD-243D-2F68-233A-250C9C9B571E");
        jsonTo.setTradeType("GetJobList");
        jsonTo.setOpenId(mUserHelper.getSid());
        jsonTo.setParkName(getIntent().getStringExtra("ParkName"));
        jsonTo.setUniqueStr(getDeviceUid());
        CustomDialogFragment customDialogFragment=new CustomDialogFragment();
        customDialogFragment.show(getSupportFragmentManager(),"");
        SXHttpUtils.requestPostData(WorkContentActivity.this, jsonTo, new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                customDialogFragment.dismiss();
                SignMessageTo<List<SignContentTo>> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);
                System.out.println(msg+"content===");
                if (msg != null && msg.getResultCode() == 0) {
                    List<SignContentTo> resultContent = msg.getResultContent();
                    contentList.addAll(resultContent);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onLoadError() {
            customDialogFragment.dismiss();
                showSignNetError();
            }
        });
    }
}
