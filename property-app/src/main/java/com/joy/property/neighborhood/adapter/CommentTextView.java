package com.joy.property.neighborhood.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.joy.property.R;


/**
 * Created by xzz on 2016/3/2.
 **/
public class CommentTextView extends LinearLayout {


    public CommentTextView(Context context) {
        this(context, null);
    }

    public CommentTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        View.inflate(context, R.layout.neighbor_commnet_item,this);
    }






}

