package com.joy.property.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jinyi.ihome.module.newshop.CarGoodsInfo;
import com.jinyi.ihome.module.newshop.CouponTo;
import com.jinyi.ihome.module.newshop.ShoppingCarTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by xz on 2016/7/12.
 **/
public class CouponAdapter extends ModeListAdapter<CouponTo> {
    private Context mContext;

    public CouponAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CouponHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.dialog_coupon_item, null);
            holder = new CouponHolder(row);
            row.setTag(holder);
        } else {
            holder = (CouponHolder) row.getTag();
        }

        CouponTo mode = mList.get(position);
        if (mode != null) {
            holder.getContainTraffic().setText("("+mode.getFreePostage()+")");
            holder.getCouponPrice().setText(mode.getDiscountAmount()+"");
            holder.getUseCondition().setText(mode.getUseRule()==0?"无限制":"订单满"+mode.getConsumptionAmount()+"元使用");
            holder.getUseTime().setText(DateUtil.getDateString(mode.getActivityStartTime(),DateUtil.mDateFormatString) +"-"+DateUtil.getDateString(mode.getActivityEndTime(), DateUtil.mDateFormatString));

        }
        return row;
    }
    private SaveCouponListener listener;

    public interface SaveCouponListener {
        void savetCoupon(String couponSid, int position);

    }
    public void setSaveCoupon(SaveCouponListener listener){
        this.listener=listener;
    }
}
