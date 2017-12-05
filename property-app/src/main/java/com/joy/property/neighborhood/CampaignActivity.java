package com.joy.property.neighborhood;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborPostParam;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodInteractionTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogTo;
import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;

import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.neighborhood.adapter.HomeAdapter;
import com.joy.property.utils.SpUtil;
import com.joy.property.utils.htmlText.HtmlTextView;
import com.joy.property.base.BaseActivity;


import com.joyhome.nacity.app.util.CustomDialogFragment;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by xz on 2016/10/31.
 **/
public class CampaignActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<OwnerMessageTo> mDatas=new ArrayList<>();
    private ImageView campaignImage;
    private List<OwnerMessageTo>toList=new ArrayList<>();
    private Button confirmJoin;
   private String campTitle;
    private int ownerCount;
    private HtmlTextView htmlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);
        initData();
    }

    private void initData() {
        NeighborApi api= ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getSupportFragmentManager(), "");
        api.getInteractionDetail(getIntent().getStringExtra("interactionSid"), SpUtil.getBoolean(getThisContext(),"ParkNeighbor")?"":mHelper.getSid(), mUserHelper.getSid(), new HttpCallback<MessageTo<NeighborhoodInteractionTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborhoodInteractionTo> msg, Response response) {
         customDialog.dismiss();
                if (msg.getSuccess() == 0) {

                    initView(msg.getData(), msg.getTotal());
                } else
                    ToastShowLong(getThisContext(), msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
           customDialog.dismiss();
            }
        });

    }

    private void initView(NeighborhoodInteractionTo interactionTo,int total) {



       findViewById(R.id.iv_back).setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        campaignImage = (ImageView) findViewById(R.id.campaignImage);
        TextView campaignContent = (TextView) findViewById(R.id.campaignContent);
        RelativeLayout joinLayout = (RelativeLayout) findViewById(R.id.joinLayout);
        findViewById(R.id.all).setOnClickListener(this);
        findViewById(R.id.all).setVisibility(View.VISIBLE);
        confirmJoin = (Button) findViewById(R.id.confirmJoin);
        confirmJoin.setOnClickListener(this);
        campaignContent.setText(interactionTo.getActivityContent());
        mDatas.addAll(interactionTo.getOwnerMessageTos());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

      findViewById(R.id.ownerMore).setOnClickListener(this);
        htmlText = (HtmlTextView) findViewById(R.id.htmlText);
        if (interactionTo.getActivitySumary()!=null)
        setHtmlText(interactionTo);
   setJoinButton(interactionTo);

if (interactionTo.getNeighborhoodMediaTo()!=null)
    Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(interactionTo.getNeighborhoodMediaTo().getMediaUrl())).into(campaignImage);

        if (interactionTo.getOwnerMessageTos()!=null&&interactionTo.getOwnerMessageTos().size()>0)
{

    toList.clear();
    toList.addAll(interactionTo.getOwnerMessageTos());
    HomeAdapter adapter=new HomeAdapter(getThisContext(),toList);
    recyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
adapter.setOnItemClickListener((view, position) -> {



 Intent intent=new Intent(getThisContext(),MyNeighborActivity.class);
    intent.putExtra("messageTo",toList.get(position));
    if (!mUserHelper.getSid().equals(toList.get(position).getOwnerSid()))
        intent.putExtra("otherMainPage",true);
    startActivity(intent);
});
}
    else
        findViewById(R.id.joinLayout).setVisibility(View.GONE);
        campTitle=interactionTo.getActivityTitle();

    }

    private void setJoinButton(NeighborhoodInteractionTo interactionTo) {
        int joinCount=0;
        List<String>sidList=new ArrayList<>();
        if (interactionTo.getShowbtns()==1) {
            confirmJoin.setVisibility(View.GONE);
            return;
        }
        if (getIntent().getBooleanExtra("outOfDate",false))
        {
            confirmJoin.setEnabled(false);
            confirmJoin.setText("已过期");
            confirmJoin.setBackgroundColor(Color.parseColor("#c4c4c4"));
            return;
        }
        if (interactionTo.getOwnerMessageTos()!=null){
            if(interactionTo.getOwnerMessageTos().size()>=interactionTo.getPatitimelimit()*interactionTo.getPaticountlimit())
            {
                confirmJoin.setText("停止报名");
                confirmJoin.setEnabled(false);
                confirmJoin.setBackgroundColor(Color.parseColor("#c4c4c4"));
                return;
            }

            for (OwnerMessageTo messageTo:interactionTo.getOwnerMessageTos())
            {
                if (mUserHelper.getSid().equals(messageTo.getOwnerSid())) {
                    joinCount++;

                }
                if (!sidList.contains(messageTo.getOwnerSid()))
                    sidList.add(messageTo.getOwnerSid());
            }
            ownerCount=sidList.size();

        }
        if (joinCount>=interactionTo.getPaticountlimit()){
            confirmJoin.setText("已报名");
            confirmJoin.setEnabled(false);
            confirmJoin.setBackgroundColor(Color.parseColor("#c4c4c4"));
            return;
        }
        if (sidList.size()>=interactionTo.getPatitimelimit()&&joinCount>=interactionTo.getPaticountlimit()*interactionTo.getPaticountlimit()){
            confirmJoin.setText("报名人数已满");
            confirmJoin.setEnabled(false);
            confirmJoin.setBackgroundColor(Color.parseColor("#c4c4c4"));
            return;
        }
        System.out.println(interactionTo.getPaticountlimit() + "part" + interactionTo.getPatitimelimit());
        if (sidList.size()>=interactionTo.getPatitimelimit()){
            confirmJoin.setText("报名人数已满");
            confirmJoin.setEnabled(false);
            confirmJoin.setBackgroundColor(Color.parseColor("#c4c4c4"));
            if (joinCount<interactionTo.getPaticountlimit()&&sidList.contains(mUserHelper.getSid())){
                confirmJoin.setText("我要报名");
                confirmJoin.setEnabled(true);
                confirmJoin.setBackgroundColor(Color.parseColor("#f17834"));
            }

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ownerMore:
                Intent intent=new Intent(getThisContext(),FansActivity.class);
                intent.putExtra("fansList", (Serializable) toList);
                intent.putExtra("JoinCampaign",true);
                startActivity(intent);
                break;
            case R.id.confirmJoin:
               joinCampaign();
                confirmJoin.setEnabled(true);
                break;
            case R.id.all:
                 intent=new Intent(getThisContext(),InteractActivity.class);
                intent.putExtra("allType", 1);
                goToAnimation(1);
                startActivity(intent);
                finish();

                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }


public void joinCampaign(){
    NeighborApi api= ApiClient.create(NeighborApi.class);
    final CustomDialogFragment customDialog = new CustomDialogFragment();
    customDialog.show(getSupportFragmentManager(), "");
    NeighborhoodLogParam param=new NeighborhoodLogParam();

    param.setOwnerSid(mUserHelper.getSid());
    param.setApartmentSid(mHelper.getSid());
    if (TextUtils.isEmpty(mHelper.getSid()))
        param.setApartmentSid("30e1baa2-877f-48c8-92be-67d026752039");
    param.setRefId(getIntent().getStringExtra("interactionSid"));
    param.setType(1);
    param.setStatus(2);
    System.out.println(param.toString()+"param");
    api.JoinCampaign(param, new HttpCallback<MessageTo<NeighborhoodLogTo>>(getThisContext()) {
        @Override
        public void success(MessageTo<NeighborhoodLogTo> msg, Response response) {
            customDialog.dismiss();

            if (msg.getSuccess() == 0) {
                ToastShowLong(getThisContext(), "报名成功");

                if (ownerCount == 0) {
                    Intent intent = new Intent(getThisContext(), CampaignActivity.class);
                    intent.putExtra("interactionSid", getIntent().getStringExtra("interactionSid"));
                    finish();
                    startActivity(intent);

                } else {
                    initData();

                    confirmJoin.setEnabled(true);
                }
                postSubmit();
            } else {
                ToastShowLong(getThisContext(), msg.getMessage());
//                confirmJoin.setText("已报名");
//                confirmJoin.setEnabled(false);
//                confirmJoin.setBackgroundColor(Color.parseColor("#c4c4c4"));
            }
        }

        @Override
        public void failure(RetrofitError error) {
            customDialog.dismiss();
            System.out.println(error.toString());
        }
    });

}
    private void postSubmit() {
        NeighborPostParam param = new NeighborPostParam();


        if (TextUtils.isEmpty(mHelper.getSid())) {
            param.setPostContent("我参与了悦园区活动，一起来吧，点击标题进行参与！");
            param.setApartmentSid("30e1baa2-877f-48c8-92be-67d026752039");
        }
        else {
            param.setPostContent("我参与了悦嘉家活动，一起来吧，点击标题进行参与！");
            param.setApartmentSid(mHelper.getSid());
        }
        param.setTopicTitle(campTitle);
        param.setPostOwner(mUserHelper.getSid());

        param.setPostType("17");
        param.setRefId(getIntent().getStringExtra("interactionSid"));
        NeighborApi api = ApiClient.create(NeighborApi.class);

        System.out.println(param);
        api.addNeighborPost(param, new HttpCallback<MessageTo<NeighborPostTo>>(this) {
            @Override
            public void success(MessageTo<NeighborPostTo> msg, Response response) {

                if (msg.getSuccess() == 0) {


                } else {

                }
            }

            @Override
            public void failure(RetrofitError error) {

                super.failure(error);
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            onBackPressed();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getIntent().getBooleanExtra("mainPage",false))
        {
            startActivity(new Intent(getThisContext(),NeighborActivity.class));
        }
        finish();
    }
public void setHtmlText(NeighborhoodInteractionTo interactionTo){
    new Thread(() ->{
        runOnUiThread(() -> {
            htmlText.setHtmlFromString(interactionTo.getActivitySumary(),false);
        });
    }).start();
}


}
