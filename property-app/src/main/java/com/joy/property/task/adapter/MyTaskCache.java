package com.joy.property.task.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by Admin on 2015-01-27.
 */
public class MyTaskCache {
    private View view;
    private ImageView mImage;
    private ImageView badComment;
    private TextView mCategory;
    private TextView mCreateOn;
    private TextView mCreateOns;
    private TextView mCreateOnf;
    private TextView mStatus;
    private TextView mApartment;
    private TextView mCr;
    private TextView mT;
    private TextView mServiceBooking;



    public TextView getm() {
        if (mT == null) {
            mT = (TextView) view.findViewById(R.id.t);
        }
        return mT;
    }
    public MyTaskCache(View view) {
        this.view = view;
    }

    public ImageView getmImage() {
        if (mImage == null) {
            mImage= (ImageView) view.findViewById(R.id.image);
        }
        return mImage;
    }
    public ImageView getBadComment() {
        if (badComment == null) {
            badComment= (ImageView) view.findViewById(R.id.bad_comment);
        }
        return badComment;
    }

    public TextView getmCategory() {
        if (mCategory == null) {
            mCategory = (TextView) view.findViewById(R.id.category);
        }
        return mCategory;
    }
    public TextView getmServiceBooking() {
        if (mServiceBooking == null) {
            mServiceBooking = (TextView) view.findViewById(R.id.servicebooking);
        }
        return mServiceBooking;
    }


    public TextView getmCreateOn() {
        if (mCreateOn == null) {
            mCreateOn = (TextView) view.findViewById(R.id.time);
        }
        return mCreateOn;
    }

    public TextView getmCreateOns() {
        if (mCreateOns == null) {
            mCreateOns = (TextView) view.findViewById(R.id.times);
        }
        return mCreateOns;
    }

    public TextView getmCr() {
        if (mCr == null) {
            mCr = (TextView) view.findViewById(R.id.timesinfo);
        }
        return mCr;
    }

    public TextView getmCreateOnf() {
        if (mCreateOnf == null) {
            mCreateOnf = (TextView) view.findViewById(R.id.timese);
        }

        return mCreateOnf;
    }


    public TextView getmStatus() {
        if (mStatus == null) {
            mStatus = (TextView) view.findViewById(R.id.status);
        }
        return mStatus;
    }

    public TextView getmApartment() {
        if (mApartment == null) {
            mApartment = (TextView) view.findViewById(R.id.apartment);
        }
        return mApartment;
    }

}
