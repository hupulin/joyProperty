package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/9.
 */
public class MyCouponParam {

    /**
     * userId :
     * currentPage :
     * pageSize :
     */

    private String userId;
    private String currentPage;
    private String pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "MyCouponParam{" +
                "userId='" + userId + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", pageSize='" + pageSize + '\'' +
                '}';
    }
}
