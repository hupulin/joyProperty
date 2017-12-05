package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.guide.ServiceGuideTo;
import com.jinyi.ihome.module.notice.HomeNoticeCommentTo;
import com.jinyi.ihome.module.notice.HomeNoticeTo;
import com.jinyi.ihome.module.notice.NoticeCommentParam;
import com.jinyi.ihome.module.notice.NoticeParam;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Admin on 2014-11-13
 */
public interface NoticeApi {
    /**
     * 办事指南
     *
     * @param sid
     * @param pageIndex
     * @param callback
     */
    @GET("/api/v1/notice/guide/{sid}/{pageIndex}")
    void findGuidePageByApartmentSid(@Path("sid") String sid, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<ServiceGuideTo>>> callback);

    /**
     * 通知
     *
     * @param sid
     * @param pageIndex
     * @param callback
     */
    @GET("/api/v1/notice/notice/{sid}/{pageIndex}")
    void findNoticePageByApartmentSid(@Path("sid") String sid, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<HomeNoticeTo>>> callback);
@POST("/api/v1/notice/add")
void sendNotice(@Body NoticeParam param,Callback<MessageTo<String>>callback);
    /**
     *
     * @param sid
     * @param callback
     */
    @GET("/api/v1/notice/notice/{sid}")
    void findNoticeBySid(@Path("sid") String sid, Callback<MessageTo<HomeNoticeTo>> callback);

    /**
     * 添加评论
     * @param param
     * @param callback
     */
    @POST("/api/v1/notice/comment")
    void addComment(@Body NoticeCommentParam param, Callback<MessageTo<HomeNoticeCommentTo>> callback);
    @GET("/api/v1/notice/noticeWorker/{sid}/{pageIndex}")
    void findNoticePageBySid(@Path("sid") String sid, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<HomeNoticeTo>>> callback);

}
