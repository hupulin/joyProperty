package com.joy.property.community.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.guide.ServiceGuideTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NoticeApi;
import com.joy.property.R;
import com.joy.property.community.GuideDetailActivity;
import com.joy.property.community.adapter.FragmentGuideAdapter;
import com.joy.property.utils.SpUtil;
import com.joyhome.nacity.app.base.BaseListFragment;
import com.joyhome.nacity.app.util.CustomDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class GuideFragment extends BaseListFragment<ServiceGuideTo> {



    private FragmentGuideAdapter mAdapter = null;
    private List<ServiceGuideTo> mGuideList = new ArrayList<>();
    @Override
    public void init() {
        super.init();
        mAdapter = new FragmentGuideAdapter(getActivity());
        String json= SpUtil.getString(getThisContext(), "Guidecache");

        if(json!=null) {
            try {
                JSONObject obj=new JSONObject(json);
                String js = obj.getString("cache");
                List<ServiceGuideTo> homeNoticeToList1 = JSON.parseArray(js, ServiceGuideTo.class);

                mGuideList.addAll(homeNoticeToList1);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        mAdapter.setList(mGuideList);
        mList.setAdapter(mAdapter);
        if(SpUtil.getBoolean(getThisContext(),"FIRSTLOADNOTICE"))
            setList2(0);
        else {
            setList(0);
        }

    }

    public void setList(final int index) {


            NoticeApi api = ApiClient.create(NoticeApi.class);
            final CustomDialogFragment customDialog = new CustomDialogFragment();
           customDialog.show(getFragmentManager(), "");
            api.findGuidePageByApartmentSid(mHelper.getSid(), index,
                    new HttpCallback<MessageTo<List<ServiceGuideTo>>>(getThisContext()) {
                        @Override
                        public void success(MessageTo<List<ServiceGuideTo>> msg, Response response) {
                            customDialog.dismissAllowingStateLoss();
                            if (msg.getSuccess() == 0) {
                                if (index == 0) {
                                    mGuideList.clear();
                                }
                                mGuideList.addAll(msg.getData());
                                Map<String, List<ServiceGuideTo>> map = new HashMap<String, List<ServiceGuideTo>>();
                                map.put("cache", mGuideList);
                                String json = JSON.toJSONString(map);
                                SpUtil.put(getThisContext(), "Guidecache", json);
                                SpUtil.put(getThisContext(), "FIRSTLOADNOTICE", true);
                                mAdapter.notifyDataSetChanged();


                                mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        ServiceGuideTo serviceGuideTo = mGuideList.get(position - 1);
                                        Intent intent = new Intent(getThisContext(), GuideDetailActivity.class);
                                        intent.putExtra("mode", serviceGuideTo);
                                        startActivity(intent);
                                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                    }
                                });

                            } else {
                                Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            //   customDialog.dismissAllowingStateLoss();
                            super.failure(error);
                        }
                    });
        }
    public void setList2(final int index) {


        NoticeApi api = ApiClient.create(NoticeApi.class);

        api.findGuidePageByApartmentSid(mHelper.getSid(), index,
                new HttpCallback<MessageTo<List<ServiceGuideTo>>>(getThisContext()) {
                    @Override
                    public void success(MessageTo<List<ServiceGuideTo>> msg, Response response) {

                        if (msg.getSuccess() == 0) {
                            if (index == 0) {
                                mGuideList.clear();
                            }
                            mGuideList.addAll(msg.getData());
                            Map<String, List<ServiceGuideTo>> map = new HashMap<String, List<ServiceGuideTo>>();
                            map.put("cache", mGuideList);
                            String json = JSON.toJSONString(map);
                            SpUtil.put(getThisContext(), "Guidecache", json);
                            SpUtil.put(getThisContext(), "FIRSTLOADNOTICE", true);
                            mAdapter.notifyDataSetChanged();


                            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    ServiceGuideTo serviceGuideTo = mGuideList.get(position - 1);
                                    Intent intent = new Intent(getThisContext(), GuideDetailActivity.class);
                                    intent.putExtra("mode", serviceGuideTo);
                                    startActivity(intent);
                                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            });

                        } else {
                            Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        super.failure(error);
                    }
                });
    }


    @Override
    public void refreshList(int key) {

        setList(key);
    }

    @Override
    public void moreList(int key) {
    setList(key);
    }




    @Override
    protected Context getThisContext() {
        return getActivity();
    }


}
