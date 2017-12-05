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

/**
 * 待处理
 * Created by Admin on 2015-02-11
 */
public class RepairsUnClaimedAdapter  extends ModeListAdapter<ServiceMainExpandTo> {
    private Context  mContext;

    public RepairsUnClaimedAdapter(Context context) {
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
            row  = inflater.inflate(R.layout.list_unclaimed_item,null);
            holder  = new UnclaimedCache(row);
            row.setTag(holder);
        }else {
            holder = (UnclaimedCache) row.getTag();
        }
        //typeName   类型
        //time  天
        //date  年
//        holder.getmImageView().setBackgroundResource(R.drawable.category_01);
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
            holder.getmDate().setText(DateUtil.getDateTimeFormat(DateUtil.mFormatDateShort, mode.getCreatedOn()));
        }


        if ("BCCF6994-9449-4E6D-9F5B-09CE08AD9353".equals(mCategorySid)) {
            holder.getmImageView().setBackgroundResource(R.drawable.categorys_05);
        }
        if ("C733AA3D-32FA-4F5B-B299-061044661DC0".equals(mCategorySid)){
            holder.getmImageView().setBackgroundResource(R.drawable.categorys_01);
        }
        if ("5CFB60A1-C1FC-4751-B123-05157F02C70D".equals(mCategorySid)){
            holder.getmImageView().setBackgroundResource(R.drawable.category_10);
        }
        return row;
    }
}
