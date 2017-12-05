package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Admin on 2015-07-18
 */
public interface WebApi {


    /***
     *  加载当前小区的loading页面
     *  @param apartmentSid
     *  @return
     *  */
    @POST("/api/v1/web/loading/{apartmentSid}")
    void loading(@Path("apartmentSid") String apartmentSid, Callback<MessageTo<String>> callback);

}
