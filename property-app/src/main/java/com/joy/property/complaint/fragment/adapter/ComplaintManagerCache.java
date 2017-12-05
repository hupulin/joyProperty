package com.joy.property.complaint.fragment.adapter;

import android.view.View;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.github.mikephil.charting.charts.PieChart;
import com.joy.property.R;

/**
 * Created by Admin on 2015-02-10
 */
public class ComplaintManagerCache {


    private View view;
    private TextView mApartment;
    private TextView mPending;
    private TextView mProcessing;
    private TextView mProcessed;
    private TextView mComplain;
    private TextView mGood;
    private TextView mSOSO;
    private TextView mBad;
    private PieChart mPie;

    private TextView mService_Good;
    private TextView mService_So;
    private TextView mService_Bad;
    private TextView mSolve_Good;
    private TextView mSolve_So;
    private TextView mSolve_Bad;
    private ConvenientBanner banner;


    public ComplaintManagerCache(View view) {
        this.view = view;
    }

    public TextView getmApartment() {
        if (mApartment == null) {
            mApartment = (TextView) view.findViewById(R.id.apartment);
        }
        return mApartment;
    }

    public TextView getmPending() {
        if (mPending == null) {
            mPending = (TextView) view.findViewById(R.id.pending);
        }
        return mPending;
    }

    public TextView getmProcessing() {

        if (mProcessing == null) {
            mProcessing = (TextView) view.findViewById(R.id.processing);
        }
        return mProcessing;
    }

    public TextView getmProcessed() {
        if (mProcessed == null) {
            mProcessed = (TextView) view.findViewById(R.id.processed);
        }
        return mProcessed;
    }

    public TextView getmComplain() {

        if (mComplain == null) {
            mComplain = (TextView) view.findViewById(R.id.complain);
        }

        return mComplain;
    }

    //服务


    public ConvenientBanner getLbt(){
        if(banner==null){
            banner= (ConvenientBanner) view.findViewById(R.id.lbt);
        }
        return banner;
    }
}
