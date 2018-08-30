package com.lawrene.falcon.aniekeme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddGrades extends AppCompatActivity {

    Toolbar mToolbar;
    EditText testEdittext, examEdittext;
    Button addItButton;
    DatabaseAccess databaseAccess;
    List<Courses> coursesList = new ArrayList<>();
    List<Grades> gradesList = new ArrayList<>();
    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grades);

        mToolbar = findViewById(R.id.add_grades_bar);
//        //        Setting up the toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Grades");

        databaseAccess = DatabaseAccess.getInstance(this);
        testEdittext = findViewById(R.id.test_score);
        title = getIntent().getStringExtra("course");

        Toast.makeText(this, "Click on a course to add grade", Toast.LENGTH_SHORT).show();
        examEdittext = findViewById(R.id.exam_score);
        addItButton = findViewById(R.id.add_grades);

        testEdittext.setText("");
        examEdittext.setText("");

        addItButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddGrades.this, "Grade has been added to " + title, Toast.LENGTH_SHORT).show();
                addGrades(title, getGrade(testEdittext.getText().toString(), examEdittext.getText().toString()));
            }
        });
    }


    private String getGrade(String test, String exam) {
        String finalGrade = "";

        if((!testEdittext.getText().toString().equals("")) && (!examEdittext.getText().toString().equals(""))){

            if((Integer.parseInt(test) + Integer.parseInt(exam)) <= 45){
                finalGrade = "F";
            }
            else if(46 <= (Integer.parseInt(test) + Integer.parseInt(exam)) && (Integer.parseInt(test) + Integer.parseInt(exam)) <= 49 ){
                finalGrade = "D";
            }
            else if(50 <= (Integer.parseInt(test) + Integer.parseInt(exam)) && (Integer.parseInt(test) + Integer.parseInt(exam)) <= 59 ){
                finalGrade = "C";
            }
            else if(60 <= (Integer.parseInt(test) + Integer.parseInt(exam)) && (Integer.parseInt(test) + Integer.parseInt(exam)) <= 69 ){
                finalGrade = "B";
            }
            else if(70 <= (Integer.parseInt(test) + Integer.parseInt(exam)) && (Integer.parseInt(test) + Integer.parseInt(exam)) <= 100 ){
                finalGrade = "A";
            }


            finish();
        }

        else {
            Toast.makeText(this, "Please fill in the grades", Toast.LENGTH_SHORT).show();
        }

        return finalGrade;
    }

    public void addGrades(String code, String grade){
        databaseAccess.updateGrades(grade, code);
    }
}
