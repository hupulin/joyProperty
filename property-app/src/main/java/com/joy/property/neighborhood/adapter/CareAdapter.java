package com.joy.property.neighborhood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinyi.ihome.module.neighbor.OwnerMessageTo;

import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by xz on 2016/5/23.
 **/
public class CareAdapter extends ModeListAdapter<OwnerMessageTo> {
    private Context mContext;
    public CareAdapter(Context context) {
        super(context);
        this.mContext = context;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        CareHolder holder;


    if (row == null) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(R.layout.care_item, null);
        holder = new CareHolder(row);
        row.setTag(holder);
    } else {
        holder = (CareHolder) row.getTag();
    }

        OwnerMessageTo mode=mList.get(position);
        if (mode!=null) {
            holder.getCareName().setText(mode.getFamilyName());
            Picasso.with(mContext).load(MainApp.getPicassoImagePath("my_family_icon2.png".equals(mode.getOwnerImage())?"kong":mode.getOwnerImage())).error(R.drawable.head_image).into(holder.getHeadImage());
if (mode.getFlag()==0)
    holder.getCareIcon().setVisibility(View.GONE);
            else
    holder.getCareIcon().setVisibility(View.VISIBLE);
            holder.getCareStatus().setOnClickListener(v -> {
                if (listener!=null)
                    listener.OnCancelCare(mode);
            });
        }
if (listenerPoint!=null)
    listenerPoint.onShowPoint(holder.getJoinPoint(),mode);
    return row;
}

    private OnCancelCareListener listener;
    public void setOnCancelCareListener(OnCancelCareListener listener){
        this.listener=listener;
    }
    public interface OnCancelCareListener{
        void OnCancelCare(OwnerMessageTo messageTo);

    }
    private showPointListener listenerPoint;
    public void setshowPointListener(showPointListener listener){
        this.listenerPoint=listener;
    }
    public interface showPointListener{
        void onShowPoint(TextView showPoint, OwnerMessageTo messageTo);

    }
}
