package com.udem.ift6243.factory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.udem.ift6243.dao.DayPartDao;
import com.udem.ift6243.dao.WeekPartDao;
import com.udem.ift6243.dao.YearPartDao;
import com.udem.ift6243.model.HeraContext;
import com.udem.ift6243.utility.Constant;

public final class HeraContextFactory
{
	public static HeraContext fromContext(Context context, Integer stressLevel) throws Exception
	{
		Double latitude = null;
		Double longitude = null;
		
		String locationService = Context.LOCATION_SERVICE;
		LocationManager locationManager = (LocationManager) context.getSystemService(locationService);
		
		String provider = LocationManager.GPS_PROVIDER;
		Location location = locationManager.getLastKnownLocation(provider);
		
		if(location == null)
		{
			provider = LocationManager.NETWORK_PROVIDER;
			location = locationManager.getLastKnownLocation(provider);
		}

		if(location != null)
		{
			latitude = Double.valueOf(location.getLatitude());
			longitude = Double.valueOf(location.getLongitude());
		}
		
		// Init calendar
		Calendar currentDate = Calendar.getInstance();
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);
		
		// Day Part
		Integer currentHour = Integer.valueOf(currentDate.get(Calendar.HOUR_OF_DAY));		
		DayPartDao dayPartDao = new DayPartDao(context);
		Integer dayPart = dayPartDao.getDayPart(currentHour).getId();
		
		// Week Part
		Integer currentDay = Integer.valueOf(currentDate.get(Calendar.DAY_OF_WEEK));
		WeekPartDao weekPartDao = new WeekPartDao(context);
		Integer weekPart = weekPartDao.getWeekPart(currentDay).getId();
		
		// Year Part
		String monthAndDay = currentDate.get(Calendar.MONTH) + "-" + currentDate.get(Calendar.DAY_OF_MONTH); 
		YearPartDao yearPartDao = new YearPartDao(context);
		Integer yearPart = yearPartDao.getYearPart(monthAndDay).getId();
		
		// Check stress level
		if(stressLevel < Constant.STRESS_LEVEL_LOW)
		{
			throw new Exception("Incorrect value of stress");
		}
		
		// Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
		String date = sdf.format(currentDate.getTime());
		
		HeraContext heraContext = new HeraContext(null, latitude, longitude,
				dayPart, weekPart, yearPart, stressLevel, date);
		
		
		return heraContext;
	}
}
