package com.joy.property.myservice.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by Admin on 2015-09-29
 */
public class ApplyServiceCache {


    private View  view;
    private ImageView mServiceIcon;
    private TextView  mServiceName;
    private TextView  mServiceDesc;

    public ApplyServiceCache(View view) {
        this.view = view;
    }

    public ImageView getmServiceIcon() {
        if (mServiceIcon == null) {

            mServiceIcon = (ImageView) view.findViewById(R.id.service_icon);
        }
        return mServiceIcon;
    }

    public TextView getmServiceName() {
        if (mServiceName == null) {

            mServiceName = (TextView) view.findViewById(R.id.service_name);
        }
        return mServiceName;
    }

    public TextView getmServiceDesc() {
        if (mServiceDesc == null) {
            mServiceDesc = (TextView) view.findViewById(R.id.service_desc);
        }
        return mServiceDesc;
    }
}
