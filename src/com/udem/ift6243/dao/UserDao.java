package com.udem.ift6243.dao;

import com.udem.ift6243.dao.DatabaseHandler;
import com.udem.ift6243.model.User;
import com.udem.ift6243.sql.schema.UserSchema;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao
{
	private Context context;
	
	public UserDao(Context context)
	{
		this.context = context;
	}

	/**
	 * Creation de l'utilisateur
	 * @param User
	 * @return true si l'utilisateur a ete ajoute, sinon false
	 */
	public boolean createUser(User user)
	{
		boolean error = true;
	    
	    DatabaseHandler dbHandler = new DatabaseHandler(this.context);
	    SQLiteDatabase db = dbHandler.getReadableDatabase();
		
		db.beginTransaction();
		try
		{
			ContentValues values = new ContentValues();
			values.put(UserSchema.TABLE_COL_LAST_NAME, user.getLastName());
			values.put(UserSchema.TABLE_COL_FIRST_NAME, user.getFirstName());
			values.put(UserSchema.TABLE_COL_GENDER, user.getGender());
			values.put(UserSchema.TABLE_COL_AGE, user.getAge());
			values.put(UserSchema.TABLE_COL_MARITAL_STATUS, user.getMaritalStatus());
			values.put(UserSchema.TABLE_COL_SOCIOPROFESSIONAL_CATEGORY, user.getSocioprofessionalCategory());
			values.put(UserSchema.TABLE_COL_SPORT, user.getSport());
			values.put(UserSchema.TABLE_COL_MEDITATION, user.getMeditation());
			values.put(UserSchema.TABLE_COL_EXPRESSION, user.getExpression());
			
		    long insertedId = db.insert(UserSchema.TABLE_NAME, null, values);
			
			db.setTransactionSuccessful(); // marks a commit
			
			if(insertedId > 0L)
			{
				user.setId(Integer.valueOf((int)insertedId));
				
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
	 * Recuperation de l'utilisateur
	 * @return User
	 */
	public User getUser()
	{
		User user = null;
		
		DatabaseHandler dbHandler = new DatabaseHandler(this.context);
		SQLiteDatabase db = dbHandler.getReadableDatabase();
		Cursor cursor_user = null;
		
		db.beginTransaction();
		try
		{
			cursor_user = db.query(UserSchema.TABLE_NAME, null, 
					null, null, null, null, null, null);
			
			if (cursor_user != null && cursor_user.moveToLast())
			{
				Integer userId = Integer.valueOf(cursor_user.getInt(0));
				String firstName = cursor_user.getString(1);
				String lastName = cursor_user.getString(2);
				Integer gender = Integer.valueOf(cursor_user.getInt(3));
				Integer age = Integer.valueOf(cursor_user.getInt(4));
				Integer maritalStatus = Integer.valueOf(cursor_user.getInt(5));
				Integer socioprofessionalCategory = Integer.valueOf(cursor_user.getInt(6));
				Boolean sport = Boolean.valueOf(Integer.valueOf(cursor_user.getInt(7)).equals(Integer.valueOf(1)));
				Boolean meditation = Boolean.valueOf(Integer.valueOf(cursor_user.getInt(8)).equals(Integer.valueOf(1)));
				Integer expression = Integer.valueOf(cursor_user.getInt(9));
				
				user = new User(userId, firstName, lastName, 
						gender, age, maritalStatus, 
						socioprofessionalCategory, 
						sport, meditation, expression);
			}
			
			db.setTransactionSuccessful(); // marks a commit
		}
		finally
		{
			db.endTransaction();
			db.close();
			if(cursor_user != null) cursor_user.close();
		}
		
		return user;
	}
}
