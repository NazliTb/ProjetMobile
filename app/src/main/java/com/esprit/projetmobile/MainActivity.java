package com.esprit.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    SearchView search;
    ImageButton sendButton;
    LinearLayout cdgAirport, orlyAirport, beijingAirport, dammamAirport, istanbulAirport;
    String longlat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_screen);

        search = findViewById(R.id.search);
        CharSequence icao = search.getQuery();
        String url = "https://www.google.com/search?q=" + icao.toString() + "+long+lat";

        sendButton = findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),icao,Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, MapActivity.class).putExtra("icaocode", icao.toString()));

            }
        });

        cdgAirport = findViewById(R.id.cdgairport);
        cdgAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class).putExtra("icaocode", "LFPG"));
            }
        });

        orlyAirport = findViewById(R.id.orlyairport);
        orlyAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class).putExtra("icaocode", "LFPO"));
            }
        });

        beijingAirport = findViewById(R.id.beijingAirport);
        beijingAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class).putExtra("icaocode", "ZBAA"));
            }
        });

        dammamAirport = findViewById(R.id.dammamAirport);
        dammamAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class).putExtra("icaocode", "OEDF"));
            }
        });

        istanbulAirport = findViewById(R.id.istanbulAirport);
        istanbulAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class).putExtra("icaocode", "LTFM"));
            }
        });
    }

    private class getLongLat extends AsyncTask<Void, Void, Void> {

        SearchView search = findViewById(R.id.search);
        CharSequence icao = search.getQuery();
        //String url = "https://www.google.com/search?q=" + icao.toString() + "+long+lat";

        @Override
        protected Void doInBackground(Void... voids) {
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.google.com/search?q=" + icao.toString() + "+long+lat").get();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements = doc.getElementsByClass("Z0LcW");
            longlat = elements.text();
            System.out.println(longlat);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}