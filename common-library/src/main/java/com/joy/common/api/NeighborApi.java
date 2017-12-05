package com.joy.common.api;

import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.home.ServiceReportFlowTo;
import com.jinyi.ihome.module.neighbor.MyNeighborJoinTo;
import com.jinyi.ihome.module.neighbor.NeighborCommentAndLikeParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentFindParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentHandleParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentParam;
import com.jinyi.ihome.module.neighbor.NeighborCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborHandleParam;
import com.jinyi.ihome.module.neighbor.NeighborJoinMessageParam;
import com.jinyi.ihome.module.neighbor.NeighborLikeParam;
import com.jinyi.ihome.module.neighbor.NeighborLikeTo;
import com.jinyi.ihome.module.neighbor.NeighborMessageParam;
import com.jinyi.ihome.module.neighbor.NeighborMessageParam2;
import com.jinyi.ihome.module.neighbor.NeighborMessageParam3;
import com.jinyi.ihome.module.neighbor.NeighborPostFindParam;
import com.jinyi.ihome.module.neighbor.NeighborPostParam;
import com.jinyi.ihome.module.neighbor.NeighborPostTo;
import com.jinyi.ihome.module.neighbor.NeighborPostTypeTo;
import com.jinyi.ihome.module.neighbor.NeighborUserCommentTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodInteractionTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodLogTo;
import com.jinyi.ihome.module.neighbor.NeighborhoodUserConnectParam;
import com.jinyi.ihome.module.neighbor.NeighborhoodUserConnectTo;
import com.jinyi.ihome.module.neighbor.OwnerMessageTo;
import com.jinyi.ihome.module.neighbor.ServiceRedPointTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Admin on 2014-11-25
 */
public interface NeighborApi {

    /**
     * 统计业主数量
     *
     * @param apartmentSid
     * @param callback
     */
    @GET("/api/v1/neighbor/owner_qty/{apartmentSid}")
    void countByApartmentSidAndOwnerType(@Path("apartmentSid") String apartmentSid, Callback<MessageTo<Integer>> callback);

    /**
     * 返回当前小区邻居圈发帖
     *
     * @param apartmentSid
     * @param pageIndex
     * @param callback
     */

    @GET("/api/v1/neighbor/{apartmentSid}/{pageIndex}")
    void findNeighborPostListByApartment(@Path("apartmentSid") String apartmentSid, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<NeighborPostTo>>> callback);

    @GET("/api/v1/neighbor/getallbytype/{apartmentSid}/{id}/{pageIndex}")
    void findNeighborPostListByApartmentSort(@Path("apartmentSid") String apartmentSid, @Path("id") int id, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<NeighborPostTo>>> callback);

    /**
     * 添加邻居圈点赞
     *
     * @param param
     * @param callback
     */

    @POST("/api/v1/neighbor/add_neighbor_like")
    void addNeighborLike(@Body NeighborLikeParam param, Callback<MessageTo<NeighborLikeTo>> callback);


    /**
     * 返回邻居圈发帖类别
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/post_type")
    void findNeighborPostTypeList(Callback<MessageTo<List<NeighborPostTypeTo>>> callback);

    @GET("/api/v1/neighbor/del_postcomment/{commentSID}")
    void deleteComment(@Path("commentSID") String commentSID, Callback<MessageTo<List<NeighborCommentHandleParam>>> callback);
    /**
     * 添加邻居圈发帖
     * @param param
     * @param callback
     */
// @POST("/api/v1/neighbor/add_neighbor_post")
// void addNeighborPost(@Body NeighborPostParam param, Callback<MessageTo<NeighborPostTo>> callback);

    /**
     * 添加邻居圈评论
     * @param param
     * @param callback
     */
//    @POST("/api/v1/neighbor/add_neighbor_comment")
//   void  addNeighborComment(@Body NeighborCommentParam param, Callback<MessageTo<NeighborCommentTo>> callback);


    /**
     * 删除邻居圈发帖
     *
     * @param postSid
     * @param callback
     */
    @GET("/api/v1/neighbor/del_neighbor_post/{postSid}")
    void delNeighborPost(@Path("postSid") String postSid, Callback<MessageTo<Void>> callback);

    /**
     * 获取新的用户的评论
     *
     * @param param
     * @param callback
     */

    @POST("/api/v1/neighbor/new_comment")
    void findNeighborPostUserNewComment(@Body NeighborMessageParam param, Callback<MessageTo<List<NeighborUserCommentTo>>> callback);

    @POST("/api/v1/neighbor/new_like")
    void findNeighborPostUserNewLike(@Body NeighborMessageParam param, Callback<MessageTo<List<NeighborLikeTo>>> callback);

