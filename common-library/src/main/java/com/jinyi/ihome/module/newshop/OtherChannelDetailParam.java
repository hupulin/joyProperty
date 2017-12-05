package com.jinyi.ihome.module.newshop;

/**
 * Created by usb on 2017/3/8.
 */
public class OtherChannelDetailParam {

    /**
     * communityId : 14210637513688064
     * currentPage :
     * pageSize :
     */


    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getGrouponId() {
        return grouponId;
    }

    public void setGrouponId(String grouponId) {
        this.grouponId = grouponId;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
    private String communityId;
    private String currentPage;
    private String pageSize;
    private String grouponId;

    @Override
    public String toString() {
        return "OtherChannelDetailParam{" +
                "communityId='" + communityId + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", grouponId='" + grouponId + '\'' +
                '}';
    }
}
