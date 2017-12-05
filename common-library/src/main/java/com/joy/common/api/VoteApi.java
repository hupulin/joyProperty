package com.joy.common.api;



import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.vote.VoteAnswerParam;
import com.jinyi.ihome.module.vote.VoteFindParam;
import com.jinyi.ihome.module.vote.VoteItemParam;
import com.jinyi.ihome.module.vote.VoteItemTo;
import com.jinyi.ihome.module.vote.VoteTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Admin on 2015-07-09
 */
public interface VoteApi {


    /**
     * 获取调查列表
     * @param param
     * @param callback
     */
    @POST("/api/v1/vote/list")
    void  findVoteList(@Body VoteFindParam param , Callback<MessageTo<List<VoteTo>>> callback);

    /**
     * 调查详情
     * @param param
     * @param callback
     */
    @POST("/api/v1/vote/detail")
   void findVoteDetail(@Body VoteItemParam param ,Callback< MessageTo<List<VoteItemTo>> > callback);

    /**
     * 提交用户投票的数据
     * @param param
     * @param callback
     */
    @POST("/api/v1/vote/user_vote")
    void  userVote(@Body VoteAnswerParam param ,Callback<MessageTo<String>> callback);
}
