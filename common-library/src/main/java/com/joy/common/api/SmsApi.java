package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.message.SmsParam;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Admin on 2014-10-23
 */
public interface SmsApi {
    @POST("/api/v1/sms/send")
    void send(@Body SmsParam param, Callback<MessageTo<Boolean>> callback);

}
