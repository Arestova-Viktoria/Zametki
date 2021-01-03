package com.example.zametki.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zametki.Models.Note;
import com.example.zametki.R;
import com.example.zametki.generic.DB;

import java.util.ArrayList;
import java.util.List;

public class OutputNoteActivity extends AppCompatActivity {
    TextView name, data, descr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_note);
        name = findViewById(R.id.textView_name);
        data = findViewById(R.id.textView_data);
        descr = findViewById(R.id.textView_note);
        List<Note> notes = new ArrayList<>();
        try {
            notes = DB.getAllNotes();
            name.setText(notes.get(0).title);
            data.setText(notes.get(0).date);
            descr.setText(notes.get(0).description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
