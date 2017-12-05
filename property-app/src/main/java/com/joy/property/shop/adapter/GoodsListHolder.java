package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by xz on 2016/7/12.
 **/
public class GoodsListHolder {
    private View view;
    private GridLayout gridView;

    public GoodsListHolder(View view){
    this.view=view;
}
  public GridLayout getGridView(){
      if(gridView==null)
          gridView= (GridLayout) view.findViewById(R.id.gridView);
      return gridView;
  }

}
