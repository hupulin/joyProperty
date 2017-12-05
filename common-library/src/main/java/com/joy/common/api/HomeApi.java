package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.common.DeviceParam;
import com.jinyi.ihome.module.common.DeviceTo;
import com.jinyi.ihome.module.home.DataTo;
import com.jinyi.ihome.module.home.LllegalParkParam;
import com.jinyi.ihome.module.home.MessageParam;
import com.jinyi.ihome.module.home.MyWorkHandleParam;
import com.jinyi.ihome.module.home.ServiceAssignParam;
import com.jinyi.ihome.module.home.ServiceCategoryParam;
import com.jinyi.ihome.module.home.ServiceEvaluationParam;
import com.jinyi.ihome.module.home.ServiceFindParam;
import com.jinyi.ihome.module.home.ServiceForwardParam;
import com.jinyi.ihome.module.home.ServiceHandleParam;
import com.jinyi.ihome.module.home.ServiceInspectionPositionTo;
import com.jinyi.ihome.module.home.ServiceMainExpandTo;
import com.jinyi.ihome.module.home.ServiceMainTo;
import com.jinyi.ihome.module.home.ServiceMessageParam;
import com.jinyi.ihome.module.home.ServiceMessageTo;
import com.jinyi.ihome.module.home.ServiceMyWorkTo;
import com.jinyi.ihome.module.home.ServiceMyworkParam;
import com.jinyi.ihome.module.home.ServiceParam;
import com.jinyi.ihome.module.home.ServiceRedPointTo;
import com.jinyi.ihome.module.home.ServiceReportFlowParam;
import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.jinyi.ihome.module.home.ServiceReportGradeTo;
import com.jinyi.ihome.module.home.ServiceRequestParam;
import com.jinyi.ihome.module.home.ServiceResponseParam;
import com.jinyi.ihome.module.home.ServiceReturnParam;
import com.jinyi.ihome.module.home.ServiceTaskParam;
import com.jinyi.ihome.module.home.ServiceTimeParam;
import com.jinyi.ihome.module.home.ServiceTimeTo;
import com.jinyi.ihome.module.home.ServiceTypeParam;
import com.jinyi.ihome.module.home.ServiceTypeSelectParam;
import com.jinyi.ihome.module.home.ServiceTypeTo;
import com.jinyi.ihome.module.home.TaskServiceTypeTo;
import com.jinyi.ihome.module.neighbor.NeighborMessageParam;
import com.jinyi.ihome.module.neighbor.NeighborReportNewPostTo;
import com.jinyi.ihome.module.neighbor.NeighborReportPostTo;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.jinyi.ihome.module.query.QueryParam;
import com.jinyi.ihome.module.system.ServiceMyTaskParam;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Admin on 2014-10-23
 */
public interface HomeApi {

    /**
     * 返回服务类型
     *
     * @param apartmentSid
     * @param type         巡检 4
     * @return
     */
    @GET("/api/v1/home/service_type/{type}/{apartmentSid}")
    void findServiceTypeInfoByApartmentAndCategory(@Path("type") int type, @Path("apartmentSid") String apartmentSid, Callback<MessageTo<List<ServiceTypeTo>>> callback);

    /**
     * 返回服务留言
     *
     * @param param
     * @param callback
     */

    @GET("/api/v1/home/service_message/{serviceSid}")
    void findMessageInfoByService(@Body ServiceMessageParam param, Callback<MessageTo<ServiceMessageTo>> callback);

    /**
     * 添加服务留言
     *
     * @param param
     * @param callback
     */

    @POST("/api/v1/home/service_message")
    void addServiceMessage(@Body ServiceMessageParam param, Callback<MessageTo<ServiceMessageTo>> callback);

    /**
     * 返回当前业主的服务记录
     *
     * @param apartmentSid
     * @param ownerSid
     * @param pageIndex
     * @param callback
     */
    @GET("/api/v1/home/service_list/{apartmentSid}/{ownerSid}/{categorySid}/{pageIndex}")
    void findServiceInfoByApartmentAndOwner(@Path("apartmentSid") String apartmentSid, @Path("ownerSid") String ownerSid, @Path("categorySid") String categorySid, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<ServiceMainTo>>> callback);


