package com.udem.ift6243.hera;

import com.udem.ift6243.dao.*;
import com.udem.ift6243.model.*;
import com.udem.ift6243.sql.schema.*;

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
		  
		  //int data = getIntent().getExtras().getInt("notificationId");
		  //System.out.print(+id);
	      //String dataS = getIntent().getExtras().getString("solution");
	      
	      //TextView solutionDisplay = (TextView) findViewById(R.id.solution);
	      //solutionDisplay.setText("La solution "+id);
	      
	      SolutionDao s = new SolutionDao(this);
	      Solution solution = s.getSolution(id);

	      TextView solutionDisplay = (TextView) findViewById(R.id.solution);
	      solutionDisplay.setText("Nous vous proposons la solution suivante : \n"+solution.getName());

	      /*Solution printing in a ListView
	            
	      Solution s = com.udem.ift6243.dao.SolutionDao.getSolution(id);
	      SolutionDao s = new SolutionDao(this);
	      Cursor cursor = s.getSolution(id);
	      
	      String[] columns = new String[] {
	    		  	SolutionSchema.TABLE_COL_NAME,
	    		  	SolutionSchema.TABLE_COL_DESCRIPTION
	    		  };
	      int[] to = new int[] { 
	    		    R.id.name,
	    		    R.id.description
	    		  };
	      
	      SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(
	    		    this, R.layout.activity_list_row, 
	    		    cursor, 
	    		    columns, 
	    		    to,
	    		    0);
	    		 
	    		  ListView listView = (ListView) findViewById(R.id.listView1);
	    		  // Assign adapter to ListView
	    		  listView.setAdapter(dataAdapter);	
	    		  */	 
	      
			final Button buttonsuiv = (Button) findViewById(R.id.accepter);
	        buttonsuiv.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	          Intent SolutionIntent = new Intent(NotificationReceiverActivity.this, SolutionActivity.class);
	          
		      Bundle dataBundle = new Bundle();
		      dataBundle.putInt("solutionID",(int)id);		      
			  SolutionIntent.putExtras(dataBundle);
			  
			  startActivity(SolutionIntent);
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
