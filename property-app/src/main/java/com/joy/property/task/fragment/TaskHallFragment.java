package com.joy.property.task.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
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
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.application.KeyValue;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.MainApplication;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.task.ReceiveTaskDetailActivity;
import com.joy.property.task.adapter.TaskHallAdapter;
import com.joy.property.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class TaskHallFragment extends BaseFragment {
    private ListView mList;
    private PullToRefreshListView mPullToRefreshListView;
    private int pageIndex = 0;
    private TaskHallAdapter mAdapter;

    private List<ServiceMainExpandTo> serviceMainExpandToList = new ArrayList<>();
    public static TaskHallFragment mInstance = null;
    List<ApartmentInfoTo>apartmentInfoTos=new ArrayList<ApartmentInfoTo>();
    private SharedPreferences sp;

    public static TaskHallFragment getInstance() {
        if (mInstance == null) {
            mInstance = new TaskHallFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_list, container, false);
        findById(rootView);
        sp = PreferenceManager.getDefaultSharedPreferences(getThisContext());
        mList = mPullToRefreshListView.getRefreshableView();
        mList.setItemsCanFocus(true);
        // setLists("杭州市");
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        registerForContextMenu(mList);
        mAdapter = new TaskHallAdapter(getActivity());
        mAdapter.setList(serviceMainExpandToList);
        mList.setAdapter(mAdapter);
        setList(0);
        initDatas();
        return rootView;
    }

    private void initDatas() {
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                String label = DateUtils.formatDateTime(
                        getActivity(),
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
                        getActivity(),
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
        final CustomDialogFragment customDialog = new CustomDialogFragment();
        customDialog.show(getFragmentManager(), "");
        System.out.println(mUserHelper.getSid()+"2222");

        api.findServiceInfoByGroupUserSid(mUserHelper.getSid(), index, new HttpCallback<MessageTo<List<ServiceMainExpandTo>>>(getActivity()) {
            @Override
            public void success(MessageTo<List<ServiceMainExpandTo>> msg, Response response) {
                customDialog.dismiss();
                if (msg.getSuccess() == 0) {
                    if (index == 0) {
                        serviceMainExpandToList.clear();
                    }
                    if (msg.getData()==null) {
                        serviceMainExpandToList.clear();
                        mAdapter.notifyDataSetChanged();
                    }
                    if (msg.getData() != null) {
                        serviceMainExpandToList.addAll(msg.getData());
                        mAdapter.notifyDataSetChanged();
                        getTime();
                        for(int i=0;i<serviceMainExpandToList.size();i++){
                            for(ApartmentInfoTo infoTo :apartmentInfoTos){
                                if(serviceMainExpandToList.get(i).getApartmentSid().equals(infoTo.getApartmentSid())){
                                    serviceMainExpandToList.get(i).setStartTime(infoTo.getStartTime());
                                    serviceMainExpandToList.get(i).setEndTime(infoTo.getEndTime());
                                    break;
                                }
                            }
                        }

                        mAdapter.setList(serviceMainExpandToList);
                        mAdapter.notifyDataSetChanged();
                    }
                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ServiceMainExpandTo mode = serviceMainExpandToList.get(position - 1);
                            Intent intent = new Intent(getActivity(), ReceiveTaskDetailActivity.class);
                            intent.putExtra("right", "right");

                            intent.putExtra("sid", mode.getServiceSid());
                            intent.putExtra("apartmentSid",mode.getApartmentSid());
                            MainApplication application = (MainApplication) getThisContext().getApplicationContext();
                            application.setTDapartmentSid(mode.getApartmentSid());
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), msg.getMessage(), Toast.LENGTH_LONG).show();


                }


            }

            @Override
            public void failure(RetrofitError error) {
                customDialog.dismiss();
                mPullToRefreshListView.onRefreshComplete();
             Toast.makeText(getThisContext(),"您暂时没有开通任务权限,请联系相关工作人员!",Toast.LENGTH_LONG).show();
            }
        });

    }


    private void findById(View view) {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.list);

    }

    @Override
    public void onResume() {
     //   pageIndex =0;
//        setList(0);
//
        super.onResume();

    }
    public void getTime(){
        String json=null;
        if (ConfigUtil.getString(sp, KeyValue.KEY_USER_INFO, "").equals(SpUtil.getString(getThisContext(),"HomeInfo")))
         json = SpUtil.getString(getThisContext(), "TaskCache");
        else
        json=SpUtil.getString(getThisContext(), "TaskCachePark");
        if(json!=null) {
            try {
                JSONObject obj=new JSONObject(json);
                String js = obj.getString("cache");

                List<ApartmentInfoTo> homeNoticeToList1 = JSON.parseArray(js, ApartmentInfoTo.class);

                apartmentInfoTos.addAll(homeNoticeToList1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
