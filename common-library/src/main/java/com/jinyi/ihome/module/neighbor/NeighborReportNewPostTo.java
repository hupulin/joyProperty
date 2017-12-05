package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by usb on 2016/8/25.
 */
public class NeighborReportNewPostTo implements Serializable {
    private static final long serialVersionUID = 1L;

    public Map<String, Integer> getValue() {
        return value;
    }

    public void setValue(Map<String, Integer> value) {
        this.value = value;
    }

    public int getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    /***
     * 目前里面只有 小区帖子总数
     * count  小区帖子总数
     */
    private Map<String, Integer> value;

    private int countTotal;


}
