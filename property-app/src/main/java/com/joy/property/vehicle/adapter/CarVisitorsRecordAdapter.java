package com.joy.property.vehicle.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.visitor.VisitorCardTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

/**
 * Created by usb on 2016/6/29.
 */
public class CarVisitorsRecordAdapter extends ModeListAdapter<VisitorCardTo> {
    private LayoutInflater inflater;

    public CarVisitorsRecordAdapter(Context context) {
        super(context);
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        CarVisitorsRecordCache holder;
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_carvisitorsrecord, null);

            holder = new CarVisitorsRecordCache(row);
            row.setTag(holder);

        } else {
            holder = (CarVisitorsRecordCache) row.getTag();
        }


        VisitorCardTo visitorCardTo = mList.get(position);

        if (!TextUtils.isEmpty(visitorCardTo.getVisitorImage())) {
            String[] path = visitorCardTo.getVisitorImage().split(";");
            displayImage(holder.getImageView(), path[0], R.drawable.car_default_ic);
        } else {
            displayImage(holder.getImageView(), "", R.drawable.car_default_ic);
        }

        holder.getmVisiting_time().setText(visitorCardTo.getVisitTime());
        holder.getmDeparture_time().setText(visitorCardTo.getLeaveTime());

        return row;
    }
}
