package com.joy.property.neighborhood;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborPostParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodInteractionTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogTo;
import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;

import com.joy.property.R;
import com.joy.property.community.NeighborVoteDetailActivity;
import com.joy.property.neighborhood.adapter.InvestigateAdapter;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.MainApp;
import com.joy.property.base.BaseActivity;


import com.joyhome.nacity.app.util.CustomDialogFragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/11/1.
 **/
public class InvestigateActivity extends BaseActivity implements View.OnClickListener {

    private PullToRefreshListView listView;
    private TextView investigateContent;
    private TextView expandAll;
    private String subContent;
    private ListView mListView;
    private List<OwnerMessageTo> messageTos=new ArrayList<>();
    private InvestigateAdapter adapter;
    private boolean isFocusChange;
    private Button confirmJoin;
    private String voteSid;
    private String campTitle;
    private boolean isRestart;
    private boolean firstAddHead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investigate);
        initView();
        refreshData();
    }



    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        investigateContent = (TextView) findViewById(R.id.investigateContent);
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView titleText = (TextView) findViewById(R.id.titleText);
        titleText.setText("调查详情");
        findViewById(R.id.all).setVisibility(View.VISIBLE);
        findViewById(R.id.all).setOnClickListener(this);
        adapter = new InvestigateAdapter(getThisContext());
        adapter.setList(messageTos);
        listView.setMode(PullToRefreshBase.Mode.DISABLED);
        mListView=listView.getRefreshableView();

        mListView.setAdapter(adapter);
        confirmJoin = (Button) findViewById(R.id.confirmJoin);
        confirmJoin.setOnClickListener(this);
        confirmJoin.setText("我要参加");

        SpUtil.put(getThisContext(), "TopicSid", null);
    }
    private void refreshData() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!isFocusChange)
            initData();
        isFocusChange=true;

    }
    public String getSubContent(TextView tv,String content){
        tv.setText(content);
        Layout layout=tv.getLayout();
        int line=tv.getLayout().getLineCount();
        if (line<5){
            return content;
        }else {

            String result = "";
            String text = layout.getText().toString();
            for (int i = 0; i < 5; i++) {
                int start = layout.getLineStart(i);
                int end = layout.getLineEnd(i);
                result += text.substring(start, end);
            }
            return result;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.all:
                Intent  intent=new Intent(getThisContext(),InteractActivity.class);
                intent.putExtra("allType", 3);
                goToAnimation(1);
                startActivity(intent);
                finish();


                break;
            case R.id.confirmJoin:
                intent = new Intent(getThisContext(), NeighborVoteDetailActivity.class);

                intent.putExtra("voteSid", voteSid);
                intent.putExtra("interactionSid", getIntent().getStringExtra("interactionSid"));
                NeighborPostParam param = new NeighborPostParam();
                param.setApartmentSid(mHelper.getSid());
                if (TextUtils.isEmpty(mHelper.getSid()))
                    param.setPostContent("我参与了悦园区活动，一起来吧，点击标题进行参与！");
                else
                    param.setPostContent("我参与了悦嘉家调查，一起来吧，点击标题进行参与！");
                param.setTopicTitle(campTitle);
                param.setPostOwner(mUserHelper.getSid());

                param.setPostType("19");
                param.setRefId(getIntent().getStringExtra("interactionSid"));
                intent.putExtra("postParam",param);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
    private void initData() {

        NeighborApi api= ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
     if (!isRestart)
        customDialog.show(getSupportFragmentManager(), "");
        api.getInteractionDetail(getIntent().getStringExtra("interactionSid"), SpUtil.getBoolean(getThisContext(),"ParkNeighbor")?"":mHelper.getSid(), mUserHelper.getSid(), new HttpCallback<MessageTo<NeighborhoodInteractionTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborhoodInteractionTo> msg, Response response) {
                if (!isRestart)
                customDialog.dismiss();

                if (msg.getSuccess() == 0) {

                    if (msg.getData()!=null){
                        subContent=getSubContent(investigateContent, msg.getData().getActivityContent());
                        voteSid=msg.getData().getRefId();
                        campTitle=msg.getData().getActivityTitle();
                        setJoinButton(msg.getData());
                        if (!firstAddHead)
                            setHeadView(subContent,msg.getData().getActivityContent(),msg.getData() );
                        firstAddHead=true;

                        if (msg.getData().getOwnerMessageTos()!=null)
                        {
                            messageTos.clear();
                            messageTos.addAll(msg.getData().getOwnerMessageTos());
                            adapter.notifyDataSetChanged();
                            mListView.setOnItemClickListener((parent, view, position, id) -> {
                                if (position==0||position==1)
                                    return;
                                OwnerMessageTo item=messageTos.get(position-2);
                                Intent intent=new Intent(getThisContext(), MyNeighborActivity.class);
                                if (mUserHelper.getSid().equals(messageTos.get(position-2).getOwnerSid()))
                                    intent.putExtra("otherMainPage",false);
                                else
                                    intent.putExtra("otherMainPage",true);

                                intent.putExtra("otherSid", item.getOwnerSid());
                                OwnerMessageTo messageTo=new OwnerMessageTo();
                                messageTo.setOwnerSid(item.getOwnerSid());
                                messageTo.setFamilyName(item.getFamilyName());
                                messageTo.setOwnerImage(item.getOwnerImage());
                                intent.putExtra("messageTo", messageTo);
                                startActivity(intent);

                                goToAnimation(1);
                            });
                        }
                    }
                } else
                    ToastShowLong(getThisContext(), msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                if (!isRestart)
                customDialog.dismiss();
                ToastShowLong(getThisContext(),error.toString());
                System.out.println(error.toString()+"er");
            }
        });

    }
    public void setHeadView(String subContent,String content,NeighborhoodInteractionTo interactionTo){
        View headView=View.inflate(getThisContext(), R.layout.investigate_head_view, null);
        expandAll = (TextView) headView.findViewById(R.id.expandAll);
        investigateContent = (TextView) headView.findViewById(R.id.investigateContent);
        ImageView investigateImage = (ImageView) headView.findViewById(R.id.investigateImage);
        Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(interactionTo.getNeighborhoodMediaTo().getMediaUrl())).into(investigateImage);
        if (subContent.length()<content.length()) {
            expandAll.setVisibility(View.VISIBLE);
            investigateContent.setText(subContent.substring(0,subContent.length()-4));
        }else
            investigateContent.setText(content);
        expandAll.setOnClickListener(v -> {
            Spannable spannable = new SpannableString(content + "收起");

            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#007aff")), content.length(), content.length() + 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new TextClick(), content.length(), content.length() + 2
                    , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            investigateContent.setMovementMethod(LinkMovementMethod.getInstance());
            investigateContent.setMaxLines(100);

            investigateContent.setText(spannable);
            expandAll.setVisibility(View.GONE);

        });
        mListView.addHeaderView(headView);
    }

    private class TextClick extends ClickableSpan {


        @Override
        public void onClick(View widget) {
            investigateContent.setMaxLines(5);
            investigateContent.setText(subContent.substring(0,subContent.length()-4));
            expandAll.setVisibility(View.VISIBLE);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.parseColor("#007aff"));
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
        param.setType(3);
        param.setStatus(2);
        api.JoinCampaign(param, new HttpCallback<MessageTo<NeighborhoodLogTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborhoodLogTo> msg, Response response) {
                customDialog.dismiss();
                if (msg.getSuccess() == 0) {

                } else {
                    ToastShowLong(getThisContext(), msg.getMessage());

                }
            }

            @Override
            public void failure(RetrofitError error) {
                customDialog.dismiss();

            }
        });

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


        }
        if (joinCount>=interactionTo.getPaticountlimit()){
            confirmJoin.setText("已参与");
            confirmJoin.setEnabled(false);
            confirmJoin.setBackgroundColor(Color.parseColor("#c4c4c4"));
            return;
        }
        if (sidList.size()>=interactionTo.getPatitimelimit()&&joinCount>=interactionTo.getPaticountlimit()*interactionTo.getPaticountlimit()){
            confirmJoin.setText("参与人已满");
            confirmJoin.setEnabled(false);
            confirmJoin.setBackgroundColor(Color.parseColor("#c4c4c4"));
            return;
        }
        System.out.println(interactionTo.getPaticountlimit() + "part" + interactionTo.getPatitimelimit());
        if (sidList.size()>=interactionTo.getPatitimelimit()){
            confirmJoin.setText("参与人数已满");
            confirmJoin.setEnabled(false);
            confirmJoin.setBackgroundColor(Color.parseColor("#c4c4c4"));
            if (joinCount<interactionTo.getPaticountlimit()&&sidList.contains(mUserHelper.getSid())){
                confirmJoin.setText("我要参加");
                confirmJoin.setEnabled(true);
                confirmJoin.setBackgroundColor(Color.parseColor("#f17834"));
            }

        }


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
            startActivity(new Intent(getThisContext(), NeighborActivity.class));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onRestart() {
        isRestart=true;
        initData();

        super.onRestart();

    }


}

