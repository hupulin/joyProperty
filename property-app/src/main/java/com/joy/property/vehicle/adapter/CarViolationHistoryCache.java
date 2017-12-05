package com.joy.property.vehicle.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by Admin on 2015-03-27
 */
public class CarViolationHistoryCache {

    private View view;
    private ImageView imageView;
    private TextView mTitle;
    private TextView mContent;
    private TextView mTime;

    public CarViolationHistoryCache(View view) {
        this.view = view;
    }

    public ImageView getImageView() {
        if (imageView == null) {
            imageView = (ImageView) view.findViewById(R.id.image);
        }
        return imageView;
    }

    public TextView getmContent() {
        if (mContent == null) {
            mContent = (TextView) view.findViewById(R.id.content);
        }
        return mContent;
    }

    public TextView getmTitle() {

        if (mTitle == null) {
            mTitle = (TextView) view.findViewById(R.id.title);
        }
        return mTitle;
    }

    public TextView getmTime() {
        if (mTime == null) {
            mTime = (TextView) view.findViewById(R.id.date_time);
        }
        return mTime;
    }
}
