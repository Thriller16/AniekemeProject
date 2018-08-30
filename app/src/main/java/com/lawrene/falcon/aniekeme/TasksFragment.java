package com.lawrene.falcon.aniekeme;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TasksFragment extends Fragment {

    TextView textView;
    ListView taskListView;
    List<MyTasks> taskslist = new ArrayList<>();
    DatabaseAccess databaseAccess;
    TasksAdapter tasksAdapter;

    FirebaseAuth mFireAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference mTaskDatabase;

    View mView;
    public TasksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_tasks, container, false);

        taskListView = mView.findViewById(R.id.taskList);
        textView = mView.findViewById(R.id.plceholder_text);
        databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();


        mFireAuth = FirebaseAuth.getInstance();
        mCurrentUser = mFireAuth.getCurrentUser();
        mTaskDatabase = FirebaseDatabase.getInstance().getReference().child("Tasks");

        taskslist = databaseAccess.getTasks();
        if(taskslist.size() == 0){
            textView.setVisibility(View.VISIBLE);
            taskListView.setVisibility(View.GONE);
        }else{
            textView.setVisibility(View.GONE);
            taskListView.setVisibility(View.VISIBLE);
        }


        FloatingActionButton addTaskFab = mView.findViewById(R.id.add_new);
        addTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CreateTask.class));
            }
        });


        tasksAdapter = new TasksAdapter(getContext(), taskslist);
        taskListView.setAdapter(tasksAdapter);

//        uploadTasksToFirebase();
        return mView;
    }

    @Override
    public void onResume() {
        taskListView = mView.findViewById(R.id.taskList);
        taskslist = new ArrayList<>();
        taskslist = databaseAccess.getTasks();

        tasksAdapter = new TasksAdapter(getContext(), taskslist);
        taskListView.setAdapter(tasksAdapter);
        tasksAdapter.notifyDataSetChanged();
        super.onResume();
    }


    public void uploadTasksToFirebase(){
        taskslist = databaseAccess.getTasks();

        for(int i = 0; i < taskslist.size(); i++){
            HashMap<String, String> taskMap = new HashMap<>();
            taskMap.put("title", taskslist.get(i).getTitle());
            taskMap.put("description", taskslist.get(i).getDescription());
            taskMap.put("type", taskslist.get(i).getType());
            taskMap.put("willAlert", taskslist.get(i).getWillAlert());
            taskMap.put("willNotify", taskslist.get(i).getWillNotify());
            taskMap.put("time", taskslist.get(i).getTime());

            mTaskDatabase.child(mCurrentUser.getUid()).push().setValue(taskMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getContext(), "All tasks have been sycned online", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
