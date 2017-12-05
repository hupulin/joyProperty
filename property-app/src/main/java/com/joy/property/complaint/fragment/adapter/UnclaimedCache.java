package com.joy.property.complaint.fragment.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by Admin on 2015-02-10
 */
public class UnclaimedCache {


    private View view;
    private ImageView mImageView;
    private TextView mTypeName;
    private TextView mTime;
    private TextView mTimes;
    private TextView mDispose;
    private TextView mResponse;
    private TextView mReplyTime;
    private TextView mDate;
    private TextView mEvaluaion;
    private TextView mService;
    private TextView mSolve;
    private TextView mEvalua;
    private TextView mServiceBooking;

    public UnclaimedCache(View view) {
        this.view = view;
    }

    public ImageView getmImageView() {
        if (mImageView == null) {
            mImageView = (ImageView) view.findViewById(R.id.image);
        }
        return mImageView;
    }
    //满意度
    public TextView getmEvaluaion() {
        if (mEvaluaion == null) {
            mEvaluaion = (TextView) view.findViewById(R.id.EvaluationItem);
        }
        return mEvaluaion;
    }
    //服务态度
    public TextView getmService() {
        if (mService == null) {
            mService = (TextView) view.findViewById(R.id.Service);
        }
        return mService;
    }

    //服务态度
    public TextView getmSolve() {
        if (mSolve == null) {
            mSolve = (TextView) view.findViewById(R.id.Solve);
        }
        return mSolve;
    }

    public TextView getmEvalua() {
        if (mEvalua == null) {
            mEvalua = (TextView) view.findViewById(R.id.Evalua);
        }
        return mEvalua;
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

    public TextView getmTimes() {
        if (mTimes == null) {
            mTimes = (TextView) view.findViewById(R.id.times);
        }
        return mTimes;
    }

    //处理时间
    public TextView getmTimeDispose() {
        if (mDispose == null) {
            mDispose = (TextView) view.findViewById(R.id.time_dispose);
        }
        return mDispose;
    }
    //相应时间
    public TextView getmTimeResponse() {
        if (mResponse == null) {
            mResponse = (TextView) view.findViewById(R.id.time_response);
        }
        return mResponse;
    }
    public TextView getmDate() {
        if (mDate == null) {
            mDate = (TextView) view.findViewById(R.id.date);
        }
        return mDate;
    }


    public TextView getmTimeReplyTime(){

        if (mReplyTime == null) {
            mReplyTime = (TextView) view.findViewById(R.id.time_replyTime);
        }
        return mReplyTime;

    }
    public TextView getmServiceBooking() {
        if (mServiceBooking == null) {
            mServiceBooking = (TextView) view.findViewById(R.id.servicebooking);
        }
        return mServiceBooking;
    }



}
