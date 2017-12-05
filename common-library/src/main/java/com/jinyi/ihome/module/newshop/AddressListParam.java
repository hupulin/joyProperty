package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by usb on 2017/2/19.
 */
public class AddressListParam implements Serializable {
    @Override
    public String toString() {
        return "AddressListParam{" +
                "userId='" + userId + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
         * Id :
         */

        private String userId;


}

