package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.newshop.ActivityListParam;
import com.jinyi.ihome.module.shop.OrderCommentParam;
import com.jinyi.ihome.module.shop.ShopCommentParam;
import com.jinyi.ihome.module.shop.ShopOrderPayParam;
import com.jinyi.ihome.module.shop.ShopPayInfoTo;
import com.jinyi.ihome.module.shop.StoreCartParam;
import com.jinyi.ihome.module.shop.StoreCartTo;
import com.jinyi.ihome.module.shop.StoreCategoryTo;
import com.jinyi.ihome.module.shop.StoreItemCommentTo;
import com.jinyi.ihome.module.shop.StoreItemListParam;
import com.jinyi.ihome.module.shop.StoreItemListTo;
import com.jinyi.ihome.module.shop.StoreItemParam;
import com.jinyi.ihome.module.shop.StoreItemTo;
import com.jinyi.ihome.module.shop.StoreOrderMainTo;
import com.jinyi.ihome.module.shop.StoreOrderParam;
import com.jinyi.ihome.module.shop.StoreOrderQueryParam;
import com.jinyi.ihome.module.shop.StoreOrderStatusParam;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Admin on 2015-05-18
 */
public interface ShopApi {

    /**
     * 获取小屋分类列表
     *
     * @param apartmentSid 小区的sid
     * @param callback     回调
     */
    @POST("/api/v1/shop/category/{apartmentSid}")
    void findByApartment(@Path("apartmentSid") String apartmentSid, Callback<MessageTo<List<StoreCategoryTo>>> callback);


    /**
     * @param param    商品列表属性
     * @param callback 回调
     */
    @POST("/api/v1/shop/list")
    void findStoreItemList(@Body StoreItemListParam param, Callback<MessageTo<List<StoreItemListTo>>> callback);

    /**
     * 商品详情
     *
     * @param param    商品属性
     * @param callback 回调
     */
    @POST("/api/v1/shop/get")
    void findByItem(@Body StoreItemParam param, Callback<MessageTo<StoreItemTo>> callback);

    /**
     * 购物车
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/shop/cart_list")
    void getCart(@Body StoreCartParam param, Callback<MessageTo<List<StoreCartTo>>> callback);

    /**
     * 购物车更新
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/shop/cart_update")
    void updateCart(@Body StoreCartParam param, Callback<MessageTo<List<StoreCartTo>>> callback);

    /**
     * 订单新增
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/shop/order_add")
    void addOrder(@Body StoreOrderParam param, Callback<MessageTo<StoreOrderMainTo>> callback);


    /**
     * 订单列表
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/shop/order_list")
    void findOrderList(@Body StoreOrderQueryParam param, Callback<MessageTo<List<StoreOrderMainTo>>> callback);

    /**
     * 订单详情
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/shop/order_detail")
    void findOrder(@Body StoreOrderQueryParam param, Callback<MessageTo<StoreOrderMainTo>> callback);

    /**
     * 商品评论列表
     *
     * @param param    ShopCommentParam
     * @param callback
     */
    @POST("/api/v1/shop/comment_list")
    void findComment(@Body ShopCommentParam param, Callback<MessageTo<List<StoreItemCommentTo>>> callback);

    /**
     * 商品支付
     *
     * @param payParam
     * @param callback
     */
    @POST("/api/v1/shop/pay_info")
    void payInfo(@Body ShopOrderPayParam payParam, Callback<MessageTo<ShopPayInfoTo>> callback);

    /**
     * 订单状态更新
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/shop/order_update_status")
    void updateOrderStatus(@Body StoreOrderStatusParam param, Callback<MessageTo<StoreOrderMainTo>> callback);

    /**
     * 添加订单评论
     * @param param
     * @param callback
     */
    @POST("/api/v1/shop/add_comment")
    void addComment(@Body OrderCommentParam param , Callback<MessageTo<StoreItemCommentTo> > callback);



}
