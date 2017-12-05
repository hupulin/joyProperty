package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/10.
 */
public class MyOlderGoods {

    /**
     * goodsId : 15555120369042432
     * goodsName : 时间
     * picUrl : null
     * goodsNum : 17
     */

    private long goodsId;
    private String goodsName;
    private String picUrl;
    private String goodsNum;

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    @Override
    public String toString() {
        return "MyOlderGoods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", picUrl=" + picUrl +
                ", goodsNum='" + goodsNum + '\'' +
                '}';
    }
}
