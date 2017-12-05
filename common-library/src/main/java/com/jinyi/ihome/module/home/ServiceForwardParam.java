/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceForwardParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String serviceSid;
/*    */   private String assistTo;
/*    */   private String workSid;
/*    */   private String assistDesc;
/*    */   private String userSid;

    @Override
    public String toString() {
        return "ServiceForwardParam{" +
                "serviceSid='" + serviceSid + '\'' +
                ", assistTo='" + assistTo + '\'' +
                ", workSid='" + workSid + '\'' +
                ", assistDesc='" + assistDesc + '\'' +
                ", userSid='" + userSid + '\'' +
                ", expectFinishTime='" + expectFinishTime + '\'' +
                '}';
    }

    public String getWorkSid() {
        return workSid;
    }

    public void setWorkSid(String workSid) {
        this.workSid = workSid;
    }

    public String getExpectFinishTime() {
        return expectFinishTime;
    }

    public void setExpectFinishTime(String expectFinishTime) {
        this.expectFinishTime = expectFinishTime;
    }

    /*    */   private String expectFinishTime;
/*    */
/*    */   public String getServiceSid()
/*    */   {
/* 15 */     return this.serviceSid;
/*    */   }
/* 17 */   public String getAssistTo() { return this.assistTo; } 
/*    */   public String getAssistDesc() {
/* 19 */     return this.assistDesc;
/*    */   }
/* 21 */   public String getUserSid() { return this.userSid; }
/*    */ 
/*    */ 
/*    */   public void setServiceSid(String serviceSid)
/*    */   {
/* 11 */     this.serviceSid = serviceSid; } 
/* 11 */   public void setAssistTo(String assistTo) { this.assistTo = assistTo; } 
/* 11 */   public void setAssistDesc(String assistDesc) { this.assistDesc = assistDesc; } 
/* 11 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ServiceForwardParam)) return false; ServiceForwardParam other = (ServiceForwardParam)o; if (!other.canEqual(this)) return false; Object this$serviceSid = getServiceSid(); Object other$serviceSid = other.getServiceSid(); if (this$serviceSid == null ? other$serviceSid != null : !this$serviceSid.equals(other$serviceSid)) return false; Object this$assistTo = getAssistTo(); Object other$assistTo = other.getAssistTo(); if (this$assistTo == null ? other$assistTo != null : !this$assistTo.equals(other$assistTo)) return false; Object this$assistDesc = getAssistDesc(); Object other$assistDesc = other.getAssistDesc(); if (this$assistDesc == null ? other$assistDesc != null : !this$assistDesc.equals(other$assistDesc)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); return this$userSid == null ? other$userSid == null : this$userSid.equals(other$userSid); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof ServiceForwardParam; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $serviceSid = getServiceSid(); result = result * 59 + ($serviceSid == null ? 0 : $serviceSid.hashCode()); Object $assistTo = getAssistTo(); result = result * 59 + ($assistTo == null ? 0 : $assistTo.hashCode()); Object $assistDesc = getAssistDesc(); result = result * 59 + ($assistDesc == null ? 0 : $assistDesc.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); return result; } 
/*    */

    /*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceForwardParam
 * JD-Core Version:    0.6.2
 */