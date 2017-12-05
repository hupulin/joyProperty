package com.joy.property.inspection.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;
import com.jinyi.ihome.module.home.ServiceTypeTo;

/**
 * Created by Admin on 2015-02-04
 */
public class TypeAdapter extends ModeListAdapter<ServiceTypeTo> {

    public TypeAdapter(Context context) {
        super(context);
        this.mContext = context ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ServiceTypeTo mode = mList.get(position);
        LayoutInflater  inflater  = LayoutInflater.from(mContext);
        if (convertView== null) {
            convertView = inflater.inflate(R.layout.list_item_type, null);
        }
        TextView  mType = (TextView) convertView.findViewById(R.id.type);

        if (!TextUtils.isEmpty(mode.getTypeName())) {
         mType.setText(mode.getTypeName());
        }
        return convertView;
    }
}
