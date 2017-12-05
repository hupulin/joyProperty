package com.joy.property.person;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.common.widget.UploadImage;

import com.joy.property.R;
import com.joy.property.common.PictureShowActivity;
import com.joy.property.neighborhood.adapter.DetailPostToAdapter;
import com.joy.property.neighborhood.adapter.NeighborPostToAdapter;
import com.joy.property.utils.CustomDialog;
import com.joyhome.nacity.app.base.BaseActivity;

import com.joyhome.nacity.app.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by Admin on 2014-11-26.
 * 邻居圈消息详情页
 */
public class MyNeighborJoinDetailActivity extends
        BaseActivity implements OnClickListener ,DetailPostToAdapter.INeighborPostTo {
    private PullToRefreshListView mPullToRefreshListView;
    private int pageIndex = 0;
    private List<NeighborPostTo> postList = new ArrayList<>();
    private List<NeighborPostTo> joinList = new ArrayList<>();
    private DetailPostToAdapter mAdapter = null;
    private EditText mContent;
    private ListView mListView;
    private boolean haveJoin;
    private  boolean haveTopic;

    protected SharedPreferences sp ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neighborhoodview_join);

        findById();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
        mListView = mPullToRefreshListView.getRefreshableView();

        registerForContextMenu(mListView);
        mAdapter = new DetailPostToAdapter(this);
