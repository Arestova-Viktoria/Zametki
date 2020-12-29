package com.example.zametki.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zametki.Models.Note;
import com.example.zametki.R;
import com.example.zametki.generic.DB;
import com.example.zametki.generic.Registry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button create;
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
}