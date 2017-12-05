package com.joyhome.nacity.app.bulk.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.property.R;
import com.joy.property.shop.shoputil.TimeTextViewUtil;


/**
 * Created by Admin on 2014-11-17
 */
public class GroupPurchaseToCache {
    private View view;
    private TextView primePrice;
    private TextView nowPrice;
    private TextView bulkAmount;
    private TextView nowPriceRight;
    private ImageView bulkImage;
    private ImageView bulkIcon;
    private TextView bulkName;
    private TextView   discount;
    private TextView   saleType;
    private TimeTextViewUtil countTime;
    private LinearLayout divideLine;
    private ImageView propertySend;
    private ImageView addCar;
    public GroupPurchaseToCache(View view) {
        this.view = view;
    }

public  TextView getPrimePrice(){
    if (primePrice==null)
        primePrice= (TextView) view.findViewById(R.id.original_price);
    return primePrice;
}
    public  TextView getSaleType(){
        if (saleType==null)
            saleType= (TextView) view.findViewById(R.id.saleType);
        return saleType;
    }
    public  TextView getNowPrice(){
        if (nowPrice==null)
            nowPrice= (TextView) view.findViewById(R.id.now_price);
        return nowPrice;
    }
    public  TextView getNowPriceRight(){
        if (nowPriceRight==null)
            nowPriceRight= (TextView) view.findViewById(R.id.now_price_right);
        return nowPriceRight;
    }
    public  TextView getBulkAmount(){
        if (bulkAmount==null)
            bulkAmount= (TextView) view.findViewById(R.id.bulk_amount);
        return bulkAmount;
    }

    public  TextView getBulkName(){
        if (bulkName==null)
            bulkName= (TextView) view.findViewById(R.id.bulk_title);
        return bulkName;
    }
    public  TextView getDiscount(){
        if (discount==null)
            discount= (TextView) view.findViewById(R.id.discount);
        return discount;
    }
    public ImageView getBulkImage(){
        if (bulkImage==null)
            bulkImage= (ImageView) view.findViewById(R.id.bulk_image);
            return bulkImage;

        }
    public ImageView getPropertySend(){
        if (propertySend==null)
            propertySend= (ImageView) view.findViewById(R.id.propertySend);
        return propertySend;

    }
    public ImageView getBulkIcon(){
        if (bulkIcon==null)
            bulkIcon= (ImageView) view.findViewById(R.id.bulk_icon);
        return bulkIcon;

    }
public TimeTextViewUtil getCountTime(){
    if (countTime==null)
        countTime= (TimeTextViewUtil) view.findViewById(R.id.countTime);
return countTime;
}
    public LinearLayout getDivideLine(){
        if (divideLine==null)
            divideLine= (LinearLayout) view.findViewById(R.id.divideLine);
        return divideLine;
    }
    public ImageView getAddCar(){
        if (addCar==null)
            addCar= (ImageView) view.findViewById(R.id.add_car);
        return addCar;

    }
}
