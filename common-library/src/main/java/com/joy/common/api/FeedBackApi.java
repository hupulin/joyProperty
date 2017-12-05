package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.feedback.FeedbackListParam;
import com.jinyi.ihome.module.feedback.FeedbackParam;
import com.jinyi.ihome.module.feedback.FeedbackTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Admin on 2014-12-20
 */
public interface FeedBackApi {
    /***
     * 添加用户反馈信息
     *
     * @param param
     * @return
     */
    @POST("/api/v1/feedback/add_feedback")
    void addFeedbackInfo(@Body FeedbackParam param, Callback<MessageTo<FeedbackTo>> callback);


    /****
     * 用户的反列表
     *
     * @param param
     * @return
     */
    @POST("/api/v1/feedback/user_list")
    void findListByUser(@Body FeedbackListParam param, Callback<MessageTo<List<FeedbackTo>>> callback);

    /***
     * 详情页
     *
     * @param sid
     * @return
     */
    @GET("/api/v1/feedback/detail/{sid}")
    void findBySid(@Path("sid") String sid, Callback<MessageTo<FeedbackTo>> callback);

    /***
     * 删除
     *
     * @param sid
     * @return
     */
    @GET("/api/v1/feedback/del/{sid}")
    void deleteBySid(@Path("sid") String sid, Callback<MessageTo<Void>> callback);
}
