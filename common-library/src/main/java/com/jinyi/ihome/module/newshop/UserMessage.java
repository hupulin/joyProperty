package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/6.
 */
public class UserMessage {

   private String userId;
    private String       message;
      private int      code;
    private String        communityId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                ", communityId='" + communityId + '\'' +
                '}';
    }
}
