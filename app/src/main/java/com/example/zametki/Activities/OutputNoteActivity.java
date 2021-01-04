package com.example.zametki.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zametki.Models.Note;
import com.example.zametki.R;
import com.example.zametki.Generic.DB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OutputNoteActivity extends AppCompatActivity {
    TextView name, data, descr;
    Button saveThis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_note);
        name = findViewById(R.id.textView_name);
        data = findViewById(R.id.textView_data);
        descr = findViewById(R.id.textView_note);
        saveThis = findViewById(R.id.button_save);

        Note note = new Note();
        Bundle arguments = getIntent().getExtras();
        if (null != arguments) {
            int id = arguments.getInt("id");
            try {
                note = DB.getNote(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<Note> notes = new ArrayList<>();
        try {
            notes = DB.getAllNotes();
            name.setText(notes.get(46).title);
            data.setText(notes.get(46).date);
            descr.setText(notes.get(46).description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        saveThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                final String dateText = dateFormat.format(currentDate);
                data.setText(dateText);

                Note editNote = new Note();
                editNote.date = dateText;

                EditText txtDescription = (EditText) descr;
                editNote.description = txtDescription.getText().toString();

                EditText txtName = (EditText) name;
                editNote.title = txtName.getText().toString();

                try {
                    DB.updateNote(editNote);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });



    }

}
