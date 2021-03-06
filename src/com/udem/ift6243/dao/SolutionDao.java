package com.udem.ift6243.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.udem.ift6243.model.Solution;
import com.udem.ift6243.sql.schema.SolutionSchema;

public class SolutionDao
{
	private Context context;
	
	public SolutionDao(Context context)
	{
		this.context = context;
	}
	
	public boolean updateSolution(Solution solution)
	{
		boolean error = true;
	    
	    DatabaseHandler dbHandler = new DatabaseHandler(this.context);
	    SQLiteDatabase db = dbHandler.getReadableDatabase();
		
		db.beginTransaction();
		try
		{
			ContentValues values = new ContentValues();
			values.put(SolutionSchema.TABLE_COL_CATEGORY_ID, solution.getCategoryId());
			values.put(SolutionSchema.TABLE_COL_NAME, solution.getName());
			values.put(SolutionSchema.TABLE_COL_DESCRIPTION, solution.getDescription());
			values.put(SolutionSchema.TABLE_COL_DURATION, solution.getDuration());
			values.put(SolutionSchema.TABLE_COL_PRIORITY, solution.getPriority());
			
		    int nbRows = db.update(SolutionSchema.TABLE_NAME, values, 
		    		SolutionSchema.TABLE_COL_ID + " = ?", 
		    		new String[] { String.valueOf(solution.getId()) });
			
			db.setTransactionSuccessful();
			
			if(nbRows > 0)
			{
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
	
	/**
	 * Get solution from id
	 * @param id de la solution
	 * @return Solution
	 */
	public Solution getSolution(Integer id)
	{
		Solution solution = null;
		
		DatabaseHandler dbHandler = new DatabaseHandler(context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor cursor_solution = null;
		
		db.beginTransaction();
		try
		{
			cursor_solution = db.query(SolutionSchema.TABLE_NAME, 
					null, 
					SolutionSchema.TABLE_COL_ID + " = ?", 
					new String[] { String.valueOf(id.intValue()) }, 
					null, null, null, null);
			
			if (cursor_solution != null && cursor_solution.moveToLast())
			{
				solution = this.createSolutionFromCursor(cursor_solution);
			}
			
			db.setTransactionSuccessful();
		}
		finally
		{
			db.endTransaction();
			db.close();
			if(cursor_solution != null) cursor_solution.close();
		}
		
		return solution;
	}
	
	public ArrayList<Solution> getSolution()
	{
		ArrayList<Solution> solutionList = new ArrayList<Solution>();
		Solution solution = null;
		
		DatabaseHandler dbHandler = new DatabaseHandler(this.context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor cursor_solution = null;
		
		db.beginTransaction();
		try
		{
			cursor_solution = db.query(SolutionSchema.TABLE_NAME, 
					null, null, null, null, null, null, null);

			if (cursor_solution != null && cursor_solution.moveToFirst())
			{
	            while (cursor_solution.isAfterLast() == false)
	            {
	            	solution = this.createSolutionFromCursor(cursor_solution);
	                
					solutionList.add(solution);
	                cursor_solution.moveToNext();
	            }
	        }
			
			db.setTransactionSuccessful();
		}
		finally
		{
			db.endTransaction();
			db.close();
			if(cursor_solution != null) cursor_solution.close();
		}
		
		return solutionList;
	}
	
	private Solution createSolutionFromCursor(Cursor cursor_solution)
	{
		Solution solution = null;
		
		Integer solutionId = Integer.valueOf(cursor_solution.getInt(0));
		Integer categoryId = Integer.valueOf(cursor_solution.getInt(1));
		String name = cursor_solution.getString(2);
		String description = cursor_solution.getString(3);
		Double duration = Double.valueOf(cursor_solution.getDouble(4));
		Double priority = Double.valueOf(cursor_solution.getDouble(5));
		
		solution = new Solution(solutionId, categoryId, name, description,
				duration, priority);
		
		return solution;
	}
}
