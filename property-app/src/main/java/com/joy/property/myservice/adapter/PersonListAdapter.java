package com.joy.property.myservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joy.property.common.adapter.ModeListAdapter;
import com.joy.property.R;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;

/**
 * Created by Admin on 2015-02-04
 */
public class PersonListAdapter extends ModeListAdapter<ApartmentInfoTo> {
    private Context  mContext ;
    public PersonListAdapter(Context context) {
        super(context);
        this.mContext = context ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApartmentInfoTo mode = mList.get(position);
        LayoutInflater inflater  = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_person ,null);
        }

        TextView mApartment = (TextView) convertView.findViewById(R.id.type);

        mApartment.setText(mode.getApartmentName());
        return convertView;
    }
}
