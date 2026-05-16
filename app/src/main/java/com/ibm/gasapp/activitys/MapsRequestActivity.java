package com.ibm.gasapp.activitys;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ibm.gasapp.R;

public class MapsRequestActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_request);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(getApplicationContext(),R.raw.map_style));

        Bundle bundle = getIntent().getExtras();
        Double latitude = bundle.getDouble("latitude");
        Double longitude = bundle.getDouble("longitude");
        // Add a marker in Sydney and move the camera
        LatLng requestMap = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(requestMap).title("Rrequest"));
        map.moveCamera(CameraUpdateFactory.newLatLng(requestMap));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(requestMap, 15.0f));
    }
}