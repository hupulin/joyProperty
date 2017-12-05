package com.joy.property.task.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.system.ServiceMyTaskParam;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.task.ReceiveTaskDetailActivity;
import com.joy.property.task.adapter.MyTaskAdapter;
import com.joy.property.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2016/6/23.
 */
public class ResultFragment extends BaseFragment {

    private String date;
    private static final int TASK_DETAILS_TYPE = 1;
    private static final int TASK_DETAILS = 2;

    private PullToRefreshListView mPullToRefreshListView;
    private ListView mList;
    private MyTaskAdapter myTaskAdapter;
    private int pageIndex = 0;
    private List<ServiceMainExpandTo> mainExpandToList = new ArrayList<>();
    private static MyTaskFragment instance = null;

    List<ApartmentInfoTo> apartmentInfoTos = new ArrayList<ApartmentInfoTo>();

    public static MyTaskFragment getInstance() {
        if (instance == null) {

            instance = new MyTaskFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_list, container, false);
        findById(rootView);
        Bundle bundle = getArguments();
        date = bundle.getString("date");
        mList = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        myTaskAdapter = new MyTaskAdapter(getActivity());

        mList.setAdapter(myTaskAdapter);
        setList(0);
        initDatas();
        return rootView;
    }

    private void initDatas() {

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
                pageIndex = 0;
                setList(0);
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
                pageIndex++;
                setList(pageIndex);
            }
        });
    }

    private void setList(final int index) {
        HomeApi api = ApiClient.create(HomeApi.class);
        Date parsedate = null;
        if (date != null && !date.isEmpty()) {

            Log.e(" date ", " = " + date);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                parsedate = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        ServiceMyTaskParam param = new ServiceMyTaskParam();
        param.setResponseTime(parsedate);
        param.setGroupUserSid(mUserHelper.getSid());
        param.setPageIndex(index);
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
        api.findMytask(param, new HttpCallback<MessageTo<List<ServiceMainExpandTo>>>(getActivity()) {
            @Override
            public void success(MessageTo<List<ServiceMainExpandTo>> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    dialogFragment.dismiss();
                    if (index == 0) {
                        mainExpandToList.clear();
                    }

                    if (msg.getData() != null) {
                        mainExpandToList.addAll(msg.getData());
                        myTaskAdapter.setList(mainExpandToList);
                        myTaskAdapter.notifyDataSetChanged();
                        getTime();
                        for (int i = 0; i < mainExpandToList.size(); i++) {
                            for (ApartmentInfoTo infoTo : apartmentInfoTos) {
                                if (mainExpandToList.get(i).getApartmentSid().equals(infoTo.getApartmentSid())) {
                                    mainExpandToList.get(i).setStartTime(infoTo.getStartTime());
                                    mainExpandToList.get(i).setEndTime(infoTo.getEndTime());

                                    // System.out.println(mainExpandToList.get(i).getApartmentName()+"比较啊"+infoTo.getApartmentName());
                                    break;
                                }
                            }
                        }
                        myTaskAdapter.setList(mainExpandToList);
                        myTaskAdapter.notifyDataSetChanged();
                    }

                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ServiceMainExpandTo mode = mainExpandToList.get(position - 1);
                            Intent intent = new Intent(getActivity(), ReceiveTaskDetailActivity.class);
                            intent.putExtra("sid", mode.getServiceSid());
                            startActivityForResult(intent, TASK_DETAILS_TYPE);

                        }
                    });
                    System.out.println("进来没有啊");
                    ImageView emptyView = new ImageView(getThisContext());
                    emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    emptyView.setImageResource(R.drawable.noresults);
                    //emptyView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    emptyView.setVisibility(View.GONE);
                    ((ViewGroup) mList.getParent()).addView(emptyView);
                    mList.setEmptyView(emptyView);
                } else {
                    Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                dialogFragment.dismiss();
                Toast.makeText(getThisContext(), "失败", Toast.LENGTH_LONG).show();
                super.failure(error);
            }
        });
    }

    private void findById(View view) {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.list);

    }

    @Override
    protected Context getThisContext() {
        return getActivity();
    }


    public void getTime() {
        String json = SpUtil.getString(getThisContext(), "TaskCache");
        if (json != null) {
            try {
                JSONObject obj = new JSONObject(json);
                String js = obj.getString("cache");

                List<ApartmentInfoTo> homeNoticeToList1 = JSON.parseArray(js, ApartmentInfoTo.class);

                apartmentInfoTos.addAll(homeNoticeToList1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        switch (resultCode) {
            case 0x123:
                if (data.getBooleanExtra("is_submit", false)) {
                    setList(0);
                    pageIndex = 0;
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
