package com.example.zametki.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_note);
        name = findViewById(R.id.textView_name);
        data = findViewById(R.id.textView_data);
        descr = findViewById(R.id.textView_note);
        saveThis = findViewById(R.id.button_save);

        Bundle arguments = getIntent().getExtras();
        if (null != arguments) {
            int id = arguments.getInt("id");
            try {
                note = DB.getNote(id);
                name.setText(note.title);
                data.setText(note.date);
                descr.setText(note.description);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                editNote.description = descr.getText().toString();
                editNote.title = name.getText().toString();

                try {
                    if (null != note) {
                        editNote.id = note.id;
                        DB.updateNote(editNote);
                    } else {
                        DB.addNote(editNote);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
