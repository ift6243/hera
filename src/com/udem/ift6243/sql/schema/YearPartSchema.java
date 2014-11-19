package com.udem.ift6243.sql.schema;

import java.util.ArrayList;

public class YearPartSchema
{
	public static final String TABLE_NAME = "year_part";
	
	public static final String TABLE_COL_ID = "id";
	public static final String TABLE_COL_NAME = "name";
	public static final String TABLE_COL_START_HOUR = "start_date"; // Format : MM-DD
	public static final String TABLE_COL_END_HOUR = "end_date"; // Format : MM-DD
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `" + TABLE_NAME + "` ("
			+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "`name`	TEXT UNIQUE,"
			+ "`start_date`	TEXT,"
			+ "`end_date`	TEXT"
			+ ");";
	}
	
	public static ArrayList<String> getInsertDataList()
	{
		ArrayList<String> queryList = new ArrayList<String>();
		
		// Saisons meteorologiques
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, 'Printemps', '03-01', '05-31');");
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, 'Eté', '06-01', '08-31');");
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, 'Autonme', '09-01', '11-30');");
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, 'Hiver', '12-01', '02-28');");
		
		return queryList;
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
