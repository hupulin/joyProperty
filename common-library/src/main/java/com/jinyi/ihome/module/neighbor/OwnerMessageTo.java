package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xz on 2016/11/7.
 */
public class OwnerMessageTo implements Serializable {
    private static final long serialVersionUID = 1L;



    //昵称
    private String familyName;
    //头像
    private String ownerImage;

    //用户sid
    private String ownerSid;

    private int flag;
    private Date createdOn;

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(String ownerImage) {
        this.ownerImage = ownerImage;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "OwnerMessageTo{" +
                "familyName='" + familyName + '\'' +
                ", ownerImage='" + ownerImage + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", flag=" + flag +
                ", createdOn=" + createdOn +
                '}';
    }
}