mAdapter.setOnNeighborPost(this);
        mAdapter.setList(postList);
        mListView.setAdapter(mAdapter);

        if (getIntent().getBooleanExtra("isJoin",false))
        getJoin(0);
        else
            setList(0);
    }

    private void findById() {
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.lv_PullToRefreshListView);
       findViewById(R.id.ll_title).setVisibility(View.VISIBLE);
        TextView title = (TextView) findViewById(R.id.title);
         title.setText("我参与的帖子");
         findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    private void setList(final int index) {



            NeighborApi api = ApiClient.create(NeighborApi.class);
            final CustomDialogFragment customDialogFragment = new CustomDialogFragment();
            customDialogFragment.show(getSupportFragmentManager(), "");
            api.getMyNeighborJoin(mUserHelper.getSid(), 0, new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
                @Override
                public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                    customDialogFragment.dismissAllowingStateLoss();
                    if (msg.getSuccess() == 0) {
                        haveJoin = true;
                        postList.clear();

                        Observable.from(msg.getData()).filter(neighborPostTo -> neighborPostTo.getPostSid().equals(getIntent().getStringExtra("mode"))).subscribe(postList::add);

                        if (postList.size() > 0 && !TextUtils.isEmpty(postList.get(0).getPostSubject())) {
                            //  postList.get(0).setPostSubject("#" + postList.get(0).getPostSubject()+ "# " );
                            postList.get(0).setPostContent(postList.get(0) + postList.get(0).getPostContent());
                        }
                        mAdapter.setList(postList);
                        mAdapter.setOnNeighborPost(MyNeighborJoinDetailActivity.this);
                        mListView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void failure(RetrofitError error) {
                    customDialogFragment.dismissAllowingStateLoss();
                    super.failure(error);
                }
            });
        }

    public void getJoin(int index){

        NeighborApi api = ApiClient.create(NeighborApi.class);
        final CustomDialogFragment customDialogFragment = new CustomDialogFragment();
        customDialogFragment.show(getSupportFragmentManager(), "");
        api.getJoinOrTopic(mHelper.getSid(), mUserHelper.getSid(), 0, new HttpCallback<MessageTo<List<NeighborPostTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<NeighborPostTo>> msg, Response response) {
                customDialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {

                    joinList.clear();
                    Observable.from(msg.getData()).filter(neighborPostTo -> neighborPostTo.getPostSid().equals(getIntent().getStringExtra("mode"))).subscribe(joinList::add);
                    if (joinList.size() > 0 && !TextUtils.isEmpty(joinList.get(0).getTopicTitle())) {

                        joinList.get(0).setPostSubject("#"+joinList.get(0).getTopicTitle()+"#");
                       // joinList.get(0).setPostContent(joinList.get(0).getPostSubject() + joinList.get(0).getPostContent());
                    }
               if (joinList==null||joinList.size()==0){
                   setList(0);
                   return;
               }
                    mAdapter.setList(joinList);
                    mAdapter.setOnNeighborPost(MyNeighborJoinDetailActivity.this);

                    mListView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();


                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                customDialogFragment.dismissAllowingStateLoss();

                super.failure(error);
                System.out.println(error.toString() + "---------------");
            }
        });
    }



    private void setListComplete() {
        mPullToRefreshListView.onRefreshComplete();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.ll_invite:
                neighborShowDialog();
                break;
            case R.id.rl_invite:
                neighborShowDialog();
                break;
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }



    /**
     * 分享对话框
     */
    private void neighborShowDialog() {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_neighbor_share, R.style.myDialogTheme);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        TextView mWeChat = (TextView) dialog.findViewById(R.id.wechat);
        TextView mQQ = (TextView) dialog.findViewById(R.id.QQ);
        final TextView mMsg = (TextView) dialog.findViewById(R.id.short_msg);
        TextView mMoment = (TextView) dialog.findViewById(R.id.moment);
   //     ShareSDK.initSDK(getThisContext());
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.share_ic);
//        mWeChat.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Wechat.ShareParams wsp = new Wechat.ShareParams();
//                wsp.setShareType(Platform.SHARE_TEXT);
//                wsp.setText("悦嘉家，等你回家！http://dwz.cn/11N1DJ");
//                Platform weChat = ShareSDK.getPlatform(getThisContext(), Wechat.NAME);
//                weChat.share(wsp);
//
//            }
//        });
//
//        mQQ.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                QQ.ShareParams qsp = new QQ.ShareParams();
//                qsp.setShareType(Platform.SHARE_IMAGE);
//                qsp.setTitle("悦嘉家，等你回家");
//                qsp.setImagePath(UploadImage.getInstance(getThisContext()).saveBitmap(bitmap));
//                Platform qq = ShareSDK.getPlatform(QQ.NAME);
//                qq.share(qsp);
//            }
//        });
//        mMsg.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShortMessage.ShareParams msp = new ShortMessage.ShareParams();
//                msp.setShareType(Platform.SHARE_TEXT);
//                msp.setText("悦嘉家，等你回家！http://dwz.cn/11N1DJ");
//                Platform msg = ShareSDK.getPlatform(ShortMessage.NAME);
//                msg.share(msp);
//            }
//        });
//        mMoment.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WechatMoments.ShareParams wsp = new WechatMoments.ShareParams();
//                wsp.setShareType(Platform.SHARE_IMAGE);
//                wsp.setTitle("悦嘉家等你回家");
//                wsp.setImageData(bitmap);
//                Platform moment = ShareSDK.getPlatform(WechatMoments.NAME);
//                moment.share(wsp);
//            }
//        });

        dialog.setCancelable(true);
        dialog.show();
    }


    @Override
    public void postComment(final NeighborPostTo item, final NeighborCommentTo mItem) {

        int mWidth =getScreenWidthPixels(getThisContext());
        final CustomDialog dialog = new CustomDialog(getThisContext(), mWidth, 120, R.layout.diolag_editor_input, R.style.myDialogTheme);
        final Button mSend = (Button) dialog.findViewById(R.id.btn_send);
        mContent = (EditText) dialog.findViewById(R.id.like_content);

        // Button btnSmile = (Button) dialog.findViewById(R.id.btn_smile);
        if(mItem!=null) {
            if (mItem.getCommentOwner().getSid().equals(mUserHelper.getSid())) {
                deleteComment(mItem);
                return;
            }
        }
//if(mUserHelper.getSid().equals(mItem.getCommentOwner().getSid())){
//    deleteComment(mItem);
//}
        if (mItem != null && mItem.getCommentOwner() != null) {
            mContent.setHint("@" + mItem.getCommentOwner().getName());
        }

        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    mSend.setBackgroundResource(R.drawable.selector_send_button);
                } else {
                    mSend.setBackgroundResource(R.drawable.null_send_ic);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });


        mSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mContent.getText().toString())) {
                    Toast.makeText(getThisContext(), "请输入评论内容！", Toast.LENGTH_LONG).show();
                } else {
                    NeighborCommentParam mParam = new NeighborCommentParam();
                    mParam.setPostSid(item.getPostSid());
                    mParam.setCommentContent(mContent.getText().toString());
                    mParam.setCommentOwnerSid(mUserHelper.getSid());
                    if (mItem != null && mItem.getCommentOwner() != null) {
                        mParam.setAtOwnerSid(mItem.getCommentOwner().getSid());
                    }
                    NeighborApi api = ApiClient.create(NeighborApi.class);
                    final CustomDialogFragment dialogFragment = new CustomDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), "");
                    api.addNeighborComment(mParam, new HttpCallback<MessageTo<NeighborCommentTo>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<NeighborCommentTo> msg, Response response) {
                            dialogFragment.dismissAllowingStateLoss();
                            if (msg.getSuccess() == 0) {
                                List<NeighborCommentTo> list = item.getCommentList();
                                list.add(msg.getData());
                                item.setCommentList(list);
                                mAdapter.notifyDataSetChanged();
                                dialog.dismiss();

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
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
        mContent.setFocusable(true);
        mContent.setFocusableInTouchMode(true);
        mContent.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) mContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(mContent, 0);
            }
        }, 300);


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
                    if (msg.getData() != null) {
                        List<NeighborLikeTo> likeToList = item.getLikeList();
                        likeToList.add(msg.getData());
                        item.setLikeList(likeToList);
                    } else {
                        List<NeighborLikeTo> likeList = item.getLikeList();
                        Iterator<NeighborLikeTo> iterator = likeList.iterator();
                        while (iterator.hasNext()) {
                            NeighborLikeTo likeTo = iterator.next();
                            if (mUserHelper.getSid().equals(likeTo.getLikeOwner().getSid())) {
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
        btnAdd.setOnClickListener(new OnClickListener() {
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
        btnCancel.setOnClickListener(new OnClickListener() {
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

    }



    @Override
    public void enterCommentMainPage(NeighborCommentTo item) {

    }

    @Override
    public void postViewImage(int index, String item) {
        if (TextUtils.isEmpty(item)) {
            return;
        }
        Intent intent = new Intent(getThisContext(), PictureShowActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("path", item);
        startActivity(intent);
    }



    @Override
    public void onDestroy() {

        super.onDestroy();
        if (mAdapter != null && mAdapter.getListView()!=null) {
            mAdapter.getListView().destroyDrawingCache();
        }
//EventBus.getDefault().unregister(MyFragment.this);
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
    public void deleteCommentContent(final NeighborCommentTo mItem){
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
}
