package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.store.StoreCommentParam;
import com.jinyi.ihome.module.store.StoreCommentTo;
import com.jinyi.ihome.module.store.StoreTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Admin on 2014-11-21
 */
public interface StoreApi {

    /**
     * 根据小区ID 获取周边商家
     *
     * @param sid
     * @param index
     * @param callback
     */
    @POST("/api/v1/store/{sid}/{index}")
    void findListByApartmentSid(@Path("sid") String sid, @Path("index") int index, Callback<MessageTo<List<StoreTo>>> callback);

    /**
     * 详情
     *
     * @param sid
     * @param callback
     */
    @GET("/api/v1/store/detail/{sid}")
    void findStoreBySid(@Path("sid") String sid, Callback<MessageTo<StoreTo>> callback);

    /**
     * 评论
     *
     * @param sid
     * @param index
     * @param callback
     */

    @POST("/api/v1/store/comments/{sid}/{index}")
    void findStoreCommentBySid(@Path("sid") String sid, @Path("index") int index, Callback<MessageTo<List<StoreCommentTo>>> callback);

    /**
     * 添加评论
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/store/comments/add")
    void addComment(@Body StoreCommentParam param, Callback<MessageTo<StoreCommentTo>> callback);

    /**
     * 拨打电话更新
     * @param sid
     *
     */
    @GET("/api/v1/store/call/{sid}")
    void updateCallPhoneBySid(@Path("sid") String sid, Callback<Void> callback);

}
