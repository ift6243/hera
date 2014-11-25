package com.udem.ift6243.hera;

import com.udem.ift6243.dao.SolutionDao;
import com.udem.ift6243.model.Solution;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;


public class SolutionActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solution);
		
		  Bundle extras = getIntent().getExtras();
		  final int id = extras.getInt("solutionID");
		  
	      SolutionDao s = new SolutionDao(this);
	      Solution solution = s.getSolution(id);
	      
	      Solution sol = new Solution(id, id, null, null);
	      Integer category_id = sol.getCategoryId();
	      
	      if(category_id == 5){
	    	  Intent MulIntent = new Intent(this, MultimediaActivity.class);
	    	  startActivity(MulIntent);
	    		}


	      TextView solutionDisplay = (TextView) findViewById(R.id.textview_solution);
	      solutionDisplay.setText("--> "+solution.getDescription());

		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.solution, menu);
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
