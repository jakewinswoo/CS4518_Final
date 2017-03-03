package com.example.jake.bathroombrowser;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 2/27/2017.
 */

public class DBHandler extends SQLiteOpenHelper{

    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "bathroomsData";
    //Table Name
    private static final String TABLE_BATHROOMS = "bathrooms";
    //Bathrooms Table Columns Names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LONG = "GPSLong";
    private static final String KEY_LAT = "GPSLat";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_STALLS = "numStalls";
    private static final String KEY_URINALS = "numUrinals";
    private static final String KEY_HANDICAP = "handicap";
    private static final String KEY_ENTRANCE = "entranceFloor";
    private static final String KEY_CHANGING = "changingTable";
    private static final String KEY_PURCHASE = "purchaseRequired";
    private static final String KEY_OPENING = "openingHour";
    private static final String KEY_CLOSING = "closingHour";
    private static final String KEY_HYGIENE = "femHygiene";
    private static final String KEY_OTHER = "otherData";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BATHROOMS);
        String CREATE_BATHROOMS_TABLE = "CREATE TABLE " + TABLE_BATHROOMS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAME + " TEXT, " +
                KEY_LONG + " REAL, " +
                KEY_LAT + " REAL, " +
                KEY_GENDER + " TEXT, " +
                KEY_STALLS + " INTEGER, " +
                KEY_URINALS + " INTEGER, " +
                KEY_HANDICAP + " INTEGER, " +
                KEY_ENTRANCE + " INTEGER, " +
                KEY_CHANGING + " INTEGER, " +
                KEY_PURCHASE + " INTEGER, " +
                KEY_OPENING + " INTEGER, " +
                KEY_CLOSING + " INTEGER, " +
                KEY_HYGIENE + " INTEGER, " +
                KEY_OTHER + " TEXT" + ")";
        db.execSQL(CREATE_BATHROOMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP OLDER TABLE IF EXISTED
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BATHROOMS);
        //CREATING TABLES AGAIN
        onCreate(db);
    }

    public void addBathroom(Bathroom_Database_Entry bathroom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bathroom.getName()); // Bathroom Location and Floor
        values.put(KEY_LONG, bathroom.getGPSLong());
        values.put(KEY_LAT, bathroom.getGPSLat());
        values.put(KEY_GENDER, bathroom.getGender()); //Gender
        values.put(KEY_STALLS, bathroom.getNumStalls());
        values.put(KEY_URINALS, bathroom.getNumUrinals());
        values.put(KEY_HANDICAP, bathroom.getHandicap());
        values.put(KEY_ENTRANCE, bathroom.getEntranceFloor());
        values.put(KEY_CHANGING, bathroom.getChangingTable());
        values.put(KEY_PURCHASE, bathroom.getPurchaseRequired());
        values.put(KEY_OPENING, bathroom.getOpeningHour());
        values.put(KEY_CLOSING, bathroom.getClosingHour());
        values.put(KEY_HYGIENE, bathroom.getFemHygiene());
        values.put(KEY_OTHER, bathroom.getOtherData());

        db.insert(TABLE_BATHROOMS, null, values);
        db.close();
    }

    //Getting one bathroom
    public Bathroom_Database_Entry getBathroom(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BATHROOMS, new String[] {KEY_ID, KEY_NAME, KEY_LONG, KEY_LAT,
                        KEY_GENDER, KEY_STALLS, KEY_URINALS, KEY_HANDICAP, KEY_ENTRANCE, KEY_CHANGING,
                        KEY_PURCHASE, KEY_OPENING, KEY_CLOSING, KEY_HYGIENE, KEY_OTHER}, KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Bathroom_Database_Entry contact = new Bathroom_Database_Entry(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Double.parseDouble(cursor.getString(2)),
                Double.parseDouble(cursor.getString(3)),
                cursor.getString(4),
                Integer.parseInt(cursor.getString(5)),
                Integer.parseInt(cursor.getString(6)),
                Integer.parseInt(cursor.getString(7)),
                Integer.parseInt(cursor.getString(8)),
                Integer.parseInt(cursor.getString(9)),
                Integer.parseInt(cursor.getString(10)),
                Integer.parseInt(cursor.getString(11)),
                Integer.parseInt(cursor.getString(12)),
                Integer.parseInt(cursor.getString(13)),
                cursor.getString(14));
        //return the newly created bathroom_database_entry
        return contact;
    }

    //Getting ALL bathrooms
    public List<Bathroom_Database_Entry> getAllBathrooms(){
        List<Bathroom_Database_Entry> bathroomList = new ArrayList<Bathroom_Database_Entry>();
        //Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BATHROOMS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Looping through all rows and adding to list
        if (cursor.moveToFirst()){
            do{
                Bathroom_Database_Entry bathroom = new Bathroom_Database_Entry();
                bathroom.setId(Integer.parseInt(cursor.getString(0)));
                bathroom.setName(cursor.getString(1));
                bathroom.setGPSLong(Double.parseDouble(cursor.getString(2)));
                bathroom.setGPSLat(Double.parseDouble(cursor.getString(3)));
                bathroom.setGender(cursor.getString(4));
                bathroom.setNumStalls(Integer.parseInt(cursor.getString(5)));
                bathroom.setNumUrinals(Integer.parseInt(cursor.getString(6)));
                bathroom.setHandicap(Integer.parseInt(cursor.getString(7)));
                bathroom.setEntranceFloor(Integer.parseInt(cursor.getString(8)));
                bathroom.setChangingTable(Integer.parseInt(cursor.getString(9)));
                bathroom.setPurchaseRequired(Integer.parseInt(cursor.getString(10)));
                bathroom.setOpeningHour(Integer.parseInt(cursor.getString(11)));
                bathroom.setClosingHour(Integer.parseInt(cursor.getString(12)));
                bathroom.setFemHygiene(Integer.parseInt(cursor.getString(13)));
                bathroom.setOtherData(cursor.getString(14));
                //Adding contact to list
                bathroomList.add(bathroom);
            } while (cursor.moveToNext());
        }
        //return contact list
        return bathroomList;
    }

    //get total number of bathrooms recorded
    public int getBathroomCount() {
        String countQuery = "SELECT * FROM " + TABLE_BATHROOMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        //return the count
        return cursor.getCount();
    }


    /*
    How to use DBHandler
    DBHandler db = new DBHandler(this);
    //To insert bathrooms
    db.addBathroom(new Bathrom_Database_Entry(blah blah blah));
    db.addBathroom.............................................
    ..............
    Bathroom_Database_Entries have getters and setters, they can be found individually
    by their ID or all can be put into a list via the getAllBathrooms method.
     */
}