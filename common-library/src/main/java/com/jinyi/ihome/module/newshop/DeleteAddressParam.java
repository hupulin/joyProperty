package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by usb on 2017/2/22.
 */
public class DeleteAddressParam implements Serializable{
    private String id;

    @Override
    public String toString() {
        return "DeleteAddressParam{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
