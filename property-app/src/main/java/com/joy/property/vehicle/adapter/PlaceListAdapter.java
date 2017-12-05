package com.joy.property.vehicle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

/**
 * Created by usb on 2017/2/28.
 */
public class PlaceListAdapter extends ModeListAdapter<String>

    {
        private Context mContext ;
        public PlaceListAdapter(Context context) {
        super(context);
        this.mContext = context ;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater  = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_place ,null);
        }

        TextView mApartment = (TextView) convertView.findViewById(R.id.type);
        mApartment.setText(mList.get(position));
        return convertView;
    }
    }