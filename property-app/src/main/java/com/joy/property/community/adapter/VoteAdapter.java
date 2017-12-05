package com.joy.property.community.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.vote.VoteTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

/**
 * Created by Admin on 2015-07-09
 */
public class VoteAdapter extends ModeListAdapter<VoteTo> {
    public VoteAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        VoteCache holder = null;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.list_vote_item, null);
            holder = new VoteCache(row);
            row.setTag(holder);

        } else {
            holder = (VoteCache) row.getTag();
        }

        VoteTo voteTo = mList.get(position);


        if (!TextUtils.isEmpty(voteTo.getVoteSubject())) {

            holder.getmVoteSubject().setText(voteTo.getVoteSubject());
        }

        String mCreateOn = "";
        if (voteTo.getCreatedOn() != null) {

            mCreateOn = DateUtil.getDateTimeFormat(DateUtil.mDateFormatString, voteTo.getCreatedOn());
        }



        holder.getmVoteDate().setText(mCreateOn + "—" + voteTo.getDueDate().replace("-","."));
        return row;
    }
}
