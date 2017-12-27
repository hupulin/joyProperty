package com.joy.property.visit.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.express.ExpressNewTo;
import com.jinyi.ihome.module.express.ExpressRecordTo;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.views.ShangshabanChangeTextSpaceView;
import com.squareup.picasso.Picasso;

/**
 * 已领取快递的列表adapter
 * Created by usb on 2017/8/1.
 */

public class ReceiveExpressRecordAdapter  extends ModeListAdapter<ExpressNewTo> {
    private Context mContext;
    public ReceiveExpressRecordAdapter(Context context) {
        super(context);
        this.mContext = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        ExpressHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.item_express_receive_record, null);
            holder = new ExpressHolder(row);
            row.setTag(holder);
        } else {
            holder = (ExpressHolder) row.getTag();
        }
        ShangshabanChangeTextSpaceView tv=  (ShangshabanChangeTextSpaceView)row.findViewById(R.id.tv_name);
        tv.setSpacing(12.5f);
        tv.setText("领取人：");
        ExpressNewTo mode=mList.get(position);

        holder.getApartmentName().setText(mode.getApartmentName());
        if(mode.getExpressIcon()!=null){
            Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getExpressIcon()+".png")).into(holder.getExpressImage());
        }if(mode.getExpressRemark()!=null){
            holder.getRemark().setText(mode.getExpressRemark());
        }if(mode.getExpressCompany()!=null){
            holder.getApartmentName().setText(mode.getApartmentName());
        } if(mode.getExpressNo()!=null){
            holder.getExpressNo().setText(mode.getExpressNo());
        }if(mode.getModifiedOn()!=null){
            holder.getReceiveTime().setText(mode.getModifiedOn());
        }if(mode.getCreatedOn()!=null){
            holder.getInputTime().setText(mode.getCreatedOn());
        }if(mode.getExpressPhone()!=null){
            holder.getPhoneNo().setText(mode.getExpressPhone());
        }if(mode.getExpressCompany()!=null){
            holder.getExpressName().setText(mode.getExpressCompany());
        }if(TextUtils.isEmpty(mode.getUserName())){
            Log.i("数据", "暂无: ");
            holder.getName().setText("(暂无)");
        }else{
            Log.i("数据", "名字: "+mode.getUserName());
            holder.getName().setText(mode.getUserName());
        }
        return row;
    }



}
