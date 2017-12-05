package com.joy.property.task.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.complaint.fragment.adapter.UnclaimedCache;
import com.joy.property.utils.Compare;
import com.joy.property.utils.SpUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * //处理中
 * Created by Admin on 2015-02-11
 */
public class   RepairsProcessingAdapter extends ModeListAdapter<ServiceMainExpandTo> {
    private Context  mContext;
    Compare compare=new Compare();
    public RepairsProcessingAdapter(Context context) {
        super(context);
        this.mContext = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        UnclaimedCache holder ;
        ServiceMainExpandTo mode = mList.get(position);
        String mCategorySid = mode.getServiceCategory();
        LayoutInflater  inflater = LayoutInflater.from(mContext);
        if (row == null) {
            row  = inflater.inflate(R.layout.list_processing_item,null);
            holder  = new UnclaimedCache(row);
            row.setTag(holder);
        }else {
            holder = (UnclaimedCache) row.getTag();
        }
        //getResponseCycle 15分钟
        //typeName   类型
        //time  天
        //date  年

        if (!TextUtils.isEmpty(mode.getTypeName())) {
            holder.getmTypeName().setText(mode.getTypeName());
        }
        if (!TextUtils.isEmpty(mode.getWaitingTime())) {
            holder.getmTime().setText(mode.getWaitingTime());
        }
        //getCreatedOn提交时间
        //getResponseTime 相应时间getmTimeResponse
        //getReplyTime  处理时间getmTimeDispose
        if (mode.getCreatedOn() !=null) {
            holder.getmTimes().setText(DateUtil.getDateTimeFormat(DateUtil.mFormatDateShort, mode.getCreatedOn()));
        }
        if (mode.getCreatedOn() !=null) {
            holder.getmDate().setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mode.getCreatedOn()));
        }
        if(mode.getResponseTime()!=null){
            holder.getmTimeResponse().setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mode.getResponseTime()));
        }

        String replyTime;
        if(mode.getReplyTime()==null)
        {
            replyTime=Compare.DateToStr(new Date());// 处理时间
        }
        else {
            replyTime = Compare.DateToStr(mode.getReplyTime());// 处理时间
        }

        //提交时间
        // 赋值
        String date=holder.getmDate().getText().toString();
        String responseTime=holder.getmTimeResponse().getText().toString();
        holder.getmTimeReplyTime().setText(replyTime);      // 处理时间
        holder.getmTimeReplyTime().setVisibility(View.GONE);

        //holder.getmServiceBooking().setText(mode.getServiceBookingTime());//预约时间
        String startTime= SpUtil.getString(mContext, "startTime");
        String timeEnd= SpUtil.getString(mContext, "endTime");

        Date createOnTime=mode.getCreatedOn();
        //System.out.println(mode.getServiceBookingTime());

            if (compare.VaryRed(date, responseTime, startTime, timeEnd, mode.getResponseCycle())){
                holder.getmTimeResponse().setTextColor(0xffcc3434);
            }else{
                holder.getmTimeResponse().setTextColor(0xff808080);
            }

        if ("BCCF6994-9449-4E6D-9F5B-09CE08AD9353".equals(mCategorySid)) {
            if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
            {
                createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                if (compare.VaryRed2(createOnTime,mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                    holder.getmImageView().setBackgroundResource(R.drawable.category_005);
                } else {
                    holder.getmImageView().setBackgroundResource(R.drawable.category_05);
                }
            }else{
                if (compare.VaryRed(date, replyTime,startTime, timeEnd, mode.getProcessCycle())) {
                    holder.getmImageView().setBackgroundResource(R.drawable.category_005);
                } else {
                    holder.getmImageView().setBackgroundResource(R.drawable.category_05);
                }
            }


        }
        if ("C733AA3D-32FA-4F5B-B299-061044661DC0".equals(mCategorySid)){
            if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
            {
                createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                if (compare.VaryRed2(createOnTime,mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                    holder.getmImageView().setBackgroundResource(R.drawable.category_001);
                } else {
                    holder.getmImageView().setBackgroundResource(R.drawable.category_01);
                }
            }else{
                if (compare.VaryRed(date, replyTime,startTime, timeEnd, mode.getProcessCycle())) {
                    holder.getmImageView().setBackgroundResource(R.drawable.category_001);
                } else {
                    holder.getmImageView().setBackgroundResource(R.drawable.category_01);
                }
            }

        }
        if ("5CFB60A1-C1FC-4751-B123-05157F02C70D".equals(mCategorySid)){
            holder.getmImageView().setBackgroundResource(R.drawable.category_10);
        }
        return row;
    }

}
