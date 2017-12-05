package com.joy.property.neighborhood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.ActivityBarColorUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborMessageParam;
import com.jinyi.ihome.module.neighbor.NeighborUserCommentTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.library.utils.ConfigUtil;
import com.joy.library.utils.DateUtil;

import com.joy.property.R;
import com.joy.property.person.MyNeighborJoinLikeDetailActivity;
import com.joy.property.MainApp;
import com.joy.property.base.BaseActivity;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2014-11-28.
 * 消息列表页
 */
public class NeighborhoodCircleNewsActivity extends
        BaseActivity implements OnClickListener {



    private PullToRefreshScrollView mPullToRefreshScrollView;
    private int pageIndex = 0;
    private TextView mOldNews ;
    private LinearLayout mViewGroup;
    private boolean haveLike=false;
    private boolean haveComment=false;
    private List<NeighborLikeTo> likeTos=new ArrayList<>();
    private List<NeighborUserCommentTo> commentTos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighborhood_circle_news);
        findById();
        mPullToRefreshScrollView.getRefreshableView();
        mPullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);

        getNeighborPostUserNewLike(0);
        getNeighborPostUserNewComment(0);

        initData();
  
    }
    private void findById() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        mPullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.scrollView);
        mOldNews = (TextView) findViewById(R.id.old_news);
        mOldNews.setOnClickListener(this);
        mViewGroup = (LinearLayout) findViewById(R.id.viewGroup);
    }
    private void initData() {

        mPullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                pageIndex = 0;
                getNeighborPostUserNewComment(0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                pageIndex++;
                getNeighborPostUserOldComment(pageIndex);
            }
        });

    }
    ///获取发贴的 新的用户评论
    private void getNeighborPostUserNewComment(final int index) {
        NeighborMessageParam mMessageParam = new NeighborMessageParam();
        mMessageParam.setApartmentSid(mHelper.getSid());
        mMessageParam.setUserSid(mUserHelper.getSid());
        mMessageParam.setPageIndex(index);
        String str = ConfigUtil.getString(sp, MainApp.KeyValue.KEY_REFRESH_TIME, "");
        mMessageParam.setLastTime(DateUtil.getFormatDateLongTime(str));
        NeighborApi api = ApiClient.create(NeighborApi.class);
        final com.joyhome.nacity.app.util.CustomDialogFragment customDialogFragment = new com.joyhome.nacity.app.util.CustomDialogFragment();
        customDialogFragment.show(getSupportFragmentManager(), "");
        api.findNeighborPostUserNewComment(mMessageParam, new HttpCallback<MessageTo<List<NeighborUserCommentTo>>>(this) {
            @Override
            public void success(MessageTo<List<NeighborUserCommentTo>> msg, Response response) {
                customDialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    if (index == 0) {

                        mViewGroup.removeAllViews();
                    }
                    haveComment = true;
                    commentTos.clear();
                    commentTos.addAll(msg.getData());

                    if (haveLike) {
                        if (likeTos.size() > 0) {
                            double timeSort[] = new double[commentTos.size() + likeTos.size()];
                            for (int i = 0; i < commentTos.size(); i++) {
                                timeSort[i] = commentTos.get(i).getCommentTime().getTime();
                                System.out.println(commentTos.get(i).getCommentTime() + "]]]]]]]]]]]]]");
                            }
                            for (int i = commentTos.size(); i < commentTos.size() + likeTos.size(); i++) {
                                timeSort[i] = likeTos.get(i - commentTos.size()).getLikeTime().getTime();
                                System.out.println(likeTos.get(i - commentTos.size()).getLikeTime() + "==============");
                            }
                         //   removePaths(timeSort);
                            Arrays.sort(timeSort);

                            double lastTime=0;
                            for (int i = 0; i < timeSort.length; i++) {
                                System.out.println(timeSort[i] + "time*********");

                                boolean canAdd=false;
                                if (lastTime!=timeSort[i]) {
                                    lastTime = timeSort[i];
                                    canAdd=true;
                                }
                                for (NeighborLikeTo likeTo : likeTos) {

                                    if (likeTo.getLikeTime().getTime() == timeSort[timeSort.length - i - 1]&&canAdd)
                                        setDataLike1(likeTo);
                                }
                                for (NeighborUserCommentTo commentTo : commentTos)
                                    if (commentTo.getCommentTime().getTime() == timeSort[timeSort.length - i - 1]&&canAdd)
                                        setData1(commentTo);
                            }
                        } else {
                            setData(msg.getData());
                        }
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
    private void getNeighborPostUserNewLike(final int index) {

        NeighborMessageParam mMessageParam = new NeighborMessageParam();
        mMessageParam.setApartmentSid(mHelper.getSid());
        mMessageParam.setUserSid(mUserHelper.getSid());
        mMessageParam.setPageIndex(index);
        String str = ConfigUtil.getString(sp, MainApp.KeyValue.KEY_REFRESH_TIME, "");
        mMessageParam.setLastTime(DateUtil.getFormatDateLongTime(str));
        NeighborApi api = ApiClient.create(NeighborApi.class);
        final com.joyhome.nacity.app.util.CustomDialogFragment customDialogFragment = new com.joyhome.nacity.app.util.CustomDialogFragment();
        customDialogFragment.show(getSupportFragmentManager(), "");
        api.findNeighborPostUserNewLike(mMessageParam, new HttpCallback<MessageTo<List<NeighborLikeTo>>>(this) {
            @Override
            public void success(MessageTo<List<NeighborLikeTo>> msg, Response response) {
                customDialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    if (index == 0) {

                        mViewGroup.removeAllViews();
                    }

                    haveLike = true;
                    likeTos.addAll(msg.getData());

                    if (haveComment) {
                        if (commentTos.size() > 0) {
                            double timeSort[] = new double[commentTos.size() + likeTos.size()];
                            for (int i = 0; i < commentTos.size(); i++) {

                                timeSort[i] = commentTos.get(i).getCommentTime().getTime();

                            }
                            for (int i = commentTos.size(); i < commentTos.size() + likeTos.size(); i++)
                                timeSort[i] = likeTos.get(i - commentTos.size()).getLikeTime().getTime();

                            Arrays.sort(timeSort);
                            double lastTime=0;
                            for (int i = 0; i < timeSort.length; i++) {

                                boolean canAdd=false;
                                if (lastTime!=timeSort[i]) {
                                    lastTime = timeSort[i];
                                    canAdd=true;
                                }
                                for (NeighborLikeTo likeTo : likeTos)
                                    if (likeTo.getLikeTime().getTime() == timeSort[timeSort.length - 1 - i]&&canAdd) {
                                        setDataLike1(likeTo);

                                    }
                                for (NeighborUserCommentTo commentTo : commentTos)
                                    if (commentTo.getCommentTime().getTime() == timeSort[timeSort.length - 1 - i]&&canAdd) {
                                        setData1(commentTo);
                                        System.out.println(commentTo.getCommentTime() + "getcomment-------");
                                    }
                            }
                        }
                        setDataLike(msg.getData());
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
    private void setData(List<NeighborUserCommentTo> data) {

        LayoutInflater inflater = LayoutInflater.from(getThisContext());

        for (NeighborUserCommentTo neighborUserCommentTo : data) {
            View view = inflater.inflate(R.layout.neighbor_join_item,null);
            ImageView mUserHeadImage = (ImageView) view.findViewById(R.id.headImage);
            TextView mCommentUser = (TextView) view.findViewById(R.id.joinName);
            TextView mPostContent = (TextView) view.findViewById(R.id.commentContent);
            TextView mCommentContent = (TextView) view.findViewById(R.id.commentedName);
            TextView mCommentTime = (TextView) view.findViewById(R.id.joinTime);
            view.setTag(neighborUserCommentTo);
            if (neighborUserCommentTo.getCommentOwner()!= null) {
                displayImage(mUserHeadImage,neighborUserCommentTo.getCommentOwner().getIcon(),R.drawable.guest_head_image);
                if (!TextUtils.isEmpty(neighborUserCommentTo.getCommentOwner().getName())) {
                    mCommentUser.setText(neighborUserCommentTo.getCommentOwner().getName());

                }
            }

            mCommentContent.setText(neighborUserCommentTo.getCommentContent());
            mPostContent.setText(neighborUserCommentTo.getPostTo().getPostContent());
            mCommentTime.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatTimeShort, neighborUserCommentTo.getCommentTime()));
            mViewGroup.addView(view);
            view.setOnClickListener(v -> {
                Intent intent = new Intent(getThisContext(), MyNeighborJoinLikeDetailActivity.class);
                intent.putExtra("mode", (NeighborUserCommentTo)v.getTag());
                intent.putExtra("isComment",true);
                startActivity(intent);
            });
        }
    }

    private void setDataLike(List<NeighborLikeTo> data) {

        LayoutInflater inflater = LayoutInflater.from(getThisContext());

        for (NeighborLikeTo neighborUserCommentTo : data) {

            View view = inflater.inflate(R.layout.neighbor_join_item,null);
            ImageView mUserHeadImage = (ImageView) view.findViewById(R.id.headImage);
            TextView mCommentUser = (TextView) view.findViewById(R.id.joinName);
            TextView mPostContent = (TextView) view.findViewById(R.id.commentContent);
            TextView mCommentContent = (TextView) view.findViewById(R.id.commentedName);
            TextView mCommentTime = (TextView) view.findViewById(R.id.joinTime);
            view.setTag(neighborUserCommentTo);
            if (neighborUserCommentTo.getLikeOwner()!= null) {
                displayImage(mUserHeadImage,neighborUserCommentTo.getLikeOwner().getIcon(),R.drawable.guest_head_image);
                if (!TextUtils.isEmpty(neighborUserCommentTo.getLikeOwner().getName())) {
                    mCommentUser.setText(neighborUserCommentTo.getLikeOwner().getName());
                }
            }

            mCommentContent.setText(neighborUserCommentTo.getLikeOwner().getName()+"赞了我");
            mPostContent.setText(neighborUserCommentTo.getPostTo().getPostContent());
            mCommentTime.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatTimeShort, neighborUserCommentTo.getLikeTime()));
            mViewGroup.addView(view);
            view.setOnClickListener(v -> {
                Intent intent = new Intent(getThisContext(), MyNeighborJoinLikeDetailActivity.class);
                intent.putExtra("mode", (NeighborLikeTo)v.getTag());
                startActivity(intent);
            });
        }
    }
    private void setData1(NeighborUserCommentTo neighborUserCommentTo) {

        LayoutInflater inflater = LayoutInflater.from(getThisContext());


        View view = inflater.inflate(R.layout.neighbor_join_item,null);
        ImageView mUserHeadImage = (ImageView) view.findViewById(R.id.headImage);
        TextView mCommentUser = (TextView) view.findViewById(R.id.joinName);
        TextView mPostContent = (TextView) view.findViewById(R.id.commentContent);
        TextView mCommentContent = (TextView) view.findViewById(R.id.commentedName);
        TextView mCommentTime = (TextView) view.findViewById(R.id.joinTime);
        view.setTag(neighborUserCommentTo);
        if (neighborUserCommentTo.getCommentOwner()!= null) {
            displayImage(mUserHeadImage,neighborUserCommentTo.getCommentOwner().getIcon(),R.drawable.guest_head_image);
            if (!TextUtils.isEmpty(neighborUserCommentTo.getCommentOwner().getName())) {
                mCommentUser.setText(neighborUserCommentTo.getCommentOwner().getName());

            }
        }

        mCommentContent.setText(neighborUserCommentTo.getCommentContent());
        mPostContent.setText(neighborUserCommentTo.getPostTo().getPostContent());
        mCommentTime.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatTimeShort, neighborUserCommentTo.getCommentTime()));
        mViewGroup.addView(view);
        view.setOnClickListener(v -> {
            Intent intent = new Intent(getThisContext(), MyNeighborJoinLikeDetailActivity.class);
            intent.putExtra("mode", (NeighborUserCommentTo)v.getTag());
            intent.putExtra("isComment",true);
            startActivity(intent);
        });

    }

    private void setDataLike1(NeighborLikeTo neighborUserCommentTo) {

        View view = View.inflate(getThisContext(),R.layout.neighbor_join_item,null);
        ImageView mUserHeadImage = (ImageView) view.findViewById(R.id.headImage);
        TextView mCommentUser = (TextView) view.findViewById(R.id.joinName);
        TextView mPostContent = (TextView) view.findViewById(R.id.commentContent);
        TextView mCommentContent = (TextView) view.findViewById(R.id.commentedName);
        TextView mCommentTime = (TextView) view.findViewById(R.id.joinTime);
        view.setTag(neighborUserCommentTo);
        if (neighborUserCommentTo.getLikeOwner()!= null) {
            displayImage(mUserHeadImage,neighborUserCommentTo.getLikeOwner().getIcon(),R.drawable.guest_head_image);
            if (!TextUtils.isEmpty(neighborUserCommentTo.getLikeOwner().getName())) {
                mCommentUser.setText(neighborUserCommentTo.getLikeOwner().getName());
            }
        }

        mCommentContent.setText(neighborUserCommentTo.getLikeOwner().getName()+"赞了我");
        mPostContent.setText(neighborUserCommentTo.getPostTo().getPostContent());
        mCommentTime.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatTimeShort, neighborUserCommentTo.getLikeTime()));
        mViewGroup.addView(view);
        view.setOnClickListener(v -> {
            Intent intent = new Intent(getThisContext(), MyNeighborJoinLikeDetailActivity.class);
            intent.putExtra("mode", (NeighborLikeTo)v.getTag());
            startActivity(intent);
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.old_news:
                getNeighborPostUserOldComment(0);
                break;
            default:
                break;
        }
    }


    // 获取邻居圈 发帖的更早的评论
    private void getNeighborPostUserOldComment(final int index) {
        NeighborMessageParam mMessageParam = new NeighborMessageParam();
        mMessageParam.setApartmentSid(mHelper.getSid());
        String str = ConfigUtil.getString(sp, MainApp.KeyValue.KEY_REFRESH_TIME, "");
        mMessageParam.setLastTime(DateUtil.getFormatDateLongTime(str));
        mMessageParam.setUserSid(mUserHelper.getSid());
        mMessageParam.setPageIndex(index);
        final com.joyhome.nacity.app.util.CustomDialogFragment customDialogFragment = new com.joyhome.nacity.app.util.CustomDialogFragment();
        customDialogFragment.show(getSupportFragmentManager(), "");
        NeighborApi api = ApiClient.create(NeighborApi.class);
        api.findNeighborPostUserOldComment(mMessageParam, new HttpCallback<MessageTo<List<NeighborUserCommentTo>>>(this) {
            @Override
            public void success(MessageTo<List<NeighborUserCommentTo>> msg, Response response) {
               customDialogFragment.dismiss();
                if (msg.getSuccess() == 0) {

                    if (msg.getData() != null && msg.getData().size() > 0) {
                        setData(msg.getData());

                        mOldNews.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getThisContext(),
                                "这已经是最早的消息了！", Toast.LENGTH_SHORT).show();
                    }
                    mPullToRefreshScrollView.onRefreshComplete();
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
            customDialogFragment.dismiss();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ConfigUtil.saveString(sp, MainApp.KeyValue.KEY_REFRESH_TIME,
                DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, new Date()));
    }
    @Override
    protected Context getThisContext() {
        return NeighborhoodCircleNewsActivity.this;
    }

    private Double[] removePaths( double[] arr) {
        List<Double> doubleList = new ArrayList<>();
        List<Double> temp = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            doubleList.add(arr[i]);

        }

        for (int i = 0; i < doubleList.size(); i++)
            if (!temp.contains(doubleList.get(i)))
                temp.add(doubleList.get(i));
        Double doubles[] = new Double[temp.size()];

        for (int i = 0; i < temp.size(); i++)
            doubles[i] = temp.get(i);
        return doubles;
    }
}