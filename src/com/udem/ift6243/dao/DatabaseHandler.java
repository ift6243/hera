package com.udem.ift6243.dao;

import java.util.ArrayList;

import com.udem.ift6243.sql.schema.HeraContextSchema;
import com.udem.ift6243.sql.schema.DayPartSchema;
import com.udem.ift6243.sql.schema.SolutionCategorySchema;
import com.udem.ift6243.sql.schema.SolutionProposedSchema;
import com.udem.ift6243.sql.schema.SolutionSchema;
import com.udem.ift6243.sql.schema.UserSchema;
import com.udem.ift6243.sql.schema.WeekPartSchema;
import com.udem.ift6243.sql.schema.YearPartSchema;

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
//	private Context context;
	
	public DatabaseHandler(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
//		this.context = context;
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
		db.execSQL(SolutionCategorySchema.getCreateTableSchema());
		db.execSQL(DayPartSchema.getCreateTableSchema());
		db.execSQL(WeekPartSchema.getCreateTableSchema());
		db.execSQL(YearPartSchema.getCreateTableSchema());
		db.execSQL(HeraContextSchema.getCreateTableSchema());
		db.execSQL(SolutionProposedSchema.getCreateTableSchema());
		
		/// Insert datas
		ArrayList<String> solutionQueries = SolutionSchema.getInsertDataList();
		for(String solutionQuery : solutionQueries)
		{
			db.execSQL(solutionQuery);
		}
		
		ArrayList<String> solutionCategoryQueries = SolutionCategorySchema.getInsertDataList();
		for(String solutionCategoryQuery : solutionCategoryQueries)
		{
			db.execSQL(solutionCategoryQuery);
		}
		
		ArrayList<String> dayPartQueries = DayPartSchema.getInsertDataList();
		for(String dayPartQuery : dayPartQueries)
		{
			db.execSQL(dayPartQuery);
		}
		
		ArrayList<String> weekPartQueries = WeekPartSchema.getInsertDataList();
		for(String weekPartQuery : weekPartQueries)
		{
			db.execSQL(weekPartQuery);
		}
		
		ArrayList<String> yearPartQueries = YearPartSchema.getInsertDataList();
		for(String yearPartQuery : yearPartQueries)
		{
			db.execSQL(yearPartQuery);
		}
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		/// Delete tables
		db.execSQL(UserSchema.getDropTableSchema());
		db.execSQL(SolutionSchema.getDropTableSchema());
		db.execSQL(SolutionCategorySchema.getDropTableSchema());
		db.execSQL(DayPartSchema.getDropTableSchema());
		db.execSQL(WeekPartSchema.getDropTableSchema());
		db.execSQL(YearPartSchema.getDropTableSchema());
		db.execSQL(HeraContextSchema.getDropTableSchema());
		db.execSQL(SolutionProposedSchema.getDropTableSchema());
		
		/// Create tables
		onCreate(db);
	}
}
