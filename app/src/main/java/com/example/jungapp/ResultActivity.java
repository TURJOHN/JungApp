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

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


public class ResultActivity extends AppCompatActivity {

    private EditText[] PersonalityStatsTextViews = new EditText[10];

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar RToolbar = findViewById(R.id.toolbarResult);
        setSupportActionBar(RToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Wynik testu:");

        String TestResult = getIntent().getStringExtra("TestResult");
        assert TestResult != null;
        String testResultPersonality = TestResult.substring(45, 49);
        String personality = getStringResourceByName(testResultPersonality);
        TextView TestResultTextView = findViewById(R.id.PersonalityName);
        TestResultTextView.setText(personality);

        int temp;
        int resistance;
        int resistancesSum = 0;
        int min = 1;
        ImageView image = findViewById(R.id.TreeAvatar);
        temp = getResources().getIdentifier(testResultPersonality, "drawable", getPackageName());
        image.setImageResource(temp);
        testResultPersonality = testResultPersonality + "_stats";
        String[] personalityStats = getStringArrayResourceByName(testResultPersonality);

        String[] id = new String[]{"LightNeed", "WarmthNeed", "WaterNeed", "NutriNeed",
                "ColdResist", "PolutionResist", "FireResist", "WindResist", "VigorStat", "AbilitySpecialDESC"};
        for(int i = 0; i< id.length; i++) {
            if(i>3 && i<8) {
                try {
                    resistance = Integer.parseInt(personalityStats[i]);
                    resistancesSum = resistancesSum + resistance;
                } catch (NumberFormatException e) {
                    resistancesSum = resistancesSum + (Integer.parseInt(personalityStats[i].substring(personalityStats[i].length() - 1)));
                }
            }
            temp = getResources().getIdentifier(id[i], "id", getPackageName());
            PersonalityStatsTextViews[i] = findViewById(temp);
            if(personalityStats[i].equals("lisciaste")) {
                personalityStats[i] = VigorStat("lisc_max", resistancesSum, min);
            }
            if(personalityStats[i].equals("iglaste")) {
                personalityStats[i] = VigorStat("igla_max", resistancesSum, min);
            }
            PersonalityStatsTextViews[i].setText(personalityStats[i]);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String VigorStat(String name, int vigor, int min) {
        int max = Integer.parseInt(getStringResourceByName(name));
        vigor = vigor + ThreadLocalRandom.current().nextInt(min, max + 1);
        return Integer.toString(vigor);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}