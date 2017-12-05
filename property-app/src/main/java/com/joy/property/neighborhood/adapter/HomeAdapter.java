package com.joy.property.neighborhood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinyi.ihome.module.neighbor.OwnerMessageTo;

import com.joy.property.R;
import com.joyhome.nacity.app.MainApp;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xz on 2016/11/10.
 */
public class HomeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<OwnerMessageTo>toList;
public HomeAdapter(Context context,List<OwnerMessageTo>toList){
    this.context=context;
    this.toList=toList;
}
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.campaign_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        viewHolder.tv.setText(toList .get(position).getFamilyName());
        Picasso.with(context).load(MainApp.getPicassoImagePath(toList.get(position).getOwnerImage())).into(viewHolder.imageView);
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }

    }



    @Override
    public int getItemCount()
    {
        return toList==null?0:toList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv;
        ImageView imageView;
        View view;
        public MyViewHolder(View view)
        {
            super(view);
            tv= (TextView) view.findViewById(R.id.headName);
            imageView= (ImageView) view.findViewById(R.id.headImage);
        }

    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
