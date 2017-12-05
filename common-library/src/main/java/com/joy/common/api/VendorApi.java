package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Admin on 2014-11-07
 */
public interface VendorApi {

    @GET("/api/v1/vendor/qn-token")
    void getQnToken(Callback<MessageTo<String>> callback);
}
