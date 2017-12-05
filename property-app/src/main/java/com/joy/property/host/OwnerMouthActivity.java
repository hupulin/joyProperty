package com.joy.property.host;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.jinyi.ihome.module.home.ServiceReportGradeTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.host.adapter.OwnerMouthAdapter;
import com.joy.property.task.SideBar;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.utils.SpUtil;
import com.joy.property.utils.StatisticsUtil;

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
 * Created by Admin on 2015-02-09
 */
public class OwnerMouthActivity extends BaseActivity
        implements OnClickListener {


    private PullToRefreshListView mPullToRefreshListView;
    private OwnerMouthAdapter mAdapter;
    private List<ServiceReportGradeTo> gradeToList = new ArrayList<>();
    private ListView mList;
    private TextView mTitle;
    private ImageView changePark;
    private boolean firstChange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownermouth_pinyin);
        findById();
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(Mode.DISABLED);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mAdapter = new OwnerMouthAdapter(getThisContext());
        mAdapter.setList(gradeToList);
        mList.setAdapter(mAdapter);
      setLists();
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
        mTitle.setText("满意度");
        if (SpUtil.getString(getThisContext(), "limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A004"))
        {
            changePark.setVisibility(View.VISIBLE);
            mTitle.setText("满意度(住宅)");
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
             // setList();
             setLists();
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

             //setList();
             setLists();
         }
     });
    }




    private void setLists() {
        HomeApi api = ApiClient.create(HomeApi.class);
        final CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "");
        api.reportGradeNew(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ServiceReportGradeTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<ServiceReportGradeTo>> msg, Response response) {
                dialog.dismiss();
                if (msg.getSuccess() == 0) {
                    if (msg.getData() != null) {
                        gradeToList.clear();
                        gradeToList.addAll(compara(msg.getData()));
                        mAdapter.notifyDataSetChanged();
                        Log.i("msg", "success:" + msg.getData().toString());
                    }


                    mList.setOnItemClickListener((parent, view, position, id) -> {
                        ServiceReportGradeTo flowTo = gradeToList.get(position - 1);
                        StatisticsUtil.sendStatistics(mUserHelper.getSid(), "满意度-" + flowTo.getApartmentName(), getThisContext());
                        Intent intent = new Intent(getThisContext(), OwnerMouthDetail.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("flowTo", flowTo);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    });
                } else {
                    Toast.makeText(getThisContext(),
                            msg.getMessage(), Toast.LENGTH_SHORT).show();
                }
                mPullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                dialog.dismiss();

            }
        });
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
    protected Context getThisContext() {
        return OwnerMouthActivity.this;
    }

    @Override
    protected String toPageName() {
        return "邻里口碑";
    }
    private void changePark() {
        if (!firstChange){
            ChangeParkUtil.changeToPark(getThisContext(),mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_home);
            firstChange=true;
            setLists();
            mTitle.setText("满意度(园区)");
        }else {
            ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
            changePark.setBackgroundResource(R.drawable.selector_park);
            firstChange=false;
            setLists();
            mTitle.setText("满意度(住宅)");
        }
    }
    public List<ServiceReportGradeTo> compara(List<ServiceReportGradeTo> infos){
        List<ServiceReportGradeTo> mInfoTos=new ArrayList<>();

        int k,j;
        String[] fstr=new String[infos.size()];
        for(k=0;k<infos.size();k++){
            fstr[k]=getPingYin(infos.get(k).getApartmentName());
            infos.get(k).setmPinyin(fstr[k]);
        }

        Arrays.sort(fstr);
        for(k=0;k<fstr.length;k++){
            for(ServiceReportGradeTo info:infos)
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
