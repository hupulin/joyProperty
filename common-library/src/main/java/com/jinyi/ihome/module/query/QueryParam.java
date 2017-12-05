package com.jinyi.ihome.module.query;

import java.io.Serializable;

/**
 * Created by usb on 2016/8/15.
 */
public class QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    private int pageIndex;          //索引 页码
    //姓名
    private String ownerName;
    //手机号码
    private String ownerPhone;

    /***
     * 过滤条件
     */
    private String filter;
}
