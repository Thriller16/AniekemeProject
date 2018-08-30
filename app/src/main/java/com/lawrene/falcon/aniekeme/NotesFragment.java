package com.lawrene.falcon.aniekeme;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {


    View mView;
    FloatingActionButton addNotesFab;
    List<Notes> notesList = new ArrayList<>();
    ListView notesListView;
    DatabaseAccess databaseAccess;
    NotesAdapter notesAdapter;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_notes, container, false);
        addNotesFab = mView.findViewById(R.id.add_notes);
        databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();

        notesList = databaseAccess.getNotes();

        notesListView = mView.findViewById(R.id.notesListView);
        notesAdapter = new NotesAdapter(getContext(), notesList);
        notesListView.setAdapter(notesAdapter);


        addNotesFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddNoteActivity.class));
            }
        });

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                startActivity(new Intent(getContext(), AddNoteActivity.class)
                        .putExtra("note", notesList.get(position).getNote())
                        .putExtra("date", notesList.get(position).getDate()));
            }
        });

        return mView;
    }

    @Override
    public void onResume() {
        notesListView = mView.findViewById(R.id.notesListView);
        notesList = new ArrayList<>();
        notesList = databaseAccess.getNotes();

        notesAdapter = new NotesAdapter(getContext(), notesList);
        notesListView.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();

        super.onResume();
    }
}
