package com.lawrene.falcon.aniekeme;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    Toolbar mToolbar;
    RelativeLayout relativeLayout;
    FloatingActionButton editFab;
    EditText editText;
    DatabaseAccess databaseAccess;
    List<Notes> notesList = new ArrayList<>();
    ProgressDialog mProgressDialog;
    String date, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        date = getIntent().getStringExtra("date");
        note = getIntent().getStringExtra("note");

        //        This is where the views will be setup
        relativeLayout = findViewById(R.id.home_layout);
        editFab = findViewById(R.id.edit_fab);
        editText = findViewById(R.id.edit_text);
        mProgressDialog = new ProgressDialog(this);

        editText.setEnabled(false);

        mToolbar = (Toolbar) findViewById(R.id.add_bar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Edit Note");


        if (note!=null) {
            editText.setText(note);
        }
        else{
            editText.setText("");
        }

        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText.isEnabled()) {
                    changeFocusToLayout();

                    if(note == null){
                        databaseAccess.addNote("22/7/2018", editText.getText().toString(), "ELE524");
//                        Toast.makeText(AddNoteActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        databaseAccess.updateNotes(editText.getText().toString(), date);
                        Toast.makeText(AddNoteActivity.this, "Done editing", Toast.LENGTH_SHORT).show();
                    }

                }

                else {
                    changefocusToEdt();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void changefocusToEdt() {
        relativeLayout.setFocusable(false);
        relativeLayout.setFocusableInTouchMode(false);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editFab.setImageResource(R.drawable.ic_done_black_24dp);
        Toast.makeText(this, "Edit Mode is On", Toast.LENGTH_SHORT).show();

        if (note == null) {
            editText.setText("");
        }

        else {
            editText.setText(editText.getText().toString());
        }

        editText.setEnabled(true);
    }


    public void changeFocusToLayout() {

        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        relativeLayout.setFocusable(true);
        editFab.setImageResource(R.drawable.ic_edit_black_24dp);
        relativeLayout.setFocusableInTouchMode(true);
        editText.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {

            if (editText.getText().toString().equals("")) {
                Toast.makeText(this, "Notes have been added", Toast.LENGTH_SHORT).show();
            } else {
                databaseAccess.deleteNote(note);
                Toast.makeText(AddNoteActivity.this, "Note has been deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public String convertToMonth(int number) {
        String monthName = "";
        switch (number) {
            case 1:
                monthName = "January";
                break;

            case 2:
                monthName = "February";
                break;

            case 3:
                monthName = "March";
                break;

            case 4:
                monthName = "April";
                break;

            case 5:
                monthName = "May";
                break;

            case 6:
                monthName = "June";
                break;

            case 7:
                monthName = "July";
                break;

            case 8:
                monthName = "August";
                break;

            case 9:
                monthName = "September";
                break;

            case 10:
                monthName = "October";
                break;

            case 11:
                monthName = "November";
                break;

            case 12:
                monthName = "December";
                break;
        }

        return monthName;
    }

    public String addSuffix(String day) {
        String suffix = "";

        if (day.equals("1") || day.equals("21") || day.equals("31")) {
            suffix = "st";
        } else if (day.equals("2") || day.equals("22")) {
            suffix = "nd";
        } else if (day.equals("3") || day.equals("23")) {
            suffix = "rd";
        } else {
            suffix = "th";
        }
        return suffix;
    }

    //    This handles what happens when the back button is pressed
    @Override
    public void onBackPressed() {
        if (editText.isEnabled()) {
            editText.setEnabled(false);
            editFab.setImageResource(R.drawable.ic_edit_black_24dp);
        } else {
            finish();
        }
    }

}

