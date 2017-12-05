package com.joy.common.api;


import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.common.AdInfoTo;
import com.jinyi.ihome.module.common.DeviceParam;
import com.jinyi.ihome.module.common.DeviceTo;
import com.jinyi.ihome.module.common.TipParam;
import com.jinyi.ihome.module.common.TipTo;
import com.jinyi.ihome.module.house.AllHouseMenuTo;
import com.jinyi.ihome.module.house.HouseValueTypeTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Admin on 2014-12-09
 */
public interface CommonApi {

    /**
     * 更新业主当前设备信息
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/common/update_device_info")
    void updateOwnerDeviceInfo(@Body DeviceParam param, Callback<MessageTo<DeviceTo>> callback);

    /**
     * 抓取当前小区发布的广告
     *
     * @param apartmentSid
     * @param callback
     */
    @POST("/api/v1/common/ad/{apartmentSid}/{type}")
    void findAdListInfoByApartmentSid(@Path("apartmentSid") String apartmentSid, @Path("type") int type,Callback<MessageTo<List<AdInfoTo>>> callback);

    /**
     * 根据客户端上次查看的时间获取首页新内容标识 
     * @param param
     * @param callback
     */
    @POST("/api/v1/common/get_tip_info")
    void getAppTipInfoData(@Body TipParam param, Callback<MessageTo<TipTo>> callback);
@GET("/api/v1/house/getAllHouseMenu")
Observable<MessageTo<List<AllHouseMenuTo>>>getAllHouseMenu();
    @GET("/api/v1/house/getPropertyValueByType/{configType}")
    Observable<MessageTo<List<HouseValueTypeTo>>> getPropertryValue(@Path("configType") String configType);
}
