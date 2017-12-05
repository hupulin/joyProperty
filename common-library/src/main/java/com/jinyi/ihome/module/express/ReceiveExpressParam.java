package com.jinyi.ihome.module.express;

/**
 * Created by usb on 2017/8/3.
 */

public class ReceiveExpressParam {
    private String expressSids; //true string 快递sid串:sid1,sid2....
    private String expressRemark; //false string 取件码
    private String receivePhoto;
    private String handleUserSid;

    @Override
    public String toString() {
        return "ReceiveExpressParam{" +
                "expressSids='" + expressSids + '\'' +
                ", expressRemark='" + expressRemark + '\'' +
                ", receivePhoto='" + receivePhoto + '\'' +
                ", handleUserSid='" + handleUserSid + '\'' +
                '}';
    }

    public String getHandleUserSid() {
        return handleUserSid;
    }

    public void setHandleUserSid(String handleUserSid) {
        this.handleUserSid = handleUserSid;
    }

    public String getExpressSids() {
        return expressSids;
    }

    public void setExpressSids(String expressSids) {
        this.expressSids = expressSids;
    }

    public String getExpressRemark() {
        return expressRemark;
    }

    public void setExpressRemark(String expressRemark) {
        this.expressRemark = expressRemark;
    }

    public String getReceivePhoto() {
        return receivePhoto;
    }

    public void setReceivePhoto(String receivePhoto) {
        this.receivePhoto = receivePhoto;
    }
}
