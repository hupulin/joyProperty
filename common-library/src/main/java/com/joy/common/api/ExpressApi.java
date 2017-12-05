package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.express.ExpressApartmentParam;
import com.jinyi.ihome.module.express.ExpressCompanyTo;
import com.jinyi.ihome.module.express.ExpressParam;
import com.jinyi.ihome.module.express.ExpressTo;
import com.jinyi.ihome.module.express.ExpressToNew;
import com.jinyi.ihome.module.express.SendCotificationParam;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Admin on 2015-03-12.
 * 代收快递接口
 */

public interface ExpressApi {

    /**
     * 根据小区SID分页查找快递数据
     * @param param
     * @param callback
     */
    @POST("/api/v1/express/get_apartment")
    void  findExpressPageByApartmentSid(@Body ExpressApartmentParam param , Callback<MessageTo<List<ExpressTo>> > callback);

    /**
     * 根据用户SID分页查找快递数据
     * @param ownerSid
     * @param pageIndex
     * @param callback
     */
    @GET("/api/v1/express/get_owner/{ownerSid}/{type}/{pageIndex}")
    void  findExpressPageByOwnerSid(@Path("ownerSid") String ownerSid, @Path("type") int type,@Path("pageIndex") int pageIndex , Callback<MessageTo<List<ExpressTo>>> callback);

    /**
     * 添加用户快递信息
     * @param param
     * @param callback
     */
    @POST("/api/v1/express/add")
    void addExpressInfo(@Body ExpressParam param , Callback<MessageTo<ExpressToNew>> callback);
    /**
     * 添加用户快递信息
     * @param param
     * @param callback
     */
    @POST("/api/v1/express/insertExpressInfo")
    void addExpressInfoNew(@Body ExpressParam param , Callback<MessageTo<ExpressToNew>> callback);

    /**
     * 发送短信
     */
    @POST("/api/v1/express/sendExpressCotification")
    void sendMessage(@Body SendCotificationParam param , Callback<MessageTo<Integer>> callback);

    /**
     * 删除用户提交的快递信息
     *
     * @param expressSid
     * @param callback
     */
    @GET("/api/v1/express/del/{expressSid}")
    void delExpressInfo(@Path("expressSid") String expressSid, Callback<MessageTo<Void>> callback);


    /**
     *返回快递信息详情
     * @param expressSid
     * @param callback
     */
    @GET("/api/v1/express/get/{expressSid}")
    void getExpressInfoBySid(@Path("expressSid") String expressSid, Callback<MessageTo<ExpressTo>> callback);

    /**
     * 更新快递状态
     *
     * @param expressSid
     * @param expressStatus
     * @param callback
     */
    @GET("/api/v1/express/update/{expressSid}/{expressStatus}")
    void updateExpressInfo(@Path("expressSid") String expressSid, @Path("expressStatus") int expressStatus, Callback<MessageTo<ExpressTo>> callback);

    /**
     * 返回快递公司列表
     * @param callback
     */
    @GET("/api/v1/express/get_company")
    void findExpressCompanyList(Callback<MessageTo<List<ExpressCompanyTo>>> callback);
    @GET("/api/v1/express//get_owner/{ownerSid}/{type}/{pageIndex}")
    void getMyExpress(@Path("ownerSid")String ownerSid,@Path("type")String type,@Path("pageIndex")int pageIndex,Callback<MessageTo<List<ExpressTo>>> callback);
    }
