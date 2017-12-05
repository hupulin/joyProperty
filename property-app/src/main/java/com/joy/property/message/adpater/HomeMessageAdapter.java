package com.joy.property.message.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.jinyi.ihome.module.owner.UserMessageTo;
import com.joy.library.utils.DateUtil;

/**
 * Created by Admin on 2015-01-26
 */
public class HomeMessageAdapter extends ModeListAdapter<UserMessageTo> {
    private Context mContext;
    public HomeMessageAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HomeMessageCache holder = null;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.list_item_message, null);
            holder = new HomeMessageCache(row);
            row.setTag(holder);
        } else {
            holder = (HomeMessageCache) row.getTag();
        }
        UserMessageTo mode = mList.get(position);
        holder.getmContent().setText(mode.getMsgContent());
        holder.getmTime().setText(DateUtil.getDateTimeFormat(DateUtil.mDateTimeFormatString,mode.getMsgTime()));
        return row;
    }
}
