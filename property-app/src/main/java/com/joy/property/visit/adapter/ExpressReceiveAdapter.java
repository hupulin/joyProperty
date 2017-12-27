package com.joy.property.visit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.express.ExpressNewTo;
import com.jinyi.ihome.module.express.ExpressRecordTo;
import com.jinyi.ihome.module.express.ExpressToNew;
import com.jinyi.ihome.module.express.FindExpressRecordTo;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.views.ShangshabanChangeTextSpaceView;
import com.squareup.picasso.Picasso;

/**
 *  领取快递列表
 * Created by usb on 2017/7/31.
 */

public class ExpressReceiveAdapter extends ModeListAdapter<ExpressNewTo> {
    private Context mContext;
    private String length;
    public ExpressReceiveAdapter(Context context,String length) {
        super(context);
        this.mContext = context;
        this.length = length;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        ExpressReceiveHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.item_express_receive, null);
            holder = new ExpressReceiveHolder(row);
            row.setTag(holder);
        } else {
            holder = (ExpressReceiveHolder) row.getTag();
        }
        ShangshabanChangeTextSpaceView tv=  (ShangshabanChangeTextSpaceView)row.findViewById(R.id.tv_name);
        tv.setSpacing(12.5f);
        tv.setText("领取人：");
        ExpressNewTo mode=mList.get(position);
        holder.getApartmentName().setText(mode.getApartmentName());
        if("6".equals(length)){
            //显示取件码
            holder.getItemCode().setVisibility(View.VISIBLE);
        }else{
            holder.getItemCode().setVisibility(View.GONE);

        }
        if(mode.getExpressIcon()!=null){
            Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getExpressIcon()+".png")).into(holder.getExpressImage());
        }
        if(mode.getExpressCompany()!=null){
            holder.getApartmentName().setText(mode.getApartmentName());

        }if(mode.getExpressRemark()!=null){
            holder.getRemark().setText(mode.getExpressRemark());

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
        holder.getResendCode().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reSentCodeListener.OnReSentCodeListener(mode.getExpressSid());
            }
        });
        if("0".equals(mode.getSendAgain())){
            holder.getResendCode().setEnabled(false);
            holder.getResendCode().setTextColor(Color.parseColor("#aaaaaa"));
        }
        holder.getGetExpress().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getExpressListener.OnGetExpressListener(mode.getExpressSid(),mode.getExpressRemark());
            }
        });
        if(1==mode.getIsRegister()){
            holder.getIsRegister().setBackgroundResource(R.drawable.register);
        }else{
            holder.getIsRegister().setBackgroundResource(R.drawable.unregister);

        }
        return row;
    }


    private ReSentCodeListener reSentCodeListener;
    public void setReSentCodeListener(ReSentCodeListener listener){
        this.reSentCodeListener=listener;
    }
    public interface ReSentCodeListener{
        void OnReSentCodeListener(String expressSid);
    }

    private GetExpressListener getExpressListener;
    public void SetGetExpressListener(GetExpressListener listener){
        this.getExpressListener=listener;
    }
    public interface GetExpressListener{
        void OnGetExpressListener(String expressSid,String remark);
    }
}
