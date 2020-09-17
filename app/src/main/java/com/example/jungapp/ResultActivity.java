package com.example.jungapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    private EditText[] PersonalityStatsTextViews = new EditText[10];

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
        String testResultPersonality = TestResult.substring(45, 49);
        String personality = getStringResourceByName(testResultPersonality);
        TextView TestResultTextView = findViewById(R.id.PersonalityName);
        TestResultTextView.setText(personality);

        int temp;
        ImageView image = findViewById(R.id.TreeAvatar);
        temp = getResources().getIdentifier(testResultPersonality, "drawable", getPackageName());
        image.setImageResource(temp);
        testResultPersonality = testResultPersonality + "_stats";
        String[] personalityStats = getStringArrayResourceByName(testResultPersonality);

        String[] id = new String[]{"LightNeed", "WarmthNeed", "WaterNeed", "NutriNeed",
                "ColdResist", "PolutionResist", "FireResist", "WindResist", "AbilitySpecialDESC"};
        for(int i = 0; i< id.length; i++) {
            temp = getResources().getIdentifier(id[i], "id", getPackageName());
            PersonalityStatsTextViews[i] = findViewById(temp);
            PersonalityStatsTextViews[i].setText(personalityStats[i]);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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

    private String[] getStringArrayResourceByName(String name) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(name, "array", packageName);
        return getResources().getStringArray(resId);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}