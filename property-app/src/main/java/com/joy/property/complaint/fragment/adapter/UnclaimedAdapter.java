package com.joy.property.complaint.fragment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

/**
 * Created by Admin on 2015-02-10
 */
public class UnclaimedAdapter extends ModeListAdapter<ServiceMainExpandTo> {

    private Context mContext;

    public UnclaimedAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        UnclaimedCache holder;
        ServiceMainExpandTo  mode = mList.get(position);
        String mCategorySid = mode.getServiceCategory();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (row == null) {
            row = inflater.inflate(R.layout.list_unclaimed_item, null);
            holder = new UnclaimedCache(row);
            row.setTag(holder);
        } else {
            holder = (UnclaimedCache) row.getTag();
        }


        if (!TextUtils.isEmpty(mode.getTypeName())) {
            holder.getmTypeName().setText(mode.getTypeName());
        }

        if (!TextUtils.isEmpty(mode.getWaitingTime())) {
            holder.getmTime().setText(mode.getWaitingTime());
        }
        //提交时间getCreatedOn
        if (mode.getCreatedOn() !=null) {
            holder.getmDate().setText(DateUtil.getDateTimeFormat(DateUtil.mDateFormatString, mode.getCreatedOn()));
        }

        //投诉 7D2B996C-12EC-4CD4-8499-B453E96AF11F
        // 家政服务 9098ED29-072D-4653-A37D-3C2F6DF80861

        if ("7D2B996C-12EC-4CD4-8499-B453E96AF11F ".equals(mCategorySid)) {
            holder.getmImageView().setBackgroundResource(R.drawable.categorys_03);
        }
        if ("9098ED29-072D-4653-A37D-3C2F6DF80861".equals(mCategorySid)){
            holder.getmImageView().setBackgroundResource(R.drawable.categorys_02);
        }
        if ("51979B62-10E6-44C7-88B9-4B239B1CE02F".equals(mCategorySid)){
            holder.getmImageView().setBackgroundResource(R.drawable.categorys_04);
        }
        return row;
    }
}
