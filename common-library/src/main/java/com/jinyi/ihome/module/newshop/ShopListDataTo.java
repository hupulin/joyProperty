package com.jinyi.ihome.module.newshop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by usb on 2017/4/12.
 */
public class ShopListDataTo implements Serializable{
        private  int nextPage ;// 下一页页码， 0 表示没有下一页了
        private int limit ;// number 每页数据条数
        private  int total ;//true number 记录总数
        private String couponId;// true string
        private  String  communityId;
        private List<ShopListDetailTo> list;//商品列表
    @Override
    public String toString() {
        return "ShopListDataTo{" +
                "nextPage=" + nextPage +
                ", limit=" + limit +
                ", total=" + total +
                ", couponId='" + couponId + '\'' +
                ", communityId='" + communityId + '\'' +
                ", list=" + list +
                '}';
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public List<ShopListDetailTo> getList() {
        return list;
    }

    public void setList(List<ShopListDetailTo> list) {
        this.list = list;
    }
}
