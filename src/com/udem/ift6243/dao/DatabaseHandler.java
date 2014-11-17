package com.udem.ift6243.dao;

import java.util.ArrayList;

import com.udem.ift6243.sql.schema.SolutionSchema;
import com.udem.ift6243.sql.schema.UserSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper 
{
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "Hera";
	
	// Others
	private Context context;
	
	public DatabaseHandler(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	public void createDatabase()
	{
		getReadableDatabase();
	}
	
	// Creating Tables
	@Override
	public void onCreate(final SQLiteDatabase db)
	{
		Log.w("Database", "onCreateDB");
		
		/// Create tables
		db.execSQL(UserSchema.getCreateTableSchema());
		db.execSQL(SolutionSchema.getCreateTableSchema());
		
		/// Insert data
		ArrayList<String> solutionQueries = SolutionSchema.getInsertDataList();
		for(String solutionQuery : solutionQueries)
		{
			db.execSQL(solutionQuery);
		}
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		/// Delete tables
		db.execSQL(UserSchema.getDropTableSchema());
		db.execSQL(SolutionSchema.getDropTableSchema());
		
		/// Create tables
		onCreate(db);
	}
}
