package com.isnb.remembeer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;



public class Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}
	
	/** Called when the user touches the button */
	public void openEvening(View view) {
		Intent intent = new Intent(this, Evening.class);
	    startActivity(intent);
	}
	
	public void startDrinking (View view) {
		Toast.makeText(getApplicationContext(), "Hier k�nnen dann die Getr�nke ausgew�hlt werden!", Toast.LENGTH_SHORT).show();
	}
	
	public void startSettings (View view) {
		Intent intent = new Intent(this, SettingsActivity.class);
	    startActivity(intent);
		//Toast.makeText(getApplicationContext(), "Hier k�nnen dann die Getr�nke ausgew�hlt werden!", Toast.LENGTH_SHORT).show();
	}
	
	public void startHistory (View view) {
		Intent intent = new Intent(this, Evening.class);
	    startActivity(intent);
	}
	

}
