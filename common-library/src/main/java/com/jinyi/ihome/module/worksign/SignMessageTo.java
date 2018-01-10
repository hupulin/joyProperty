package com.jinyi.ihome.module.worksign;

import java.io.Serializable;


public class SignMessageTo<T>
        implements Serializable {
    private int ResultCode;
    private String Reason;

    private T ResultContent;

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public T getResultContent() {
        return ResultContent;
    }

    public void setResultContent(T resultContent) {
        ResultContent = resultContent;
    }

    public SignMessageTo(int resultCode) {
        ResultCode = resultCode;
    }

    public SignMessageTo(int resultCode, String reason) {
        ResultCode = resultCode;
        Reason = reason;
    }

    @Override
    public String toString() {
        return "SignMessageTo{" +
                "ResultCode=" + ResultCode +
                ", Reason='" + Reason + '\'' +
                ", ResultContent=" + ResultContent +
                '}';
    }
}

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.infrastructure.MessageTo
 * JD-Core Version:    0.6.2
 */