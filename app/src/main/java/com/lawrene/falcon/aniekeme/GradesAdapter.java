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

public class GradesAdapter extends ArrayAdapter<Grades> {
    public GradesAdapter(@NonNull Context context, @NonNull List<Grades> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grades_list_item, parent, false);
        }

        Grades grades = getItem(position);

        TextView titleText = convertView.findViewById(R.id.titleeGrades);
        TextView codeView = convertView.findViewById(R.id.courseCodegrades);
        TextView totalgradeText = convertView.findViewById(R.id.gradeGrades);

        titleText.setText(grades.getTitle());
        codeView.setText(grades.getCode());
        totalgradeText.setText(grades.getTotalGrade());

        return convertView;
    }
}
