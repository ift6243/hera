package com.udem.ift6243.hera;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MultimediaActivity extends Activity {
	 

	private MediaPlayer mPlayer = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multimedia);
 
		Button btn_sound = (Button) findViewById(R.id.play);
		btn_sound.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				playSound(R.raw.test);
			}
 
		});
	}
 
	@Override
	public void onPause() {
		super.onPause();
		if(mPlayer != null) {
			mPlayer.stop();
			mPlayer.release();
		}
	}
 
	private void playSound(int resId) {
		if(mPlayer != null) {
			mPlayer.stop();
			mPlayer.release();
		}
		mPlayer = MediaPlayer.create(this, resId);
		mPlayer.start();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.multimedia, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
