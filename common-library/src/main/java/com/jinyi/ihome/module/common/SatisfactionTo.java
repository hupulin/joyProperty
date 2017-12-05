package com.jinyi.ihome.module.common;

import lombok.Data;

/**
 * Created by xz on 2017/3/31.
 */
@Data
public class SatisfactionTo {
    public SatisfactionTo(float goodComment, float middleComment, float badComment) {
        this.goodComment = goodComment;
        this.middleComment = middleComment;
        this.badComment = badComment;
    }

    private  float goodComment;
    private  float middleComment;
    private  float badComment;
}
