package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/5/23.
 */
public class UserAddressTo {


    /**
     * userName : mock
     * userAccount : mock
     * userId : mock
     * userMobile : mock
     * ownerCategory : mock
     * userType : mock
     * communityId : mock
     * communityName : mock
     * receiverDetailAddress : mock
     */

    private String userName;
    private String userAccount;
    private String userId;
    private String userMobile;
    private String ownerCategory;
    private String userType;
    private String communityId;
    private String communityName;
    private String receiverDetailAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getOwnerCategory() {
        return ownerCategory;
    }

    public void setOwnerCategory(String ownerCategory) {
        this.ownerCategory = ownerCategory;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    @Override
    public String toString() {
        return "UserAddressTo{" +
                "userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userId='" + userId + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", ownerCategory='" + ownerCategory + '\'' +
                ", userType='" + userType + '\'' +
                ", communityId='" + communityId + '\'' +
                ", communityName='" + communityName + '\'' +
                ", receiverDetailAddress='" + receiverDetailAddress + '\'' +
                '}';
    }
}
