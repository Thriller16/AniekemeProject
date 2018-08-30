package com.lawrene.falcon.aniekeme;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoursesFragment extends Fragment {


    View mView;
    List<Courses> coursesList;
    ListView listView;
    CoursesAdapter coursesAdapter;
    DatabaseAccess databaseAccess;
    FloatingActionButton fab;

    public CoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_courses, container, false);
        listView = mView.findViewById(R.id.coursesListView);
        fab = mView.findViewById(R.id.add_courses_btn);
        databaseAccess = DatabaseAccess.getInstance(getContext());

        coursesList = new ArrayList();
        coursesList = databaseAccess.getCourses();

        coursesAdapter = new CoursesAdapter(getContext(), coursesList);

        listView.setAdapter(coursesAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddCoursesActivity.class));
            }
        });

        return mView;
    }

    @Override
    public void onResume() {
        listView = mView.findViewById(R.id.coursesListView);
        coursesList = databaseAccess.getCourses();

        coursesAdapter = new CoursesAdapter(getContext(), coursesList);
        listView.setAdapter(coursesAdapter);
        coursesAdapter.notifyDataSetChanged();
        super.onResume();
    }


}
