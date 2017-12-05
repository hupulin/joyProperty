package com.joyhome.nacity.app.bean;

import com.jinyi.ihome.module.neighbor.NeighborPostTypeTo;

import java.util.List;

/**
 * Created by xzz on 2016/3/3.
 */
public class FirstLoadType {
   private String  success;
    private String  message;
   private  int total;
    private String tag;
List<NeighborPostTypeTo> data;

    public FirstLoadType(String success, String message, int total, String tag, List<NeighborPostTypeTo> data) {
        this.success = success;
        this.message = message;
        this.total = total;
        this.tag = tag;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<NeighborPostTypeTo> getData() {
        return data;
    }

    public void setData(List<NeighborPostTypeTo> data) {
        this.data = data;
    }
}
