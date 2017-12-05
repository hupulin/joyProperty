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
import com.joy.common.api.ApiClient;
import com.joy.common.api.HomeApi;
import com.joy.common.api.HttpCallback;
import com.joy.common.application.KeyValue;
import com.joy.library.fragment.CustomDialogFragment;
import com.joy.library.utils.ConfigUtil;
import com.joy.property.R;
import com.joy.property.base.BaseFragment;
import com.joy.property.task.ReceiveTaskDetaActivity;
import com.joy.property.task.adapter.MyInfoAdapter;
import com.joy.property.utils.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyInfoFragment extends BaseFragment {
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mList;
    private MyInfoAdapter myInfoAdapter;
    private int pageIndex = 0;
    private List<ServiceMainExpandTo> mainExpandToList = new ArrayList<>();
    private static MyInfoFragment instance=null;

    List<ApartmentInfoTo>apartmentInfoTos=new ArrayList<ApartmentInfoTo>();
    private SharedPreferences sp;

    public static MyInfoFragment getInstance() {
		if (instance==null) {

			instance = new MyInfoFragment();
		}
		return instance;
	}

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
        myInfoAdapter = new MyInfoAdapter(getActivity());

        mList.setAdapter(myInfoAdapter);
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
        final CustomDialogFragment dialogFragment =new CustomDialogFragment();
        dialogFragment.show(getFragmentManager(),"");
        api.findByUser(mUserHelper.getSid(), index, new HttpCallback<MessageTo<List<ServiceMainExpandTo>>>(getActivity()) {
            @Override
            public void success(MessageTo<List<ServiceMainExpandTo>> msg, Response response) {
                if (msg.getSuccess() == 0) {
                    dialogFragment.dismiss();
                    if (index == 0) {
                        mainExpandToList.clear();
                    }

                    if (msg.getData() != null) {
                        mainExpandToList.addAll(msg.getData());
                        Log.i("2222", "mainExpandToList: "+mainExpandToList.toString());
                        getTime();
                        for(int i=0;i<mainExpandToList.size();i++){
                            for(ApartmentInfoTo infoTo :apartmentInfoTos){
                                if(mainExpandToList.get(i).getApartmentSid().equals(infoTo.getApartmentSid())){
                                    mainExpandToList.get(i).setStartTime(infoTo.getStartTime());
                                   mainExpandToList.get(i).setEndTime(infoTo.getEndTime());
                                    //System.out.println(mainExpandToList.get(i).getApartmentName()+"比较啊"+infoTo.getApartmentName());
                                    break;
                                }
                            }
                        }
                        myInfoAdapter.setList(mainExpandToList);
                        myInfoAdapter.notifyDataSetChanged();
                    }
                    mPullToRefreshListView.onRefreshComplete();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ServiceMainExpandTo mode = mainExpandToList.get(position - 1);
                            Intent intent = new Intent(getActivity(), ReceiveTaskDetaActivity.class);
                            intent.putExtra("sid", mode.getServiceSid());
                            startActivity(intent);
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

    @Override
    public void onResume() {
        super.onResume();
        setList(0);
        pageIndex = 0;
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
