package com.joy.property.person.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.person.MyNeighborJoinDetailActivity;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


//Created by xz on 2016/5/23.

public class MyNeighborJoinFragment extends BaseFragment implements View.OnClickListener {
    private PullToRefreshScrollView mPullToRefreshScrollView;

    private LinearLayout mViewGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mRootView = inflater.inflate(R.layout.activity_neighborhood_join, container, false);
        findById(mRootView);
        mPullToRefreshScrollView.getRefreshableView();
        mPullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        getNeighborPostUserNewComment(0);

        initData();

        return mRootView;
    }

    private void findById(View mRootView) {

        mPullToRefreshScrollView = (PullToRefreshScrollView) mRootView.findViewById(R.id.scrollView);
        mViewGroup = (LinearLayout) mRootView.findViewById(R.id.viewGroup);
    }

    private void initData() {

        mPullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {

                getNeighborPostUserNewComment(0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {

            }
        });

    }

    ///获取发贴的 新的用户评论
    private void getNeighborPostUserNewComment(final int index) {

        NeighborApi api = ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialogFragment = new CustomDialogFragment();
        customDialogFragment.show(getFragmentManager(), "");
        api.getMyNeighborJoins(mHelper.getSid(), mUserHelper.getSid(), 0, new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                customDialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    if (index == 0) {
                        mViewGroup.removeAllViews();
                    }

                    setData(msg.getData());

                    if (msg.getData().size() == 0) {
                        System.out.println("我来了");
                        TextView emptyView = new TextView(getThisContext());
                        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
                        emptyView.setText("暂无参与记录！");
                        emptyView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                        emptyView.setVisibility(View.GONE);
                        ((ViewGroup) mViewGroup.getParent()).addView(emptyView);
                        (new ListView(getThisContext())).setEmptyView(emptyView);
                    }

                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
                mPullToRefreshScrollView.onRefreshComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                customDialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });
    }

    private void setData(List<NeighborPostTo> data) {
        System.out.println(data);
        LayoutInflater inflater = LayoutInflater.from(getThisContext());

        for (NeighborPostTo neighborUserCommentTo : data) {
            View view = inflater.inflate(R.layout.list_item_new_messages, null);
            ImageView mUserHeadImage = (ImageView) view.findViewById(R.id.icon);
            TextView mCommentUser = (TextView) view.findViewById(R.id.comment_user);
            TextView mPostContent = (TextView) view.findViewById(R.id.post_content);
            TextView mCommentContent = (TextView) view.findViewById(R.id.comment_content);
            TextView mCommentTime = (TextView) view.findViewById(R.id.time);
            view.setTag(neighborUserCommentTo);
            if (neighborUserCommentTo.getPostOwner() != null) {
                displayImage(mUserHeadImage, neighborUserCommentTo.getPostOwner().getIcon(), R.drawable.guest_head_image);
                if (!TextUtils.isEmpty(neighborUserCommentTo.getPostOwner().getName())) {
                    mCommentUser.setText(neighborUserCommentTo.getPostOwner().getName());
                }
            }
            String myLike = null;
            String myComment = null;
            for (NeighborLikeTo likeTo : neighborUserCommentTo.getLikeList()) {
                System.out.println("无底洞：" + mUserHelper.getUserInfoTo().getSid() + "深渊：" + likeTo.getLikeOwner().getSid());
                if (likeTo.getLikeOwner().getSid().equals(mUserHelper.getUserInfoTo().getSid())) {

                    myLike = "我赞了" + neighborUserCommentTo.getPostOwner().getName();
                    mCommentContent.setText(myLike);
                }
            }
            for (NeighborCommentTo commentTo : neighborUserCommentTo.getCommentList()) {
                if (commentTo.getCommentOwner().getSid().equals(mUserHelper.getSid())) {
                    myComment = "我评论了" + neighborUserCommentTo.getPostOwner().getName();
                    mCommentContent.setText(myComment);
                }
            }
            if (myComment != null && myLike != null) {
                mCommentContent.setText("我参与了" + neighborUserCommentTo.getPostOwner().getName());
            }
            mPostContent.setText(neighborUserCommentTo.getPostContent());
            mCommentTime.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatTimeShort, neighborUserCommentTo.getPostTime()));
            mViewGroup.addView(view);
            view.setOnClickListener(v -> {
                Intent intent = new Intent(getThisContext(), MyNeighborJoinDetailActivity.class);
                intent.putExtra("mode", neighborUserCommentTo.getPostContent());
                startActivity(intent);
            });
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;
        }
    }


}