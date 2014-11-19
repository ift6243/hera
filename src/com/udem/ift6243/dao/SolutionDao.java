package com.udem.ift6243.dao;

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
	
	/**
	 * Get solution from id
	 * @param id de la solution
	 * @return Solution
	 */
	public Solution getSolution(Integer id)
	{
		Solution solution = null;
		
		DatabaseHandler dbHandler = new DatabaseHandler(this.context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor cursor_solution = null;
		
		db.beginTransaction();
		try
		{
			cursor_solution = db.query(SolutionSchema.TABLE_NAME, 
					null, 
					SolutionSchema.TABLE_COL_ID + " = ?", 
					new String[] { String.valueOf(id) }, 
					null, null, null, null);
			
			if (cursor_solution != null && cursor_solution.moveToLast())
			{
				Integer solutionId = Integer.valueOf(cursor_solution.getInt(0));
				Integer categoryId = Integer.valueOf(cursor_solution.getInt(1));
				String name = cursor_solution.getString(2);
				String description = cursor_solution.getString(3);
				
				solution = new Solution(solutionId, categoryId, name, description);
			}
			
			db.setTransactionSuccessful(); // marks a commit
		}
		finally
		{
			db.endTransaction();
			db.close();
			if(cursor_solution != null) cursor_solution.close();
		}
		
		return solution;
	}
}
