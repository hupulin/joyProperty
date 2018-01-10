package com.joy.property.worksign.adapter;

import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;
import com.tuyenmonkey.mkloader.MKLoader;


/**
 * Created by xz on 2016/7/22.
 */
public class SignRecordHolder {
    private View view;
    private GridLayout gridView;
    private TextView signTime;
    private TextView signPosition;
    private TextView remarkContent;
    private TextView workContent;
    private LinearLayout parent;
    private RelativeLayout upload;
    private MKLoader uploadLoading;
    private View uploadIcon;




    public SignRecordHolder(View view) {
        this.view = view;
    }

    public GridLayout getGridView(){
        if (gridView==null)
            gridView= (GridLayout) view.findViewById(R.id.grid_view);
        return gridView;
    }
  public TextView getSignTime(){
      if (signTime==null)
          signTime=(TextView) view.findViewById(R.id.sign_time);
      return signTime;
  }
    public TextView getSignPosition(){
        if (signPosition==null)
            signPosition=(TextView) view.findViewById(R.id.sign_position);
        return signPosition;
    }
    public TextView getWorkContent(){
        if (workContent==null)
            workContent=(TextView) view.findViewById(R.id.work_content);
        return workContent;
    }
    public TextView getRemarkContent(){
        if (remarkContent==null)
            remarkContent=(TextView) view.findViewById(R.id.remark_content);
        return remarkContent;
    }

    public LinearLayout getParent(){
        if (parent==null)
            parent=(LinearLayout)view.findViewById(R.id.parent);
        return parent;
    }

    public RelativeLayout getUpload(){
        if (upload==null)
            upload= (RelativeLayout) view.findViewById(R.id.upload);
        return upload;
    }
    public MKLoader getUploadLoading(){
        if (uploadLoading==null)
            uploadLoading= (MKLoader) view.findViewById(R.id.upload_loading);
        return uploadLoading;
    }
    public View getUploadIcon(){
        if (uploadIcon==null)
            uploadIcon=view.findViewById(R.id.upload_cloud_icon);
        return uploadIcon;
    }


}