    /**
     * 添加服务呼叫
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/home/service_request")
    void addServiceRequest(@Body ServiceRequestParam param, Callback<MessageTo<ServiceMainTo>> callback);

    /**
     * 添加服务评论
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/home/service_evaluation")
    void addServiceEvaluation(@Body ServiceEvaluationParam param, Callback<MessageTo<ServiceMainTo>> callback);

    /**
     * 添加服务打回
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/home/service_return")
    void addServiceReturn(@Body ServiceReturnParam param, Callback<MessageTo<ServiceMainTo>> callback);

    /**
     * 返回未读服务留言数量
     *
     * @param serviceSid
     * @param callback
     */
    @GET("/api/v1/home/service_message_qty/{serviceSid}")
    void getMessageWaitQtyByService(@Path("serviceSid") String serviceSid, Callback<MessageTo<Integer>> callback);

    /**
     * 更新服务留言TAG
     *
     * @param serviceSid
     * @param callback
     */
    @POST("/api/v1/home/service_message_tag/{serviceSid}")
    void updateMessageTagByService(@Path("serviceSid") String serviceSid, Callback<MessageTo<Void>> callback);

    /**
     * 返回当前主服务记录
     *
     * @param sid
     * @param callback
     */
    @GET("/api/v1/home/service_main/{sid}")
    void findServiceMainBySid(@Path("sid") String sid, Callback<MessageTo<ServiceMainTo>> callback);

    /**
     * 撤销提报
     *
     * @param sid
     * @param userSid
     */
    @GET("/api/v1/home/service_revoke/{sid}/{userSid}")
    void revokeServiceMain(@Path("sid") String sid, @Path("userSid") String userSid, Callback<MessageTo<ServiceMainTo>> callback);

    /**
     * 处理中
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/home/service/accpetServiceTask")
    void inTheProcessing(@Body ServiceForwardParam param, Callback<MessageTo<ServiceMainExpandTo>> callback);
    @POST("/api/v1/work/BeginDealWork")
    //日常工单开始处理
    void beginDealWork(@Body ServiceForwardParam param, Callback<MessageTo<ServiceMyWorkTo>> callback);
    @POST("/api/v1/work/GetMyWorkList")
    void getMyworKList(@Body ServiceMyworkParam param, Callback<MessageTo<List<ServiceMyWorkTo>>> callback);
/**
 * 物业版
 */
    /**
     * 根据登录人员ID获取任务大厅记录
     *
     * @param groupUserSid
     * @param pageIndex
     * @param callback
     */
    @GET("/api/v1/home/service/taskhall/{groupUserSid}/{pageIndex}")
    void findServiceInfoByGroupUserSid(@Path("groupUserSid") String groupUserSid, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<ServiceMainExpandTo>>> callback);
    @GET("/api/v1/app/evaluationTag/selectTagNameByType")
    void findEvaluateLabel(@Query("type") String type, Callback<MessageTo<DataTo>> callback);
    @GET("/api/v1/home/service/mytask/{groupUserSid}/{pageIndex}")
    void findByResponseUser(@Path("groupUserSid") String groupUserSid, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<ServiceMainExpandTo>>> callback);
//    @POST("/api/v1/home/service/accpetServiceTask")
//    void findByResose(@Path("groupUserSid") String groupUserSid,@Path("pageIndex") int pageIndex,Callback<MessageTo<List<ServiceMainExpandTo>>> callback);


