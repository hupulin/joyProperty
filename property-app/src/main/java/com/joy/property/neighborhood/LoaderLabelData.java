package com.joy.property.neighborhood;

import android.content.Context;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTypeTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.NeighborApi;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Admin on 2015-10-27
 */
public class LoaderLabelData {

    public static LoaderLabelData instance = null;

    private ArrayList<NeighborPostTypeTo> typeList = new ArrayList<>();

    public ArrayList<NeighborPostTypeTo> getTypeList() {
        return typeList;
    }

    public static LoaderLabelData getInstance(Context context) {

        if (instance == null) {
            synchronized (LoaderLabelData.class) {
                instance = new LoaderLabelData();
                instance.loader(context);
            }
        }
        return instance;
    }

    private void loader(final Context context) {

        NeighborApi api = ApiClient.create(NeighborApi.class);

        api.findNeighborPostTypeList(new HttpCallback<MessageTo<List<NeighborPostTypeTo>>>(context) {
            @Override
            public void success(MessageTo<List<NeighborPostTypeTo>> msg, Response response) {
                if (msg == null) return;
                if (msg.getSuccess() == 0) {
                    typeList.clear();
                    typeList.addAll(msg.getData());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });
    }
}
