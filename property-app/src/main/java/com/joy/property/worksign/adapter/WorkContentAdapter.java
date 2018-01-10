package com.joy.property.worksign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jinyi.ihome.module.worksign.SignContentTo;
import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

/**
 * Created by xz on 2016/7/12.
 **/
public class WorkContentAdapter extends ModeListAdapter<SignContentTo> {
    private Context mContext;
    private Gson gson;

    public WorkContentAdapter(Context context) {
        super(context);
        this.mContext = context;
        gson=new Gson();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WorkContentHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.sign_work_content_item, null);
            holder = new WorkContentHolder(row);
            row.setTag(holder);
        } else {
            holder = (WorkContentHolder) row.getTag();
        }
        SignContentTo signContentTo = gson.fromJson(gson.toJson(mList.get(position)),SignContentTo.class);
        holder.getTitle().setText(signContentTo.getTitle());

        return row;
    }

}
