package com.lawrene.falcon.aniekeme;

/**
 * Created by lawrene on 6/13/18.
 */

public class MyTasks {
    String title, time, date, description, type, willAlert, willNotify;

    public MyTasks(String title, String description, String type, String willAlert, String willNotify, String time, String date) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.willAlert = willAlert;
        this.willNotify = willNotify;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getWillAlert() {
        return willAlert;
    }

    public String getWillNotify() {
        return willNotify;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWillAlert(String willAlert) {
        this.willAlert = willAlert;
    }

    public void setWillNotify(String willNotify) {
        this.willNotify = willNotify;
    }
}
