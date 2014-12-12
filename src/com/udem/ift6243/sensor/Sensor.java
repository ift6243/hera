package com.udem.ift6243.sensor;

import android.util.Log;

import com.udem.ift6243.oracle.Oracle;
import com.udem.ift6243.utility.Constant;

public class Sensor implements Runnable
{
	private ReadEdaTask edaTask;
	
	public Sensor()
	{
	}
	
	private void startCapturing()
	{
		try
		{
			this.edaTask.execute(Constant.SOURCE_DATA_EDA);
			
//			String mesures = edaTask.get();
//
//			ArrayList<Double> d = edaTask.getEdaList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void startListening()
	{
		final ReadEdaTask edaTask = this.edaTask;
		
		Oracle.getInstance().start(edaTask);
		
		Thread thread = new Thread()
		{
		    @Override
		    public void run() {
		        try
		        {
		            while(true)
		            {
		                Integer currentStressLevel = edaTask.getStressLevel();
		                
		                // TODO : Moins de sensibilite en remplacant >= par > 
		                // (detection des stress importants seulement)
		                if(currentStressLevel != null 
		                		&& currentStressLevel >= Constant.STRESS_LEVEL_LOW)
		                {
		                	Log.e("currentStressLevel", currentStressLevel.toString());
		                	Oracle.getInstance().notifyUser(currentStressLevel);
		                }
//		                else if(currentStressLevel != null)
//		                {
//		                	Log.e("currentStressLevel", currentStressLevel.toString());
//		                }
		                Thread.sleep(1000);
		            }
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		};

		thread.start();
	}

	@Override
	public void run()
	{
//		Log.e("Sensor", String.valueOf(android.os.Process.myTid()));

		this.edaTask = (ReadEdaTask) new ReadEdaTask();
		
		startListening();
		startCapturing();
	}
}
