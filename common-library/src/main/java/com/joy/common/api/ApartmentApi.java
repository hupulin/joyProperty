package com.joy.common.api;


import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
import com.jinyi.ihome.module.apartment.ApartmentSearchParam;
import com.jinyi.ihome.module.apartment.ApartmentTagTreeTo;
import com.jinyi.ihome.module.apartment.HomeMenuTo;
import com.jinyi.ihome.module.express.CheckExpressParam;
import com.jinyi.ihome.module.express.CompanyTo;
import com.jinyi.ihome.module.express.ExpressNewTo;
import com.jinyi.ihome.module.express.ExpressRecordTo;
import com.jinyi.ihome.module.express.ExpressRecordParam;
import com.jinyi.ihome.module.express.ExpressToNew;
import com.jinyi.ihome.module.express.FindExpressParam;
import com.jinyi.ihome.module.express.FindExpressRecordTo;
import com.jinyi.ihome.module.express.ReceiveExpressParam;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Admin on 2014-11-14
 */
public interface ApartmentApi {


    /**
     * 根据小区的Id 查询小区信息
     *
     * @param apartmentNo
     * @param callback
     */

    @GET("/api/v1/apartment/get/{apartmentNo}")
    void findApartmentByNo(@Path("apartmentNo") String apartmentNo, Callback<MessageTo<ApartmentInfoTo>> callback);

    /**
     * 根据id查询小区
     *
     * @param id
     * @param callback
     */
    @POST("/api/v1/apartment/{id}")
    void findUserInfo(@Path("id") String id, Callback<MessageTo<ApartmentInfoTo>> callback);

    /**
     * 根据城市查询所有小区
     *
     * @param city
     * @param cb
     */
    @GET("/api/v1/apartment/city/{city}")
    void findApartmentByCity(@Path("city") String city, Callback<MessageTo<List<ApartmentInfoTo>>> cb);



    @GET("/api/v1/apartment/city_list")
    void  findByAll( Callback<MessageTo<List<ApartmentInfoTo>>> callback);
    /**
     * 根据UID查询小区基本信息
     *
     * @param uid
     * @param callback
     */
    @GET("/api/v1/apartment/city_listn")
    void  findByAllCity( Callback<MessageTo<List<ApartmentInfoTo>>> callback);
    /**
     * 根据UID查询小区基本信息
     *

     * @param callback
     */
    @GET("/api/v1/apartment/uid/{uid}")
    void finApartmentByUid(@Path("uid") String uid, Callback<MessageTo<ApartmentInfoTo>> callback);

    /**
     * 根据位置返回1000条小区基本信息
     *
     * @param lat
     * @param lng
     * @param callback
     */
    @GET("/api/v1/apartment/location/{lat},{lng}/{pageIndex}")
    void findApartmentByLocation(@Path("lat") double lat, @Path("lng") double lng, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<ApartmentInfoTo>>> callback);


    /**
     * 小区搜索
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/apartment/search")
    void searchApartment(@Body ApartmentSearchParam param, Callback<MessageTo<List<ApartmentInfoTo>>> callback);

    @GET("/api/v1/apartment/user/{usid}")
    void findByUserSid(@Path("usid") String usid, Callback<MessageTo<List<ApartmentInfoTo>>> callback);
    @GET("/api/v1/express/resendExpressRemark")
    void resendExpressRemark(@Query("expressSid") String expressSid, Callback<MessageTo> callback);
    @POST("/api/v1/express/receiveExpress")
    void receiveExpress(@Body ReceiveExpressParam param, Callback<MessageTo> callback);
    @POST("/api/v1/express/get_company")
    void findExpressCompany(Callback<MessageTo<List<CompanyTo>>> callback);
    //判断快递是否录入
    @POST("/api/v1/express/selectExpressNoStatus")
    void checkExpressStatus(@Body CheckExpressParam param, Callback<MessageTo<Integer>> callback);
    @GET("/api/v1/express/getMyAddExpressLog/{userSid}/{pageIndex}")
    void findExpressRecord(@Path("userSid") String userSid,@Path("pageIndex") int pageIndex,Callback<MessageTo<List<ExpressNewTo>>> callback);
    @POST("/api/v1/express/selectListPage")
    void findExpressReceiveRecord(@Body FindExpressParam param, Callback<MessageTo<FindExpressRecordTo>> callback);
    /**
     * 日期搜索
     * @param param
     * @param callback
     */
    @POST( "/api/v1/home/service/mytask")
    void TaskHall(@Body ApartmentSearchParam param, Callback<MessageTo<List<ApartmentInfoTo>>> callback);
    /**
     * * 获取首页的模块自定义
     * @param apartmentSid
     * @return
     */
    @POST("/api/v1/apartment/model/{apartmentSid}")
    void findModelBySid(@Path("apartmentSid") String apartmentSid, Callback< MessageTo<HomeMenuTo>> callback);


    /**
     * 获取小区房号
     * @param apartmentSid
     * @param callback
     */

    @GET("/api/v1/apartment/house_no/{apartmentSid}")
    void findHouseNo (@Path("apartmentSid") String apartmentSid ,Callback<MessageTo<List<ApartmentTagTreeTo>> > callback);


}
