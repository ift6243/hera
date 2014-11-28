package com.udem.ift6243.sensor;

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
		
		Thread thread = new Thread()
		{
		    @Override
		    public void run() {
		        try
		        {
		            while(true)
		            {
		                Integer currentStressLevel = edaTask.getStressLevel();
		                
		                if(currentStressLevel != null 
		                		&& currentStressLevel >= Constant.STRESS_LEVEL_LOW)
		                {
//		                	Log.e("Stress Level", currentStressLevel.toString());
		                	Oracle.getInstance().start();
		                }
		                
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
