package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/12.
 */
public class GoodsCommentParam {

    /**
     * goodsId : 15468283711521792
     * userId : 15565416294843392
     * orderId : 15882966493889536
     */

    private String goodsId;
    private String userId;
    private String orderId;
    private List<GoodsCommentParamListTo>orderGoodsList;
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<GoodsCommentParamListTo> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<GoodsCommentParamListTo> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    @Override
    public String toString() {
        return "GoodsCommentParam{" +
                "goodsId='" + goodsId + '\'' +
                ", userId='" + userId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderGoodsList=" + orderGoodsList +
                '}';
    }
}
