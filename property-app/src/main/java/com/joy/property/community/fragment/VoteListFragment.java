package com.joy.property.community.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.vote.VoteFindParam;
import com.jinyi.ihome.module.vote.VoteTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VoteApi;
import com.joy.property.R;
import com.joy.property.community.VoteDetailActivity;
import com.joy.property.community.adapter.VoteAdapter;
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

/**
 * Created by Admin on 2015-07-09
 */
public class VoteListFragment extends BaseListFragment<VoteTo> {


    private VoteAdapter mAdapter = null;
    private List<VoteTo> mVoteToList = new ArrayList<>();


    @Override
    public void init() {
        super.init();


        mAdapter = new VoteAdapter(getActivity());
        String json= SpUtil.getString(getThisContext(), "Votecache");
        if(json!=null) {
            try {
                JSONObject obj=new JSONObject(json);
                String js = obj.getString("cache");
                List<VoteTo> homeNoticeToList1 = JSON.parseArray(js, VoteTo.class);

                mVoteToList.addAll(homeNoticeToList1);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        mAdapter.setList(mVoteToList);
        mList.setAdapter(mAdapter);
        if(SpUtil.getBoolean(getThisContext(),"FIRSTLOADNOTICE"))
            setList2(0);
        else {
            setList(0);
        }

    }

    public void setList(final int index) {
        VoteApi api = ApiClient.create(VoteApi.class);
        VoteFindParam param = new VoteFindParam();
        param.setIndex(index);
        param.setApartmentSid(mHelper.getSid());
        final CustomDialogFragment dialogFragment = new CustomDialogFragment();
      dialogFragment.show(getFragmentManager(), "");
        api.findVoteList(param, new HttpCallback<MessageTo<List<VoteTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<VoteTo>> msg, Response response) {
                dialogFragment.dismissAllowingStateLoss();
                mPullToRefreshListView.onRefreshComplete();
                if (msg == null) return;
                if (msg.getSuccess() == 0) {

                    mVoteToList.clear();
                    mVoteToList.addAll(msg.getData());
                    Map<String, List<VoteTo>> map = new HashMap<String, List<VoteTo>>();
                    map.put("cache", mVoteToList);
                    String json = JSON.toJSONString(map);
                    SpUtil.put(getThisContext(), "Votecache", json);
                    SpUtil.put(getThisContext(), "FIRSTLOADNOTICE", true);
                    mAdapter.notifyDataSetChanged();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            VoteTo voteTo = mVoteToList.get(position - 1);
                            Intent intent = new Intent(getThisContext(), VoteDetailActivity.class);
                            intent.putExtra("voteSid", voteTo.getVoteSid());
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
                dialogFragment.dismissAllowingStateLoss();
                mPullToRefreshListView.onRefreshComplete();
                super.failure(error);
            }
        });
    }
    public void setList2(final int index) {
        VoteApi api = ApiClient.create(VoteApi.class);
        VoteFindParam param = new VoteFindParam();
        param.setIndex(index);
        param.setApartmentSid(mHelper.getSid());

        api.findVoteList(param, new HttpCallback<MessageTo<List<VoteTo>>>(getThisContext()) {
            @Override
            public void success(MessageTo<List<VoteTo>> msg, Response response) {

                mPullToRefreshListView.onRefreshComplete();
                if (msg == null) return;
                if (msg.getSuccess() == 0) {

                    mVoteToList.clear();
                    mVoteToList.addAll(msg.getData());
                    Map<String, List<VoteTo>> map = new HashMap<String, List<VoteTo>>();
                    map.put("cache", mVoteToList);
                    String json = JSON.toJSONString(map);
                    SpUtil.put(getThisContext(), "Votecache", json);
                    SpUtil.put(getThisContext(), "FIRSTLOADNOTICE", true);
                    mAdapter.notifyDataSetChanged();
                    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            VoteTo voteTo = mVoteToList.get(position - 1);
                            Intent intent = new Intent(getThisContext(), VoteDetailActivity.class);
                            intent.putExtra("voteSid" ,voteTo.getVoteSid());
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

                mPullToRefreshListView.onRefreshComplete();
                super.failure(error);
            }
        });
    }
    @Override
    public void refreshList(int key) {
        setList(0);
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
