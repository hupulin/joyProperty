package com.joy.property.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by Admin on 2015-08-10
 */
public class GroupMenuCache {

    private View view ;
    private ImageView mImageView;
    private TextView mTextName;
    private RelativeLayout layout;
    private RelativeLayout relativeLayout;
    public GroupMenuCache(View view) {

        this.view = view;
    }

    public ImageView getmImageView() {
        if (mImageView == null) {
            mImageView  = (ImageView) view.findViewById(R.id.image);
        }
        return mImageView;
    }

    public TextView getmTextName() {
        if (mTextName == null) {
            mTextName = (TextView) view.findViewById(R.id.text_name);
        }
        return mTextName;
    }

    public RelativeLayout getLayout() {
        if (layout == null) {
            layout = (RelativeLayout) view.findViewById(R.id.layout);
        }
        return layout;
    }

    public RelativeLayout getRelativeLayout() {
        if (relativeLayout == null) {
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        }
        return relativeLayout;
    }
}
