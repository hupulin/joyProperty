package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by usb on 2017/2/21.
 */
public class DefaultAddressParam implements Serializable {
    private String id;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
