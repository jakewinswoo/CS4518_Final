package com.example.jake.bathroombrowser;

import android.content.Intent;
import android.location.Location;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    public GoogleApiClient mGoogleApiClient = null;
    public Location mLastLocation;
    protected String mLatitudeLabel;
    protected String mLongitudeLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        try{
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }catch(SecurityException ex){
            System.out.println("Permission error");
        }
        if (mLastLocation != null) {
            mLatitudeLabel = (String.valueOf(mLastLocation.getLatitude()));
            mLongitudeLabel =(String.valueOf(mLastLocation.getLongitude()));
        }
        centerMapOnMyLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in worcester and move the camera
        LatLng sydney = new LatLng(42.2626, -71.8023);
        mMap.addMarker(new MarkerOptions().position(sydney).title("You are here."));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    private void centerMapOnMyLocation() {

        LatLng myLocation = new LatLng(42.274752, -71.808331);
        //LatLng myLocation = new LatLng(0, 0);

        if(mLatitudeLabel != null && mLongitudeLabel != null){
            System.out.println("we have labels");
            System.out.println(mLatitudeLabel);
            System.out.println(mLongitudeLabel);

            myLocation = new LatLng(Integer.parseInt(mLatitudeLabel), Integer.parseInt(mLongitudeLabel));
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,18));
    }

    public void listButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),ListActivity.class));
    }

    public void settingsButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
