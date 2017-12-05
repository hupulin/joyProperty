package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by xz on 2016/7/24.
 */
public class MyAddressHolder {
    private View view;
    private TextView addressName;
    private TextView addressPhone;
    private TextView addressDetail;
    private TextView idCardNum;
    private TextView addressDelete;
    private TextView addressEdit;
    private RelativeLayout changeDefaultAddress;
    private RelativeLayout idCardLayout;
    private TextView setDefault;
    private ImageView isSelect;

    public RelativeLayout getChangeDefaultAddress() {
        if(changeDefaultAddress==null)
            changeDefaultAddress= (RelativeLayout) view.findViewById(R.id.change_default_address);
        return changeDefaultAddress;
    }
    public RelativeLayout getidCardLayout() {
        if(idCardLayout==null)
            idCardLayout= (RelativeLayout) view.findViewById(R.id.idCard_layout);
        return idCardLayout;
    }
    public TextView getIdCardNum() {
        if(changeDefaultAddress==null)
            idCardNum= (TextView) view.findViewById(R.id.idCardNum);
        return idCardNum;
    }

    public ImageView getIsSelect() {
        if(isSelect==null)
            isSelect= (ImageView) view.findViewById(R.id.is_select);
        return isSelect;
    }

    public TextView getSetDefault() {
        if(setDefault==null)
            setDefault= (TextView) view.findViewById(R.id.tv_set);
        return setDefault;
    }




    public MyAddressHolder(View view) {
        this.view = view;
    }
    public TextView getAddressName(){
        if(addressName==null)
            addressName= (TextView) view.findViewById(R.id.addressName);
        return addressName;
    }
    public TextView getAddressPhone(){
        if(addressPhone==null)
            addressPhone= (TextView) view.findViewById(R.id.addressPhone);
        return addressPhone;
    }
    public TextView getAddressDetail(){
        if(addressDetail==null)
            addressDetail= (TextView) view.findViewById(R.id.addressDetail);
        return addressDetail;
    }
    public TextView getAddressDelete(){
        if (addressDelete==null)
            addressDelete= (TextView) view.findViewById(R.id.addressDelete);
        return addressDelete;
    }
    public TextView getAddressEdit(){
        if (addressEdit==null)
            addressEdit= (TextView) view.findViewById(R.id.addressEdit);
        return addressEdit;
    }
}
