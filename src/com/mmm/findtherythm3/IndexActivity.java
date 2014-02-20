package com.mmm.findtherythm3;


import com.mmm.findtherythm3.conf.Instrument;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IndexActivity extends Activity {

	Instrument instrument = Instrument.getInstance();
	
	private Button startButton;
	private Button confButton;
	private Button quitButton;
	private Button buttoneldrum; // R1
	private Button buttonkendrum; // R2
	private Button buttondrumdol; // L1
	private Button buttondundrum; // L2
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
		// Initialiser les bouttons
		startButton = (Button) findViewById(R.id.buttonStart);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setContentView(R.layout.choice);
				buttondrumdol = (Button) findViewById(R.id.drumdol_button);
				buttondundrum = (Button) findViewById(R.id.dundrum_button);
				buttoneldrum = (Button) findViewById(R.id.eldrum_button);
				buttonkendrum = (Button) findViewById(R.id.kendrum_button);
				
				buttondrumdol.setOnClickListener(drumsButtonHandler);
				buttondundrum.setOnClickListener(drumsButtonHandler);
				buttoneldrum.setOnClickListener(drumsButtonHandler);
				buttonkendrum.setOnClickListener(drumsButtonHandler);
				
				setDrumsButton(buttondrumdol);
				setDrumsButton(buttondundrum);
				setDrumsButton(buttoneldrum);
				setDrumsButton(buttonkendrum);
				
			}
		});
		confButton = (Button) findViewById(R.id.buttonConf);
		quitButton = (Button) findViewById(R.id.buttonQuit);
		quitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IndexActivity.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
		return true;
	}
	
	public void setDrumsButton(Button button) {
		button.setVisibility(View.VISIBLE);
		button.setBackgroundColor(Color.TRANSPARENT);
	}
	
	OnClickListener drumsButtonHandler = new OnClickListener() {
		@Override
		public void onClick(View button) {
			if(button.getId() == R.id.drumdol_button) {
				instrument.setSonId(R.raw.drumdol);
				instrument.setImageId(R.drawable.leftup);
				Log.i("Index Activity", "drumdol_button clicked");
			}
			else if(button.getId() == R.id.dundrum_button) {
				instrument.setSonId(R.raw.dundrum);
				instrument.setImageId(R.drawable.leftdown);
				Log.i("Index Activity", "dundrum_button clicked");
			}
			else if(button.getId() == R.id.eldrum_button) {
				instrument.setSonId(R.raw.eldrum);
				instrument.setImageId(R.drawable.rightup);
				Log.i("Index Activity", "eldrum_button clicked");
			}
			else if(button.getId() == R.id.kendrum_button) {
				instrument.setSonId(R.raw.kendrum);
				instrument.setImageId(R.drawable.rightdown);
				Log.i("Index Activity", "kendrum_button clicked");
			}
			Intent intent = new Intent(IndexActivity.this, DrumsActivity.class);
			startActivityForResult(intent,0);
			
		}
	};
	@Override
	public void onBackPressed() {
	   Log.d("CDA", "onBackPressed Called");
	   startActivity(new Intent(IndexActivity.this , IndexActivity.class));
	}

}
