package com.udem.ift6243.hera;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
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

import com.udem.ift6243.hera.GalleryImageAdapter;

public class MultimediaActivity extends Activity {
	
	
	   int mFlipping = 1 ; // Initially flipping is off
	   //Button mButton ;  Reference to button available in the layout to start and stop the flipper

	 
 
	private MediaPlayer mPlayer = null;
	/*	
	ImageView selectedImage;  
    private Integer[] mImageIds = {
               R.drawable.ic_launcher,
               R.drawable.ciel,
               R.drawable.cocotiers
    };
    
    private ImageSwitcher imageSwitcher;
    */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multimedia);
		playSound(R.raw.test);
		
        //OnClickListener listener = new OnClickListener() {
        	 
            //@Override
            //public void onClick(View v) {
                ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipp);
 
                if(mFlipping==1){
                    /** Start Flipping */
                    flipper.startFlipping();
                    //mFlipping=1;
                    //mButton.setText(R.string.str_btn_stop);
                }
                else{
                    /** Stop Flipping */
                    flipper.stopFlipping();
                    mFlipping=0;
                    //mButton.setText(R.string.str_btn_start);
                }
            //}
                
  		      TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
  		      stackBuilder.addParentStack(NotificationReceiverActivity.class);
        };
       
 
        /** Getting a reference to the button available in the resource */
        //mButton = (Button) findViewById(R.id.bouton);
 
        /** Setting click event listner for the button */
        //mButton.setOnClickListener(listener);
	 
		
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
