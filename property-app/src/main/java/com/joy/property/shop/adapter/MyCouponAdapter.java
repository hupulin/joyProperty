package com.joy.property.shop.adapter;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jinyi.ihome.module.newshop.CouponTo;
import com.joy.library.utils.DateUtil;

import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.joy.property.shop.ActivityShopMore;


/**
 * Created by xz on 2016/7/12.
 **/
public class MyCouponAdapter extends ModeListAdapter<CouponTo> {
    private Context mContext;
    private int type;
    public MyCouponAdapter(Context context,int type) {
        super(context);
        this.mContext = context;
        this.type=type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyCouponHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.my_coupon_item, null);
            holder = new MyCouponHolder(row);
            row.setTag(holder);
        } else {
            holder = (MyCouponHolder) row.getTag();
        }

        CouponTo mode = mList.get(position);
        if (mode != null) {
            holder.getItemCoupon().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mode.getGoodsType()==0){
                        //进入普通商品页面
                        Log.i("22222", "进入的普通商品: ");
                        if (type==1||type==2) {
                            Toast.makeText(mContext,type==1?"优惠券已使用了哦":"优惠券已过期哦", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (mode.getIsStart()==0)
                        {
                            Toast.makeText(mContext,"卡券还不能使用哦", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Intent intent =new Intent(mContext, ActivityShopMore.class);
                        intent.putExtra("couponId",mode.getCouponId());
                        mContext.startActivity(intent);
                    }
                    else {
                        //进入活动界面
                        Log.i("22222", "进入的活动商品: ");
                        if (listener!=null&&type==0)
                            listener.intoActivity(mode.getCouponId(),mode.getIsStart());
                    }
                }
            });
            holder.getUseRange().setText("使用范围：查看使用商品");
            holder.getUseCondition().setText("使用限制："+(mode.getUseRule()==0?"无限制":"满"+mode.getConsumptionAmount()+"元使用"));
            holder.getUseTime().setText("使用时间："+DateUtil.getDateString(mode.getActivityStartTime(),"yyyy.MM.dd")+"-"+DateUtil.getDateString(mode.getActivityEndTime(), "yyyy.MM.dd"));
            holder.getCouponAmount().setText(mode.getDiscountAmount()+"");
            if(type==0){
                holder.getAmountLayout().setBackgroundResource(R.drawable.coupon_right_un_use_bg);
            }else
                holder.getAmountLayout().setBackgroundResource(R.drawable.coupon_right_use_bg);
        }
        return row;
    }
    private IntoActivityListener listener;
    public interface IntoActivityListener {
        void intoActivity(String couponId,int isStart);

    }
    public void setIntoActivityListener(IntoActivityListener listener){
        this.listener=listener;
    }
}
