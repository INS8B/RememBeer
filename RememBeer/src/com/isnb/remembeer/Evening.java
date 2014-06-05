package com.isnb.remembeer;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Evening extends Activity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Uri fileUri;

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
            imgbtn_currentDrink.setTag(prefs.getInt("currentImgButton", 0));

        }
        if (prefs.contains("currentDrinkTime")){
            txtvi_currentDrinkTime.setText(prefs.getString("currentDrinkTime", "00:00"));
        }
        if (prefs.contains("currentBloodAlcohol")){
            txtvi_currentBloodAlcohol.setText(prefs.getString("currentBloodAlcohol", "0.0‰"));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
        TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
        TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

        int img_ID = 0;
        try {
            img_ID = (Integer) imgbtn_currentDrink.getTag();
        } catch (NullPointerException e) {
            img_ID = 0;
            Log.wtf("OnDestroy", e + "");
        }


        SharedPreferences.Editor prefEditor = getSharedPreferences("Evening", Context.MODE_PRIVATE).edit();
        prefEditor.putString("currentBloodAlcohol", txtvi_currentBloodAlcohol.getText().toString());
        prefEditor.putString("currentDrinkTime", txtvi_currentDrinkTime.getText().toString());
        prefEditor.putInt("currentImgButton", img_ID);
        prefEditor.commit();
    }
	
	/**
	 * Bier Flasche (0.3 dl mit 0.05 Vol.%)
	 * 
	 * @param view
	 */
	public void chooseDrinkOne(View view) {
        ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

		// aktuelle Zeit
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		String currentTime = dateFormat.format(new Date());
		
		// wenn kein Tag vorhanden ist wurde noch kein Getr�nk vorher getrunken, daher ist noch keine berechnung des Promillestandes m�glich
		if(imgbtn_currentDrink.getTag() != null) {
			int tagLastDrink = (Integer) imgbtn_currentDrink.getTag();
		
	        int size = getDrinkSize(tagLastDrink); //Gr�sse des Getr�nks in ml
	        double volume = getDrinkVolume(tagLastDrink); //Anteil an Alkohol in Getränk als dezimalzahl
	
	        if ("0.0".equals(txtvi_currentBloodAlcohol.getText())) {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromille(size, volume)) + "‰");
	        } else {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(size, volume)) + "‰");
	        }

		}

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Evening.this);
        boolean selfie = false;
        try {
            selfie = prefs.getBoolean("selfie", false);
        } catch (Exception e) {
            Log.wtf("SharedPrefs", e);
        }

        if (selfie) {
            takeSelfie();
            Toast.makeText(getApplicationContext(), "Die Fotos können im Menüpunkt History oder in deiner Gallerie betrachtet werden", Toast.LENGTH_LONG).show();
        }

        // neues Bild des Getr�nks das ausgew�hlt wurde
        imgbtn_currentDrink.setImageResource(R.drawable.beerbottle);
        imgbtn_currentDrink.setTag(1);
        
        // aktuelle Zeit des ausgew�hlten Getr�nks
        txtvi_currentDrinkTime.setText(currentTime);
	}
	
	/**
	 * Stange (0.3 dl mit 0.05 Vol.%)
	 * 
	 * @param view
	 * @throws ParseException 
	 */
	public void chooseDrinkTwo(View view) throws ParseException {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		String currentTime = dateFormat.format(new Date());
		
		//Date lastEndDrinkTime = dateFormat.parse(currentTime);
		//Date lastStartDrinkTime = dateFormat.parse(txtvi_currentDrinkTime.getText().toString());
		

		
		//TextView txtvi_testSettings = (TextView) findViewById(R.id.txtvi_testSettings);


		// wenn kein Tag vorhanden ist wurde noch kein Getr�nk vorher getrunken, daher ist noch keine berechnung des Promillestandes m�glich
		if(imgbtn_currentDrink.getTag() != null) {
			int tagLastDrink = (Integer) imgbtn_currentDrink.getTag();
		
	        int size = getDrinkSize(tagLastDrink); //Gr�sse des Getr�nks in ml
	        double volume = getDrinkVolume(tagLastDrink); //Anteil an Alkohol in Getränk als dezimalzahl
	
	        if ("0.0".equals(txtvi_currentBloodAlcohol.getText())) {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromille(size, volume)) + "‰");
	        } else {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(size, volume)) + "‰");
	        }

		}
        
        // neues Bild das ausgew�hlt wurde 
		imgbtn_currentDrink.setImageResource(R.drawable.beerglass);
        imgbtn_currentDrink.setTag(2);
		
        // aktuelle Zeit des ausgew�hlten Getr�nks
        txtvi_currentDrinkTime.setText(currentTime);
    }
	
	/**
	 *  Ch�bel (0.5 dl mit 0.05 Vol.%)
	 *  
	 * @param view
	 */
	public void chooseDrinkThree(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

		// aktuelle Zeit
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		String currentTime = dateFormat.format(new Date());
		
		// wenn kein Tag vorhanden ist wurde noch kein Getr�nk vorher getrunken, daher ist noch keine berechnung des Promillestandes m�glich
		if(imgbtn_currentDrink.getTag() != null) {
			int tagLastDrink = (Integer) imgbtn_currentDrink.getTag();
		
	        int size = getDrinkSize(tagLastDrink); //Gr�sse des Getr�nks in ml
	        double volume = getDrinkVolume(tagLastDrink); //Anteil an Alkohol in Getränk als dezimalzahl
	
	        if ("0.0".equals(txtvi_currentBloodAlcohol.getText())) {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromille(size, volume)) + "‰");
	        } else {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(size, volume)) + "‰");
	        }

		}
		
        imgbtn_currentDrink.setImageResource(R.drawable.beer);
        imgbtn_currentDrink.setTag(R.drawable.beer);
        
        // aktuelle Zeit des ausgew�hlten Getr�nks
        txtvi_currentDrinkTime.setText(currentTime);
	}
	
	/**
	 * Weinglass (0.2 dl mit 0.1 Vol.%)
	 * 
	 * @param view
	 */
	public void chooseDrinkFour(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);
		
		// aktuelle Zeit
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		String currentTime = dateFormat.format(new Date());
		
		// wenn kein Tag vorhanden ist wurde noch kein Getr�nk vorher getrunken, daher ist noch keine berechnung des Promillestandes m�glich
		if(imgbtn_currentDrink.getTag() != null) {
			int tagLastDrink = (Integer) imgbtn_currentDrink.getTag();
		
	        int size = getDrinkSize(tagLastDrink); //Gr�sse des Getr�nks in ml
	        double volume = getDrinkVolume(tagLastDrink); //Anteil an Alkohol in Getränk als dezimalzahl
	
	        if ("0.0".equals(txtvi_currentBloodAlcohol.getText())) {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromille(size, volume)) + "‰");
	        } else {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(size, volume)) + "‰");
	        }

		}
		
        imgbtn_currentDrink.setImageResource(R.drawable.wineglass);
        imgbtn_currentDrink.setTag(3);
        
        // aktuelle Zeit des ausgew�hlten Getr�nks
        txtvi_currentDrinkTime.setText(currentTime);
	}
	
	/**
	 * Gespritzten Weissen (0.2 dl mit 0.1 Vol.% + 0.2 Citro)
	 * 
	 * @param view
	 */
	public void chooseDrinkFive(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

		// aktuelle Zeit
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		String currentTime = dateFormat.format(new Date());
		
		// wenn kein Tag vorhanden ist wurde noch kein Getr�nk vorher getrunken, daher ist noch keine berechnung des Promillestandes m�glich
		if(imgbtn_currentDrink.getTag() != null) {
			int tagLastDrink = (Integer) imgbtn_currentDrink.getTag();
		
	        int size = getDrinkSize(tagLastDrink); //Gr�sse des Getr�nks in ml
	        double volume = getDrinkVolume(tagLastDrink); //Anteil an Alkohol in Getränk als dezimalzahl
	
	        if ("0.0".equals(txtvi_currentBloodAlcohol.getText())) {
	        	
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromille(size, volume)) + "‰");
	        } else {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(size, volume)) + "‰");
	        }

		}
		
        imgbtn_currentDrink.setImageResource(R.drawable.wineglass);
        imgbtn_currentDrink.setTag(4);
        
        // aktuelle Zeit des ausgew�hlten Getr�nks
        txtvi_currentDrinkTime.setText(currentTime);
	}
	
	/**
	 *  ()
	 * 
	 * @param view
	 */
	public void chooseDrinkSix(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

		// aktuelle Zeit
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		String currentTime = dateFormat.format(new Date());
		
		// wenn kein Tag vorhanden ist wurde noch kein Getr�nk vorher getrunken, daher ist noch keine berechnung des Promillestandes m�glich
		if(imgbtn_currentDrink.getTag() != null) {
			int tagLastDrink = (Integer) imgbtn_currentDrink.getTag();
		
	        int size = getDrinkSize(tagLastDrink); //Gr�sse des Getr�nks in ml
	        double volume = getDrinkVolume(tagLastDrink); //Anteil an Alkohol in Getränk als dezimalzahl
	
	        if ("0.0".equals(txtvi_currentBloodAlcohol.getText())) {
	        	
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromille(size, volume)) + "‰");
	        } else {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(size, volume)) + "‰");
	        }

		}
		
        imgbtn_currentDrink.setImageResource(R.drawable.wineglass);
        imgbtn_currentDrink.setTag(6);
        
        // aktuelle Zeit des ausgew�hlten Getr�nks
        txtvi_currentDrinkTime.setText(currentTime);
        
	}
	
	/**
	 * Cocktail ()
	 * 
	 * @param view
	 */
	public void chooseDrinkSeven(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

		// aktuelle Zeit
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		String currentTime = dateFormat.format(new Date());
		
		// wenn kein Tag vorhanden ist wurde noch kein Getr�nk vorher getrunken, daher ist noch keine berechnung des Promillestandes m�glich
		if(imgbtn_currentDrink.getTag() != null) {
			int tagLastDrink = (Integer) imgbtn_currentDrink.getTag();
		
	        int size = getDrinkSize(tagLastDrink); //Gr�sse des Getr�nks in ml
	        double volume = getDrinkVolume(tagLastDrink); //Anteil an Alkohol in Getränk als dezimalzahl
	
	        if ("0.0".equals(txtvi_currentBloodAlcohol.getText())) {
	        	
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromille(size, volume)) + "‰");
	        } else {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(size, volume)) + "‰");
	        }

		}
		
        imgbtn_currentDrink.setImageResource(R.drawable.coctail);
        imgbtn_currentDrink.setTag(7);
        
        // aktuelle Zeit des ausgew�hlten Getr�nks
        txtvi_currentDrinkTime.setText(currentTime);
        

	}
	
	/**
	 * 
	 * @param view
	 */
	public void chooseDrinkEight(View view) {
		ImageButton imgbtn_currentDrink = (ImageButton) findViewById(R.id.imgbtn_currentDrink);
		TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
		TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);

		// aktuelle Zeit
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
		String currentTime = dateFormat.format(new Date());
		
		// wenn kein Tag vorhanden ist wurde noch kein Getr�nk vorher getrunken, daher ist noch keine berechnung des Promillestandes m�glich
		if(imgbtn_currentDrink.getTag() != null) {
			int tagLastDrink = (Integer) imgbtn_currentDrink.getTag();
		
	        int size = getDrinkSize(tagLastDrink); //Gr�sse des Getr�nks in ml
	        double volume = getDrinkVolume(tagLastDrink); //Anteil an Alkohol in Getränk als dezimalzahl
	
	        if ("0.0".equals(txtvi_currentBloodAlcohol.getText())) {
	        	
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromille(size, volume)) + "‰");
	        } else {
	        	txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(size, volume)) + "‰");
	        }

		}
		
        imgbtn_currentDrink.setImageResource(R.drawable.coctail);
        imgbtn_currentDrink.setTag(8);
        
        // aktuelle Zeit des ausgew�hlten Getr�nks
        txtvi_currentDrinkTime.setText(currentTime);
	}
	
	/**
	 * Cocktail ()
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
        imgbtn_currentDrink.setImageResource(R.drawable.coctail);
        txtvi_currentBloodAlcohol.setText("0.0‰");
        txtvi_currentDrinkTime.setText("00:00");
	}

	/**
	 * 
	 * @param tagLastDrink
	 * @return
	 * Grösse in "cl" angeben
     */
	private int getDrinkSize (int tagLastDrink) {
		
		int drinkSize = 0;

		if (tagLastDrink == 1) {
			drinkSize = 33;
		} else if (tagLastDrink == 2) {
			drinkSize = 30;
		} else if (tagLastDrink == 3) {
			drinkSize = 50;
		} else if (tagLastDrink == 4) {
			drinkSize = 20;
		} else if (tagLastDrink == 5) {
			drinkSize = 20;
		} else if (tagLastDrink == 6) {
			
		} else if (tagLastDrink == 7) {
			
		} else if (tagLastDrink == 8) {
			
		} else if (tagLastDrink == 9) {
			
		}
		
		return drinkSize;
	}
	
	private double getDrinkVolume (int tagLastDrink) {
		double drinkVolume = 0;
		
		if (tagLastDrink == 1) {
			drinkVolume = 5;
		} else if (tagLastDrink == 2) {
			drinkVolume = 5;
		} else if (tagLastDrink == 3) {
			drinkVolume = 5;
		} else if (tagLastDrink == 4) {
			drinkVolume = 10;
		} else if (tagLastDrink == 5) {
			drinkVolume = 10;
		} else if (tagLastDrink == 6) {
			
		} else if (tagLastDrink == 7) {
			
		} else if (tagLastDrink == 8) {
			
		} else if (tagLastDrink == 9) {
			
		}
		
		return drinkVolume;
	}
	
    public void neuBerechnen (View view) {
        TextView txtvi_currentDrinkTime = (TextView)findViewById(R.id.txtvi_currentDrinkTime);
        TextView txtvi_currentBloodAlcohol = (TextView)findViewById(R.id.txtvi_currentBloodAlcohol);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yy");

        txtvi_currentBloodAlcohol.setText(String.format("%.1f", calcPromilleZeit(0, 0.0)) + "‰");
        txtvi_currentDrinkTime.setText(dateFormat.format(new Date()));
    }
	
    public double calcPromille (int drinkSize, double volume) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Evening.this);
        int gewicht = Integer.parseInt(prefs.getString("gewicht", ""));
        int sex = Integer.parseInt(prefs.getString("geschlecht", ""));

        double pure_alc;
        double alc_gramms;
        double wasseranteil; //Wasseranteil im Körper
        double promille;

        // 300ml (gr�sse des Getr�nks) * 0.05 (=5% durchschnittlicher Alkoholgehalt von Bier)
        pure_alc = drinkSize * volume;

        //Alkohol in Getränk (pure_alc) * spezifisches Gewicht von Alkohol (0.81)
        alc_gramms = pure_alc * 0.081;

        if (sex == 1) { //Falls männlich
            wasseranteil = gewicht * 0.7;
            promille = alc_gramms / wasseranteil;
            promille = promille * 0.85; //Resorptionsdefizit
        } else if (sex == 2) {//Falls weiblich
            wasseranteil = gewicht * 0.6;
            promille = alc_gramms / wasseranteil;
            promille = promille * 0.85; //Resorptionsdefizit
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

    public void takeSelfie() {
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        SharedPreferences.Editor prefEditor = getSharedPreferences("SelfieUri", Context.MODE_PRIVATE).edit();
        prefEditor.putString("LatestSelfiePath", fileUri.toString());
        prefEditor.commit();

        camIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        startActivityForResult(camIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }


}
