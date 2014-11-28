package com.udem.ift6243.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.udem.ift6243.model.HeraContext;
import com.udem.ift6243.sql.schema.HeraContextSchema;

public class HeraContextDao
{
	private Context context;
	
	public HeraContextDao(Context context)
	{
		this.context = context;
	}
	
	public boolean createHeraContext(HeraContext heraContext)
	{
		boolean error = true;
	    
	    DatabaseHandler dbHandler = new DatabaseHandler(this.context);
	    SQLiteDatabase db = dbHandler.getReadableDatabase();
		
		db.beginTransaction();
		try
		{
			ContentValues values = new ContentValues();
			values.put(HeraContextSchema.TABLE_COL_ID, heraContext.getId());
			values.put(HeraContextSchema.TABLE_COL_LATITUDE, heraContext.getLatitude());
			values.put(HeraContextSchema.TABLE_COL_LONGITUDE, heraContext.getLongitude());
			values.put(HeraContextSchema.TABLE_COL_DAY_PART, heraContext.getDayPart());
			values.put(HeraContextSchema.TABLE_COL_WEEK_PART, heraContext.getWeekPart());
			values.put(HeraContextSchema.TABLE_COL_YEAR_PART, heraContext.getYearPart());
			values.put(HeraContextSchema.TABLE_COL_STRESS_LEVEL, heraContext.getStressLevel());
			values.put(HeraContextSchema.TABLE_COL_DATE, heraContext.getDate());
			
		    long insertedId = db.insert(HeraContextSchema.TABLE_NAME, null, values);
			
			db.setTransactionSuccessful();
			
			if(insertedId > 0L)
			{
				heraContext.setId(Integer.valueOf((int)insertedId));
				
				error = false;
			}
		}
		finally
		{
			db.endTransaction();
			db.close();
		}
		
		return !error;
	}
	
	public HeraContext getHeraContext(Integer requestedId)
	{
		HeraContext heraContext = null;
		
		DatabaseHandler dbHandler = new DatabaseHandler(this.context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor cursor_hera_context = null;
		
		db.beginTransaction();
		try
		{
			cursor_hera_context = db.query(HeraContextSchema.TABLE_NAME, null, 
					HeraContextSchema.TABLE_COL_ID + " = ?", 
					new String[] { String.valueOf(requestedId.intValue()) }, 
					null, null, null, null);
			
			if (cursor_hera_context != null && cursor_hera_context.moveToLast())
			{
				Integer heraContextId = Integer.valueOf(cursor_hera_context.getInt(0));
				Double latitude = Double.valueOf(cursor_hera_context.getDouble(3));
				Double longitude = Double.valueOf(cursor_hera_context.getDouble(3));
				Integer dayPart = Integer.valueOf(cursor_hera_context.getInt(4));
				Integer weekPart = Integer.valueOf(cursor_hera_context.getInt(5));
				Integer yearPart = Integer.valueOf(cursor_hera_context.getInt(6));
				Integer stressLevel = Integer.valueOf(cursor_hera_context.getInt(7));
				String date = cursor_hera_context.getString(8);
				
				heraContext = new HeraContext(heraContextId, latitude, longitude,
						dayPart, weekPart, yearPart, stressLevel, date);
			}
			
			db.setTransactionSuccessful(); // marks a commit
		}
		finally
		{
			db.endTransaction();
			db.close();
			if(cursor_hera_context != null) cursor_hera_context.close();
		}
		
		return heraContext;
	}
}