package com.lawrene.falcon.aniekeme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddExams extends AppCompatActivity {


    Spinner examCourseSpinner;
    List<Exams> examsList;
    List<Courses> courses;
    List<String> coursesList;
    DatabaseAccess databaseAccess;
    Toolbar mToolbar;
    EditText examtitle, examday, examTime;
    String mCourseCode = "";
    Button addExamBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exams);

        mToolbar = findViewById(R.id.add_exams);
//        //        Setting up the toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Exams");



        examtitle = findViewById(R.id.add_exams_title);
        examday = findViewById(R.id.add_exams_day);
        examTime = findViewById(R.id.add_exams_time);
        examCourseSpinner = findViewById(R.id.spinner_exams);
        addExamBtn = findViewById(R.id.add_exam_btn);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        examsList = new ArrayList<>();
        examsList = databaseAccess.getExams();

        coursesList = new ArrayList<>();
        courses = new ArrayList<>();
        courses = databaseAccess.getCourses();

        for(int i = 0; i < courses.size(); i++){
            coursesList.add(courses.get(i).getCode().toString());
        }

        //---------------------Setting up the arrayadapter to use for the spinners------------------
        ArrayAdapter courseAdapter = new ArrayAdapter(this,R.layout.spinner_look, coursesList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        examCourseSpinner.setAdapter(courseAdapter);


        examCourseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                mCourseCode = coursesList.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addExamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseAccess.addExams(examtitle.getText().toString(), examday.getText().toString(), examTime.getText().toString(), mCourseCode);
                Toast.makeText(AddExams.this, "Exam has been added to homescreen", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
