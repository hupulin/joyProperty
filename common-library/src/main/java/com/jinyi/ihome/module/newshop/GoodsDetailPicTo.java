package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/14.
 */
public class GoodsDetailPicTo {

    /**
     * goodsId : 16300227493954560
     * picUrl : web_6e253999-cf0d-4298-b006-d1f19a7788e7
     * description : null
     */

    private long goodsId;
    private String picUrl;
    private String description;

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GoodsDetailPicTo{" +
                "goodsId='" + goodsId + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
