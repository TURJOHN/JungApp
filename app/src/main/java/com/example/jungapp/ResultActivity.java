package com.example.jungapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar RToolbar = findViewById(R.id.toolbarResult);
        setSupportActionBar(RToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Test results:");

        String TestResult = getIntent().getStringExtra("TestResult");
        assert TestResult != null;
        TestResult = TestResult.substring(32,36);
        TestResult = getStringResourceByName(TestResult);
        TextView TestResultTextView = findViewById(R.id.TEMP);
        TestResultTextView.setText(TestResult);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private String getStringResourceByName(String name) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(name, "string", packageName);
        return getString(resId);
    }

}