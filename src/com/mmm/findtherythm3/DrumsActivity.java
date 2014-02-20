package com.mmm.findtherythm3;


import com.mmm.findtherythm3.conf.Instrument;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.RelativeLayout;

public class DrumsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drums);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.layoutDrum);
		rl.setBackgroundResource(Instrument.getInstance().getImageId());
		Log.i("drumactivity", Integer.toString(Instrument.getInstance().getImageId()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drums, menu);
		return true;
	}

}
