package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2017/1/6.
 */
public class BulkUserInfoParam implements Serializable{

    /**
     * oldCommunityId : a241b63a-df3c-43a8-a7fd-477e5f950d9f
     * oldUserId : 909efc19-ee4d-450d-9832-13c94ea3e931
     */

    private String oldCommunityId;
    private String oldUserId;

    public String getOldCommunityId() {
        return oldCommunityId;
    }

    public void setOldCommunityId(String oldCommunityId) {
        this.oldCommunityId = oldCommunityId;
    }

    public String getOldUserId() {
        return oldUserId;
    }

    public void setOldUserId(String oldUserId) {
        this.oldUserId = oldUserId;
    }

    @Override
    public String toString() {
        return "BulkUserInfoParam{" +
                "oldCommunityId='" + oldCommunityId + '\'' +
                ", oldUserId='" + oldUserId + '\'' +
                '}';
    }
}
