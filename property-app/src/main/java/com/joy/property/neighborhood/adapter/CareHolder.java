package com.joy.property.neighborhood.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by xz on 2016/5/23.
 */
public class CareHolder {
    private View view;
    private TextView careStatus;
    private TextView careName;


    private ImageView headImage;
    private ImageView verified;
    private ImageView careIcon;
    private TextView joinPoint;
    public CareHolder(View view) {
        this.view = view;
    }



    public TextView getCareStatus(){
        if (careStatus==null)
            careStatus= (TextView) view.findViewById(R.id.joinTime);
        return careStatus;
    }
    public TextView getCareName(){
        if (careName==null)
            careName= (TextView) view.findViewById(R.id.joinName);
        return careName;
    }
    public TextView getJoinPoint(){
        if (joinPoint==null)
            joinPoint= (TextView) view.findViewById(R.id.joinPoint);
        return joinPoint;
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
    public ImageView getCareIcon(){
        if (careIcon==null)
            careIcon= (ImageView) view.findViewById(R.id.careIcon);
        return careIcon;
    }
}
