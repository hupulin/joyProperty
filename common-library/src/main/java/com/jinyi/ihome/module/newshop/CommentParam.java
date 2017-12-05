package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2017/1/6.
 */
public class CommentParam implements Serializable {


    /**
     * goodsId : 15555120369042432
     * currentPage :
     * pageSize :
     */

    private String goodsId;
    private String currentPage;
    private String pageSize;
    private String specificationsId;
    private String goodsType;
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSpecificationsId() {
        return specificationsId;
    }

    public void setSpecificationsId(String specificationsId) {
        this.specificationsId = specificationsId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "CommentParam{" +
                "goodsId='" + goodsId + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", specificationsId='" + specificationsId + '\'' +
                ", goodsType='" + goodsType + '\'' +
                '}';
    }
}
