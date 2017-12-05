package com.joy.property.neighborhood.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by xz on 2016/5/23.
 */
public class FansHolder {
    private View view;
    private TextView careStatus;
    private TextView fansName;


    private ImageView headImage;
    private ImageView verified;
    public FansHolder(View view) {
        this.view = view;
    }



    public TextView getCareStatus(){
        if (careStatus==null)
            careStatus= (TextView) view.findViewById(R.id.joinTime);
        return careStatus;
    }
    public TextView getFansName(){
        if (fansName==null)
            fansName= (TextView) view.findViewById(R.id.joinName);
        return fansName;
    }

    public ImageView getHeadImage(){
        if (headImage==null)
            headImage= (ImageView) view.findViewById(R.id.headImage);
        return headImage;
    }
    public ImageView getVerified(){
        if (verified==null)
            verified= (ImageView) view.findViewById(R.id.verified);
        return verified;
    }
}
