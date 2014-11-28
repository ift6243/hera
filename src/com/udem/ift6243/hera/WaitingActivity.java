package com.udem.ift6243.hera;

import java.io.IOException;
import java.io.InputStream;

import com.udem.ift6243.factory.HeraContextFactory;
import com.udem.ift6243.model.HeraContext;
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
/* Class for the Gif Image
 
 
class GifMovieView extends View{

    private Movie mMovie; 
    
    public GifMovieView(Context context, InputStream stream) {   
    	super(context);        
    	InputStream mStream = stream;      
    	mMovie = Movie.decodeStream(mStream);   
    } 
    @Override protected void onDraw(Canvas canvas) { 
    	canvas.drawColor(Color.TRANSPARENT); 
    	super.onDraw(canvas);
    	final long now = SystemClock.uptimeMillis();
    	long mMoviestart = 0;
		if (mMoviestart == 0) { 
    		mMoviestart = now;
    		} 
    	final int relTime = (int)((now - mMoviestart) % mMovie.duration());
    	mMovie.setTime(relTime);
    	mMovie.draw(canvas, 10, 10); 
    	this.invalidate(); } 
}
*/
public class WaitingActivity extends Activity {
private static String packageName;
	
	public static String getApplicationPackageName()
	{
		return PaulActivity.packageName;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waiting);
		
		//// INITIALIZE
		PaulActivity.packageName = getApplicationContext().getPackageName();
		Oracle.getInstance().setContext(getApplicationContext());
		Oracle.getInstance().setActivity(this);
		
		Sensor sensor = new Sensor();
		new Thread(sensor).start();
		//// INITIALIZE
		
/*	 GifImage class call
	
		InputStream stream = null;
		stream =getResources().openRawResource(R.raw.radar);
		GifMovieView view = new GifMovieView(this, stream);
		setContentView(view);
*/
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
