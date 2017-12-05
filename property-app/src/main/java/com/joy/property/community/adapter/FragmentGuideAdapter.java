package com.joy.property.community.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.guide.ServiceGuideTo;
import com.joy.property.R;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

/**
 * Created by Admin on 2015-01-07
 */
public class FragmentGuideAdapter extends ModeListAdapter<ServiceGuideTo> {

  
    public FragmentGuideAdapter(Context context) {
        super(context);
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row= convertView;
        HomeGuideToCache  holder = null;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row= inflater.inflate(R.layout.list_item_guide, null);
            holder = new HomeGuideToCache(row);
            row.setTag(holder);
        }else {
            holder= (HomeGuideToCache) row.getTag();
        }

        ServiceGuideTo mode = mList.get(position);
        if (!TextUtils.isEmpty(mode.getGuideTitle())) {
            holder.getmGuideTitle().setText(mode.getGuideTitle());
        }
        if ( !TextUtils.isEmpty(mode.getGuideContent())) {
            holder.getmGuideAbstract().setText(mode.getGuideAbstract());
        }
        return row;
    }
}
