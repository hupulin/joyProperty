package com.joy.property.visit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.express.ExpressNewTo;
import com.jinyi.ihome.module.express.ExpressRecordTo;
import com.jinyi.ihome.module.express.ExpressToNew;
import com.jinyi.ihome.module.system.GroupMenuTo;
import com.joy.property.R;
import com.joy.property.adapter.GroupMenuCache;
import com.joy.property.common.adapter.ModeListAdapter;

/**
 * Created by usb on 2017/8/2.
 */

public class ExpressGridViewAdapter extends ModeListAdapter<ExpressNewTo> {


    public ExpressGridViewAdapter(Context context) {
        super(context);
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ExpressGridViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (row == null) {

            row = inflater.inflate(R.layout.item_express_grid, null);
            holder = new ExpressGridViewHolder(row);
            row.setTag(holder);

        } else {
            holder = (ExpressGridViewHolder) row.getTag();

        }
        ExpressNewTo mode = mList.get(position);

        if(mode.getExpressCompany()!=null){
            holder.getApartmentName().setText(mode.getApartmentName());

        } if(mode.getExpressNo()!=null){
            holder.getExpressNo().setText(mode.getExpressNo());
        }
        if(mode.getExpressCompany()!=null){
            holder.getExpressName().setText(mode.getExpressCompany());
        }

        return row;
    }
}