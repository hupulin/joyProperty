package com.joy.property.worksign.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.Util.signencode.SXHttpUtils;
import com.Util.signencode.aes.WLHSecurityUtils;
import com.Util.smoothchart.SmoothLineChartView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.google.gson.Gson;
import com.jinyi.ihome.module.worksign.MyPrintTo;
import com.jinyi.ihome.module.worksign.SignJsonTo;
import com.jinyi.ihome.module.worksign.SignMessageTo;
import com.jinyi.ihome.module.worksign.SignRecordInfoTo;
import com.joy.library.utils.DateUtil;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.task.TimePickerDialog.TimePickerDialog;
import com.joy.property.task.TimePickerDialog.data.Type;
import com.joy.property.task.TimePickerDialog.listener.OnDateSetListener;
import com.joy.property.worksign.SignRecordActivity;
import com.joy.property.worksign.adapter.FootprintHolderView;
import com.joy.property.worksign.adapter.SignBaseParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xzz on 2017/12/28.
 **/
public class FootprintFragment extends BaseFragment implements OnDateSetListener, View.OnClickListener {

    private View rootView;
    private int maxTotal;
    private GridLayout timeLayout;
    private LinearLayout workContentLayout;
    private TextView workContent;
    private TextView signDate;
    private TextView address;
    private TextView signCount;
    private TextView detailDate;
    private String recordListSid;
    private View selectTime;
    private int signType;
    private TextView day;
    private TextView week;
    private TextView month;
    private ConvenientBanner autoRow;
    private Handler handler=new Handler();
    private List<MyPrintTo.TimeslistBean> autoRowTimeList=new ArrayList<>();
    private  int lastPosition;
    private TextView topTime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_footprint, container, false);
        findView();
        getData(DateUtil.getDateString(DateUtil.mFormatDateString), DateUtil.getDateString(DateUtil.mFormatDateString));
        return rootView;
    }

    private void findView() {
        timeLayout = (GridLayout) rootView.findViewById(R.id.time_layout);
        workContentLayout = (LinearLayout) rootView.findViewById(R.id.work_content_layout);
        workContent = (TextView) rootView.findViewById(R.id.work_content);
        signDate = (TextView) rootView.findViewById(R.id.sign_date);
        address = (TextView) rootView.findViewById(R.id.address);
        signCount = (TextView) rootView.findViewById(R.id.sign_count);
        detailDate = (TextView) rootView.findViewById(R.id.detail_date);
        selectTime = rootView.findViewById(R.id.select_time);
        day = (TextView) rootView.findViewById(R.id.day);
        week = (TextView) rootView.findViewById(R.id.week);
        month = (TextView) rootView.findViewById(R.id.month);
        day.setOnClickListener(this);
        week.setOnClickListener(this);
        month.setOnClickListener(this);
        selectTime.setOnClickListener(this);
        autoRow = (ConvenientBanner) rootView.findViewById(R.id.auto_row);
        topTime = (TextView) rootView.findViewById(R.id.top_time);

    }


    private void getData(String startTime, String endTime) {


        TelephonyManager tm = (TelephonyManager) getThisContext().getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = tm.getDeviceId();
        SignJsonTo jsonTo = new SignJsonTo();
        jsonTo.setDeviceId("1909DCFD-243D-2F68-233A-250C9C9B571E");
        jsonTo.setTradeType("GetReport");
        jsonTo.setUniqueStr(mUserHelper.getSid());
        jsonTo.setReportType(signType);
        jsonTo.setStartDate(startTime);
        jsonTo.setOpenId(mUserHelper.getSid());
        jsonTo.setUniqueStr(IMEI);
        jsonTo.setEndDate(endTime);
        SignBaseParam param = new SignBaseParam();
        param.setParamData(WLHSecurityUtils.toURLDecoded(WLHSecurityUtils.encrypt(new Gson().toJson(jsonTo))));

        Map<String, String> params = new HashMap<>();
        params.put("ParamData", param.getParamData());
        SXHttpUtils.requestPostData(getActivity(), "http://nd.alipayer.cn/index.php/backend/api.html", params, "UTF-8", new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                SignMessageTo<MyPrintTo> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);
                MyPrintTo myPrintTo = new Gson().fromJson(new Gson().toJson(msg.getResultContent()), MyPrintTo.class);
                if (myPrintTo != null && myPrintTo.getTimeslist() != null && myPrintTo.getTypelist() != null)
                    initView(myPrintTo,startTime,endTime);

                System.out.println(msg + "myPrintTo");
            }

            @Override
            public void onLoadError() {

            }
        });
    }


    public void initView(MyPrintTo myPrintTo,String startTime,String endTime) {
         lastPosition=0;

        setAutoRow(myPrintTo.getTimeslist(),1,startTime,endTime);
        //     Toast.makeText(getThisContext(), DateUtil.getDateString(DateUtil.getLastWeek().get("monday"), DateUtil.mWorkDate) + "=====" + DateUtil.getDateString(DateUtil.getLastWeek().get("sunday"), DateUtil.mWorkDate), Toast.LENGTH_LONG).show();
        final SmoothLineChartView chartView = (SmoothLineChartView) rootView.findViewById(R.id.smoothChartView);

        RelativeLayout.LayoutParams layoutParams;
        if (signType == 0) {
            layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 2396.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));
            layoutParams.setMargins((int) (getScreenWidthPixels(getThisContext()) * -98.0 / 720), 0, 0, 0);
        } else if (signType == 1) {
            layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 860.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));
            layoutParams.setMargins((int) (getScreenWidthPixels(getThisContext()) * -95.0 / 720), 0, 0, 0);

        } else {
            if (myPrintTo.getTimeslist().size() == 31)
                layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 3020.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));
            else if (myPrintTo.getTimeslist().size() == 30)
                layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 2930.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));
            else if (myPrintTo.getTimeslist().size() == 29)
                layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 2840.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));
            else
                layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 2761.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));

            layoutParams.setMargins((int) (getScreenWidthPixels(getThisContext()) * -96.0 / 720), 0, 0, 0);
        }


        chartView.setLayoutParams(layoutParams);
        chartView.setCustomBorder(true);
        chartView.setSelectPosition(1);
        chartView.setTextColor(Color.WHITE);
        chartView.setTextSize(10);
        chartView.setTextOffset(4);

        chartView.enableShowTag(true);
        chartView.enableDrawArea(true);
        chartView.setLineColor(Color.parseColor("#FFDCDCDC"));
        chartView.setCircleColor(Color.parseColor("#FFfbcc3b"));
        chartView.setInnerCircleColor(Color.parseColor("#ffffff"));
        chartView.setNodeStyle(SmoothLineChartView.NODE_STYLE_RING);
        List<Float> data = new ArrayList<>();
        data.add(-1f);
        List<String> x = new ArrayList<>();
        timeLayout.removeAllViews();
        timeLayout.addView(View.inflate(getThisContext(), R.layout.footprint_time_item, null));
        for (int i = 0; i < myPrintTo.getTimeslist().size(); i++) {
            MyPrintTo.TimeslistBean timeTo = myPrintTo.getTimeslist().get(i);
            data.add((float) timeTo.getBktotal());
            x.add("3-12");
            if (timeTo.getBktotal() > maxTotal) {
                maxTotal = timeTo.getBktotal();

            }
            View timeView = View.inflate(getThisContext(), R.layout.footprint_time_item, null);
            TextView timeText = (TextView) timeView.findViewById(R.id.time_text);
            if (i % 2 == 1)
                timeText.setVisibility(View.GONE);
//            timeText.setText(timeTo.getBlock().substring(11));
            timeText.setText(timeTo.getBlock());
            timeLayout.addView(timeView);

        }
        workContentLayout.removeAllViews();
        for (int i = 0; i < myPrintTo.getTypelist().size(); i++) {
            View mView = View.inflate(getThisContext(), R.layout.footpring_work_content_item, null);
            MyPrintTo.TypelistBean typeTo = myPrintTo.getTypelist().get(i);
            ((TextView) mView.findViewById(R.id.work_content)).setText(typeTo.getTpname());
            ((TextView) mView.findViewById(R.id.sign_count)).setText(typeTo.getTpnum() + "");
            workContentLayout.addView(mView);
        }
       if (signType==0)
        topTime.setText(myPrintTo.getTimeslist().get(0).getBkey().substring(5)+"至"+myPrintTo.getTimeslist().get(myPrintTo.getTimeslist().size()-1).getBkey().substring(5));
      else
           topTime.setText(myPrintTo.getTimeslist().get(0).getBkey()+"至"+myPrintTo.getTimeslist().get(myPrintTo.getTimeslist().size()-1).getBkey());
        data.add(-2f);

        x.add("3-12");
        x.add("3-12");


        chartView.setData(data, x);
        chartView.setMaxY(maxTotal + 10);
        chartView.setMinY(0);


        chartView.setOnChartClickListener((position, value) -> {
            MyPrintTo.TimeslistBean timeTo = myPrintTo.getTimeslist().get(position - 1);
             if (position==lastPosition)
                 return;
            autoRowTimeList.clear();
            autoRowTimeList.add(null);
            if (lastPosition<position){


                autoRowTimeList.add(myPrintTo.getTimeslist().get(autoRow.getCurrentItem()));
                autoRowTimeList.add(timeTo);
                setAutoRow(autoRowTimeList,1,startTime,endTime);

                handler.postDelayed(() -> {
                    autoRow.setcurrentitem(2);
                },100);

            }
            if (lastPosition>position){

                autoRowTimeList.add(timeTo);
                autoRowTimeList.add(myPrintTo.getTimeslist().get(autoRow.getCurrentItem()));
                setAutoRow(autoRowTimeList,2,startTime,endTime);

                handler.postDelayed(() -> {
                    autoRow.setcurrentitem(1);
                },100);


            }

            lastPosition=position;
//


            recordListSid = timeTo.getBkey();
        });




    }

    public void selectTime(int type) {
        long tenYears = 60L * 365 * 1000 * 60 * 60 * 24L;
        long oneYears = 365 * 1000 * 60 * 60 * 24L;
        TimePickerDialog mDialogAll = new TimePickerDialog.Builder().setCallBack(FootprintFragment.this).
                setCancelStringId("取消").
                setSureStringId("确定").
                setTitleStringId("选择日期").
                setMonthText("月").setDayText("日").setYearText("年").setMinMillseconds(System.currentTimeMillis() - tenYears).setMaxMillseconds(System.currentTimeMillis() + oneYears).setCurrentMillseconds(System.currentTimeMillis()).
                setCyclic(false).setThemeColor(Color.parseColor("#6d75a4")).setType(type == 0 ? Type.YEAR_MONTH_DAY : Type.YEAR_MONTH).setWheelItemTextNormalColor(R.color.timetimepicker_default_text_color).setWheelItemTextSelectorColor(R.color.timepicker_toolbar_bg).setWheelItemTextSize(12).build();
        mDialogAll.show(getFragmentManager(), "");
    }


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) throws ParseException {
        //Toast.makeText(getThisContext(),DateUtil.longToString(timePickerView.getCurrentMillSeconds(),DateUtil.mDateFormatString),Toast.LENGTH_LONG).show();
        if (signType == 0)
            getData(DateUtil.longToString(millseconds, DateUtil.mFormatDateString), DateUtil.longToString(millseconds, DateUtil.mFormatDateString));
        else
            //   getData(DateUtil.getFirstDayOfMonth(2018,3),DateUtil.getLastDayOfMonth(2018,3));
            getData(DateUtil.getFirstDayOfMonth(Integer.parseInt(DateUtil.longToString(millseconds, DateUtil.mDateFormatString).substring(0, 4)), Integer.parseInt(DateUtil.longToString(millseconds, DateUtil.mDateFormatString).substring(6, 7)), true), DateUtil.getLastDayOfMonth(Integer.parseInt(DateUtil.longToString(millseconds, DateUtil.mDateFormatString).substring(0, 4)), Integer.parseInt(DateUtil.longToString(millseconds, DateUtil.mDateFormatString).substring(6, 7)), true));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.day:
                day.setTextColor(Color.parseColor("#333333"));
                week.setTextColor(Color.parseColor("#999999"));
                month.setTextColor(Color.parseColor("#999999"));
                signType = 0;
                selectTime.setVisibility(View.VISIBLE);
                getData(DateUtil.getDateString(DateUtil.mFormatDateString), DateUtil.getDateString(DateUtil.mFormatDateString));
                break;
            case R.id.week:
                day.setTextColor(Color.parseColor("#999999"));
                week.setTextColor(Color.parseColor("#333333"));
                month.setTextColor(Color.parseColor("#999999"));
                selectTime.setVisibility(View.GONE);
                signType = 1;
                getData(DateUtil.getDateString(DateUtil.getLastWeek().get("monday"), DateUtil.mFormatDateString), DateUtil.getDateString(DateUtil.getLastWeek().get("sunday"), DateUtil.mFormatDateString));
                break;
            case R.id.month:
                day.setTextColor(Color.parseColor("#999999"));
                week.setTextColor(Color.parseColor("#999999"));
                month.setTextColor(Color.parseColor("#333333"));
                signType = 2;
                selectTime.setVisibility(View.VISIBLE);
                getData(DateUtil.getFirstDayOfMonth(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), false), DateUtil.getLastDayOfMonth(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), false));
                break;
            case R.id.select_time:
                selectTime(signType);
                break;
        }
    }

    public void setAutoRow(List<MyPrintTo.TimeslistBean>timeList,int currentPosition,String startDate,String endDate){
        String transformerName =  CubeOutTransformer.class.getSimpleName();
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transformerName);
            ABaseTransformer transformer = (ABaseTransformer) cls.newInstance();
            autoRow.getViewPager().setPageTransformer(true, transformer);
            //部分3D特效需要调整滑动速度
            if (transformerName.equals("StackTransformer")) {
                autoRow.setScrollDuration(1200);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

      autoRow.setCanLoop(false);
        autoRow.setManualPageable(false);

        autoRow.setPages(new CBViewHolderCreator<FootprintHolderView>() {
            @Override
            public FootprintHolderView createHolder() {

                SignRecordInfoTo recordInfoTo=new SignRecordInfoTo();
                recordInfoTo.setFootprintType(signType);
                recordInfoTo.setStartDate( startDate);
                recordInfoTo.setEndDate(endDate);
                recordInfoTo.setUserName(SignFragment.getWokerNameInfo());
                return new FootprintHolderView(recordInfoTo);
            }

        }, timeList);
        autoRow.setcurrentitem(currentPosition);
    }


}
