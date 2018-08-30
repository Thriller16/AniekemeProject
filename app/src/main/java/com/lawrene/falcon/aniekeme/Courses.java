package com.lawrene.falcon.aniekeme;

/**
 * Created by lawrene on 6/23/18.
 */

public class Courses {
    String code, title, lecturer, creditLoad, grade, day1, day2, time1, time2;

    public String getCode() {
        return code;
    }

    public String getGrade() {
        return grade;
    }

    public String getDay1() {
        return day1;
    }

    public String getDay2() {
        return day2;
    }

    public String getTitle() {
        return title;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getTime1() {
        return time1;
    }

    public String getTime2() {
        return time2;
    }

    public String getCreditLoad() {
        return creditLoad;
    }

    public Courses(String code, String title, String lecturer, String creditLoad, String grade, String day1, String day2, String time1, String time2) {
        this.grade = grade;
        this.day1 = day1;
        this.time1 = time1;
        this.time2 = time2;
        this.day2 = day2;
        this.code = code;
        this.title = title;
        this.lecturer = lecturer;
        this.creditLoad = creditLoad;
    }
}
