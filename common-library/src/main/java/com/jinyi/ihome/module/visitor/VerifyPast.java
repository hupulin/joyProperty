package com.jinyi.ihome.module.visitor;

/**
 * Created by Administrator on 2016/6/16.
 */
public class VerifyPast {
    /***
     * 通过标识 0：通过 1：未通过
     */
    private int passType;
    private String cardNo;
    public int getPassType() {
        return passType;
    }

    public void setPassType(int passType) {
        this.passType = passType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String toString() {
        return "VerifyPast{" +
                "passType=" + passType +
                ", cardNo='" + cardNo + '\'' +
                '}';
    }
}
