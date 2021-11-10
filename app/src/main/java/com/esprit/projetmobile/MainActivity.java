package com.esprit.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    SearchView search;
    ImageButton sendButton;
    LinearLayout cdgAirport, orlyAirport, beijingAirport, dammamAirport, istanbulAirport;
    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_screen);

        search = findViewById(R.id.search);
        test = findViewById(R.id.test);

        CharSequence icao = search.getQuery();
        String url = "https://www.google.com/search?q=" + icao.toString() + "+long+lat";

        sendButton = findViewById(R.id.send);
        sendButton = findViewById(R.id.send);

        final int[] i = {0};
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i[0]++;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (i[0] == 1) {

                            new getLongLat().execute(icao.toString());

                        } else if (i[0] == 2) {

                            //Toast.makeText(getApplicationContext(), icao, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, MapActivity.class);
                            intent.putExtra("icaocode", icao.toString());
                            intent.putExtra("longlat", test.getText().toString());
                            startActivity(intent);

                        }
                    }
                }, 500);
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
                Intent intent = new Intent(MainActivity.this, InformationsActivity.class);
                intent.putExtra("title", "King Fahd International Airport");
                intent.putExtra("body", "Dammam is the largest and capital city of the Eastern Province of Saudi Arabia. The city bases part of its economy around the oil industry, since it is a major administrative centre aside of being the main economic, social and cultural hub of the Eastern region. Given the mixture between cultures, the local cuisine is extremely rich with Yemeni, Lebanese, Syrian, Turkish, Pakistani and Filipino influences.");
                intent.putExtra("icaocode", "OEDF");
                intent.putExtra("longlat", "26.4683 49.7972");
                startActivity(intent);
                //startActivity(new Intent(MainActivity.this, MapActivity.class).putExtra("icaocode", "OEDF"));
            }
        });

        istanbulAirport = findViewById(R.id.istanbulAirport);
        istanbulAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InformationsActivity.class);
                intent.putExtra("title", "Istanbul International Airport");
                intent.putExtra("body", "Istanbul Airport is the new airport in the city and is considered as a huge achievement of the Turkish Government. Added to the two already existing airports of Istanbul (Ataturk Airport and Sabiha Gokcen International Airport), the new aviation hub has undertaken all the Ataturk Airport’s passenger flights from 10/29/18 and is expected to host more than 200 million passengers by 2025. Istanbul Airport is located at the Çatalca- Göktürk-Arnavutköy area, in the European side of the city, 40km from Taksim and 52km from Kadıköy. Set in the middle between Tayakadin and Akpinar village, it serves more than 350 domestic and international destinations in 110 countries while its duty-free area is one of the largest (if not the largest) worldwide.");
                intent.putExtra("icaocode", "LTFM");
                intent.putExtra("longlat", "41.2768  28.7302");
                startActivity(intent);
                //startActivity(new Intent(MainActivity.this, MapActivity.class).putExtra("icaocode", "LTFM"));
            }
        });
    }

    private class getLongLat extends AsyncTask<String, Void, String> {

        //SearchView search = findViewById(R.id.search);
        //CharSequence icao = search.getQuery();
        //String url = "https://www.google.com/search?q=" + icao.toString() + "+long+lat";
        String longlat, lg, lt;

        @Override
        protected String doInBackground(String... voids) {
            final String code = voids[0];
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.google.com/search?q=" + code + "+long+lat").get();
                Element element = doc.getElementById("search");
                longlat = element.text();

                lg = longlat.split("Longitude : ")[1].split(" ")[0];
                lt = longlat.split("Latitude : ")[1].split(" ")[0];


            } catch (IOException e) {
                e.printStackTrace();
            }
            // Element element = doc.getElementsByClass("Z0LcW").last();
            //longlat = element.text();
            //test.setText(longlat);
            return null;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            test.setText(lg + " " + lt);
            test.setVisibility(View.GONE);

        }
    }
}