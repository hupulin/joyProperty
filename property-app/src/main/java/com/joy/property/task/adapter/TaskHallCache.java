package com.joy.property.task.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by Admin on 2015-01-27.
 */
public class TaskHallCache {

    private View view;
    private TextView mCategory;
    private TextView mPostTime;
    private ImageView mImage;
    private TextView mApartment;
    public TaskHallCache(View view) {
        this.view = view;
    }

    public TextView getmCategory() {
        if (mCategory == null) {
            mCategory = (TextView) view.findViewById(R.id.category);
        }
        return mCategory;
    }

    public TextView getmPostTime() {
        if (mPostTime == null) {
            mPostTime = (TextView) view.findViewById(R.id.post_time);
        }
        return mPostTime;
    }

    public ImageView getmImage() {
        if (mImage == null) {
            mImage = (ImageView) view.findViewById(R.id.image);
        }
        return mImage;
    }

    public TextView getmApartment() {
        if (mApartment == null) {
            mApartment = (TextView) view.findViewById(R.id.apartment);
        }
        return mApartment;
    }
}
