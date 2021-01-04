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
import java.util.Date;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity {

    TextView data;
    EditText name, descr;
    Button bSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //Сделать это при нажатии кнопки сохранить
        data = findViewById(R.id.textView_data);
        name = findViewById(R.id.editText_name);
        descr = findViewById(R.id.editText_note);
        bSave = findViewById(R.id.button_save);

       Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        final String dateText = dateFormat.format(currentDate);
        data.setText(dateText);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note newNote = new Note();
                newNote.date = dateText;

                EditText txtDescription = (EditText) descr;
                newNote.description = txtDescription.getText().toString();

                EditText txtName = (EditText) name;
                newNote.title = txtName.getText().toString();

                try {
                    DB.addNote(newNote);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /*List<Note> notes = new ArrayList<>();
                try {
                    notes = DB.getAllNotes();
                    name.setText(notes.get(0).title);
                    data.setText(notes.get(0).date);
                    descr.setText(notes.get(0).description);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                finish();
            }
        });
    }


}
