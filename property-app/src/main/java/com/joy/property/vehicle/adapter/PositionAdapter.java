package com.joy.property.vehicle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceInspectionPositionTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

import java.util.Map;

/**
 * Created by Admin on 2015-04-08
 */
public class PositionAdapter extends ModeListAdapter<ServiceInspectionPositionTo> {

    private LayoutInflater inflater;
    public PositionAdapter(Context context) {
        super(context);
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PositionSelectHolder  holder;
        if (row == null) {
           row = inflater.inflate(R.layout.select_position_item,null);
            holder =  new PositionSelectHolder(row);
            row.setTag(holder);
        }else {
            holder = (PositionSelectHolder) row.getTag();
        }
        ServiceInspectionPositionTo mode=mList.get(position);
        holder.getPositionName().setText(mode.getPositionName());
        holder.getNextIcon().setVisibility(mode.getChildList()==null?View.GONE:View.VISIBLE);

        return row;
    }
}
