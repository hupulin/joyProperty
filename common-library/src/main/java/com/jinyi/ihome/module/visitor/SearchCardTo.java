package com.jinyi.ihome.module.visitor;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/16.
 */
public class SearchCardTo {
    private static final long serialVersionUID = 1L;

    /***
     * 服务响应的用户
     */
    private String groupUserSid;
    /***
     * 页数
     */
    private int pageIndex;
    /***
     * 响应时间  yyyy-MM-dd
     */
    private Date responseTime;

    public String getGroupUserSid() {
        return groupUserSid;
    }

    public void setGroupUserSid(String groupUserSid) {
        this.groupUserSid = groupUserSid;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }
}
