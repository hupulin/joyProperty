package com.joy.property.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jinyi.ihome.module.newshop.CategoryChannelTo;
import com.jinyi.ihome.module.shop.GoodsSpecificationsTo;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

import java.util.List;

/**
 * Created by xz on 2016/7/12.
 */
public class HotAdapter extends ModeListAdapter<CategoryChannelTo> {
    private Context mContext;
    private String priceRight,priceRightOne,priceRightTow,priceRightThree,priceRightFour;
    public HotAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HotHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.sort_list_item, null);
            holder = new HotHolder(row);
            row.setTag(holder);
        } else {
            holder = (HotHolder) row.getTag();
        }

        CategoryChannelTo mode=mList.get(position);
        if(mode!=null){
            holder.getChannelName().setText(mode.getCategoryName());
            Glide.with(MainApplication.mContext).load(MainApp.getPicassoImagePath(mode.getHomePagePic())).error(R.drawable.activity_load_bg).into(holder.getChannelmage());
            if (mode.getGoodsApiVolist().size()<3)
                holder.getGoodsListLayout().setVisibility(View.GONE);
            else
            holder.getGoodsListLayout().setVisibility(View.VISIBLE);
            if(mode.getGoodsApiVolist()!=null&&mode.getGoodsApiVolist().size()>0){
                holder.getHotImage().setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder.getGoodsName().setText(mode.getGoodsApiVolist().get(0).getGoodsName());
                Glide.with(MainApplication.mContext).load(MainApp.getPicassoImagePath(mode.getGoodsApiVolist().get(0).getPicUrl())).error(R.drawable.category_goods_load).into(holder.getHotImage());
                holder.getPrimePrice().setText("¥" + getMinSpecification(mode.getGoodsApiVolist().get(0).getGoodsSpecificationsList()).getRetailPrice() + "");
                holder.getPrimePrice().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.getHotPrice().setText((int) getMinSpecification(mode.getGoodsApiVolist().get(0).getGoodsSpecificationsList()).getRetailPrice() + "");
                holder.getSellingLayout().setOnClickListener(v -> Toast.makeText(mContext, mode.getGoodsApiVolist().get(0).getGoodsId() + "", Toast.LENGTH_LONG).show());
                priceRight=getMinSpecification(mode.getGoodsApiVolist().get(0).getGoodsSpecificationsList()).getRetailPrice()+"0";
                priceRight=priceRight.contains(".")?priceRight.substring(priceRight.indexOf("."),priceRight.length()):".00";

              if (mode.getGoodsApiVolist().get(0).getGoodsLableList()!=null&&mode.getGoodsApiVolist().get(0).getGoodsLableList().size()>0)
              {

                  holder.getChannelLabel1().setVisibility(View.VISIBLE);
                  holder.getChannelLabel1().setText(mode.getGoodsApiVolist().get(0).getGoodsLableList().get(0).getLableName());

              }else {
                  holder.getChannelLabel1().setVisibility(View.GONE);
              }

                if ("团购".equals(mode.getGoodsApiVolist().get(0).getIsGroup()))
                    holder.getPrimePrice().setVisibility(View.VISIBLE);
                else
                    holder.getPrimePrice().setVisibility(View.GONE);
                holder.getHotPriceRight().setText(priceRight.substring(priceRight.indexOf(".")));
                holder.getSellingLayout().setOnClickListener(view -> {
                    if (listener != null)
                        listener.jumpActivity(mode.getGoodsApiVolist().get(0).getGoodsId());
                });

                if (mode.getGoodsApiVolist().size()>1) {
                    holder.getGoodsNameOne().setText(mode.getGoodsApiVolist().get(1).getGoodsName());
                    holder.getHotImageOne().setBackgroundColor(Color.parseColor("#FFFFFF"));
                    Glide.with(MainApplication.mContext).load(MainApp.getPicassoImagePath(mode.getGoodsApiVolist().get(1).getPicUrl())).error(R.drawable.category_goods_load).into(holder.getHotImageOne());
                    holder.getPrimePriceOne().setText("¥ " + getMinSpecification(mode.getGoodsApiVolist().get(1).getGoodsSpecificationsList()).getRetailPrice() + "");
                    priceRightOne=getMinSpecification(mode.getGoodsApiVolist().get(1).getGoodsSpecificationsList()).getRetailPrice()+"0";
                    priceRightOne=priceRightOne.contains(".")?priceRightOne.substring(priceRightOne.indexOf("."),priceRightOne.length()):".00";
                    holder.getHotPriceOneRight().setText(priceRightOne.substring(priceRightOne.indexOf(".")));
                    holder.getPrimePriceOne().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.getHotPriceOne().setText((int) getMinSpecification(mode.getGoodsApiVolist().get(1).getGoodsSpecificationsList()).getRetailPrice() + "");
                    holder.getSellingLayoutOne().setOnClickListener(v -> Toast.makeText(mContext, mode.getGoodsApiVolist().get(1).getGoodsId() + "", Toast.LENGTH_LONG).show());

                    if (mode.getGoodsApiVolist().get(1).getGoodsLableList()!=null&&mode.getGoodsApiVolist().get(1).getGoodsLableList().size()>0)
                    {
                        holder.getChannelLabel2().setVisibility(View.VISIBLE);
                        holder.getChannelLabel2().setText(mode.getGoodsApiVolist().get(1).getGoodsLableList().get(0).getLableName());
                    }else {
                        holder.getChannelLabel2().setVisibility(View.GONE);
                    }

                    if ("团购".equals(mode.getGoodsApiVolist().get(1).getIsGroup()))
                        holder.getPrimePriceOne().setVisibility(View.VISIBLE);
                    else
                        holder.getPrimePriceOne().setVisibility(View.GONE);
                    holder.getSellingLayoutOne().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (listener != null)
                                listener.jumpActivity(mode.getGoodsApiVolist().get(1).getGoodsId());
                        }
                    });

                }else {
                    Glide.with(MainApplication.mContext).load(R.drawable.channel_small_load).into(holder.getHotImageOne());
                    //  holder.getHotImageOne().setScaleType(ImageView.ScaleType.FIT_XY);
                    holder.getGoodsNameOne().setText("敬请期待");
                    holder.getHotPriceOne().setText("0.");
                    holder.getHotPriceOneRight().setText("00");
                    holder.getPrimePriceOne().setText("¥0.00");
                    holder.getPrimePriceOne().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.getSellingLayoutOne().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }

                if (mode.getGoodsApiVolist().size()>2) {
                    holder.getHotImageTow().setBackgroundColor(Color.parseColor("#FFFFFF"));
                    holder.getGoodsNameTow().setText(mode.getGoodsApiVolist().get(2).getGoodsName());
                    Glide.with(MainApplication.mContext).load(MainApp.getPicassoImagePath(mode.getGoodsApiVolist().get(2).getPicUrl())).error(R.drawable.category_goods_load).into(holder.getHotImageTow());
                    holder.getPrimePriceTwo().setText("¥ " + getMinSpecification(mode.getGoodsApiVolist().get(2).getGoodsSpecificationsList()).getRetailPrice() + "");
                    priceRightTow=getMinSpecification(mode.getGoodsApiVolist().get(2).getGoodsSpecificationsList()).getRetailPrice()+"0";
                    priceRightTow=priceRightTow.contains(".")?priceRightTow.substring(priceRightTow.indexOf("."),priceRightTow.length()):".00";
                    holder.getHotPriceTwoRight().setText(priceRightTow.substring(priceRightTow.indexOf(".")));
                    holder.getPrimePriceTwo().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.getHotPriceTwo().setText((int)getMinSpecification(mode.getGoodsApiVolist().get(2).getGoodsSpecificationsList()).getRetailPrice() + "");

                    if (mode.getGoodsApiVolist().get(2).getGoodsLableList()!=null&&mode.getGoodsApiVolist().get(2).getGoodsLableList().size()>0)
                    {
                        holder.getChannelLabel3().setVisibility(View.VISIBLE);
                        holder.getChannelLabel3().setText(mode.getGoodsApiVolist().get(2).getGoodsLableList().get(0).getLableName());
                    }else {
                        holder.getChannelLabel3().setVisibility(View.GONE);
                    }

                    if ("团购".equals(mode.getGoodsApiVolist().get(2).getIsGroup()))
                        holder.getPrimePriceTwo().setVisibility(View.VISIBLE);
                    else
                        holder.getPrimePriceTwo().setVisibility(View.GONE);
                    holder.getSellingLayoutTwo().setOnClickListener(view -> {
                        if (listener != null)
                            listener.jumpActivity(mode.getGoodsApiVolist().get(2).getGoodsId());
                    });

                }else {
                    Glide.with(MainApplication.mContext).load(R.drawable.channel_small_load).into(holder.getHotImageTow());
                    //  holder.getHotImageTow().setScaleType(ImageView.ScaleType.FIT_XY);
                    holder.getGoodsNameTow().setText("敬请期待");
                    holder.getHotPriceTwo().setText("0.");
                    holder.getHotPriceTwoRight().setText("00");
                    holder.getPrimePriceTwo().setText("¥0.00");
                    holder.getPrimePriceTwo().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.getSellingLayoutTwo().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }
