package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/10.
 */
public class PayInfo {
    private String  name;
    private String  money;
    private List<PayOlderTo>payPartners;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<PayOlderTo> getPayPartners() {
        return payPartners;
    }

    public void setPayPartners(List<PayOlderTo> payPartners) {
        this.payPartners = payPartners;
    }

    @Override
    public String toString() {
        return "PayInfo{" +
                "name='" + name + '\'' +
                ", money='" + money + '\'' +
                ", payPartners=" + payPartners +
                '}';
    }
}
