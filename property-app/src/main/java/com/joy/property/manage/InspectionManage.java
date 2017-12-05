package com.joy.property.manage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.home.ServiceReportFlowParam;
import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.complaint.ComplaintTablesActivity;
import com.joy.property.house.fragment.adapter.HouseManagerAdapter;
import com.joy.property.task.SideBar;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.SpUtil;
import com.joy.property.utils.StatisticsUtil;
import com.joyhome.nacity.app.MainApp;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

    /**
     * Created by Administrator on 2016/6/20.
     */
    public class InspectionManage extends BaseActivity
            implements View.OnClickListener {

        private PullToRefreshListView mPullToRefreshListView;
        private List<ServiceReportFlowTo> flowToList = new ArrayList<>();
        private ListView mList;
        private TextView mTitle;
    private HouseManagerAdapter mAdapter;
    private  List<ApartmentInfoTo>apartmentInfoTos=new ArrayList<>();
        private ImageView changePark;
        private boolean firstChange;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownermouth);
        findById();
        mList = mPullToRefreshListView.getRefreshableView();
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mAdapter = new HouseManagerAdapter(this);
        mAdapter.setList(flowToList);
        mList.setAdapter(mAdapter);
        setListCity();
        initData();
    }


    private void findById() {
        mTitle = (TextView) findViewById(R.id.title2);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        changePark = (ImageView) findViewById(R.id.changePark);
        changePark.setOnClickListener(this);
        SideBar sideBar = (SideBar) findViewById(R.id.sideBar);
        TextView   dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 璇ュ瓧姣嶉娆″嚭鐜扮殑浣嶇疆
                int position = mAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mList.setSelection(position);
                }

            }
        });

    }

    private void initData() {
        mTitle.setText("巡检管理");
        if (SpUtil.getString(getThisContext(), "limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A014"))
        {
            changePark.setVisibility(View.VISIBLE);
            mTitle.setText("巡检管理(住宅)");
        }

        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {

                String label = DateUtils.formatDateTime(
                        getThisContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                listViewPullToRefreshBase.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);

                setList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                String label = DateUtils.formatDateTime(
                        getThisContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                listViewPullToRefreshBase.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                setList();
            }
        });

    }

    /*
       拉取数据
     */
    private void setList() {
        HomeApi api = ApiClient.create(HomeApi.class);
        final ServiceReportFlowParam param = new ServiceReportFlowParam();
        param.setUsid(mUserHelper.getSid());
        //
        param.setCategory("51979B62-10E6-44C7-88B9-4B239B1CE02F");
        final CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "");
