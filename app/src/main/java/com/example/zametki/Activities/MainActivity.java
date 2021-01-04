package com.example.zametki.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zametki.Adapters.RecyclerViewAdapter;
import com.example.zametki.Models.Note;
import com.example.zametki.R;
import com.example.zametki.Generic.DB;
import com.example.zametki.Generic.Registry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemListener{

    Button create;
    RecyclerView rv;

    List<Note> notes = new ArrayList<>();

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

        try {
            notes = DB.getAllNotes();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rv = findViewById(R.id.recycler);
        RecyclerViewAdapter rva = new RecyclerViewAdapter(this, getLayoutInflater(), notes,
                this);
        rv.setAdapter(rva);
        rv.setLayoutManager(new LinearLayoutManager(this));

        create = findViewById(R.id.button_create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cr;
                cr = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(cr);

            }
        });
    }

    @Override
    public void onItemClick(int position) throws IllegalAccessException, NoSuchFieldException, InterruptedException {
        Intent i = new Intent(this, OutputNoteActivity.class);
        i.putExtra("id", notes.get(position).id);
        startActivity(i);
    }
}