package com.joy.property.community.adapter;


import android.view.View;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by Admin on 2015-07-09
 */
public class VoteCache {
    private View mView;
    private TextView mVoteSubject;
    private TextView mVoteDate;

    public VoteCache(View view) {
        this.mView = view;
    }

    public TextView getmVoteSubject() {
        if (mVoteSubject == null) {
            mVoteSubject = (TextView) mView.findViewById(R.id.voteSubject);
        }
        return mVoteSubject;
    }

    public TextView getmVoteDate() {
        if (mVoteDate == null) {
            mVoteDate = (TextView) mView.findViewById(R.id.voteDate);
        }
        return mVoteDate;
    }
}
