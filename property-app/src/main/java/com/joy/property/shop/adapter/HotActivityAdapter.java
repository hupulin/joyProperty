package com.joy.property.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jinyi.ihome.module.newshop.ActivityGoodsVoListTo;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.utils.flowlayout.FlowLayout;
import com.joy.property.utils.flowlayout.TagAdapter;
import com.joy.property.utils.flowlayout.TagFlowLayout;
import com.joyhome.nacity.app.MainApp;
import com.joyhome.nacity.app.common.adapter.ModeListAdapter;

import java.util.List;

import rx.Observable;

//活动的adapter
public class HotActivityAdapter extends ModeListAdapter<ActivityGoodsVoListTo> {
    private Context mContext;
     private boolean isHotActivity;
     private ActivityGoodsVoListTo.ActivityGoodsSpecificationVOBean  specificationTo=new ActivityGoodsVoListTo.ActivityGoodsSpecificationVOBean();

     public HotActivityAdapter(Context context,boolean isHotActivity) {
        super(context);
        this.mContext = context;
        this.isHotActivity=isHotActivity;
    }

            @Override
            public int getCount() {
                return  mList==null?0:isHotActivity?mList.size():mList.size()>=8?8:mList.size();
            }

            @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HotActivityHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.list_activity_shop, null);
            holder = new HotActivityHolder(row);
            row.setTag(holder);
        } else {
            holder = (HotActivityHolder) row.getTag();
        }

                ActivityGoodsVoListTo mode = mList.get(position);

        if (mode != null) {

            Glide.with(MainApplication.mContext).load(MainApp.getPicassoImagePath(mode.getActivityGoodsPicVO().get(0).getUrl())).error(R.drawable.activity_goods_load).into(holder.getGoodsImage());
            holder.getGoodsName().setText(mode.getActivityGoodsName());
            holder.getMarketPrice().setText(mode.getOriginalPriceName()+" ¥ "+getMinPrice(mode.getActivityGoodsSpecificationVO()).getOriginalPrice());
            holder.getGoodsPrice().setText(getMinPrice(mode.getActivityGoodsSpecificationVO()).getCurrentPrice()+"");
            holder.getCurrentPriceName().setText(mode.getCurrentPriceName()+" ¥");
            holder.getGoodsDescription().setText(mode.getGoodsDescribe());
            if (mode.getActivityGoodsLableVO()!=null&&mode.getActivityGoodsLableVO().size()>0) {
                holder.getTagFlowLayout().setVisibility(View.VISIBLE);
                setLabel(holder.getTagFlowLayout(), mode.getActivityGoodsLableVO());
            }
            else
                holder.getTagFlowLayout().setVisibility(View.INVISIBLE);

        }

        return row;
    }
    public void setLabel(TagFlowLayout mFlowLayout,List<ActivityGoodsVoListTo.ActivityGoodsLableVOBean>labelList){
        String[] mLabels=new String[labelList.size()];
      for (int i=0;i<labelList.size();i++)
          mLabels[i]=labelList.get(i).getLableName();

        mFlowLayout.setAdapter(new TagAdapter<String>(mLabels) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                RelativeLayout mView = (RelativeLayout) mInflater.inflate(R.layout.activity_goods_label,
                        mFlowLayout, false);
                TextView tv= (TextView) mView.findViewById(R.id.text);
                tv.setText(s);
                return mView;
            }
        });
    }
    public ActivityGoodsVoListTo.ActivityGoodsSpecificationVOBean getMinPrice(List<ActivityGoodsVoListTo.ActivityGoodsSpecificationVOBean> specificationList){


        if (specificationList!=null) {
            specificationTo=specificationList.get(0);
         Observable.from(specificationList).subscribe(activityGoodsSpecificationVOBean -> {
             if (Double.parseDouble(activityGoodsSpecificationVOBean.getCurrentPrice())<Double.parseDouble(specificationTo.getCurrentPrice()))
                 specificationTo=activityGoodsSpecificationVOBean;
         });
            return specificationTo;
        }else
            return null;
    }
}