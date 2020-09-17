package com.example.jungapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openTest = findViewById(R.id.startButton);
        Button openInfo = findViewById(R.id.infoButton);
        openTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPersonalityTest();
            }
        });
        openInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfo();
            }
        });
    }

    public void startPersonalityTest(){
        Intent intent = new Intent(this, PersonalityTestActivity.class);
        startActivity(intent);
    }

    public void showInfo(){
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}