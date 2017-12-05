package com.joy.property.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by usb on 2017/6/26.
 */

public class PhotoCacheTo {
private String  path;
private String timePath;
private Date photoTime;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimePath() {
        return timePath;
    }

    public void setTimePath(String timePath) {
        this.timePath = timePath;
    }

    public Date getPhotoTime() {
        return photoTime;
    }

    @Override
    public String toString() {
        return "PhotoCacheTo{" +
                "path='" + path + '\'' +
                ", timePath='" + timePath + '\'' +
                ", photoTime=" + photoTime +
                '}';
    }

    public void setPhotoTime(Date photoTime) {
        this.photoTime = photoTime;
    }
}
