package com.joy.property.message.adpater;

import android.view.View;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by Admin on 2015-01-26.
 */
public class HomeMessageCache {
    private View view;
    private TextView mTime;
    private TextView mContent;
    public HomeMessageCache(View view) {
        this.view = view;
    }

    public TextView getmTime() {
        if (mTime == null) {
            mTime = (TextView) view.findViewById(R.id.time);
        }
        return mTime;
    }

    public TextView getmContent() {
        if (mContent== null) {
           mContent = (TextView) view.findViewById(R.id.message_content);
        }
        return mContent;
    }
}
