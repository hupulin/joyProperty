package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/12.
 */
public class MainInfoTo {

    /**
     * communityId : mock
     * layoutType : 18
     * total : 78
     */

    private String communityId;
    private int layoutType;
    private int total;
    private List<MainInfoDettailTo>list;
    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MainInfoDettailTo> getList() {
        return list;
    }

    public void setList(List<MainInfoDettailTo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MainInfoTo{" +
                "communityId='" + communityId + '\'' +
                ", layoutType=" + layoutType +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
