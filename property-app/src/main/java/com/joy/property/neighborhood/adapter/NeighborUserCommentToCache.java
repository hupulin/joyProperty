package com.joy.property.neighborhood.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by Admin on 2014-11-28
 */
public class NeighborUserCommentToCache {

    private View view;
    private ImageView mCommentIcon;
    private TextView mCommentUser;
    private TextView mCommentTime;
    private TextView mPostContent;
    private TextView mCommentContent;

    public NeighborUserCommentToCache(View view) {
        this.view = view;
    }

    public ImageView getmCommentIcon() {
        if (mCommentIcon == null) {
            mCommentIcon = (ImageView) view.findViewById(R.id.icon);
        }
        return mCommentIcon;
    }

    public TextView getmCommentUser() {
        if (mCommentUser == null) {
            mCommentUser = (TextView) view.findViewById(R.id.comment_user);
        }
        return mCommentUser;
    }

    public TextView getmCommentTime() {
        if (mCommentTime == null) {
            mCommentTime = (TextView) view.findViewById(R.id.time);
        }
        return mCommentTime;
    }

    public TextView getmPostContent() {
        if (mPostContent == null) {
            mPostContent = (TextView) view.findViewById(R.id.post_content);
        }
        return mPostContent;
    }

    public TextView getmCommentContent() {

        if (mCommentContent == null) {
            mCommentContent = (TextView) view.findViewById(R.id.comment_content);
        }
        return mCommentContent;
    }
}
