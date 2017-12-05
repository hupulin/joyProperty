package com.joy.property.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jinyi.ihome.module.newshop.CarGoodsInfo;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by xz on 2016/7/12.
 **/
public class GoodsOlderListAdapter extends ModeListAdapter<CarGoodsInfo>{
    private Context mContext;
    public GoodsOlderListAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ShoppingCarHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.goods_list_item, null);
            holder = new ShoppingCarHolder(row);
            row.setTag(holder);
        } else {
            holder = (ShoppingCarHolder) row.getTag();
        }

        CarGoodsInfo mode=mList.get(position);
  if(mode!=null) {

        if (position==mList.size()-1)
            holder.getDivideLine().setVisibility(View.GONE);
        else
        holder.getDivideLine().setVisibility(View.VISIBLE);
        Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getPicUrl())).into(holder.getGoodsImage());
        holder.getGoodsName().setText(mode.getGoodsName());


      if (TextUtils.isEmpty(mode.getSpecificationsName())||"null".equals(mode.getSpecificationsName())||mode.getSpecificationsName().contains("null")){
          holder.getLabel().setVisibility(View.INVISIBLE);
      }else {
          holder.getLabel().setVisibility(View.VISIBLE);
      }
      holder.getGoodsNumber().setText(mode.getGoodsNum()+"");
        holder.getLabel().setText(mode.getSpecificationsName());
      holder.getPrice().setText("¥"+mode.getRetailPrice()+"");


            if (mode.getDistributionCost() > 0) {
                holder.getTrafficExpense().setText("配送费：¥ " + mode.getDistributionCost() + "");
            } else
                holder.getTrafficExpense().setText("免运费");
      holder.getCarNumber().setText(mode.getGoodsNum() + "");

        }





        return row;
    }

}
