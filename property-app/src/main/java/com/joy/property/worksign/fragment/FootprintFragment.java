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
import com.joyhome.nacity.app.util.CustomDialogFragment;

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
    private Handler handler = new Handler();
    private List<MyPrintTo.TimeslistBean> autoRowTimeList = new ArrayList<>();
    private int lastPosition;
    private TextView topTime;
    private long netTime;
    private LinearLayout autoRowLayout;
    private TextView totalSignNumber;


    public FootprintFragment(long netTime) {
        this.netTime = netTime;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_footprint, container, false);
        findView();
        System.out.println(netTime + "netTime===");

        getData((DateUtil.longToString(netTime, DateUtil.mFormatDateString)), DateUtil.longToString(netTime, DateUtil.mFormatDateString));
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
        autoRowLayout = (LinearLayout) rootView.findViewById(R.id.auto_row_layout);
        totalSignNumber = (TextView) rootView.findViewById(R.id.total_sign_number);

    }


    private void getData(String startTime, String endTime) {
        System.out.println(startTime + "==" + endTime);
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
        TelephonyManager tm = (TelephonyManager) getThisContext().getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = tm.getDeviceId();
        SignJsonTo jsonTo = new SignJsonTo();
        jsonTo.setTradeType("GetReport");
        jsonTo.setUniqueStr(mUserHelper.getSid());
        jsonTo.setReportType(signType);
        jsonTo.setStartDate(startTime);
        jsonTo.setOpenId(mUserHelper.getSid());
        jsonTo.setUniqueStr(IMEI);
        jsonTo.setEndDate(endTime);

        SXHttpUtils.requestPostData(getActivity(),jsonTo, new SXHttpUtils.LoadListener() {
            @Override
            public void onLoadSuccess(String result) {
                dialogFragment.dismiss();
                SignMessageTo<MyPrintTo> msg = new Gson().fromJson(new String(WLHSecurityUtils.decrypt(result.getBytes())), SignMessageTo.class);
                MyPrintTo myPrintTo = new Gson().fromJson(new Gson().toJson(msg.getResultContent()), MyPrintTo.class);
                if (myPrintTo != null && myPrintTo.getTimeslist() != null && myPrintTo.getTypelist() != null)
                    initView(myPrintTo, startTime, endTime);

                System.out.println(msg + "myPrintTo");
            }

            @Override
            public void onLoadError() {
                dialogFragment.dismiss();
            }
        });
    }


    public void initView(MyPrintTo myPrintTo, String startTime, String endTime) {
        lastPosition = 0;
        autoRowTimeList.clear();
        autoRowTimeList.add(null);
        //因为动画，轮播图只加三个数据，首次进入的时候，如果第一个时间段没有打卡记录，展示最后一次打卡数据
        if (myPrintTo.getTimeslist().get(0).getBktotal() == 0) {
            for (int i = myPrintTo.getTimeslist().size() - 1; i >= 0; i--) {
                if (myPrintTo.getTimeslist().get(i).getBktotal() > 0) {
                    autoRowTimeList.add(myPrintTo.getTimeslist().get(i));
                    break;
                }
            }
        } else
            autoRowTimeList.add(myPrintTo.getTimeslist().get(0));


        autoRowTimeList.add(myPrintTo.getTimeslist().get(1));
        setAutoRow(autoRowTimeList, 1, startTime, endTime);
        final SmoothLineChartView chartView = (SmoothLineChartView) rootView.findViewById(R.id.smoothChartView);

        RelativeLayout.LayoutParams layoutParams;
        if (signType == 0) {
            layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 2214.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));
            layoutParams.setMargins((int) (getScreenWidthPixels(getThisContext()) * -7.0 / 720), 0, 0, 0);
        } else if (signType == 1) {
            layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 685.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));
            layoutParams.setMargins((int) (getScreenWidthPixels(getThisContext()) * -7.0 / 720), 0, 0, 0);

        } else {
            if (myPrintTo.getTimeslist().size() == 31)
                layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 2844.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));
            else if (myPrintTo.getTimeslist().size() == 30)
                layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 2753.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));
            else if (myPrintTo.getTimeslist().size() == 29)
                layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 2665.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));
            else
                layoutParams = new RelativeLayout.LayoutParams((int) (getScreenWidthPixels(getThisContext()) * 2577.0 / 720), (int) (getScreenWidthPixels(getThisContext()) * 420.0 / 720));

            layoutParams.setMargins((int) (getScreenWidthPixels(getThisContext()) * -7.0 / 720), 0, 0, 0);
        }


        chartView.setLayoutParams(layoutParams);
        chartView.setCustomBorder(true);
        chartView.setSelectPosition(0);
        chartView.setTextColor(Color.WHITE);
        chartView.setTextSize(10);
        chartView.setTextOffset(4);

        chartView.enableShowTag(true);
        chartView.enableDrawArea(true);
        chartView.setLineColor(Color.parseColor("#00000000"));
        chartView.setCircleColor(Color.parseColor("#FFfbcc3b"));
        chartView.setInnerCircleColor(Color.parseColor("#ffffff"));
        chartView.setNodeStyle(SmoothLineChartView.NODE_STYLE_RING);
        List<Float> data = new ArrayList<>();
