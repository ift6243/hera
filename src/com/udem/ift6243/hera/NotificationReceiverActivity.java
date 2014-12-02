package com.udem.ift6243.hera;

import com.udem.ift6243.dao.*;
import com.udem.ift6243.factory.HeraContextFactory;
import com.udem.ift6243.model.*;
import com.udem.ift6243.oracle.Oracle;
import com.udem.ift6243.sensor.Sensor;
import com.udem.ift6243.sql.schema.*;
import com.udem.ift6243.utility.Constant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class NotificationReceiverActivity extends Activity {
	
	//private Solution s;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_receiver);
		
		  Bundle extras = getIntent().getExtras();
		  final int id = extras.getInt("notificationID");
		  	      
	      SolutionDao s = new SolutionDao(this);
	      final Solution solution = s.getSolution(id);

	      TextView solutionDisplay = (TextView) findViewById(R.id.solution);
	      solutionDisplay.setText("Nous vous proposons la solution suivante : \n"+solution.getName());

		  final Button buttonacc = (Button) findViewById(R.id.accepter);
	      buttonacc.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	          Intent SolutionIntent = new Intent(NotificationReceiverActivity.this, SolutionActivity.class);
	          
		      Bundle dataBundle = new Bundle();
		      dataBundle.putInt("solutionID",(int)id);		      
			  SolutionIntent.putExtras(dataBundle);
			  
			  Oracle.getInstance().feedback(solution, Constant.STATE_ACCEPTED);
			  
			  startActivity(SolutionIntent);
	          }
	        });
	      
	      final Button buttonrefus = (Button) findViewById(R.id.refus);
	      buttonrefus.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	          Intent SolutionRefusIntent = new Intent(NotificationReceiverActivity.this, NotificationReceiverActivity.class);
			
	          Solution newsolution =Oracle.getInstance().feedback(solution, Constant.STATE_REFUSED);
	          ;
		      Bundle dataBundle = new Bundle();
		      dataBundle.putInt("notificationID",(int)newsolution.getId());		      
		      SolutionRefusIntent.putExtras(dataBundle);
			  
			  startActivity(SolutionRefusIntent);
	          }
	        });
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification_receiver, menu);
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
