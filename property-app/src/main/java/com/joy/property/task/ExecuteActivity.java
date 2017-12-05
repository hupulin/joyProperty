package com.joy.property.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceAssignParam;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.task.adapter.DispatchCenterAdapter;
import com.joy.property.task.adapter.SearchWorkerAdapter;
import com.joy.property.utils.CustomDialog;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2016/8/5.
 */
public class ExecuteActivity extends BaseActivity
        implements View.OnClickListener {
    private TextView mTitle;
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mList;
    private ServiceMainExpandTo mainExpandTo;
    private DispatchCenterAdapter mAdapter;
    private SearchWorkerAdapter sAdapter;
    private List<UserInfoTo> userInfoToList = new ArrayList<>();
    private String center;
    private String heart;
    private CustomDialog dialog;
    private MainApplication application;
    private List<UserInfoTo> userInfoToList_Q = new ArrayList<UserInfoTo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        application = (MainApplication) this.getApplicationContext();
        findById();
        initIntentDatas();
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        mAdapter = new DispatchCenterAdapter(this);
        mAdapter.setList(userInfoToList);
        mList.setAdapter(mAdapter);
        setList();
        initDatas();
    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.title);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        RelativeLayout autoCompleteTextView = (RelativeLayout) findViewById(R.id.act_content);
        autoCompleteTextView.setOnClickListener(this);
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
        mainExpandTo = (ServiceMainExpandTo) getIntent().getSerializableExtra("mode");
        center = getIntent().getStringExtra("center");
        heart = getIntent().getStringExtra("heart");
    }

    private void initDatas() {
        if (!TextUtils.isEmpty(center)) {
            if (center.equals("center")) {
                mTitle.setText("派单中心");
            }
        }

        if (!TextUtils.isEmpty(heart)) {
            if (heart.equals("heart")) {
                mTitle.setText("转发中心");
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

        ServiceMainExpandTo app_serviceMainExpandTo = application.getApp_ServiceMainExpandTo();
        param.setTypeSid(app_serviceMainExpandTo.getTypeSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
        System.out.println(param+"param");
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
                            Intent intent = new Intent(getThisContext(), SelectPersonPopupWindow.class);
                            application.setApp_execute_man(userInfoTo);

                            intent.putExtra("assist", userInfoTo);
                            intent.putExtra("mode", mainExpandTo);
                            startActivity(intent);
                            onBackPressed();
                        }
                    });

                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                super.failure(error);
            }
        });

    }
