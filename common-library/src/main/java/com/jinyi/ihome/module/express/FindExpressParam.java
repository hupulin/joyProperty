package com.jinyi.ihome.module.express;

import java.io.Serializable;

/**
 * Created by usb on 2017/8/3.
 */

public class FindExpressParam  implements Serializable {

    /*    */   private static final long serialVersionUID = 1L;
    /*    */   private int nextPage;
    /*    */   private String search;

    @Override
    public String toString() {
        return "FindExpressParam{" +
                "nextPage=" + nextPage +
                ", search='" + search + '\'' +
                ", handleUserSid='" + handleUserSid + '\'' +
                ", limit='" + limit + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", expressStatusStr='" + expressStatusStr + '\'' +
                '}';
    }

    public String getHandleUserSid() {
        return handleUserSid;
    }

    public void setHandleUserSid(String handleUserSid) {
        this.handleUserSid = handleUserSid;
    }

    /*    */   private String handleUserSid;

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    /*    */   private String limit;
    /*    */   private String apartmentSid;
    /*    */   private String expressStatusStr;
//    expressStatusStr true string 快递状态: 2 待领取(可领取快递), 3 已领取(领取记录)


    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getExpressStatusStr() {
        return expressStatusStr;
    }

    public void setExpressStatusStr(String expressStatusStr) {
        this.expressStatusStr = expressStatusStr;
    }
}
