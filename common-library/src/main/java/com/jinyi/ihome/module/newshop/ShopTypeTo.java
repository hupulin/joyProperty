package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2016/7/13.
 */
public class ShopTypeTo {

    /**
     * typeName : 频道1
     * typeIcon : http://192.168.1.149:8080/ihome/typeImage/1.png
     * typeSid : 001
     */

    private String typeName;
    private String typeIcon;
    private String typeSid;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    public String getTypeSid() {
        return typeSid;
    }

    public void setTypeSid(String typeSid) {
        this.typeSid = typeSid;
    }

    @Override
    public String toString() {
        return "ShopTypeTo{" +
                "typeName='" + typeName + '\'' +
                ", typeIcon='" + typeIcon + '\'' +
                ", typeSid='" + typeSid + '\'' +
                '}';
    }
}
