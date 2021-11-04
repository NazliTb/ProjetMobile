package com.esprit.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TafMetarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tafmetar_layout);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String code = extras.getString("key");
        }

        String url = "https://www.getmetar.com/";
        WebView webview = findViewById(R.id.mywebview);

        //webview.setWebViewClient(new MyWebViewClient(this));
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }
}