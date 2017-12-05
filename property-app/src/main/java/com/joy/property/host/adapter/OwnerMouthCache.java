package com.joy.property.host.adapter;

import android.view.View;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by Admin on 2015-02-09
 */
public class OwnerMouthCache {


    private View view;
    private TextView mApartment;
    private TextView mGood;//满意度总的好评
    private TextView mSOSO;//中评
    private TextView mBad;//差评

    private TextView mService_good;//服务态度总的
    private TextView mService_so;
    private TextView mService_bad;

    private TextView mSolve_good;//响应速度总的
    private TextView mSolve_so;
    private TextView mSolve_bad;


    private TextView mrs_Service_good; //服务态度的入室维修
    private TextView mrs_Service_so;
    private TextView mrs_Service_bad;
    private TextView mrs_Solve_good;//响应入室维修
    private TextView mrs_Solve_so;
    private TextView mrs_Solve_bad;
    private TextView mrs_good;//满意度入室维修
    private TextView mrs_so;
    private TextView mrs_bad;



    private TextView mgg_Service_good; //服务态度的公共维修
    private TextView mgg_Service_so;
    private TextView mgg_Service_bad;
    private TextView mgg_Solve_good;//响应公共维修
    private TextView mgg_Solve_so;
    private TextView mgg_Solve_bad;
    private TextView mgg_good;//满意度公共维修
    private TextView mgg_so;
    private TextView mgg_bad;



    private TextView mts_Service_good; //服务态度的投诉管理
    private TextView mts_Service_so;
    private TextView mts_Service_bad;
    private TextView mts_Solve_good;//响应投诉管理
    private TextView mts_Solve_so;
    private TextView mts_Solve_bad;
    private TextView mts_good;//满意度投诉管理
    private TextView mts_so;
    private TextView mts_bad;



    private TextView mjz_Service_good; //服务态度的家政管理
    private TextView mjz_Service_so;
    private TextView mjz_Service_bad;
    private TextView mjz_Solve_good;//响应家政管理
    private TextView mjz_Solve_so;
    private TextView mjz_Solve_bad;
    private TextView mjz_good;//满意度家政管理
    private TextView mjz_so;
    private TextView mjz_bad;



    public OwnerMouthCache(View view) {
        this.view = view;
    }

    public TextView getmApartment() {
        if (mApartment == null) {
            mApartment = (TextView) view.findViewById(R.id.apartment);
        }
        return mApartment;
    }

    public TextView getmGood() {
        if (mGood == null) {
            mGood = (TextView) view.findViewById(R.id.good);
        }
        return mGood;
    }

    public TextView getmSOSO() {
        if (mSOSO == null) {
            mSOSO = (TextView) view.findViewById(R.id.so);
        }
        return mSOSO;
    }

    public TextView getmBad() {
        if (mBad == null) {
            mBad = (TextView) view.findViewById(R.id.bad);
        }
        return mBad;
    }
    //服务态度
    public TextView getmService_good() {
        if (mService_good == null) {
            mService_good = (TextView) view.findViewById(R.id.Service_good);
        }
        return mService_good;
    }

    public TextView getmService_so() {
        if (mService_so == null) {
            mService_so = (TextView) view.findViewById(R.id.Service_so);
        }
        return mService_so;
    }

    public TextView getmService_bad() {
        if (mService_bad == null) {
            mService_bad = (TextView) view.findViewById(R.id.Service_bad);
        }
        return mService_bad;
    }
          // 解决速度汇总    valueSolve;
    public TextView getmSolve_good() {
        if (mSolve_good == null) {
            mSolve_good = (TextView) view.findViewById(R.id.Solve_good);
        }
        return mSolve_good;
    }

    public TextView getmSolve_so() {
        if (mSolve_so == null) {
            mSolve_so = (TextView) view.findViewById(R.id.Solve_so);
        }
        return mSolve_so;
    }

    public TextView getmSolve_bad() {
        if (mSolve_bad == null) {
            mSolve_bad = (TextView) view.findViewById(R.id.Solve_bad);
        }
        return mSolve_bad;
    }








    // 服务入室维修    valueRSWXSatisfied;    rs_Service_good