    /**
     * 领取任务
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/home/service/response")
    void responseService(@Body ServiceResponseParam param, Callback<MessageTo<ServiceMainExpandTo>> callback);


    /**
     * 处理反馈
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/home/service/handle")
    void handleService(@Body ServiceHandleParam param, Callback<MessageTo<ServiceMainExpandTo>> callback);   /**
     * 工单处理反馈
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/work/FinishAndFeedWork")
    void finishAndFeedWork(@Body MyWorkHandleParam param, Callback<MessageTo<ServiceMyWorkTo>> callback);

    /**
     * 转发中心
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/home/service/get_assign_user")
    void assignService(@Body ServiceAssignParam param, Callback<MessageTo<List<UserInfoTo>>> callback);

    @GET("/api/v1/user/getDeptWorkerAllWithApartment/0/{apartmentSid}")
    void caremanService(@Path("apartmentSid") String apartmentSid, Callback<MessageTo<List<UserInfoTo>>> callback);

    /**
     * 转发
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/home/service/forward")
    void forwardService(@Body ServiceForwardParam param, Callback<MessageTo<ServiceMainExpandTo>> callback);

    @GET("/api/v1/home/service/property/{sid}")
    void findServiceMainExpandBySid(@Path("sid") String sid, Callback<MessageTo<ServiceMainExpandTo>> callback);
    @GET("/api/v1/work/GetMyWorkById/{workSid}")
    void getMyWorkById(@Path("workSid") String workSid, Callback<MessageTo<ServiceMyWorkTo>> callback);

    /**
     * 派单
     **/
    @POST("/api/v1/home//service/responsenew")
    void distributionService(@Body ServiceResponseParam param, Callback<MessageTo<ServiceMainExpandTo>> callback);

    /**
     * 返回服务类型
     *
     * @param apartmentSid
     */
    @GET("/api/v1/home/service_type/{apartmentSid}")
    void findServiceTypeInfoByApartment(@Path("apartmentSid") String apartmentSid, Callback<MessageTo<List<ServiceTypeTo>>> callback);

    /**
     * 返回当前用户提报
     *
     * @param ownerSid
     * @param type
     * @param pageIndex
     * @param callback
     */

    @GET("/api/v1/home/service_list/{ownerSid}/{type}{pageIndex}")
    void findByOwner(@Path("ownerSid") String ownerSid,@Path("type") String type, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<ServiceMainExpandTo>>> callback);


    /**
     * 邻里口碑旧的接口
     *
     * @param ownerSid
     * @param callback
     */
    @GET("/api/v1/home/service/report_grade/{ownerSid}")
    void reportGrade(@Path("ownerSid") String ownerSid, Callback<MessageTo<List<ServiceReportGradeTo>>> callback);

    /**
     * 邻里口碑新的接口
     *
     * @param ownerSid
     * @param callback
     */
    @GET("/api/v1/home/service/report_gradeNew/{ownerSid}")
    void reportGradeNew(@Path("ownerSid") String ownerSid, Callback<MessageTo<List<ServiceReportGradeTo>>> callback);


    /**
     * 投诉管理ServiceReportGradesTo
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/home/service/report_flowNew")
    void reportFlow(@Body ServiceReportFlowParam param, Callback<MessageTo<List<ServiceReportFlowTo>>> callback);

//    @POST("/api/v1/home/service/report_flow")
//    void reportFlow(@Body ServiceReportFlowParam param,Callback<MessageTo<List<ServiceReportFlowTo>>>  callback);


    /**
     * 投诉详情
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/home/service/property/find")
    void findServiceMain(@Body ServiceFindParam param, Callback<MessageTo<List<ServiceMainExpandTo>>> callback);

    /*************************************************************************/
    /***
     * 返回服务类型 侧边栏
     *
     * @param param
     * @return
     */
    @POST("/api/v1/home/service/getCategoryServiceMainInfoList")
    void getTypeByCategory(@Body ServiceTypeSelectParam param, Callback<MessageTo<List<ServiceTypeTo>>> callback);
    /*************************************************************************/
    /***
     * 返回服务类型
     *
     * @param param
     * @return
     */
    @POST("/api/v1/home/service/type")
    void findTypeByCategory(@Body ServiceTypeParam param, Callback<MessageTo<List<ServiceTypeTo>>> callback);

