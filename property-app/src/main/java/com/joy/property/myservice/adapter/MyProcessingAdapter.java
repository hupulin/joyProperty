package com.joy.property.myservice.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.myservice.adapter.CommonProcessHolder;
import com.joy.property.utils.Compare;
import com.joy.property.utils.SpUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Admin on 2015-02-10
 */
public class MyProcessingAdapter extends ModeListAdapter<ServiceMainExpandTo> {

    private Context mContext;
    Compare compare=new Compare();
    public MyProcessingAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        CommonProcessHolder holder ;
        ServiceMainExpandTo mode = mList.get(position);
        Log.i("22222", "mode: "+mode.toString());
        String mCategorySid = mode.getServiceCategory();
        LayoutInflater  inflater = LayoutInflater.from(mContext);
        if (row == null) {
            row  = inflater.inflate(R.layout.my_list_complete_item,null);
            holder  = new CommonProcessHolder(row);
            row.setTag(holder);
        }else {
            holder = (CommonProcessHolder) row.getTag();
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
        if (!TextUtils.isEmpty(mode.getApartmentName())) {
            holder.getApartment().setText(mode.getApartmentName());
        }
        // Toast.makeText(mContext, SpUtil.getString(mContext,"startTime")+ "" + SpUtil.getString(mContext, "endTime"), Toast.LENGTH_SHORT).show();
        //getCreatedOn提交时间
        //getResponseTime 相应时间getmTimeResponse
        //getReplyTime  处理时间getmTimeDispose
        if (!TextUtils.isEmpty(mode.getServiceStatus())) {
            switch (Integer.parseInt(mode.getServiceStatus())) {

                case 2:
          
                    holder.getmTimes().setText("已指派");
              
            
                    break;
                case 3:
        
                    holder.getmTimes().setText("已撤消");
           

                    break;
                case 4:
             
                    holder.getmTimes().setText("待评价");
              
                    break;
                case 6:
               
                    holder.getmTimes().setText("已结束");
                
                    break;
                case 22:
               
                    holder.getmTimes().setText("处理中");
                   
             
                    break;
                case 21:
              
                    holder.getmTimes().setText("处理中");
                  
                    break;
                case 9:
                
                    holder.getmTimes().setText("已结束");
                 
                    //  getCompare();
                    break;
                case 20:
               
                    holder.getmTimes().setText("已指派");
                  
                  
                    break;
            }
        }

        if (mode.getCreatedOn() !=null) {

            holder.getTextView2().setVisibility(View.VISIBLE);
            holder.getmTimeResponse().setVisibility(View.VISIBLE);
            holder.getmDate().setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mode.getCreatedOn()));
        }
        if(mode.getResponseTime()!=null){
            holder.getmTimeResponse().setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString, mode.getResponseTime()));
        }else{

                holder.getTextView2().setVisibility(View.GONE);
                holder.getmTimeResponse().setVisibility(View.GONE);

        }
        String replyTime;
        if(mode.getReplyTime()==null)
        {
            replyTime=Compare.DateToStr(new Date());// 处理时间
        }
        else {
            replyTime = Compare.DateToStr(mode.getReplyTime());// 处理时间
        }
        String date=holder.getmDate().getText().toString();
       // 处理时间
        //holder.getmServiceBooking().setText(mode.getServiceBookingTime());//预约时间
        holder.getmTimeReplyTime().setVisibility(View.GONE);
        String startTime= SpUtil.getString(mContext, "startTime");
        String timeEnd= SpUtil.getString(mContext, "endTime");
        Date createOnTime=mode.getCreatedOn();
        //System.out.println(mode.getServiceBookingTime());




        if ("7D2B996C-12EC-4CD4-8499-B453E96AF11F".equals(mCategorySid)) {
             if ("4".equals(mode.getServiceStatus())||"6".equals(mode.getServiceStatus())||"9".equals(mode.getServiceStatus())){
                 holder.getmImageView().setBackgroundResource(R.drawable.category_3);
             }else {
                 if (mode.getServiceBookingTime() != null && StringUtils.isNotEmpty(mode.getServiceBookingTime())) {
                     createOnTime = compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                     if (compare.VaryRed2(createOnTime, mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                         holder.getmImageView().setBackgroundResource(R.drawable.category_003);
                     } else {
                         holder.getmImageView().setBackgroundResource(R.drawable.category_03);
                     }
                 } else {
                     if (compare.VaryRed(date, replyTime, startTime, timeEnd, mode.getProcessCycle())) {
                         holder.getmImageView().setBackgroundResource(R.drawable.category_003);
                     } else {
                         holder.getmImageView().setBackgroundResource(R.drawable.category_03);
                     }
                 }
             }
        }
        if ("9098ED29-072D-4653-A37D-3C2F6DF80861".equals(mCategorySid)){
            if ("4".equals(mode.getServiceStatus())||"6".equals(mode.getServiceStatus())||"9".equals(mode.getServiceStatus())){
                holder.getmImageView().setBackgroundResource(R.drawable.category_2);
            }else {
                if (mode.getServiceBookingTime() != null && StringUtils.isNotEmpty(mode.getServiceBookingTime())) {
                    createOnTime = compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                    if (compare.VaryRed2(createOnTime, mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_002);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_02);
                    }
                } else {
                    if (compare.VaryRed(date, replyTime, startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_002);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_02);
                    }
                }
            }
        }
        if ("51979B62-10E6-44C7-88B9-4B239B1CE02F".equals(mCategorySid)){
            if ("4".equals(mode.getServiceStatus())||"6".equals(mode.getServiceStatus())||"9".equals(mode.getServiceStatus())){
                holder.getmImageView().setBackgroundResource(R.drawable.category_4);
            }else {
                if (mode.getServiceBookingTime() != null && StringUtils.isNotEmpty(mode.getServiceBookingTime())) {
                    createOnTime = compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                    if (compare.VaryRed2(createOnTime, mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_004);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_04);
                    }
                } else {
                    if (compare.VaryRed(date, replyTime, startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_004);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_04);
                    }
                }
            }
        }
        if ("C733AA3D-32FA-4F5B-B299-061044661DC0".equals(mCategorySid)){
            if ("4".equals(mode.getServiceStatus())||"6".equals(mode.getServiceStatus())||"9".equals(mode.getServiceStatus())){
                holder.getmImageView().setBackgroundResource(R.drawable.category_1);
            }else {
                if (mode.getServiceBookingTime() != null && StringUtils.isNotEmpty(mode.getServiceBookingTime())) {
                    createOnTime = compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                    if (compare.VaryRed2(createOnTime, mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_001);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_01);
                    }
                } else {
                    if (compare.VaryRed(date, replyTime, startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_001);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_01);
                    }
                }
            }
        }
        if ("BCCF6994-9449-4E6D-9F5B-09CE08AD9353".equals(mCategorySid)){
            if ("4".equals(mode.getServiceStatus())||"6".equals(mode.getServiceStatus())||"9".equals(mode.getServiceStatus())){
                holder.getmImageView().setBackgroundResource(R.drawable.category_5);
            }else {
                if (mode.getServiceBookingTime() != null && StringUtils.isNotEmpty(mode.getServiceBookingTime())) {
                    createOnTime = compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                    if (compare.VaryRed2(createOnTime, mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_005);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_05);
                    }
                } else {
                    if (compare.VaryRed(date, replyTime, startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_005);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_05);
                    }
                }
            }
        }
        if ("51979B62-10E6-44C7-88B8-4B239B1CE03F".equals(mCategorySid)){
            if ("4".equals(mode.getServiceStatus())||"6".equals(mode.getServiceStatus())||"9".equals(mode.getServiceStatus())){
                holder.getmImageView().setBackgroundResource(R.drawable.category_6);
            }else {
                if (mode.getServiceBookingTime() != null && StringUtils.isNotEmpty(mode.getServiceBookingTime())) {
                    createOnTime = compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                    if (compare.VaryRed2(createOnTime, mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_006);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_06);
                    }
                } else {
                    if (compare.VaryRed(date, replyTime, startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_006);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_06);
                    }
                }
            }
        }
        if ("5CFB60A1-C1FC-4751-B123-05157F02C70D".equals(mCategorySid)){
            if ("4".equals(mode.getServiceStatus())||"6".equals(mode.getServiceStatus())||"9".equals(mode.getServiceStatus())){
                holder.getmImageView().setBackgroundResource(R.drawable.category_10_finish);
            }else {
                if (mode.getServiceBookingTime() != null && StringUtils.isNotEmpty(mode.getServiceBookingTime())) {
                    createOnTime = compare.BookingStrToDate(mode.getServiceBookingTime(), mode.getCreatedOn());
                    if (compare.VaryRed2(createOnTime, mode.getReplyTime(), startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_10);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_10);
                    }
                } else {
                    if (compare.VaryRed(date, replyTime, startTime, timeEnd, mode.getProcessCycle())) {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_10);
                    } else {
                        holder.getmImageView().setBackgroundResource(R.drawable.category_10);
                    }
                }
            }
        }
           if ("1".equals(mode.getServiceStatus())){
               holder.getProcessText().setVisibility(View.GONE);
               holder.getComplementPart().setVisibility(View.GONE);
               holder.getmTimeDispose().setVisibility(View.GONE);

           }else if ("4".equals(mode.getServiceStatus())||"6".equals(mode.getServiceStatus())||"9".equals(mode.getServiceStatus())){
               holder.getProcessText().setVisibility(View.VISIBLE);
               holder.getComplementPart().setVisibility(View.GONE);
               holder.getmTimeDispose().setVisibility(View.VISIBLE);
               String responseTime=holder.getmTimeResponse().getText().toString();
               holder.getmTimeReplyTime().setText(replyTime);
               if (compare.VaryRed(date, responseTime, startTime, timeEnd, mode.getResponseCycle())){
                   holder.getmTimeResponse().setTextColor(0xffcc3434);
               }else{
                   holder.getmTimeResponse().setTextColor(0xff808080);
               }


               

           }else if ("2".equals(mode.getServiceStatus())||"20".equals(mode.getServiceStatus())||"21".equals(mode.getServiceStatus())||"22".equals(mode.getServiceStatus())){

               holder.getProcessText().setVisibility(View.VISIBLE);
               holder.getComplementPart().setVisibility(View.GONE);
               holder.getmTimeDispose().setVisibility(View.VISIBLE);
               String responseTime=holder.getmTimeResponse().getText().toString();
               holder.getmTimeReplyTime().setText(replyTime);
               if (compare.VaryRed(date, responseTime, startTime, timeEnd, mode.getResponseCycle())){
                   holder.getmTimeResponse().setTextColor(0xffcc3434);
               }else{
                   holder.getmTimeResponse().setTextColor(0xff808080);
               }

           }
        if (mode.getReplyTime()==null) {
            holder.getProcessText().setVisibility(View.GONE);
            holder.getmTimeDispose().setVisibility(View.GONE);
        }
        else {
            holder.getProcessText().setVisibility(View.VISIBLE);
            holder.getmTimeDispose().setVisibility(View.VISIBLE);
        }
        return row;
    }

}
