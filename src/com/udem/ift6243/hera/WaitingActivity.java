package com.udem.ift6243.hera;

import java.io.IOException;
import java.io.InputStream;

import com.udem.ift6243.dao.SolutionDao;
import com.udem.ift6243.dao.UserDao;
import com.udem.ift6243.factory.HeraContextFactory;
import com.udem.ift6243.model.HeraContext;
import com.udem.ift6243.model.Solution;
import com.udem.ift6243.model.User;
import com.udem.ift6243.oracle.Oracle;
import com.udem.ift6243.sensor.Sensor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class WaitingActivity extends Activity {
	
	private static String packageName;
	
	public static String getApplicationPackageName()
	{
		return WaitingActivity.packageName;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waiting);
		
	      UserDao u = new UserDao(this);
	      User user = u.getUser();
		
	      TextView NameDisplay = (TextView) findViewById(R.id.textView1);
	      NameDisplay.setText("Veuillez patienter "+user.getLastName()+" "+user.getFirstName()+", HERA analyse vos donnees.");
			
		//// INITIALIZE
      	if(!Oracle.getInstance().isStarting())
      	{
			WaitingActivity.packageName = getApplicationContext().getPackageName();
			Oracle.getInstance().setContext(getApplicationContext());
			Oracle.getInstance().setActivity(this);
			
			Sensor sensor = new Sensor();
				new Thread(sensor).start();
      	}
      	else
		{
			Oracle.getInstance().reset();
		}
		//// INITIALIZE
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.waiting, menu);
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
