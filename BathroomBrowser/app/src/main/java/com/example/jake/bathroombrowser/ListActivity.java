package com.example.jake.bathroombrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.min;

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

        List<Bathroom_Database_Entry> displayList = filterList(list);

        Collections.sort(displayList, new Comparator<Bathroom_Database_Entry>() {
            public int compare(Bathroom_Database_Entry o1, Bathroom_Database_Entry o2) {
                double lat1 = MapsActivity.phone_location.getLatitude();
                double long1 = MapsActivity.phone_location.getLongitude();
                double dist1 = Bathroom_List_Adapter.haversine(lat1, long1, o1.getGPSLat(), o1.getGPSLong());
                double dist2 = Bathroom_List_Adapter.haversine(lat1, long1, o2.getGPSLat(), o2.getGPSLong());
                if (dist1 == dist2) {
                    return 0;
                } else {
                    return dist1 < dist2 ? -1 : 1;
                }
            }
        });

        //only display nearest 10 bathrooms
        List<Bathroom_Database_Entry> sublist = displayList.subList(0, min(displayList.size(), 10));

        adapter = new Bathroom_List_Adapter(this, sublist);

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

    public List<Bathroom_Database_Entry> filterList(List<Bathroom_Database_Entry> startlist){
        int size = startlist.size();
        List<Bathroom_Database_Entry> newlist = new ArrayList<>();

        boolean handi, change, hy, card, open;
        handi = getFromSP("cb1");
        change = getFromSP("cb2");
        hy = getFromSP("cb3");
        card = getFromSP("cb4");
        open = getFromSP("cb5");

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);

        for(int i=0;i<size;i++){
            boolean add = true;
            if(handi){
                if(!(list.get(i).getHandicap() == 1)){
                    add = false;
                    continue;
                }
            }
            if(change){
                if(!(list.get(i).getChangingTable() == 1)){
                    add = false;
                    continue;
                }
            }
            if(hy){
                if(!(list.get(i).getFemHygiene() == 1)){
                    add = false;
                    continue;
                }
            }
            if(card){
                if(!(list.get(i).getEntranceFloor() == 1)){
                    add = false;
                    continue;
                }
            }
            if(open){
                if(!((list.get(i).getOpeningHour() < hour) && (hour < list.get(i).getClosingHour()))){
                    add = false;
                    continue;
                }
            }
            if(!getFromSP(list.get(i).getGender().toLowerCase())){
                add = false;
                continue;
            }

            if(add){
                newlist.add(list.get(i));
            }
        }
        System.out.println(newlist.size());
        System.out.println(startlist.size());
        return newlist;
    }

    private boolean getFromSP(String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean(key, false);
    }
}
