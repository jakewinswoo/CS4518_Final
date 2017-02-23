package com.example.jake.bathroombrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listscreen);
    }

    public void mapButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
    }

    public void settingsButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
    }
}
