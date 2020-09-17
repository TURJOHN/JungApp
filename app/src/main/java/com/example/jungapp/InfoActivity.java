package com.example.jungapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] personalities;
    private EditText[] PersonalityStatsTextViews = new EditText[10];

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toolbar IToolbar = findViewById(R.id.toolbarTest);
        setSupportActionBar(IToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Możliwe wyniki:");

        personalities = getStringArrayResourceByName("personalities");
        String[] arraySpinner = getStringArrayResourceByName("tree_names");
        Spinner treeSpinner = findViewById(R.id.TreeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        treeSpinner.setAdapter(adapter);
        treeSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(InfoActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String personality = personalities[position];

        String personalityName = parent.getItemAtPosition(position).toString();
        TextView TestResultTextView = findViewById(R.id.PersonalityName);
        TestResultTextView.setText(personalityName);

        int temp;
        ImageView image = findViewById(R.id.TreeAvatar);
        temp = getResources().getIdentifier(personality, "drawable", getPackageName());
        image.setImageResource(temp);
        personality = personality + "_stats";
        String[] personalityStats = getStringArrayResourceByName(personality);

        String[] idPersonality = new String[]{"LightNeed", "WarmthNeed", "WaterNeed", "NutriNeed",
                "ColdResist", "PolutionResist", "FireResist", "WindResist", "AbilitySpecialDESC"};
        for(int i = 0; i< idPersonality.length; i++) {
            temp = getResources().getIdentifier(idPersonality[i], "id", getPackageName());
            PersonalityStatsTextViews[i] = findViewById(temp);
            PersonalityStatsTextViews[i].setText(personalityStats[i]);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private String[] getStringArrayResourceByName(String name) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(name, "array", packageName);
        return getResources().getStringArray(resId);
    }
}

