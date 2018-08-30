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

public class HomeWorkAdapter extends ArrayAdapter<HomeWork> {
    public HomeWorkAdapter(@NonNull Context context, @NonNull List<HomeWork> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.task_list_item, parent, false);
        }

        TextView codeText = (TextView)convertView.findViewById(R.id.taskTITLE);
        TextView descText = (TextView)convertView.findViewById(R.id.taskDESCRIPTION);
//        TextView typetext = (TextView)convertView.findViewById(R.id.taskType);
        TextView timeText = (TextView)convertView.findViewById(R.id.taskTime);
        TextView dateText = (TextView)convertView.findViewById(R.id.taskDate);

        HomeWork homeWork = getItem(position);

        codeText.setText(homeWork.getCode());
        descText.setText(homeWork.getDescription());
        timeText.setText(homeWork.getTime());
        dateText.setText(homeWork.getDay());


        return convertView;
    }
}
