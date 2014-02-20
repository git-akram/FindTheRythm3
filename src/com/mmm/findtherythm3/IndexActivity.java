package com.mmm.findtherythm3;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IndexActivity extends Activity {

	private Button startButton;
	private Button confButton;
	private Button quitButton;
	private Button Buttoneldrum; // R1
	private Button Buttonkendrum; // R2
	private Button Buttondrumdol; // L1
	private Button Buttondundrum; // L2
	
	
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
				Buttondrumdol = (Button) findViewById(R.id.drumdol_button);
				Buttondundrum = (Button) findViewById(R.id.dundrum_button);
				Buttoneldrum = (Button) findViewById(R.id.eldrum_button);
				Buttondundrum = (Button) findViewById(R.id.dundrum_button);
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
				
			}
			else if(button.getId() == R.id.dundrum_button) {
				
			}
			else if(button.getId() == R.id.eldrum_button) {
				
			}
			else if(button.getId() == R.id.dundrum_button) {
				
			}
			
		}
	};

}
