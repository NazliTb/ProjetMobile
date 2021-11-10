package com.esprit.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MapActivity extends AppCompatActivity {
    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_screen);


        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this,MainActivity.class));
            }
        });

        String icao = getIntent().getStringExtra("icaocode");
        String longlat = getIntent().getStringExtra("longlat");
        //Toast.makeText(getApplicationContext(), icao, Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), longlat, Toast.LENGTH_LONG).show();

        //String longitude = longlat.split(" ")[0];
        //String latitude = longlat.split(" ")[1];

        String[] data = longlat.split(" ");
        String longitude = data[0];
        String latitude = data[data.length - 1];

        //Toast.makeText(getApplicationContext(),longitude.toString(), Toast.LENGTH_LONG).show();

        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK); //render
        map.setBuiltInZoomControls(true); //zoomable
        GeoPoint startpoint = new GeoPoint(Float.parseFloat(latitude), Float.parseFloat(longitude));
        IMapController mapController = map.getController();
        mapController.setZoom(12.0);
        mapController.setCenter(startpoint);


        ArrayList<OverlayItem> items = new ArrayList<>();
        //OverlayItem airport1 = new OverlayItem("CDG Airport", "Paris Charles de Gaulle Airport", new GeoPoint(49.0097, 2.5480));
        //Drawable m = airport1.getMarker(0);

        items.add(new OverlayItem("Airport", "Airport", new GeoPoint(Float.parseFloat(latitude), Float.parseFloat(longitude))));
        //items.add(new OverlayItem("Orly Airport", "Paris Orly Airport", new GeoPoint(48.7262, 2.3652)));

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(), items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                startActivity(new Intent(MapActivity.this, TafMetarActivity.class).putExtra("icaocode", icao));
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });
        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

}