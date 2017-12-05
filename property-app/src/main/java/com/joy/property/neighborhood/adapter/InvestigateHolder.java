package com.joy.property.neighborhood.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by xz on 2016/5/23.
 */
public class InvestigateHolder {
    private View view;
    private TextView joinTime;
    private TextView joinName;



    private ImageView headImage;
    public InvestigateHolder(View view) {
        this.view = view;
    }



    public TextView getJoinTime(){
        if (joinTime==null)
            joinTime= (TextView) view.findViewById(R.id.joinTime);
        return joinTime;
    }
    public TextView getJoinName(){
        if (joinName==null)
            joinName= (TextView) view.findViewById(R.id.joinName);
        return joinName;
    }
    public ImageView getHeadImage(){
        if (headImage==null)
            headImage= (ImageView) view.findViewById(R.id.headImage);
        return headImage;
    }
}
