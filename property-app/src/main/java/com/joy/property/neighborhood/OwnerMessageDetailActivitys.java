package com.joy.property.neighborhood;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.ActivityBarColorUtil;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborCommentParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborLikeParam;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborUserCommentTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.library.utils.DateUtil;

import com.joy.property.R;
import com.joy.property.utils.CustomDialog;
import com.joy.property.utils.SpUtil;
import com.joy.property.base.BaseActivity;


import com.joyhome.nacity.app.util.CustomDialogFragment;

import com.wefika.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2014-11-26.
 * 邻居圈消息详情页
 */
public class OwnerMessageDetailActivitys extends BaseActivity implements OnClickListener {

    private ImageView postIcon;
    private TextView postOwner;
    private ImageView postImage;
    private TextView postContent;
    private TextView mDelete;
    private TextView mTime;
    private ImageView mTriangle;
    private FlowLayout mFlImage;
    private LinearLayout mllComment;
    private Button mCommentOPen;
    private ImageView mCommentIcon;
    private TextView mCommentUser;
    private TextView mCommentTime;
    private TextView mReplyContent;
    private EditText mContent;
    private NeighborUserCommentTo mUserComment;
    private View view;
    private List<NeighborLikeTo> list;
    private PopupWindow mPopupWindow = null;
    private Button btnPraise;
    private boolean firstLoad=false;
    private   List<NeighborLikeTo>likeTos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_message);
        findById();
        ActivityBarColorUtil.initWindowState(this, R.color.white);
        initIntentDatas();
        initDatas();

    }

    private void findById() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        postIcon = (ImageView) findViewById(R.id.headImage);
        postOwner = (TextView) findViewById(R.id.postOwner);
        postImage = (ImageView) findViewById(R.id.post_image);
        postContent = (TextView) findViewById(R.id.post_content);
        mCommentOPen = (Button) findViewById(R.id.comment_open);
        mCommentOPen.setOnClickListener(this);
        mTime = (TextView) findViewById(R.id.time);
        mTriangle = (ImageView) findViewById(R.id.triangle);
        mFlImage = (FlowLayout) findViewById(R.id.fl_image);
        mllComment = (LinearLayout) findViewById(R.id.ll_comment);
        mDelete = (TextView) findViewById(R.id.delete);
        mDelete.setOnClickListener(this);
    }

    private void initIntentDatas() {
        mUserComment = (NeighborUserCommentTo) getIntent().getSerializableExtra("mode");
    }


    private void initDatas() {

        if (mUserComment.getPostTo().getPostOwner() != null) {
            if (!mUserHelper.getSid().equals(mUserComment.getPostTo().getPostOwner().getSid())) {
                mDelete.setVisibility(View.INVISIBLE);
            }
        }

        postImage.setVisibility(View.GONE);
        mTriangle.setVisibility(View.GONE);
        mFlImage.setVisibility(View.GONE);
        /**
         * postOwner icon
         */
        if (mUserComment.getPostTo().getPostOwner() != null) {
            displayImage(postIcon, mUserComment.getPostTo().getPostOwner().getIcon(), R.drawable.guest_head_image);
        }


        mTime.setText(mUserComment.getPostTo().getPostTimeStr());
        postOwner.setText(mUserComment.getPostTo().getPostOwner().getName());
        postContent.setText(mUserComment.getPostTo().getPostContent());
        if (!TextUtils.isEmpty(mUserComment.getPostTo().getPostImages())) {
            postImage.setVisibility(View.VISIBLE);
            String[] imageArr = mUserComment.getPostTo().getPostImages().split(";");
            displayImage(postImage, imageArr[0]);
        }
        /**
         * 设置点赞显示
         */
        list = mUserComment.getPostTo().getLikeList();
        if (list != null && list.size() > 0) {
            mTriangle.setVisibility(View.VISIBLE);
            mFlImage.setVisibility(View.VISIBLE);
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT,
                    FlowLayout.LayoutParams.WRAP_CONTENT);
            int i = -1;
            for (NeighborLikeTo likeTo : list) {
                i++;
                TextView textView = new TextView(this);
                if (i < list.size()-1) {
                    textView.setText(likeTo.getLikeOwner().getName() + ",");
                } else {
                    textView.setText(likeTo.getLikeOwner().getName());
                }

                mFlImage.addView(textView, mFlImage.getChildCount(), params);
            }
        }


        /**
         * 评论列表
         */

        List<NeighborCommentTo> commentList = mUserComment.getPostTo().getCommentList();

        if (commentList != null && commentList.size() > 0) {
            mTriangle.setVisibility(View.VISIBLE);
            mllComment.setVisibility(View.VISIBLE);
            for (NeighborCommentTo commentTo : commentList) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.bottomMargin = 6;
                view = getLayoutInflater().inflate(R.layout.list_item_circle_content, null);
                mCommentIcon = (ImageView) view.findViewById(R.id.icon);
                mCommentUser = (TextView) view.findViewById(R.id.comment_user);
                mReplyContent = (TextView) view.findViewById(R.id.comment_content);
                mCommentTime = (TextView) view.findViewById(R.id.time);
                if (commentTo.getCommentOwner() != null) {
                    displayImage(mCommentIcon, commentTo.getCommentOwner().getIcon(), R.drawable.guest_head_image);
                }

                if (commentTo.getAtOwner() != null && commentTo.getCommentOwner() != null) {

                    String value = "";
                    if (commentTo.getAtOwner() != null) {
                        value = String.format("<font color='#95a5b0'>%s</font><font color='#333333'>%s</font><font color='#95a5b0'>%s</font>",
                                commentTo.getCommentOwner().getName(), "回复", commentTo.getAtOwner().getName());
                    } else {
                        value = String.format("<font color='#95a5b0'>%s</font>", commentTo.getCommentOwner().getName());
                    }
                    mCommentUser.setText(Html.fromHtml(value));

                } else {
                    if (commentTo.getCommentOwner() != null) {
                        mCommentUser.setText(commentTo.getCommentOwner().getName());
                    }
                }
                mReplyContent.setText(commentTo.getCommentContent());
                mCommentTime.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatTimeShort, commentTo.getCommentTime()));

                view.setTag(commentTo);
                mllComment.addView(view, layoutParams);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NeighborCommentTo mComment = (NeighborCommentTo) v.getTag();
                        if(!(mComment.getCommentOwner().getSid().equals(mUserHelper.getSid())))
                        showEditorInput(mComment);
                    }
                });
            }
        }
    }

    private void showEditorInput(final NeighborCommentTo item) {
        int mWidth = getScreenWidthPixels(this);
        final CustomDialog dialog = new CustomDialog(this, mWidth, 120, R.layout.diolag_editor_input, R.style.myDialogTheme);
        final Button mSend = (Button) dialog.findViewById(R.id.btn_send);
        mContent = (EditText) dialog.findViewById(R.id.like_content);
        if (item != null && item.getCommentOwner() != null) {
            mContent.setHint("@" + item.getCommentOwner().getName());
        }
        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
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
                    Toast.makeText(getThisContext(), "请输入评论内容!", Toast.LENGTH_LONG).show();
                } else {
                    NeighborCommentParam mParam = new NeighborCommentParam();
                    if (item != null) {
                        mParam.setAtOwnerSid(item.getCommentOwner().getSid());
                    }
                    mParam.setPostSid(mUserComment.getPostSid());
                    mParam.setCommentContent(mContent.getText().toString());
                    mParam.setCommentOwnerSid(mUserHelper.getSid());
                    NeighborApi api = ApiClient.create(NeighborApi.class);
                    final CustomDialogFragment dialogFragment = new CustomDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), "");
                    api.addNeighborComment(mParam, new HttpCallback<MessageTo<NeighborCommentTo>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<NeighborCommentTo> msg, Response response) {
                            dialogFragment.dismissAllowingStateLoss();
                            if (msg.getSuccess() == 0) {
                                mTriangle.setVisibility(View.VISIBLE);
                                NeighborCommentTo commentTo = msg.getData();
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                                        (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                layoutParams.bottomMargin = 6;
                                view = getLayoutInflater().inflate(R.layout.list_item_circle_content, null);
                                mCommentIcon = (ImageView) view.findViewById(R.id.icon);
                                mCommentUser = (TextView) view.findViewById(R.id.comment_user);
                                mReplyContent = (TextView) view.findViewById(R.id.comment_content);
                                mCommentTime = (TextView) view.findViewById(R.id.time);
                                if (commentTo.getCommentOwner() != null) {
                                    displayImage(mCommentIcon, commentTo.getCommentOwner().getIcon(), R.drawable.guest_head_image);
                                }

                                String user = "";
                                if (commentTo.getAtOwner() != null && commentTo.getCommentOwner() != null) {
                                    user = String.format("<font color='#95a5b0'>%s</font><font color='#333333'>%s</font><font color='#95a5b0'>%s</font>", commentTo.getCommentOwner().getName(), "回复", commentTo.getAtOwner().getName());
                                } else {
                                    if (commentTo.getCommentOwner() != null) {
                                        user = String.format("<font color='#95a5b0'>%s</font>", commentTo.getCommentOwner().getName());
                                    }

                                }
                                mCommentUser.setText(Html.fromHtml(user));
                                mCommentTime.setText(DateUtil.getDateTimeFormat(DateUtil.mFormatTimeShort, commentTo.getCommentTime()));
                                mReplyContent.setText(commentTo.getCommentContent());
                                mllComment.addView(view, layoutParams);
                                view.setTag(msg.getData());
                                view.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        NeighborCommentTo mComment = (NeighborCommentTo) v.getTag();
                                        showEditorInput(mComment);
                                    }
                                });
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

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.comment_open:
                initPopupWindow();
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                } else {
                    mPopupWindow.showAsDropDown(mCommentOPen, -60, -60);
                }
                break;
            case R.id.delete:
                deletePost();
                break;

            default:
                break;
        }
    }

    private void initPopupWindow() {

        mPopupWindow = new PopupWindow(getThisContext());
        mPopupWindow.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);

        View mView = getLayoutInflater().inflate(R.layout.list_item_popupwindow, null);
        btnPraise = (Button) mView.findViewById(R.id.btn_good);
        likeTos.clear();
        likeTos.addAll(mUserComment.getPostTo().getLikeList());
   if(!firstLoad){
    for(NeighborLikeTo likeTo:likeTos){

        if(likeTo.getLikeOwner().getSid().equals(mUserHelper.getSid())){
            btnPraise.setBackgroundResource(R.drawable.selector_circle_cancel);
        }else {
            btnPraise.setBackgroundResource(R.drawable.selector_circle_good);
        }
    }
   firstLoad=true;
   }
        else {
       if (!SpUtil.getBoolean(getThisContext(), "isGoodComment"))
           btnPraise.setBackgroundResource(R.drawable.selector_circle_cancel);
       else
           btnPraise.setBackgroundResource(R.drawable.selector_circle_good);
   }
        final Button btnComment = (Button) mView.findViewById(R.id.btn_comment);
        final RelativeLayout mPopup = (RelativeLayout) mView.findViewById(R.id.popup);
        mPopupWindow.setContentView(mView);

        mPopup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        btnPraise.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                postPraise();
                mPopupWindow.dismiss();
            }
        });


        btnComment.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showEditorInput(null);
                mPopupWindow.dismiss();
            }
        });


    }


    /**
     * 点赞
     */
    private void postPraise() {

        NeighborLikeParam likeParam = new NeighborLikeParam();
        likeParam.setLikeOwnerSid(mUserHelper.getSid());
        likeParam.setPostSid(mUserComment.getPostSid());
        NeighborApi api = ApiClient.create(NeighborApi.class);
        api.addNeighborLike(likeParam, new HttpCallback<MessageTo<NeighborLikeTo>>(this) {
            @Override
            public void success(MessageTo<NeighborLikeTo> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    FlowLayout.LayoutParams param = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
                    param.setMargins(11, 22, 0, 11);

                    if (msg.getData() != null && msg.getData().getLikeOwner() != null) {
                        mTriangle.setVisibility(View.VISIBLE);
                        mFlImage.setVisibility(View.VISIBLE);
                        mFlImage.removeAllViews();

                        SpUtil.put(getThisContext(),"isGoodComment",false);
                        ImageView imageView = new ImageView(getThisContext());
                        imageView.setBackgroundResource(R.drawable.selector_good);
                        mFlImage.addView(imageView, 0, param);
                        list.add(msg.getData());
                        int i = -1;
                        for (NeighborLikeTo likeTo : list) {
                            i++;
                            TextView textView = new TextView(getThisContext());
                            if (i < list.size()-1) {

                                textView.setText(likeTo.getLikeOwner().getName() + ",");
                            } else {
                                textView.setText(likeTo.getLikeOwner().getName());
                            }

                            mFlImage.addView(textView, mFlImage.getChildCount(), param);
                        }

                    } else {

                        SpUtil.put(getThisContext(), "isGoodComment", true);
                            Iterator<NeighborLikeTo> iterator = list.iterator();
                            while (iterator.hasNext()) {
                                NeighborLikeTo likeTo = iterator.next();
                                if (likeTo.getLikeOwner()!=null&&mUserHelper.getSid().equals(likeTo.getLikeOwner().getSid())) {
                                    iterator.remove();
                                }
                            }

                        mFlImage.removeAllViews();

                            mTriangle.setVisibility(View.VISIBLE);
                            mFlImage.setVisibility(View.VISIBLE);
                            ImageView imageView = new ImageView(OwnerMessageDetailActivitys.this);
                            imageView.setBackgroundResource(R.drawable.selector_good);
                            mFlImage.addView(imageView, 0, param);
                            int i = -1;
                            for (NeighborLikeTo likeTo : list) {
                                i++;
                                TextView textView = new TextView(OwnerMessageDetailActivitys.this);
                                if (i < list.size()-1) {
                                    textView.setText(likeTo.getLikeOwner().getName() +",");
                                } else {
                                    textView.setText(likeTo.getLikeOwner().getName());
                                }

                                mFlImage.addView(textView, mFlImage.getChildCount(), param);
                            }


                            if (mllComment.getChildCount() > 0) {
                                mTriangle.setVisibility(View.VISIBLE);
                            } else {
                                mTriangle.setVisibility(View.GONE);
                            }

                            if (mFlImage.getChildCount() > 1) {
                                mFlImage.setVisibility(View.VISIBLE);
                            } else {
                                mFlImage.setVisibility(View.GONE);
                            }

                        }


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


    /**
     * 删除
     */
    private void deletePost() {
        final CustomDialog dialog = new CustomDialog(this, R.layout.dialog_neighbor_post, R.style.myDialogTheme);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                NeighborApi api = ApiClient.create(NeighborApi.class);
                api.delNeighborPost(mUserComment.getPostSid(), new HttpCallback<MessageTo<Void>>(getThisContext()) {
                    @Override
                    public void success(MessageTo<Void> msg, Response response) {
                        if (msg.getSuccess() == 0) {
                            Intent intent = new Intent(getThisContext(), NeighborActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
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
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    protected Context getThisContext() {
        return OwnerMessageDetailActivitys.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpUtil.put(getThisContext(), "isGoodComment", false);
    }
}
