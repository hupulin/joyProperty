package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by xz on 2016/7/21.
 **/
public class ShoppingCarHolder {
    private View view;
    public ImageView goodsSelect;
    public ImageView canUse;
    public ImageView goodsImage;
    public TextView goodsName;
    public TextView trafficExpense;
    public TextView primePrice;
    public TextView price;
    public TextView tvedit;
    public TextView discount;
    public TextView goodsNumber;
    public TextView storeName;
    public ImageView shopSelect;
    //    public ImageView carReduce;
//    public ImageView carAdd;
    public TextView carNumber;
    public TextView coupons;
    public RelativeLayout shopInfo;
    public RelativeLayout goodInfo;
    public TextView selectStoreLayout;
    public RelativeLayout getCouponLayout;
    public RelativeLayout selectGoodsLayout;
    public View divideLine;
    public View divideLine01;
    public RelativeLayout disableClick;
    public TextView editSelectSpecification;
    public TextView label;
    public ImageView purchaseAdd;
    public ImageView storeNameArrow;
    public View activityLine;
    public ImageView purchaseReduce;
    public TextView purchaseNumber;
    public RelativeLayout noEditLayout;
    public RelativeLayout editSpecification;
    public RelativeLayout bulkLayout;
    public TextView goodsPrice;
    public TextView containTraffic;
    public TextView goodsCount;
    public RelativeLayout balanceLayout;

    public ShoppingCarHolder(View view) {
        this.view = view;
    }
    public ImageView getGoodsSelect(){
        if(goodsSelect==null)
            goodsSelect= (ImageView) view.findViewById(R.id.goodsSelect);
        return goodsSelect;
    }
    public ImageView getCanUse(){
        if(canUse==null)
            canUse= (ImageView) view.findViewById(R.id.canUse);
        return canUse;
    }
    public ImageView getGoodsImage(){
        if(goodsImage==null)
            goodsImage= (ImageView) view.findViewById(R.id.goodsImage);
        return goodsImage;
    }

    public TextView getTrafficExpense(){
        if(trafficExpense==null)
            trafficExpense= (TextView) view.findViewById(R.id.trafficExpense);
        return trafficExpense;
    }

    public TextView getPrice(){
        if(price==null)
            price= (TextView) view.findViewById(R.id.price);
        return price;
    }
    public TextView getDiscount(){
        if(discount==null)
            discount= (TextView) view.findViewById(R.id.discount);
        return discount;
    }
    public TextView getGoodsNumber(){
        if(goodsNumber==null)
            goodsNumber= (TextView) view.findViewById(R.id.goodsNumber);
        return goodsNumber;
    }
    public TextView getGoodsName(){
        if(goodsName==null)
            goodsName= (TextView) view.findViewById(R.id.goodsName);
        return goodsName;
    }
    public TextView getEdit(){
        if(tvedit==null)
            tvedit= (TextView) view.findViewById(R.id.tv_edit);
        return tvedit;
    }

    public RelativeLayout getRl(){
        if(shopInfo==null)
            shopInfo= (RelativeLayout) view.findViewById(R.id.shopInfo);
        return shopInfo;
    }

