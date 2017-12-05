package com.joy.property.task.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.home.ServiceMyWorkTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.utils.Compare;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by usb on 2017/6/15.
 */

public class MyWorkAdapter extends ModeListAdapter<ServiceMyWorkTo> {
    private Context mContext;
    private LayoutInflater inflater ;
    private int state=0;
    Compare compare=new Compare();
    private  MyTaskCache holder;
    private String createOn;//提交时间
    private String responseTime;//相应时间
    private  String replyTime;//处理时间

    ServiceMyWorkTo mode;
    public MyWorkAdapter(Context context) {
        super(context);
        this.mContext = context;
        inflater  = LayoutInflater.from(mContext);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_mytask, null);
            holder = new MyTaskCache(row);
            row.setTag(holder);
        } else {
            holder = (MyTaskCache) row.getTag();
        }
        mode = mList.get(position);
//        holder.getm().setVisibility(View.VISIBLE);
//        holder.getmCreateOnf().setVisibility(View.VISIBLE);
//        holder.getmCr().setVisibility(View.VISIBLE);
//        holder.getmCreateOns().setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(mode.getTypeName())) {
            //保洁，家政...
            holder.getmCategory().setText(mode.getTypeName());
        }
        if (!TextUtils.isEmpty(mode.getApartmentName())) {
            //幸福家园
            holder.getmApartment().setText(mode.getApartmentName());
        }


        if(mode.getCallerTime()==null)
        {
            createOn=Compare.DateToStr(new Date());// 创建时间

        }
        else {
            createOn = mode.getCallerTime();// 创建时间
        }
        if(mode.getResponseTime()==null)
        {
            responseTime=Compare.DateToStr(new Date());// 响应时间
        }
        else {
            responseTime = mode.getResponseTime();// 响应时间
        }

        if(mode.getFinishTime()==null)
        {
            replyTime=Compare.DateToStr(new Date());// 处理时间
        }

        else {
            replyTime = mode.getFinishTime();// 处理时间
        }
        // 赋值
        holder.getmCreateOn().setText(createOn);        // 创建时间
        holder.getmCreateOns().setText(responseTime);   // 响应时间
        holder.getmCreateOnf().setText(replyTime);      // 处理时间
        //  holder.getmServiceBooking().setText(mode.getServiceBookingTime());//预约时间
        // System.out.println(replyTime.toString()+"replyTime");
//        if (mode.getReplyTime() != null) {
//            holder.getmCreateOnf().setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mode.getReplyTime()));
//            System.out.println(mode.getReplyTime()+"mode.getreply");
//        }
        /**
         *responseCycle 15分钟
         *processCycle 24小时
         * startTime 上班时间
         * endTime   下班时间
         * 2 处理中
         * 4 待评论
         * 6 处理结束
         */

        if (!TextUtils.isEmpty(mode.getWorkStatus())) {
            Log.i("222222222222", "getView: "+mode.getWorkNo()+"------"+mode.getWorkStatus());
            switch (Integer.parseInt(mode.getWorkStatus())) {
                case 2:
                    state = 1;
                    holder.getmStatus().setText("已指派");
                    holder.getmStatus().setTextColor(Color.parseColor("#f17834"));
                    holder.getmImage().setBackgroundResource(R.drawable.category_08);
                    holder.getmCr().setVisibility(View.GONE);
                    holder.getmCreateOns().setVisibility(View.GONE);
                    holder.getmCreateOnf().setVisibility(View.GONE);
                    holder.getm().setVisibility(View.GONE);
                    break;
//                case 3:
//                    state = 1;
//                    holder.getmStatus().setText("已撤消");
//                    holder.getmCreateOnf().setVisibility(View.GONE);
//                    holder.getm().setVisibility(View.GONE);
//                    break;
//                case 4:
//                    state = 2;
//                    holder.getmStatus().setText("待评价");
//                    holder.getmCreateOnf().setVisibility(View.VISIBLE);
//                    holder.getm().setVisibility(View.VISIBLE);
//                    break;
                case 6:
                    state = 3;
                    holder.getmStatus().setText("已结束");
                    holder.getmStatus().setTextColor(Color.parseColor("#808080"));
                    holder.getmImage().setBackgroundResource(R.drawable.category_008);

                    holder.getm().setVisibility(View.VISIBLE);
                    holder.getmCreateOnf().setVisibility(View.VISIBLE);
                    holder.getmCr().setVisibility(View.VISIBLE);
                    holder.getmCreateOns().setVisibility(View.VISIBLE);
                    break;
                case 22:
                    state = 1;
                    holder.getmStatus().setText("处理中");
                    holder.getmStatus().setTextColor(Color.parseColor("#46b4d9"));
                    holder.getmImage().setBackgroundResource(R.drawable.category_08);
                    holder.getmCreateOnf().setVisibility(View.GONE);
                    holder.getm().setVisibility(View.GONE);
                    holder.getmCr().setVisibility(View.VISIBLE);
                    holder.getmCreateOns().setVisibility(View.VISIBLE);
                    break;
//                case 21:
//                    state = 1;
//                    holder.getmStatus().setText("处理中");
//                    holder.getmCreateOnf().setVisibility(View.GONE);
//                    holder.getm().setVisibility(View.GONE);
//                    break;
//                case 9:
//                    state = 3;
//                    holder.getmStatus().setText("已结束");
//                    holder.getm().setVisibility(View.VISIBLE);
//                    holder.getmCreateOnf().setVisibility(View.VISIBLE);
//                    holder.getmCr().setVisibility(View.VISIBLE);
//                    holder.getmCreateOns().setVisibility(View.VISIBLE);
//                    //  getCompare();
//                    break;
//                case 20:
//                    state = 1;
//                    holder.getmStatus().setText("已指派");
//                    holder.getmCreateOnf().setVisibility(View.GONE);
//                    holder.getmCr().setVisibility(View.GONE);
//                    holder.getmCreateOns().setVisibility(View.GONE);
//                    holder.getm().setVisibility(View.GONE);
//                    break;
            }
        }

        /***
         * 家政服务 9098ED29-072D-4653-A37D-3C2F6DF80861
         * 入室维修 BCCF6994-9449-4E6D-9F5B-09CE08AD9353
         * 公共维修 C733AA3D-32FA-4F5B-B299-061044661DC0
         * 投诉 7D2B996C-12EC-4CD4-8499-B453E96AF11F
         * 表扬 3198DD68-1346-4856-BD50-90E9373559A0
         * 建议 9B773735-1E0E-4677-A3B5-19A50B58D15D
         * 巡检 51979B62-10E6-44C7-88B9-4B239B1CE02F
         * 送水 51979B62-10E6-44C7-88B8-4B239B1CE03F
         * 服务分类
         * B89C08B9-CD76-4B94-AE27-2617157180EF日常工单的分类ID

         */


        return row;
    }
}
