package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.visitor.MyGuestRecordTo;
import com.jinyi.ihome.module.visitor.VerifyCardParam;
import com.jinyi.ihome.module.visitor.VerifyCardResultTo;
import com.jinyi.ihome.module.visitor.VerifyPast;
import com.jinyi.ihome.module.visitor.VisitorCardParam;
import com.jinyi.ihome.module.visitor.VisitorCardTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Admin on 2014-11-18
 */
public interface VisitorApi {

@POST("/api/v1/visitor/add_card")
    void addVisitorCard(@Body VisitorCardParam param, Callback<MessageTo<VisitorCardTo>> callback);

    @POST("/api/v1/visitor/verify")
   void  verifyCard(@Body VerifyCardParam param ,Callback<MessageTo<VerifyCardResultTo>> callback);


    @POST("/api/v1/visitor/verify-with-no")

   void  verifyCardWithNo(@Body VerifyCardParam param ,Callback<MessageTo<VerifyCardResultTo> > callback);

    @POST("/api/v1/visitor/verifyPast")
    void verifyPastCard(@Body VerifyPast param ,Callback<MessageTo<VisitorCardTo> > callback );


@GET("/api/v1/visitor/cardneverUse/{apartmentSid}/{ownerSid}/{pageIndex}")
void getMyGuestRecord(@Path("apartmentSid")String apartmentSid,@Path("ownerSid")String ownerSid,@Path("pageIndex")int pageIndex,Callback<MessageTo<List<MyGuestRecordTo>>>callback);
@GET("/api/v1/visitor/cardalreadyuse/{apartmentSid}/{ownerSid}/{pageIndex}")
void getMyGuestRecordAlready(@Path("apartmentSid")String apartmentSid,@Path("ownerSid")String ownerSid,@Path("pageIndex")int pageIndex,Callback<MessageTo<List<MyGuestRecordTo>>>callback);
}
