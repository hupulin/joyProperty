package com.joy.property.visit.adapter;

import android.view.View;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by usb on 2017/8/2.
 */

public class ExpressGridViewHolder {
    private View view;
    public ExpressGridViewHolder(View view) {
        this.view = view;
    }
    private TextView apartmentName;
    private   TextView expressName;
    private   TextView expressNo;
    public TextView getApartmentName() {
        if (apartmentName==null)
            apartmentName= (TextView) view.findViewById(R.id.apartment_name);
        return apartmentName;
    }
    public TextView getExpressName() {
        if (expressName==null)
            expressName= (TextView) view.findViewById(R.id.express_name);
        return expressName;
    }
    public TextView getExpressNo() {
        if (expressNo==null)
            expressNo= (TextView) view.findViewById(R.id.express_num);
        return expressNo;
    }

}
