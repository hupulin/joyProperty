package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/4/9.
 */
public class GoodsDetailParam {


    /**
     * goodsId :
     * goodsType :
     */

    private String goodsId;
    private String goodsType;
    private String communityId;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    @Override
    public String toString() {
        return "GoodsDetailParam{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsType='" + goodsType + '\'' +
                '}';
    }
}
