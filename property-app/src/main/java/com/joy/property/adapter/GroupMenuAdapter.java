package com.joy.property.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jinyi.ihome.module.system.GroupMenuTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;


/**
 * Created by Admin on 2015-08-10
 */
public class GroupMenuAdapter extends ModeListAdapter<GroupMenuTo> {


    public GroupMenuAdapter(Context context) {
        super(context);
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        GroupMenuCache holder = null;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (row == null) {

            row = inflater.inflate(R.layout.list_group_menu_items, null);
            holder = new GroupMenuCache(row);
            row.setTag(holder);

        } else {
            holder = (GroupMenuCache) row.getTag();

        }
        GroupMenuTo menuTo = mList.get(position);
        holder.getmTextName().setText(menuTo.getName());
        if("敬请期待".equals(menuTo.getName())){
            holder.getmImageView().setBackgroundResource(R.drawable.coming_soon);

        }else{
            displayImage(holder.getmImageView(), "main_android_" + menuTo.getIcon() + ".png");
        }

        holder.getLayout().setLayoutParams(getLayoutParam());

        return row;
    }

    public RelativeLayout.LayoutParams getLayoutParam() {

        int mScreenWidth = getScreenWidthPixels(mContext);
        int mWidth = (mScreenWidth - 4) / 4;
        int mHeight = (int) (mWidth * 0.8666);
        return new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight);
    }


}
