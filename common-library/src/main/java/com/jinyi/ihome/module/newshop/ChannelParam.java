package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/8.
 */
public class ChannelParam {
    private String communityId;
    private String grouponId;
    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getGrouponId() {
        return grouponId;
    }

    public void setGrouponId(String grouponId) {
        this.grouponId = grouponId;
    }

    @Override
    public String toString() {
        return "ChannelParam{" +
                "communityId='" + communityId + '\'' +
                '}';
    }
}
