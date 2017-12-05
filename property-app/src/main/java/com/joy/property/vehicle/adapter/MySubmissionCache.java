package com.joy.property.vehicle.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by Admin on 2015-02-04
 */
public class MySubmissionCache {

    private View view ;
    private ImageView mImageView;
    private TextView  mTypeName;
    private TextView  mServiceDesc;
    private TextView  mTime;
    private TextView  mProgress;
    private TextView  mApartmentName;
    private TextView  mSerialNumber;
    private TextView  mEmergency;

    @Override
    public String toString() {
        return "MySubmissionCache{" +
                "view=" + view +
                ", mTypeName=" + mTypeName +
                ", mTime=" + mTime +
                ", mServiceDesc=" + mServiceDesc +
                ", mSerialNumber=" + mSerialNumber +
                ", mProgress=" + mProgress +
                ", mImageView=" + mImageView +
                ", mEmergency=" + mEmergency +
                ", mApartmentName=" + mApartmentName +
                '}';
    }

    public TextView getmEmergency() {
        if (mEmergency == null) {
            mEmergency = (TextView) view.findViewById(R.id.emergency);
        }
        return mEmergency;
    }

    public void setmEmergency(TextView mEmergency) {
        this.mEmergency = mEmergency;
    }

    public MySubmissionCache(View view) {
        this.view = view;

    }

    public ImageView getmImageView() {
        if (mImageView == null) {
            mImageView = (ImageView) view.findViewById(R.id.image);
        }
        return mImageView;
    }

    public TextView getmTypeName() {
        if (mTypeName == null) {
            mTypeName = (TextView) view.findViewById(R.id.typeName);
        }
        return mTypeName;
    }



    public TextView getmTime() {
        if (mTime == null) {
            mTime = (TextView) view.findViewById(R.id.time);
        }
        return mTime;
    }

    public TextView getmProgress() {

        if (mProgress == null) {
            mProgress = (TextView) view.findViewById(R.id.progress);
        }
        return mProgress;
    }

    public TextView getmServiceDesc() {
        if (mServiceDesc == null) {
            mServiceDesc = (TextView) view.findViewById(R.id.type_desc);
        }
        return mServiceDesc;
    }

    public TextView getmApartmentName() {
        if (mApartmentName == null) {
            mApartmentName = (TextView) view.findViewById(R.id.apartment_name);
        }
        return mApartmentName;
    }
    public TextView getmSerialNumber() {
        if (mSerialNumber == null) {
            mSerialNumber = (TextView) view.findViewById(R.id.serial_number);
        }
        return mSerialNumber;
    }
}
