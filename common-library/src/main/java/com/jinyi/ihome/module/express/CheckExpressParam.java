package com.jinyi.ihome.module.express;

import java.io.Serializable;

/**
 * Created by usb on 2017/9/11.
 */

public class CheckExpressParam implements Serializable {
    /*    */   private static final long serialVersionUID = 1L;

    private String expressNo;

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }
}
