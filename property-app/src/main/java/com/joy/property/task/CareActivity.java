package com.joy.property.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
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
import com.joy.property.task.adapter.CaredCenterAdapter;
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
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2016/8/9.
 */
public class CareActivity extends BaseActivity
        implements View.OnClickListener {
    private ListView mList, cList;
    private ServiceMainExpandTo mainExpandTo;
    private String apartmentSid = "";
    private DispatchCenterAdapter mAdapter;
    private List<UserInfoTo> userInfoToList = new ArrayList<>();
    private UserInfoTo userInfoTo, userInfoTo_copy, userInfoTo_Y;
    private SlideBar mSlideBar;
    private TextView float_letter;
    private ScrollView sv;
    private SearchWorkerAdapter sAdapter;
    private CaredCenterAdapter cAdapter;

    private MainApplication application;
    private CustomDialog dialog;
    private List<UserInfoTo> userInfoToList_S = new ArrayList<>();
    private List<UserInfoTo> userInfoToList_Y = new ArrayList<>();
    private boolean flag = false;
    private List<UserInfoTo> userInfoToList_copy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_care);
        application = (MainApplication) this.getApplicationContext();
        findById();
        initIntentDatas();
        sv = (ScrollView) findViewById(R.id.act_solution_1_sv);
        sv.smoothScrollTo(0, 0);
        mAdapter = new DispatchCenterAdapter(this);
        mList = (ListView) this.findViewById(R.id.care_man_listView);
        cAdapter = new CaredCenterAdapter(this);
        cList = (ListView) this.findViewById(R.id.list_compat);
        setList();
        mSlideBar.setOnTouchLetterChangeListenner((boolean isTouched, String s)-> {

                float_letter.setText(s);
                if (isTouched) {
                    float_letter.setVisibility(View.VISIBLE);
                } else {
                    float_letter.postDelayed(()-> {
                            float_letter.setVisibility(View.GONE);

                    }, 100);
                }
                int position = mAdapter.getPositionForSection(s.charAt(0));//这个array就是传给自定义Adapter的
                mList.setSelection(position);//调用ListView的setSelection()方法就可实现了
            }
        );


    }


    private void findById() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mSlideBar = (SlideBar) findViewById(R.id.slideBar);
        float_letter = (TextView) findViewById(R.id.float_letter);
        RelativeLayout autoCompleteTextView = (RelativeLayout) findViewById(R.id.act_content);
        autoCompleteTextView.setOnClickListener(this);
    }

    private void initIntentDatas() {
        mainExpandTo = (ServiceMainExpandTo) getIntent().getSerializableExtra("mode");
        apartmentSid = getIntent().getStringExtra("toSid");
    }

    /**
     * 拉数据
     **/
    private void setList() {

        HomeApi api = ApiClient.create(HomeApi.class);
        ServiceAssignParam param = new ServiceAssignParam();
        param.setGroupUserSid(mUserHelper.getSid());

        ServiceMainExpandTo app_serviceMainExpandTo = application.getApp_ServiceMainExpandTo();
        param.setTypeSid(app_serviceMainExpandTo.getTypeSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");

        api.assignService(param, new HttpCallback<MessageTo<List<UserInfoTo>>>(this) {
            @Override
            public void success(MessageTo<List<UserInfoTo>> msg, Response response) {
                dialogFragment.dismiss();
                if (msg.getSuccess() == 0) {
                    userInfoToList_Y.addAll(msg.getData());
                    userInfoToList.clear();
                    userInfoToList.addAll(msg.getData());

                    List<UserInfoTo> userInfoToList_twice = application.getApp_care_man();

                    /**
                     *剔除已选的关注人
                     **/
                    for (int i = 0; i < userInfoToList_twice.size(); i++) {
                        UserInfoTo fuck_you = userInfoToList_twice.get(i);
                        System.out.println("还好吗" + fuck_you.getName());
                    }
                    boolean ret = userInfoToList.removeAll(userInfoToList_twice);
                    System.out.println("有用吗" + ret);

                    screenBlackNameList(userInfoToList, userInfoToList_twice);

                    mAdapter.setList(userInfoToList);
                    mList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    System.out.println("userInfoToList:" + JSON.toJSONString(userInfoToList));
                    if (application.getApp_care_man() != null) {
                        findViewById(R.id.lv).setVisibility(View.VISIBLE);
                        userInfoToList_copy = userInfoToList_twice;
                        cAdapter.setList(userInfoToList_copy);
                        cList.setAdapter(cAdapter);
                        cAdapter.notifyDataSetChanged();
                        cList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                userInfoTo_copy = userInfoToList_copy.get(position);
                                userInfoToList.add(userInfoTo_copy);
                                cAdapter.remove(userInfoTo_copy);
                                cAdapter.notifyDataSetChanged();
                                mAdapter.setList(userInfoToList);
                                mList.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }

                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            userInfoTo = userInfoToList.get(position);
                            userInfoToList_copy.add(userInfoTo);
                            mAdapter.remove(userInfoTo);
                            mAdapter.notifyDataSetChanged();
                            findViewById(R.id.lv).setVisibility(View.VISIBLE);
                            application.setApp_care_man(userInfoToList_copy);
                            cAdapter.setList(userInfoToList_copy);
                            cList.setAdapter(cAdapter);
                            cAdapter.notifyDataSetChanged();
                            cList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    userInfoTo_copy = userInfoToList_copy.get(position);
                                    userInfoToList.add(userInfoTo_copy);
                                    cAdapter.remove(userInfoTo_copy);
                                    cAdapter.notifyDataSetChanged();
                                    mAdapter.setList(userInfoToList);
                                    mList.setAdapter(mAdapter);
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
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
                userInfoToList_S.clear();
            }

            @Override
            public void onTextChanged(CharSequence s, final int start, int before, int count) {
                if (s.length() > 0) {
                    for (int i = 0; i < userInfoToList_Y.size(); i++) {
                        UserInfoTo userInfoTo_s1 = userInfoToList_Y.get(i);
                        if (isNumeric(s.toString()) == true) {
                            if (isContain(userInfoTo_s1.getPhone(), s.toString()) == true) {

                                userInfoToList_S.add(userInfoTo_s1);
                            }
                        } else {
                            if (isContain(userInfoTo_s1.getName(), s.toString()) == true) {

                                userInfoToList_S.add(userInfoTo_s1);
                            }
                        }
                    }
                    sAdapter = new SearchWorkerAdapter(getThisContext());
                    sAdapter.setList(userInfoToList_S);
                    searchList.setAdapter(sAdapter);
                    searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            UserInfoTo userInfoTo_S = userInfoToList_S.get(position);
                            for (int i = 0; i < userInfoToList_copy.size(); i++) {
                                UserInfoTo userInfoTo_X = userInfoToList_copy.get(i);
                                if (userInfoTo_X.getSid().equals(userInfoTo_S.getSid())) {
                                    flag = true;
                                    continue;
                                }
                            }
                            if (flag == false) {
                                if (userInfoToList_copy != null) {
                                    userInfoTo_Y = new UserInfoTo();
                                    userInfoTo_Y = userInfoTo_S;
                                    userInfoToList_copy.add(userInfoTo_Y);
                                    mAdapter.remove(userInfoTo_S);
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mAdapter.remove(userInfoTo_S);
                                    mAdapter.notifyDataSetChanged();
                                    findViewById(R.id.lv).setVisibility(View.VISIBLE);
                                    userInfoToList_copy.add(userInfoTo_Y);
                                    application.setApp_care_man(userInfoToList_copy);
                                    cAdapter.setList(userInfoToList_copy);
                                    cList.setAdapter(cAdapter);
                                    cAdapter.notifyDataSetChanged();
                                }
                            }
                            dialog.dismiss();
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
     * Java中如何循环删除一个集合(如List)中的多个元素
     * 今天我需要从一个java的集合中，根据另一个集合的内容，删除第一个集合中不特定的元素
     * 使用Iterator的remove()方法删除集合中的元素
     * 注意，一次Iterator的next()方法，不能多次调用remove()方法。否则会抛出异常。
     *
     * @param source
     * @param blackNameList
     **/
    private void screenBlackNameList(List<UserInfoTo> source, List<UserInfoTo> blackNameList) {
        Iterator<UserInfoTo> sourceIt = source.iterator();

        while (sourceIt.hasNext()) {
            UserInfoTo tmpSharedBoardSmsWrapper = sourceIt.next();
            Iterator<UserInfoTo> blackNameListIt = blackNameList.iterator();
            while (blackNameListIt.hasNext()) {
                UserInfoTo tmpBlackNameListModel = blackNameListIt.next();
                if (tmpSharedBoardSmsWrapper.getSid().equals(tmpBlackNameListModel.getSid())) {
                    sourceIt.remove();
                    break;
                }
            }
        }
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
        return CareActivity.this;
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

                intent.putExtra("aSid", apartmentSid);
                intent.putExtra("assist", userInfoTo);
                intent.putExtra("mode", mainExpandTo);
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
