package com.joy.property.neighborhood.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joy.property.R;


/**
 * Created by xz on 2016/5/23.
 */
public class NeighborJoinHolder {
    private View view;
    private TextView joinTime;
    private TextView joinName;
private TextView commentedName;
private TextView commentContent;

    private ImageView headImage;
    private ImageView verified;
    public NeighborJoinHolder(View view) {
        this.view = view;
    }



    public TextView getJoinTime(){
        if (joinTime==null)
            joinTime= (TextView) view.findViewById(R.id.joinTime);
        return joinTime;
    }
    public TextView getJoinName(){
        if (joinName==null)
            joinName= (TextView) view.findViewById(R.id.joinName);
        return joinName;
    }
    public TextView getCommentedName(){
        if (commentedName==null)
            commentedName= (TextView) view.findViewById(R.id.commentedName);
        return commentedName;
    }
    public TextView getCommentContent(){
        if (commentContent==null)
            commentContent= (TextView) view.findViewById(R.id.commentContent);
        return commentContent;
    }
    public ImageView getHeadImage(){
        if (headImage==null)
            headImage= (ImageView) view.findViewById(R.id.headImage);
        return headImage;
    }
    public ImageView getVerified(){
        if (verified==null)
            verified= (ImageView) view.findViewById(R.id.verified);
        return verified;
    }
}
