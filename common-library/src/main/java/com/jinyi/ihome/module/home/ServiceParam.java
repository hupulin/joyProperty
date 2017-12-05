package com.jinyi.ihome.module.home;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by usb on 2016/7/26.
 */
public class ServiceParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /***
     * 上一次的浏览时间
     */
    private Date lastTime;

    /***
     * 服务响应的用户
     */
    private String groupUserSid;

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getGroupUserSid() {
        return groupUserSid;
    }

    public void setGroupUserSid(String groupUserSid) {
        this.groupUserSid = groupUserSid;
    }
}
