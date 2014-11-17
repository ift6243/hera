package com.udem.ift6243.sql.schema;

public final class SolutionSchema
{
	public static final String TABLE_NAME = "solution";
	
	public static final String TABLE_COL_ID = "id";
	public static final String TABLE_COL_CATEGORY_ID = "category_id";
	public static final String TABLE_COL_NAME = "name";
	public static final String TABLE_COL_DESCRIPTION = "description";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `solution` ("
				+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "`category_id`	INTEGER,"
				+ "`name`	TEXT UNIQUE,"
				+ "`description`	TEXT"
				+ ");";
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
