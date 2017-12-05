package com.joy.property.visit.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;
import com.lidong.photopicker.Image;

/**
 * Created by usb on 2017/6/6.
 */

public class ExpressHolder {
    private View view;
    private TextView apartmentName;
    private TextView expressName;
    private TextView expressNo;
    private TextView phoneNo;
    private TextView name;
    private TextView receiveTime;


    private TextView inputTime;
    private ImageView expressImage;

    public TextView getExpressName() {
        if (expressName==null)
            expressName= (TextView) view.findViewById(R.id.express_name);
        return expressName;
    }
public TextView getName() {
        if (name==null)
            name= (TextView) view.findViewById(R.id.name);
        return name;
    }

    public ImageView getExpressImage() {
        if (expressImage==null)
            expressImage= (ImageView) view.findViewById(R.id.express_icon);
        return expressImage;
    }

    public TextView getReceiveTime() {
        if (receiveTime==null)
            receiveTime= (TextView) view.findViewById(R.id.receive_time);
        return receiveTime;
    }public TextView getInputTime() {
        if (inputTime==null)
            inputTime= (TextView) view.findViewById(R.id.inputTime);
        return inputTime;
    }

    public ExpressHolder(View view) {
        this.view = view;
    }
    public TextView getPhoneNo() {
        if (phoneNo==null)
            phoneNo= (TextView) view.findViewById(R.id.phoneNo);
        return phoneNo;
    }


    public TextView getExpressNo() {
        if (expressNo==null)
            expressNo= (TextView) view.findViewById(R.id.expressNo);
        return expressNo;
    }

    public TextView getApartmentName() {
         if (apartmentName==null)
            apartmentName= (TextView) view.findViewById(R.id.apartment_name);
        return apartmentName;
    }



}
