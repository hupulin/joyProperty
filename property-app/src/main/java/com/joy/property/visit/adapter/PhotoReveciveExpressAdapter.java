package com.joy.property.visit.adapter;

import android.content.Context;
import android.text.TextUtils;
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
 * 拍照领取快递
 * Created by usb on 2017/8/2.
 */

public class PhotoReveciveExpressAdapter extends ModeListAdapter<ExpressNewTo> {
    private Context mContext;
    public PhotoReveciveExpressAdapter(Context context) {
        super(context);
        this.mContext = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ExpressReceiveHolder holder;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.item_photo_receive, null);
            holder = new ExpressReceiveHolder(row);
            row.setTag(holder);
        }else{
            holder = (ExpressReceiveHolder) row.getTag();

        }
        ShangshabanChangeTextSpaceView tv=  (ShangshabanChangeTextSpaceView)row.findViewById(R.id.tv_name);
        tv.setSpacing(12.5f);
        tv.setText("领取人：");
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
        }if(!TextUtils.isEmpty(mode.getUserName())){
            holder.getReceivename().setText(mode.getUserName());
        }else{
            holder.getReceivename().setText("(暂无)");
        }
        if (mode.isSelect()) {
            holder.getSelectExpress().setImageResource(R.drawable.select_express);
        } else {
            holder.getSelectExpress().setImageResource(R.drawable.unselect_express);
        }
        holder.getTvSelectExpress().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode.isSelect()) {
                        holder.getSelectExpress().setImageResource(R.drawable.unselect_express);
                    mode.setIsSelect(false);
                    if (selectExpressListener != null)
                        selectExpressListener.OnSelectExpress(false, position);
                } else {
                    holder.getSelectExpress().setImageResource(R.drawable.select_express);
                    mode.setIsSelect(true);
                    if (selectExpressListener != null)
                        selectExpressListener.OnSelectExpress(true, position );
                }

            }
        });
        if(1==mode.getIsRegister()){
            holder.getIsRegister().setBackgroundResource(R.drawable.register);
        }else{
            holder.getIsRegister().setBackgroundResource(R.drawable.unregister);
        }
        return row;
    }


    private SelectExpressListener selectExpressListener;
    public void setSelectExpressListener(SelectExpressListener listener){
        this.selectExpressListener=listener;
    }
    public interface SelectExpressListener{
        void OnSelectExpress(boolean select,int position);
    }


}
