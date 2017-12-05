package com.joy.property.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.jinyi.ihome.module.newshop.GoodsListTo;
import com.joyhome.nacity.app.MainApp;
import com.joy.property.R;
import com.joy.property.common.PictureShowActivity;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by xz on 2016/7/12.
 **/
public class OlderDetailAdapter extends ModeListAdapter<GoodsListTo> {
    private Context mContext;

    public OlderDetailAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        OlderDetailHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.older_detail_item, null);
            holder = new OlderDetailHolder(row);
            row.setTag(holder);
        } else {
            holder = (OlderDetailHolder) row.getTag();
        }

        GoodsListTo mode = mList.get(position);
        if (mode != null) {
            holder.getExpressName().setText("包裹"+(position+1));
            holder.getExpressCompany().setText(mode.getExpressCompany());
            holder.getExpressFee().setText(mode.getTraffic()>0?"运费"+mode.getTraffic():"不含运费");
            String[] path = mode.getImages().split(";");
             holder.getGoodsNumber().setText(path.length+"");
            holder.getGridView().removeAllViews();

            for(int i=0;i<path.length&&i<2;i++){
                GridLayout.LayoutParams layoutParams=new GridLayout.LayoutParams();
                int mWidth=getScreenWidth();
                View view=View.inflate(mContext, R.layout.confirm_order_image, null);
                layoutParams.width=(int)(mWidth*0.2430);
                layoutParams.height=(int)(mWidth*0.2430);
                layoutParams.setMargins(0, 0, 10, 0);

                ImageView postImage= (ImageView) view.findViewById(R.id.image);
                Picasso.with(mContext).load(MainApp.getImagePath(path[i])).placeholder(R.drawable.post_content_bg).error(R.drawable.post_content_bg).into(postImage);
                postImage.setTag(R.id.tag_first, i);
                postImage.setTag(R.id.tag_second, mode.getImages());
                view.setLayoutParams(layoutParams);



                postImage.setOnClickListener(v -> {
                    postViewImage((int) v.getTag(R.id.tag_first), (String) v.getTag(R.id.tag_second));
                });

                holder.getGridView().addView(view);
                System.out.println(position+"position");
            }

        }
        return row;
    }
    public void postViewImage(int index, String item) {
        if (TextUtils.isEmpty(item)) {
            return;
        }
        Intent intent = new Intent(mContext, PictureShowActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("path", item);
        mContext.startActivity(intent);
    }
    public int getScreenWidth(){
        WindowManager wm = (WindowManager)mContext.getSystemService(mContext.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }
}
