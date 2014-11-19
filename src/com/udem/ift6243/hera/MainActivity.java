package com.udem.ift6243.hera;



import com.udem.ift6243.dao.DatabaseHandler;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnForm;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnForm = (Button) findViewById(R.id.button1);
	    btnForm.setOnClickListener(this);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		// Create Database for the first run
//		SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean firstRun = p.getBoolean("preference_first_run", true);
//        if(firstRun)
//        {
        	DatabaseHandler db = null;
        	try  
            {
            	db = new DatabaseHandler(this);
            	db.createDatabase();
            }
            catch (Exception e)  
            {  
                e.printStackTrace();  
            }
            finally
            {
            	if(db != null) db.close();
            	
            	// modification des preferences
//            	p.edit().putBoolean("preference_first_run", false).commit(); 
            }
//        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(getApplicationContext(),UserFormActivity.class);
  	    startActivity(i);
	}
}
