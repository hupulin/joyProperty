package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/12.
 */
public class PayParam {
    List<OlderSidTo>orderList;

    public List<OlderSidTo> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OlderSidTo> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "PayParam{" +
                "orderList=" + orderList +
                '}';
    }
}