//        Log.i("99990", "setList: "+param.toString());
              api.reportFlow(param, new HttpCallback<MessageTo<List<ServiceReportFlowTo>>>(this) {
            @Override
            public void success(MessageTo<List<ServiceReportFlowTo>> msg, Response response) {
                dialog.dismiss();
                 if (msg.getSuccess() == 0) {
                    if (msg.getData() != null) {
                        flowToList.clear();
//                        Log.i("99991", "setList: "+msg.getData().toString());
//                        Log.i("99991", "setList: "+msg.getData().size());
                        flowToList.addAll(compara(msg.getData()));
//                        Log.i("99992", "setList: "+compara(msg.getData()).toString());
//                        Log.i("99992", "setList: "+compara(msg.getData()).size());

                        mAdapter.notifyDataSetChanged();
                    }
                    mPullToRefreshListView.onRefreshComplete();
                    mAdapter.setOnContinuePayClickListener(new HouseManagerAdapter.OnContinuePayClickListener() {
                        @Override
                        public void OnContinuePayClick(int position) {
                            ServiceReportFlowTo flowTo = flowToList.get(position);
                            StatisticsUtil.sendStatistics(mUserHelper.getSid(), "巡检管理-" + flowTo.getApartmentName(), getThisContext());
                            Intent intent = new Intent(getThisContext(), InspectionTablesActivity.class);
                            intent.putExtra("mode", flowTo.getApartmentSid());
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("flowTo", flowTo);
                            intent.putExtras(bundle);
                            //intent.putExtra("fto", flowTo.getApartmentName());
                            for (ApartmentInfoTo apartmentInfoTo : apartmentInfoTos) {
                                if (apartmentInfoTo.getApartmentSid().equals(flowTo.getApartmentSid())) {
                                    SpUtil.put(getThisContext(), "startTime", apartmentInfoTo.getStartTime());
                                    SpUtil.put(getThisContext(), "endTime", apartmentInfoTo.getEndTime());
                                    break;
                                }
                            }
                            startActivity(intent);
                        }
                    });


                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ServiceReportFlowTo flowTo = flowToList.get(position - 1);
                            Intent intent = new Intent(getThisContext(), InspectionTablesActivity.class);
                            intent.putExtra("mode", flowTo.getApartmentSid());
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("flowTo", flowTo);
                            intent.putExtras(bundle);
                            for (ApartmentInfoTo apartmentInfoTo : apartmentInfoTos) {
                                if (apartmentInfoTo.getApartmentSid().equals(flowTo.getApartmentSid())) {
                                    SpUtil.put(getThisContext(), "startTime", apartmentInfoTo.getStartTime());
                                    SpUtil.put(getThisContext(), "endTime", apartmentInfoTo.getEndTime());
                                    break;
                                }
                            }
                            startActivity(intent);

                        }
                    });

                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialog.dismiss();
                super.failure(error);
            }
        });

    }

    @Override
    protected Context getThisContext() {
        return InspectionManage.this;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.changePark:
                changePark();
                break;
        }
    }

    @Override
    protected String toPageName() {
        super.toPageName();
        return "巡检管理";
    }



    private void setListCity() {

        ApartmentApi api = ApiClient.create(ApartmentApi.class);
        final com.joyhome.nacity.app.util.CustomDialogFragment dialogFragment = new com.joyhome.nacity.app.util.CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        api.findByAll(new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(MainApp.mContext) {
            @Override
            public void success(MessageTo<List<ApartmentInfoTo>> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                if (msg.getSuccess() == 0) {
                    if (msg.getTag() != null) {
                        apartmentInfoTos.clear();
                        apartmentInfoTos.addAll(msg.getData());
                        setList();
                    }
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {

                super.failure(error);
            }
        });

    }
        private void changePark() {
            if (!firstChange){
                ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
                changePark.setBackgroundResource(R.drawable.selector_home);
                firstChange=true;
                setListCity();
                mTitle.setText("巡检管理(园区)");
            }else {
                ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
                changePark.setBackgroundResource(R.drawable.selector_park);
                firstChange=false;
                mTitle.setText("巡检管理(住宅)");
                setListCity();
            }
        }
//        public List<ServiceReportFlowTo> compara(List<ServiceReportFlowTo> infos){
//            List<ServiceReportFlowTo> mInfoTos=new ArrayList<>();
//
//            int k,j;
//            String[] fstr=new String[infos.size()];
//            for(k=0;k<infos.size();k++){
//
//                fstr[k]=getPingYin(infos.get(k).getApartmentName().substring(0,1));
//                infos.get(k).setmPinyin(fstr[k]);
//            }
//
//            Arrays.sort(fstr);
//            for(k=0;k<fstr.length;k++){
//                for(ServiceReportFlowTo info:infos)
//                {
//
//                    if(fstr[k].equals(info.getmPinyin())){
//                        mInfoTos.add(info);
//
//                    }
//                }
//            }
//
//            return mInfoTos;
//        }
//        public static String getPingYin(String inputString) {
//
//            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//
//            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//
//            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//
//            format.setVCharType(HanyuPinyinVCharType.WITH_V);
//
//            char[] input = inputString.trim().toCharArray();
//
//            String output = "";
//
//            try {
//
//                for (char curchar : input) {
//
//                    if (java.lang.Character.toString(curchar).matches(
//
//                            "[\\u4E00-\\u9FA5]+")) {
//
//                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(
//
//                                curchar, format);
//
//                        output += temp[0];
//
//                    } else
//
//                        output += java.lang.Character.toString(curchar);
//
//                }
//
//            } catch (BadHanyuPinyinOutputFormatCombination e) {
//
//                e.printStackTrace();
//
//            }
//
//            return output;
//
//        }
public List<ServiceReportFlowTo> compara(List<ServiceReportFlowTo> infos){
    List<ServiceReportFlowTo> mInfoTos=new ArrayList<>();

    int k,j;
    String[] fstr=new String[infos.size()];
    for(k=0;k<infos.size();k++){
        fstr[k]=getPingYin(infos.get(k).getApartmentName());
        infos.get(k).setmPinyin(fstr[k]);
    }

    Arrays.sort(fstr);
    for(k=0;k<fstr.length;k++){
        for(ServiceReportFlowTo info:infos)
        {

            if(fstr[k].equals(info.getmPinyin())){
                mInfoTos.add(info);

            }
        }
    }

    return mInfoTos;
}
        public static String getPingYin(String inputString) {

            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            format.setVCharType(HanyuPinyinVCharType.WITH_V);

            char[] input = inputString.trim().toCharArray();

            String output = "";

            try {

                for (char curchar : input) {

                    if (java.lang.Character.toString(curchar).matches(

                            "[\\u4E00-\\u9FA5]+")) {

                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(

                                curchar, format);

                        output += temp[0];

                    } else

                        output += java.lang.Character.toString(curchar);

                }

            } catch (BadHanyuPinyinOutputFormatCombination e) {

                e.printStackTrace();

            }

            return output;

        }

}