////////////////////////////////////////////////////////

    /**
     * 搜索
     **/
   /* private void SearchShowDialog() {
        dialog = new CustomDialog(this, R.layout.dialog_search_man, R.style.myDialogTheme);
        ImageView mEmpty = (ImageView) dialog.findViewById(R.id.iv_empty);
        final AutoCompleteTextView auto = (AutoCompleteTextView) dialog.findViewById(R.id.act_content);
        ImageView ivBack = (ImageView) dialog.findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(auto.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                dialog.dismiss();
            }
        });
        ivBack.setOnClickListener(v -> dialog.dismiss());
        mEmpty.setOnClickListener(v -> auto.setText(""));
        final ListView searchList = (ListView) dialog.findViewById(R.id.search_list);
        mEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto.setText("");
            }
        });
        auto.setFocusable(true);
        auto.setFocusableInTouchMode(true);
        auto.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) auto.getContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(auto, 0);
            }
        }, 300);

        */

    /**
     * 设置显示输入法
     *//*

        auto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, final int start, int before, int count) {
                if (s.length() > 0) {
//
                    QueryParam queryParam = new QueryParam();
                    queryParam.setPageIndex(0);
                    if(isNumeric(s.toString())==true){
                        queryParam.setOwnerPhone(s.toString());
                        queryParam.setFilter(s.toString());
                        queryParam.setOwnerName("");
                    }else {
                        queryParam.setOwnerName(s.toString());
                        queryParam.setFilter(s.toString());
                        queryParam.setOwnerPhone("");
                    }


                    HomeApi api = ApiClient.create(HomeApi.class);
                    api.searchWorker(queryParam, new HttpCallback<MessageTo<List<UserInfoTo>>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<List<UserInfoTo>> msg, Response response) {

                            if (msg.getSuccess() == 0) {
                                final List<UserInfoTo> infoList = msg.getData();
                                sAdapter = new SearchWorkerAdapter(getThisContext());
                                sAdapter.setList(infoList);
                                searchList.setAdapter(sAdapter);
                                searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        UserInfoTo userInfoTo = infoList.get(position);
                                        application.setApp_execute_man(userInfoTo);
                                        setResult(Activity.RESULT_OK);
                                        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                                                .hideSoftInputFromWindow(auto.getWindowToken(),
                                                        InputMethodManager.HIDE_NOT_ALWAYS);

                                        startActivity(intent);
                                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                        finish();

                                    }
                                });

                            } else {
                                Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            super.failure(error);
                            System.out.println("访问失败"+error);
                        }
                    });
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }*/
    private void SearchShowDialog() {
        dialog = new CustomDialog(this, R.layout.dialog_search_man, R.style.myDialogTheme);
        ImageView mEmpty = (ImageView) dialog.findViewById(R.id.iv_empty);
        final AutoCompleteTextView auto = (AutoCompleteTextView) dialog.findViewById(R.id.act_content);
        ImageView ivBack = (ImageView) dialog.findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(auto.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                dialog.dismiss();
            }
        });
        ivBack.setOnClickListener(v -> dialog.dismiss());
        mEmpty.setOnClickListener(v -> auto.setText(""));
        final ListView searchList = (ListView) dialog.findViewById(R.id.search_list);
        mEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto.setText("");
            }
        });
        auto.setFocusable(true);
        auto.setFocusableInTouchMode(true);
        auto.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) auto.getContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(auto, 0);
            }
        }, 300);

        /**
         * 设置显示输入法
         */

        auto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                userInfoToList_Q.clear();
            }

            @Override
            public void onTextChanged(CharSequence s, final int start, int before, int count) {
                if (s.length() > 0) {
                    for (int i = 0; i < userInfoToList.size(); i++) {
                        UserInfoTo userInfoTo_s1 = userInfoToList.get(i);
                        if (isNumeric(s.toString()) == true) {
                            if (isContain(userInfoTo_s1.getPhone(), s.toString()) == true) {

                                userInfoToList_Q.add(userInfoTo_s1);
                            }
                        } else {
                            if (isContain(userInfoTo_s1.getName(), s.toString()) == true) {

                                userInfoToList_Q.add(userInfoTo_s1);
                            }
                        }
                    }
                    sAdapter = new SearchWorkerAdapter(getThisContext());
                    sAdapter.setList(userInfoToList_Q);
                    searchList.setAdapter(sAdapter);
                    searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            UserInfoTo userInfoTo_Q = userInfoToList_Q.get(position);
                            application.setApp_execute_man(userInfoTo_Q);
                            setResult(Activity.RESULT_OK);
                            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                                    .hideSoftInputFromWindow(auto.getWindowToken(),
                                            InputMethodManager.HIDE_NOT_ALWAYS);
                            Intent intent = new Intent(ExecuteActivity.this, SelectPersonPopupWindow.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish();

                        }
                    });
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
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
        return ExecuteActivity.this;
    }

    public List<UserInfoTo> compara(List<UserInfoTo> infos) {
        List<UserInfoTo> mInfoTos = new ArrayList<>();

        int k, j;
        String[] fstr = new String[infos.size()];
        for (k = 0; k < infos.size(); k++) {
            fstr[k] = getPingYin(infos.get(k).getName());

        }

        Arrays.sort(fstr);
        for (k = 0; k < fstr.length; k++) {
            for (UserInfoTo info : infos) {

                if (fstr[k].equals(getPingYin(info.getName()))) {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent(getThisContext(), SelectPersonPopupWindow.class);
                startActivity(intent);
                onBackPressed();
                break;
            case R.id.act_content:
                SearchShowDialog();
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
            startActivity(new Intent(getThisContext(), SelectPersonPopupWindow.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected String toPageName() {

        return "转发中心";
    }
}
