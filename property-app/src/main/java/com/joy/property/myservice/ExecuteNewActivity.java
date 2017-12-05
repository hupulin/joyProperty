package com.joy.property.myservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceAssignParam;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.task.SideBar;
import com.joy.property.task.adapter.DispatchCenterAdapter;
import com.joy.property.task.adapter.SearchWorkerAdapter;
import com.joy.property.vehicle.adapter.RefreshEventOther;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2016/8/5.
 */
public class ExecuteNewActivity extends BaseActivity
        implements View.OnClickListener {

    private PullToRefreshListView mPullToRefreshListView;
    private ListView mList;

    private DispatchCenterAdapter mAdapter;
    private SearchWorkerAdapter sAdapter;
    private List<UserInfoTo> userInfoToList = new ArrayList<>();
    private String center;
    private String heart;

    private MainApplication application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        application = (MainApplication) this.getApplicationContext();
        findById();
        initIntentDatas();
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mAdapter = new DispatchCenterAdapter(this);
        mAdapter.setList(userInfoToList);
        mList.setAdapter(mAdapter);
        setList();
        initDatas();
    }


    private void findById() {
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        findViewById(R.id.cancel).setOnClickListener(this);
        SideBar sideBar = (SideBar) findViewById(R.id.sideBar);
        TextView dialog = (TextView) findViewById(R.id.dialog);
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

    private void initIntentDatas() {

        center = getIntent().getStringExtra("center");
        heart = getIntent().getStringExtra("heart");
    }

    private void initDatas() {
        if (!TextUtils.isEmpty(center)) {
            if (center.equals("center")) {

            }
        }

        if (!TextUtils.isEmpty(heart)) {
            if (heart.equals("heart")) {

            }
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

    private void setList() {

        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceAssignParam param = new ServiceAssignParam();
        param.setGroupUserSid(mUserHelper.getSid());


        param.setTypeSid(getIntent().getStringExtra("typeSid"));

       CustomDialogFragment dialogFragment=new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
        api.assignService(param, new HttpCallback<MessageTo<List<UserInfoTo>>>(this) {
            @Override
            public void success(MessageTo<List<UserInfoTo>> msg, Response response) {
               dialogFragment.dismiss();
                if (msg.getSuccess() == 0) {
                    userInfoToList.clear();
                    List<UserInfoTo> toList = new ArrayList<>();
                    if (msg.getData() != null && !msg.getData().isEmpty()) {
                        for (UserInfoTo infoTo : msg.getData()) {
                            if (infoTo.getName() != null) {
                                toList.add(infoTo);
                            }
                        }
                        userInfoToList.addAll(compara(toList));
                        toList.clear();
                    } else {
                        userInfoToList.addAll(msg.getData());
                        Toast.makeText(getThisContext(), "信息为空，请联系悦嘉家工作人员！",
                                Toast.LENGTH_LONG).show();
                    }

                    mAdapter.notifyDataSetChanged();
                    mPullToRefreshListView.onRefreshComplete();

                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            UserInfoTo userInfoTo = userInfoToList.get(position - 1);
                            Intent intent = new Intent();
                            application.setApp_execute_man(userInfoTo);

                            intent.putExtra("assist", userInfoTo);
                            setResult(10, intent);

                            EventBus.getDefault().post(new RefreshEventOther(userInfoTo.getSid(), 4, userInfoTo.getName(), ""));
                            finish();
                        }
                    });

                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {

                dialogFragment.dismiss();
            }
        });

    }

    /**
     * 判断字符串是否包含某个字符
     **/
    public static boolean isContain(String s1, String s2) {
        return s1.contains(s2);
    }

    /**
     * 判断字符串是否仅为数字
     **/
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    @Override
    protected Context getThisContext() {
        return ExecuteNewActivity.this;
    }

    public List<UserInfoTo> compara(List<UserInfoTo> infos) {
        List<UserInfoTo> mInfoTos = new ArrayList<>();

        int k, j;
        String[] fstr = new String[infos.size()];
        for (k = 0; k < infos.size(); k++) {
            fstr[k] = getPingYin(infos.get(k).getName());
             infos.get(k).setmPinyin(fstr[k]);
        }

        Arrays.sort(fstr);
        for (k = 0; k < fstr.length; k++) {
            for (UserInfoTo info : infos) {

                if (fstr[k].equals(info.getmPinyin())) {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:

                onBackPressed();
                break;
            case R.id.act_content:

                break;
            case R.id.cancel:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //do something...

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected String toPageName() {

        return "转发中心";
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

}
