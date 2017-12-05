package com.joy.property.visit.adapter;

import android.content.Context;
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

import static com.joy.property.R.id.campaignImage;


/**
 * Created by usb on 2017/6/6.
 */

public class ExpressRecordAdapter extends ModeListAdapter<ExpressNewTo> {
    private Context mContext;
    public ExpressRecordAdapter(Context context) {
        super(context);
        this.mContext = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        ExpressHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.item_express_record, null);
            holder = new ExpressHolder(row);
            row.setTag(holder);
        } else {
            holder = (ExpressHolder) row.getTag();
        }
//        ShangshabanChangeTextSpaceView tv=  (ShangshabanChangeTextSpaceView)row.findViewById(R.id.tv_name);
//        tv.setSpacing(12.5f);
//        tv.setText("领取人：");
        ExpressNewTo mode=mList.get(position);

        holder.getApartmentName().setText(mode.getApartmentName());
        if(mode.getExpressIcon()!=null){
           Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getExpressIcon()+".png")).into(holder.getExpressImage());
        }
        if(mode.getExpressCompany()!=null){
            holder.getApartmentName().setText(mode.getApartmentName());

        } if(mode.getExpressNo()!=null){
            holder.getExpressNo().setText(mode.getExpressNo());
        }if(mode.getCreatedOn()!=null){
            holder.getInputTime().setText(mode.getCreatedOn());
        }if(mode.getExpressPhone()!=null){
            holder.getPhoneNo().setText(mode.getExpressPhone());
        }if(mode.getExpressCompany()!=null){
            holder.getExpressName().setText(mode.getExpressCompany());
        }
//        if(mode.getUserName()!=null){
//            holder.getName().setText(mode.getUserName());
//        }
        return row;
    }



}
