package com.joy.property.neighborhood.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodInteractionTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogTo;
import com.jinyi.ihome.module.vote.VoteTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.common.helper.UserInfoHelper;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.neighborhood.CampaignActivity;
import com.joy.property.neighborhood.InvestigateActivity;
import com.joy.property.neighborhood.TopicActivity;
import com.joy.property.neighborhood.adapter.InteractAdapter;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.base.BaseListFragment;

import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/10/31.
 **/
@SuppressLint("ValidFragment")
public class InteractFragment extends BaseListFragment<VoteTo> {
    private View self;
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mListView;
    private InteractAdapter mAdapter = null;
    private int type;
    private int pageIndex;
    private List<NeighborhoodInteractionTo> interactionTos=new ArrayList<>();
    private List<NeighborhoodInteractionTo> tos;
public InteractFragment(){

}
    public InteractFragment(int type){
        this.type=type;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.self == null) {
            this.self = inflater.inflate(R.layout.neighborhoodview, null);
        }
        if (this.self.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.self.getParent();
            parent.removeView(this.self);
        }
        mHelper = ApartmentInfoHelper.getInstance(MainApp.mContext);
        mUserHelper= UserInfoHelper.getInstance(MainApp.mContext);
    initView();
setInterationList(type, 0);
        refreshData();
        return self;
    }



    private void initView() {
        mPullToRefreshListView = (PullToRefreshListView)  self.findViewById(R.id.list);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView = mPullToRefreshListView.getRefreshableView();
        registerForContextMenu(mListView);
        mAdapter = new InteractAdapter(getThisContext());
        mAdapter.setList(interactionTos);
         mListView.setAdapter(mAdapter);

        mListView.setDivider(getResources().getDrawable(R.drawable.divide_bg));
        mListView.setDividerHeight((int) (getScreenWidthPixels(getThisContext()) * 0.0277));
        mListView.setOnItemClickListener((parent, view, position, id) -> {

            startActivity(new Intent(getThisContext(), TopicActivity.class));
        });
    }
    private void refreshData() {
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex = 0;
                setInterationList(type, 0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                setInterationList(type, pageIndex);
            }
        });
    }
    public void setInterationList(int type,int index) {

        NeighborApi api= ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getFragmentManager(), "");
        api.getInteractionList(SpUtil.getBoolean(getThisContext(),"ParkNeighbor")?"":mHelper.getSid(),type, index, new HttpCallback<MessageTo<List<NeighborhoodInteractionTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<NeighborhoodInteractionTo>> msg, Response response) {
                customDialog.dismiss();
                if (msg.getSuccess() == 0) {

if (index==0){
    interactionTos.clear();
}
           if (msg.getData()!=null) {
               interactionTos.addAll(msg.getData());
        sortList();
               mAdapter.setList(tos);


               mAdapter.notifyDataSetChanged();
           }
                } else
                    Toast.makeText(getThisContext(), msg.getMessage(),Toast.LENGTH_LONG).show();
                mPullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                customDialog.dismiss();
                mPullToRefreshListView.onRefreshComplete();
            }
        });


mListView.setOnItemClickListener((parent, view, position, id) -> {
    Intent intent = null;
    if (type == 1)
        intent = new Intent(getThisContext(), CampaignActivity.class);
    else if (type == 2)
        intent = new Intent(getThisContext(), TopicActivity.class);
    else if (type == 3)
        intent = new Intent(getThisContext(), InvestigateActivity.class);
    intent.putExtra("interactionSid", interactionTos.get(position - 1).getRefId());
    intent.putExtra("outOfDate", interactionTos.get(position - 1).isOutOfDate());
    joinCampaign(interactionTos.get(position - 1).getRefId(), type);
    if (getActivity().getIntent().getIntExtra("allType",0)!=0)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);

});
    }



    @Override
    public void refreshList(int key) {
        setInterationList(type, 0);

    }

    @Override
    public void moreList(int key) {
        setInterationList(type, key);
    }

    @Override
    public void setList(int index) {

    }
    public void sortList(){
        tos = new ArrayList<>();
        tos.clear();
        for(int i=0;i<interactionTos.size();i++) {

            if (interactionTos.get(i).getActivityDeadline().getTime() < DateUtil.getDate().getTime())
             interactionTos.get(i).setOutOfDate(true);
          //  System.out.println(interactionTos.get(i).getActivityDeadline()+"和"+interactionTos.get(i).isOutOfDate());
        }
        for (NeighborhoodInteractionTo interactionTo:interactionTos) {
            if (!interactionTo.isOutOfDate())
                tos.add(interactionTo);
        }
        for (NeighborhoodInteractionTo interactionTo:interactionTos)
        {
            if (interactionTo.isOutOfDate())
                tos.add(interactionTo);
        }
        interactionTos= tos;

    }
    public void joinCampaign(String interactionSid,int type){
        NeighborApi api= ApiClient.create(NeighborApi.class);

        NeighborhoodLogParam param=new NeighborhoodLogParam();
        param.setOwnerSid(mUserHelper.getSid());
        param.setApartmentSid(mHelper.getSid());
        param.setRefId(interactionSid);
        param.setType(type);
        param.setStatus(1);
        api.JoinCampaign(param, new HttpCallback<MessageTo<NeighborhoodLogTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborhoodLogTo> msg, Response response) {


                if (msg.getSuccess() == 0) {

                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
