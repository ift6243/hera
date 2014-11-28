package com.udem.ift6243.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.udem.ift6243.model.DayPart;
import com.udem.ift6243.sql.schema.DayPartSchema;

public class DayPartDao
{
	private Context context;
	
	public DayPartDao(Context context)
	{
		this.context = context;
	}
	
	/**
	 * Get DayPart from current hour
	 * @param currentHour Heure courante
	 * @return
	 * @throws Exception 
	 */
	public DayPart getDayPart(Integer currentHour)
	{
		DayPart dayPart = null;
		
		DatabaseHandler dbHandler = new DatabaseHandler(this.context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor cursor_day_part = null;
		
		db.beginTransaction();
		try
		{
			cursor_day_part = db.query(DayPartSchema.TABLE_NAME, 
					null, 
					DayPartSchema.TABLE_COL_START_HOUR + " <= ? AND "
					+ DayPartSchema.TABLE_COL_END_HOUR + " >= ?", 
					new String[] {
						String.valueOf(currentHour.intValue()),
						String.valueOf(currentHour.intValue()) }, 
					null, null, null, null);
			
			if (cursor_day_part != null && cursor_day_part.moveToLast())
			{
				Integer dayPartId = Integer.valueOf(cursor_day_part.getInt(0));
				String name = cursor_day_part.getString(1);
				Integer startHour = Integer.valueOf(cursor_day_part.getInt(2));
				Integer endHour = Integer.valueOf(cursor_day_part.getInt(3));
				
				dayPart = new DayPart(dayPartId, name, startHour, endHour);
			}
			
			db.setTransactionSuccessful();
		}
		catch(Exception e)
		{
			Log.e(DayPartDao.class.toString(), e.getMessage());
		}
		finally
		{
			db.endTransaction();
			db.close();
			if(cursor_day_part != null) cursor_day_part.close();
		}
		
		return dayPart;
	}
}
