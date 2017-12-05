package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2017/1/5.
 */
public class CategoryTo implements Serializable{

    /**
     * id : 14214114562343936
     * name : 3333
     */

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryTo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
