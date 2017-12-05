package com.joy.property.neighborhood;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.property.R;
import com.joy.property.neighborhood.adapter.NeighborJoinAdapter;
import com.joy.property.person.MyNeighborJoinDetailActivity;
import com.joy.property.base.BaseActivity;

import com.joyhome.nacity.app.util.CustomDialogFragment;
import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/11/2.
 **/
public class NeighborJoinActivity extends BaseActivity implements View.OnClickListener {

    private PullToRefreshListView listView;
    private List<NeighborPostTo> postToList=new ArrayList<>();
    private List<NeighborPostTo> topicList=new ArrayList<>();
    private NeighborJoinAdapter adapter;
    private int pageIndex;
    private boolean haveJoin;
    private  boolean haveTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbor_join);
        initView();
        refresh();
        getNeighborPostUserNewComment(0);

    }



    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        adapter = new NeighborJoinAdapter(getThisContext());
        adapter.setList(postToList);
        listView.setAdapter(adapter);
        findViewById(R.id.iv_back).setOnClickListener(this);

    }
    private void refresh() {
listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 0;
        getNeighborPostUserNewComment(0);

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        getNeighborPostUserNewComment(pageIndex);
    }
});
    }
    ///获取发贴的 新的用户评论
    private void getNeighborPostUserNewComment(final int index) {

        NeighborApi api = ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialogFragment = new CustomDialogFragment();
        customDialogFragment.show(getSupportFragmentManager(), "");
        api.getMyNeighborJoin(mUserHelper.getSid(), 0, new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                customDialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    haveJoin = true;
                    if (index == 0) {
                        postToList.clear();
                    }
                    if (msg.getData()!=null)
                    postToList.addAll(msg.getData());
               getJoin(0);





                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
                listView.onRefreshComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                customDialogFragment.dismissAllowingStateLoss();
                listView.onRefreshComplete();
                super.failure(error);
            }
        });
    }

    public void getJoin(int index){
        NeighborApi api = ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialogFragment = new CustomDialogFragment();
        customDialogFragment.show(getSupportFragmentManager(), "");
        api.getJoinOrTopic(TextUtils.isEmpty(mHelper.getSid())?"30e1baa2-877f-48c8-92be-67d026752039":mHelper.getSid(), mUserHelper.getSid(), 0, new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                customDialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
for (NeighborPostTo postTo:msg.getData())
    System.out.println(postTo.toString()+"-----------------------");
                  if (msg.getData()!=null)
                      postToList.addAll(msg.getData());
                    adapter.notifyDataSetChanged();
                    System.out.println(postToList.toString()+"msg");
                    listView.setOnItemClickListener((parent, view, position, id) -> {
                        Intent intent = new Intent(getThisContext(), PostDetailActivity.class);
                        intent.putExtra("postSid", postToList.get(position - 1).getPostSid());
//                        if ("活动".equals(postToList.get(position - 1).getPostTypeName()) || "话题".equals(postToList.get(position - 1).getPostTypeName()) || "调查".equals(postToList.get(position - 1).getPostTypeName())) {
//                            intent.putExtra("isJoin", true);
//
//                        }
                        startActivity(intent);
                    });

                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
                listView.onRefreshComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                customDialogFragment.dismissAllowingStateLoss();
                listView.onRefreshComplete();
                super.failure(error);
                System.out.println(error.toString()+"---------------");
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                onBackPressed();
                goToAnimation(2);
                break;
        }
    }
    public List<NeighborPostTo> sortList(List<NeighborPostTo> list,List<NeighborPostTo>topicList) {
//        long time[] = new long[list.size() + topicList.size()];
//        List<Long> timeSort = new ArrayList<>();
//        List<NeighborPostTo>subList=new ArrayList<>();
//        list.addAll(topicList);
//
//            for (int j=0;j<time.length;j++){
//time[j]=list.get(j).getPostTime().getTime();
//        }
//
//        double lastTime = 0;
//        for (int i = 0; i < time.length; i++) {
//            System.out.println(time[i] + "time*********");
//
//
//            if (lastTime != time[i]) {
//                lastTime = time[i];
//                timeSort.add(time[i]);
//            }
//        }
//        long subTime[]=new long[timeSort.size()];
//  for (int i=0;i<timeSort.size();i++){
//      subTime[i]=timeSort.get(0);
//  }
//        Arrays.sort(subTime);
//        for (int i=0;i<subTime.length;i++){
//            for (int j=0;j<list.size();j++){
//                if (subTime[subTime.length-1-i]==list.get(j).getPostTime().getTime());
//                subList.add(list.get(j));
//            }
//        }
        haveTopic=false;
        haveJoin=false;
        if (topicList!=null){
            topicList.addAll(list);
            return topicList;
        }else
        return list;
    }

}
