package com.example.jungapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView TEST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String URLResult = getIntent().getStringExtra("TestResult");
        TEST = findViewById(R.id.TEMP);
        TEST.setText(URLResult);
    }
}