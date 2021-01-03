package com.example.zametki.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zametki.Models.Note;
import com.example.zametki.R;
import com.example.zametki.generic.DB;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    TextView name, data, descr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }


}
