package com.udem.ift6243.sql.schema;

public class ContextSchema
{
	public static final String TABLE_NAME = "context";
	
	public static final String TABLE_COL_ID = "id";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `context` ("
				+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "`latitude`	REAL,"
				+ "`longitude`	REAL,"
				+ "`day_part`	INTEGER,"
				+ "`week_part`	INTEGER,"
				+ "`year_part`	INTEGER,"
				+ "`date`	TEXT"
				+ ");";
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