//        data.add(-1f);
        List<String> x = new ArrayList<>();


        /**
         * 添加时间分割线
         */
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
        //最下面类型信息
        totalSignNumber.setText("在本段时间内您一共签到" + myPrintTo.getTotalsign() + "次");
        workContentLayout.removeAllViews();
        for (int i = 0; i < myPrintTo.getTypelist().size(); i++) {
            View mView = View.inflate(getThisContext(), R.layout.footpring_work_content_item, null);
            MyPrintTo.TypelistBean typeTo = myPrintTo.getTypelist().get(i);
            ((TextView) mView.findViewById(R.id.work_content)).setText(typeTo.getTpname());
            ((TextView) mView.findViewById(R.id.sign_count)).setText(typeTo.getTpnum() + "");
            workContentLayout.addView(mView);
        }

        if (signType == 0)
            topTime.setText(myPrintTo.getTimeslist().get(0).getBkey().substring(5) + "至" + myPrintTo.getTimeslist().get(myPrintTo.getTimeslist().size() - 1).getBkey().substring(5));
        else
            topTime.setText(myPrintTo.getTimeslist().get(0).getBkey() + "至" + myPrintTo.getTimeslist().get(myPrintTo.getTimeslist().size() - 1).getBkey());
//        data.add(-1f);
//
//        x.add("3-12");
//        x.add("3-12");


        chartView.setData(data, x);
        chartView.setMaxY(maxTotal + 10);
        chartView.setMinY(0);

      /*
       足迹上的点点击
       */
        chartView.setOnChartClickListener((position, value) -> {
            MyPrintTo.TimeslistBean timeTo = myPrintTo.getTimeslist().get(position);
            if (position == lastPosition)
                return;
            autoRowTimeList.clear();
            autoRowTimeList.add(null);
            if (lastPosition < position) {


                autoRowTimeList.add(myPrintTo.getTimeslist().get(autoRow.getCurrentItem()));
                autoRowTimeList.add(timeTo);
                setAutoRow(autoRowTimeList, 1, startTime, endTime);

                handler.postDelayed(() -> {
                    autoRow.setcurrentitem(2);
                }, 100);

            }
            if (lastPosition > position) {

                autoRowTimeList.add(timeTo);
                autoRowTimeList.add(myPrintTo.getTimeslist().get(autoRow.getCurrentItem()));
                setAutoRow(autoRowTimeList, 2, startTime, endTime);

                handler.postDelayed(() -> {
                    autoRow.setcurrentitem(1);
                }, 100);


            }

            lastPosition = position;
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
        if (signType == 0)
            getData(DateUtil.longToString(millseconds, DateUtil.mFormatDateString), DateUtil.longToString(millseconds, DateUtil.mFormatDateString));
        else
            getData(DateUtil.getFirstDayOfMonth(Integer.parseInt(DateUtil.longToString(millseconds, DateUtil.mDateFormatString).substring(0, 4)), Integer.parseInt(DateUtil.longToString(millseconds, DateUtil.mDateFormatString).substring(6, 7)), true, netTime), DateUtil.getLastDayOfMonth(Integer.parseInt(DateUtil.longToString(millseconds, DateUtil.mDateFormatString).substring(0, 4)), Integer.parseInt(DateUtil.longToString(millseconds, DateUtil.mDateFormatString).substring(6, 7)), true, netTime));
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
                getData((DateUtil.longToString(netTime, DateUtil.mFormatDateString)), DateUtil.longToString(netTime, DateUtil.mFormatDateString));

                break;
            case R.id.week:
                day.setTextColor(Color.parseColor("#999999"));
                week.setTextColor(Color.parseColor("#333333"));
                month.setTextColor(Color.parseColor("#999999"));
                selectTime.setVisibility(View.GONE);
                signType = 1;
                getData(DateUtil.getDateString(DateUtil.getLastWeek(netTime).get("monday"), DateUtil.mFormatDateString), DateUtil.getDateString(DateUtil.getLastWeek(netTime).get("sunday"), DateUtil.mFormatDateString));
                break;
            case R.id.month:
                day.setTextColor(Color.parseColor("#999999"));
                week.setTextColor(Color.parseColor("#999999"));
                month.setTextColor(Color.parseColor("#333333"));
                signType = 2;
                selectTime.setVisibility(View.VISIBLE);
                getData(DateUtil.getFirstDayOfMonth(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), false, netTime), DateUtil.getLastDayOfMonth(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), false, netTime));
                break;
            case R.id.select_time:
                selectTime(signType);
                break;
        }
    }

    public void setAutoRow(List<MyPrintTo.TimeslistBean> timeList, int currentPosition, String startDate, String endDate) {
        autoRowLayout.removeAllViews();
        autoRow = new ConvenientBanner(getThisContext(), true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getScreenWidthPixels(getThisContext()), (int) (getScreenWidthPixels(getThisContext()) * 200.0 / 720));
        autoRow.setLayoutParams(layoutParams);
        autoRowLayout.addView(autoRow);
        String transformerName = CubeOutTransformer.class.getSimpleName();
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

                SignRecordInfoTo recordInfoTo = new SignRecordInfoTo();
                recordInfoTo.setFootprintType(signType);
                recordInfoTo.setStartDate(startDate);
                recordInfoTo.setEndDate(endDate);
                recordInfoTo.setUserName(SignFragment.getWokerNameInfo());
                return new FootprintHolderView(recordInfoTo);
            }

        }, timeList);
        autoRow.setcurrentitem(currentPosition);
    }

    /**
     * 从其它页面，都要刷新足迹
     */
    public void reLoadingData() {
        if (signType == 0)
            getData((DateUtil.longToString(netTime, DateUtil.mFormatDateString)), DateUtil.longToString(netTime, DateUtil.mFormatDateString));
        else if (signType == 1)
            getData(DateUtil.getDateString(DateUtil.getLastWeek(netTime).get("monday"), DateUtil.mFormatDateString), DateUtil.getDateString(DateUtil.getLastWeek(netTime).get("sunday"), DateUtil.mFormatDateString));
        else if (signType == 2)
            getData(DateUtil.getFirstDayOfMonth(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), false, netTime), DateUtil.getLastDayOfMonth(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), false, netTime));

    }
}
