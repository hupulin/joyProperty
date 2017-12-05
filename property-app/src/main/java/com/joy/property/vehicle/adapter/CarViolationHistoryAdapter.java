package com.joy.property.vehicle.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.R;
import com.jinyi.ihome.module.car.CarViolationTo;
import com.joy.library.utils.DateUtil;

/**
 * Created by Admin on 2015-03-27
 */
public class CarViolationHistoryAdapter extends ModeListAdapter<CarViolationTo> {

    private LayoutInflater inflater;

    public CarViolationHistoryAdapter(Context context) {
        super(context);
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        CarViolationHistoryCache holder;
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_history,null);

            holder = new CarViolationHistoryCache(row);
            row.setTag(holder);

        } else {
           holder = (CarViolationHistoryCache) row.getTag();
        }


        CarViolationTo carViolationTo = mList.get(position);

        if (!TextUtils.isEmpty(carViolationTo.getViolationsImages())) {
            String [] path = carViolationTo.getViolationsImages().split(";");
            displayImage(holder.getImageView(), path[0], R.drawable.car_default_ic);
        }else {
            displayImage(holder.getImageView(),"",R.drawable.car_default_ic);
        }


        if (!TextUtils.isEmpty(carViolationTo.getPackingLocation())) {
            holder.getmTitle().setText(carViolationTo.getPackingLocation());
        }else {
            holder.getmTitle().setText("未记录违停地点");
        }

        if (!TextUtils.isEmpty(carViolationTo.getViolationsRemark())) {
          holder.getmContent().setText(carViolationTo.getViolationsRemark());
        }else {
            holder.getmContent().setText("暂无描述");
        }

        holder.getmTime().setText(
                DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString,
                carViolationTo.getCreatedOn()));

        return row;
    }
}
