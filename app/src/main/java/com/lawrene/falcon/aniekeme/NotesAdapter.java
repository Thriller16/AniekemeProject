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
 * Created by lawrene on 7/3/18.
 */

public class NotesAdapter extends ArrayAdapter<Notes> {
    public NotesAdapter(@NonNull Context context, @NonNull List<Notes> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notes_list_item, parent, false);
        }

        Notes currentNote = getItem(position);

        TextView titleText = convertView.findViewById(R.id.notecourseCode);
        TextView descText = convertView.findViewById(R.id.noteDesc);
        TextView dateText = convertView.findViewById(R.id.noteDate);

        if(currentNote.getNote().length() > 13){
            descText.setText(currentNote.getNote());
        }
        titleText.setText(currentNote.getCourseTitle());
        dateText.setText(currentNote.getDate());

        return convertView;
    }
}
