package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.house.HouseListParam;
import com.jinyi.ihome.module.house.HouseMainTo;
import com.jinyi.ihome.module.house.HouseParam;
import com.jinyi.ihome.module.house.HouseRequirementAddParam;
import com.jinyi.ihome.module.house.HouseRequirementTo;
import com.jinyi.ihome.module.house.HouseStateParam;
import com.jinyi.ihome.module.house.HouseTo;
import com.jinyi.ihome.module.house.HouseValueTypeTo;
import com.jinyi.ihome.module.house.RequirementConfigParam;
import com.jinyi.ihome.module.house.RequirementConfigTo;
import com.jinyi.ihome.module.house.SearchHouseLabelParam;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Admin on 2014-12-03
 */
public interface HouseApi {

    /**
     * 发布房屋出租或出售信息
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/house/publish")
    void publishHouseInfo(@Body HouseParam param, Callback<MessageTo<HouseTo>> callback);

    /**
     * 编辑房屋出租或出售信息
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/house/edit")
    void editHouseInfo(@Body HouseParam param, Callback<MessageTo<HouseTo>> callback);

//    /**
//     * 下架房屋出租或出售信息
//     *
//     * @param propertySid
//     * @param callback
//     */
//    @GET("/api/v1/house/disable/{propertySid}")
//    void disableHouseInfo(@Path("propertySid") String propertySid, Callback<MessageTo<HouseTo>> callback);

    /**
     * 返回出租或出售详情
     *
     * @param propertySid
     * @param callback
     */
    @GET("/api/v1/house/get/{propertySid}")
    void getHouseInfoBySid(@Path("propertySid") String propertySid, Callback<MessageTo<HouseTo>> callback);

    /***
     * 返回当前城市所有出租或出售信息
     *
     * @param param 查询条件
     * @return
     */

    @POST("/api/v1/house/get_all")
    void findAllHouseInfo(@Body HouseListParam param, Callback<MessageTo<List<HouseTo>>> callback);
    /***
     * 获取根据标签的房源
     *
     * @param param 查询条件
     * @return
     */

    @POST("/api/v1/house/searchsHouse")
    void findHouseByLabel(@Body SearchHouseLabelParam param, Callback<MessageTo<List<HouseTo>>> callback);
    /**
     * 返回房产首页基本信息
     *
     * @param apartmentSid
     * @param ownerSid
     * @param callback
     */
    @GET("/api/v1/house/info/{apartmentSid}/:{ownerSid}")
    void getHouseMainInfo(@Path("apartmentSid") String apartmentSid, @Path("ownerSid") String ownerSid, Callback<MessageTo<HouseMainTo>> callback);

    /**
     * 获取用户需求配置文件
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/house/config")
    void findRequirementConfig(@Body RequirementConfigParam param, Callback<MessageTo<RequirementConfigTo>> callback);

    /**
     * 添加用户需求
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/house/add_user_requirement")
    void addUserRequirement(@Body HouseRequirementAddParam param, Callback<MessageTo<HouseRequirementTo>> callback);

       /********************************************************/
    /***
     * 下架房屋出租或出售信息
     *
     * @param param
     * @return
     */
    @POST("/api/v1/house/disable")
    void disableHouseInfo(@Body HouseStateParam param, Callback<MessageTo<HouseTo>> callback);


    /***
     * 删除房产
     *
     * @param param
     * @return
     */
    @POST("/api/v1/house/del")
    void delHouse(@Body HouseStateParam param, Callback<MessageTo<HouseTo>> callback);


    @POST("/api/v1/house/disable_requirement")
    void disableRequirement(@Body HouseStateParam param, Callback<MessageTo<HouseRequirementTo>> callback);


    @POST("/api/v1/house/del_requirement")
    void delRequirement(@Body HouseStateParam param, Callback<MessageTo<HouseRequirementTo>> callback);


    /***
     * 获取所有我的发布
     *
     * @param param
     * @return
     */
    @POST("/api/v1/house/my_publish")
    void findAllMyPublish(@Body HouseListParam param, Callback<MessageTo<List<HouseTo>>> callback);

    @POST("/api/v1/house/my_requirement")
    void findAllMyRequirement(@Body HouseListParam param, Callback<MessageTo<List<HouseRequirementTo>>> callback);

    /**
     * 重新上架
     * @param param
     * @param callback
     */
    @POST("/api/v1/house//change_state")
    void changeHouse(@Body HouseStateParam param ,Callback< MessageTo<HouseTo> > callback);
    @GET("/api/v1/house/getPropertyValueByType/{configType}")
    Observable<MessageTo<List<HouseValueTypeTo>>> getPropertryValue(@Path("configType") String configType);
}
