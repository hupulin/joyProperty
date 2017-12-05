package com.joy.common.api;



import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.system.GroupMenuParam;
import com.jinyi.ihome.module.system.GroupMenuTo;
import com.jinyi.ihome.module.system.RoleRightsTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Admin on 2015-08-10
 */
public interface SystemApi {
     @POST("/api/v1/system/group_menu")
      void  findByUser(@Body GroupMenuParam params , Callback<MessageTo<List<GroupMenuTo>>> callback);
    @GET("/api/v1/user/takePictureIsHasAuthority/{userSid}")
      void  findPhotoLimit(@Path("userSid") String userSid , Callback<MessageTo<Boolean>> callback);
    @POST("/api/v1/system/newGroup_menu")
    void  findByNewUser(@Body GroupMenuParam params , Callback<MessageTo<List<GroupMenuTo>>> callback);
    @POST("/api/v1/system/newGroup_menuMessage")
    void  findByTwiceUser(@Body GroupMenuParam params , Callback<MessageTo<List<GroupMenuTo>>> callback);
    @POST("/api/v1/system/roleGroup_menu")
    void  onTheWay(@Body GroupMenuParam params , Callback<MessageTo<List<GroupMenuTo>>> callback);
///*
//*获取拥有的园区权限
// */
//    @GET("/api/v1/user/findUserInfoMessage/{ownerName}/{ownerPhone}")
//    void getParkLimit(@Path("ownerName")String ownerName,@Path("ownerPhone")String ownerPhone,Callback<MessageTo<List<GroupMenuTo>>> callback);
}
