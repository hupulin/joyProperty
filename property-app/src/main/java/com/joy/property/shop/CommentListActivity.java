package com.joy.property.shop;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.newshop.CommentParam;
import com.jinyi.ihome.module.newshop.GoodsCommentTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.ApiClientBulk;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.common.api.NewShopApi;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.shop.adapter.CommentListAdapter;
import com.joy.property.utils.ACache;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joyhome.nacity.app.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2017/3/31.
 */
public class CommentListActivity extends BaseActivity implements View.OnClickListener {
    private ListView listView;
    private PullToRefreshListView pullListView;
    private List<GoodsCommentTo> commentList=new ArrayList<>();
    private int pageIndex = 0;
    private ImageView noData;
    private TextView title;
    private CommentListAdapter adapter;
    final CustomDialogFragment customDialog = new CustomDialogFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        findViewById();
        setList(0);
        refreshData();
    }


    private void findViewById() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        noData = (ImageView) findViewById(R.id.noData);
        title = (TextView) findViewById(R.id.title);
        adapter = new CommentListAdapter(getThisContext());
        adapter.setList(commentList);
        pullListView = (PullToRefreshListView) findViewById(R.id.listView);
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        listView= pullListView.getRefreshableView();
        listView.setAdapter(adapter);

        // listView.setDividerHeight(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
    private void refreshData() {
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex = 0;
                setList(0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                setList(pageIndex);
            }
        });
    }
    private void setList(final int index) {
        NewShopApi api= ApiClientBulk.create(NewShopApi.class);
        CommentParam param=new CommentParam();
        param.setGoodsId(getIntent().getStringExtra("GoodsSid"));
        param.setPageSize("20");
        param.setCurrentPage(index + 1 + "");
        param.setGoodsType(getIntent().getStringExtra("GoodsType"));
        CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.getAllComment(param, new HttpCallback<MessageToBulk<List<GoodsCommentTo>>>(getThisContext()) {
            @Override
            public void success(MessageToBulk<List<GoodsCommentTo>> msg, Response response) {
                dialogFragment.dismiss();
                setRefreshComplement();
                if (msg.getCode() == 0) {
                    if (index == 0) {
                        commentList.clear();
                    }
                    if (msg.getGoodsAllEvaluateList() != null) {
                        Log.i("22222", "getGoodsAllEvaluateList"+msg.getGoodsAllEvaluateList().toString());
                        commentList.addAll(msg.getGoodsAllEvaluateList());
                        Log.i("22222", "commentList" + commentList.toString());
                        title.setText("全部评价(" + msg.getTotal() + ")");
                        if (commentList.size() > 15)
                            pullListView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
                        else
                            pullListView.setMode(PullToRefreshBase.Mode.DISABLED);
                        setNoDataIcon(commentList.size());
                        adapter.setList(commentList);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                setRefreshComplement();
                dialogFragment.dismiss();
            }
        });
    }
    public void setRefreshComplement(){
        pullListView.onRefreshComplete();
    }
    //设置没数据
    public void setNoDataIcon(int size){
        if (size==0)
            noData.setVisibility(View.VISIBLE);
        else
            noData.setVisibility(View.GONE);
    }

}
