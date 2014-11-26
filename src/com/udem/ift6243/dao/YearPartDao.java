package com.udem.ift6243.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.udem.ift6243.model.YearPart;
import com.udem.ift6243.sql.schema.YearPartSchema;

public class YearPartDao
{
	private Context context;
	
	public YearPartDao(Context context)
	{
		this.context = context;
	}
	
	/**
	 * Get YearPart from current date
	 * @param currentDate Format MM-DD expected
	 * @return YearPart
	 */
	public YearPart getYearPart(String currentDate)
	{
		YearPart yearPart = null;
		
		DatabaseHandler dbHandler = new DatabaseHandler(this.context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor cursor_year_part = null;
		
		db.beginTransaction();
		try
		{
			cursor_year_part = db.query(YearPartSchema.TABLE_NAME, 
					null, 
					YearPartSchema.TABLE_COL_START_DATE + " <= ? AND "
					+ YearPartSchema.TABLE_COL_END_DATE + " >= ?", 
					new String[] { currentDate, currentDate }, 
					null, null, null, null);
			
			if (cursor_year_part != null && cursor_year_part.moveToLast())
			{
				Integer yearPartId = Integer.valueOf(cursor_year_part.getInt(0));
				String name = cursor_year_part.getString(1);
				String startDate = cursor_year_part.getString(2);
				String endDate = cursor_year_part.getString(3);
				
				yearPart = new YearPart(yearPartId, name, startDate, endDate);
			}
			
			db.setTransactionSuccessful();
		}
		catch(Exception e)
		{
			Log.e(YearPartDao.class.toString(), e.getMessage());
		}
		finally
		{
			db.endTransaction();
			db.close();
			if(cursor_year_part != null) cursor_year_part.close();
		}
		
		return yearPart;
	}
}
