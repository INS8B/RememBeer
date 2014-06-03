package com.isnb.remembeer.DatabaseRemembeer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DrinkTable {
	DataSource ds;

	public static final String TABLE_DRINK = "tbl_drinks";
    public static final String DRINK_ID = "drinkID";
    public static  final String DRINK_STARTTIME = "startTime";
    public static final String DRINK_ENDTIME = "endTime";
    public static final String DRINK_BLOODALCOHOL = "bloodAlcohol";
    public static  final String DRINK_EVENINGDATE = "eveningDate";

    public static String[] allColumns = {DRINK_ID, DRINK_STARTTIME, DRINK_ENDTIME, DRINK_BLOODALCOHOL, DRINK_EVENINGDATE};


    public static final String CREATE_TABLE_DRINK = "create table "
            + TABLE_DRINK + "(" + DRINK_ID
            + " integer primary key autoincrement, " + DRINK_STARTTIME
            + " numeric not null, "+ DRINK_ENDTIME + " numeric not null, " + DRINK_BLOODALCOHOL + " real not null, " + DRINK_EVENINGDATE + " numeric not null);";

    public DrinkTable(Context ctx) {
         ds = new DataSource(ctx);
    }

    public Drink createDrinkTable(Date startTime, Date endTime, Double bloodAlcohol, Date eveningDate) {

        // Open Database
        ds.getDatabase().isOpen();


        // Insert Values in Database
        ContentValues values = new ContentValues();
        values.put(DRINK_STARTTIME, startTime.toString());
        values.put(DRINK_ENDTIME, endTime.toString());
        values.put(DRINK_BLOODALCOHOL, bloodAlcohol);
        values.put(DRINK_EVENINGDATE, eveningDate.toString());

        long insertId = ds.getDatabase().insert(TABLE_DRINK, null,
                values);

        // Select the created Record
        Cursor cursor = ds.getDatabase().query(TABLE_DRINK,
                allColumns, DRINK_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();

        // Create an readable Object
        Drink lfLocation = cursorToDrink(cursor);
        cursor.close();

        // Return the Object
        return lfLocation;
    }

    private Drink cursorToDrink(Cursor cursor) {
    	
    	DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		//String currentTime = dateFormat.format(new Date());
		
    	Drink lfLocation = new Drink();
    	
    	try {
	        lfLocation.setDrinkID(cursor.getLong(0));
	        lfLocation.setStartTime(dateFormat.parse(cursor.getString(1)));
			lfLocation.setEndTime(dateFormat.parse(cursor.getString(2)));
			lfLocation.setBloodAlcohol(cursor.getDouble(3));
			lfLocation.setEveningDate(dateFormat.parse(cursor.getString(4)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return lfLocation;
    }
}
