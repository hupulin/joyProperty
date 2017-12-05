package com.joy.property.vehicle.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by Admin on 2015-04-08
 */
public class PositionSelectHolder {

    private View mView;
    private TextView positionName;
    private ImageView nextIcon;

    public PositionSelectHolder(View view) {
        this.mView = view;
    }

    public TextView getPositionName() {
        if (positionName == null) {
            positionName = (TextView) mView.findViewById(R.id.positionName);
        }
        return positionName;
    }

    public ImageView getNextIcon() {
        if (nextIcon == null) {
            nextIcon = (ImageView) mView.findViewById(R.id.nextIcon);
        }
        return nextIcon;
    }
}
