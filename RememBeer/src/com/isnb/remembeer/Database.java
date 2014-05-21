package com.isnb.remembeer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {


		   private SQLiteDatabase db;

		   // Konstruktor
		   public Database(Context activity, String dbName) {
		      super(activity, dbName, null, 1); 
		      db = getWritableDatabase();
		   }

		   public void onCreate(SQLiteDatabase db) {
		     try {
		        // Tabelle anlegen 
		        /*String sqlUser = "CREATE TABLE tbl_user "  +
		        		"(userId INTEGER PRIMARY KEY AUTOINCREMENT," +
		        		"name VARCHAR(250) NOT NULL, " +
		        		"sex BOOLEAN NOT NULL, " +       
		        		"weight INTEGER NOT NULL, " +
		        		"drinker INTEGER NOT NULL, " +
		        		"withSelfie BOOLEAN NOT NULL)";
		        db.execSQL(sqlUser); */
		        
		        String sqlDrinks = "CREATE TABLE tbl_drinks "  +
		        		"(drinkId INTEGER PRIMARY KEY AUTOINCREMENT," +
		        		"startTime DATETIME NOT NULL, " +
		        		"endTime DATETIME NOT NULL, " +
		        		"bloodAlcohol DOUBLE NOT NULL)";
		        db.execSQL(sqlDrinks);
		        
		        String sqlEvening = "CREATE TABLE tbl_evening "  +
		        		"(eveningId INTEGER PRIMARY KEY AUTOINCREMENT," +
		        		"startTime DATETIME NOT NULL, " +
		        		"endTime DATETIME NOT NULL, " +
		        		"FOREIGN KEY(drinkId) REFERENCES tbl_drinks(drinkId))";
		        db.execSQL(sqlEvening);
		        
		     }
		     catch(Exception ex) {
		       Log.e("carpelibrum", ex.getMessage());
		     }
		   }

		   public void onUpgrade(SQLiteDatabase db, int oldVersion, 
		                        int newVersion) {
		      // auf Versionswechsel reagieren
		   }
		
}
