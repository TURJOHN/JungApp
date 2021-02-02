package com.example.jungapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Objects;

public class PersonalityTestActivity extends AppCompatActivity {
    private WebView webview;
    private final String[] personalities = {"intj", "intp", "entj", "entp", "infj", "infp", "enfj", "enfp", "istj", "isfj", "estj", "esfj", "istp", "isfp", "estp", "esfp"};
    private final String testURL = "https://www.16personalities.com/pl/darmowy-test-osobowosci";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test);

        final Toast wrong_url = Toast.makeText(getApplicationContext(), "Rozwiąż test!", Toast.LENGTH_SHORT);

        Toolbar PTAToolbar = findViewById(R.id.toolbarTest);
        setSupportActionBar(PTAToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Rozwiąż test:");

        webview = findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(false);
        webSettings.setAllowFileAccessFromFileURLs(false);
        webSettings.setAllowContentAccess(false);
        if (savedInstanceState == null) webview.loadUrl(testURL);
        webview.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                url = webview.getUrl();
                boolean found = false;
                if(!url.equals(testURL)) {

                    for(String personality : personalities) {
                        found = url.equals("https://www.16personalities.com/pl/" + "osobowosc-" + personality);
                        if(found) {
                            break;
                        }
                    }
                    if(found) showResultTest(url); else {
                        webview.goBack();
                        wrong_url.show();
                    }
                }
            }
        });
    }

    public void showResultTest(String url){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("TestResult", url);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(PersonalityTestActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        webview.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        webview.restoreState(savedInstanceState);
    }
}