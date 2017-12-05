package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/12.
 */
public class GoodsCommentParamListTo {
    /**
     * goodsId :
     * evaluateResult :
     * evaluateContent :
     * goodsType :
     * specificationsId :
     * specificationsName :
     */

    private String goodsId;
    private String evaluateResult;
    private String evaluateContent;
    private String goodsType;
    private String specificationsId;
    private String specificationsName;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getSpecificationsId() {
        return specificationsId;
    }

    public void setSpecificationsId(String specificationsId) {
        this.specificationsId = specificationsId;
    }

    public String getSpecificationsName() {
        return specificationsName;
    }

    public void setSpecificationsName(String specificationsName) {
        this.specificationsName = specificationsName;
    }

    @Override
    public String toString() {
        return "GoodsCommentParamListTo{" +
                "goodsId='" + goodsId + '\'' +
                ", evaluateResult='" + evaluateResult + '\'' +
                ", evaluateContent='" + evaluateContent + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", specificationsId='" + specificationsId + '\'' +
                ", specificationsName='" + specificationsName + '\'' +
                '}';
    }
}
