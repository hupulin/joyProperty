package com.joy.property.task.adapter;

import android.view.View;
import android.widget.TextView;

import com.joy.property.R;
import com.joy.library.utils.HeadImageView;

/**
 * Created by Admin on 2015-01-27.
 */
public class ForwardCenterCache {

    private View view;
    private HeadImageView mHeadImage;
    private TextView       mTypeName;
    private TextView       mPhone;
    public ForwardCenterCache(View view) {
        this.view = view;
    }

    public HeadImageView getmHeadImage() {
        if (mHeadImage == null) {
          mHeadImage = (HeadImageView) view.findViewById(R.id.head_image);
        }
        return mHeadImage;
    }

    public TextView getmTypeName() {
        if (mTypeName == null) {
            mTypeName = (TextView) view.findViewById(R.id.typeName);
        }
        return mTypeName;
    }

    public TextView getmPhone() {
        if (mPhone == null) {
            mPhone = (TextView) view.findViewById(R.id.phone);
        }
        return mPhone;
    }
}
