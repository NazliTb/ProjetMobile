package com.esprit.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class MapActivity extends AppCompatActivity {
    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_screen);

        String icao = getIntent().getStringExtra("icaocode");
        Toast.makeText(getApplicationContext(), icao, Toast.LENGTH_LONG).show();

        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK); //render
        map.setBuiltInZoomControls(true); //zoomable
        GeoPoint startpoint = new GeoPoint(48.8566, 2.3522);
        IMapController mapController = map.getController();
        mapController.setZoom(12.0);
        mapController.setCenter(startpoint);


        ArrayList<OverlayItem> items = new ArrayList<>();
        //OverlayItem airport1 = new OverlayItem("CDG Airport", "Paris Charles de Gaulle Airport", new GeoPoint(49.0097, 2.5480));
        //Drawable m = airport1.getMarker(0);

        items.add(new OverlayItem("CDG Airport", "Paris Charles de Gaulle Airport", new GeoPoint(49.0097, 2.5480)));
        items.add(new OverlayItem("Orly Airport", "Paris Orly Airport", new GeoPoint(48.7262, 2.3652)));

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