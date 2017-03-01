package com.example.jake.bathroombrowser;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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

        //create description
        String temp_desc ="";
        temp_desc += (data.get(position).getName());
        temp_desc += ("\n Floor: ");
        temp_desc += (String.valueOf(data.get(position).getEntranceFloor()));
        temp_desc += ("\n Times Open: ");
        temp_desc += (String.valueOf(data.get(position).getOpeningHour()));
        temp_desc += ("-");
        temp_desc += (String.valueOf(data.get(position).getClosingHour()));

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
            Toast.makeText(context, "Please go to Map First",
                    Toast.LENGTH_LONG).show();
        }

        distance.setText(String.valueOf((int)temp_dist));
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
