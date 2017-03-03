package com.example.jake.bathroombrowser;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.min;

public class SplashActivity extends AppCompatActivity {

    static public List<Bathroom_Database_Entry> list = Arrays.asList();
    //static public List<Bathroom_Database_Entry> list = Arrays.asList(new Bathroom_Database_Entry(0, "Test",-71.808331,42.274752,"Neutral",0,0,0,1,0,0,5,23,0,""),new Bathroom_Database_Entry(0, "Jeff",-71.820385,42.264675,"Male",1,1,0,3,1,1,7,22,0,"There is a lofted urinal above the stall. You must climb a rolling ladder to use."));
    static public Location phone_location = new Location("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        //phone_location.setLatitude(42.274752);
        //phone_location.setLongitude(-71.808331);
        readFile();

        list = list.subList(0, min(list.size(),110));
        System.out.println(list.size());

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    1337);
            return;
        }
    }

    public void mapButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
    }

    public void listButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),ListActivity.class));
    }

    public void settingsButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
    }

    private void readFile(){
        String data = "";
        int id = 0;

        //StringBuffer sbuffer = new StringBuffer();
        InputStream is = getResources().openRawResource(R.raw.bathrooms);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Context mContext = this.getApplicationContext();
        DBHandler bathroomDatabase = new DBHandler(mContext);
        if (is != null) {
            try {
                while ((data = reader.readLine()) != null) {
                    String[] line = data.split("\t");
                    Bathroom_Database_Entry newBathroom = new Bathroom_Database_Entry(
                            Integer.parseInt(line[0]),
                            line[1],
                            Double.parseDouble(line[2]),
                            Double.parseDouble(line[3]),
                            line[4],
                            Integer.parseInt(line[5]),
                            Integer.parseInt(line[6]),
                            Integer.parseInt(line[7]),
                            Integer.parseInt(line[8]),
                            Integer.parseInt(line[9]),
                            Integer.parseInt(line[10]),
                            Integer.parseInt(line[11]),
                            Integer.parseInt(line[12]),
                            Integer.parseInt(line[13]),
                            line[14]);
                    //Log.v("ID " + line[0], "Lat: " + line[2]);
                    //Log.v("ID " + line[0], "Lon: " + line[3]);
                    bathroomDatabase.addBathroom(newBathroom);
                }
                is.close();
                bathroomDatabase.addBathroom(new Bathroom_Database_Entry(0, "Jeff",42.264675,-71.820385,"Male",1,1,0,0,1,1,7,22,0,"There is a lofted urinal above the stall. You must climb a rolling ladder to use."));
                list = bathroomDatabase.getAllBathrooms();
                int numBathrooms = list.size();
                for(int i = 0; i < numBathrooms; i++){
                    //Log.v("Log","ID: " + list.get(i).getId() + "::: Lat: " + list.get(i).getGPSLat() + "::: Lon: " + list.get(i).getGPSLong());
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
