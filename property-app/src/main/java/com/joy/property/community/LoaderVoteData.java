package com.joy.property.community;

import android.content.Context;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.vote.VoteFindParam;
import com.jinyi.ihome.module.vote.VoteTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VoteApi;
import com.joy.common.helper.ApartmentInfoHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-11-25
 */
public class LoaderVoteData {
    private static  LoaderVoteData instance = null;
    private ApartmentInfoHelper mHelper;
    private List<VoteTo> voteToList = new ArrayList<>();

    public List<VoteTo> getVoteToList() {
        return voteToList;
    }

    public static LoaderVoteData getInstance(Context context) {
        if (instance == null) {
            synchronized (LoaderVoteData.class){
                if (instance == null) {
                    instance = new LoaderVoteData();
                instance.mHelper = ApartmentInfoHelper.getInstance(context);
                    instance.loader(context);
                }
            }
        }
        return instance;
    }

    private void loader(Context context) {
        VoteApi api = ApiClient.create(VoteApi.class);
        VoteFindParam param = new VoteFindParam();
        param.setIndex(0);
        param.setApartmentSid(mHelper.getSid());
        api.findVoteList(param, new HttpCallback<MessageTo<List<VoteTo>>>(context) {
            @Override
            public void success(MessageTo<List<VoteTo>> msg, Response response) {
                if (msg == null) return;
                if (msg.getSuccess() == 0) {

                    if (msg.getData()!=null && msg.getData().size()>0) {
                         voteToList.clear();
                        voteToList.addAll(msg.getData());

                    }else {
                        voteToList.clear();
                    }

                }
            }

            @Override
            public void failure(RetrofitError error) {
                voteToList.clear();
                super.failure(error);
            }
        });
    }


}
