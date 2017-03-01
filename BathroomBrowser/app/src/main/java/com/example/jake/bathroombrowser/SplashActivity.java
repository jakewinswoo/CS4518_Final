package com.example.jake.bathroombrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    static public List<Bathroom_Database_Entry> list = Arrays.asList(new Bathroom_Database_Entry(0, "Test",-71.808331,42.274752,"Male",0,0,0,1,0,0,5,23,0,""),new Bathroom_Database_Entry(0, "Test2",-71.808380,42.274790,"Male",0,0,0,1,0,0,5,23,0,""));


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
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
}