    public RelativeLayout getGoodInfo(){
        if(goodInfo==null)
            goodInfo= (RelativeLayout) view.findViewById(R.id.goodInfo);
        return goodInfo;
    }
    public RelativeLayout getBulkLayout(){
        if(bulkLayout==null)
            bulkLayout= (RelativeLayout) view.findViewById(R.id.bulkLayout);
        return bulkLayout;
    }
    public TextView getStoreName(){
        if(storeName==null)
            storeName= (TextView) view.findViewById(R.id.storeName);
        return storeName;
    }
    public TextView getCoupons(){
        if(coupons==null)
            coupons= (TextView) view.findViewById(R.id.coupons);
        return coupons;
    }
    public ImageView getShopSelect(){
        if(shopSelect==null)
            shopSelect= (ImageView) view.findViewById(R.id.shopSelect);
        return shopSelect;
    }
    public TextView getCarNumber(){
        if(carNumber==null)
            carNumber= (TextView) view.findViewById(R.id.carNumber);
        return carNumber;
    }
    //    public ImageView getCarReduce(){
//        if(carReduce==null)
//            carReduce= (ImageView) view.findViewById(R.id.carReduce);
//        return carReduce;
//    }
//    public ImageView getCarAdd(){
//        if(carAdd==null)
//            carAdd= (ImageView) view.findViewById(R.id.carAdd);
//        return carAdd;
//    }
    public View getDivideLine(){
        if (divideLine==null)
            divideLine=view.findViewById(R.id.divideLine);
        return divideLine;

    }
    public View getDivideLine01(){
        if (divideLine01==null)
            divideLine01=view.findViewById(R.id.divideLine_01);
        return divideLine01;

    }
    public TextView getSelectStoreLayout(){
        if (selectStoreLayout==null)
            selectStoreLayout= (TextView) view.findViewById(R.id.selectStoreLayout);
        return selectStoreLayout;
    }
    public RelativeLayout getGetCouponLayout(){
        if (getCouponLayout==null)
            getCouponLayout= (RelativeLayout) view.findViewById(R.id.getCouponLayout);
        return getCouponLayout;
    }
    public RelativeLayout getSelectGoodsLayout(){
        if (selectGoodsLayout==null)
            selectGoodsLayout= (RelativeLayout) view.findViewById(R.id.selectGoodsLayout);
        return selectGoodsLayout;
    }


    public TextView getPurchaseNumber(){
        if(purchaseNumber==null)
            purchaseNumber= (TextView) view.findViewById(R.id.purchaseNumber);
        return purchaseNumber;
    }
    public TextView getEditSelectSpecification(){
        if(editSelectSpecification==null)
            editSelectSpecification= (TextView) view.findViewById(R.id.editSelectSpecification);
        return editSelectSpecification;
    }
    public ImageView getPurchaseAdd(){
        if(purchaseAdd==null)
            purchaseAdd= (ImageView) view.findViewById(R.id.purchaseAdd);
        return purchaseAdd;
    }
    public ImageView getPurchaseReduce(){
        if(purchaseReduce==null)
            purchaseReduce= (ImageView) view.findViewById(R.id.purchaseReduce);
        return purchaseReduce;
    }
    public TextView getTvedit(){
        if(tvedit==null)
            tvedit= (TextView) view.findViewById(R.id.tv_edit);
        return tvedit;
    }
    public RelativeLayout getNoEditLayout(){
        if (noEditLayout==null)
            noEditLayout= (RelativeLayout) view.findViewById(R.id.noEditLayout);
        return noEditLayout;
    }
    public RelativeLayout getEditSpecification(){
        if (editSpecification==null)
            editSpecification= (RelativeLayout) view.findViewById(R.id.editSpecification);
        return editSpecification;
    }

    public ImageView getStoreNameArrow(){
        if(storeNameArrow==null)
            storeNameArrow= (ImageView) view.findViewById(R.id.storeNameArrow);
        return storeNameArrow;
    }

    public View getActivityLine(){
        if (activityLine==null)
            activityLine=view.findViewById(R.id.activityLine);
        return activityLine;
    }
    public TextView getLabel(){
        if(label==null)
            label= (TextView) view.findViewById(R.id.label);
        return label;
    }
    public TextView getGoodsPrice(){
        if (goodsPrice==null)
            goodsPrice=(TextView)view.findViewById(R.id.goodsPrice);
        return goodsPrice;
    }

    public TextView getContainTraffic(){
        if (containTraffic==null)
            containTraffic=(TextView)view.findViewById(R.id.containTraffic);
        return containTraffic;
    }
    public TextView getGoodsCount(){
        if (goodsCount==null)
            goodsCount=(TextView)view.findViewById(R.id.goodsCount);
        return goodsCount;
    }
    public RelativeLayout getBalanceLayout(){
        if (balanceLayout==null)
            balanceLayout= (RelativeLayout) view.findViewById(R.id.balanceLayout);
        return balanceLayout;
    }
}
