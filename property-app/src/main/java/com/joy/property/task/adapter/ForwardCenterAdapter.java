package com.joy.property.task.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.jinyi.ihome.module.owner.UserInfoTo;


/**
 * Created by Admin on 2015-01-27.
 */
public class ForwardCenterAdapter extends ModeListAdapter<UserInfoTo> {
    private Context mContext;
    public ForwardCenterAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View row = convertView;
        ForwardCenterCache holder ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (row == null) {
            row = inflater.inflate(R.layout.list_item_forwardcenter,null);
            holder = new ForwardCenterCache(row);
            row.setTag(holder);
        }else {
            holder = (ForwardCenterCache) row.getTag();
        }
        UserInfoTo mode = mList.get(position);
        if  (!TextUtils.isEmpty(mode.getName())) {
            holder.getmTypeName().setText(mode.getName());
        }

        if (!TextUtils.isEmpty(mode.getPhone())) {
            holder.getmPhone().setText(mode.getPhone());
        }
        displayImage(holder.getmHeadImage(),mode.getImage(),R.drawable.head_image);
        return row;
    }
}
