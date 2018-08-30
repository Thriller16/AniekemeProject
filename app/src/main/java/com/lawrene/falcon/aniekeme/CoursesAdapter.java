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
 * Created by lawrene on 6/23/18.
 */

public class CoursesAdapter extends ArrayAdapter<Courses> {
    public CoursesAdapter(@NonNull Context context, @NonNull List<Courses> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.courses_list_item, parent, false);
        }

        Courses currentCourse = getItem(position);

        TextView titleText = (TextView)convertView.findViewById(R.id.titlee);
        TextView codeTExt = (TextView)convertView.findViewById(R.id.courseCode);
        TextView LecturerTExt = (TextView)convertView.findViewById(R.id.courseLecturer);
        TextView loadText = (TextView)convertView.findViewById(R.id.courseLoad);

        titleText.setText(currentCourse.getTitle());
        codeTExt.setText(currentCourse.getCode());
        loadText.setText(currentCourse.getCreditLoad());
        LecturerTExt.setText(currentCourse.getLecturer());

        return convertView;
    }
}