    public TextView getmrs_Service_good() {
        if (mrs_Service_good == null) {
            mrs_Service_good = (TextView) view.findViewById(R.id.rs_Service_good);
        }
        return mrs_Service_good;
    }


    public TextView getmrs_Service_so() {
        if (mrs_Service_so == null) {
            mrs_Service_so = (TextView) view.findViewById(R.id.rs_Service_so);
        }
        return mrs_Service_so;
    }

    public TextView getmrs_Service_bad() {
        if (mrs_Service_bad == null) {
            mrs_Service_bad = (TextView) view.findViewById(R.id.rs_Service_bad);
        }
        return mrs_Service_bad;
    }

    // 响应入室维修    valueRSWXSatisfied;
    public TextView getmrs_Solve_good() {
        if (mrs_Solve_good == null) {
            mrs_Solve_good = (TextView) view.findViewById(R.id.rs_Solve_good);
        }
        return mrs_Solve_good;
    }


    public TextView getmrs_Solve_so() {
        if (mrs_Solve_so == null) {
            mrs_Solve_so = (TextView) view.findViewById(R.id.rs_Solve_so);
        }
        return mrs_Solve_so;
    }

    public TextView getmrs_Solve_bad() {
        if (mrs_Solve_bad == null) {
            mrs_Solve_bad = (TextView) view.findViewById(R.id.rs_Solve_bad);
        }
        return mrs_Solve_bad;
    }


    // 满意度入室维修   valueRSWXSatisfied;

    public TextView getmrs_good() {
        if (mrs_good == null) {
            mrs_good = (TextView) view.findViewById(R.id.rs_good);
        }
        return mrs_good;
    }


    public TextView getmrs_so() {
        if (mrs_so == null) {
            mrs_so = (TextView) view.findViewById(R.id.rs_so);
        }
        return mrs_so;
    }

    public TextView getmrs_bad() {
        if (mrs_bad == null) {
            mrs_bad = (TextView) view.findViewById(R.id.rs_bad);
        }
        return mrs_bad;
    }




    // 服务公共维修    valueRSWXSatisfied;    rs_Service_good

    public TextView getmgg_Service_good() {
        if (mgg_Service_good == null) {
            mgg_Service_good = (TextView) view.findViewById(R.id.gg_Service_good);
        }
        return mgg_Service_good;
    }


    public TextView getmgg_Service_so() {
        if (mgg_Service_so == null) {
            mgg_Service_so = (TextView) view.findViewById(R.id.gg_Service_so);
        }
        return mgg_Service_so;
    }

    public TextView getmgg_Service_bad() {
        if (mgg_Service_bad == null) {
            mgg_Service_bad = (TextView) view.findViewById(R.id.gg_Service_bad);
        }
        return mgg_Service_bad;
    }

    // 响应公共维修    valueRSWXSatisfied;
    public TextView getmgg_Solve_good() {
        if (mgg_Solve_good == null) {
            mgg_Solve_good = (TextView) view.findViewById(R.id.gg_Solve_good);
        }
        return mgg_Solve_good;
    }


    public TextView getmgg_Solve_so() {
        if (mgg_Solve_so == null) {
            mgg_Solve_so = (TextView) view.findViewById(R.id.gg_Solve_so);
        }
        return mgg_Solve_so;
    }

    public TextView getmgg_Solve_bad() {
        if (mgg_Solve_bad == null) {
            mgg_Solve_bad = (TextView) view.findViewById(R.id.gg_Solve_bad);
        }
        return mgg_Solve_bad;
    }


    // 满意度公共维修   valueRSWXSatisfied;

    public TextView getmgg_good() {
        if (mgg_good == null) {
            mgg_good = (TextView) view.findViewById(R.id.gg_good);
        }
        return mgg_good;
    }


    public TextView getmgg_so() {
        if (mgg_so == null) {
            mgg_so = (TextView) view.findViewById(R.id.gg_so);
        }
        return mrs_so;
    }

    public TextView getmgg_bad() {
        if (mgg_bad == null) {
            mgg_bad = (TextView) view.findViewById(R.id.gg_bad);
        }
        return mgg_bad;
    }






// 服务投诉维修    valueRSWXSatisfied;    rs_Service_good

