package com.udem.ift6243.sql.schema;

import java.util.ArrayList;

import com.udem.ift6243.utility.Constant;

public final class SolutionSchema
{
	public static final String TABLE_NAME = "solution";
	
	public static final String TABLE_COL_ID = "id";
	public static final String TABLE_COL_CATEGORY_ID = "category_id";
	public static final String TABLE_COL_NAME = "name";
	public static final String TABLE_COL_DESCRIPTION = "description";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `" + TABLE_NAME + "` ("
				+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "`category_id`	INTEGER,"
				+ "`name`	TEXT UNIQUE,"
				+ "`description`	TEXT"
				+ ");";
	}
	
	public static ArrayList<String> getInsertDataList()
	{
		ArrayList<String> queryList = new ArrayList<String>();
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, " + 
				Constant.SOLUTION_CATEGORY_SPORT + ", 'Course', 'Courrez pendant 2 minutes !');");
		
		return queryList;
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
