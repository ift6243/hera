package com.udem.ift6243.sensor;

import java.util.ArrayList;

import com.udem.ift6243.utility.Constant;

import android.util.Log;

public class Sensor 
{
	private final ReadEdaTask edaTask = (ReadEdaTask) new ReadEdaTask().execute(Constant.SOURCE_DATA_EDA);
	
	public Sensor()
	{
		startCapturing();
		startListening();
	}
	
	private void startCapturing()
	{
		try	{
			String mesures = edaTask.get();

			ArrayList<Double> d = edaTask.getEdaList();
			Log.e("ValeurTest", d.get(0).toString());
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void startListening()
	{
		Thread thread = new Thread()
		{
		    @Override
		    public void run() {
		        try {
		            while(true)
		            {
		                Integer currentStressLevel = edaTask.getStressLevel();
		                
		                Log.e("Stress Level", currentStressLevel.toString());
		                
		                Thread.sleep(1000);
		            }
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		};

		thread.start();
	}
}
