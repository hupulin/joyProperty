package com.joy.property.neighborhood.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.library.utils.DateUtil;

import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by xz on 2016/5/23.
 **/
public class FansAdapter extends ModeListAdapter<OwnerMessageTo> {
    private Context mContext;
    private Boolean joinCampaign;


    private String content;
    private String subContent;
    public FansAdapter(Context context,Boolean joinCampaign) {
        super(context);
        this.mContext = context;
        this.joinCampaign=joinCampaign;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        FansHolder holder;


    if (row == null) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(R.layout.fans_item, null);
        holder = new FansHolder(row);
        row.setTag(holder);
    } else {
        holder = (FansHolder) row.getTag();
    }

OwnerMessageTo mode=mList.get(position);
        holder.getFansName().setText(mode.getFamilyName());
        Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getOwnerImage())).into(holder.getHeadImage());
        if (joinCampaign){
            holder.getCareStatus().setText(DateUtil.getDateString(mode.getCreatedOn(), DateUtil.mDateTimeFormatString));
        }else {

            if (mode.getFlag() == 0) {
                holder.getCareStatus().setText("关注");
                holder.getCareStatus().setTextColor(Color.parseColor("#4fb2d6"));

            } else {
                holder.getCareStatus().setText("已关注");
                holder.getCareStatus().setTextColor(Color.parseColor("#d9d9d9"));
            }
        }
        holder.getCareStatus().setOnClickListener(v -> {
            if (listener!=null)
                listener.OnCancelCare(mode,holder.getCareStatus());
        });
    return row;
}
    private OnCancelCareListener listener;
    public void setOnCancelCareListener(OnCancelCareListener listener){
        this.listener=listener;
    }
    public interface OnCancelCareListener{
        void OnCancelCare(OwnerMessageTo ownerMessageTo, TextView care);
    }


}
