package com.joy.property.neighborhood.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by xz on 2016/5/23.
 */
public class InteractItemHolder {
    private View view;
    private TextView content;
    private TextView time;
    private TextView interactState;
    private TextView theme;
    private TextView joinNumber;
    private TextView lookNumber;
    private LinearLayout interactItemLayout;
    private ImageView contentImage;
    private LinearLayout joinLayout;
    public InteractItemHolder(View view) {
        this.view = view;
    }
    public TextView getContent(){
        if (content==null)
            content= (TextView) view.findViewById(R.id.content);
        return content;
    }
    public TextView getTime(){
        if (time==null)
            time= (TextView) view.findViewById(R.id.time);
        return time;
    }
    public TextView getInteractState(){
        if (interactState==null)
            interactState= (TextView) view.findViewById(R.id.interactState);
        return interactState;
    }
    public TextView getTheme(){
        if (theme==null)
            theme= (TextView) view.findViewById(R.id.theme);
        return theme;
    }
    public TextView getJoinNumber(){
        if (joinNumber==null)
            joinNumber= (TextView) view.findViewById(R.id.joinNumber);
        return joinNumber;
    }
    public TextView getLookNumber(){
        if (lookNumber==null)
            lookNumber= (TextView) view.findViewById(R.id.lookNumber);
        return lookNumber;
    }
    public ImageView getContentImage(){
        if (contentImage==null)
            contentImage= (ImageView) view.findViewById(R.id.contentImage);
        return contentImage;
    }
    public LinearLayout getInteractItemLayout(){
        if (interactItemLayout==null)
            interactItemLayout= (LinearLayout) view.findViewById(R.id.interactItemLayout);
        return interactItemLayout;
    }
    public LinearLayout getJoinLayout(){
        if (joinLayout==null)
            joinLayout= (LinearLayout) view.findViewById(R.id.joinLayout);
        return joinLayout;
    }
}
