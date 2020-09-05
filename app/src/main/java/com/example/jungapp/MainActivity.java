package com.example.jungapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button openTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openTest = findViewById(R.id.startButton);
        openTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPersonalityTest();
            }
        });
    }

    public void startPersonalityTest(){
        Intent intent = new Intent(this, PersonalityTestActivity.class);
        startActivity(intent);
    }
}