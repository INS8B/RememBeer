package com.isnb.remembeer.DatabaseRemembeer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class LocationTable {
	
	//DataSource ds;

   /* public static final String TABLE_LOCATIONS = "t_locations";
    public static final String LOCATION_ID = "location_id";
    public static final String LOCATION_DESCRIPTION = "location_description";
    public static  final String LOCATION_NAME = "location_name";
    public static final String LOCATION_LONGITUDE = "location_longitude";
    public static final String LOCATION_LATITUDE = "location_latitude";
    public static  final String LOCATION_CREATIONDATE = "location_creationdate";*/

    //public static String[] allColumns = {LOCATION_ID, LOCATION_DESCRIPTION, LOCATION_NAME, LOCATION_LATITUDE, LOCATION_LONGITUDE, LOCATION_CREATIONDATE};


   /* public static final String CREATE_TABLE_LOCATIONS = "create table "
            + TABLE_LOCATIONS + "(" + LOCATION_ID
            + " integer primary key autoincrement, " + LOCATION_NAME
            + " text not null, "+ LOCATION_DESCRIPTION + " text not null, " + LOCATION_LATITUDE + " real, " + LOCATION_LONGITUDE + " real, "+ LOCATION_CREATIONDATE +" numeric);";

    public LocationTable(Context ctx) {
         ds = new DataSource(ctx);
    }3*/

    /*public RemembeerLocation createRemembeerLocation(String LocationDescription, Double Latitude, Double Longitude) {

        // Open Database
        ds.getDatabase().isOpen();


        // Insert Values in Database
        ContentValues values = new ContentValues();
        values.put(LOCATION_DESCRIPTION, LocationDescription);
        values.put(LOCATION_LATITUDE, Latitude);
        values.put(LOCATION_LONGITUDE, Longitude);

        long insertId = ds.getDatabase().insert(TABLE_LOCATIONS, null,
                values);

        // Select the created Record
        Cursor cursor = ds.getDatabase().query(TABLE_LOCATIONS,
                allColumns, LOCATION_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();

        // Create an readable Object
        RemembeerLocation lfLocation = cursorToLazyFriendLocation(cursor);
        cursor.close();

        // Return the Object
        return lfLocation;
    }*/

   /* private RemembeerLocation cursorToLazyFriendLocation(Cursor cursor) {
    	/*RemembeerLocation lfLocation = new RemembeerLocation();
        lfLocation.setLocationID(cursor.getLong(0));
        lfLocation.setLocationDescription(cursor.getString(1));
        lfLocation.setLatitude(cursor.getDouble(3));
        lfLocation.setLongitude(cursor.getDouble(4));
        return lfLocation;
    }*/
}
