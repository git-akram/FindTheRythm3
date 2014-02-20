package com.mmm.findtherythm3;


import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;


import com.mmm.findtherythm3.conf.Instrument;

public class DrumsActivity extends Activity {

	MediaPlayer mMediaPlayer = new MediaPlayer();
	private Button soundButton;
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

}
