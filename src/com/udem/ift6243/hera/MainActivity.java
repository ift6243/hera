package com.udem.ift6243.hera;

//import com.example.notificationdemo.R;
import com.udem.ift6243.dao.DatabaseHandler;
import com.udem.ift6243.dao.UserDao;
import com.udem.ift6243.model.User;
import com.udem.ift6243.oracle.Oracle;

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
import android.widget.Button;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Button paulBtn = (Button) findViewById(R.id.paul);
	      paulBtn.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View v) {
	    			Intent intent = new Intent(MainActivity.this, PaulActivity.class);
	    			startActivity(intent);
	    			}
	      });
	      
	      Button imeneBtn = (Button) findViewById(R.id.imene);
	      imeneBtn.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View v) {
	    			Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
	    			startActivity(intent);
	    			}
	      });
	      
	      Button reneBtn = (Button) findViewById(R.id.rene);
	      reneBtn.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View v) {
	    			Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
	    			startActivity(intent);
	    			}
	      });
		
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
 //           	p.edit().putBoolean("preference_first_run", false).commit(); 
            }
//        }

  	    
        UserDao userDao = new UserDao(getApplicationContext());
        final User user = userDao.getUser();
        
		Button starthera = (Button) findViewById(R.id.starthera);
		starthera.setOnClickListener(new View.OnClickListener() {
	    	  public void onClick(View v) {
				  if(user == null)
				  {
					Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
					startActivity(intent);
				}
				  else{
					Oracle.getInstance().start();
					Intent intent = new Intent(MainActivity.this, WaitingActivity.class);
					startActivity(intent); 
				  }
	    	  }
		});
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
}
