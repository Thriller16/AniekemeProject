package com.lawrene.falcon.aniekeme;

/**
 * Created by lawrene on 7/1/18.
 */

public class Notes {
    String date, note, courseTitle, courseCode;

    public Notes(String date, String note, String courseTitle, String courseCode) {
        this.date = date;
        this.note = note;
        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
