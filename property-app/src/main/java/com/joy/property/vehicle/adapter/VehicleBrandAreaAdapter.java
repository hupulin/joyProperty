package com.joy.property.vehicle.adapter;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Admin on 2015-03-26
 */
public class VehicleBrandAreaAdapter extends ModeListAdapter<String[][]> implements
        StickyListHeadersAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private IBrandItemTo brandItemTo;
    public VehicleBrandAreaAdapter(Context context) {
        super(context);
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);


    }

    public IBrandItemTo getBrandItemTo() {
        return brandItemTo;
    }

    public void setBrandItemTo(IBrandItemTo brandItemTo) {
        this.brandItemTo = brandItemTo;
    }


    private int[] getSectionIndices() {
        List<Integer> sectionIndices = new ArrayList<>();
        sectionIndices.add(0);
        for (int i = 1; i < 31; i++) {
         sectionIndices.add(i);

        }
        int[] sections = new int[sectionIndices.size()];
        for (int i = 0; i < sectionIndices.size(); i++) {
            sections[i] = sectionIndices.get(i);
        }
        return sections;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_child, null);
        }
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , 100);
        params.topMargin = 2;
        String[] str = mList.get(position)[1];

        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < str.length; i++) {
            TextView text = new TextView(mContext);
            text.setText(str[i]);
            text.setTextSize(18);
            text.setPadding(150, 0, 0, 0);
            text.setGravity(Gravity.CENTER_VERTICAL);
            text.setTag(str[i]);
            text.setBackgroundResource(R.drawable.selector_list_item_bg);
            linearLayout.addView(text, params);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (brandItemTo == null) return;
                    brandItemTo.brandLetter(mList.get(position)[0][0], (String) v.getTag()) ;
                }
            });
        }
        return convertView;
    }


    @Override
    public View getHeaderView(int action, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_text, null);
        }
        TextView mBrand = (TextView) view.findViewById(R.id.brand);
        mBrand.setText(mList.get(action)[0][0]);
        return view;
    }

    @Override
    public long getHeaderId(int action) {
        return action;
    }


    public  interface IBrandItemTo {
        void brandLetter(String brand , String letter);
    }
}
