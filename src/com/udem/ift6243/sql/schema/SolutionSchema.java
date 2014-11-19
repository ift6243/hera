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
				Constant.SOLUTION_CATEGORY_SPORT + ", 'Courir', 'Courrez pendant 2 minutes.');");
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, " + 
				Constant.SOLUTION_CATEGORY_ACTION + ", 'Action', 'Faites une sieste de 15 minutes.');");
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, " + 
				Constant.SOLUTION_CATEGORY_MEDICAL + ", 'Consulter un médecin', 'Calmez-vous, reposez-vous et pensez à consulter un médecin');");
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, " + 
				Constant.SOLUTION_CATEGORY_EXPRESSION + ", 'Dessin', 'Faites un petit dessin.');");
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, " + 
				Constant.SOLUTION_CATEGORY_MULTIMEDIA + ", 'Son de relaxation', 'Relaxez-vous en écoutant cette douce musique');");
		return queryList;
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
