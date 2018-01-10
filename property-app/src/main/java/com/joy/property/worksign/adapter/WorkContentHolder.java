package com.joy.property.worksign.adapter;

import android.view.View;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by xz on 2016/7/22.
 */
public class WorkContentHolder {
    private View view;
    private TextView title;




    public WorkContentHolder(View view) {
        this.view = view;
    }

 public TextView getTitle(){
     if (title==null)
         title= (TextView) view.findViewById(R.id.title);
     return title;
 }

}
