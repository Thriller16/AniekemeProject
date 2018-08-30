package com.lawrene.falcon.aniekeme;

public class HomeWork {
    String code, description, time, day;

    public HomeWork(String code, String description, String time, String day) {
        this.code = code;
        this.description = description;
        this.time = time;
        this.day = day;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }
}
