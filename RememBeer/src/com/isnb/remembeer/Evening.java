package com.isnb.remembeer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class Evening extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.activity_evening);
        SharedPreferences prefs = getSharedPreferences("Evening", Context.MODE_PRIVATE);

        ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
        TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
        TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

        if (prefs.contains("currentImgButton")){
            imgbtn_currentDrink.setImageResource(prefs.getInt("currentImgButton", 0));
        }
        if (prefs.contains("currentImgButton")){
            txtvi_currentDrinkTime.setText(prefs.getString("currentDrinkTime", "00:00"));
        }
        if (prefs.contains("currentImgButton")){
            txtvi_currentBloodAlcohol.setText(prefs.getString("currentBloodAlcohol", "0.0‰"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
        TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
        TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

        int img_ID = (Integer) imgbtn_currentDrink.getTag();

        SharedPreferences.Editor prefEditor = getSharedPreferences("Evening", Context.MODE_PRIVATE).edit();
        prefEditor.putString("currentBloodAlcohol", txtvi_currentBloodAlcohol.getText().toString());
        prefEditor.putString("currentDrinkTime", txtvi_currentDrinkTime.getText().toString());
        prefEditor.putInt("currentImgButton", img_ID);
        prefEditor.commit();
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
        imgbtn_currentDrink.setTag(R.drawable.beer);
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
        imgbtn_currentDrink.setTag(R.drawable.beerglass);
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		String currentTime = dateFormat.format(new Date());
		
		//Date lastEndDrinkTime = dateFormat.parse(currentTime);
		//Date lastStartDrinkTime = dateFormat.parse(txtvi_currentDrinkTime.getText().toString());
		

		
		//TextView txtvi_testSettings = (TextView) findViewById(R.id.txtvi_testSettings);


        int size = 300; //Grösse des Getränks in ml
        double volume = 0.05; //Anteil an Alkohol in Getränk als dezimalzahl

        if ("0.0‰".equals(txtvi_currentBloodAlcohol.getText()) && "00:00".equals(txtvi_currentDrinkTime.getText())) {
            txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromille(size, volume)) + "‰");
            txtvi_currentDrinkTime.setText(currentTime);
        } else {
            txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(size, volume)) + "‰");
            txtvi_currentDrinkTime.setText(dateFormat.format(new Date()));
        }
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
        imgbtn_currentDrink.setTag(R.drawable.wineglass);
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
        imgbtn_currentDrink.setTag(R.drawable.coctail);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkFive(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

        imgbtn_currentDrink.setImageResource(0); //Clear Image
        imgbtn_currentDrink.setTag(0);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkSix(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

        imgbtn_currentDrink.setImageResource(0); //Clear Image
        imgbtn_currentDrink.setTag(0);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkSeven(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

        imgbtn_currentDrink.setImageResource(0); //Clear Image
        imgbtn_currentDrink.setTag(0);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkEight(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

        imgbtn_currentDrink.setImageResource(0); //Clear Image
        imgbtn_currentDrink.setTag(0);
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

        //imgbtn_currentDrink.setImageResource(0); //Clear Image
        //imgbtn_currentDrink.setTag(0);

        /** Temporär um die Promillen zurückzusetzen */
        imgbtn_currentDrink.setImageResource(0);
        txtvi_currentBloodAlcohol.setText("0.0‰");
        txtvi_currentDrinkTime.setText("00:00");
	}

    public void neuBerechnen (View view) {
        TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
        TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");

        txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(0, 0.0)) + "‰");
        txtvi_currentDrinkTime.setText(dateFormat.format(new Date()));
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

    public double calcPromille (int drinkSize, double volume) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Evening.this);
        int gewicht = Integer.parseInt(prefs.getString("gewicht", ""));
        int sex = Integer.parseInt(prefs.getString("geschlecht", ""));

        double pure_alc;
        double alc_gramms;
        double wasseranteil; //Wasseranteil im Körper
        double promille;

        // 300ml (grösse einer Stange) * 0.05 (=5% durchschnittlicher Alkoholgehalt von Bier)
        pure_alc = drinkSize * volume;

        //Alkohol in Getränk (pure_alc) * spezifisches Gewicht von Alkohol (0.81)
        alc_gramms = pure_alc * 0.81;

        if (sex == 1) { //Falls männlich
            wasseranteil = gewicht * 0.7;
            promille = alc_gramms / wasseranteil;
            promille = promille * 3; //Resorptionsdefizit
        } else if (sex == 2) {//Falls weiblich
            wasseranteil = gewicht * 0.6;
            promille = alc_gramms / wasseranteil;
            promille = promille * 3; //Resorptionsdefizit
        } else {
            Toast.makeText(getApplicationContext(), "Bitte in den Einstellungen ein Geschlecht auswählen!", Toast.LENGTH_SHORT).show();
            promille = 0;
        }
        return promille;
    }

    public double calcPromilleZeit (int drinkSize, double volume) {
        TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
        TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");

        double currentBloodAlc;
        long timeDiff;
        double hourDiff;
        double abgb_alc;

        currentBloodAlc = Double.parseDouble(txtvi_currentBloodAlcohol.getText().toString().substring(0,3));
        Date timeOld = new Date();
        try {
            timeOld = dateFormat.parse(txtvi_currentDrinkTime.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timeDiff = (new Date()).getTime() - timeOld.getTime();
        hourDiff = ((double)timeDiff) / (60 * 60 * 1000);
        abgb_alc = hourDiff * 0.15; //0.15‰ werden pro Stunde abgebaut

        currentBloodAlc = currentBloodAlc - abgb_alc;
        currentBloodAlc = currentBloodAlc + calcPromille(drinkSize, volume);

        return currentBloodAlc;
    }

}
