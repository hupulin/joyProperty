package com.jinyi.ihome.module.express;

import java.io.Serializable;

/**
 * Created by usb on 2017/6/13.
 */

public class ExpressRecordParam  implements Serializable {

/*    */   private static final long serialVersionUID = 1L;
/*    */   private String userSid;
/*    */   private int pageIndex;

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
/*    */
    }
