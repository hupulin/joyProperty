package com.joy.property.task.adapter;


import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.utils.Compare;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyInfoAdapter extends ModeListAdapter<ServiceMainExpandTo> {
    private Context mContext;
    private LayoutInflater inflater ;
    private int state=0;
    Compare compare=new Compare();
    private String createOn;//提交时间
    private String responseTime;//相应时间
    private  String replyTime;//处理时间
    MyTaskCache holder;
    ServiceMainExpandTo mode;
    public MyInfoAdapter(Context context) {
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
        String mCategorySid = mode.getServiceCategory();
        if (!TextUtils.isEmpty(mode.getTypeName())) {
            //保洁，家政...
            holder.getmCategory().setText(mode.getTypeName());
        }

        if (mode.getIsBadEvaluate()) {
            holder.getBadComment().setVisibility(View.VISIBLE);
        }else{
            holder.getBadComment().setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mode.getApartmentName())) {
            //幸福家园
            holder.getmApartment().setText(mode.getApartmentName());
        }

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

        if(mode.getReplyTime()==null)
        {
            replyTime=Compare.DateToStr(new Date());// 处理时间
            holder.getmCreateOnf().setTextColor(0xffcc3434);
        }
        else {
            replyTime = Compare.DateToStr(mode.getReplyTime());// 处理时间
        }
        // 赋值
        holder.getmCreateOn().setText(createOn);        // 创建时间
        holder.getmCreateOns().setText(responseTime);   // 响应时间
        holder.getmCreateOnf().setText(replyTime);      // 处理时间
        //holder.getmServiceBooking().setText( mode.getServiceBookingTime());//预约时间

        //提交时间
        /**
         *responseCycle 15分钟
         *processCycle 24小时
         * startTime 上班时间
         * endTime   下班时间
         * 2 处理中
         * 4 待评论
         * 6 处理结束
         */
        if (!TextUtils.isEmpty(mode.getServiceStatus())) {
            switch (Integer.parseInt(mode.getServiceStatus())) {
                case 2:
                    state = 1;
                    holder.getmStatus().setText("已指派");
                    holder.getmStatus().setTextColor(Color.parseColor("#f17834"));
                    holder.getmCreateOnf().setVisibility(View.GONE);
                    holder.getm().setVisibility(View.GONE);
                    getCompare();
                    break;
                case 4:
                    state = 2;
                    holder.getmStatus().setText("待评价");
                    holder.getmCreateOnf().setVisibility(View.VISIBLE);
                    holder.getm().setVisibility(View.VISIBLE);
                    getCompare();
                    break;
                case 6:
                    state = 3;
                    holder.getmStatus().setText("已结束");
                    holder.getmStatus().setTextColor(Color.parseColor("#808080"));
                    holder.getm().setVisibility(View.VISIBLE);
                    holder.getmCreateOnf().setVisibility(View.VISIBLE);
                    getCompare();
                    break;
                case 9:
                    state = 3;
                    holder.getmStatus().setText("已结束");
                    holder.getmStatus().setTextColor(Color.parseColor("#808080"));
                    holder.getm().setVisibility(View.VISIBLE);
                    holder.getmCreateOnf().setVisibility(View.VISIBLE);
                    getCompare();
                    break;
                case 22:
                    state = 1;
                    holder.getmStatus().setText("处理中");
                    holder.getmStatus().setTextColor(Color.parseColor("#46b4d9"));
                    holder.getmCreateOnf().setVisibility(View.GONE);
                    holder.getm().setVisibility(View.GONE);
                    getCompare();
                    break;
                case 20:
                    state = 1;
                    holder.getmStatus().setText("已指派");
                    holder.getmStatus().setTextColor(Color.parseColor("#f17834"));
                    holder.getmCreateOnf().setVisibility(View.GONE);
                    holder.getm().setVisibility(View.GONE);
                    getCompare();
                    break;
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
         * 服务分类
         *
         */
        if (!TextUtils.isEmpty(mode.getServiceCategory())) {
            Date createOnTime=mode.getCreatedOn();
            DateFormat fmt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date reply=fmt.parse(replyTime, new ParsePosition(0));
            if ("7D2B996C-12EC-4CD4-8499-B453E96AF11F".equals(mCategorySid)) {
                // 投诉
                switch (state) {
                    case 1:
                        if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
                        {
                            createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                            if (compare.VaryRed2(createOnTime,mode.getReplyTime(), mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_003);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_03);
                            }
                        }else {
                            if (compare.VaryRed(createOn, replyTime, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_003);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_03);
                            }
                        }
                        break;
                    case 2:
                        holder.getmImage().setBackgroundResource(R.drawable.category_3);
                        break;
                    case 3:
                        holder.getmImage().setBackgroundResource(R.drawable.category_3);
                        break;
                }
            } else if ("C733AA3D-32FA-4F5B-B299-061044661DC0".equals(mCategorySid)) {
                // 公共报修
                switch (state) {
                    case 1:
                        if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
                        {
                            createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                            if (compare.VaryRed2(createOnTime, mode.getReplyTime(), mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_001);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_01);
                            }

                        }else{
                            if (compare.VaryRed(createOn,replyTime, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_001);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_01);
                            }
                        }
                        break;
                    case 2:
                        holder.getmImage().setBackgroundResource(R.drawable.category_1);
                        break;
                    case 3:
                        holder.getmImage().setBackgroundResource(R.drawable.category_1);
                        break;
                }
            } else if ("9098ED29-072D-4653-A37D-3C2F6DF80861".equals(mCategorySid)) {
                //家政
                switch (state) {
                    case 1:
                        if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
                        {
                            createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                            if (compare.VaryRed2(createOnTime,mode.getReplyTime(), mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_002);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_02);
                            }
                        }else{
                            if (compare.VaryRed(createOn,replyTime, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_002);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_02);
                            }
                        }


                        break;
                    case 2:
                        holder.getmImage().setBackgroundResource(R.drawable.category_2);
                        break;
                    case 3:
                        holder.getmImage().setBackgroundResource(R.drawable.category_2);
                        break;

                }
            } else if ("51979B62-10E6-44C7-88B9-4B239B1CE02F".equals(mCategorySid)) {
                //巡检
                switch (state) {
                    case 1:
                        if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
                        {
                            createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                            if (compare.VaryRed2(createOnTime,mode.getReplyTime(), mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_004);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_04);
                            }
                        }else{
                            if (compare.VaryRed(createOn, replyTime, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_004);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_04);
                            }
                        }

                        break;
                    case 2:
                        holder.getmImage().setBackgroundResource(R.drawable.category_4);
                        break;
                    case 3:
                        holder.getmImage().setBackgroundResource(R.drawable.category_4);
                        break;

                }
            } else if ("5CFB60A1-C1FC-4751-B123-05157F02C70D".equals(mCategorySid)) {
                //巡检
                switch (state) {
                    case 1:
                        if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
                        {
                            createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                            if (compare.VaryRed2(createOnTime,mode.getReplyTime(), mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_10);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_10);
                            }
                        }else{
                            if (compare.VaryRed(createOn, replyTime, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_10);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_10);
                            }
                        }

                        break;
                    case 2:
                        holder.getmImage().setBackgroundResource(R.drawable.category_10_finish);
                        break;
                    case 3:
                        holder.getmImage().setBackgroundResource(R.drawable.category_10_finish);
                        break;

                }
            }

            else if (TextUtils.equals(mCategorySid, "BCCF6994-9449-4E6D-9F5B-09CE08AD9353")) {
                // 入室维修
                switch (state) {
                    case 1:
                        if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
                        {

                            createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                            if (compare.VaryRed2(createOnTime,reply, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_005);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_05);
                            }
                        }else {
                            if (compare.VaryRed(createOn, replyTime, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_005);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_05);
                            }
                        }
                        break;
                    case 2:
                        holder.getmImage().setBackgroundResource(R.drawable.category_5);
                        break;
                    case 3:
                        holder.getmImage().setBackgroundResource(R.drawable.category_5);
                        break;
                }
            }else if (TextUtils.equals(mCategorySid,"51979B62-10E6-44C7-88B8-4B239B1CE03F")){
                switch (state) {
                    case 1:
                        if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
                        {
                            createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                            if (compare.VaryRed2(createOnTime,mode.getReplyTime(), mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_006);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_06);
                            }

                        }else{
                            if (compare.VaryRed(createOn, replyTime, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_006);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_06);
                            }
                        }
                        break;
                    case 2:
                        holder.getmImage().setBackgroundResource(R.drawable.category_6);
                        break;
                    case 3:
                        holder.getmImage().setBackgroundResource(R.drawable.category_6);
                        break;
                }
            }else if (TextUtils.equals(mCategorySid,"5CFB60A1-C1FC-4751-B123-05157F02C70D")){
                switch (state) {
                    case 1:
                        if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
                        {
                            createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                            if (compare.VaryRed2(createOnTime,mode.getReplyTime(), mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_10);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_10);
                            }

                        }else{
                            if (compare.VaryRed(createOn, replyTime, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                                holder.getmImage().setBackgroundResource(R.drawable.category_10);
                            } else {
                                holder.getmImage().setBackgroundResource(R.drawable.category_10);
                            }
                        }
                        break;
                    case 2:
                        holder.getmImage().setBackgroundResource(R.drawable.category_10_finish);
                        break;
                    case 3:
                        holder.getmImage().setBackgroundResource(R.drawable.category_10_finish);
                        break;
                }
            }
        }

        else {
            holder.getmImage().setBackgroundResource(R.drawable.category_02);
        }
        return row;
    }

    public  void getCompare(){
        //相应时间 超时大于15分钟


        //  优先使用预约时间 再使用 提报
        Date createOnTime=mode.getCreatedOn();
        DateFormat fmt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date reply=fmt.parse(replyTime, new ParsePosition(0));
//响应超时 超时大于15分钟
        if (compare.VaryRed(createOn, responseTime, mode.getStartTime(), mode.getEndTime(), mode.getResponseCycle())) {
            holder.getmCreateOns().setTextColor(0xffcc3434);
        } else {
            holder.getmCreateOns().setTextColor(0xff808080);
        }
        if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
        {
            createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
            //处理超时 超时大于24小时
            if (compare.VaryRed2(createOnTime,reply, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                holder.getmCreateOnf().setTextColor(0xffcc3434);
            } else {
                holder.getmCreateOnf().setTextColor(0xff808080);
            }

        }else {

            //处理超时 超时大于24小时
            if (compare.VaryRed(createOn, replyTime, mode.getStartTime(), mode.getEndTime(), mode.getProcessCycle())) {
                holder.getmCreateOnf().setTextColor(0xffcc3434);
            } else {
                holder.getmCreateOnf().setTextColor(0xff808080);
            }

        }
    }
}
