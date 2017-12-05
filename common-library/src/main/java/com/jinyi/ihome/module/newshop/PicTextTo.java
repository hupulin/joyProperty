package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2017/1/15.
 */
public class PicTextTo implements Serializable{

    /**
     * id : 20
     * layoutName : mock
     * graphicDetails : mock
     */

    private String id;
    private String layoutName;
    private String graphicDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public String getGraphicDetails() {
        return graphicDetails;
    }

    public void setGraphicDetails(String graphicDetails) {
        this.graphicDetails = graphicDetails;
    }

    @Override
    public String toString() {
        return "PicTextTo{" +
                "id='" + id + '\'' +
                ", layoutName='" + layoutName + '\'' +
                ", graphicDetails='" + graphicDetails + '\'' +
                '}';
    }
}
