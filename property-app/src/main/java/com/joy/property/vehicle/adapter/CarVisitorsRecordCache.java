package com.joy.property.vehicle.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by usb on 2016/6/29.
 */
public class CarVisitorsRecordCache {
    private View view;
    private ImageView imageView;
    private TextView mVisiting_time;
    private TextView mDeparture_time;

    public CarVisitorsRecordCache(View view) {
        this.view = view;
    }

    public ImageView getImageView() {
        if (imageView == null) {
            imageView = (ImageView) view.findViewById(R.id.image);
        }
        return imageView;
    }

    public TextView getmVisiting_time() {
        if (mVisiting_time == null) {
            mVisiting_time = (TextView) view.findViewById(R.id.visiting_time);
        }
        return mVisiting_time;
    }

    public TextView getmDeparture_time() {

        if (mDeparture_time == null) {
            mDeparture_time = (TextView) view.findViewById(R.id.departure_time);
        }
        return mDeparture_time;
    }


}
