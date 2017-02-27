package com.example.jake.bathroombrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listscreen);

        ListView listview = (ListView) findViewById(R.id.bathroom_list);

        //POPULATE WITH LIST ITEMS FROM DATABASE
        // maybe look in CriminalIntent to see how they populate a list with fragments
        /**
        Array[] dataArray;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.bathroom_list_fragment,"description",dataArray);
        listview.setAdapter(adapter);
         */
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
