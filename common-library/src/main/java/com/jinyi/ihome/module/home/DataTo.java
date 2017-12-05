package com.jinyi.ihome.module.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by usb on 2017/6/12.
 */

public class DataTo implements Serializable{
   private List<String> list;
   private String type;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getList() {

        return list;
    }

    public String getType() {
        return type;
    }
}
