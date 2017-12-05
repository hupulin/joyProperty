package com.joy.property.repairs.adapter;

import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.joy.property.R;

/**
 * Created by Admin on 2015-02-10
 */
public class RepairsManagementCache {

    private View view ;
    private TextView  mPending;
    private TextView  mProcessing;
    private TextView  mProcessed;
    private TextView  mApartment;
    private TextView  mRepair;
    private TextView mGood;
    private TextView mSOSO;
    private TextView mBad;
    private PieChart mPie;
    public RepairsManagementCache(View view) {
        this.view = view;
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
        if (mProcessed== null) {
            mProcessed = (TextView) view.findViewById(R.id.processed);
        }
        return mProcessed;
    }

    public TextView getmApartment() {
        if (mApartment == null) {
            mApartment = (TextView) view.findViewById(R.id.apartment);
        }
        return mApartment;
    }

    public TextView getmRepair() {
        if (mRepair == null) {
            mRepair = (TextView) view.findViewById(R.id.repair);
        }
        return mRepair;
    }



    public TextView getmGood() {
        if (mGood == null) {
            mGood = (TextView) view.findViewById(R.id.good);
        }
        return mGood;
    }

    public TextView getmSOSO() {
        if (mSOSO == null) {
            mSOSO = (TextView) view.findViewById(R.id.so);
        }
        return mSOSO;
    }

    public TextView getmBad() {
        if (mBad == null) {
            mBad = (TextView) view.findViewById(R.id.bad);
        }
        return mBad;
    }
    public PieChart getPie() {
        if (mPie == null) {
            mPie = (PieChart) view.findViewById(R.id.spread_pie_chart);
        }
        return mPie;
    }
}
