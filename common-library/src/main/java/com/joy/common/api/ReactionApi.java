package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.reaction.ReactionTimeListTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Admin on 2015-03-23
 */
public interface ReactionApi {

    /**
     * 查找响应速度标题
     *
     * @param param
     * @param callback
     */
    @GET("/api/v1/user/getOwnerAccessApartment/{userSID}")
    void getReactionTitle(@Path("userSID")String userSID , Callback<MessageTo<List<ApartmentInfoTo>>> callback);

    /**
     * 更新车辆信息
     *
     * @param param
     * @param callback
     */
    @GET("/api/v1/home/service/exceedReport/{apartmentSid}")

    void getReactionData(@Path("apartmentSid") String apartmentSid, Callback<ReactionTimeListTo> callback);

}
