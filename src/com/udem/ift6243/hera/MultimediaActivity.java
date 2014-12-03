package com.udem.ift6243.hera;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher.ViewFactory;

import com.udem.ift6243.dao.SolutionDao;
import com.udem.ift6243.hera.GalleryImageAdapter;
import com.udem.ift6243.model.Solution;
import com.udem.ift6243.oracle.Oracle;
import com.udem.ift6243.utility.Constant;

public class MultimediaActivity extends Activity {
	
	
	int mFlipping = 1 ; 
	private MediaPlayer mPlayer = null;
	
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
				        MultimediaActivity.this);

				alertDialog.setTitle("Succes");

				alertDialog.setMessage("Bravo, vous avez surmonté votre stress ");

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
				        MultimediaActivity.this);

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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multimedia);
		playSound(R.raw.test);
		
  
                ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipp);
 
                if(mFlipping==1){
                    /** Start Flipping */
                    flipper.startFlipping();

                }
                else{
                    /** Stop Flipping */
                    flipper.stopFlipping();
                    mFlipping=0;

                }
                
      		  Bundle extras = getIntent().getExtras();
    		  final int id = extras.getInt("solutionID");
    		  
    	      SolutionDao s = new SolutionDao(this);
    	      Solution solution = s.getSolution(id);
    	      
      	      Double duration = (solution.getDuration());
    	      long delai = duration.longValue()*60000;
    	      
    	      mHandler.postDelayed(mOracleFeedback, delai);

        };
        
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
