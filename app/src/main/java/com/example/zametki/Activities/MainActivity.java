package com.example.zametki.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zametki.Models.Note;
import com.example.zametki.R;
import com.example.zametki.generic.DB;
import com.example.zametki.generic.Registry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button create;
    TextView thatNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Registry.baseContext = getBaseContext();
        try {
            DB.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Note first = new Note();
        first.title = "Kit";
        first.date = "10.02.2020";
        first.description = "А знаешь как kit с украинского переводится?";
        try {
            DB.addNote(first);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        create = findViewById(R.id.button_create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cr;
                cr = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(cr);
            }
        });
        thatNote = findViewById(R.id.note);
        thatNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent that;
                that = new Intent(MainActivity.this, OutputNoteActivity.class);
                startActivity(that);
            }
        });

    }
}