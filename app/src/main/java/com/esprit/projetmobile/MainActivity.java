package com.esprit.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SearchView search;
    ImageButton sendButton;
    LinearLayout cdgAirport, orlyAirport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_screen);

        search = findViewById(R.id.search);
        CharSequence icao = search.getQuery();
        sendButton = findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),icao,Toast.LENGTH_LONG).show();
               startActivity(new Intent(MainActivity.this, TafMetarActivity.class).putExtra("icao code",icao));

            }
        });

        cdgAirport = findViewById(R.id.cdgairport);
        cdgAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });

        orlyAirport = findViewById(R.id.orlyairport);
        orlyAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });
    }
}