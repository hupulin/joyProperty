package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.query.HomeQueryTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Admin on 2014-11-12
 */
public interface QueryApi {
    /***
     * 返回查一查数据
     * @param callback
     */
    @GET("/api/v1/query/query")
    void getHomeQueryInfo(Callback<MessageTo<List<HomeQueryTo>>> callback);
}
