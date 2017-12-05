package com.joy.property.neighborhood;

/**
 * Created by xz on 2016/4/6.
 */
public class RefreshEvent {
    private String mMsg;
    private String specificationsId;
    private String specificationsName;
    private double retailPrice;
    private int purchaseNumber;
    public RefreshEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
    public RefreshEvent(String msg,String specificationsId,String specificationsName,double retailPrice,int purchaseNumber){
        this.mMsg=msg;
        this.specificationsId=specificationsId;
        this.specificationsName=specificationsName;
        this.retailPrice=retailPrice;
        this.purchaseNumber =purchaseNumber;
    }



    public String getSpecificationsId() {
        return specificationsId;
    }

    public String getSpecificationsName() {
        return specificationsName;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public String getmMsg() {
        return mMsg;
    }
}
