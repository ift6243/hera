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
import android.widget.ViewSwitcher.ViewFactory;

import com.udem.ift6243.hera.GalleryImageAdapter;

public class MultimediaActivity extends Activity {
	 
 
	private MediaPlayer mPlayer = null;
	
	ImageView selectedImage;  
    private Integer[] mImageIds = {
               R.drawable.ic_launcher,
               R.drawable.ciel,
               R.drawable.cocotiers
    };
    
    private ImageSwitcher imageSwitcher;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multimedia);
		playSound(R.raw.test);
		
	      TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
	      stackBuilder.addParentStack(SolutionActivity.class);
		
		imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher1);

	    imageSwitcher.setFactory(new ViewFactory() {

	   @Override
	   public View makeView() {
	      ImageView myView = new ImageView(getApplicationContext());
	      myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	      myView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.
	      FILL_PARENT,LayoutParams.FILL_PARENT));
	      return myView;
	       }

	   });

	   }
	
	   public void next(View view){
		      Toast.makeText(getApplicationContext(), "Next Image", 
		      Toast.LENGTH_LONG).show();
		      Animation in = AnimationUtils.loadAnimation(this,
		      android.R.anim.slide_in_left);
		      Animation out = AnimationUtils.loadAnimation(this,
		      android.R.anim.slide_out_right);
		      imageSwitcher.setInAnimation(in);
		      imageSwitcher.setOutAnimation(out);
		      imageSwitcher.setImageResource(R.drawable.ic_launcher);
		   }
		   public void previous(View view){
		      Toast.makeText(getApplicationContext(), "previous Image", 
		      Toast.LENGTH_LONG).show();
		      Animation in = AnimationUtils.loadAnimation(this,
		      android.R.anim.slide_out_right);
		      Animation out = AnimationUtils.loadAnimation(this,
		      android.R.anim.slide_in_left);
		      imageSwitcher.setInAnimation(out);
		      imageSwitcher.setOutAnimation(in);
		      imageSwitcher.setImageResource(R.drawable.ic_launcher);
		   }
		
		//Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        //selectedImage=(ImageView)findViewById(R.id.imageView1);
        //gallery.setSpacing(1);
        //gallery.setAdapter(new GalleryImageAdapter(this));
        
        // clicklistener for Gallery
        //gallery.setOnItemClickListener(new OnItemClickListener() {
          //  public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(MultimediaActivity.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();
                // show the selected Image
            //    selectedImage.setImageResource(mImageIds[position]);
            //}

		//	public void onClick(View v) {
				// TODO Auto-generated method stub
				
		//	}
       // }); 	
 
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
