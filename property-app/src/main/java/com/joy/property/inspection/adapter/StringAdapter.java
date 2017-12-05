package com.joy.property.inspection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

/**
 * Created by usb on 2017/6/19.
 */

public class StringAdapter extends ModeListAdapter<String> {
    private Context mContext ;
    public StringAdapter(Context context) {
        super(context);
        this.mContext = context ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String state = mList.get(position);
        LayoutInflater inflater  = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_type ,null);
        }

        TextView mApartment = (TextView) convertView.findViewById(R.id.type);

        mApartment.setText(state);
        return convertView;
    }
}
