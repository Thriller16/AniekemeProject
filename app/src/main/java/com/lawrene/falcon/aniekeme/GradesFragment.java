package com.lawrene.falcon.aniekeme;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GradesFragment extends Fragment {

    DatabaseAccess databaseAccess;
    List<Grades> gradesList = new ArrayList<>();
    List<Courses> courses = new ArrayList<>();
    List<String> coursesname = new ArrayList<>();
    GradesAdapter gradesAdapter;
    ListView gradesListView;
    View mView;
    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_grades, container, false);

        gradesListView = mView.findViewById(R.id.grades_list);

        databaseAccess = DatabaseAccess.getInstance(getContext());
        courses = databaseAccess.getCourses();
        gradesAdapter = new GradesAdapter(getContext(), gradesList);
        gradesListView.setAdapter(gradesAdapter);
        // Inflate the layout for this fragment

        for(int i = 0; i< courses.size(); i++){
            gradesList.add(new Grades(courses.get(i).getTitle(), courses.get(i).getCode(), courses.get(i).getGrade()));
//            Toast.makeText(getContext(), "" + courses.get(i).getGrade(), Toast.LENGTH_SHORT).show();
        }

        gradesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                startActivity(new Intent(getContext(), AddGrades.class).putExtra("course", courses.get(position).getCode()));
            }
        });

        return mView;
    }

    @Override
    public void onResume() {
        gradesListView = mView.findViewById(R.id.grades_list);
        gradesList = new ArrayList<>();
        courses = new ArrayList<>();
        courses = databaseAccess.getCourses();

        for(int i = 0; i< courses.size(); i++){
            gradesList.add(new Grades(courses.get(i).getTitle(), courses.get(i).getCode(), courses.get(i).getGrade()));
//            Toast.makeText(getContext(), "" + courses.get(i).getGrade(), Toast.LENGTH_SHORT).show();
        }
//        gradesList = databaseAccess.get();

        gradesAdapter = new GradesAdapter(getContext(), gradesList);
        gradesListView.setAdapter(gradesAdapter);
        gradesAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
