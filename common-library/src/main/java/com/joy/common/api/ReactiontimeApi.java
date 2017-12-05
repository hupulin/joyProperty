package com.joy.common.api;


import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.reactiontime.ApartmentReactionTo;
import com.jinyi.ihome.module.reactiontime.ReactiontimeListTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by fz on 2016/5/24.
 */
public interface ReactiontimeApi {

    /**
    * 获取工作人员的小区列表
    *
    * @param userSID 用户SID
    * @param callback  回调
    */

     @GET("/api/v1/user/getOwnerAccessApartment/{userSID}")
     void findApartmentList(@Path("userSID") String userSID, Callback<MessageTo<List<ApartmentReactionTo>>> callback);


    /**
     * 响应速度
     *
     * @param apartmentSid 小区编号
     * @param callback  回调
     */

    @GET("/api/v1/home/service/exceedReport/{apartmentSid}")
    void findReactionOTList(@Path("apartmentSid") String apartmentSid,Callback<MessageTo<List<ReactiontimeListTo>>> callback);



}
