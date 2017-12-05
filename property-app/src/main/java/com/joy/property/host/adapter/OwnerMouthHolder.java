package com.joy.property.host.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.joy.property.R;

/**
 * Created by Admin on 2015-02-09
 */
public class OwnerMouthHolder {


    private View view;
    private TextView apartmentName;
    private TextView serviceAttitude;
    private TextView solveSpeed;
    private TextView satisfaction;
    private ConvenientBanner autoRow;
   private ImageView image;
    public OwnerMouthHolder(View view) {
        this.view = view;
    }

  public TextView getApartmentName(){
      if (apartmentName==null)
          apartmentName= (TextView) view.findViewById(R.id.apartmentName);
      return apartmentName;
  }
    public TextView getServiceAttitude(){
        if (serviceAttitude==null)
            serviceAttitude= (TextView) view.findViewById(R.id.serviceAttitude);
        return serviceAttitude;
    }
    public TextView getSolveSpeed(){
        if (solveSpeed==null)
            solveSpeed= (TextView) view.findViewById(R.id.solveSpeed);
        return solveSpeed;
    }
    public TextView getSatisfaction(){
        if (satisfaction==null)
            satisfaction= (TextView) view.findViewById(R.id.satisfaction);
        return satisfaction;
    }
    public ConvenientBanner getAutoRow(){
        if (autoRow==null)
            autoRow= (ConvenientBanner) view.findViewById(R.id.autoRow);
        return autoRow;
    }
public ImageView getImage(){
    if (image==null)
        image= (ImageView) view.findViewById(R.id.image);
    return image;
}
}
