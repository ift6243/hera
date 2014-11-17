package com.udem.ift6243.sql.schema;

import java.util.ArrayList;

import com.udem.ift6243.utility.Constant;

public class SolutionCategorySchema
{
	public static final String TABLE_NAME = "solution_category";
	
	public static final String TABLE_COL_ID = "id";
	public static final String TABLE_COL_NAME = "name";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `solution_category` ("
				+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "`name`	TEXT UNIQUE"
				+ ");";
	}
	
	public static ArrayList<String> getInsertDataList()
	{
		ArrayList<String> queryList = new ArrayList<String>();
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (" + 
				Constant.SOLUTION_CATEGORY_SPORT + ", 'Sport');");
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (" + 
				Constant.SOLUTION_CATEGORY_ACTION + ", 'Action');");
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (" + 
				Constant.SOLUTION_CATEGORY_MEDICAL + ", 'Médical');");
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (" + 
				Constant.SOLUTION_CATEGORY_CREATIVITY + ", 'Créativité');");
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (" + 
				Constant.SOLUTION_CATEGORY_MULTIMEDIA + ", 'Multimédia');");
		
		return queryList;
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
