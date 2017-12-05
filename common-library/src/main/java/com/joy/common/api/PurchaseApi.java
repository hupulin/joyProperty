package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.purchase.GroupPurchaseOrderParam;
import com.jinyi.ihome.module.purchase.GroupPurchaseOrderTo;
import com.jinyi.ihome.module.purchase.GroupPurchasePayInfoTo;
import com.jinyi.ihome.module.purchase.GroupPurchasePayParam;
import com.jinyi.ihome.module.purchase.GroupPurchaseTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Admin on 2014-11-17
 */
public interface PurchaseApi {

    /**
     * 查找小区物业团购信息
     *
     * @param sid
     * @param callback
     */
    @GET("/api/v1/purchase/list/{sid}")
    void findGroupPurchaseDataByApartmentSidAndDate(@Path("sid") String sid, Callback<MessageTo<List<GroupPurchaseTo>>> callback);


    /**
     * 添加团购订单
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/purchase/add_order")
    void addGroupPurchaseOrder(@Body GroupPurchaseOrderParam param, Callback<MessageTo<GroupPurchaseOrderTo>> callback);

    /**
     * 取消团购订单
     *
     * @param sid
     * @param callback
     */

    @GET("/api/v1/purchase/del_order/{userSid}/{sid}")
    void deleteGroupPurchaseOrder(@Path("userSid") String userSid, @Path("sid") String sid, Callback<MessageTo<GroupPurchaseOrderTo>> callback);

    /**
     * 查找我的团购订单
     *
     * @param sid
     * @param callback
     */
    @GET("/api/v1/purchase/my_order/{sid}/{index}")
    void findGroupPurchaseOrderByOwner(@Path("sid") String sid, @Path("index") int index, Callback<MessageTo<List<GroupPurchaseOrderTo>>> callback);
    /**
     * 查找未支付团购订单
     *
     * @param sid
     * @param callback
     */
    @GET("/api/v1/purchase/my_unpaidOrder/{sid}/{index}")
    void getUnPayOrder(@Path("sid") String sid, @Path("index") int index, Callback<MessageTo<List<GroupPurchaseOrderTo>>> callback);
    /**
     * 查找已支付团购订单
     *
     * @param sid
     * @param callback
     */
    @GET("/api/v1/purchase/my_alreadyOrder/{sid}/{index}")
    void getPayOrder(@Path("sid") String sid, @Path("index") int index, Callback<MessageTo<List<GroupPurchaseOrderTo>>> callback);
    /**
     * 根据团购Sid获取团购详情
     * @param sid
     * @param callback
     */
    @GET("/api/v1/purchase/detail/{sid}")
    void findGroupPurchaseBySid(@Path("sid") String sid, Callback<MessageTo<GroupPurchaseTo>> callback);

    /**
     *  团购支付接口
     * @param payParam
     * @param callback
     */
    @POST("/api/v1/purchase/pay_info")
    void  payInfo(@Body GroupPurchasePayParam payParam ,Callback<MessageTo<GroupPurchasePayInfoTo>> callback);
}
