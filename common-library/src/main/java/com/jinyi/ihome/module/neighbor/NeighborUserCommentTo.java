/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */ public class NeighborUserCommentTo extends NeighborCommentTo
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private NeighborPostTo postTo;
/*    */ 
/*    */   public NeighborPostTo getPostTo()
/*    */   {
/* 17 */     return this.postTo;
/*    */   }
/*    */ 
/*    */   public void setPostTo(NeighborPostTo postTo)
/*    */   {
/*  9 */     this.postTo = postTo; } 
/*  9 */   public String toString() { return "NeighborUserCommentTo(postTo=" + getPostTo() + ")"; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NeighborUserCommentTo)) return false; NeighborUserCommentTo other = (NeighborUserCommentTo)o; if (!other.canEqual(this)) return false; Object this$postTo = getPostTo(); Object other$postTo = other.getPostTo(); return this$postTo == null ? other$postTo == null : this$postTo.equals(other$postTo); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof NeighborUserCommentTo; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $postTo = getPostTo(); result = result * 59 + ($postTo == null ? 0 : $postTo.hashCode()); return result;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborUserCommentTo
 * JD-Core Version:    0.6.2
 */