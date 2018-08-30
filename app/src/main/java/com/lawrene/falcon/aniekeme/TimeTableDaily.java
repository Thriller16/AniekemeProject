package com.lawrene.falcon.aniekeme;

/**
 * Created by lawrene on 7/10/18.
 */

public class TimeTableDaily {
    String name, title, lecturer, time;

    public TimeTableDaily(String name, String title, String lecturer, String time) {
        this.name = name;
        this.title = title;
        this.lecturer = lecturer;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getTime() {
        return time;
    }
}
