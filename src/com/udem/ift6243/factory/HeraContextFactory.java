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

public final class HeraContextFactory
{
	public static HeraContext fromContext(Context context)
	{
		Double latitude = null;
		Double longitude = null;
		
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE); 
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if(location != null)
		{
			latitude = location.getLatitude();
			longitude = location.getLongitude();
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
		
		// Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
		String date = sdf.format(currentDate.getTime());
		
		
		HeraContext heraContext = new HeraContext(null, latitude, longitude,
				dayPart, weekPart, yearPart, date);
		
		
		return heraContext;
	}
}