//        if (mode.getGoodsApiVolist().size()>3) {
//            holder.getHotImageThree().setBackgroundColor(Color.parseColor("#FFFFFF"));
//            holder.getGoodsNameThree().setText(mode.getGoodsApiVolist().get(3).getGoodsName());
//            Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getGoodsApiVolist().get(3).getPicUrl())).into(holder.getHotImageThree());
//            holder.getPrimePriceThree().setText("¥ " + mode.getGoodsApiVolist().get(3).getRetailPrice() + "");
//            priceRightThree = mode.getGoodsApiVolist().get(3).getCurrentPrice() - (int) mode.getGoodsApiVolist().get(3).getCurrentPrice() + "0";
//
//            holder.getHotPriceThreeRight().setText(priceRightThree.substring(priceRightThree.indexOf(".")));
//                                System.out.println(priceRightThree.substring(priceRightThree.indexOf("."))+"----");
//            holder.getPrimePriceThree().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.getHotPriceThree().setText((int) mode.getGoodsApiVolist().get(3).getCurrentPrice() + "");
//            if ("团购".equals(mode.getGoodsApiVolist().get(3).getIsGroup()))
//                holder.getPrimePriceThree().setVisibility(View.VISIBLE);
//            else
//                holder.getPrimePriceThree().setVisibility(View.GONE);
//            holder.getSellingLayoutThree().setOnClickListener(view -> {
//                if (listener != null)
//                    listener.jumpActivity(mode.getGoodsApiVolist().get(3).getGoodsId());
//            });
//        }else {
//            Picasso.with(mContext).load(R.drawable.channel_small_load).into(holder.getHotImageThree());
//          //  holder.getHotImageThree().setScaleType(ImageView.ScaleType.FIT_XY);
//            holder.getGoodsNameThree().setText("敬请期待");
//            holder.getHotPriceThree().setText("0.");
//            holder.getHotPriceThreeRight().setText("00");
//            holder.getPrimePriceThree().setText("¥0.00");
//            holder.getPrimePriceThree().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.getSellingLayoutThree().setOnClickListener(view -> {
//
//            });
//
//        }
//        if (mode.getGoodsApiVolist().size()>4) {
//            holder.getHotImageFour().setBackgroundColor(Color.parseColor("#FFFFFF"));
//            holder.getGoodsNameFour().setText(mode.getGoodsApiVolist().get(4).getGoodsName());
//            Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getGoodsApiVolist().get(4).getPicUrl())).into(holder.getHotImageFour());
//            holder.getPrimePriceFour().setText("¥ " + mode.getGoodsApiVolist().get(4).getRetailPrice() + "");
//            priceRightFour = mode.getGoodsApiVolist().get(4).getCurrentPrice() - (int) mode.getGoodsApiVolist().get(4).getCurrentPrice() + "0";
//            holder.getHotPriceFourRight().setText(priceRightFour.substring(priceRightFour.indexOf(".")));
//            holder.getPrimePriceFour().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.getHotPriceFour().setText((int) mode.getGoodsApiVolist().get(4).getCurrentPrice() + "");
//            if ("团购".equals(mode.getGoodsApiVolist().get(4).getIsGroup()))
//                holder.getPrimePriceFour().setVisibility(View.VISIBLE);
//            else
//                holder.getPrimePriceFour().setVisibility(View.GONE);
//            holder.getSellingLayoutFour().setOnClickListener(view -> {
//                if (listener != null)
//                    listener.jumpActivity(mode.getGoodsApiVolist().get(4).getGoodsId());
//            });
//        }else {
//            Picasso.with(mContext).load(R.drawable.channel_small_load).into(holder.getHotImageFour());
//         //   holder.getHotImageFour().setScaleType(ImageView.ScaleType.FIT_XY);
//            holder.getGoodsNameFour().setText("敬请期待");
//            holder.getHotPriceFour().setText("0.");
//            holder.getHotPriceFourRight().setText("00");
//            holder.getPrimePriceFour().setText("¥0.00");
//            holder.getPrimePriceFour().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.getSellingLayoutFour().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
//
//        }
            }
        }

        return row;
    }
    private JumpActivityListener listener;

    public interface JumpActivityListener {
        void jumpActivity(String goodsSid);

    }
    public void setJumpAcitivty(JumpActivityListener listener){
        this.listener=listener;
    }


    public GoodsSpecificationsTo getMinSpecification(List<GoodsSpecificationsTo> specificationsToList){
        double minPrice=0.00;

        if (specificationsToList!=null&&specificationsToList.size()>0){
            GoodsSpecificationsTo minPriceSpecification=specificationsToList.get(0);
            for (int i=0;i<specificationsToList.size();i++){
                if (minPriceSpecification.getRetailPrice()>specificationsToList.get(i).getRetailPrice())
                    minPriceSpecification=specificationsToList.get(i);

            }
            return minPriceSpecification;
        }else
            return null;

    }
}

