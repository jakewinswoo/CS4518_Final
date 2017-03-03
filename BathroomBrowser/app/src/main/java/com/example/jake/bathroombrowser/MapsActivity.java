package com.example.jake.bathroombrowser;

import android.*;
import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    static public Location phone_location = SplashActivity.phone_location;
    List<Bathroom_Database_Entry> list = SplashActivity.list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in worcester and move the camera
        //LatLng sydney = new LatLng(42.274752, -71.808331);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("You are here."));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if (status != ConnectionResult.SUCCESS) { // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        } else { // Google Play Services are available

            // Enabling MyLocation Layer of Google Map
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        1337);
                return;
            }
            mMap.setMyLocationEnabled(true);

            // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);
            phone_location = location;
            //phone_location = SplashActivity.phone_location;

            if (location != null) {
                onLocationChanged(phone_location);

            }

            android.location.LocationListener locationListener = new android.location.LocationListener() {
                public void onLocationChanged(Location location) {
                    // Getting latitude of the current location
                    double latitude = phone_location.getLatitude();

                    // Getting longitude of the current location
                    double longitude = phone_location.getLongitude();

                    // Creating a LatLng object for the current location
                    LatLng latLng = new LatLng(latitude, longitude);

                    // Showing the current location in Google Map
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    // Zoom in the Google Map
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
                    drawBathrooms(mMap,list);

                }
                public void onStatusChanged (String provider, int status, Bundle extras){}
                public void onProviderEnabled(String provider) {}
                public void onProviderDisabled (String provider){}
            };
            locationManager.requestLocationUpdates(provider, 20000, 0, locationListener);
        }
    }

    public void onLocationChanged(Location location) {
        drawBathrooms(mMap,list);

        // Getting latitude of the current location
        double latitude = location.getLatitude();

        // Getting longitude of the current location
        double longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Showing the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
    }

    public void listButtonOnClick(View v) {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), ListActivity.class));
    }

    public void settingsButtonOnClick(View v) {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }

    public void drawBathrooms(GoogleMap googleMap, List<Bathroom_Database_Entry> bathrooms){
        mMap = googleMap;

        int b_size = bathrooms.size();

        for(int i=0;i<b_size;i++){
            double latitude = bathrooms.get(i).getGPSLat();
            double longitude = bathrooms.get(i).getGPSLong();
            LatLng latLng = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title(bathrooms.get(i).getName()));
        }
    }

}
