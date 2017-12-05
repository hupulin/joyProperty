package com.joy.property.neighborhood.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.neighbor.NeighborhoodInteractionTo;
import com.joy.library.utils.DateUtil;

import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by xz on 2016/5/23.
 **/
public class InteractAdapter extends ModeListAdapter<NeighborhoodInteractionTo> {
    private Context mContext;
    public InteractAdapter(Context context) {
        super(context);
        this.mContext = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        InteractItemHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.interact_item, null);
            holder = new InteractItemHolder(row);
            row.setTag(holder);
        } else {
            holder = (InteractItemHolder) row.getTag();
        }

        NeighborhoodInteractionTo recordTo=mList.get(position);
        holder.getContent().setText(recordTo.getActivityContent());
        holder.getTheme().setText(recordTo.getActivityTitle());
        if (recordTo.getCreatedOn()!=null&&recordTo.getActivityDeadline()!=null)
        holder.getTime().setText(DateUtil.getDateString(recordTo.getCreatedOn(), DateUtil.mDateFormatString)+"-"+ DateUtil.getDateString(recordTo.getActivityDeadline(), DateUtil.mDateFormatString));
Picasso.with(mContext).load(MainApp.getPicassoImagePath(recordTo.getNeighborhoodMediaTo().getMediaUrl())).into(holder.getContentImage());

        Spannable spannableJoin=new SpannableString("参与人("+(recordTo.getShowpaticipants()==0?recordTo.getCanyuUserCount():0)+")");
        spannableJoin.setSpan(new ForegroundColorSpan(0xff4fb2d6), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.getJoinNumber().setText(spannableJoin);
        System.out.println();
        Spannable spannableLook=new SpannableString("浏览数("+(recordTo.getShowviews()==0?recordTo.getViewCount():0)+")");
        spannableLook.setSpan(new ForegroundColorSpan(0xff4fb2d6), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.getLookNumber().setText(spannableLook);
if (recordTo.getShowpaticipants()==1&&recordTo.getShowviews()==1){
    holder.getJoinLayout().setVisibility(View.GONE);
}else if (recordTo.getShowpaticipants()==1&&recordTo.getShowviews()==0){
    holder.getJoinLayout().setVisibility(View.VISIBLE);
    holder.getLookNumber().setVisibility(View.VISIBLE);
    holder.getJoinNumber().setVisibility(View.GONE);
}else if (recordTo.getShowpaticipants()==0&&recordTo.getShowviews()==1){
    holder.getJoinLayout().setVisibility(View.VISIBLE);
    holder.getLookNumber().setVisibility(View.GONE);
    holder.getJoinNumber().setVisibility(View.VISIBLE);
}else {
    holder.getJoinLayout().setVisibility(View.VISIBLE);
    holder.getLookNumber().setVisibility(View.VISIBLE);
    holder.getJoinNumber().setVisibility(View.VISIBLE);
}
        if (!recordTo.isOutOfDate()){
            holder.getInteractState().setText("进行中");
            recordTo.setOutOfDate(false);
            holder.getInteractItemLayout().setBackgroundColor(Color.parseColor("#ffffff"));
        }else {
            holder.getInteractState().setText("已结束");
            recordTo.setOutOfDate(true);
            holder.getInteractItemLayout().setBackgroundColor(Color.parseColor("#f9f9f9"));
        }
        return row;
    }
}
