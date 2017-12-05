package com.jinyi.ihome.module.express;

import java.io.Serializable;

/**
 * Created by usb on 2017/6/13.
 */

public class SendCotificationParam  implements Serializable{
        /*    */   private static final long serialVersionUID = 1L;

            private String mobile;
            private String templateId;
            private String expressName;
            private String expressNo;
            private String fetchCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getFetchCode() {
        return fetchCode;
    }

    public void setFetchCode(String fetchCode) {
        this.fetchCode = fetchCode;
    }

    @Override
    public String toString() {
        return "SendCotificationParam{" +
                "mobile='" + mobile + '\'' +
                ", templateId='" + templateId + '\'' +
                ", expressName='" + expressName + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", fetchCode='" + fetchCode + '\'' +
                '}';
    }
}
