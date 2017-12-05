package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.car.CarFindParam;
import com.jinyi.ihome.module.car.CarParam;
import com.jinyi.ihome.module.car.CarTo;
import com.jinyi.ihome.module.car.CarViolationHistoryParam;
import com.jinyi.ihome.module.car.CarViolationParam;
import com.jinyi.ihome.module.car.CarViolationTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Admin on 2015-03-23
 */
public interface CarApi {

    /**
     * 查找车辆
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/car/find")
    void findCar(@Body CarFindParam param, Callback<MessageTo<CarTo>> callback);

    /**
     * 更新车辆信息
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/car/update")
    void updateCar(@Body CarParam param, Callback<MessageTo<CarTo>> callback);

    /**
     * 新增车辆违停
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/car/violation")
    void violation(@Body CarViolationParam param, Callback<MessageTo<CarTo>> callback);

    /**
     * 车辆违停历史记录
     * @param param
     * @param callback
     */
    @POST("/api/v1/car/violation_history")
    void violationHistory(@Body CarViolationHistoryParam param, Callback<MessageTo<List<CarViolationTo>>> callback);

    /**
     * 获取车的品牌与颜色
     * @param callback
     */
   @GET("/api/v1/car/config")
    void loadConfig(Callback<MessageTo<String[][]>> callback);
}
