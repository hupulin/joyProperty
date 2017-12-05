package com.joy.property.shop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jinyi.ihome.module.newshop.CarGoodsInfo;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.joy.property.shop.GoodsDetailActivity;
import com.joy.property.shop.InvalidGoodsActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by xz on 2016/7/12.
 **/
public class ShoppingCarAdapter extends ModeListAdapter<CarGoodsInfo>{
    private Context mContext;
    private Activity activity;
    private Boolean isFirst=true;//开始

    public ShoppingCarAdapter(Context context,Activity activity) {
        super(context);
        this.mContext = context;
        this.activity=activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ShoppingCarHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.shopping_car_item, null);
            holder = new ShoppingCarHolder(row);
            row.setTag(holder);
        } else {
            holder = (ShoppingCarHolder) row.getTag();
        }

        CarGoodsInfo mode=mList.get(position);
        if(mode!=null) {

            if (!TextUtils.isEmpty(mode.getMerchantNameTop()) || !TextUtils.isEmpty(mode.getStoresNameTop())) {
                if (!mode.isBottom()){
                    holder.getRl().setVisibility(View.VISIBLE);
                    holder.getGoodInfo().setVisibility(View.GONE);
                    holder.getBulkLayout().setVisibility(View.GONE);
                    holder.getStoreName().setText("activity".equals(mode.getStoresNameTop()) ? "" : mode.getStoresNameTop());
                    if ("activity".equals(mode.getMerchantId())) {

                        holder.getActivityLine().setVisibility(View.VISIBLE);
                    } else {

                        holder.getActivityLine().setVisibility(View.GONE);
                    }





                    holder.getEdit().setTag(holder.getEdit().getText().toString());
                    if (!mode.isEdit())
                        holder.getEdit().setText("编辑");
                    else
                        holder.getEdit().setText("完成");


                    holder.getEdit().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (mode.isEdit()) {
                                mode.setIsEdit(false);
                                holder.getEdit().setText("编辑");
                                if (editListener != null)
                                    editListener.editCar(mode, false);

                            } else {
                                mode.setIsEdit(true);
                                if (editListener != null)
                                    editListener.editCar(mode, true);
                                holder.getEdit().setText("完成");
                            }

                        }
                    });
                    holder.getCoupons().setTag(mode);
                    holder.getGetCouponLayout().setOnClickListener(view -> {
                        if (couponListener != null)
                            couponListener.getCoupon((CarGoodsInfo) holder.getCoupons().getTag());
                    });
                }else {
                    holder.getRl().setVisibility(View.GONE);
                    holder.getGoodInfo().setVisibility(View.GONE);
                    holder.getBulkLayout().setVisibility(View.VISIBLE);
                    holder.getGoodsPrice().setText(mode.getRetailPrice() + "");
                    //     holder.getContainTraffic().setText("(" + (mode.getDistributionCost() <= 0 ? "不含运费" : "含运费" + mode.getDistributionCost()) + ")");
                    holder.getGoodsCount().setText(mode.getGoodsNum()+"");
                    holder.getSelectStoreLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (mode.isShopSelect()) {
                                mode.setIsShopSelect(false);
                                if (shopSelectListener != null)
                                    shopSelectListener.shopAllSelect(false, mode.getMerchantId());
                                holder.getShopSelect().setBackgroundResource(R.drawable.shopping_car_circle);
                            } else {
                                mode.setIsShopSelect(true);
                                if (shopSelectListener != null)
                                    shopSelectListener.shopAllSelect(true, mode.getMerchantId());
                                holder.getShopSelect().setBackgroundResource(R.drawable.shopping_car_select);
                            }
                        }
                    });

                    holder.getBulkLayout().setOnClickListener(v -> {
                        if (makeOlderListener!=null){
                            makeOlderListener.makeOlderListener(mode);
                        }
                    });

                    if (mode.isShopSelect()) {
                        holder.getShopSelect().setBackgroundResource(R.drawable.shopping_car_select);

                    }
                    else {
                        holder.getShopSelect().setBackgroundResource(R.drawable.shopping_car_circle);

                    }
                    if (mode.getGoodsNum()>0)
                        holder.getBalanceLayout().setBackgroundColor(mContext.getResources().getColor(R.color.page_title_color));
                    else
                        holder.getBalanceLayout().setBackgroundColor(mContext.getResources().getColor(R.color.gray_text));
                }



            } else {
//        holder.getGoodInfo().setOnClickListener(view1 -> {
//            if ("正常".equals(mode.getIsInvalid())) {
//                             Intent intent = new Intent(mContext, GoodsDetailActivity.class);
//                             intent.putExtra("GoodsSid", mode.getGoodsId());
//                             mContext.startActivity(intent);
//                         } else {
//                             mContext.startActivity(new Intent(mContext, InvalidGoodsActivity.class));
//                         }
//            activity. overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);;
//        });
//        if (mode.isNoLine()) {
//            holder.getDivideLine().setVisibility(View.GONE);
//            holder.getDivideLine01().setVisibility(View.VISIBLE);
//        }        else{
//            holder.getDivideLine01().setVisibility(View.GONE);
//            holder.getDivideLine().setVisibility(View.VISIBLE);
//        }

                if (TextUtils.isEmpty(mode.getSpecificationsName())||"null".equals(mode.getSpecificationsName())){
                    holder.getEditSelectSpecification().setVisibility(View.GONE);
                    holder.getLabel().setVisibility(View.INVISIBLE);

                }else {
                    holder.getEditSelectSpecification().setText("已选择:"+(TextUtils.isEmpty(mode.getNewSpecificationsName())?mode.getSpecificationsName():mode.getNewSpecificationsName()));
                    holder.getEditSelectSpecification().setVisibility(View.VISIBLE);
                    holder.getLabel().setText((TextUtils.isEmpty(mode.getNewSpecificationsName())?mode.getSpecificationsName():mode.getNewSpecificationsName()));
                    holder.getLabel().setVisibility(View.VISIBLE);
                }

                holder.getRl().setVisibility(View.GONE);
                holder.getGoodInfo().setVisibility(View.VISIBLE);
                holder.getBulkLayout().setVisibility(View.GONE);
                Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getPicUrl())).error(R.drawable.shop_car_goods_bg).into(holder.getGoodsImage());
                holder.getGoodsName().setText(mode.getGoodsName());
                if (mode.getDistributionCost() > 0) {
                    holder.getTrafficExpense().setText("配送费：¥ " + mode.getDistributionCost());
                } else
                    holder.getTrafficExpense().setText("免运费");
                holder.getPrice().setText("¥" + mode.getRetailPrice());
                if ("正常".equals(mode.getIsInvalid())) {


                    //     holder.getPrimePrice().setVisibility(View.VISIBLE);
                    //     holder.getPrimePrice().setText("¥" + mode.getRetailPrice());
                    //    holder.getPrimePrice().setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.getDiscount().setVisibility(View.VISIBLE);
//            holder.getCarAdd().setVisibility(View.VISIBLE);
//            holder.getCarReduce().setVisibility(View.VISIBLE);
                    holder.getCarNumber().setVisibility(View.VISIBLE);
                    if ("团购".equals(mode.getIsGroup())){
                        if (mode.getDiscountPercentage()<100)
                            holder.getDiscount().setVisibility(View.VISIBLE);
                        else
                            holder.getDiscount().setVisibility(View.GONE);
                        holder.getDiscount().setText((float) (mode.getDiscountPercentage() / 10) + "折");
                    }else
                        holder.getDiscount().setVisibility(View.GONE);

                    holder.getGoodsSelect().setVisibility(View.VISIBLE);
                    holder.getCanUse().setVisibility(View.GONE);

                    if (mode.isSelect()) {
                        holder.getGoodsSelect().setImageResource(R.drawable.shopping_car_select);
                    } else {
                        holder.getGoodsSelect().setImageResource(R.drawable.shopping_car_circle);
                    }

                    holder.getGoodsNumber().setVisibility(View.GONE);
                    holder.getCarNumber().setText(mode.getGoodsNum() + "");
                    holder.getPurchaseNumber().setText(mode.getGoodsNum()+"");
                    holder.getPurchaseAdd().setTag(holder.getPurchaseNumber());
                    holder.getPurchaseAdd().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(isFirst) {
                                isFirst=false;
                                holder.getPurchaseReduce().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.purchase_reduce_new));}
                            if (Integer.valueOf(((TextView)view.getTag()).getText().toString()) < mode.getMaxSalesNum() && Integer.valueOf(((TextView)view.getTag()).getText().toString()) < mode.getNum()) {
                                holder.getPurchaseNumber().setText(Integer.valueOf(((TextView)view.getTag()).getText().toString()) + 1 + "");
                                if (listener != null)
                                    listener.changeGoodsNumber(mode.getGoodsId(), ((TextView)view.getTag()).getText().toString(), position, true, mode);
                            } else
                                Toast.makeText(mContext, "您已超出购买限制", Toast.LENGTH_LONG).show();

                        }
                    });
                    holder.getPurchaseReduce().setTag(holder.getPurchaseNumber());
                    //根据数量和最小购买量的比较设置减数量的图标，然后设置点击后的设置图标
                    if(Integer.valueOf(holder.getPurchaseNumber().getText().toString())>mode.getMinSalesNum()){
                        holder.getPurchaseReduce().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.purchase_reduce_new));
                    }else{
                        holder.getPurchaseReduce().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.purchase_reduce_new_dim));

                    }
                    holder.getPurchaseReduce().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (Integer.valueOf(holder.getPurchaseNumber().getText().toString())<=mode.getMinSalesNum()+1){
                                isFirst=true;
                                holder.getPurchaseReduce().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.purchase_reduce_new_dim));
                            }
                            if (Integer.valueOf(((TextView)view.getTag()).getText().toString())>mode.getMinSalesNum()&&Integer.valueOf(((TextView)view.getTag()).getText().toString())>=1){
                                holder.getPurchaseNumber().setText(Integer.valueOf(((TextView)view.getTag()).getText().toString())-1+"");
                                if (listener!=null)
                                    listener.changeGoodsNumber(mode.getGoodsId(),((TextView)view.getTag()).getText().toString(),position,false,mode);
                            }else
                                Toast.makeText(mContext, "不能再少了~", Toast.LENGTH_LONG).show();

                        }
                    });
                } else {

                    //   holder.getPrimePrice().setVisibility(View.GONE);
                    holder.getDiscount().setVisibility(View.GONE);
                    holder.getCanUse().setVisibility(View.GONE);
//            holder.getCarAdd().setVisibility(View.GONE);
//            holder.getCarReduce().setVisibility(View.GONE);
                    holder.getCarNumber().setVisibility(View.VISIBLE);
                    holder.getGoodsSelect().setVisibility(View.GONE);
                    holder.getGoodsNumber().setVisibility(View.GONE);
                    holder.getGoodsNumber().setText(mode.getGoodsNum()+"");
                }
                holder.getSelectGoodsLayout().setOnClickListener(v -> {

                    if (!"正常".equals(mode.getIsInvalid()))
                        return;
                    if (mode.isSelect()) {


                        holder.getGoodsSelect().setImageResource(R.drawable.shopping_car_circle);
                        mode.setIsSelect(false);
                        if (listener != null)
                            pucchaselistener.purchaseNumberChange(false, position, mode);
                    } else {
                        holder.getGoodsSelect().setImageResource(R.drawable.shopping_car_select);
                        mode.setIsSelect(true);
                        if (listener != null)
                            pucchaselistener.purchaseNumberChange(true, position, mode);
                    }
                });
                System.out.println(mode.getSpecificationsName());


                holder.getEditSelectSpecification().setOnClickListener(v -> {
                    if (selectSpecificationListener != null)
                        selectSpecificationListener.selectSpecificationListener(mode, (TextView) v, holder.getPrice());

                });



            }

            if (mode.isEdit()&&"正常".equals(mode.getIsInvalid())){
                holder.getNoEditLayout().setVisibility(View.GONE);
                holder.getEditSpecification().setVisibility(View.VISIBLE);
            }else {
                holder.getNoEditLayout().setVisibility(View.VISIBLE);
                holder.getEditSpecification().setVisibility(View.GONE);
            }
        }


        return row;
    }
    private ShopCarChangeListener listener;
    private PurchaseChangeListener pucchaselistener;
    public void setPurchaseNumberChangeListener(PurchaseChangeListener listener){
        this.pucchaselistener=listener;
    }
    public void setChangeGoodsListener(ShopCarChangeListener listener){
        this.listener=listener;
    }
    public interface ShopCarChangeListener {

        void changeGoodsNumber(String goodsId,String number,int position,boolean isAdd,CarGoodsInfo mode);
    }
    public interface PurchaseChangeListener {
        void purchaseNumberChange(Boolean select, int position,CarGoodsInfo goodsTo);

    }
    private ShopAllSelectListener shopSelectListener;
    public interface ShopAllSelectListener {
        void shopAllSelect(Boolean select, String merchantId);

    }
    public void setShopAllSelect(ShopAllSelectListener listener){
        this.shopSelectListener=listener;
    }
    private CouponListener couponListener;

    public interface CouponListener {
        void getCoupon(CarGoodsInfo goodsTo);

    }
    public void setGetCoupon(CouponListener listener){
        this.couponListener=listener;
    }


    private EditListener editListener;

    public interface EditListener {

        void editCar(CarGoodsInfo mode,boolean isEdit);
    }
    public void setEditCar(EditListener listener){
        this.editListener=listener;
    }


    private SelectSpecificationListener selectSpecificationListener;

    public interface SelectSpecificationListener {

        void selectSpecificationListener(CarGoodsInfo mode,TextView specificationText,TextView priceText);
    }
    public void setSelectSpecification(SelectSpecificationListener listener){
        this.selectSpecificationListener=listener;
    }

    private MakeOlderListener makeOlderListener;
    public interface MakeOlderListener {

        void makeOlderListener(CarGoodsInfo mode);
    }
    public void setMakeOlderListener(MakeOlderListener listener){
        this.makeOlderListener=listener;
    }
}
