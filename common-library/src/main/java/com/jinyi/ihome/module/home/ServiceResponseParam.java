/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceResponseParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String groupUserSid;
/*    */   private String serviceSid;

    @Override
    public String toString() {
        return "ServiceResponseParam{" +
                "groupUserSid='" + groupUserSid + '\'' +
                ", serviceSid='" + serviceSid + '\'' +
                ", userSid='" + userSid + '\'' +
                ", assignType=" + assignType +
                ", assignDesc='" + assignDesc + '\'' +
                ", careUserSid='" + careUserSid + '\'' +
                '}';
    }

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    /*    */   private String userSid;
/*    */   private int assignType;
/*    */   private String assignDesc;
    private String careUserSid;
    public String getCareUserSid() {
        return careUserSid;
    }

    public void setCareUserSid(String careUserSid) {
        this.careUserSid = careUserSid;
    }

    /*    */

    /*    */   public String getGroupUserSid()
/*    */   {
/* 18 */     return this.groupUserSid;
/*    */   }
/*    */ 
/*    */   public String getServiceSid() {
/* 22 */     return this.serviceSid;
/*    */   }
/*    */ 
/*    */   public int getAssignType()
/*    */   {
/* 27 */     return this.assignType;
/*    */   }
/*    */ 
/*    */   public String getAssignDesc()
/*    */   {
/* 32 */     return this.assignDesc;
/*    */   }
/*    */ 
/*    */   public void setGroupUserSid(String groupUserSid)
/*    */   {
/* 10 */     this.groupUserSid = groupUserSid; } 
/* 10 */   public void setServiceSid(String serviceSid) { this.serviceSid = serviceSid; } 
/* 10 */   public void setAssignType(int assignType) { this.assignType = assignType; } 
/* 10 */   public void setAssignDesc(String assignDesc) { this.assignDesc = assignDesc; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ServiceResponseParam)) return false; ServiceResponseParam other = (ServiceResponseParam)o; if (!other.canEqual(this)) return false; Object this$groupUserSid = getGroupUserSid(); Object other$groupUserSid = other.getGroupUserSid(); if (this$groupUserSid == null ? other$groupUserSid != null : !this$groupUserSid.equals(other$groupUserSid)) return false; Object this$serviceSid = getServiceSid(); Object other$serviceSid = other.getServiceSid(); if (this$serviceSid == null ? other$serviceSid != null : !this$serviceSid.equals(other$serviceSid)) return false; if (getAssignType() != other.getAssignType()) return false; Object this$assignDesc = getAssignDesc(); Object other$assignDesc = other.getAssignDesc(); return this$assignDesc == null ? other$assignDesc == null : this$assignDesc.equals(other$assignDesc); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ServiceResponseParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $groupUserSid = getGroupUserSid(); result = result * 59 + ($groupUserSid == null ? 0 : $groupUserSid.hashCode()); Object $serviceSid = getServiceSid(); result = result * 59 + ($serviceSid == null ? 0 : $serviceSid.hashCode()); result = result * 59 + getAssignType(); Object $assignDesc = getAssignDesc(); result = result * 59 + ($assignDesc == null ? 0 : $assignDesc.hashCode()); return result; } 
/*    */
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceResponseParam
 * JD-Core Version:    0.6.2
 */