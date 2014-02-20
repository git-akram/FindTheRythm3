package com.mmm.findtherythm3;


import com.mmm.findtherythm3.conf.Instrument;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class FreeDrumsActivity extends Activity implements SensorEventListener {

	MediaPlayer mMediaPlayer = new MediaPlayer();
	SensorManager mSensorManager;
	Sensor mAccelerometer;
	private float  mLastX ,mLastY, mLastZ;
	private boolean mInitialized ;
	private final float NOISE_etat = (float) 15.0;
	private final float NOISE_song = (float) 2.0;
	private static final String TAG = "MainActivity";
	private enum Etat {centre, droite, gauche, haut} ;
	private Etat etat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_free_drums);
		//Declarer le manager des sensor
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Instancier l'accéléromètre
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        //Init 
        mInitialized = false;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.free_drums, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    mSensorManager.registerListener((SensorEventListener) this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    mSensorManager.unregisterListener(this);
	}
	  
	public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
			
	}
	public void onSensorChanged(SensorEvent event) {
	     
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			//Log.i(TAG, "New move x "+x+" y "+y+" z "+z);
			
			if (!mInitialized) {
				mLastX = x;
				mLastY = y;
				mLastZ = z;
				mInitialized = true;
				etat = Etat.centre;
			}
			else {
				float deltaX = x - mLastX;
				float deltaY = y - mLastY;
				
				mLastX = x;
				mLastY = y;
				mLastZ = z;
						
				if (Math.abs(deltaX) < NOISE_song) deltaX = (float)0.0;
				if (Math.abs(deltaY) < NOISE_song) deltaY = (float)0.0;
				
				if(deltaX == 0 && deltaY == 0)
					return;
				
				if (Math.abs(deltaX) < NOISE_etat) deltaX = (float)0.0;
				if (Math.abs(deltaY) < NOISE_etat) deltaY = (float)0.0;
				
				if(deltaX == 0 && deltaY == 0)
				{
					Log.i(TAG, "Etat " + etat);
					chooseSound(etat);
					return;
				}
				
				if(etat == Etat.centre){
					if (Math.abs(deltaY) > Math.abs(deltaX)) {
						etat = Etat.haut;	
						chooseSound(etat);
						Log.i(TAG, "Etat "+etat);
						etat = Etat.centre;
					} 
					else{
						if(deltaX > 0){
							etat = Etat.gauche;
							chooseSound(etat);
							Log.i(TAG, "Etat "+etat);
						}
						else{
							etat = Etat.droite;
							chooseSound(etat);
							Log.i(TAG, "Etat "+etat);
						}							
					}
									
				}
				else if(etat == Etat.droite){
					if(deltaX < 0) {
						etat = Etat.gauche;
						chooseSound(etat);
					}
				}
				else if(etat == Etat.gauche)
					if(deltaX > 0) {
						etat = Etat.droite;	
						chooseSound(etat);
					}
		}	        
	}
		
	@Override
	public void onBackPressed() {
		Log.d("CDA", "onBackPressed Called");
		startActivity(new Intent(FreeDrumsActivity.this , IndexActivity.class));
	}
	
	public void playSound(int soundId) {
		if(!mMediaPlayer.isPlaying()) {
			mMediaPlayer = MediaPlayer.create(this,soundId);
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.setLooping(false);
			mMediaPlayer.setVolume(100, 100);
			mMediaPlayer.start();
		}
		else {
			mMediaPlayer.stop();
		}
	}
	
	public void chooseSound(Etat etat) {
		if(etat == Etat.centre) {
			playSound(R.raw.rightdown);
		}
		else if(etat == Etat.droite) {
			playSound(R.raw.rightup);
		}
		else if(etat == Etat.gauche) {
			playSound(R.raw.leftup);
			mMediaPlayer.seekTo(1000);
		}
		else if(etat == Etat.haut) {
			playSound(R.raw.leftdown);
		}
	}
}
