package com.example.jake.bathroombrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        CheckBox cb1,cb2,cb3,cb4,cb5,cb6;
        RadioButton male, neutral, female;

        cb1 = (CheckBox)findViewById(R.id.checkBox);
        cb1.setChecked(getFromSP("cb1"));
        cb1.setOnCheckedChangeListener(this);
        cb2 = (CheckBox)findViewById(R.id.checkBox2);
        cb2.setChecked(getFromSP("cb2"));
        cb2.setOnCheckedChangeListener(this);
        cb3 = (CheckBox)findViewById(R.id.checkBox3);
        cb3.setChecked(getFromSP("cb3"));
        cb3.setOnCheckedChangeListener(this);
        cb4 = (CheckBox)findViewById(R.id.checkBox4);
        cb4.setChecked(getFromSP("cb4"));
        cb4.setOnCheckedChangeListener(this);
        cb5 = (CheckBox)findViewById(R.id.checkBox5);
        cb5.setChecked(getFromSP("cb5"));
        cb5.setOnCheckedChangeListener(this);
        cb6 = (CheckBox)findViewById(R.id.checkBox6);
        cb6.setChecked(getFromSP("cb6"));
        cb6.setOnCheckedChangeListener(this);
        male = (RadioButton) findViewById(R.id.radioButton2);
        male.setChecked(getFromSP("male"));
        male.setOnCheckedChangeListener(this);
        female = (RadioButton) findViewById(R.id.radioButton3);
        female.setChecked(getFromSP("female"));
        female.setOnCheckedChangeListener(this);
        neutral = (RadioButton) findViewById(R.id.radioButton4);
        neutral.setChecked(getFromSP("neutral"));
        neutral.setOnCheckedChangeListener(this);

    }

    public void mapButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
    }

    public void listButtonOnClick(View v){
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(),ListActivity.class));
    }

    public boolean getFromSP(String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean(key, false);
    }

    private void saveInSp(String key,boolean value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()){
            case R.id.checkBox:
                saveInSp("cb1",isChecked);
                break;
            case R.id.checkBox2:
                saveInSp("cb2",isChecked);
                break;
            case R.id.checkBox3:
                saveInSp("cb3",isChecked);
                break;
            case R.id.checkBox4:
                saveInSp("cb4",isChecked);
                break;
            case R.id.checkBox5:
                saveInSp("cb5",isChecked);
                break;
            case R.id.checkBox6:
                saveInSp("cb6",isChecked);
                break;
            case R.id.radioButton2:
                saveInSp("male",isChecked);
                break;
            case R.id.radioButton3:
                saveInSp("female",isChecked);
                break;
            case R.id.radioButton4:
                saveInSp("neutral",isChecked);
                break;
        }
    }
}
