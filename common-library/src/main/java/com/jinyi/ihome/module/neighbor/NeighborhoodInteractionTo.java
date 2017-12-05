package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xz on 2016/11/7.
 */
public class NeighborhoodInteractionTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String interactionSid;

    /**
     * 小区Id
     */
    private String apartmentSid;

    /**
     * 调查、活动或者话题的id
     */
    private String refId;

    /**
     * 互动类型（0，话题；1，活动；2，调查)
     */
    private int interactionType;


    private String authorId;

    /**
     * 活动标题
     */
    private String activityTitle;



    /**
     * 摘要
     */
    private String activitySumary;

    /**
     * 内容
     */
    private String activityContent;

    /**
     * 创建日期
     */
    private Date createdOn;

    /**
     * 活动截止日期
     */
    private Date activityDeadline;

    /**
     * 活动权重
     */
    private int score;
    private int paticountlimit;

    /**
     * 总共参与总人数
     */

    private int patitimelimit;
    /**
     * 活动状态(1:草稿 2:发布 3:屏蔽 4 过期)
     */
    private int status;

    /**
     * （1，活动；2，话题；3，调查）
     */
    private String activityType;

    /**
     * 是否显示参与数(0:显示,1:不显示)
     */
    private int showpaticipants;

    /**
     * 是否显示浏览数(0:显示,1:不显示)
     */
    private int showviews;

    /**
     * 是否显示按钮(0:显示,1:不显示)
     */


    //用户名字

    private String ownerName;

    //小区

    private String apartmentName;
    //房号
    private String roomNo;

    //是否显示参与按钮

    private int Showbtns;

    //图片信息

    private NeighborhoodMediaTo neighborhoodMediaTo;

    //用户图像
    private boolean outOfDate;
    private List<OwnerMessageTo> ownerMessageTos;
private List<NeighborPostTo>neighborPostToList;
    //返回状态
    private int flag;
    private int canyuUserCount;
    private int viewCount;
    private int commentCount;

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCanyuUserCount() {
        return canyuUserCount;
    }

    public void setCanyuUserCount(int canyuUserCount) {
        this.canyuUserCount = canyuUserCount;
    }

    public String getInteractionSid() {
        return interactionSid;
    }

    public void setInteractionSid(String interactionSid) {
        this.interactionSid = interactionSid;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public int getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(int interactionType) {
        this.interactionType = interactionType;
    }

    public int getShowbtns() {
        return Showbtns;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivitySumary() {
        return activitySumary;
    }

    public void setActivitySumary(String activitySumary) {
        this.activitySumary = activitySumary;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getActivityDeadline() {
        return activityDeadline;
    }

    public void setActivityDeadline(Date activityDeadline) {
        this.activityDeadline = activityDeadline;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getShowpaticipants() {
        return showpaticipants;
    }

    public void setShowpaticipants(int showpaticipants) {
        this.showpaticipants = showpaticipants;
    }

    public int getShowviews() {
        return showviews;
    }

    public void setShowviews(int showviews) {
        this.showviews = showviews;
    }

    public boolean isOutOfDate() {
        return outOfDate;
    }

    public void setOutOfDate(boolean outOfDate) {
        this.outOfDate = outOfDate;
    }

    public void setShowbtns(int showbtns) {
        Showbtns = showbtns;
    }

    public NeighborhoodMediaTo getNeighborhoodMediaTo() {
        return neighborhoodMediaTo;
    }

    public void setNeighborhoodMediaTo(NeighborhoodMediaTo neighborhoodMediaTo) {
        this.neighborhoodMediaTo = neighborhoodMediaTo;
    }

    public List<OwnerMessageTo> getOwnerMessageTos() {
        return ownerMessageTos;
    }

    public void setOwnerMessageTos(List<OwnerMessageTo> ownerMessageTos) {
        this.ownerMessageTos = ownerMessageTos;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public List<NeighborPostTo> getNeighborPostToList() {
        return neighborPostToList;
    }

    public void setNeighborPostToList(List<NeighborPostTo> neighborPostToList) {
        this.neighborPostToList = neighborPostToList;
    }

    public int getPaticountlimit() {
        return paticountlimit;
    }

    public void setPaticountlimit(int paticountlimit) {
        this.paticountlimit = paticountlimit;
    }

    public int getPatitimelimit() {
        return patitimelimit;
    }

    public void setPatitimelimit(int patitimelimit) {
        this.patitimelimit = patitimelimit;
    }

    @Override
    public String toString() {
        return "NeighborhoodInteractionTo{" +
                "interactionSid='" + interactionSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", refId='" + refId + '\'' +
                ", interactionType=" + interactionType +
                ", authorId='" + authorId + '\'' +
                ", activityTitle='" + activityTitle + '\'' +
                ", activitySumary='" + activitySumary + '\'' +
                ", activityContent='" + activityContent + '\'' +
                ", createdOn=" + createdOn +
                ", activityDeadline=" + activityDeadline +
                ", score=" + score +
                ", status=" + status +
                ", activityType='" + activityType + '\'' +
                ", showpaticipants=" + showpaticipants +
                ", showviews=" + showviews +
                ", ownerName='" + ownerName + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", Showbtns=" + Showbtns +
                ", neighborhoodMediaTo=" + neighborhoodMediaTo +
                ", outOfDate=" + outOfDate +
                ", ownerMessageTos=" + ownerMessageTos +
                ", neighborPostToList=" + neighborPostToList +
                ", flag=" + flag +
                ", canyuUserCount=" + canyuUserCount +
                ", viewCount=" + viewCount +
                ", commentCount=" + commentCount +
                '}';
    }
}

