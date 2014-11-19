package com.udem.ift6243.sensor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.udem.ift6243.utility.Constant;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

public class ReadEdaTask extends AsyncTask<String, Integer, String>
{
	private ArrayList<Double> eda;
	private ArrayList<Double> temperature;
	private static int lastLineNumber = -1;
	private String url;
	
	public ReadEdaTask()
	{
		eda = new ArrayList<Double>();
		temperature = new ArrayList<Double>();
	}
	
    // Expected : Only 1 url
    protected String doInBackground(String... urls)
    {
    	this.url = urls[0];
    	StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(this.url);
		
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			
			if (statusCode == 200)
			{
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				
				String line;
				boolean record = false;
				int i = 0;
				while ((line = reader.readLine()) != null)
				{					
					if(record && i > ReadEdaTask.lastLineNumber)
					{
						String[] split = line.split(",");
						if(split.length == 6 
								&& Double.valueOf(split[5]) > 0.0) // temperature
						{
							// All data
							builder.append(line);
							
							// EDA & Temperature
							temperature.add(Double.valueOf(split[4]));
							eda.add(Double.valueOf(split[5]));
							
							// FOR TESTING / Development : Sleep 100ms
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
//							Log.e("i", String.valueOf(i));
//							Log.e("lastLineNumber", String.valueOf(ReadEdaTask.lastLineNumber));
							Log.e("newValue", String.valueOf(eda.get(eda.size()-1)));
							
							ReadEdaTask.lastLineNumber++;
							i++;
						}
					}

					if(line.startsWith("-----------"))
					{
						record = true;
					}
				}
			}
			else
			{
				Log.e(ReadEdaTask.class.toString(), "Failed to download json file");
			}
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
    }
    
    public ArrayList<Double> getEdaList()
	{
		return this.eda;
	}
    
    public ArrayList<Double> getTemperatureList()
	{
		return this.temperature;
	}
    
    public Integer getStressLevel()
    {
    	Integer returnedValue = null;
    	
    	int firstIndex = (this.eda.size() < 100) ? 0 : (this.eda.size() - 100);
    	int lastIndex = (this.eda.size() - 1);
    	
    	double startValue = ((this.eda.get(firstIndex).doubleValue() 
    			+ this.eda.get(firstIndex+1).doubleValue() 
    			+ this.eda.get(firstIndex+2).doubleValue()) / 3);
    	double endValue = ((this.eda.get(lastIndex).doubleValue() 
    			+ this.eda.get(lastIndex+1).doubleValue() 
    			+ this.eda.get(lastIndex+2).doubleValue()) / 3);
    	
    	Double min = Collections.min(this.eda);
    	Double max = Collections.max(this.eda);
    	
    	if(endValue - startValue <= 0)
    	{
    		returnedValue = Constant.STRESS_LEVEL_NEGATIVE_OR_CONSTANT;
    	}
    	else if(min * Constant.STRESS_VARIATION_ALERT < max)
    	{
    		returnedValue = Constant.STRESS_LEVEL_HIGH;
    	}
    	else
    	{
    		returnedValue = Constant.STRESS_LEVEL_LOW;
    	}
    	
    	return returnedValue;
    }

    protected void onprogressUpdate(Integer... progress)
    {
    	
    }

    protected void onPostExecute(String result)
    {
    	final String url = this.url;
    	
    	new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new ReadEdaTask().execute(url);
            }
        }, 10*1000);
    }
}
