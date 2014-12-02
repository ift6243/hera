package com.udem.ift6243.sql.schema;

public class SolutionProposedSchema
{
	public static final String TABLE_NAME = "solution_proposed";
	
	public static final String TABLE_COL_ID = "id";
	public static final String TABLE_COL_CONTEXT_ID = "context_id";
	public static final String TABLE_COL_SOLUTION_ID = "solution_id";
	public static final String TABLE_COL_BONUS = "bonus";
	public static final String TABLE_COL_DATE = "date";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `" + TABLE_NAME + "` ("
				+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "`context_id` INTEGER,"
				+ "`solution_id` INTEGER,"
				+ "`bonus` REAL,"
				+ "`date` TEXT"
				+ ");";
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
