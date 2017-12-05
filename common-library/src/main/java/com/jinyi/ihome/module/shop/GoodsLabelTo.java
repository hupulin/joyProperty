package com.jinyi.ihome.module.shop;

/**
 * Created by xz on 2017/4/9.
 */
public class GoodsLabelTo {

    /**
     * lableName : mock
     * lableId : mock
     */

    private String lableName;
    private String lableId;

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    public String getLableId() {
        return lableId;
    }

    public void setLableId(String lableId) {
        this.lableId = lableId;
    }

    @Override
    public String toString() {
        return "GoodsLabelTo{" +
                "lableName='" + lableName + '\'' +
                ", lableId='" + lableId + '\'' +
                '}';
    }
}
