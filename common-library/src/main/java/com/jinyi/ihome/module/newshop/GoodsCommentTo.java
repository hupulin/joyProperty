package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2016/7/19.
 **/
public class GoodsCommentTo {


    /**
     * goodsId : 15555120369042432
     * evaluateTime : 2017-01-10 13:36:24
     * goodsName : 时间
     * nickName : 总监3
     * headUrl : user_C54D68D2-21FE-4E0F-ADA9-3CFCF70C165D
     */

    private String goodsId;
    private String evaluateTime;
    private String goodsName;
    private String nickName;
    private String headUrl;
    private String  evaluateResult;
    private String  evaluateContent;
    private String goodsDetailsStr;
    private String specificationsName;
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(String evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getEvaluateResult() {
        return evaluateResult;
    }

    public void setEvaluateResult(String evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getGoodsDetailsStr() {
        return goodsDetailsStr;
    }

    public void setGoodsDetailsStr(String goodsDetailsStr) {
        this.goodsDetailsStr = goodsDetailsStr;
    }

    public String getSpecificationsName() {
        return specificationsName;
    }

    public void setSpecificationsName(String specificationsName) {
        this.specificationsName = specificationsName;
    }

    @Override
    public String toString() {
        return "GoodsCommentTo{" +
                "goodsId='" + goodsId + '\'' +
                ", evaluateTime='" + evaluateTime + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", evaluateResult='" + evaluateResult + '\'' +
                ", evaluateContent='" + evaluateContent + '\'' +
                '}';
    }
}
