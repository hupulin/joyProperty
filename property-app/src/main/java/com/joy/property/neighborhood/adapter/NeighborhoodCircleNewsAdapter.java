package com.joy.property.neighborhood.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.neighbor.NeighborUserCommentTo;
import com.joy.library.utils.DateUtil;

import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

/**
 * Created by Admin on 2014-11-28
 */
public class NeighborhoodCircleNewsAdapter  extends ModeListAdapter<NeighborUserCommentTo> {

    public NeighborhoodCircleNewsAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View  row = convertView;
        NeighborUserCommentToCache holder = null;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.list_item_new_message,null);
            holder = new NeighborUserCommentToCache(row);
            row.setTag(holder);

        }else {
          holder = (NeighborUserCommentToCache) row.getTag();
        }



        NeighborUserCommentTo mode = mList.get(position);

        if (mode.getCommentOwner()!= null) {
            displayImage(holder.getmCommentIcon(),mode.getCommentOwner().getIcon(), R.drawable.guest_head_image);
            if (!TextUtils.isEmpty(mode.getCommentOwner().getName())) {
                holder.getmCommentUser().setText(mode.getCommentOwner().getName());
            }

        }

        holder.getmCommentContent().setText(mode.getCommentContent());
        holder.getmPostContent().setText(mode.getPostTo().getPostContent());
        holder.getmCommentTime().setText(DateUtil.getDateTimeFormat(DateUtil.mFormatTimeShort, mode.getCommentTime()));
        return  row;
    }
}
