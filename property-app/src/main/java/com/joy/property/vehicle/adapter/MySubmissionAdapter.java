package com.joy.property.vehicle.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.vehicle.adapter.MySubmissionCache;

/**
 * Created by Admin on 2015-02-04
 */
public class MySubmissionAdapter extends ModeListAdapter<ServiceMainExpandTo> {
    private Context mContext;

    public MySubmissionAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MySubmissionCache holder;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_submission, null);
            holder = new MySubmissionCache(row);
            row.setTag(holder);
        } else {
            holder = (MySubmissionCache) row.getTag();
        }
        row.findViewById(R.id.emergency).setVisibility(View.GONE);
        ServiceMainExpandTo mode = mList.get(position);

        if (!TextUtils.isEmpty(mode.getApartmentName())) {
            holder.getmApartmentName().setText(mode.getApartmentName());
        }

        if ( !TextUtils.isEmpty(mode.getTypeName())) {
            holder.getmTypeName().setText(mode.getTypeName());
        }

        if (!TextUtils.isEmpty(mode.getCreatedOn().toString())) {

           holder.getmTime().setText(DateUtil.getDateTimeFormat(DateUtil.mDateFormatString, mode.getCreatedOn()));
        }

        if (!TextUtils.isEmpty(mode.getServiceDesc())) {
            holder.getmServiceDesc().setText(mode.getServiceDesc());
        }
        if (!TextUtils.isEmpty(mode.getServiceDesc())&&!TextUtils.isEmpty(mode.getRoomNo())){
            holder.getmServiceDesc().setText( (TextUtils.isEmpty(mode.getServiceContact())?"":(mode.getServiceContact()+";"))+mode.getRoomNo()+";"+(TextUtils.isEmpty(mode.getServicePhone())?"":mode.getServicePhone()+";")+mode.getServiceDesc());
        }
        if (!TextUtils.isEmpty(mode.getStatusStr())) {
            String progress = String.format("<font color='#979797'>%s</font><font color ='#46b4d9'>%s</font>" , "服务进度：",mode.getStatusStr());
            holder.getmProgress().setText(Html.fromHtml(progress));
        }
        //紧急程度
        if (!TextUtils.isEmpty(mode.getServiceEmergenctStatus())) {
            String serialnumber = String.format("<font color='#979797'>%s</font><font color ='#46b4d9'>%s</font>" , "紧急程度：",mode.getServiceEmergenctStatus());
            holder.getmEmergency().setText(Html.fromHtml(serialnumber));
        }
        //获取服务编号
        if (!TextUtils.isEmpty(mode.getServiceNo())) {
            String serialnumber = String.format("<font color='#979797'>%s</font>" , "服务编号："+mode.getServiceNo());
            holder.getmSerialNumber().setText(Html.fromHtml(serialnumber));
        }

        if (TextUtils.isEmpty(mode.getServiceCategory())) {
            //默认图片
            holder.getmImageView().setImageResource(R.drawable.category_02);
        } else {
            //投诉
            if ("7D2B996C-12EC-4CD4-8499-B453E96AF11F".equals(mode.getServiceCategory())) {
                holder.getmImageView().setImageResource(R.drawable.category_03);
                //报修
            } else if ("C733AA3D-32FA-4F5B-B299-061044661DC0".equals(mode.getServiceCategory())) {
                holder.getmImageView().setImageResource(R.drawable.category_01);
                //服务
            } else if ("9098ED29-072D-4653-A37D-3C2F6DF80861".equals(mode.getServiceCategory())) {
                holder.getmImageView().setImageResource(R.drawable.category_02);
            }else if ("51979B62-10E6-44C7-88B9-4B239B1CE02F".equals(mode.getServiceCategory())) {
                holder.getmImageView().setImageResource(R.drawable.category_04);
                //巡检
            }else if ("5CFB60A1-C1FC-4751-B123-05157F02C70D".equals(mode.getServiceCategory())) {
                holder.getmImageView().setImageResource(R.drawable.category_10);
                //车辆巡检
            }
        }


        return row;
    }
}
