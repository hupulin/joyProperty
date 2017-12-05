package com.joy.property.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinyi.ihome.module.newshop.ExpressTo;
import com.jinyi.ihome.module.newshop.MyOlderGoods;
import com.jinyi.ihome.module.newshop.MyOlderTo;
import com.joy.library.utils.DateUtil;
import com.joyhome.nacity.app.MainApp;
import com.joy.property.R;
import com.joy.property.common.PictureShowActivity;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.joy.property.shop.ShoppingExpressActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by xz on 2016/7/12.
 **/
public class MyShoppingAdapter extends ModeListAdapter<MyOlderTo> {
    private Context mContext;
    private int type;
    private boolean isAll;
    String imagePath = "";
    private int androidVersion=android.os.Build.VERSION.SDK_INT;
    public MyShoppingAdapter(Context context,int type,boolean isAll) {
        super(context);
        this.mContext = context;
        this.type=type;
        this.isAll=isAll;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyShoppingHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);

                row = inflater.inflate(R.layout.my_shopping_item, null);

            holder = new MyShoppingHolder(row);
            row.setTag(holder);
        } else {
            holder = (MyShoppingHolder) row.getTag();
        }

        MyOlderTo mode = mList.get(position);
        if (mode != null) {
            imagePath="";
            for (MyOlderGoods goods:mode.getOrderGoodsList()) {
                if (!imagePath.contains(goods.getPicUrl()==null?"R.drawable.goods_un_load_bg":goods.getPicUrl()))
                imagePath = imagePath +  goods.getPicUrl() + ";";

            }
            if (!TextUtils.isEmpty(imagePath))
            imagePath = imagePath.substring(0, imagePath.length() - 1);

            holder.getPrice().setText("¥"+mode.getActualPayAmount() + "");
            holder.getOlderTime().setText("下单时间:"+DateUtil.formatDateString(DateUtil.mDateFormatString, mode.getOrderTime()));

            String[] path = imagePath.split(";");
             holder.getGoodsNumber().setText(mode.getOrderGoodsList().size() + "");
             holder.getGridView().removeAllViews();
            System.out.println(imagePath);
            for(int i=0;i<path.length;i++){
                System.out.println(path[i]);
                GridLayout.LayoutParams layoutParams=new GridLayout.LayoutParams();
                int mWidth=getScreenWidthPixels(mContext);
                layoutParams.width=(int)(mWidth*0.2430);
                layoutParams.height=(int)(mWidth*0.2430);
                layoutParams.setMargins(0, 0, 10, 0);
                layoutParams.setGravity(Gravity.CENTER_VERTICAL);

                View view=View.inflate(mContext,R.layout.confirm_order_image,null);
                ImageView postImage= (ImageView) view.findViewById(R.id.image);
                Picasso.with(mContext).load(MainApp.getImagePath(path[i])).placeholder(R.drawable.shop_car_goods_bg).error(R.drawable.shop_car_goods_bg).into(postImage);
                postImage.setTag(R.id.tag_first, i);
                postImage.setTag(R.id.tag_second, imagePath);
                view.setLayoutParams(layoutParams);
               if (i>=2)
                view.setVisibility(View.GONE);


                postImage.setOnClickListener(v -> {
                    postViewImage((int) v.getTag(R.id.tag_first), (String) v.getTag(R.id.tag_second));
                });

                holder.getGridView().addView(view);
            }


            if(type==1){

                holder.getOlderStatus().setText("待支付");
                holder.getContactCustomService().setVisibility(View.GONE);
                holder.getPayOlder().setVisibility(View.VISIBLE);
                holder.getPayOlder().setBackgroundResource(R.drawable.pay_older_bg);
                holder.getPayOlder().setText("去支付");
                holder.getDeleteOlder().setVisibility(View.VISIBLE);
                holder.getDeleteOlder().setBackgroundResource(R.drawable.delect_older);
                holder.getDeleteOlder().setText("取消订单");

                holder.getOlderTime().setText("下单时间:"+DateUtil.formatDateString(DateUtil.mDateFormatString,mode.getOrderTime()));

            }else if(type==2){
                holder.getOlderStatus().setText("待发货");
                holder.getContactCustomService().setVisibility(View.GONE);
                holder.getPayOlder().setVisibility(View.GONE);
                holder.getPayOlder().setBackgroundResource(R.drawable.pay_older_bg);
                holder.getPayOlder().setText("去支付");
                holder.getDeleteOlder().setVisibility(View.GONE);
                holder.getDeleteOlder().setBackgroundResource(R.drawable.delect_older);
                holder.getDeleteOlder().setText("取消订单");

            }else  if(type==3){
                holder.getOlderStatus().setText("待收货");
                holder.getContactCustomService().setVisibility(View.GONE);
                holder.getPayOlder().setVisibility(View.VISIBLE);
                holder.getPayOlder().setBackgroundResource(R.drawable.delect_older);
                holder.getPayOlder().setText("确认收货");
                holder.getPayOlder().setOnClickListener(v -> mContext.startActivity(new Intent(mContext, ShoppingExpressActivity.class)));
                holder.getDeleteOlder().setVisibility(View.GONE);
                holder.getDeleteOlder().setBackgroundResource(R.drawable.delect_older);
                holder.getDeleteOlder().setText("申请退款");
                holder.getExpressLayout().removeAllViews();
                if (mode.getOrderExpressList()!=null&&mode.getOrderExpressList().size()>0){
                    for (ExpressTo expressTo:mode.getOrderExpressList()) {
                        View view = View.inflate(mContext, R.layout.shop_express_item, null);
                        TextView expressCompany = (TextView) view.findViewById(R.id.expressCompany);
                        TextView expressName = (TextView) view.findViewById(R.id.expressName);
                        TextView deliverName = (TextView) view.findViewById(R.id.deliverName);
                        TextView expressNumber = (TextView) view.findViewById(R.id.expressNumber);
                        TextView phone = (TextView) view.findViewById(R.id.phone);
                        if (TextUtils.isEmpty(expressTo.getExpressNo())||TextUtils.isEmpty(expressTo.getExpressCompany()))
                        {

                            expressCompany.setVisibility(View.GONE);
                            deliverName.setVisibility(View.GONE);
                            expressName.setText(expressTo.getPackageName());
                            expressNumber.setText("配送员姓名："+expressTo.getDeliverName());
                            phone.setText("配送员手机："+expressTo.getDeliverPhone());
                            holder.getExpressLayout().addView(view);
                        }else {
                            expressCompany.setVisibility(View.GONE);
                            deliverName.setVisibility(View.GONE);
                            expressName.setText(expressTo.getPackageName());
                            expressNumber.setText("配送快递："+expressTo.getExpressCompany());
                             phone.setText("配送单号："+expressTo.getExpressNo());
                            holder.getExpressLayout().addView(view);
                        }

                    }

                }else
                    holder.getExpressLayout().setVisibility(View.GONE);
            }
            else  if(type==4){
                holder.getOlderStatus().setText("待评价");
                holder.getContactCustomService().setVisibility(View.GONE);
                holder.getPayOlder().setVisibility(View.VISIBLE);
                holder.getPayOlder().setBackgroundResource(R.drawable.pay_older_bg);
                holder.getPayOlder().setText("去评价");
                holder.getDeleteOlder().setVisibility(View.GONE);
                holder.getDeleteOlder().setBackgroundResource(R.drawable.delect_older);
                holder.getDeleteOlder().setText("取消订单");
            } else  if(type==5){
                holder.getOlderStatus().setText("已完成");
                holder.getContactCustomService().setVisibility(View.GONE);
                holder.getPayOlder().setVisibility(View.GONE);
                holder.getPayOlder().setBackgroundResource(R.drawable.pay_older_bg);
                holder.getPayOlder().setText("已完成");
                holder.getDeleteOlder().setVisibility(View.GONE);
                holder.getDeleteOlder().setBackgroundResource(R.drawable.delect_older);
                holder.getDeleteOlder().setText("取消订单");
            }
            holder.getDeleteOlder().setOnClickListener(view -> {
                if (cancelListener != null)
                    cancelListener.setCancel(mode.getOrderId() + "", 0);
            });
            holder.getPayOlder().setOnClickListener(view -> {

                if (confirmListener!=null)
                    confirmListener.setConfirm(mode.getOrderId()+"",0);
            });

}
        return row;
    }
    public void postViewImage(int index, String item) {
        if (TextUtils.isEmpty(item)) {
            return;
        }
        Intent intent = new Intent(mContext, PictureShowActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("path", item);
        mContext.startActivity(intent);
    }
    private ConfirmListener confirmListener;

    public interface ConfirmListener {
        void setConfirm(String olderId, int type);

    }
    public void setConfirm(ConfirmListener listener){
        this.confirmListener=listener;
    }
    private CancelListener cancelListener;

    public interface CancelListener {
        void setCancel(String olderId, int type);

    }
    public void setCancel(CancelListener listener){
        this.cancelListener=listener;
    }
}
