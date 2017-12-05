package com.joy.property.vehicle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

import java.util.Map;

/**
 * Created by Admin on 2015-04-08
 */
public class ContactAdapter extends ModeListAdapter<Map<String,String>> {

    private LayoutInflater inflater;
    public ContactAdapter(Context context) {
        super(context);
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ContactCache  holder;
        if (row == null) {
           row = inflater.inflate(R.layout.list_item_contact,null);
            holder =  new ContactCache(row);
            row.setTag(holder);
        }else {
            holder = (ContactCache) row.getTag();
        }

        Map<String,String> map = mList.get(position);

        holder.getmName().setText(map.get("name"));

        holder.getmPhone().setText(map.get("phone"));

        return row;
    }
}
