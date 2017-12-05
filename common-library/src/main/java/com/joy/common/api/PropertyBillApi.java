package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.bill.PayPartnerTo;
import com.jinyi.ihome.module.bill.PropertyBillParam;
import com.jinyi.ihome.module.bill.PropertyBillPayInfoTo;
import com.jinyi.ihome.module.bill.PropertyBillPayParam;
import com.jinyi.ihome.module.bill.PropertyBillTo;
import com.jinyi.ihome.module.payment.PaymentParam;
import com.jinyi.ihome.module.payment.PropertyBillPayXSCParam;

import java.util.List;


import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;


public interface PropertyBillApi {
    /**
     * @param sid
     * @param userSid
     * @param callback
     */

    @GET("/api/v1/propertybill/{sid}/{userSid}")
    void getBill(@Path("sid") String sid, @Path("userSid") String userSid, Callback<MessageTo<PropertyBillTo>> callback);


    /**
     * @param param
     * @param callback
     */
    @POST("/api/v1/propertybill/list")
    void findByBillList(@Body PropertyBillParam param, Callback<MessageTo<List<PropertyBillTo>>> callback);
    @POST("/api/v1/propertybill/xsclist")
    public Observable<MessageTo<List<PropertyBillTo>>> findByBillListXsc(@Body PropertyBillParam param);

    @POST("/api/v1/propertybill/finishedlist")
    void finishList(@Body PropertyBillParam param, Callback<MessageTo<List<PropertyBillTo>>> callback);

    /**
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/propertybill/info")
    void findBillPayInfo(@Body PropertyBillPayParam param, Callback<MessageTo<PropertyBillPayInfoTo>> callback);
    @POST("/api/v1/propertybill/xscinfo")
    void NewWindowPay(@Body PropertyBillPayXSCParam param, Callback<MessageTo<String>> callback);


}