    /**
     * 获取更早的用户的评论
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/neighbor/old_comment")
    void findNeighborPostUserOldComment(@Body NeighborMessageParam param, Callback<MessageTo<List<NeighborUserCommentTo>>> callback);
    /**
     * 邻居圈的小红点
     * PS：悦服务app邻居圈获取最新的发帖
     **/
    @POST("/api/v1/neighbor/new_post")
    void haveNeighborRedPoint(@Body NeighborMessageParam param,Callback<MessageTo<List<NeighborPostTo>>> callback);
    /**
     * 邻居圈改版后V2
     */

    /**
     * 返回当前小区邻居圈发帖
     *
     * @param param
     * @param callback
     */
    @POST("/api/v2/neighbor/list")
    void findTopicList(@Body NeighborPostFindParam param, Callback<MessageTo<List<NeighborPostTo>>> callback);

    /**
     * 添加邻居圈发帖
     *
     * @param param
     * @param callback
     */
    @POST("/api/v2/neighbor/add_post")
    void addNeighborPost(@Body NeighborPostParam param, Callback<MessageTo<NeighborPostTo>> callback);

    /**
     * 删除邻居圈发帖
     *
     * @param param
     * @param callback
     */
    @POST("/api/v2/neighbor/del_post")
    void delNeighborPost(@Body NeighborHandleParam param, Callback<MessageTo<Void>> callback);

    /**
     * 添加邻居圈点赞
     *
     * @param param
     * @param callback
     */
    @POST("/api/v2/neighbor/add_like")
    void addNeighborLike(@Body NeighborHandleParam param, Callback<MessageTo<NeighborLikeTo>> callback);


    /**
     * 举报邻居圈发帖
     *
     * @param param
     * @return
     */
    @POST("/api/v2/neighbor/report_post")
    void reportNeighborPost(@Body NeighborHandleParam param, Callback<MessageTo<Void>> callback);

    /**
     * 评论列表
     *
     * @param param
     * @param callback
     */

    @POST("/api/v2/neighbor/comment_list")
    void findComment(@Body NeighborCommentFindParam param, Callback<MessageTo<List<NeighborCommentTo>>> callback);


    /**
     * 添加邻居圈评价
     *
     * @param param
     * @param callback
     */
    @POST("/api/v2/neighbor/add_comment")
    void addNeighborComment(@Body NeighborCommentParam param, Callback<MessageTo<NeighborCommentTo>> callback);

    /**
     * 删除用户自己的评论
     *
     * @param param
     * @param callback
     */
    @POST("/api/v2/neighbor/del_comment")
    void delComment(@Body NeighborCommentHandleParam param, Callback<MessageTo<String>> callback);

    /**
     * 添加邻居圈分享
     *
     * @param param
     * @param callback
     */
    @POST("/api/v2/neighbor/add_share")
    void addShared(@Body NeighborHandleParam param, Callback<MessageTo<Void>> callback);

    @GET("/api/v1/neighbor/getpostreport/{ownerSid}")
    void getApartmentList(@Path("ownerSid") String ownerSid, Callback<MessageTo<List<ServiceReportFlowTo>>> callback);

    //获取我发的帖子
    @GET("/api/v1/neighbor/ownercreatePOST/{postOwner}/{pageIndex}")
    void getMyPost(@Path("postOwner") String postOwner, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<NeighborPostTo>>> callback);

    @GET("/api/v1/neighbor//ownerJoinPOST/{postOwner}/{pageIndex}")
    void getMyNeighborJoin(@Path("postOwner") String postOwner, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<NeighborPostTo>>> callback);
    //悦服务获取我的帖子

    /**
     * 我发的帖子(根据小区sid和用户sid)
     * http://121.43.158.111:9007/ihome/api/v1/neighbor/ownercreatePOST/{apartmentSid}/{postOwner}/{pageIndex}
     * <p>
     * 我参与的帖子(根据小区sid和用户sid)
     * http://121.43.158.111:9007/ihome/api/v1/neighbor/ownerJoinPOST/{apartmentSid}/{postOwner}/{pageIndex}
     */
    @GET("/api/v1/neighbor/ownercreatePOST/{apartmentSid}/{postOwner}/{pageIndex}")
    void getMyPosts(@Path("apartmentSid") String apartmentSid,@Path("postOwner") String postOwner, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<NeighborPostTo>>> callback);

