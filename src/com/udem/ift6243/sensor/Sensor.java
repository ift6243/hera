package com.udem.ift6243.sensor;

import java.util.ArrayList;

import com.udem.ift6243.utility.Constant;

import android.util.Log;

public class Sensor 
{
	public Sensor()
	{
		startCapturing();
	}
	
	private void startCapturing()
	{
		ReadEdaTask edaTask = 
				(ReadEdaTask) new ReadEdaTask().execute(Constant.SOURCE_DATA_EDA);
		
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
}
