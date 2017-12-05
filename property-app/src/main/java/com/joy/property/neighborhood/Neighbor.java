package com.joy.property.neighborhood;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;
import com.joy.common.application.KeyValue;
import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.ConfigUtil;
import com.joy.library.utils.DateUtil;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.neighborhood.adapter.NeighborManagerAdapter;
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


public class Neighbor extends BaseActivity
        implements OnClickListener {

    private PullToRefreshListView mPullToRefreshListView;
    private List<ServiceReportFlowTo> flowToList = new ArrayList<>();
    private ListView mList;
    private TextView mTitle;
    private NeighborManagerAdapter mAdapter;


    private   ApartmentInfoTo info = new ApartmentInfoTo();
    private ImageView changePark;
    private boolean firstChange;
    private SideBar sideBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbor_list);
        findById();

        mList = mPullToRefreshListView.getRefreshableView();
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mPullToRefreshListView.setMode(Mode.DISABLED);
        mAdapter = new NeighborManagerAdapter(this);
        mAdapter.setList(flowToList);
        mList.setAdapter(mAdapter);
        SpUtil.put(getThisContext(), "ParkNeighbor", false);
        setUserInfo();
        setList();
        initData();
        setSideBarTransparent();
    }

    private void setUserInfo() {
        mHelper=null;
        mHelper=ApartmentInfoHelper.getInstance(MainApp.mContext);
        ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
        SpUtil.put(getThisContext(), "ParkNeighbor", false);
    }


    private void findById() {
        mTitle = (TextView) findViewById(R.id.title2);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        changePark = (ImageView) findViewById(R.id.changePark);
        changePark.setOnClickListener(this);
        sideBar = (SideBar) findViewById(R.id.sideBar);
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
        mTitle.setText("邻居圈");
if (SpUtil.getString(getThisContext(),"limitPark")!=null&&SpUtil.getString(getThisContext(),"limitPark").contains("A009"))
      changePark.setVisibility(View.VISIBLE);
        if (ConfigUtil.getString(sp, KeyValue.KEY_USER_INFO, "").equals(SpUtil.getString(getThisContext(),"HomeInfo")))
            changePark.setBackgroundResource(R.drawable.selector_park);
        else
            changePark.setBackgroundResource(R.drawable.selector_home);
        mTitle.setText("邻居圈(住宅)");
        mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {
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
     *  拉取数据
     */
    private void setList() {

        NeighborApi api=ApiClient.create(NeighborApi.class);
        final CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "");
        api.getApartmentList(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ServiceReportFlowTo>>>(this) {

            @Override
            public void success(MessageTo<List<ServiceReportFlowTo>> msg, Response response) {

                dialog.dismiss();
                if (msg.getSuccess() == 0) {

                    if (msg.getData() != null) {
                        flowToList.clear();
                        flowToList.addAll(compara(msg.getData()));

                        mAdapter.notifyDataSetChanged();
                    }
                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener((parent, view, position, id) -> {

                        ServiceReportFlowTo flowTo = flowToList.get(position - 1);
                        StatisticsUtil.sendStatistics(mUserHelper.getSid(), "邻居圈-" + flowTo.getApartmentName(), getThisContext());

                        String SrflowTo = flowTo.getApartmentName();
                        SpUtil.put(Neighbor.this, "xf_title", SrflowTo);
                        Intent intent = new Intent(getThisContext(), NeighborActivity.class);
                        intent.putExtra("fto", flowTo);
                        info.setApartmentSid(flowTo.getApartmentSid());
                        info.setApartmentName(flowTo.getApartmentName());
                        mHelper.updateApartment(info, getThisContext());
                        startActivity(intent);

                        Log.i("我的", flowToList.get(position - 1).toString());

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
        return Neighbor.this;
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
        return "邻居圈";
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mHelper=null;
        mHelper=ApartmentInfoHelper.getInstance(MainApp.mContext);
        ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
        SpUtil.put(getThisContext(), "ParkNeighbor", false);
    }
    private void changePark() {

            ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
        Intent intent = new Intent(getThisContext(), NeighborActivity.class);
        info.setApartmentSid("");
        info.setApartmentName("");
        SpUtil.put(getThisContext(), "ParkNeighbor", true);
        mHelper.updateApartment(info, getThisContext());
        startActivity(intent);
    }

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

                if (Character.toString(curchar).matches(

                        "[\\u4E00-\\u9FA5]+")) {

                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(

                            curchar, format);

                    output += temp[0];

                } else

                    output += Character.toString(curchar);

            }

        } catch (BadHanyuPinyinOutputFormatCombination e) {

            e.printStackTrace();

        }

        return output;

    }

public void setSideBarTransparent(){

    mList.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
       switch (event.getAction()){
           case MotionEvent.ACTION_DOWN:

               if (sideBar.canHaveAnimation&&System.currentTimeMillis()-sideBar.firstTime>=2800||sideBar.isDismiss) {
                   setSideBarIn();
                   sideBar.canHaveAnimation=false;

               }

               sideBar.firstTime = System.currentTimeMillis();

               break;
           case MotionEvent.ACTION_UP:

               sideBar.mHandler.postDelayed(() -> {
    if (!sideBar.isDismiss) {

        if (System.currentTimeMillis()-sideBar.firstTime>=2000) {
            setSideBarOut();
            sideBar.canHaveAnimation = false;
        }
    }
  },2000);

               break;
       }



            return false;
        }
    });


}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setSideBarIn(){

        ObjectAnimator anim = ObjectAnimator.ofFloat(sideBar,"alpha", 0f,1f);
        anim.setDuration(1500);// 动画持续时间
        anim.start();
        sideBar.isDismiss=false;
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            sideBar.canHaveAnimation=true;

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    public void setSideBarOut(){
        ObjectAnimator anim = ObjectAnimator.ofFloat(sideBar,"alpha", 1f,0f);
        anim.setDuration(1500);// 动画持续时间
        anim.start();
        sideBar.isDismiss=true;
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                sideBar.canHaveAnimation=true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
