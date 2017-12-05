package com.joy.property.task.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.utils.Compare;
import com.squareup.picasso.Picasso;

import java.util.Date;

/**
 * Created by Admin on 2015-01-27
 */
public class TaskHallAdapter extends ModeListAdapter<ServiceMainExpandTo> {
    private Context mContext;
    Compare compare = new Compare();
    private String createOn;//提交时间
    private String responseTime;//相应时间
    private TaskHallCache holder;
    ServiceMainExpandTo mode;

    public
    TaskHallAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_taskhall, null);
            holder = new TaskHallCache(row);
            row.setTag(holder);
        } else {
            holder = (TaskHallCache) row.getTag();
        }
         mode = mList.get(position);

        if(mode.getCreatedOn()==null)
        {
            createOn=Compare.DateToStr(new Date());// 创建时间

        }
        else {
            createOn = Compare.DateToStr(mode.getCreatedOn());// 创建时间
        }
        if(mode.getResponseTime()==null)
        {
            responseTime=Compare.DateToStr(new Date());// 响应时间
        }
        else {
            responseTime = Compare.DateToStr(mode.getResponseTime());// 响应时间
        }


        if (!TextUtils.isEmpty(mode.getTypeName())) {
            holder.getmCategory().setText(mode.getTypeName());
        }

        if (!TextUtils.isEmpty(mode.getWaitingTime())) {
            holder.getmPostTime().setText(mode.getWaitingTime());
            getCompare();
        }
        if (!TextUtils.isEmpty(mode.getApartmentName())) {
            holder.getmApartment().setText(mode.getApartmentName());
        }

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
        System.out.println(mode.getServiceCategory()+"service--------------");


            if ("7D2B996C-12EC-4CD4-8499-B453E96AF11F".equals(mode.getServiceCategory())) {
                // 投诉
           //     holder.getmImage().setBackgroundResource(R.drawable.category_03);
                Picasso.with(mContext).load(R.drawable.category_03).into(holder.getmImage());
            }
            if ("C733AA3D-32FA-4F5B-B299-061044661DC0".equals(mode.getServiceCategory())) {
                // 公共维修
               // holder.getmImage().setBackgroundResource(R.drawable.category_01);
                Picasso.with(mContext).load(R.drawable.category_01).into(holder.getmImage());
            }
            if ("9098ED29-072D-4653-A37D-3C2F6DF80861".equals(mode.getServiceCategory())) {
                // 家政服务
          //      holder.getmImage().setBackgroundResource(R.drawable.category_02);
                Picasso.with(mContext).load(R.drawable.category_02).into(holder.getmImage());
            }
            if ("51979B62-10E6-44C7-88B9-4B239B1CE02F".equals(mode.getServiceCategory())) {
         //       holder.getmImage().setImageResource(R.drawable.category_04);
                //巡检
                Picasso.with(mContext).load(R.drawable.category_04).into(holder.getmImage());
            }
            if ( "BCCF6994-9449-4E6D-9F5B-09CE08AD9353".equals(mode.getServiceCategory())) {
                // 入室维修
//.getmImage().setBackgroundResource(R.drawable.category_05);
                Picasso.with(mContext).load(R.drawable.category_05).into(holder.getmImage());
            }
            if ("51979B62-10E6-44C7-88B8-4B239B1CE03F".equals(mode.getServiceCategory())) {
             //  holder.getmImage().setBackgroundResource(R.drawable.category_06);
                Picasso.with(mContext).load(R.drawable.category_06).into(holder.getmImage());
            }
            if ("5CFB60A1-C1FC-4751-B123-05157F02C70D".equals(mode.getServiceCategory())) {
//                holder.getmImage().setBackgroundResource(R.drawable.category_10);
                Picasso.with(mContext).load(R.drawable.category_10).into(holder.getmImage());
            }

        return row;
    }

    public void getCompare() {

        //响应超时 超时大于15分钟
        System.out.println(createOn + "--" + responseTime + "--" + mode.getStartTime() + "--" + mode.getEndTime() + "--" + mode.getResponseCycle());
        if (mode.getStartTime() != null && mode.getEndTime() != null) {
            if (compare.VaryRed(createOn, responseTime, mode.getStartTime(), mode.getEndTime(), mode.getResponseCycle())) {
                holder.getmPostTime().setTextColor(0xffcc3434);
            } else {
                holder.getmPostTime().setTextColor(0xff808080);
            }

        }
    }
}
