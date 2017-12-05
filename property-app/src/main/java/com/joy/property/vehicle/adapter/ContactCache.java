package com.joy.property.vehicle.adapter;

import android.view.View;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by Admin on 2015-04-08
 */
public class ContactCache {

    private View mView;
    private TextView mName;
    private TextView mPhone;

    public ContactCache(View view) {
        this.mView = view;
    }

    public TextView getmName() {
        if (mName == null) {
            mName = (TextView) mView.findViewById(R.id.name);
        }
        return mName;
    }

    public TextView getmPhone() {
        if (mPhone == null) {
            mPhone = (TextView) mView.findViewById(R.id.phone);
        }
        return mPhone;
    }
}
