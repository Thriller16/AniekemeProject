package com.lawrene.falcon.aniekeme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddHomeWork extends AppCompatActivity {

    Spinner courseSpinner;
    List<Courses> courses;
    DatabaseAccess databaseAccess;
    List<String> coursesList;
    Toolbar mToolbar;
    EditText homeWkDesc, homeWrkTime, homeWrkDay;
    String mCourseCode = "";
    Button addHomeWork;
    String currentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home_work);

        mToolbar = findViewById(R.id.add_hw);
//        //        Setting up the toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Homework");
        homeWkDesc = findViewById(R.id.add_hw_desc);
        homeWrkTime = findViewById(R.id.add_hw_time);
        homeWrkDay = findViewById(R.id.add_hw_day);
        addHomeWork = findViewById(R.id.add_hwk_btn);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        courseSpinner = findViewById(R.id.spinner_course);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();


        coursesList = new ArrayList<>();
        courses = new ArrayList<>();
        courses = databaseAccess.getCourses();


        for(int i = 0; i < courses.size(); i++){
            coursesList.add(courses.get(i).getCode().toString());
        }

        //---------------------Setting up the arrayadapter to use for the spinners------------------
        ArrayAdapter courseAdapter = new ArrayAdapter(this,R.layout.spinner_look, coursesList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);


        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                mCourseCode = coursesList.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addHomeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseAccess.addHomeWork(mCourseCode, homeWkDesc.getText().toString(), homeWrkTime.getText().toString(), homeWrkDay.getText().toString());
                Toast.makeText(AddHomeWork.this, "Home work has been added to homescreen", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        homeWrkDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                currentText = homeWrkDay.getText().toString();

                if(currentText.length() == 2){
                    homeWrkDay.setText(currentText + "/");
                }
                else if(currentText.length() == 5){
                    homeWrkDay.setText(currentText + "/");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//
//        homeWrkTime.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

    }
}
