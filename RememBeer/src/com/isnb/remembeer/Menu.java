package com.isnb.remembeer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;



public class Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.activity_menu);

        boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun){
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Bitte als Erstes die Einstellugen ausfüllen", Toast.LENGTH_LONG).show();
            // Save the state
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstrun", false)
                    .commit();
        }

        TextView txtvi_testSettings = (TextView) findViewById(R.id.txtvi_testSettings);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Menu.this);

        try {
            txtvi_testSettings.setText(prefs.getString("benutzername", "Hans Muster") + "\n" +
                    prefs.getString("gewicht", "NA") + "\n" +
                    prefs.getString("geschlecht", "NA"));
        } catch (Exception e) {
            Log.wtf("SharedPrefs", e);
        }

	}

    @Override
    public void onResume(){
        super.onResume();
        setContentView(R.layout.activity_menu);

        TextView txtvi_testSettings = (TextView) findViewById(R.id.txtvi_testSettings);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Menu.this);

        try {
            txtvi_testSettings.setText(prefs.getString("benutzername", "Hans Muster") + "\n" +
                    prefs.getString("gewicht", "NA") + "\n" +
                    prefs.getString("geschlecht", "NA"));
        } catch (Exception e) {
            Log.wtf("SharedPrefs", e);
        }
    }

	/** Called when the user touches the button */
	public void openEvening(View view) {
		Intent intent = new Intent(this, Evening.class);
	    startActivity(intent);
	}
	
	public void startSettings (View view) {
		Intent intent = new Intent(this, Settings.class);
	    startActivity(intent);
		//Toast.makeText(getApplicationContext(), "Hier können dann die Einstellungen ausgewählt werden!", Toast.LENGTH_SHORT).show();
	}
	
	public void startHistory (View view) {
		Intent intent = new Intent(this, Evening.class);
	    startActivity(intent);
	}
	

}
