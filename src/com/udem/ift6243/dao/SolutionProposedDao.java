package com.udem.ift6243.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.udem.ift6243.model.SolutionProposed;
import com.udem.ift6243.sql.schema.SolutionProposedSchema;

public class SolutionProposedDao
{
	private Context context;
	
	public SolutionProposedDao(Context context)
	{
		this.context = context;
	}

	public boolean createSolutionProposed(SolutionProposed SolutionProposed)
	{
		boolean error = true;
	    
	    DatabaseHandler dbHandler = new DatabaseHandler(this.context);
	    SQLiteDatabase db = dbHandler.getReadableDatabase();
		
		db.beginTransaction();
		try
		{
			ContentValues values = new ContentValues();
			values.put(SolutionProposedSchema.TABLE_COL_ID, SolutionProposed.getId());
			values.put(SolutionProposedSchema.TABLE_COL_CONTEXT_ID, SolutionProposed.getHeraContextId());
			values.put(SolutionProposedSchema.TABLE_COL_SOLUTION_ID, SolutionProposed.getSolutionId());
			values.put(SolutionProposedSchema.TABLE_COL_BONUS, SolutionProposed.getBonus());
			values.put(SolutionProposedSchema.TABLE_COL_DATE, SolutionProposed.getDate());
			
		    long insertedId = db.insert(SolutionProposedSchema.TABLE_NAME, null, values);
			
			db.setTransactionSuccessful();
			
			if(insertedId > 0L)
			{
				SolutionProposed.setId(Integer.valueOf((int)insertedId));
				
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
	
	public ArrayList<SolutionProposed> getSolutionProposedFromHeraContext(Integer previousHeraContextId)
	{
		ArrayList<SolutionProposed> solutionProposedList = new ArrayList<SolutionProposed>();
		SolutionProposed solutionProposed = null;
		
		DatabaseHandler dbHandler = new DatabaseHandler(this.context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor cursor_solution_proposed = null;
		
		db.beginTransaction();
		try
		{
			cursor_solution_proposed = db.query(SolutionProposedSchema.TABLE_NAME, 
					null, SolutionProposedSchema.TABLE_COL_CONTEXT_ID + " = ?", 
					new String[] { String.valueOf(previousHeraContextId.intValue()) }, 
					null, null, null, null);

			if (cursor_solution_proposed != null && cursor_solution_proposed.moveToFirst())
			{
	            while (cursor_solution_proposed.isAfterLast() == false)
	            {
	            	Integer solutionProposedId = Integer.valueOf(cursor_solution_proposed.getInt(0));
	        		Integer heraContextId = Integer.valueOf(cursor_solution_proposed.getInt(1));
	            	Integer solutionId = Integer.valueOf(cursor_solution_proposed.getInt(2));
	        		Double bonus = Double.valueOf(cursor_solution_proposed.getDouble(3));
	        		String date = cursor_solution_proposed.getString(4);
	        		
	        		solutionProposed = new SolutionProposed(solutionProposedId, 
	        				heraContextId, solutionId, bonus, date);
	        		
					solutionProposedList.add(solutionProposed);
	                cursor_solution_proposed.moveToNext();
	            }
	        }
			
			db.setTransactionSuccessful();
		}
		finally
		{
			db.endTransaction();
			db.close();
			if(cursor_solution_proposed != null) cursor_solution_proposed.close();
		}
		
		return solutionProposedList;
	}
	
	public ArrayList<SolutionProposed> getSolutionProposed()
	{
		ArrayList<SolutionProposed> solutionProposedList = new ArrayList<SolutionProposed>();
		SolutionProposed solutionProposed = null;
		
		DatabaseHandler dbHandler = new DatabaseHandler(this.context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor cursor_solution_proposed = null;
		
		db.beginTransaction();
		try
		{
			cursor_solution_proposed = db.query(SolutionProposedSchema.TABLE_NAME, 
					null, null, null, null, null, null, null);

			if (cursor_solution_proposed != null && cursor_solution_proposed.moveToFirst())
			{
	            while (cursor_solution_proposed.isAfterLast() == false)
	            {
	            	Integer solutionProposedId = Integer.valueOf(cursor_solution_proposed.getInt(0));
	        		Integer heraContextId = Integer.valueOf(cursor_solution_proposed.getInt(1));
	            	Integer solutionId = Integer.valueOf(cursor_solution_proposed.getInt(2));
	        		Double bonus = Double.valueOf(cursor_solution_proposed.getDouble(3));
	        		String date = cursor_solution_proposed.getString(4);
	        		
	        		solutionProposed = new SolutionProposed(solutionProposedId, 
	        				heraContextId, solutionId, bonus, date);
	        		
					solutionProposedList.add(solutionProposed);
	                cursor_solution_proposed.moveToNext();
	            }
	        }
			
			db.setTransactionSuccessful();
		}
		finally
		{
			db.endTransaction();
			db.close();
			if(cursor_solution_proposed != null) cursor_solution_proposed.close();
		}
		
		return solutionProposedList;
	}
}
