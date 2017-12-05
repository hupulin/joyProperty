package com.joy.property.DataStatistics;

import com.jinyi.ihome.infrastructure.MessageTo;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Admin on 2015-03-23
 */
public interface StatisticsApi {

    /**
     * 上传数据统计
     */
    @POST("/api/v1/user/OwnerLog")
    void getStatistics(@Body OwnerLogParam param , Callback<MessageTo<OwnerLogTo> > callback );
}
