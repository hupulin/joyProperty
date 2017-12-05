package com.jinyi.ihome.module.shop;

/**
 * Created by xz on 2017/4/9.
 */
public class GoodsSpecificationsTo {

    /**
     * specificationsName : mock
     * buyingPrice : mock
     * retailPrice : mock
     * inventoryNum : mock
     * specificationsId : mock
     */

    private String specificationsName;
    private String buyingPrice;
    private double retailPrice;
    private int inventoryNum;
    private String specificationsId;

    public String getSpecificationsName() {
        return specificationsName;
    }

    public void setSpecificationsName(String specificationsName) {
        this.specificationsName = specificationsName;
    }

    public String getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getSpecificationsId() {
        return specificationsId;
    }

    public void setSpecificationsId(String specificationsId) {
        this.specificationsId = specificationsId;
    }

    @Override
    public String toString() {
        return "GoodsSpecificationsTo{" +
                "specificationsName='" + specificationsName + '\'' +
                ", buyingPrice='" + buyingPrice + '\'' +
                ", retailPrice='" + retailPrice + '\'' +
                ", inventoryNum='" + inventoryNum + '\'' +
                ", specificationsId='" + specificationsId + '\'' +
                '}';
    }
}
