package com.isnb.remembeer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class Evening extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evening);
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.evening, menu);
		return true;
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkOne(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		imgbtn_currentDrink.setImageResource(R.drawable.beer);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkTwo(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		imgbtn_currentDrink.setImageResource(R.drawable.beer);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkThree(View view) {
		
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkFour(View view) {
		
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkFive(View view) {
		
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkSix(View view) {
		
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkSeven(View view) {
		
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkEight(View view) {
		
	}
	
	/**
	 * 
	 * 
	 * @param view
	 */
	public void chooseDrinkNine(View view) {
		
	}

}
