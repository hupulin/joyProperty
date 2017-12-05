package com.joy.property.community.adapter;

import android.view.View;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by Admin on 2015-01-07
 */
public class HomeGuideToCache {


    private View view;
    private TextView mGuideTitle;
    private TextView mGuideAbstract;
    public HomeGuideToCache(View view) {
        this.view = view;
    }

    public TextView getmGuideTitle() {
        if (mGuideTitle == null) {
            mGuideTitle = (TextView) view.findViewById(R.id.guideTitle);
        }
        return mGuideTitle;
    }

    public TextView getmGuideAbstract() {
        if (mGuideAbstract == null) {
            mGuideAbstract = (TextView) view.findViewById(R.id.guideAbstract);
        }
        return mGuideAbstract;
    }
}
