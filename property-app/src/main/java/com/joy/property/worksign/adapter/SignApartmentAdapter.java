package com.joy.property.worksign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinyi.ihome.module.worksign.SignApartmentTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

/**
 * Created by Admin on 2015-02-04
 */
public class SignApartmentAdapter extends ModeListAdapter<SignApartmentTo> {
    private Context  mContext ;
    public SignApartmentAdapter(Context context) {
        super(context);
        this.mContext = context ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SignApartmentTo mode = mList.get(position);
        LayoutInflater inflater  = LayoutInflater.from(mContext);
        if (convertView == null) {
           convertView = inflater.inflate(R.layout.list_item_type ,null);
        }

        TextView mApartment = (TextView) convertView.findViewById(R.id.type);

        mApartment.setText(mode.getTitle());
        return convertView;
    }
}
