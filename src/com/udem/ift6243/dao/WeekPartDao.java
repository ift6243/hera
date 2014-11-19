package com.udem.ift6243.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.udem.ift6243.model.WeekPart;
import com.udem.ift6243.sql.schema.WeekPartSchema;

public class WeekPartDao
{
	private Context context;
	
	public WeekPartDao(Context context)
	{
		this.context = context;
	}
	
	/**
	 * Get WeekPart from current day
	 * @param currentDay today
	 * @return WeekPart
	 */
	public WeekPart getWeekPart(Integer currentDay)
	{
		WeekPart weekPart = null;
		
		DatabaseHandler dbHandler = new DatabaseHandler(this.context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor cursor_week_part = null;
		
		db.beginTransaction();
		try
		{
			cursor_week_part = db.query(WeekPartSchema.TABLE_NAME, 
					null, 
					WeekPartSchema.TABLE_COL_START_DAY + " >= ? AND "
					+ WeekPartSchema.TABLE_COL_END_DAY + " <= ?", 
					new String[] { String.valueOf(currentDay.intValue()) }, 
					null, null, null, null);
			
			if (cursor_week_part != null && cursor_week_part.moveToLast())
			{
				Integer weekPartId = Integer.valueOf(cursor_week_part.getInt(0));
				String name = cursor_week_part.getString(1);
				Integer startDay = Integer.valueOf(cursor_week_part.getInt(2));
				Integer endDay = Integer.valueOf(cursor_week_part.getInt(3));
				
				weekPart = new WeekPart(weekPartId, name, startDay, endDay);
			}
			
			db.setTransactionSuccessful();
		}
		finally
		{
			db.endTransaction();
			db.close();
			if(cursor_week_part != null) cursor_week_part.close();
		}
		
		return weekPart;
	}
}
