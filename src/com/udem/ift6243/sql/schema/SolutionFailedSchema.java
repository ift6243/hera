package com.udem.ift6243.sql.schema;

public class SolutionFailedSchema
{
	public static final String TABLE_NAME = "solution_failed";
	
	public static final String TABLE_COL_ID = "id";
	public static final String TABLE_COL_CONTEXT_ID = "context_id";
	public static final String TABLE_COL_SOLUTION_ID = "solution_id";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `" + TABLE_NAME + "` ("
				+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "`context_id` INTEGER,"
				+ "`solution_id` INTEGER,"
				+ "`date`	TEXT"
				+ ");";
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
