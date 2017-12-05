package com.joy.property.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinyi.ihome.module.newshop.ShopListDetailTo;
import com.joy.property.R;
import com.joy.property.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by xz on 2016/7/12.
 **/
public class CouponGoodsAdapter extends ModeListAdapter<ShopListDetailTo> {
    private Context mContext;
    private List<ShopListDetailTo> detailList=new ArrayList<>();
    private double minRetailPrice;
    public CouponGoodsAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList==null?0:(mList.size()/2+(mList.size()%2==0?0:1));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        GoodsListHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.goods_item, null);
            holder = new GoodsListHolder(row);
            row.setTag(holder);
        } else {
            holder = (GoodsListHolder) row.getTag();
        }
        ShopListDetailTo mode1=null;
        ShopListDetailTo mode=mList.get(position*2);
        if (position*2+1<mList.size()) {
            mode1 = mList.get(position * 2 + 1);
        }else
            mode1=null;
        detailList.clear();
        detailList.add(mode);
        detailList.add(mode1);
        holder.getGridView().removeAllViews();

        for(int i=0;i<detailList.size();i++) {

            View childView = View.inflate(mContext, R.layout.shop_more_item, null);
            ImageView goodsImage = (ImageView) childView.findViewById(R.id.goods_image);
            TextView goodsName = (TextView) childView.findViewById(R.id.goods_name);
            TextView discount = (TextView) childView.findViewById(R.id.discount);
            TextView primePrice = (TextView) childView.findViewById(R.id.prime_price);
            ImageView addCar = (ImageView) childView.findViewById(R.id.add_car);
            TextView priceLeft = (TextView) childView.findViewById(R.id.price_left);
            TextView priceRight = (TextView) childView.findViewById(R.id.price_right);
            TextView saleType = (TextView) childView.findViewById(R.id.saleType);
            TextView label = (TextView) childView.findViewById(R.id.label);
            ImageView bulkIcon = (ImageView) childView.findViewById(R.id.bulk_icon);
            TextView priceLeftIcon = (TextView) childView.findViewById(R.id.price_left_icon);
            TextView topLabel = (TextView) childView.findViewById(R.id.topLabel);

            ShopListDetailTo detailTo = detailList.get(i);

            if (detailTo!=null) {
                goodsImage.setVisibility(View.VISIBLE);
                goodsName.setVisibility(View.VISIBLE);
//       discount.setVisibility(View.VISIBLE);
//       primePrice.setVisibility(View.VISIBLE);
//       addCar.setVisibility(View.VISIBLE);
                priceLeft.setVisibility(View.VISIBLE);
//       priceRight.setVisibility(View.VISIBLE);
//       bulkIcon.setVisibility(View.VISIBLE);
//       saleType.setVisibility(View.VISIBLE);
//       priceLeftIcon.setVisibility(View.VISIBLE);
//       if (TextUtils.isEmpty(detailTo.getGoodsLabel()))
//           label.setVisibility(View.GONE);
//       else {
//           label.setVisibility(View.VISIBLE);
//           label.setText(detailTo.getGoodsLabel());
                //    }
//       if (!TextUtils.isEmpty(detailTo.getLabelColor()))
//           label.setBackgroundColor(Color.parseColor(detailTo.getLabelColor()));
                Picasso.with(mContext).load(com.joyhome.nacity.app.MainApp.getPicassoImagePath(detailTo.getPicUrl())).error(R.drawable.goods_un_load_bg).into(goodsImage);

                goodsName.setText(detailTo.getGoodsName());


                //primePrice.setText("¥" + minRetailPrice);
                primePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                saleType.setText(detailTo.getStoresName());
//       double price = Double.valueOf(detailTo.getCurrentPrice());

                priceLeft.setText("¥" + detailTo.getRetailPrice());
                if (TextUtils.isEmpty(detailTo.getGoodsLabel()))
                    topLabel.setVisibility(View.GONE);
                else {
                    topLabel.setVisibility(View.VISIBLE);
                    topLabel.setText(detailTo.getGoodsLabel());
                }
                addCar.setOnClickListener(v -> {

                    if (addCarListener != null)
                        addCarListener.OnAddCarClick(detailTo.getGoodsId() + "", "");
                });
                childView.setBackgroundColor(Color.parseColor("#ffffff"));
                saleType.setOnClickListener(view -> {
                    if (listener!=null)
                        listener.OnCanEnterShopClick(detailTo.getMerchantId(),detailTo.getStoresName(),detailTo.getSalesType());
                });
//       if ("非团购".equals(detailTo.getIsGroup())) {
//           bulkIcon.setVisibility(View.GONE);
//           discount.setVisibility(View.INVISIBLE);
//           primePrice.setVisibility(View.INVISIBLE);
//       }else {
//           bulkIcon.setVisibility(View.VISIBLE);
//           goodsName.setText("\u3000\u3000"+" " + detailTo.getGoodsName());
//           discount.setVisibility(View.VISIBLE);
//           primePrice.setVisibility(View.VISIBLE);
//           if (!TextUtils.isEmpty(detailTo.getDiscountPercentage()) && Double.valueOf(detailTo.getDiscountPercentage()) < 100) {
//               discount.setVisibility(View.VISIBLE);
//               discount.setText((float)(Double.valueOf(detailTo.getDiscountPercentage())/10) + "折");
//           }else
//               discount.setVisibility(View.GONE);
//       }
            }else {
//       label.setVisibility(View.INVISIBLE);
                goodsImage.setVisibility(View.INVISIBLE);
                goodsName.setVisibility(View.INVISIBLE);
//       discount.setVisibility(View.INVISIBLE);
//       primePrice.setVisibility(View.INVISIBLE);
//       addCar.setVisibility(View.INVISIBLE);
                priceLeft.setVisibility(View.INVISIBLE);
//       priceRight.setVisibility(View.INVISIBLE);
//       bulkIcon.setVisibility(View.INVISIBLE);
//       saleType.setVisibility(View.INVISIBLE);
//       priceLeftIcon.setVisibility(View.INVISIBLE);
                childView.setBackgroundColor(Color.parseColor("#f1f1f1"));
            }
            int mWidth = getScreenWidthPixels(mContext);
            int margin = (int) (mWidth * 0.002777);
            int width = (int) (mWidth * 0.49722);
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.width = width;
            lp.height = (int) (mWidth * 0.60138);
            lp.setMargins(0, 0, margin, margin);
            childView.setLayoutParams(lp);
            childView.setOnClickListener(view -> {
                if (detailListener!=null&&detailTo!=null)
                    detailListener.OnEnterDetailClick(detailTo.getGoodsId()+"",detailTo.getGoodsType());
            });
            holder.getGridView().addView(childView);
        }
        return row;
    }
    public int getScreenWidthPixels(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
    private OnCanEnterShopClickListener listener;
    public void setCanEnterShopClickListener(OnCanEnterShopClickListener listener){
        this.listener=listener;
    }
    public interface OnCanEnterShopClickListener{
        void OnCanEnterShopClick(String shopSid, String name, String saleType);
    }
    private AddCarListener addCarListener;
    public void setAddCarClickListener(AddCarListener listener){
        this.addCarListener=listener;
    }
    public interface AddCarListener{
        void OnAddCarClick(String shopSid, String name);
    }

    private EnterGoodsDetailListener detailListener;
    public void setEnterGoodsDetailListener(EnterGoodsDetailListener listener){
        this.detailListener=listener;
    }
    public interface EnterGoodsDetailListener{
        void OnEnterDetailClick(String goodsSid,String goodsType);
    }
}
