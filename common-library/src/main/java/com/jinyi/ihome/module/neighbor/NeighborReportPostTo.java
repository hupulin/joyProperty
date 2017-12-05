package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by usb on 2016/8/22.
 */
public class NeighborReportPostTo  implements Serializable {

    private static final long serialVersionUID = 1L;


    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public Map<String, Integer> getValue() {
        return value;
    }

    public void setValue(Map<String, Integer> value) {
        this.value = value;
    }

    private String apartmentSid;        //小区SID

    private String apartmentName;       //小区名称


    /*** 目前里面只有 小区帖子总数
     * count  小区帖子总数
     */
    private Map<String, Integer> value;
}
