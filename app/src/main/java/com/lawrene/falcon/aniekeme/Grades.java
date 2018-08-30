package com.lawrene.falcon.aniekeme;

public class Grades {
    String title, code, totalGrade;

    public Grades(String title, String code, String totalGrade) {
        this.title = title;
        this.code = code;
        this.totalGrade = totalGrade;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getTotalGrade() {
        return totalGrade;
    }
}
