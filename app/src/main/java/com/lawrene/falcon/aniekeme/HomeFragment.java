package com.lawrene.falcon.aniekeme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HomeFragment extends Fragment {


    View mView;
    TextView datetext;
    List<HomeWork> homeWorkList = new ArrayList<>();
    List<Exams> examsList = new ArrayList<>();
    ListView undoneTaskListView;
    DatabaseAccess databaseAccess;
    HomeWorkAdapter homeWorkAdapter;
    TextView textView;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();


        mView = inflater.inflate(R.layout.fragment_home, container, false);
        datetext = (TextView) mView.findViewById(R.id.date_text);
        textView = mView.findViewById(R.id.empty);
        homeWorkList = databaseAccess.getHomeWork();
        examsList = databaseAccess.getExams();

        undoneTaskListView = mView.findViewById(R.id.undoneTaskListView);


        if (!homeWorkList.isEmpty()) {
            undoneTaskListView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        } else {
            undoneTaskListView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }


        homeWorkAdapter = new HomeWorkAdapter(getContext(), homeWorkList);
        undoneTaskListView.setAdapter(homeWorkAdapter);

        long currentDate = System.currentTimeMillis();

        String displayDate = new SimpleDateFormat().format(currentDate);

        StringTokenizer stringTokenizer = new StringTokenizer(displayDate);


        datetext.setText(stringTokenizer.nextToken());


        for (int i = 0; i < examsList.size(); i++) {
            homeWorkList.add(new HomeWork(examsList.get(i).getCode(), examsList.get(i).getTitle(), examsList.get(i).getTime(), examsList.get(i).getDay()));
        }

//        homeWorkList.add(new HomeWork("skdjfsd", "skldjfnsdf", "skdfsd", "jksdfsdf"));

        return mView;
    }


    @Override
    public void onResume() {
        undoneTaskListView = mView.findViewById(R.id.undoneTaskListView);

        homeWorkList = new ArrayList<>();
        homeWorkList = databaseAccess.getHomeWork();

        examsList = new ArrayList<>();
        examsList = databaseAccess.getExams();

        for (int i = 0; i < examsList.size(); i++) {
            homeWorkList.add(new HomeWork(examsList.get(i).getCode() + " exam", examsList.get(i).getTitle(), examsList.get(i).getTime(), examsList.get(i).getDay()));
        }


        homeWorkAdapter = new HomeWorkAdapter(getContext(), homeWorkList);
        undoneTaskListView.setAdapter(homeWorkAdapter);
        homeWorkAdapter.notifyDataSetChanged();

        super.onResume();
    }

}
