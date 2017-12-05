package com.joy.property.task.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.home.ServiceMyWorkTo;
import com.jinyi.ihome.module.home.ServiceMyworkParam;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.application.KeyValue;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.task.MyWorkDetailActivity;
import com.joy.property.task.ReceiveTaskDetailActivity;
import com.joy.property.task.RefreshEventTask;
import com.joy.property.task.adapter.MyTaskAdapter;
import com.joy.property.task.adapter.MyWorkAdapter;
import com.joy.property.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usb on 2017/6/15.
 */

public class MyWorkFragment extends BaseFragment {
    private static final int TASK_DETAILS_TYPE = 1;
    private static final int TASK_DETAILS = 2;

    private PullToRefreshListView mPullToRefreshListView;
    private ListView mList;
    private MyWorkAdapter myWorkAdapter;
    private int pageIndex = 0;
    private boolean initEventBus;
    private List<ServiceMyWorkTo> mainExpandToList = new ArrayList<>();
    private static MyWorkFragment instance=null;

    List<ApartmentInfoTo>apartmentInfoTos=new ArrayList<ApartmentInfoTo>();
    private SharedPreferences sp;

    public static MyWorkFragment getInstance() {
        if (instance==null) {
            instance = new MyWorkFragment();
        }
        return instance;
    }
    public MyWorkFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_list,container, false);
        findById(rootView);
        sp = PreferenceManager.getDefaultSharedPreferences(getThisContext());
        mList=mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mList.setItemsCanFocus(true);
        registerForContextMenu(mList);
        myWorkAdapter = new MyWorkAdapter(getActivity());
        mList.setDividerHeight(0);
        mList.setAdapter(myWorkAdapter);
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
        ServiceMyworkParam param=new ServiceMyworkParam();
        param.setUserSid(mUserHelper.getSid());
        param.setCategorySid("B89C08B9-CD76-4B94-AE27-2617157180EF");
        param.setPageIndex(index);
        Log.i("222", "setList: "+param.toString());
        final CustomDialogFragment dialogFragment =new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(), "");
        api.getMyworKList(param, new HttpCallback<MessageTo<List<ServiceMyWorkTo>>>(getActivity()) {
            @Override
            public void success(MessageTo<List<ServiceMyWorkTo>> msg, Response response) {
                dialogFragment.dismiss();
                if(msg==null){
                    return;
                }
                if (msg.getSuccess() == 0) {
                    if (index == 0) {
                        mainExpandToList.clear();
                    }
                    if (msg.getData() != null) {
                        List<ServiceMyWorkTo> list= msg.getData();
//                        Iterator<ServiceMyWorkTo> sme_it = list.iterator();
//                        while(sme_it.hasNext()){
//                            ServiceMainExpandTo serviceMainExpandTo = sme_it.next();
//                            if(Integer.parseInt(serviceMainExpandTo.getServiceStatus())==3){
//                                sme_it.remove();
//                            }
//                        }

                        mainExpandToList.addAll(list);
                        myWorkAdapter.setList(mainExpandToList);
                        myWorkAdapter.notifyDataSetChanged();


                    }
                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ServiceMyWorkTo mode = mainExpandToList.get(position - 1);
                            Intent intent = new Intent(getActivity(), MyWorkDetailActivity.class);
                            // intent.putExtra("sid", mode.getServiceSid());
                            Bundle bundle = new Bundle();
                            bundle.putString("sid", mode.getWorkSid());
                            Log.i("2222", "mode: "+mode.toString());
                            Log.i("2222", "sid: "+mode.getWorkSid());
                               /*将Bundle对象assign给Intent*/
                            intent.putExtras(bundle);
                            startActivityForResult(intent, TASK_DETAILS_TYPE);
//                            startActivity(intent);
                            goToAnimation(1);
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

    private void findById(View view) {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.list);

    }

    @Override
    protected Context getThisContext() {
        return getActivity();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        setList(0);
//        pageIndex = 0;
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

       /* if (requestCode == TASK_DETAILS_TYPE && resultCode == TASK_DETAILS) {
            setList(0);
            pageIndex = 0;
        }*/
        if (data==null){
            return;
        }
        switch (resultCode){
            case 0x123:
                if (data.getBooleanExtra("is_submit",false)){
                    setList(0);
                    pageIndex = 0;
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
//    @Subscribe
//    public void onEventMainThread(RefreshEventTask event) {
//
//        if ("task".equals(event.getMsg())) {
//            noData = event.getNoData();
//            Log.i("22", "onEventMainThread: "+event.getNoData());
//            if(noData){
//                mainExpandToList.clear();
//                myWorkAdapter.setList(mainExpandToList);
//                myWorkAdapter.notifyDataSetChanged();
//            }else {
//                setList(0);
//            }
//        }
//
//    }


}