    @GET("/api/v1/neighbor/ownerJoinPOST{apartmentSid}/{postOwner}/{pageIndex}")
    void getMyNeighborJoins(@Path("apartmentSid") String apartmentSid,@Path("postOwner") String postOwner, @Path("pageIndex") int pageIndex, Callback<MessageTo<List<NeighborPostTo>>> callback);
    /**
     * 获取帖子更新红点
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/neighbor/myNewNeighborMessage")
    void getNeighborRedPoint(@Body NeighborMessageParam2 param,Callback<ServiceRedPointTo>callback);
    /**
     * 获取邻居圈最新回复
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/neighbor/newCommentMessage")
    void getNewReply(@Body NeighborMessageParam3 param,Callback<MessageTo<List<NeighborUserCommentTo>>> callback);
    /**
     * 获取邻居圈首页的轮播图
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/findNeighborhoodInteractionList/2/{apartmentSid}")
    void getAutoRow(@Path("apartmentSid")String apartmentSid,Callback<MessageTo<List<NeighborhoodInteractionTo>>>callback);
    /**
     * 获取邻居圈互动详情页
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/findNeighborhoodInteractioninfo/{refId}/{apartmentSid}/{ownerSid}")
    void getInteractionDetail(@Path("refId")String refId,@Path("apartmentSid")String apartmentSid,@Path("ownerSid")String ownerSid,Callback<MessageTo<NeighborhoodInteractionTo>>callback);
    /**
     * 获取邻居圈互动列表
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/findNeighborhoodInteractionInfoByinteractionType/{apartmentSid}/{interactionType}/{pageIndex}")
    void getInteractionList(@Path("apartmentSid")String apartmentSid,@Path("interactionType")int interactionType,@Path("pageIndex")int pageIndex,Callback<MessageTo<List<NeighborhoodInteractionTo>>>callback);
    /**
     * 关注别人
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/neighbor/saveNeighborhoodUserConnect")
    void getCare(@Body NeighborhoodUserConnectParam param,Callback<MessageTo<NeighborhoodUserConnectTo>> callback);
    /**
     * 删除关注人
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/deleteNeighborhoodUserConnectUser/{ownerSid}/{toOwnerSid}")
    void deleteCare(@Path("ownerSid")String ownerSid,@Path("toOwnerSid")String toOwnerSid,Callback<MessageTo<Boolean>>callback);

    /**
     * 获取关注列表
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/findNeighborhoodUserConnectUser/{ownerSid}/{pageIndex}")
    void getCareList(@Path("ownerSid")String ownerSid,@Path("pageIndex")int pageIndex,Callback<MessageTo<List<OwnerMessageTo>>>callback);
    /**
     * 获取粉丝列表
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/findNeighborhoodUserConnectUserFollower/{ownerSid}/{pageIndex}")
    void getFansList(@Path("ownerSid")String ownerSid,@Path("pageIndex")int pageIndex,Callback<MessageTo<List<OwnerMessageTo>>>callback);
    /**
     * 获取粉我或者其他人发的帖子
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/ownercreatePOST/{apartmentSid}/{postOwner}/{toOwnerSid}/{pageIndex}")
    void getAllPost(@Path("apartmentSid")String apartmentSid,@Path("postOwner")String postOwner,@Path("toOwnerSid")String toOwnerSid,@Path("pageIndex")int pageIndex,Callback<MessageTo<List<NeighborPostTo>>>callback);
    /**
     * 参加活动或者调查
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/neighbor/savNeighborhoodLog")
    void JoinCampaign(@Body NeighborhoodLogParam param,Callback<MessageTo<NeighborhoodLogTo>> callback);
    /**
     * 获取参与红点
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/neighbor/findownerJoinPOSTMessage")
    void getJoinRedPoint(@Body NeighborJoinMessageParam param,Callback<MessageTo<List<NeighborPostTo>>> callback);

    /**
     * 获取参与红点
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/neighbor/findMyCareOwnerMessage")
    void getCareRedPoint(@Body NeighborJoinMessageParam param,Callback<MessageTo<Integer>> callback);
    /**
     * 获取粉丝红点
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/neighbor/findMyFlowerOwnerMessage")
    void getFansRedPoint(@Body NeighborJoinMessageParam param,Callback<MessageTo<Integer>> callback);

    /**
     * 获取最新回复
     *
     * @param param
     * @param callback
     */
    @POST("/api/v1/neighbor/queryCommentAndLikeInfo")
    void getNewReply(@Body NeighborCommentAndLikeParam param,Callback<MessageTo<List<NeighborPostTo>>> callback);
    /**
     * 获取我关注人的帖子
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/myCareOwnerNeighborPostList/{ownerSid}/{pageIndex}")
    void getMyCareList(@Path("ownerSid")String ownerSid,@Path("pageIndex")int pageIndex,Callback<MessageTo<List<NeighborPostTo>>>callback);

    /**
     * 获取我参加话题的帖子
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/findOwnerPOSTNeighborPost/{apartmentSid}/{postOwner}/{pageIndex}")
    void getJoinOrTopic(@Path("apartmentSid")String apartmentSid,@Path("postOwner")String postOwner,@Path("pageIndex")int pageIndex,Callback<MessageTo<List<NeighborPostTo>>>callback);
    /**
     * 通过帖子id获取帖子详情
     *
     * @param callback
     */
    @GET("/api/v1/neighbor/getOnePost/{postSid}")
    void findPostDetail(@Path("postSid") String postSid, Callback<MessageTo<NeighborPostTo>> callback);

}
