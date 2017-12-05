package com.joy.property.task.adapter;

import android.view.View;
import android.widget.TextView;

import com.joy.library.utils.HeadImageView;
import com.joy.property.R;

/**
 * Created by usb on 2016/8/18.
 */
public class CaredCenterCache {
    private View view;
    private HeadImageView mHeadImage;
    private TextView mTypeName;
    private TextView       mPhone;
    public CaredCenterCache(View view) {
        this.view = view;
    }

    public HeadImageView getmHeadImage() {
        if (mHeadImage == null) {
            mHeadImage = (HeadImageView) view.findViewById(R.id.icon);
        }
        return mHeadImage;
    }

    public TextView getmTypeName() {
        if (mTypeName == null) {
            mTypeName = (TextView) view.findViewById(R.id.title);
        }
        return mTypeName;
    }

    public TextView getmPhone() {
        if (mPhone == null) {
            mPhone = (TextView) view.findViewById(R.id.msg);
        }
        return mPhone;
    }
}
