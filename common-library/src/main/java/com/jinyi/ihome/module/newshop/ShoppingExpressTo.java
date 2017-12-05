package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2016/7/25.
 */
public class ShoppingExpressTo {

    /**
     * company : 圆通快递
     * isProperty : false
     * expressNumber : 145943092744444
     * deliverName : 熊壮
     * deliverPhone : 15168234205
     * expressInformation : 你写给我，我的第一首歌，你和我十指紧扣，默写前奏，可是那然后呢，还好我有
     */

    private String company;
    private boolean isProperty;
    private String expressNumber;
    private String deliverName;
    private String deliverPhone;
    private String expressInformation;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isIsProperty() {
        return isProperty;
    }

    public void setIsProperty(boolean isProperty) {
        this.isProperty = isProperty;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getDeliverPhone() {
        return deliverPhone;
    }

    public void setDeliverPhone(String deliverPhone) {
        this.deliverPhone = deliverPhone;
    }

    public String getExpressInformation() {
        return expressInformation;
    }

    public void setExpressInformation(String expressInformation) {
        this.expressInformation = expressInformation;
    }

    @Override
    public String toString() {
        return "ShoppingExpressTo{" +
                "company='" + company + '\'' +
                ", isProperty=" + isProperty +
                ", expressNumber='" + expressNumber + '\'' +
                ", deliverName='" + deliverName + '\'' +
                ", deliverPhone='" + deliverPhone + '\'' +
                ", expressInformation='" + expressInformation + '\'' +
                '}';
    }
}
