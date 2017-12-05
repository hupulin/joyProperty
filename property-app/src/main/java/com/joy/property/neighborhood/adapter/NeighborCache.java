package com.joy.property.neighborhood.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;
import com.wefika.flowlayout.FlowLayout;

/**
 * Created by Admin on 2014-11-25
 */
public class NeighborCache {
    private View view;
    private TextView postOwner;
    private ImageView headImage;
    private TextView postContent;
    private TextView postTime;
    private Button commentOpen;
    private FlowLayout flowLayout;
    private ImageView triangle;
    private TextView likeList;
    private TextView mDelete;
    private TextView mLabel;
    private LinearLayout commentList;
    private LinearLayout mLike;
    private View mLikeLine;
    private View padding;
    private TextView expandAll;
    private TextView likeCount;
    private TextView commentCount;
    private LinearLayout btnLike;
    private LinearLayout btnComment;
    private ImageView likeIcon;
    private View divideLine;
    private RelativeLayout enterMainPage;
    private RelativeLayout  myLike;
    public NeighborCache(View view) {
        this.view = view;
    }
    public TextView getPostOwner() {
        if (postOwner == null) {
            postOwner = (TextView) view.findViewById(R.id.tv_apartment);
        }
        return postOwner;
    }
    public View getLikeLine() {
        if (mLikeLine == null) {
            mLikeLine = view.findViewById(R.id.like_line);
        }
        return mLikeLine;
    }
    public View getPadding() {
        if (padding == null) {
            padding = view.findViewById(R.id.padding);
        }
        return padding;
    }
    public ImageView getHeadImage() {
        if (headImage == null) {
            headImage = (ImageView) view.findViewById(R.id.iv_headImage);
        }
        return headImage;
    }

    public TextView getPostContent() {
        if (postContent == null) {
            postContent = (TextView) view.findViewById(R.id.content);
        }
        return postContent;
    }



    public TextView getPostTime() {
        if (postTime == null) {

            postTime = (TextView) view.findViewById(R.id.date);

        }
        return postTime;
    }



//    public Button getCommentOpen() {
//        if (commentOpen == null) {
//            commentOpen = (Button) view.findViewById(R.id.comment_open);
//        }
//        return commentOpen;
//    }

    public FlowLayout getFlowLayout() {
        if (flowLayout == null) {
            flowLayout = (FlowLayout) view.findViewById(R.id.ll_image);
        }
        return flowLayout;
    }

    public ImageView getTriangle() {
        if (triangle == null) {
            triangle = (ImageView) view.findViewById(R.id.triangle);
        }
        return triangle;
    }

    public TextView getLikeList() {
        if (likeList == null) {
            likeList = (TextView) view.findViewById(R.id.like_list);
        }
        return likeList;
    }

    public LinearLayout getCommentList() {

        if (commentList == null) {
            commentList = (LinearLayout) view.findViewById(R.id.comment_list);
        }
        return commentList;
    }

    public TextView getmDelete() {
        if (mDelete == null) {
            mDelete = (TextView) view.findViewById(R.id.delete);
        }
        return mDelete;
    }

    public TextView getmLabel() {
        if (mLabel==null) {
            mLabel = (TextView) view.findViewById(R.id.label);
        }
        return mLabel;
    }

    public LinearLayout getmLike() {
        if (mLike == null) {
            mLike = (LinearLayout) view.findViewById(R.id.like);
        }
        return mLike;
    }
public TextView getExpandAll(){
    if(expandAll==null)
        expandAll= (TextView) view.findViewById(R.id.expandAll);
    return expandAll;
}
    public TextView getLikeCount(){
        if(likeCount==null)
            likeCount= (TextView) view.findViewById(R.id.likeCount);
        return likeCount;
    }
    public TextView getCommentCount(){
        if(commentCount==null)
            commentCount= (TextView) view.findViewById(R.id.commentCount);
        return commentCount;
    }
    public LinearLayout getBtnLike(){
        if(btnLike==null)
            btnLike= (LinearLayout) view.findViewById(R.id.btnLike);
        return btnLike;
    }
    public LinearLayout getBtnComment(){
        if(btnComment==null)
            btnComment= (LinearLayout) view.findViewById(R.id.btnComment);
        return btnComment;
    }
    public ImageView getLikeIcon(){
        if(likeIcon==null)
            likeIcon= (ImageView) view.findViewById(R.id.likeIcon);
        return likeIcon;
    }
    public View getDivideLine(){
        if (divideLine==null)
            divideLine=view.findViewById(R.id.divideLine);
        return divideLine;
    }
    public RelativeLayout getEnterMainPage(){
        if (enterMainPage==null)
            enterMainPage= (RelativeLayout) view.findViewById(R.id.enterMainPage);
        return enterMainPage;
    }
    public RelativeLayout getMyLike(){
        if (myLike==null)
            myLike= (RelativeLayout) view.findViewById(R.id.myLike);
        return myLike;
    }
}
