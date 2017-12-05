package com.joy.property.neighborhood;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.neighbor.NeighborUserCommentTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.common.helper.UserInfoHelper;

import com.joy.property.R;
import com.joy.property.neighborhood.adapter.NeighborPostToAdapter;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xzz on 2016/2/22.
 */
public class NeighborhoodView {
    Context context;
    String  typename;
    View rootView;

    private UserInfoHelper mUserHelper;
    private SharedPreferences sp;
    private TextView liveQty;
    private TextView newsQty;
    protected ApartmentInfoHelper mHelper;
    private NeighborPostToAdapter mAdapter = null;
    private ListView mListView;
    private List<NeighborPostTo> postList = new ArrayList<>();
    private List<NeighborUserCommentTo> mUserCommentList;
    private PullToRefreshListView mPullToRefreshListView=null;
    public NeighborhoodView(final Context context, List<NeighborPostTo> postlist,PullToRefreshListView mPullToRefreshListView,NeighborPostToAdapter mAdapter) {
        super();
        this.context = context;
        this.postList = postlist;
        this.mPullToRefreshListView=mPullToRefreshListView;
        this.mAdapter=mAdapter;
        mHelper = ApartmentInfoHelper.getInstance(context);
        rootView=View.inflate(context, R.layout.neighborhoodview, null);
        mPullToRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.lv_PullToRefreshListView);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView = mPullToRefreshListView.getRefreshableView();

        mAdapter = new NeighborPostToAdapter(context);

        mAdapter.setList(postlist);

        mListView.setAdapter(mAdapter);
applyScrollListener();

    }

    public View getView() {
        return rootView;
    }
    public static interface OnItemClickListener{
        public void onItemClick(View v, String text);
    }

    OnItemClickListener dsglOicl;


    public void setOnItemClickListener(OnItemClickListener dsglOicl) {
        this.dsglOicl = dsglOicl;
        mPullToRefreshListView.onRefreshComplete();
    }

public void setFinish(){
    mPullToRefreshListView.onRefreshComplete();
}
public void setRefresh(){
    mAdapter.notifyDataSetChanged();
}
    private void applyScrollListener() {
        mListView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));
    }
    private void setList(final int index) {
        if (!TextUtils.isEmpty(mHelper.getSid())) {
            NeighborApi api = ApiClient.create(NeighborApi.class);
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            api.findNeighborPostListByApartment(mHelper.getSid(), index,
                    new HttpCallback<MessageTo<List<NeighborPostTo>>>(context) {
                        @Override
                        public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                            if (msg.getSuccess() == 0) {
                                customDialog.dismissAllowingStateLoss();
                                if (index == 0) {
                                    postList.clear();
                                }
                                postList.addAll(msg.getData());
                                System.out.println(postList.toString() + "");
                                Map<String,List<NeighborPostTo>> map=new HashMap<String, List<NeighborPostTo>>();
                                map.put("Neighborhood_Cache", postList);
                                String json= JSON.toJSONString(map);

                                SpUtil.put(context, "Neighborhood_Cache", json);
                                // SpUtil.put(getThisContext(),"FIRSTLOADERCICLE",true);
                                map.put("小区广场",postList);
                                mAdapter.notifyDataSetChanged();

                               mPullToRefreshListView.onRefreshComplete();


                            } else {
                                Toast.makeText(context,
                                        msg.getMessage(), Toast.LENGTH_LONG).show();
                                customDialog.dismissAllowingStateLoss();

                                mAdapter.notifyDataSetChanged();
                            }

                            mPullToRefreshListView.onRefreshComplete();

                        }

                        @Override
                        public void failure(RetrofitError error) {

                            mPullToRefreshListView.onRefreshComplete();
                            customDialog.dismissAllowingStateLoss();
                            mAdapter.notifyDataSetChanged();
                            super.failure(error);
                        }
                    });

        }
    }
}
