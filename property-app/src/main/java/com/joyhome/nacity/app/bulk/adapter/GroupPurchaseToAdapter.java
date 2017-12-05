package com.joyhome.nacity.app.bulk.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.newshop.ShopListDetailTo;

import com.joy.property.R;
import com.joy.property.shop.MerchantShopActivity;
import com.joy.property.shop.SelfShopActivity;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 2014-11-17
 */
public class GroupPurchaseToAdapter extends ModeListAdapter<ShopListDetailTo> {
    private  Activity activity;
    public GroupPurchaseToAdapter(Context context,Activity activity) {
        super(context);
        this.mContext = context;
        this.activity=activity;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        GroupPurchaseToCache holder = null;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.list_item_property_bulk, null);
            holder = new GroupPurchaseToCache(row);
            row.setTag(holder);
         } else {
            holder = (GroupPurchaseToCache) row.getTag();
         }

        ShopListDetailTo mode = mList.get(position);
        if (mode!=null) {
            holder.getPrimePrice().setText("¥" + mode.getRetailPrice());
            holder.getPrimePrice().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getPicUrl())).into(holder.getBulkImage());
            double nowPrice=Double.valueOf(mode.getCurrentPrice());
            holder.getNowPrice().setText((int)nowPrice+"");
            String nowPriceText;
            holder.getNowPrice().setText((int) nowPrice + "");
            nowPriceText=nowPrice+"0";
            nowPriceText=nowPriceText.contains(".")?nowPriceText.substring(nowPriceText.indexOf("."),nowPriceText.length()):".00";
            holder.getNowPriceRight().setText(nowPriceText);
            holder.getBulkAmount().setText(mode.getNum() + "");
         if ("团购".equals(mode.getIsGroup())){
             holder.getBulkIcon().setVisibility(View.VISIBLE);
             holder.getBulkName().setText("\u3000　"+ mode.getGoodsName());
         }else {
             holder.getBulkIcon().setVisibility(View.GONE);
             holder.getBulkName().setText( mode.getGoodsName());
         }
            holder.getSaleType().setOnClickListener(view1 -> jumpShop(mode.getMerchantId(),mode.getSalesType(),mode.getStoresName()));
             if (Double.valueOf(mode.getDiscountPercentage())<100){
                 holder.getDiscount().setVisibility(View.VISIBLE);
                 holder.getDiscount().setText((float)(Double.valueOf(mode.getDiscountPercentage())/10)+"折");
             } else {
                 holder.getDiscount().setVisibility(View.GONE);
             }
            long times=mode.getActivityEndTime().getTime()-System.currentTimeMillis();
            holder.getCountTime().setTimes(times);
            if (!holder.getCountTime().isRun())
                holder.getCountTime().run();
            holder.getCountTime().setOnStopCountTimeListener(() -> {
                if (listener != null)
                    listener.OnStopCountTime(position);
            });
            holder.getBulkAmount().setText(mode.getSalesNum() + "");
          //  holder.getDiscount().setText((float)(Double.valueOf(mode.getDiscountPercentage())/100*10)+"");
            holder.getSaleType().setText(mode.getStoresName());
            if (position==mList.size()-1)
                holder.getDivideLine().setVisibility(View.GONE);
            else
                holder.getDivideLine().setVisibility(View.VISIBLE);
       if ("快递配送".equals(mode.getDistributionMode()))
           holder.getPropertySend().setVisibility(View.GONE);
            else
           holder.getPropertySend().setVisibility(View.GONE);
            holder.getAddCar().setOnClickListener(view -> {
                if (addCarListener!=null)
                    addCarListener.OnAddCarClick(mode.getGoodsId(),"");
            });
        }


        return row;
    }
    private StopCountTime listener;
    public void setOnStopCountTimeListener(StopCountTime listener){
        this.listener=listener;
    }
    public interface StopCountTime{
        void OnStopCountTime(int position);
    }
    private AddCarListener addCarListener;
    public void setAddCarClickListener(AddCarListener listener){
        this.addCarListener=listener;
    }
    public interface AddCarListener{
        void OnAddCarClick(String shopSid, String name);
    }
    public void jumpShop(String shopSid,String name,String storeName){
        Intent intent;
        if ("自营商品".equals(name))
            intent=new Intent(mContext,SelfShopActivity.class);
        else
            intent=new Intent(mContext,MerchantShopActivity.class);
        intent.putExtra("ShopSid",shopSid);
        intent.putExtra("ShopName", storeName);
        mContext.startActivity(intent);

     activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
