package com.example.jake.bathroombrowser;

/**
 * Created by Joe on 2/27/2017.
 */

public class Bathroom_Database_Entry {
    private int id;
    private String name; //This is the Location
    private float GPSLong; // This is the X coord
    private float GPSLat; // This is the Y coord
    private String gender; // Gender of bathroom
    private int numStalls; //Number of stalls
    private int numUrinals; //Number of urinals, this will only be > 0 in Male bathrooms
    private int handicap; // 0 = N, 1 = Y
    private int entranceFloor; // 0 = N, 1 = Y
    private int changingTable; // 0 = N, 1 = Y
    private int purchaseRequired; //0 = N, 1 = Y
    private int openingHour; // 0 through 23
    private int closingHour; // 0 through 23
    private int femHygiene; // 0 = N, 1 = Y


    public Bathroom_Database_Entry(){

    }
    public Bathroom_Database_Entry(int id,
                                   String name,
                                   float GPSLong,
                                   float GPSLat,
                                   String gender,
                                   int numStalls,
                                   int numUrinals,
                                   int handicap,
                                   int entranceFloor,
                                   int changingTable,
                                   int purchaseRequired,
                                   int openingHour,
                                   int closingHour,
                                   int femHygiene)
    {
        this.id = id;
        this.name = name;
        this.GPSLong = GPSLong;
        this.GPSLat = GPSLat;
        this.gender = gender;
        this.numStalls = numStalls;
        this.numUrinals = numUrinals;
        this.handicap = handicap;
        this.entranceFloor = entranceFloor;
        this.changingTable = changingTable;
        this.purchaseRequired = purchaseRequired;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.femHygiene = femHygiene;
    }
    public void setId (int id){
        this.id = id;
    }
    public void setName (String name){
        this.name = name;
    }
    public void setGPSLong (float gpsLong){
        this.GPSLong = gpsLong;
    }
    public void setGPSLat (float gpsLat){
        this.GPSLat = GPSLat;
    }
    public void setGender (String gender){
        this.gender = gender;
    }
    public void setNumStalls (int numStalls){
        this.numStalls = numStalls;
    }
    public void setNumUrinals (int numUrinals){
        this.numUrinals = numUrinals;
    }
    public void setHandicap (int handicap){
        this.handicap = handicap;
    }
    public void setEntranceFloor (int entranceFloor){
        this.entranceFloor = entranceFloor;
    }
    public void setChangingTable (int changingTable){
        this.changingTable = changingTable;
    }
    public void setPurchaseRequired (int purchaseRequired){
        this.purchaseRequired = purchaseRequired;
    }
    public void setOpeningHour (int openingHour){
        this.openingHour = openingHour;
    }
    public void setClosingHour (int closingHour){
        this.closingHour = closingHour;
    }
    public void setFemHygiene (int femHygiene){
        this.femHygiene = femHygiene;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getGPSLong() {
        return GPSLong;
    }

    public float getGPSLat() {
        return GPSLat;
    }

    public String getGender() {
        return gender;
    }

    public int getNumStalls() {
        return numStalls;
    }

    public int getNumUrinals() {
        return numUrinals;
    }

    public int getHandicap() {
        return handicap;
    }

    public int getEntranceFloor() {
        return entranceFloor;
    }

    public int getChangingTable() {
        return changingTable;
    }

    public int getPurchaseRequired() {
        return purchaseRequired;
    }

    public int getOpeningHour() {
        return openingHour;
    }

    public int getClosingHour() {
        return closingHour;
    }

    public int getFemHygiene() {
        return femHygiene;
    }
}