    /***
     * 获取服务时间
     * @return
     */
    @POST("/api/v1/home/service/time")
    void findTime(@Body ServiceTimeParam param, Callback<MessageTo<List<ServiceTimeTo>>> callback);

    @GET("/api/v1/home/service/mycare/{userid}/{pageIndex}")
    void findByUser(@Path("userid") String userid, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<ServiceMainExpandTo>>> callback);

    @GET("/api/v1/work/GetWorkTypeByApartmentSid/{apartmentSid}/{categorySid}")
    void getWorkTypeByApartmentSid(@Path("apartmentSid") String apartmentSid, @Path("categorySid") String categorySid, Callback<MessageTo<List<TaskServiceTypeTo>>> callback);

    /**
     * 日期搜索
     * @param param
     * @param callback
     */
//   @POST( "/api/v1/home/service/mytask")
//    void TaskHall(@Body ServiceMyTaskParam param, Callback<MessageTo<List<ServiceMainExpandTo>>> callback);
    @POST("/api/v1/home/service/myDateTask")
    void findMytask(@Body ServiceMyTaskParam param, Callback<MessageTo<List<ServiceMainExpandTo>>> callback);

    @POST("/api/v1/neighbor/new_postCount")
    void redPointNeighbor(@Body NeighborMessageParam param, Callback<MessageTo<List<NeighborReportNewPostTo>>> callback);

    @POST("/api/v1/home/service/mycareAndMyTaskMessage")
    void redPointTaskHall(@Body ServiceTaskParam param, Callback<MessageTo<Integer>> callback);

    @POST("/api/v1/user/groupNewUserMessage")
    void redPointMessageCenter(@Body MessageParam param, Callback<ServiceRedPointTo> callback);

    @POST("/api/v1/home/service/taskHallCountMessage")
    void competenceTaskHall(@Body ServiceParam  param, Callback<MessageTo<Integer>> callback);

    @POST("/api/v1/user/QueryUserMessage")
    void searchWorker(@Body QueryParam param, Callback<MessageTo<List<UserInfoTo>>> callback);

    /***
     * 获取我的巡检
     *
     * @param param
     * @return
     */
    @GET("/api/v1/home/service_list/{ownerSid}/{type}/{pageIndex}")
    void getMyInspectList(@Path("ownerSid") String ownerSid,@Path("type") String type,@Path("pageIndex") int pageIndex, Callback<MessageTo<List<ServiceMainExpandTo>>> callback);


    /***
     * 获取筛选结果
     *
     * @param param
     * @return
     */
    @POST("/api/v1/home/service/getCategoryServiceMainInfoList")
    void getFilterResult(@Body ServiceCategoryParam param, Callback<MessageTo<List<ServiceMainExpandTo>>> callback);
    /***
     * 任务大厅筛选结果
     *
     * @param param
     * @return
     */
  @POST("/api/v1/home/getServiceMainExpandToList")
    void getServiceFilterResult(@Body ServiceCategoryParam param, Callback<MessageTo<List<ServiceMainExpandTo>>> callback);

    /***
     * 获取地点
     *
     * @return
     */
    @GET("/api/v1/home/service/serviceInspectionPositionMessage/{aparmentSid}/{parentPosition}")
    void getPosition(@Path("aparmentSid") String aparmentSid,@Path("parentPosition") int parentPosition, Callback<MessageTo<List<ServiceInspectionPositionTo>>> callback);

    /***
     * 根据sid获取地址列表
     *
     * @return
     */
    @GET("/api/v1/home/service/serviceInspectionPositionInfo/{apartmentSid}/{positionSid}")
    void getPositionBySid(@Path("apartmentSid") String aparmentSid,@Path("positionSid") String positionSid, Callback<MessageTo<List<ServiceInspectionPositionTo>>> callback);

    /***
     * 获取违停列表
     *
     * @return
     */
    @POST("/api/v1/home/service/type")
    void getLllegalList(@Body LllegalParkParam param, Callback<MessageTo<List<ServiceTypeTo>>> callback);


}
