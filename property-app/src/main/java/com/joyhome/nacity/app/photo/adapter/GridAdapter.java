package com.joyhome.nacity.app.photo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joy.property.R;
import com.joy.property.neighborhood.PostActivity;

import java.util.ArrayList;

/**
 * Created by usb on 2017/5/12.
 */
public class GridAdapter  extends BaseAdapter {
    private ArrayList<String> listUrls;
    private LayoutInflater    inflater;
    private Context    mContext;
    public GridAdapter(Context mContext,ArrayList<String> listUrls) {
        this.listUrls = listUrls;
        this.mContext = mContext;
        if(listUrls.size() == 5){
            listUrls.remove(listUrls.size()-1);
        }
        inflater = LayoutInflater.from(mContext);
    }

    public int getCount(){
        return  listUrls.size();
    }
    @Override
    public String getItem(int position) {
        return listUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new GridAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_image, parent,false);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        final String path=listUrls.get(position);
        if (path.equals("000000")){
            holder.image.setImageResource(R.drawable.property_add_picture_bg);
        }else {
            Glide.with(mContext.getApplicationContext())
                    .load(path)
                    .placeholder(R.mipmap.default_error)
                    .error(R.mipmap.default_error)
                    .centerCrop()
                    .crossFade()
                    .into(holder.image);
        }
        return convertView;
    }
    class ViewHolder {
        ImageView image;
    }
}
