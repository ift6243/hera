package com.udem.ift6243.sql.schema;

import java.util.ArrayList;

public class WeekPartSchema
{
	public static final String TABLE_NAME = "week_part";
	
	public static final String TABLE_COL_ID = "id";
	public static final String TABLE_COL_NAME = "name";
	public static final String TABLE_COL_START_DAY = "start_day";
	public static final String TABLE_COL_END_DAY = "end_day";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `" + TABLE_NAME + "` ("
			+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "`name`	TEXT UNIQUE,"
			+ "`start_day`	INTEGER,"
			+ "`end_day`	INTEGER"
			+ ");";
	}
	
	public static ArrayList<String> getInsertDataList()
	{
		ArrayList<String> queryList = new ArrayList<String>();
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, 'Début', 0, 3);"); // Lundi a jeudi
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, 'Milieu', 4, 4);"); // vendredi
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, 'Fin', 5, 6);"); // samedi et dimanche
		
		return queryList;
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
