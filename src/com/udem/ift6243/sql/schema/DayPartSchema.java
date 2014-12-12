package com.udem.ift6243.sql.schema;

import java.util.ArrayList;

public class DayPartSchema
{
	public static final String TABLE_NAME = "day_part";
	
	public static final String TABLE_COL_ID = "id";
	public static final String TABLE_COL_NAME = "name";
	public static final String TABLE_COL_START_HOUR = "start_hour";
	public static final String TABLE_COL_END_HOUR = "end_hour";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `" + TABLE_NAME + "` ("
			+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "`name`	TEXT UNIQUE,"
			+ "`start_hour`	INTEGER,"
			+ "`end_hour`	INTEGER"
			+ ");";
	}
	
	public static ArrayList<String> getInsertDataList()
	{
		ArrayList<String> queryList = new ArrayList<String>();
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, 'Matin', 0, 12);");
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, 'Apres-midi', 13, 18);");
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, 'Soir', 19, 23);");
		
		return queryList;
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
