package com.jinyi.ihome.module.home;

import java.io.Serializable;

/**
 * Created by usb on 2017/6/15.
 */

public class MyWorkHandleParam implements Serializable {
 private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "MyWorkHandleParam{" +
                "workSid='" + workSid + '\'' +
                ", processDesc='" + processDesc + '\'' +
                ", processImage='" + processImage + '\'' +
                '}';
    }

    /**
     * workSid :
     * processDesc :
     * processImage :
     */

    private String workSid;
    private String processDesc;
    private String processImage;

    public String getWorkSid() {
        return workSid;
    }

    public void setWorkSid(String workSid) {
        this.workSid = workSid;
    }

    public String getProcessDesc() {
        return processDesc;
    }

    public void setProcessDesc(String processDesc) {
        this.processDesc = processDesc;
    }

    public String getProcessImage() {
        return processImage;
    }

    public void setProcessImage(String processImage) {
        this.processImage = processImage;
    }
}
