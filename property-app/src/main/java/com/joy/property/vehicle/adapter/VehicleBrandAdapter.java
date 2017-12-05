package com.joy.property.vehicle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

/**
 * Created by Admin on 2015-03-27
 */
public class VehicleBrandAdapter extends ModeListAdapter<String> {

    private LayoutInflater inflater;

    public VehicleBrandAdapter(Context context) {
        super(context);
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_text, null);
        }
        convertView.setBackgroundResource(R.drawable.selector_list_item_bg);
        TextView textView = (TextView) convertView.findViewById(R.id.brand);
        textView.setText(mList.get(position));

        return convertView;
    }
}
