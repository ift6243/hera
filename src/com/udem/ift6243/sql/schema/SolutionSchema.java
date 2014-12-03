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
	public static final String TABLE_COL_DURATION = "duration";
	public static final String TABLE_COL_PRIORITY = "priority";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `" + TABLE_NAME + "` ("
				+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "`category_id`	INTEGER,"
				+ "`name`	TEXT UNIQUE,"
				+ "`description`	TEXT,"
				+ "`duration`	REAL," // in Milliseconds
				+ "`priority`	REAL"
				+ ");";
	}
	
	public static ArrayList<String> getInsertDataList()
	{
		ArrayList<String> queryList = new ArrayList<String>();
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, " + 
				Constant.SOLUTION_CATEGORY_SPORT + ", 'Courir', 'Courrez pendant 2 minutes.', 2, 0.5);");
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, " + 
				Constant.SOLUTION_CATEGORY_ACTION + ", 'Action', 'Faites une sieste de 15 minutes.', 15, 0.5);");
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, " + 
				Constant.SOLUTION_CATEGORY_MEDICAL + ", 'Consulter un medecin', 'Calmez-vous, reposez-vous et pensez a consulter un medecin', 0, 0.5);");
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, " + 
				Constant.SOLUTION_CATEGORY_EXPRESSION + ", 'Dessin', 'Faites un petit dessin.', 1, 0.5);");
		
		queryList.add("INSERT INTO " + TABLE_NAME + " VALUES (NULL, " + 
				Constant.SOLUTION_CATEGORY_MULTIMEDIA + ", 'Son de relaxation', 'Relaxez-vous en ecoutant cette douce musique', 1, 0.5);");
		return queryList;
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
