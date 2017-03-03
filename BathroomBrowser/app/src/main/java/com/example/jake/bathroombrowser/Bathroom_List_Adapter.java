package com.example.jake.bathroombrowser;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.color.holo_blue_dark;
import static android.R.color.holo_purple;
import static android.content.Context.LOCATION_SERVICE;
import static com.example.jake.bathroombrowser.R.mipmap.ic_launcher;
import static java.security.AccessController.getContext;

/**
 * Created by Jake on 2/28/2017.
 */

public class Bathroom_List_Adapter extends BaseAdapter {

    Context context;
    List<Bathroom_Database_Entry> data;
    private static LayoutInflater inflater = null;

    public Bathroom_List_Adapter(Context context, List<Bathroom_Database_Entry> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(data != null){
            return data.size();
        }
        else{
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if(vi==null){
            vi = inflater.inflate(R.layout.bathroom_list_fragment, null);
        }

        //get reference to list item description view
        TextView description = (TextView) vi.findViewById(R.id.description);

        ImageView thumbnail = (ImageView) vi.findViewById(R.id.thumbnail);

        if(data.get(position).getGender().toLowerCase().equals("neutral")){
            thumbnail.setImageResource(ic_launcher);
        }
        if(data.get(position).getGender().toLowerCase().equals("female")){
            thumbnail.setImageResource(R.drawable.female);
        }
        if(data.get(position).getGender().toLowerCase().equals("male")){
            thumbnail.setImageResource(R.drawable.male);
        }
        if(data.get(position).getName().equals("Jeff")){
            thumbnail.setImageResource(R.drawable.test);
        }

        //create description
        String temp_desc ="";
        temp_desc += (data.get(position).getName());
        temp_desc += ("\n");
        int floor = data.get(position).getEntranceFloor();
        if(floor == 1){
            temp_desc += "On entrance floor.";
        }
        else{
            temp_desc += "Not on entrance floor.";
        }
        temp_desc += ("\nTimes Open: ");
        temp_desc += (String.valueOf(data.get(position).getOpeningHour()));
        temp_desc += ("-");
        temp_desc += (String.valueOf(data.get(position).getClosingHour()));
        temp_desc += ("\n");
        temp_desc += (String.valueOf(data.get(position).getOtherData()));

        description.setText(temp_desc);

        //get reference to list item distance from user textview
        TextView distance = (TextView) vi.findViewById(R.id.distance);

        //calculate distance and set value
        double temp_dist = 0;
        Location phone_location = MapsActivity.phone_location;

        if(phone_location != null){
            //for testing with emulator
            //phone_location.setLatitude(42.274752);
            //phone_location.setLongitude(-71.808331);
            double phone_lat = phone_location.getLatitude();
            double phone_long = phone_location.getLongitude();

            temp_dist = haversine(phone_lat, phone_long, data.get(position).getGPSLat(), data.get(position).getGPSLong());
        }
        else{
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

            }
            LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();
            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location
            phone_location = locationManager.getLastKnownLocation(provider);
            double phone_lat = phone_location.getLatitude();
            double phone_long = phone_location.getLongitude();

            temp_dist = haversine(phone_lat, phone_long, data.get(position).getGPSLat(), data.get(position).getGPSLong());
        }

        distance.setText(String.valueOf((int)temp_dist));

        final int posFinal = position;
        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BathroomActivity.class);
                intent.putExtra("name", data.get(posFinal).getName());
                intent.putExtra("gender", data.get(posFinal).getGender());
                intent.putExtra("stalls",data.get(posFinal).getNumStalls());
                intent.putExtra("floor", data.get(posFinal).getEntranceFloor());
                intent.putExtra("handicap",data.get(posFinal).getHandicap());
                intent.putExtra("urinals", data.get(posFinal).getNumUrinals());
                intent.putExtra("other", data.get(posFinal).getOtherData());
                context.startActivity(intent);
            }
        });
        return vi;
    }

    public static double haversine(
            double lat1, double lng1, double lat2, double lng2) {
        int r = 6371; // average radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = r * c;
        //d is in km, convert to feet
        return (d * 3280.84);
    }
}
