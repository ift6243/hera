package com.udem.ift6243.hera;

import com.udem.ift6243.dao.SolutionDao;
import com.udem.ift6243.model.Solution;
import com.udem.ift6243.oracle.Oracle;
import com.udem.ift6243.utility.Constant;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.Date;
import java.util.Timer;

public class SolutionActivity extends Activity {
	
	private final Handler mHandler = new Handler();
	
	private Runnable mOracleFeedback = new Runnable() {
        @Override
        public void run() {
            Oracle.getInstance().stop();
            Intent Intent = new Intent(SolutionActivity.this, WaitingActivity.class);
	    	startActivity(Intent);
            
        }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solution);
		
		  Bundle extras = getIntent().getExtras();
		  final int id = extras.getInt("solutionID");
		  
	      SolutionDao s = new SolutionDao(this);
	      Solution solution = s.getSolution(id);
	      
	      Integer category_id = solution.getCategoryId();
	      
	      if(category_id == Constant.SOLUTION_CATEGORY_MULTIMEDIA){
	    	  Intent MulIntent = new Intent(this, MultimediaActivity.class);
	    	  startActivity(MulIntent);
	    		}

	      TextView solutionDisplay = (TextView) findViewById(R.id.textview_solution);
	      solutionDisplay.setText("--> "+solution.getDescription());
	      
	      Double duration = (solution.getDuration());
	      long delai = duration.longValue()*60000;
	      
	      mHandler.postDelayed(mOracleFeedback, delai);

	      
		  
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
