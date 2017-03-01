package com.example.jake.bathroombrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    List<Bathroom_Database_Entry> list = SplashActivity.list;

    Bathroom_List_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listscreen);

        ListView listview = (ListView) findViewById(R.id.bathroom_list);

        //create list of bathrooms and set list = to it

        //list = database.get10NearestBathrooms;

        adapter = new Bathroom_List_Adapter(this, list);

        listview.setAdapter(adapter);
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
