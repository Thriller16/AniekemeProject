package com.lawrene.falcon.aniekeme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lawrene on 7/10/18.
 */


public class TimeTableDailyAdapter extends ArrayAdapter<TimeTableDaily> {


    List<MyTasks> tasksList = new ArrayList<>();
    DatabaseAccess databaseAccess;

    public TimeTableDailyAdapter(@NonNull Context context, @NonNull List<TimeTableDaily> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.time_table, parent, false);
        }

        databaseAccess = DatabaseAccess.getInstance(getContext());
        tasksList = databaseAccess.getTasks();

//        This is the layout inflater
        TimeTableDaily timeTableDaily = getItem(position);

        TextView courseTitle = convertView.findViewById(R.id.time_table_title);
        TextView courseTime = convertView.findViewById(R.id.time_table_time);
        TextView courseName = convertView.findViewById(R.id.time_table_name);


        courseTitle.setText(timeTableDaily.getTitle());
        courseTime.setText(timeTableDaily.getTime());
        courseName.setText(timeTableDaily.getName());

        for(int counter = 0; counter < tasksList.size(); counter++){

            if(timeTableDaily.getTitle().toString().toLowerCase().equals(tasksList.get(position).getTitle())){
                Toast.makeText(getContext(), "Task is suppose to be added", Toast.LENGTH_SHORT).show();
            }

        }

        return convertView;
    }

}
