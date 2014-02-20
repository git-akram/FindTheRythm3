package com.mmm.findtherythm3;
import android.app.Activity;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.mmm.findtherythm3.conf.Instrument;


	public class DrumsActivity extends  Activity implements SensorEventListener{
	MediaPlayer mMediaPlayer = new MediaPlayer();
	private Button soundButton;
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
		setContentView(R.layout.activity_drums);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.layoutDrum);
		rl.setBackgroundResource(Instrument.getInstance().getImageId());
		Log.i("drumactivity", Integer.toString(Instrument.getInstance().getSonId()));
		configSound();
		soundButton = (Button) findViewById(R.id.button1);
		soundButton.setOnClickListener(chooseSound);
		
		Log.i("drumactivity", Integer.toString(Instrument.getInstance().getSonId()));
		//Declarer le manager des sensor
				mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		        // Instancier l'accéléromètre
		        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		        
		        //Init 
		        mInitialized = false;
	}
	
	OnClickListener chooseSound = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mMediaPlayer.start();
		}

				};
				
	public void configSound(){
		mMediaPlayer = MediaPlayer.create(this,Instrument.getInstance().getSonId());
		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mMediaPlayer.setLooping(false);
		mMediaPlayer.setVolume(100, 100);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drums, menu);
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
				Log.i(TAG, "Etat ... " + etat);
				return;
			}
			
			if(etat == Etat.centre){
				if (Math.abs(deltaY) > Math.abs(deltaX)) {
					etat = Etat.haut;	
					Log.i(TAG, "Etat "+etat);
					etat = Etat.centre;
				} 
				else{
					if(deltaX > 0){
						etat = Etat.gauche;
						Log.i(TAG, "Etat "+etat);
					}
					else if(deltaX < 0){
						etat = Etat.droite;
						Log.i(TAG, "Etat "+etat);
					}							
				}
								
			}
			else if(etat == Etat.droite){
				if(deltaX < 0)
					etat = Etat.centre;
			}
			else if(etat == Etat.gauche){
				if(deltaX > 0)
					etat = Etat.centre;			
		 }
	}
	}	
	@Override
	public void onBackPressed() {
	   Log.d("CDA", "onBackPressed Called");
	   startActivity(new Intent(DrumsActivity.this , IndexActivity.class));
	}

}
