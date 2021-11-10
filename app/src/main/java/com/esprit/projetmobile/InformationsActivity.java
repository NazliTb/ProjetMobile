package com.esprit.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.informations_layout);

        TextView title = findViewById(R.id.airportTitle);
        TextView body = findViewById(R.id.airportDetails);
        ImageView image = findViewById(R.id.imageAirport);

        title.setText(getIntent().getStringExtra("title"));
        body.setText(getIntent().getStringExtra("body"));


        String icao = getIntent().getStringExtra("icaocode");
        String longlat = getIntent().getStringExtra("longlat");

        ImageButton home = findViewById(R.id.homeBtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InformationsActivity.this, MainActivity.class));
            }
        });

        ImageButton map = findViewById(R.id.mapBtn);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationsActivity.this, MapActivity.class);
                intent.putExtra("icaocode", icao);
                intent.putExtra("longlat", longlat);
                startActivity(intent);
            }
        });


    }
}