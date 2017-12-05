package com.jinyi.ihome.module.neighbor;

import com.jinyi.ihome.module.owner.UserBasicTo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xz on 2016/5/26.
 */
public class MyNeighborJoinTo implements Serializable{

    /**
     * postSid : e804a80b-5cc8-4c43-946e-5b8bf35a2466
     * apartmentSid : a241b63a-df3c-43a8-a7fd-477e5f950d9f
     * postTypeName : 随便说说
     * postContent : 😜
     * postImages : null
     * postOwner : {"sid":"04891f97-571a-4371-9143-421292d5420e","name":"悦悦","icon":"user_AABF31A0-E969-4A22-9D5B-E20AAE366D85","phone":null}
     * postTime : 2016-05-19 13:59:21
     * postTimeStr : 6天前
     * commentList : []
     * likeList : [{"likeSid":"517675e1-5981-4343-8de5-fd495ca5fe27","postSid":"e804a80b-5cc8-4c43-946e-5b8bf35a2466","likeOwner":{"sid":"909efc19-ee4d-450d-9832-13c94ea3e931","name":"你好","icon":null,"phone":null},"likeTime":null},{"likeSid":"9ca80c4a-20de-4d24-ac00-7870b55f41dd","postSid":"e804a80b-5cc8-4c43-946e-5b8bf35a2466","likeOwner":{"sid":"1a2a9444-a315-49db-be7e-0d7d0543fb76","name":"","icon":null,"phone":null},"likeTime":null},{"likeSid":"9f501175-d06f-4aad-b21c-d11bef60d0b6","postSid":"e804a80b-5cc8-4c43-946e-5b8bf35a2466","likeOwner":{"sid":"4dc146bb-4b6e-4108-b9ae-6b7e298c2816","name":"取个长点的名字不方便","icon":"user_9F89E29B-37D2-46A4-B253-492DBC79F929","phone":null},"likeTime":null}]
     * postTag : 0
     * postSticky : 0
     * stickyEndTime : null
     * countComment : 0
     * countLike : 0
     * countShare : 0
     * userLike : false
     */

    private String postSid;
    private String apartmentSid;
    private String postTypeName;
    private String postContent;
    private String postImages;
    /**
     * sid : 04891f97-571a-4371-9143-421292d5420e
     * name : 悦悦
     * icon : user_AABF31A0-E969-4A22-9D5B-E20AAE366D85
     * phone : null
     */

    private UserBasicTo postOwner;
    private Date postTime;
    private String postTimeStr;
    private int postTag;
    private int postSticky;
    private Object stickyEndTime;
    private int countComment;
    private int countLike;
    private int countShare;
    private boolean userLike;
    private List<NeighborCommentTo> commentList;
    /**
     * likeSid : 517675e1-5981-4343-8de5-fd495ca5fe27
     * postSid : e804a80b-5cc8-4c43-946e-5b8bf35a2466
     * likeOwner : {"sid":"909efc19-ee4d-450d-9832-13c94ea3e931","name":"你好","icon":null,"phone":null}
     * likeTime : null
     */

    private List<NeighborLikeTo> likeList;

    public String getPostSid() {
        return postSid;
    }

    public void setPostSid(String postSid) {
        this.postSid = postSid;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getPostTypeName() {
        return postTypeName;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostImages() {
        return postImages;
    }

    public void setPostImages(String postImages) {
        this.postImages = postImages;
    }

    public UserBasicTo getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(UserBasicTo postOwner) {
        this.postOwner = postOwner;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getPostTimeStr() {
        return postTimeStr;
    }

    public void setPostTimeStr(String postTimeStr) {
        this.postTimeStr = postTimeStr;
    }

    public int getPostTag() {
        return postTag;
    }

    public void setPostTag(int postTag) {
        this.postTag = postTag;
    }

    public int getPostSticky() {
        return postSticky;
    }

    public void setPostSticky(int postSticky) {
        this.postSticky = postSticky;
    }

    public Object getStickyEndTime() {
        return stickyEndTime;
    }

    public void setStickyEndTime(Object stickyEndTime) {
        this.stickyEndTime = stickyEndTime;
    }

    public int getCountComment() {
        return countComment;
    }

    public void setCountComment(int countComment) {
        this.countComment = countComment;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public int getCountShare() {
        return countShare;
    }

    public void setCountShare(int countShare) {
        this.countShare = countShare;
    }

    public boolean isUserLike() {
        return userLike;
    }

    public void setUserLike(boolean userLike) {
        this.userLike = userLike;
    }

    public List<NeighborCommentTo> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<NeighborCommentTo> commentList) {
        this.commentList = commentList;
    }

    public List<NeighborLikeTo> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<NeighborLikeTo> likeList) {
        this.likeList = likeList;
    }

    @Override
    public String toString() {
        return "MyNeighborJoinTo{" +
                "postSid='" + postSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", postTypeName='" + postTypeName + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postImages='" + postImages + '\'' +
                ", postOwner=" + postOwner +
                ", postTime=" + postTime +
                ", postTimeStr='" + postTimeStr + '\'' +
                ", postTag=" + postTag +
                ", postSticky=" + postSticky +
                ", stickyEndTime=" + stickyEndTime +
                ", countComment=" + countComment +
                ", countLike=" + countLike +
                ", countShare=" + countShare +
                ", userLike=" + userLike +
                ", commentList=" + commentList +
                ", likeList=" + likeList +
                '}';
    }
}
