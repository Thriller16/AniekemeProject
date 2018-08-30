package com.lawrene.falcon.aniekeme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lawrene on 6/13/18.
 */

public class TasksAdapter extends ArrayAdapter<MyTasks> {

    public TasksAdapter(@NonNull Context context, @NonNull List<MyTasks> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_list_item, parent, false);
        }

        MyTasks myTasks = getItem(position);

        TextView titleText = (TextView)convertView.findViewById(R.id.taskTITLE);
        TextView descText = (TextView)convertView.findViewById(R.id.taskDESCRIPTION);
        TextView typetext = (TextView)convertView.findViewById(R.id.taskType);
        TextView timeText = (TextView)convertView.findViewById(R.id.taskTime);
        TextView dateText = (TextView)convertView.findViewById(R.id.taskDate);



        titleText.setText(myTasks.getTitle());
        typetext.setText(myTasks.getType());
        timeText.setText(myTasks.getTime());
        dateText.setText(myTasks.getDate());
//        descText.setText(myTasks.getDescription());


        if(myTasks.getType().equals("Urgent")){
            typetext.setTextColor(convertView.getResources().getColor(R.color.red));
        }

        else if (myTasks.getType().equals("Normal")) {
            typetext.setTextColor(convertView.getResources().getColor(R.color.blue));
        }


        if(myTasks.getDescription().length()> 18){
            descText.setText(myTasks.getDescription().substring(0, 15));
        }else {
            descText.setText(myTasks.getDescription());
        }



        return convertView;

    }
}
