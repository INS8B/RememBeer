package com.isnb.remembeer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;


public class Evening extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.activity_evening);
    }
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkOne(View view) {
        ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

        imgbtn_currentDrink.setImageResource(R.drawable.beer);
		/*
		// Name des letzten Getr�nks
		int drinkId = imgbtn_currentDrink.getResources().getIdentifier("imgbtn_currentDrink", "drawable", getPackageName());
		String drinkName = getResources().getResourceName(drinkId);

		// Alkohol Prozent und gr�sse des letzten Getr�nks
		int alcohol = getAlcohol(drinkName);
		int drinkSize = getDrinkSize(drinkName);
		
		// TODO BERECHNUNG WIE VIEL PROMILLE NEU
		
		// Start und End Datum des letzten Getr�nks
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		
		Date startLastDrink = new Date();
		Date endLastDrink = new Date();
		
		try {
			startLastDrink = dateFormat.parse(txtvi_currentDrinkTime.getText().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO letztes Getr�nk in DB speichern
		
		// Bild, aktuelle Promille und Zeit des neuen Getr�nks
		imgbtn_currentDrink.setImageResource(R.drawable.beer);
		String currentTime = dateFormat.format(new Date());
		txtvi_currentDrinkTime.setText(currentTime);
		
		// TODO neuer Promille Wert TextView anzeigen
		txtvi_currentBloodAlcohol.setText("0.3");
		*/
	}
	
	/**
	 * 
	 * @param view
	 * @throws ParseException 
	 */
	public void chooseDrinkTwo(View view) throws ParseException {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);
		
		imgbtn_currentDrink.setImageResource(R.drawable.beerglass);
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		String currentTime = dateFormat.format(new Date());
		
		//Date lastEndDrinkTime = dateFormat.parse(currentTime);
		//Date lastStartDrinkTime = dateFormat.parse(txtvi_currentDrinkTime.getText().toString());
		
		txtvi_currentDrinkTime.setText(currentTime);
		
		//TextView txtvi_testSettings = (TextView) findViewById(R.id.txtvi_testSettings);
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Evening.this);

        //int gewicht = prefs.getInt("gewicht", 0);
        //int sex = prefs.getInt("geschlecht", 0);
        
        //Menge in ml  x  (Vol.-% / 100)  x  0,8 = Gramm reiner Alkohol
		double alcohol = 6/100;// * 0.8;
        
        //  ALkoholmenge in g / (k�rpergewicht in kg * k�rperfl�ssigkeit)		k�rperfl�ssigkeit: M=0.68, F=0.55
        double promille = alcohol / (52 * 0.55);
        
        txtvi_currentBloodAlcohol.setText(String.valueOf(promille));
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkThree(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

        imgbtn_currentDrink.setImageResource(R.drawable.wineglass);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkFour(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

        imgbtn_currentDrink.setImageResource(R.drawable.coctail);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkFive(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);
		
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkSix(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);
		
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkSeven(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);
		
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkEight(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);
		
	}
	
	/**
	 * 
	 * 
	 * @param view
	 */
	public void chooseDrinkNine(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);
		
	}
	
	/**
	 * gibt den Alkohol Prozent des Getr�nks zur�ck
	 * @param imageName Bildname des Getr�nks
	 * @return Alkohol Prozent
	 */
	private int getAlcohol(String imageName) {
		int alcohol = 0;
		
		if(imageName.equals("beerglass") || imageName.equals("beer")) {
			alcohol = 1;
		} else if(imageName.equals("wineglass") || imageName.equals("")) {
			alcohol = 2;
		}
		
		return alcohol;
	}
	
	/**
	 * gibt die Gr�sse des Getr�nks zur�ck
	 * 
	 * @param imageName Bildname des Getr�nks
	 * @return Gr�sse
	 */
	private int getDrinkSize(String imageName) {
		int drinkSize = 0;
		
		if(imageName.equals("beerglass") || imageName.equals("")) {
			drinkSize = 1;
		} else if(imageName.equals("beer") || imageName.equals("")) {
			drinkSize = 2;
		}
		
		return drinkSize;
	}

}
