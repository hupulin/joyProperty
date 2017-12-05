package com.joyhome.nacity.app.bulk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jinyi.ihome.module.purchase.GroupPurchaseOrderTo;
import com.joy.library.utils.DateUtil;

import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;


public class GroupPurchaseOrderToAdapter extends ModeListAdapter<GroupPurchaseOrderTo> {

    private Context mContext;

    private IGroupPurchaseOrder onGroupPurchaseOrder;

    public IGroupPurchaseOrder getOnGroupPurchaseOrder() {
        return onGroupPurchaseOrder;
    }

    public void setOnGroupPurchaseOrder(IGroupPurchaseOrder onGroupPurchaseOrder) {
        this.onGroupPurchaseOrder = onGroupPurchaseOrder;
    }

    public GroupPurchaseOrderToAdapter(Context context) {
        super(context);
        this.mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View row = convertView;
         GroupPurchaseOrderToCache holder = null;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.my_group_purchase, null);
            holder = new GroupPurchaseOrderToCache(row);
            row.setTag(holder);

        } else {
            holder = (GroupPurchaseOrderToCache) row.getTag();
        }

        final GroupPurchaseOrderTo mode = mList.get(position);
        int mScreenWidth =getScreenWidthPixels(mContext);
        int mWidth = mScreenWidth - 80 ;
        int mHeight = (int)(mWidth * 0.48);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.getPurchaseImage().getLayoutParams();

        params.width = mWidth;
        params.height = mHeight;
        holder.getPurchaseImage().setLayoutParams(params);
        holder.getPurchaseImage().setScaleType(ImageView.ScaleType.FIT_XY);
        if (mode.getPurchaseTo() != null) {
            displayImage(holder.getPurchaseImage(), mode.getPurchaseTo().getPurchaseImage(), R.drawable.store_business_ic);
        }else {
            displayImage(holder.getPurchaseImage(), "", R.drawable.store_business_ic);
        }


        holder.getPurchaseQty().setText(String.valueOf(mode.getPurchaseQty()));

        holder.getTotalPrice().setText(mode.getTotalPrice().toString());

        if (mode.getPurchaseTo() !=null) {
            holder.getPurchaseTitle().setText(mode.getPurchaseTo().getPurchaseTitle());
        }

        /***
         * 0 提交订单
         * 1 支付完成
         * 2 送货完成
         * 3 订单取消
         */

        if ("0".equals(mode.getOrderStatus())) {
            holder.getOrderStatus().setText("已下单");
            holder.getBtnCancel().setVisibility(View.VISIBLE);
            holder.getBtnPay().setVisibility(View.VISIBLE);
        } else if ("1".equals(mode.getOrderStatus())) {
            holder.getOrderStatus().setText("已支付");
            holder.getBtnCancel().setVisibility(View.INVISIBLE);
            holder.getBtnPay().setVisibility(View.INVISIBLE);
        } else if ("2".equals(mode.getOrderStatus())) {
            holder.getOrderStatus().setText("已收货");
            holder.getBtnPay().setVisibility(View.INVISIBLE);
            holder.getBtnCancel().setVisibility(View.INVISIBLE);
        } else if ("3".equals(mode.getOrderStatus())) {
            holder.getOrderStatus().setText("已取消");
            holder.getBtnPay().setVisibility(View.INVISIBLE);
            holder.getBtnCancel().setVisibility(View.INVISIBLE);
        }

        holder.getOrderDate().setText(DateUtil.getDateTimeFormat(DateUtil.mDateFormatString, mode.getPurchaseDate()));
        holder.getBtnCancel().setTag(mode);
        holder.getBtnCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onGroupPurchaseOrder == null) return;
                onGroupPurchaseOrder.reservationCancel((GroupPurchaseOrderTo) v.getTag());

            }
        });

        holder.getBtnPay().setTag(mode.getOrderSid());
        holder.getBtnPay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onGroupPurchaseOrder==null) return;
                onGroupPurchaseOrder.payGroupPurchase((String)v.getTag());
            }
        });

        return row;
    }

    public interface IGroupPurchaseOrder {
        void reservationCancel(GroupPurchaseOrderTo item);
        void payGroupPurchase(String id);
    }

}
