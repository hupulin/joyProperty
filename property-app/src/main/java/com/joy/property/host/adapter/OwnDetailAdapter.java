package com.joy.property.host.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinyi.ihome.module.home.ServiceReportGradeTo;
import com.joy.property.R;
import com.joy.property.common.adapter.ModeListAdapter;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/5/26.
 */
public class OwnDetailAdapter extends ModeListAdapter<ServiceReportGradeTo> {


    private Context mContext;

    public OwnDetailAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        OwnerMouthCache holder;
        ServiceReportGradeTo mode = mList.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (row == null) {
            row = inflater.inflate(R.layout.activity_ownermdetail, null);
            holder = new OwnerMouthCache(row);
            row.setTag(holder);
        } else {
            holder = (OwnerMouthCache) row.getTag();
        }

        // 服务入室维修   valueRSWXService;
        if (mode.getValueRSWXService() != null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            float mtsGood = 0.0f;
            float mtsMedium = 0.0f;
            float mtsBad = 0.0f;
            if (mode.getValueRSWXService().containsKey("praise")) {
                mtsGood = mode.getValueRSWXService().get("praise");
            }
            if (mode.getValueRSWXService().containsKey("medium")) {
                mtsMedium = mode.getValueRSWXService().get("medium");
            }
            if (mode.getValueRSWXService().containsKey("bad")) {
                mtsBad = mode.getValueRSWXService().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmrs_Service_good().setText(df.format(mtsGood) + "%");
            holder.getmrs_Service_so().setText(df.format(mtsMedium) + "%");
            holder.getmrs_Service_bad().setText(df.format(mtsBad) + "%");
//            holder.getmrs_Service_good().setTextColor(0xff008cee);
//            holder.getmrs_Service_so().setTextColor(0xff008cee);
//            holder.getmrs_Service_bad().setTextColor(0xffeb4f38);
//            System.out.println(mode.getValueTS() + "touse");
        } else{
            holder.getmrs_Service_good().setText("0.0%");
            holder.getmrs_Service_so().setText("0.0%");
            holder.getmrs_Service_bad().setText("0.0%");
        }
// 解决速度入室维修  valueRSWXSolve;
        if (mode.getValueRSWXSolve() != null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            float mggGood = 0.0f;
            float mggMedium = 0.00f;
            float mggBad = 0.0f;
            if (mode.getValueRSWXSolve().containsKey("praise")) {
                mggGood = mode.getValueRSWXSolve().get("praise");
            }
            if (mode.getValueRSWXSolve().containsKey("medium")) {
                mggMedium = mode.getValueRSWXSolve().get("medium");
            }

            if (mode.getValueRSWXSolve().containsKey("bad")) {
                mggBad = mode.getValueRSWXSolve().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmrs_Solve_good().setText(df.format(mggGood) + "%");
            holder.getmrs_Solve_so().setText(df.format(mggMedium) + "%");
            holder.getmrs_Solve_bad().setText(df.format(mggBad) + "%");
        } else{
            holder.getmrs_Solve_good().setText("0.0%");
            holder.getmrs_Solve_so().setText("0.0%");
            holder.getmrs_Solve_bad().setText("0.0%");
        }


        //入室维修 满意度的valueRSWXSatisfied
        if (mode.getValueRSWXSatisfied() != null) {
            float mGood = 0.0f;
            float mMedium = 0.0f;
            float mBad = 0.0f;
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            if (mode.getValueRSWXSatisfied().containsKey("praise")) {
                mGood = mode.getValueRSWXSatisfied().get("praise");
            }
            if (mode.getValueRSWXSatisfied().containsKey("medium")) {
                mMedium = mode.getValueRSWXSatisfied().get("medium");
            }

            if (mode.getValueRSWXSatisfied().containsKey("bad")) {
                mBad = mode.getValueRSWXSatisfied().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmrs_good().setText(df.format(mGood) + "%");
            holder.getmrs_so().setText(df.format(mMedium) + "%");
            holder.getmrs_bad().setText(df.format(mBad) + "%");
        } else{
            holder.getmrs_good().setText("0.0%");
            holder.getmrs_so().setText("0.0%");
            holder.getmrs_bad().setText("0.0%");
        }














        // 服务公共维修   valueGGWXService;
        if (mode.getValueGGWXService()!= null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            float mtsGood = 0.0f;
            float mtsMedium = 0.0f;
            float mtsBad = 0.0f;
            if (mode.getValueGGWXService().containsKey("praise")) {
                mtsGood = mode.getValueGGWXService().get("praise");
            }
            if (mode.getValueGGWXService().containsKey("medium")) {
                mtsMedium = mode.getValueGGWXService().get("medium");
            }
            if (mode.getValueGGWXService().containsKey("bad")) {
                mtsBad = mode.getValueGGWXService().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmgg_Service_good().setText(df.format(mtsGood) + "%");
            holder.getmgg_Service_so().setText(df.format(mtsMedium) + "%");
            holder.getmgg_Service_bad().setText(df.format(mtsBad) + "%");
        } else{
            holder.getmgg_Service_good().setText("0.0%");
            holder.getmgg_Service_so().setText("0.0%");
            holder.getmgg_Service_bad().setText("0.0%");
        }
// 解决速度 valueRSWXSolve;
        if (mode.getValueGGWXSolve() != null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            float mggGood = 0.0f;
            float mggMedium = 0.00f;
            float mggBad = 0.0f;
            if (mode.getValueGGWXSolve().containsKey("praise")) {
                mggGood = mode.getValueGGWXSolve().get("praise");
            }
            if (mode.getValueGGWXSolve().containsKey("medium")) {
                mggMedium = mode.getValueGGWXSolve().get("medium");
            }

            if (mode.getValueGGWXSolve().containsKey("bad")) {
                mggBad = mode.getValueGGWXSolve().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmgg_Solve_good().setText(df.format(mggGood) + "%");
            holder.getmgg_Solve_so().setText(df.format(mggMedium) + "%");
            holder.getmgg_Solve_bad().setText(df.format(mggBad) + "%");
        } else{
            holder.getmgg_Solve_good().setText("0.0%");
            holder.getmgg_Solve_so().setText("0.0%");
            holder.getmgg_Solve_bad().setText("0.0%");
        }


        //满意度的valueRSWXSatisfied
        if (mode.getValueGGWXSatisfied()!= null) {
            float mGood = 0.0f;
            float mMedium = 0.0f;
            float mBad = 0.0f;
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            if (mode.getValueGGWXSatisfied().containsKey("praise")) {
                mGood = mode.getValueGGWXSatisfied().get("praise");
            }
            if (mode.getValueGGWXSatisfied().containsKey("medium")) {
                mMedium = mode.getValueGGWXSatisfied().get("medium");
            }

            if (mode.getValueGGWXSatisfied().containsKey("bad")) {
                mBad = mode.getValueGGWXSatisfied().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmgg_good().setText(df.format(mGood) + "%");
            holder.getmgg_so().setText(df.format(mMedium) + "%");
            holder.getmgg_bad().setText(df.format(mBad) + "%");
        } else{
            holder.getmgg_good().setText("0.0%");
            holder.getmgg_so().setText("0.0%");
            holder.getmgg_bad().setText("0.0%");
        }









        // 服务投诉管理   valueGGWXService;
        if (mode.getValueTSService()!= null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            float mtsGood = 0.0f;
            float mtsMedium = 0.0f;
            float mtsBad = 0.0f;
            if (mode.getValueTSService().containsKey("praise")) {
                mtsGood = mode.getValueTSService().get("praise");
            }
            if (mode.getValueTSService().containsKey("medium")) {
                mtsMedium = mode.getValueTSService().get("medium");
            }
            if (mode.getValueTSService().containsKey("bad")) {
                mtsBad = mode.getValueTSService().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmts_Service_good().setText(df.format(mtsGood) + "%");
            holder.getmts_Service_so().setText(df.format(mtsMedium) + "%");
            holder.getmts_Service_bad().setText(df.format(mtsBad) + "%");
        } else{
            holder.getmts_Service_good().setText("0.0%");
            holder.getmts_Service_so().setText("0.0%");
            holder.getmts_Service_bad().setText("0.0%");
        }
// 解决速度  valueRSWXSolve;
        if (mode.getValueTSSolve() != null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            float mggGood = 0.0f;
            float mggMedium = 0.00f;
            float mggBad = 0.0f;
            if (mode.getValueTSSolve().containsKey("praise")) {
                mggGood = mode.getValueTSSolve().get("praise");
            }
            if (mode.getValueTSSolve().containsKey("medium")) {
                mggMedium = mode.getValueTSSolve().get("medium");
            }

            if (mode.getValueTSSolve().containsKey("bad")) {
                mggBad = mode.getValueTSSolve().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmts_Solve_good().setText(df.format(mggGood) + "%");
            holder.getmts_Solve_so().setText(df.format(mggMedium) + "%");
            holder.getmts_Solve_bad().setText(df.format(mggBad) + "%");
        } else{
            holder.getmts_Solve_good().setText("0.0%");
            holder.getmts_Solve_so().setText("0.0%");
            holder.getmts_Solve_bad().setText("0.0%");
        }


        // 满意度的valueRSWXSatisfied
        if (mode.getValueTSSatisfied()!= null) {
            float mGood = 0.0f;
            float mMedium = 0.0f;
            float mBad = 0.0f;
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            if (mode.getValueTSSatisfied().containsKey("praise")) {
                mGood = mode.getValueTSSatisfied().get("praise");
            }
            if (mode.getValueTSSatisfied().containsKey("medium")) {
                mMedium = mode.getValueTSSatisfied().get("medium");
            }

            if (mode.getValueTSSatisfied().containsKey("bad")) {
                mBad = mode.getValueTSSatisfied().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmts_good().setText(df.format(mGood) + "%");
            holder.getmts_so().setText(df.format(mMedium) + "%");
            holder.getmts_bad().setText(df.format(mBad) + "%");
        } else{
            holder.getmts_good().setText("0.0%");
            holder.getmts_so().setText("0.0%");
            holder.getmts_bad().setText("0.0%");
        }






        // 服务家政  valueJZService
        if (mode.getValueJZService()!= null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            float mtsGood = 0.0f;
            float mtsMedium = 0.0f;
            float mtsBad = 0.0f;
            if (mode.getValueJZService().containsKey("praise")) {
                mtsGood = mode.getValueJZService().get("praise");
            }
            if (mode.getValueJZService().containsKey("medium")) {
                mtsMedium = mode.getValueJZService().get("medium");
            }
            if (mode.getValueJZService().containsKey("bad")) {
                mtsBad = mode.getValueJZService().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmjz_Service_good().setText(df.format(mtsGood) + "%");
            holder.getmjz_Service_so().setText(df.format(mtsMedium) + "%");
            holder.getmjz_Service_bad().setText(df.format(mtsBad) + "%");
        } else{
            holder.getmjz_Service_good().setText("0.0%");
            holder.getmjz_Service_so().setText("0.0%");
            holder.getmjz_Service_bad().setText("0.0%");
        }
// 解决速度  valueRSWXSolve;
        if (mode.getValueJZSolve() != null) {
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            float mggGood = 0.0f;
            float mggMedium = 0.00f;
            float mggBad = 0.0f;
            if (mode.getValueJZSolve().containsKey("praise")) {
                mggGood = mode.getValueJZSolve().get("praise");
            }
            if (mode.getValueJZSolve().containsKey("medium")) {
                mggMedium = mode.getValueJZSolve().get("medium");
            }

            if (mode.getValueJZSolve().containsKey("bad")) {
                mggBad = mode.getValueJZSolve().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmjz_Solve_good().setText(df.format(mggGood) + "%");
            holder.getmjz_Solve_so().setText(df.format(mggMedium) + "%");
            holder.getmjz_Solve_bad().setText(df.format(mggBad) + "%");
        } else{
            holder.getmjz_Solve_good().setText("0.0%");
            holder.getmjz_Solve_so().setText("0.0%");
            holder.getmjz_Solve_bad().setText("0.0%");
        }


        // 满意度的valueRSWXSatisfied
        if (mode.getValueJZSatisfied()!= null) {
            float mGood = 0.0f;
            float mMedium = 0.0f;
            float mBad = 0.0f;
            /***
             * praise 好评
             * medium 中评
             * bad 差评
             */
            if (mode.getValueJZSatisfied().containsKey("praise")) {
                mGood = mode.getValueJZSatisfied().get("praise");
            }
            if (mode.getValueJZSatisfied().containsKey("medium")) {
                mMedium = mode.getValueJZSatisfied().get("medium");
            }

            if (mode.getValueJZSatisfied().containsKey("bad")) {
                mBad = mode.getValueJZSatisfied().get("bad");
            }
            DecimalFormat df = new DecimalFormat("0.0");
            holder.getmjz_good().setText(df.format(mGood) + "%");
            holder.getmjz_so().setText(df.format(mMedium) + "%");
            holder.getmjz_bad().setText(df.format(mBad) + "%");
        } else{
            holder.getmjz_good().setText("0.0%");
            holder.getmjz_so().setText("0.0%");
            holder.getmjz_bad().setText("0.0%");
        }



        return row;
    }
}