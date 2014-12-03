package com.udem.ift6243.hera;

import com.udem.ift6243.dao.SolutionDao;
import com.udem.ift6243.model.Solution;
import com.udem.ift6243.oracle.Oracle;
import com.udem.ift6243.utility.Constant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class SolutionActivity extends Activity {
	
	private final Handler mHandler = new Handler();

	private Runnable mOracleFeedback = new Runnable() {
        @SuppressWarnings("deprecation")
		@Override
        public void run() {
  		  Bundle extras = getIntent().getExtras();
  		  final int id = extras.getInt("solutionID");
          SolutionDao s = new SolutionDao(getApplicationContext());
	      final Solution solution = s.getSolution(id);
	      
		  if(Oracle.getInstance().feedback(solution, Constant.STATE_TERMINATED)== null){
			  
			  AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				        SolutionActivity.this);

				alertDialog.setTitle("Succes");

				alertDialog.setMessage("Bravo, vous avez surmont� votre stress ");

				alertDialog.setIcon(android.R.drawable.btn_star);

				alertDialog.setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int which) {
				    	
				    	Intent i = new Intent(getApplicationContext(), WaitingActivity.class);
				    	startActivity(i);

				    }
				});
				alertDialog.setNegativeButton("Quitter",
				        new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int which) {
				            	android.os.Process.killProcess(android.os.Process.myPid());
				            }
				        });
				alertDialog.show();
		  }
		  else {
			  AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				        SolutionActivity.this);

				alertDialog.setTitle("Echec");

				alertDialog.setMessage("Essayez une autre solution");

				alertDialog.setIcon(android.R.drawable.ic_delete);
				 
				// Le premier bouton "Oui" 
				alertDialog.setPositiveButton("Continuer",
				        new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int which) {
						      
				              Intent i = new Intent(getApplicationContext(), NotificationReceiverActivity.class);

				              Solution newsolution =Oracle.getInstance().feedback(solution, Constant.STATE_REFUSED);
					          
						      Bundle dataBundle = new Bundle();
						      dataBundle.putInt("notificationID",(int)newsolution.getId());		      
						      i.putExtras(dataBundle);
						      
						      startActivity(i);
				            }
				        });
				
				alertDialog.setNegativeButton("Quitter",
				        new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int which) {
				            	android.os.Process.killProcess(android.os.Process.myPid());
				            }
				        });
				// Affiche la boite du dialogue
				alertDialog.show();
		  }
            
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


	      TextView solutionDisplay = (TextView) findViewById(R.id.textview_solution);
	      solutionDisplay.setText(solution.getDescription());
	      
	      
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
