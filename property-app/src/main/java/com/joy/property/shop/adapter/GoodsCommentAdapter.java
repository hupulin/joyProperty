package com.joy.property.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.jinyi.ihome.module.newshop.GoodsCommentTo;
import com.joyhome.nacity.app.MainApp;
import com.joy.property.R;
import com.joy.property.common.PictureShowActivity;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;
import com.joyhome.nacity.app.util.htmlText.HtmlTextView;
import com.squareup.picasso.Picasso;

/**
 * Created by xz on 2016/5/23.
 **/
public class GoodsCommentAdapter extends ModeListAdapter<GoodsCommentTo> {
    private Context mContext;
    GoodsCommentHolder holder;
    private HtmlTextView htmlText;

    public GoodsCommentAdapter(Context context) {
        super(context);
        this.mContext = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;


        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.good_comment_item, null);
            holder = new GoodsCommentHolder(row);
            row.setTag(holder);
        } else {
            holder = (GoodsCommentHolder) row.getTag();
        }
        htmlText = (HtmlTextView) row.findViewById(R.id.htmlText);
        GoodsCommentTo mode=mList.get(position);
        if (mode!=null) {
            if (TextUtils.isEmpty(mode.getGoodsDetailsStr())) {
                holder.getCommenterName().setText(mode.getNickName());
                holder.getCommentContent().setText(mode.getEvaluateContent());
                holder.getCommentResult().setText(mode.getEvaluateResult());
                holder.getCommentTime().setText(mode.getEvaluateTime());
                if (mode.getHeadUrl()==null)
                Picasso.with(mContext).load(MainApp.getPicassoImagePath(mode.getHeadUrl())).into(holder.getHeadImage());
else
                    Picasso.with(mContext).load(mode.getHeadUrl().startsWith("http")?mode.getHeadUrl():MainApp.getPicassoImagePath(mode.getHeadUrl())).into(holder.getHeadImage());
//                holder.getHtmlText().setVisibility(View.GONE);
//                holder.getCommentLayout().setVisibility(View.VISIBLE);
            } else {
//                holder.getHtmlText().setVisibility(View.VISIBLE);
//                holder.getCommentLayout().setVisibility(View.GONE);
//                holder.getHtmlText().setHtmlFromString(mode.getGoodsDetailsStr(), false);

            }

            htmlText.setVisibility(View.GONE);

        }
        return row;
    }
    public int getScreenWidth(){
        WindowManager wm = (WindowManager)mContext.getSystemService(mContext.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
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
    public void setNull(){

    }
}
