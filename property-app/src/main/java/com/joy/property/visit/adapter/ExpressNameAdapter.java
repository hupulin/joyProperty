package com.joy.property.visit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.express.CompanyTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

/**
 * Created by usb on 2017/6/5.
 */

public class ExpressNameAdapter extends ModeListAdapter<CompanyTo> {
        private Context mContext ;
      public ExpressNameAdapter(Context context) {
        super(context);
        this.mContext = context ;
       }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CompanyTo mode = mList.get(position);
        LayoutInflater inflater  = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_type ,null);
        }

        TextView mApartment = (TextView) convertView.findViewById(R.id.type);

        mApartment.setText(mode.getCompanyName());
        return convertView;
    }
    }