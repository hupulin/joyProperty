package com.joy.property.myservice.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceTypeTo;
import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

/**
 * Created by Admin on 2015-09-29
 */
public class ApplyServiceAdapter extends ModeListAdapter<ServiceTypeTo> {
    public ApplyServiceAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View row = convertView;
        ApplyServiceCache holder = null;


        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.list_item_apply_service,null);
            holder = new ApplyServiceCache(row);
            row.setTag(holder);
        }else {
            holder = (ApplyServiceCache) row.getTag();
        }

        ServiceTypeTo serviceTypeTo = mList.get(position);

        /***
         * 家政服务 9098ED29-072D-4653-A37D-3C2F6DF80861
         * 入室维修 BCCF6994-9449-4E6D-9F5B-09CE08AD9353
         * 公共维修 C733AA3D-32FA-4F5B-B299-061044661DC0
         * 投诉 7D2B996C-12EC-4CD4-8499-B453E96AF11F
         * 表扬 3198DD68-1346-4856-BD50-90E9373559A0
         * 建议 9B773735-1E0E-4677-A3B5-19A50B58D15D
         * 巡检 51979B62-10E6-44C7-88B9-4B239B1CE02F
         * 服务分类
         */
        if (!TextUtils.isEmpty(serviceTypeTo.getCategorySid())) {


            if (TextUtils.equals(serviceTypeTo.getCategorySid(), "9098ED29-072D-4653-A37D-3C2F6DF80861")) {

                //家政服务
                holder.getmServiceIcon().setImageResource(R.drawable.h_s_icon);

                switch (position % 3){
                    case 0:
                        holder.getmServiceIcon().setImageResource(R.drawable.h_s_icon);
                        break;
                    case 1:
                        holder.getmServiceIcon().setImageResource(R.drawable.h_s_icon_01);
                        break;
                    case 2:
                        holder.getmServiceIcon().setImageResource(R.drawable.h_s_icon_02);
                        break;
                    case 3:
                        holder.getmServiceIcon().setImageResource(R.drawable.h_s_icon_03);
                        break;
                }
            } else if (TextUtils.equals(serviceTypeTo.getCategorySid(), "BCCF6994-9449-4E6D-9F5B-09CE08AD9353")) {

                //入室维修


                switch (position % 4){
                    case 0:
                        holder.getmServiceIcon().setImageResource(R.drawable.a_s_e_repair);
                        break;
                    case 1:
                        holder.getmServiceIcon().setImageResource(R.drawable.a_s_e_repair_01);
                        break;
                    case 2:
                        holder.getmServiceIcon().setImageResource(R.drawable.a_s_e_repair_02);
                        break;
                    case 3:
                        holder.getmServiceIcon().setImageResource(R.drawable.a_s_e_repair_03);
                        break;
                }
            } else if (TextUtils.equals(serviceTypeTo.getCategorySid(), "C733AA3D-32FA-4F5B-B299-061044661DC0")) {
                //公共维修
                switch (position%3) {

                    case 0:
                        holder.getmServiceIcon().setImageResource(R.drawable.a_s_p_repair);
                        break;
                    case 1:
                        holder.getmServiceIcon().setImageResource(R.drawable.a_s_p_repair_01);
                        break;
                    case 2:
                        holder.getmServiceIcon().setImageResource(R.drawable.a_s_p_repair_02);
                        break;
                    case 3:
                        holder.getmServiceIcon().setImageResource(R.drawable.a_s_p_repair_03);
                        break;
                }

            }
        }else {
            holder.getmServiceIcon().setImageResource(R.drawable.h_s_icon);
        }
          holder.getmServiceName().setText(serviceTypeTo.getTypeName());
           holder.getmServiceDesc().setText(serviceTypeTo.getTypeDescription());

        return row;
    }
}
