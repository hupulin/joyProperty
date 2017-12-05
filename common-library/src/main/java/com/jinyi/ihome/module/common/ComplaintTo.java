package com.jinyi.ihome.module.common;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ComplaintTo {
    private String good;
    private String medium;
    private String bad;
    private int image;
    private String name;


    public ComplaintTo(String good, String medium, String bad,int image,String name) {
        this.good = good;
        this.medium = medium;
        this.bad = bad;
        this.image=image;
        this.name=name;

    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ComplaintTo{" +
                "good=" + good +
                ", medium=" + medium +
                ", bad=" + bad +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
