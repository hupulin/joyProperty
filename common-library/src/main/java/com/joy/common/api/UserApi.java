package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.common.DeviceParam;
import com.jinyi.ihome.module.owner.FindVerCodeParam;
import com.jinyi.ihome.module.owner.LoginParam;
import com.jinyi.ihome.module.owner.LostPwdParam;
import com.jinyi.ihome.module.owner.NewPwdParam;
import com.jinyi.ihome.module.owner.ParkUserInfoParam;
import com.jinyi.ihome.module.owner.RegVerCodeParam;
import com.jinyi.ihome.module.owner.ResetPwdParam;
import com.jinyi.ihome.module.owner.UserBasicParam;
import com.jinyi.ihome.module.owner.UserInfoTo;
import com.jinyi.ihome.module.owner.UserLoginParam;
import com.jinyi.ihome.module.owner.UserMessageTo;
import com.jinyi.ihome.module.owner.UserVerificationParam;
import com.jinyi.ihome.module.owner.UserVerificationTo;
import com.joy.common.bean.RegisterParam;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by warrior on 14/10/17
 */

public interface UserApi {



    @POST("/api/v1/user/login")
    void login(@Body LoginParam param, Callback<MessageTo<UserInfoTo>> callback);

    @POST("/api/v1/user/registern")
    void register(@Body RegisterParam param, Callback<MessageTo<UserInfoTo>> cb);


    /**
     * 检查手机号并发送注册验证码
     *
     * @return MessageTo#sucesss 0 发送成功 1 手机号已注册 2 发送失败
     */
    @POST("/api/v1/user/register-verification-code")
    void sendRegisterVerificationCode(@Body RegVerCodeParam param, Callback<MessageTo<String>> callback);

    @POST("/api/v1/user/findUserInfo")
    void findUserInfo(@Path("id") String id, Callback<MessageTo<UserInfoTo>> callback);

    /**
     * * 检查手机号并发送找回密码验证码
     * * @param param
     * * @return MessageTo#sucesss 0 发送成功 1 手机号已注册 2 发送失败
     */
    @POST("/api/v1/user/find-password-verification-code")
    void sendFindPasswordVerificationCode(@Body FindVerCodeParam param, Callback<MessageTo<String>> callback);

    /**
     * 重置用户密码
     *
     * @param param
     * @return
     */
    @POST("/api/v1/user/reset")
    void reset(@Body ResetPwdParam param, Callback<MessageTo<UserInfoTo>> callback);

    /**
     * * -----------悦服务忘记密码---------
     * *检查手机号并发送找回密码验证码
     * * @param param
     * * @return MessageTo#sucesss 0 发送成功 1 手机号已注册 2 发送失败
     */
    @POST("/api/v1/user/findWokerPasswordWithcode")
    void findWokerPasswordWithcode(@Body LostPwdParam param, Callback<MessageTo<String>> callback);

    /**
     * 重置悦服务密码
     *
     * @param param
     * @return
     */
    @POST("/api/v1/user/worker/reset")
    void resetPwd(@Body NewPwdParam param, Callback<MessageTo<UserInfoTo>> callback);

    /**
     * 更新用户头像
     *
     * @param ownerSid
     * @param ownerIcon
     * @param callback
     */
    @GET("/api/v1/user/update_user_icon/{ownerSid}/{ownerIcon}")
    void updateOwnerIcon(@Path("ownerSid") String ownerSid, @Path("ownerIcon") String ownerIcon, Callback<MessageTo<UserInfoTo>> callback);


    /**
     * 用户申请验证
     *
     * @param param
     */
    @POST("/api/v1/user/verify_user_info")
    void verifyUserInfo(@Body UserVerificationParam param, Callback<MessageTo<UserVerificationTo>> callback);


    /**
     * @param sid
     * @param callback
     */
    @GET("api/v1/user/message_read")
    // void msgRead(@Path("sid") String sid, Callback<Void> callback);
    void msgRead(@Path("sid") String sid, Callback<Void> callback);
    /**
     * 个人中心更新用户信息
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/user/update_user_info")
    void updateUserInfo(@Body UserBasicParam param, Callback<MessageTo<UserInfoTo>> callback);

    /**
     * 消息列表
     *
     * @param apartmentSid
     * @param userSid
     * @param pageIndex
     * @param callback
     */
    @GET("/api/v1/user/message/{apartmentSid}/{userSid}/{pageIndex}")
    void findUserMessageList(@Path("apartmentSid") String apartmentSid, @Path("userSid") String userSid, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<UserMessageTo>>> callback);

    /**
     * @param id
     * @param mobile
     */
    @GET("/api/v1/user/get/{id}/{mobile}")
    void findUserInfo(@Path("id") String id, @Path("mobile") String mobile, Callback<MessageTo<UserInfoTo>> callback);

    @GET("/api/v1/user/message_del/{sid}")
    void msgDel(@Path("sid") String sid, Callback<Void> callback);


    /**
     * 物业版
     */

    @GET("/api/v1/user/group_message/{userSid}/{pageIndex}")
    void findGroupUserMessageList(@Path("userSid") String userSid, @Path("pageIndex") int pageIndex , Callback<MessageTo<List<UserMessageTo>> > callback);

    @POST("/api/v1/user/group_login")
    void groupLogin(@Body UserLoginParam param ,Callback<MessageTo<UserInfoTo>> callback);


    /**
     * 校对用户昵称 是否有重复
     * @param param
     * @param callback
     */

    @POST("/api/v1/user/check_nickname")
    void checkNickname(@Body RegisterParam param ,Callback<MessageTo<Boolean>> callback);
    /**
     * 注销接口
     * @param param
     * @param callback
     */

    @POST("/api/v1/account/accountLoggedOut")
    void accountLoggedOut(@Body DeviceParam param , Callback<MessageTo> callback);
    /**
     * 自主验证
     * @param param
     * @param callback
     */
    @POST("/api/v1/user/auto_verify")
    void  autoVerify(@Body UserVerificationParam param ,Callback<MessageTo<UserVerificationTo> > callback);
    @POST("/api/v1/user/auto_verifyn")
    void  autoVerifyn(@Body UserVerificationParam param ,Callback<MessageTo<UserVerificationTo> > callback);
    @POST("/api/v1/user/update_worker_info")
    void updateUserInfoWork(@Body UserBasicParam param, Callback<MessageTo<UserInfoTo>> callback);
    /**
     * 获取园区权限
     */
    @GET("/api/v1/user/findUserInfoMessage/{ownerName}/{ownerPhone}")
    void getParkLimit(@Path("ownerName") String ownerName ,@Path("ownerPhone") String ownerPhone ,Callback<MessageTo<UserInfoTo>> callback);

    /**
     * 获取园区权限Post
     */
    @POST("/api/v1/user/findUserInfo")
    void getParkLimitPost(@Body ParkUserInfoParam param,Callback<MessageTo<UserInfoTo>> callback);
}
