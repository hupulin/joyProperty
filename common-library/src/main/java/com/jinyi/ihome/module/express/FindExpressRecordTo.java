package com.jinyi.ihome.module.express;

import java.util.List;

/**
 * Created by usb on 2017/8/3.
 */

public class FindExpressRecordTo {
    /**
     * nextPage : 2
     * limit : 20
     * expressStatusStr : 2
     * apartmentSid : a241b63a-df3c-43a8-a7fd-477e5f950d9f
     * handleUserSid : null
     * search : null
     * total : 110
     */

   private List<ExpressNewTo> list;

    public List<ExpressNewTo> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "FindExpressRecordTo{" +
                "list=" + list +
                '}';
    }

    public void setList(List<ExpressNewTo> list) {
        this.list = list;
    }
}
