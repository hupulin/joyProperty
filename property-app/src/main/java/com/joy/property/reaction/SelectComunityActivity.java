package com.joy.property.reaction;

/**
 * Created by usb on 2017/3/2.
 */



import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.apartment.ApartmentSearchParam;
import com.joy.common.api.ApartmentApi;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.ReactionApi;
import com.joy.common.helper.ApartmentInfoHelper;
import com.joy.common.helper.UserInfoHelper;
import com.joy.library.utils.ConfigUtil;
import com.joy.library.utils.FileUtil;
import com.joy.property.MainApp;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.constant.Constant;
import com.joy.property.reaction.adapter.ApartmentAdapter;
import com.joy.property.utils.citypicker.adapter.CityListAdapter;
import com.joy.property.utils.citypicker.adapter.ResultListAdapter;
import com.joy.property.utils.citypicker.view.SideLetterBar;
import com.joyhome.nacity.app.util.CustomDialog;
import com.joyhome.nacity.app.util.CustomDialogFragment;
import com.joy.property.task.SideBar;
import com.joyhome.nacity.app.util.SpUtil;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class SelectComunityActivity extends BaseActivity
        implements OnClickListener {

    public Vibrator mVibrator;

    private PullToRefreshListView mPullToRefreshListView;
    private ListView mList;
    private ApartmentAdapter adapter;
    private CustomDialogFragment customDialog=new CustomDialogFragment();
    private List<HashMap<String, String>> mapList = new ArrayList<>();
    private CustomDialog dialog;
    List<ApartmentInfoTo> infoList = new ArrayList<>();
    List<ApartmentInfoTo> searchList = new ArrayList<>();
    private CityListAdapter mCityAdapter;
    private ResultListAdapter mResultAdapter;
    private ListView mListView;
    private ListView mResultListView;
    private SideLetterBar mLetterBar;
    private EditText searchBox;
    private ImageView clearBtn;
    private ImageView backBtn;
    private ViewGroup emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_comunity);
        findById();

        setList();
    }

    private void findById() {



    }




    @SuppressWarnings("unchecked")
    private void setList() {
        ReactionApi api = ApiClient.create(ReactionApi.class);


        //   customDialog.dismissAllowingStateLoss();
        customDialog.show(getSupportFragmentManager(), "");
        api.getReactionTitle(mUserHelper.getSid(), new HttpCallback<MessageTo<List<ApartmentInfoTo>>>(MainApp.mContext) {
            @Override
            public void success(MessageTo<List<ApartmentInfoTo>> msg, retrofit.client.Response response) {
                customDialog.dismissAllowingStateLoss();

                if (msg.getSuccess() == 0) {

                    infoList.addAll(compara(msg.getData()));

                    mCityAdapter = new CityListAdapter(getThisContext(), infoList);

                    mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
                        @Override
                        public void onCityClick(String name) {
                            for (int i = 0;i < infoList.size(); i ++)
                               if(name.equals(infoList.get(i).getApartmentName())){
                                   back(infoList.get(i).getApartmentSid());
                               }

                        }

                        @Override
                        public void onLocateClick() {

                        }
                    });

                    mResultAdapter = new ResultListAdapter(getThisContext(), null);


                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
                initView();
            }

            @Override
            public void failure(RetrofitError error) {
                //          customDialog.dismissAllowingStateLoss();
                super.failure(error);
            }
        });

    }
    @Override
    protected void onStop() {
        //     mLocClient.stop();
        super.onStop();
    }







    @Override
    protected Context getThisContext() {
        return SelectComunityActivity.this;
    }
    //排序首字母
    public List<ApartmentInfoTo> compara(List<ApartmentInfoTo> infos){
        List<ApartmentInfoTo> mInfoTos=new ArrayList<>();

        int k,j;
        String[] fstr=new String[infos.size()];
        for(k=0;k<infos.size();k++){
            fstr[k]=getPingYin(infos.get(k).getPlace().getName());
            infos.get(k).setmPinyin(fstr[k]);
        }

        Arrays.sort(fstr);
        for(k=0;k<fstr.length;k++){
            for(ApartmentInfoTo info:infos)
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



    private void back(String city){

        Intent data = new Intent();
        data.putExtra("sid", city);
        setResult(20, data);
        finish();
        goToAnimation(2);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search_clear:

                clearBtn.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                mResultListView.setVisibility(View.GONE);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    private void initView() {
        mListView = (ListView) findViewById(R.id.listview_all_city);
        mListView.setAdapter(mCityAdapter);

        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });

        searchBox = (EditText) findViewById(R.id.et_search);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    clearBtn.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    mResultListView.setVisibility(View.GONE);
                } else {
                    clearBtn.setVisibility(View.VISIBLE);
                    mResultListView.setVisibility(View.VISIBLE);
                    searchList.clear();
                    for (ApartmentInfoTo infoTo:infoList)
                        if (infoTo.getApartmentName().contains(keyword))
                            searchList.add(infoTo);
                    if (searchList == null || searchList.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(searchList);
                    }
                }
            }
        });

        emptyView = (ViewGroup) findViewById(R.id.empty_view);
        mResultListView = (ListView) findViewById(R.id.listview_search_result);
        mResultListView.setAdapter(mResultAdapter);
        mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                back(mResultAdapter.getItem(position).getApartmentSid());
            }
        });

        clearBtn = (ImageView) findViewById(R.id.iv_search_clear);
        backBtn = (ImageView) findViewById(R.id.back);

        clearBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }
}

