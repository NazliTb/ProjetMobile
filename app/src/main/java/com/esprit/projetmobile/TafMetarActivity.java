package com.esprit.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

public class TafMetarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tafmetar_layout);

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TafMetarActivity.this,MainActivity.class));
            }
        });

        String icao = getIntent().getStringExtra("icaocode");

        /*Bundle extras = getIntent().getExtras();
        //        if (extras != null) {
        //            String icao = extras.getString("icao code");
        //            //Toast.makeText(getApplicationContext(),icao,Toast.LENGTH_LONG);
        //        }*/
        //
        //        //Toast.makeText(getApplicationContext(), icao, Toast.LENGTH_LONG).show();
        String url = "https://www.getmetar.com/" + icao;

        WebView webview = findViewById(R.id.mywebview);

        //webview.setWebViewClient(new MyWebViewClient(this));
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }
}