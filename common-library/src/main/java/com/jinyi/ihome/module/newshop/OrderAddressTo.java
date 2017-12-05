package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2016/7/23.
 */
public class OrderAddressTo implements Serializable{

    /**
     * name : 熊壮
     * receiverNumber : 15168234205
     * receiver : 湖北省咸宁市咸安区温泉区湖北科技学院核技术与化学生物学院熊壮
     */

    private String name;
    private String receiverNumber;
    private String receiver;

    @Override
    public String toString() {
        return "OrderAddressTo{" +
                "name='" + name + '\'' +
                ", receiverNumber='" + receiverNumber + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(String receiverNumber) {
        this.receiverNumber = receiverNumber;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
