package com.isnb.remembeer;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;



public class Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.activity_menu);
        

        // DATABASE testen

        Database db = new Database(Menu.this, "db_remembeer");
        
        SQLiteDatabase ddd = db.getWritableDatabase();
       
        
		@SuppressWarnings("deprecation")
		Map<String,String> tables = ddd.getSyncedTables();
        
        String t = "";
        
        String h = t;
	}
	
	/** Called when the user touches the button */
	public void openEvening(View view) {
		Intent intent = new Intent(this, Evening.class);
	    startActivity(intent);
	}
	
	public void startDrinking (View view) {
        Intent intent = new Intent(this, Evening.class);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(), "Hier können dann die Getränke ausgewählt werden!", Toast.LENGTH_SHORT).show();
	}
	
	public void startSettings (View view) {
		Intent intent = new Intent();
	    startActivity(intent);
		//Toast.makeText(getApplicationContext(), "Hier können dann die Einstellungen ausgewählt werden!", Toast.LENGTH_SHORT).show();
	}
	
	public void startHistory (View view) {
		Intent intent = new Intent(this, Evening.class);
	    startActivity(intent);
	}
	

}
