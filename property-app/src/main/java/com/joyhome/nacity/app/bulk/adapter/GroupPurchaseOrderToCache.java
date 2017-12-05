package com.joyhome.nacity.app.bulk.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by Admin on 2014-11-18
 */
public class GroupPurchaseOrderToCache {

    private View view;
    private ImageView purchaseImage;
    private TextView TotalPrice;
    private TextView purchaseQty;
    private TextView purchaseTitle;
    private TextView OrderStatus;
    private TextView OrderDate;
    private Button btnCancel;
    private Button btnPay;
    public GroupPurchaseOrderToCache(View view) {
        this.view = view;
    }

    public ImageView getPurchaseImage() {
        if (purchaseImage == null) {
            purchaseImage = (ImageView) view.findViewById(R.id.bulk_image);
        }
        return purchaseImage;
    }

    public TextView getPurchaseQty() {
        if (purchaseQty == null) {
            purchaseQty = (TextView) view.findViewById(R.id.number);
        }
        return purchaseQty;
    }

    public TextView getTotalPrice() {
        if (TotalPrice == null) {
            TotalPrice = (TextView) view.findViewById(R.id.total_price);
        }
        return TotalPrice;
    }

    public TextView getPurchaseTitle() {
        if (purchaseTitle == null) {
            purchaseTitle = (TextView) view.findViewById(R.id.bulk_title);
        }
        return purchaseTitle;
    }

    public TextView getOrderStatus() {
        if (OrderStatus == null) {
            OrderStatus = (TextView) view.findViewById(R.id.status);
        }
        return OrderStatus;
    }

    public TextView getOrderDate() {
        if (OrderDate == null) {
            OrderDate = (TextView) view.findViewById(R.id.date);
        }
        return OrderDate;
    }

    public Button getBtnCancel() {
        if (btnCancel == null) {
            btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        }
        return btnCancel;
    }


    public Button getBtnPay() {
        if (btnPay == null) {
            btnPay = (Button) view.findViewById(R.id.btn_pay);
        }
        return btnPay;
    }
}
