package com.joy.property.neighborhood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.joy.library.utils.DateUtil;

import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by xz on 2016/5/23.
 **/
public class InvestigateAdapter extends ModeListAdapter<OwnerMessageTo> {
    private Context mContext;


    public InvestigateAdapter(Context context) {
        super(context);
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        InvestigateHolder holder;


    if (row == null) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(R.layout.investigate_item, null);
        holder = new InvestigateHolder(row);
        row.setTag(holder);
    } else {
        holder = (InvestigateHolder) row.getTag();
    }

OwnerMessageTo mode=mList.get(position);
    holder.getJoinName().setText(mode.getFamilyName());
        Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getOwnerImage())).into(holder.getHeadImage());
holder.getJoinTime().setText(DateUtil.getDateString(mode.getCreatedOn(), DateUtil.mDateTimeFormatString)+"参加");
    return row;
}
    }