    public TextView getmts_Service_good() {
        if (mts_Service_good == null) {
            mts_Service_good = (TextView) view.findViewById(R.id.ts_Service_good);
        }
        return mts_Service_good;
    }


    public TextView getmts_Service_so() {
        if (mts_Service_so == null) {
            mts_Service_so = (TextView) view.findViewById(R.id.ts_Service_so);
        }
        return mts_Service_so;
    }

    public TextView getmts_Service_bad() {
        if (mts_Service_bad == null) {
            mts_Service_bad = (TextView) view.findViewById(R.id.ts_Service_bad);
        }
        return mts_Service_bad;
    }

    // 响应投诉维修    valueRSWXSatisfied;
    public TextView getmts_Solve_good() {
        if (mts_Solve_good == null) {
            mts_Solve_good = (TextView) view.findViewById(R.id.ts_Solve_good);
        }
        return mts_Solve_good;
    }


    public TextView getmts_Solve_so() {
        if (mts_Solve_so == null) {
            mts_Solve_so = (TextView) view.findViewById(R.id.ts_Solve_so);
        }
        return mts_Solve_so;
    }

    public TextView getmts_Solve_bad() {
        if (mts_Solve_bad == null) {
            mts_Solve_bad = (TextView) view.findViewById(R.id.ts_Solve_bad);
        }
        return mts_Solve_bad;
    }


    // 满意度投诉维修  valueRSWXSatisfied;

    public TextView getmts_good() {
        if (mts_good == null) {
            mts_good = (TextView) view.findViewById(R.id.ts_good);
        }
        return mts_good;
    }


    public TextView getmts_so() {
        if (mts_so == null) {
            mts_so = (TextView) view.findViewById(R.id.ts_so);
        }
        return mts_so;
    }

    public TextView getmts_bad() {
        if (mts_bad == null) {
            mts_bad = (TextView) view.findViewById(R.id.ts_bad);
        }
        return mts_bad;
    }





    // 服务家政    valueRSWXSatisfied;    rs_Service_good

    public TextView getmjz_Service_good() {
        if (mjz_Service_good == null) {
            mjz_Service_good = (TextView) view.findViewById(R.id.jz_Service_good);
        }
        return mjz_Service_good;
    }


    public TextView getmjz_Service_so() {
        if (mjz_Service_so == null) {
            mjz_Service_so = (TextView) view.findViewById(R.id.jz_Service_so);
        }
        return mjz_Service_so;
    }

    public TextView getmjz_Service_bad() {
        if (mjz_Service_bad == null) {
            mjz_Service_bad = (TextView) view.findViewById(R.id.jz_Service_bad);
        }
        return mjz_Service_bad;
    }

    // 响应投诉维修    valueRSWXSatisfied;
    public TextView getmjz_Solve_good() {
        if (mjz_Solve_good == null) {
            mjz_Solve_good = (TextView) view.findViewById(R.id.jz_Solve_good);
        }
        return mjz_Solve_good;
    }


    public TextView getmjz_Solve_so() {
        if (mjz_Solve_so == null) {
            mjz_Solve_so = (TextView) view.findViewById(R.id.jz_Solve_so);
        }
        return mjz_Solve_so;
    }

    public TextView getmjz_Solve_bad() {
        if (mjz_Solve_bad == null) {
            mjz_Solve_bad = (TextView) view.findViewById(R.id.jz_Solve_bad);
        }
        return mjz_Solve_bad;
    }


    // 满意度投诉维修  valueRSWXSatisfied;

    public TextView getmjz_good() {
        if (mjz_good == null) {
            mjz_good = (TextView) view.findViewById(R.id.jz_good);
        }
        return mjz_good;
    }


    public TextView getmjz_so() {
        if (mjz_so == null) {
            mjz_so = (TextView) view.findViewById(R.id.jz_so);
        }
        return mjz_so;
    }

    public TextView getmjz_bad() {
        if (mjz_bad == null) {
            mjz_bad = (TextView) view.findViewById(R.id.jz_bad);
        }
        return mjz_bad;
    }





}
