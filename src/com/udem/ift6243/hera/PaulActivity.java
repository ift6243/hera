package com.udem.ift6243.hera;

import com.udem.ift6243.dao.SolutionDao;
import com.udem.ift6243.factory.HeraContextFactory;
import com.udem.ift6243.model.HeraContext;
import com.udem.ift6243.model.Solution;
import com.udem.ift6243.oracle.Oracle;
import com.udem.ift6243.sensor.Sensor;
import com.udem.ift6243.utility.Constant;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class PaulActivity extends Activity
{
	static String packageName;
	
	public static String getApplicationPackageName()
	{
		return PaulActivity.packageName;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paul);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		
//		Log.e("Activity", String.valueOf(android.os.Process.myTid()));
		
		//// INITIALIZE
		// A DEPLACER POUR LA VERSION FINALE
		PaulActivity.packageName = getApplicationContext().getPackageName();
		
		Oracle.getInstance().setContext(getApplicationContext());
		Oracle.getInstance().setActivity(this);
		
		Sensor sensor = new Sensor();
			new Thread(sensor).start();
		//// INITIALIZE
			
		// TEST creation du heraContext
//		HeraContext heraContext = HeraContextFactory.fromContext(getApplicationContext(), Constant.STRESS_LEVEL_HIGH);
//		Log.e("text_context", heraContext.toString());
			
		// TEST
//		SolutionDao solutionDao = new SolutionDao(getApplicationContext());
//		Solution currentSolution = solutionDao.getSolution(Integer.valueOf(1));
//		Log.e("currentSolution", String.valueOf(currentSolution));
//		Solution result = Oracle.getInstance().feedback(currentSolution, Constant.STATE_ACCEPTED);
//		Log.e("STATE_ACCEPTED", String.valueOf(result == null));
//		
//		currentSolution = solutionDao.getSolution(Integer.valueOf(2));
//		Log.e("currentSolution", String.valueOf(currentSolution));
//		result = Oracle.getInstance().feedback(currentSolution, Constant.STATE_REFUSED);
//		Log.e("STATE_REFUSED", result.toString());
//		
//		currentSolution = solutionDao.getSolution(Integer.valueOf(3));
//		Log.e("currentSolution", String.valueOf(currentSolution));
//		result = Oracle.getInstance().feedback(currentSolution, Constant.STATE_TERMINATED);
//		Log.e("STATE_TERMINATED", String.valueOf(result));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paul, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_paul, container,
					false);
			return rootView;
		}
	}
}
