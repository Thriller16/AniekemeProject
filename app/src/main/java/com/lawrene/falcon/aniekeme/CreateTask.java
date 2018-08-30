package com.lawrene.falcon.aniekeme;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CreateTask extends AppCompatActivity {



    String [] alltypes = {"Urgent", "Normal"};
    Spinner typeSpinner;
    EditText titleEdt, descEdt;
    Toolbar mToolbar;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    DatabaseAccess databaseAccess;
    Button setTimeBtn, setDateBtn;
    String mtaskType;

    String mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        mToolbar = (Toolbar) findViewById(R.id.create_bar);
        setTimeBtn = (Button)findViewById(R.id.setTime);
        setDateBtn = (Button)findViewById(R.id.setDate);
        typeSpinner = (Spinner)findViewById(R.id.type_spinner);


        //---------------------Setting up the arrayadapter to use for the spinners------------------
        ArrayAdapter typeAdapter = new ArrayAdapter(this,R.layout.spinner_look, alltypes);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);



        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mtaskType = alltypes[i].toString();
                Toast.makeText(CreateTask.this, "" + mtaskType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



//        Setting up the toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add New Task");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        long currentDate = System.currentTimeMillis();
        String displayDate = new SimpleDateFormat().format(currentDate);
        StringTokenizer stringTokenizer = new StringTokenizer(displayDate, "/");


        final String month = stringTokenizer.nextToken();
        final String day = stringTokenizer.nextToken();

        setTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(CreateTask.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
//                        Toast.makeText(CreateTask.this, "" + hour + ":" + minutes, Toast.LENGTH_SHORT).show();
                        mHour = ""+hour;
                        mMinute = ""+minutes;
                    }

                }, 24, 0, false);


                timePickerDialog.show();
            }
        });


        setDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(CreateTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mYear = ""+year;
                        mMonth = ""+(month+1);
                        mDay = ""+day;
//                        Toast.makeText(CreateTask.this, "year" + year + " month " + (month+1) + " day" + day, Toast.LENGTH_SHORT).show();
                    }
                }, 2018, (Integer.parseInt(month)-1), Integer.parseInt(day));

                datePickerDialog.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_task_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_task) {
            storeNewTask();
        }
        return super.onOptionsItemSelected(item);
    }

    private void storeNewTask() {
        titleEdt = (EditText)findViewById(R.id.task_title);
        descEdt = (EditText)findViewById(R.id.task_description);

        String title = titleEdt.getText().toString();
        String description = descEdt.getText().toString();


        long currentDate = System.currentTimeMillis();
        String displayDate = new SimpleDateFormat().format(currentDate);
        StringTokenizer stringTokenizer = new StringTokenizer(displayDate, "/");


        final String month = stringTokenizer.nextToken();
        final String day = stringTokenizer.nextToken();



        if(title.equals("")){
            Toast.makeText(this, "Title field cannot left empty", Toast.LENGTH_SHORT).show();
        }

        else{
            if(description.equals("")){
                description = "No description";
            }
            if(mHour == null){
                mHour = "00";
            }
            if(mMinute == null){
                mMinute = "00";
            }if(mYear == null){
                mYear = "2018";
            }if(mMonth == null){
                mMonth = month;
            }if(mDay == null){
                mDay = day;
            }

            databaseAccess.add(title, description, mtaskType, "sjrthrt", "sjrthrt", mHour+":"+mMinute, mDay+"/"+mMonth+"/"+mYear);

            Toast.makeText(this, "Task Added Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
