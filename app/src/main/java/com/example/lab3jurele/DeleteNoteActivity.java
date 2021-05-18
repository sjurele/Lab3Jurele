package com.example.lab3jurele;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner spDeleteSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spDeleteSelection = findViewById(R.id.spDeleteSelection);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ArrayList<String> fullNotes = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));

        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, fullNotes);
        spDeleteSelection.setAdapter(listAdapter);
    }

    public void onDeleteNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> allList = sp.getStringSet("notes", new HashSet<String>());

        String deleteNote = spDeleteSelection.getSelectedItem().toString();

        Set<String> newList = new HashSet<String>();

        for (String note : allList) {
            if (!note.equalsIgnoreCase(deleteNote)) {
                newList.add(note);
            }
        }

        spEd.putStringSet("notes", newList);
        spEd.apply();

        finish();
    }
}