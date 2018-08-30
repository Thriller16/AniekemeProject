package com.lawrene.falcon.aniekeme;

public class Exams {
    String title, day, time, code;

    public Exams(String title, String day, String time, String code) {
        this.title = title;
        this.day = day;
        this.time = time;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getCode() {
        return code;
    }
}
