package com.example.jake.bathroombrowser;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.jake.bathroombrowser.R.id.BathroomName;
import static com.example.jake.bathroombrowser.R.mipmap.ic_launcher;

public class BathroomActivity extends AppCompatActivity {
    String name = "name";
    String gender = "gender";
    int stalls = 0;
    int urinals = 0;
    int floor = 0;
    int handicap = 0;
    String other = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.bathroom);
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            gender = extras.getString("gender");
            stalls = extras.getInt("stalls");
            floor = extras.getInt("floor");
            handicap = extras.getInt("handicap");
            urinals = extras.getInt("urinals");
            other = extras.getString("other");
        }

        TextView bname = (TextView) findViewById(R.id.BathroomName);
        bname.setText(name);
        TextView bgender = (TextView) findViewById(R.id.textView6);
        bgender.setText(gender);
        ImageView imageview = (ImageView) findViewById((R.id.imageView));
        if(gender.toLowerCase().equals("male")){
            imageview.setImageResource(R.drawable.male);
        }
        else if(gender.toLowerCase().equals("female")){
            imageview.setImageResource((R.drawable.female));
        }
        else{
            imageview.setImageResource(ic_launcher);
        }
        if(name.equals("Jeff")){
            imageview.setImageResource(R.drawable.test);
        }
        TextView bstalls = (TextView) findViewById(R.id.textView7);
        bstalls.setText("Stalls: " + stalls + " Urinals: " + urinals);
        TextView bfloor = (TextView) findViewById(R.id.textView8);
        if(floor == 1){
            bfloor.setText("On entrance floor.");
        }
        else{
            bfloor.setText("Not on entrance floor.");
        }
        TextView bhandi = (TextView) findViewById(R.id.textView9);
        if(handicap == 0){
            bhandi.setText("Not handicap accessible");
        }
        else{
            bhandi.setText("Handicap accessible.");
        }
        TextView bother = (TextView) findViewById(R.id.textView10);
        bother.setText(other);
    }

    public void mapButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
    }

    public void listButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),ListActivity.class));
    }
}
