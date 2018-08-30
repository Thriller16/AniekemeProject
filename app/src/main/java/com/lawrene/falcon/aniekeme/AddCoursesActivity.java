package com.lawrene.falcon.aniekeme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddCoursesActivity extends AppCompatActivity {

    SeekBar loadseekbar;
    LinearLayout linearLayout;
    SeekBar daysSeekbar;
    TextView loadtextView, selectedDay1, selectedDay2;
    TextView daysTextView;
    Spinner spinner1, spinner2;

    Toolbar mToolbar;
    EditText titleText, codeText, lecturerText, day1time1, day1time2, day2time1, day2time2;
    DatabaseAccess databaseAccess;
    Button addCourseBtn;

    String day1, day2, time1, time2;

    List<String> spinnerFeed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);


        loadseekbar = findViewById(R.id.seekBar);
        daysSeekbar = findViewById(R.id.number_of_days);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        linearLayout = findViewById(R.id.layout_2);
        selectedDay1 = findViewById(R.id.selected_day1);
        selectedDay2 = findViewById(R.id.selected_day2);
        loadtextView = findViewById(R.id.progressNumber);
        daysTextView = findViewById(R.id.daysProgressNumber);
        mToolbar = findViewById(R.id.add_courses_toolbar);
        addCourseBtn = findViewById(R.id.add_course_btn);

        day1time1 = findViewById(R.id.day1time1);
        day1time2 = findViewById(R.id.day1time2);
        day2time1 = findViewById(R.id.day2time1);
        day2time2 = findViewById(R.id.day2time2);

        titleText = findViewById(R.id.add_course_title);
        codeText = findViewById(R.id.add_course_code);
        lecturerText = findViewById(R.id.add_lecturer_name);

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();


        day1 = "";
        day2 = "";
        time1 = "";
        time2 = "";
//        Setting up the toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Courses");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spinnerFeed.add("Monday");
        spinnerFeed.add("Tuesday");
        spinnerFeed.add("Wednesday");
        spinnerFeed.add("Thursday");
        spinnerFeed.add("Friday");

        //---------------------Setting up the arrayadapter to use for the spinners------------------
        ArrayAdapter courseAdapter = new ArrayAdapter(this, R.layout.spinner_look, spinnerFeed);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(courseAdapter);
        spinner2.setAdapter(courseAdapter);


        loadseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                loadtextView.setText("" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        daysSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                daysTextView.setText("" + i);

                if(daysSeekbar.getProgress() == 2){
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else {
                    linearLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day1 = spinnerFeed.get(i);
                selectedDay1.setText(day1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day2 = spinnerFeed.get(i);
                selectedDay2.setText(day2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                time1 = day1time1.getText().toString() + " - " + day1time2.getText().toString();
                time2 = day2time1.getText().toString() + " - " + day2time2.getText().toString();

                databaseAccess.addCourses(titleText.getText().toString(), codeText.getText().toString(), lecturerText.getText().toString(), String.valueOf(loadseekbar.getProgress()), day1, day2, time1, time2);
                Toast.makeText(AddCoursesActivity.this, titleText.getText().toString() + " has been added to your courses", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
