package com.joy.property.complaint.fragment.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.utils.Compare;
import com.joy.property.utils.SpUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Admin on 2015-02-10
 */
public class CompleteAdapter extends ModeListAdapter<ServiceMainExpandTo> {

    private Context mContext;
    Compare compare=new Compare();
    public CompleteAdapter(Context context) {
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
            row  = inflater.inflate(R.layout.list_complete_item,null);
            holder  = new UnclaimedCache(row);
            row.setTag(holder);
        }else {
            holder = (UnclaimedCache) row.getTag();
        }
        //typeName   类型
        //time  天
        //date  年
        holder.getmImageView().setBackgroundResource(R.drawable.category_3);
        if (!TextUtils.isEmpty(mode.getTypeName())) {
            holder.getmTypeName().setText(mode.getTypeName());
        }
        if (!TextUtils.isEmpty(mode.getWaitingTime())) {
            holder.getmTime().setText(mode.getWaitingTime());
        }
        if (!TextUtils.isEmpty(mode.getEvaluationItem3Str())){
            holder.getmEvaluaion().setText(mode.getEvaluationItem3Str());
            switch (String.format(mode.getEvaluationItem3Str())) {
                case "待评价":
                    holder.getmEvaluaion().setTextColor(0xff4fb2d6);
                    break;
                case "惊喜" :
                    holder.getmEvaluaion().setTextColor(0xff4fb2d6);
                    break;
                case "满意" :
                    holder.getmEvaluaion().setTextColor(0xff4fb2d6);
                    break;
                case "一般" :
                    holder.getmEvaluaion().setTextColor(0xff4e4e4e);
                    break;
                case "失望" :
                    holder.getmEvaluaion().setTextColor(0xffcc3434);
//                    holder.getmEvalua().setBackgroundResource(R.drawable.warn);
                    break;
                case "不满" :
                    holder.getmEvaluaion().setTextColor(0xffcc3434);
//                    holder.getmEvalua().setBackgroundResource(R.drawable.warn);
                    break;
            }
        }

        if (!TextUtils.isEmpty(mode.getEvaluationItem1Str())){
            holder.getmService().setText(mode.getEvaluationItem1Str());
            switch (String.format(mode.getEvaluationItem1Str())) {
                case "待评价":
                    holder.getmService().setTextColor(0xff4fb2d6);
                    break;
                case "惊喜" :
                    holder.getmService().setTextColor(0xff4fb2d6);
                    break;
                case "满意" :
                    holder.getmService().setTextColor(0xff4fb2d6);
                    break;
                case "一般" :
                    holder.getmService().setTextColor(0xff4e4e4e);
                    break;
                case "失望" :
                    holder.getmService().setTextColor(0xffcc3434);
                    break;
                case "不满" :
                    holder.getmService().setTextColor(0xffcc3434);
                    break;
            }
        }
        if (!TextUtils.isEmpty(mode.getEvaluationItem2Str())){
            holder.getmSolve().setText(mode.getEvaluationItem2Str());
            switch (String.format(mode.getEvaluationItem2Str())) {
                case "待评价":
                    holder.getmSolve().setTextColor(0xff4fb2d6);
                    break;
                case "惊喜" :
                    holder.getmSolve().setTextColor(0xff4fb2d6);
                    break;
                case "满意" :
                    holder.getmSolve().setTextColor(0xff4fb2d6);
                    break;
                case "一般" :
                    holder.getmSolve().setTextColor(0xff4e4e4e);
                    break;
                case "失望" :
                    holder.getmSolve().setTextColor(0xffcc3434);
                    break;
                case "不满" :
                    holder.getmSolve().setTextColor(0xffcc3434);
                    break;
            }
        }

        //   Toast.makeText(mContext,mode.getEndTime()+""+mode.getStartTime(),Toast.LENGTH_SHORT).show();
        //getCreatedOn提交时间
        //getResponseTime 相应时间getmTimeResponse
        //getReplyTime  处理时间getmTimeDispose
        //getResponseCycle 15分钟
        if (mode.getCreatedOn() !=null) {
            holder.getmTimes().setText(DateUtil.getDateTimeFormat(DateUtil.mFormatDateShort, mode.getCreatedOn()));
        }
        if (mode.getCreatedOn() !=null) {
            holder.getmDate().setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mode.getCreatedOn()));
        }
        if(mode.getResponseTime()!=null){
            holder.getmTimeResponse().setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mode.getResponseTime()));
        }
        if (mode.getReplyTime()!=null){
            holder.getmTimeDispose().setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mode.getReplyTime()));

        }


        String create=holder.getmDate().getText().toString();
        String esponse=holder.getmTimeResponse().getText().toString();
        String dispose=holder.getmTimeDispose().getText().toString();
        String startTime= SpUtil.getString(mContext, "startTime");
        String timeEnd= SpUtil.getString(mContext, "endTime");

       // holder.getmServiceBooking().setText( mode.getServiceBookingTime());//预约时间
        if (compare.VaryRed( create, esponse, startTime, timeEnd, mode.getResponseCycle())) {
            holder.getmTimeResponse().setTextColor(0xffcc3434);

        }else{
            holder.getmTimeResponse().setTextColor(0xff808080);

        }

        Date createOnTime=mode.getCreatedOn();
        if (mode.getServiceBookingTime()!=null && StringUtils.isNotEmpty(mode.getServiceBookingTime()))
        {
            createOnTime=compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
            //处理超时 超时大于24小时

            if (compare.VaryRed2(createOnTime, mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                holder.getmTimeDispose().setTextColor(0xffcc3434);
            } else {
                holder.getmTimeDispose().setTextColor(0xff808080);
            }
        }else {

                //处理超时 超时大于24小时
                if (compare.VaryRed2(createOnTime, mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                    holder.getmTimeDispose().setTextColor(0xffcc3434);
                } else {
                    holder.getmTimeDispose().setTextColor(0xff808080);
                }
        }
        if ("7D2B996C-12EC-4CD4-8499-B453E96AF11F".equals(mCategorySid)) {
            holder.getmImageView().setBackgroundResource(R.drawable.categorys_3);
        }
        if ("9098ED29-072D-4653-A37D-3C2F6DF80861".equals(mCategorySid)){
            holder.getmImageView().setBackgroundResource(R.drawable.categorys_2);
        }
        if ("51979B62-10E6-44C7-88B9-4B239B1CE02F".equals(mCategorySid)){
            holder.getmImageView().setBackgroundResource(R.drawable.categorys_4);
        }
        return row;
    }





}
