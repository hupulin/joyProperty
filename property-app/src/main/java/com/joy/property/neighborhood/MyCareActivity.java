package com.joy.property.neighborhood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborCommentHandleParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborLikeParam;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodInteractionTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogTo;
import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.property.R;
import com.joy.property.common.LongPictureShowActivity;
import com.joy.property.neighborhood.adapter.NeighborPostToAdapter;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.SpUtil;
import com.joy.property.utils.htmlText.HtmlTextView;
import com.joy.property.MainApp;
import com.joy.property.base.BaseActivity;


import com.joyhome.nacity.app.util.CustomDialogFragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by xz on 2016/10/31.
 **/
public class MyCareActivity extends BaseActivity implements NeighborPostToAdapter.INeighborPostTo, View.OnClickListener {
    private PullToRefreshListView listView;
    private HtmlTextView investigateContent;
    private List<NeighborPostTo> postList = new ArrayList<>();

    private EditText mContent;
    private int pageIndex = 0;
    private boolean havesend;
    private NeighborPostToAdapter mAdapter;
    private boolean isChange = false;
    private TextView textView;
    private ListView mListView;
    private TextView expandAllTop;
    private String content;
    private String subContent;
    private Button confirmJoin;
    private String topicTitle;
    private TextView commentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investigate);
        initView();
     refreshData();
    }


    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.DISABLED);
        mListView = listView.getRefreshableView();

        textView = (TextView) findViewById(R.id.content);

  findViewById(R.id.all).setOnClickListener(this);
        TextView titleText = (TextView) findViewById(R.id.titleText);
        titleText.setText("我关注的");
        confirmJoin = (Button) findViewById(R.id.confirmJoin);
        confirmJoin.setOnClickListener(this);
        confirmJoin.setVisibility(View.GONE);
       findViewById(R.id.iv_back).setOnClickListener(this);
        commentContent = (TextView) findViewById(R.id.commentContent);
        SpUtil.put(getThisContext(), "BackRefresh", false);
    }

    private void refreshData() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //  subContent=subContent.substring(0,subContent.length()-4);

        if (!isChange) {
            isChange = true;
            mAdapter = new NeighborPostToAdapter(getThisContext());
            mAdapter.setOnNeighborPost(MyCareActivity.this);
            setList(0);
            mAdapter.setList(postList);

            listView.setAdapter(mAdapter);
        }

    }

    private void setList(final int index) {

            NeighborApi api = ApiClient.create(NeighborApi.class);
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            customDialog.show(getSupportFragmentManager(), "");

            api.getMyCareList(mUserHelper.getSid(), index,
                    new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                            if (msg.getSuccess() == 0) {
                                customDialog.dismissAllowingStateLoss();

                                SpUtil.put(getThisContext(), "BackRefresh", false);


                                if (msg.getData() != null && msg.getData().size()>0) {
                                    postList.clear();
                                    postList.addAll(msg.getData());
                                    postList = getSubList(postList);
                                    mAdapter.setList(postList);

                                }
                                mAdapter.notifyDataSetChanged();
                                setListComplete();

                            } else {
                                Toast.makeText(getThisContext(),
                                        msg.getMessage(), Toast.LENGTH_LONG).show();
                                customDialog.dismissAllowingStateLoss();
                            }
                            setListComplete();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            setListComplete();
                            customDialog.dismissAllowingStateLoss();
                            System.out.println(error.toString());
                        }
                    });

        }



    private void setListComplete() {
        listView.onRefreshComplete();
    }

    @Override
    public void postComment(final NeighborPostTo item, final NeighborCommentTo mItem) {

        Intent intent=new Intent(getThisContext(), PostDetailActivity.class);
        intent.putExtra("postSid", item.getPostSid());
        startActivity(intent);

    }

    /**
     * 点赞
     *
     * @param item
     */
    @Override
    public void postPraise(final NeighborPostTo item) {

        NeighborLikeParam likeParam = new NeighborLikeParam();
        likeParam.setPostSid(item.getPostSid());
        likeParam.setLikeOwnerSid(mUserHelper.getSid());
        NeighborApi api = ApiClient.create(NeighborApi.class);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.addNeighborLike(likeParam, new HttpCallback<MessageTo<NeighborLikeTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborLikeTo> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    List<NeighborLikeTo> likeToList = item.getLikeList();
                    if (likeToList == null)
                        likeToList = new ArrayList<>();
                    if (msg.getData() != null) {


                        likeToList.add(msg.getData());
                        item.setLikeList(likeToList);
                    } else {
                        List<NeighborLikeTo> likeList = item.getLikeList();

                        Iterator<NeighborLikeTo> iterator = likeList.iterator();
                        while (iterator.hasNext()) {
                            NeighborLikeTo likeTo = iterator.next();
                            if (likeTo.getLikeOwner()!=null&&mUserHelper.getSid().equals(likeTo.getLikeOwner().getSid())) {
                                iterator.remove();
                            }
                        }
                        item.setLikeList(likeList);
                    }
                    mAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismissAllowingStateLoss();
                super.failure(error);
            }
        });

    }


    /**
     * 删除帖子
     *
     * @param item
     */
    @Override
    public void postDeleteItem(final NeighborPostTo item) {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_neighbor_post, R.style.myDialogTheme);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NeighborApi api = ApiClient.create(NeighborApi.class);
                api.delNeighborPost(item.getPostSid(), new HttpCallback<MessageTo<Void>>(getThisContext()) {
                    @Override
                    public void success(MessageTo<Void> msg, Response response) {
                        if (msg.getSuccess() == 0) {

                            setList(pageIndex);


                            dialog.dismiss();
                        } else {
                            Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        super.failure(error);
                    }
                });


            }
        });
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();

    }


    @Override
    public void enterMainPage(NeighborPostTo item) {
        Intent intent=new Intent(getThisContext(), MyNeighborActivity.class);
        intent.putExtra("otherMainPage",true);

        intent.putExtra("otherSid", item.getPostOwner().getSid());
        OwnerMessageTo messageTo=new OwnerMessageTo();
        messageTo.setOwnerSid(item.getPostOwner().getSid());
        messageTo.setFamilyName(item.getPostOwner().getName());
        messageTo.setOwnerImage(item.getPostOwner().getIcon());
        intent.putExtra("messageTo", messageTo);
        startActivity(intent);

        goToAnimation(1);
    }

    @Override
    public void enterPostDetail(NeighborPostTo item) {
        Intent intent=new Intent(getThisContext(), PostDetailActivity.class);
        intent.putExtra("postSid",item.getPostSid());
        startActivity(intent);
    }

    @Override
    public void enterCommentMainPage(NeighborCommentTo item) {
        if (mUserHelper.getSid().equals(item.getCommentOwner().getSid()))
            return;
        Intent intent=new Intent(getThisContext(), MyNeighborActivity.class);
        intent.putExtra("otherMainPage",true);

        intent.putExtra("otherSid", item.getCommentOwner().getSid());
        OwnerMessageTo messageTo=new OwnerMessageTo();
        messageTo.setOwnerSid(item.getCommentOwner().getSid());
        messageTo.setFamilyName(item.getCommentOwner().getName());
        messageTo.setOwnerImage(item.getCommentOwner().getIcon());
        intent.putExtra("messageTo", messageTo);
        startActivity(intent);

        goToAnimation(1);
    }
    @Override
    public void postViewImage(int index, String item) {
        if (TextUtils.isEmpty(item)) {
            return;
        }
        Intent intent = new Intent(getThisContext(), LongPictureShowActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("path", item);
        startActivity(intent);
    }

    private void deleteComment(final NeighborCommentTo mItem) {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.delete_comment, R.style.myDialogTheme);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnAdd.setOnClickListener(v -> {
            deleteCommentContent(mItem);
            dialog.dismiss();
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    public void deleteCommentContent(final NeighborCommentTo mItem) {
        if (!TextUtils.isEmpty(mHelper.getSid())) {
            NeighborApi api = ApiClient.create(NeighborApi.class);
            final CustomDialogFragment customDialog = new CustomDialogFragment();
            customDialog.show(getSupportFragmentManager(), "");
            api.deleteComment(mItem.getCommentSid(),
                    new HttpCallback<MessageTo<List<NeighborCommentHandleParam>>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<List<NeighborCommentHandleParam>> msg, Response response) {
                            if (msg.getSuccess() == 0) {
                                customDialog.dismissAllowingStateLoss();
                                setList(pageIndex);

                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {

                            customDialog.dismissAllowingStateLoss();
                            super.failure(error);
                        }
                    });
        }
    }

    public String getSubContent(TextView tv, String content) {


        tv.setText(content);
        Layout layout = tv.getLayout();
        int line = tv.getLayout().getLineCount();
        if (line <5) {
            return content;
        } else {

            String result = "";
            String text = layout.getText().toString();
            for (int i = 0; i < 5; i++) {
                int start = layout.getLineStart(i);
                int end = layout.getLineEnd(i);
                result += text.substring(start, end);
            }


            return result.substring(0, result.length() - 4);
        }
    }

    public List<NeighborPostTo> getSubList(List<NeighborPostTo> postList){

        for (int i=0;i<postList.size();i++) {
            if (!TextUtils.isEmpty(postList.get(i).getRefId()))
                postList.get(i).setPostSubject(postList.get(i).getTopicTitle());
            if (!TextUtils.isEmpty(postList.get(i).getPostSubject()))
                postList.get(i).setPostSubject("#"+postList.get(i).getPostSubject()+"# ");
            if (!TextUtils.isEmpty(postList.get(i).getPostSubject())){
                if (postList.get(i).getPostContent() != null && !(postList.get(i).getPostSubject()+postList.get(i).getPostContent()).equals(getSubContent(textView,postList.get(i).getPostSubject()+ postList.get(i).getPostContent())))
                    postList.get(i).setSubPostContent((getSubContent(textView, postList.get(i).getPostSubject() + postList.get(i).getPostContent())).substring(0,getSubContent(textView, postList.get(i).getPostSubject() + postList.get(i).getPostContent()).length()-4));
                else
                    postList.get(i).setSubPostContent(null);
            }
            else {
                if (postList.get(i).getPostContent() != null && !(postList.get(i).getPostContent()).equals(getSubContent(textView, postList.get(i).getPostContent())))
                    postList.get(i).setSubPostContent(getSubContent(textView, postList.get(i).getPostContent()).substring(0,getSubContent(textView, postList.get(i).getPostContent()).length()-4));
                else
                    postList.get(i).setSubPostContent(null);

            }
        }
        String subContent;
        for (int j=0;j<postList.size();j++) {
            if (postList.get(j).getCommentList() != null) {
                for (int i = 0; i < postList.get(j).getCommentList().size(); i++) {
                    NeighborCommentTo commentTo = postList.get(j).getCommentList().get(i);
                    subContent = getSubContent(commentContent, commentTo.getCommentContent());
                    if (!subContent.equals(commentTo.getCommentContent()))
                        postList.get(j).getCommentList().get(i).setSubComment(subContent);

                }
            }
        }
        return postList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all:
                Intent intent = new Intent(getThisContext(), InteractActivity.class);
                intent.putExtra("allType", 2);
        startActivity(intent);
                break;
            case R.id.confirmJoin:
                intent=new Intent(getThisContext(),PostActivity.class);

                intent.putExtra("TopicSid",getIntent().getStringExtra("interactionSid"));
                intent.putExtra("topicTitle",topicTitle);
                startActivity(intent);
                joinCampaign();
                break;
            case R.id.iv_back:

                onBackPressed();
                goToAnimation(2);
                break;
        }
    }

    public void setHeadView(NeighborhoodInteractionTo interactionTo) {
        if (TextUtils.isEmpty(interactionTo.getActivityContent()))
            content="时光是琥珀 泪一滴滴被反锁 情书再不朽 也磨成沙漏 青春的上游 白云飞走苍狗与海鸥 闪过的念头 潺潺的溜走 命运好幽默 让爱的人都沉默 一整个宇宙 换一颗红豆 回忆如困兽 寂寞太久而渐渐温柔 放开了拳头 反而更自由 慢动作 缱绻胶卷 重播默片 定格一瞬间 我们在 告别的演唱会 说好不再见 你写给我 我的第一首歌 你和我 十指紧扣 默写前奏 可是那然后呢 还好我有 我这一首情歌 轻轻的 轻轻哼着 哭着笑着 我的 天长地久 长镜头 越拉越远 越来越远 事隔好几年 我们在 怀念的演唱会 礼貌的吻别 陪我唱歌 清唱你的情歌 舍不得 短短副歌 心还热着 也该告一段落 还好我有 我下一首情歌 生命宛如 静静的 相拥的河 永远 天长地久";
     else
        content=interactionTo.getActivityContent();
         subContent = getSubContent(textView,content);
        View headView = View.inflate(getThisContext(), R.layout.topic_head_view, null);

        investigateContent = (HtmlTextView) headView.findViewById(R.id.investigateContent);


        investigateContent.setHtmlFromString(interactionTo.getActivitySumary(), false);

        ImageView investigateImage = (ImageView) headView.findViewById(R.id.investigateImage);
        if (interactionTo.getNeighborhoodMediaTo()!=null)
        Picasso.with(getThisContext()).load(MainApp.getPicassoImagePath(interactionTo.getNeighborhoodMediaTo().getMediaUrl())).into(investigateImage);
        mListView.addHeaderView(headView);
    }

    private class TextClick extends ClickableSpan {
        private TextView view;
        private String content;

        private String tag;






        public TextClick(TextView view, String content, String tag) {
            this.tag = tag;
            this.view = view;
            this.content = content;
        }



        @Override
        public void onClick(View widget) {
            investigateContent.setMaxLines(5);
            investigateContent.setText(subContent);
            expandAllTop.setVisibility(View.VISIBLE);

        }
    }
    public void joinCampaign(){
        NeighborApi api= ApiClient.create(NeighborApi.class);

        NeighborhoodLogParam param=new NeighborhoodLogParam();
        param.setOwnerSid(mUserHelper.getSid());
        param.setApartmentSid(mHelper.getSid());
        param.setRefId(getIntent().getStringExtra("interactionSid"));
        param.setType(2);

        param.setStatus(2);
        api.JoinCampaign(param, new HttpCallback<MessageTo<NeighborhoodLogTo>>(getThisContext()) {
            @Override
            public void success(MessageTo<NeighborhoodLogTo> msg, Response response) {

                if (msg.getSuccess()==0){
                    confirmJoin.setText("已报名");
                    confirmJoin.setBackgroundColor(Color.parseColor("#c4c4c4"));
                }else
                    ToastShowLong(getThisContext(),msg.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {

                System.out.println(error.toString());
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (SpUtil.getBoolean(getThisContext(), "BackRefresh"))
            setList(pageIndex);
    }
}
