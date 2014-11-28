package com.udem.ift6243.sql.schema;

public class HeraContextSchema
{
	public static final String TABLE_NAME = "context";
	
	public static final String TABLE_COL_ID = "id";
	public static final String TABLE_COL_LATITUDE = "latitude";
	public static final String TABLE_COL_LONGITUDE = "longitude";
	public static final String TABLE_COL_DAY_PART = "day_part";
	public static final String TABLE_COL_WEEK_PART = "week_part";
	public static final String TABLE_COL_YEAR_PART = "year_part";
	public static final String TABLE_COL_STRESS_LEVEL = "stress_level";
	public static final String TABLE_COL_DATE = "date";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `" + TABLE_NAME + "` ("
				+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "`latitude`	REAL,"
				+ "`longitude`	REAL,"
				+ "`day_part`	INTEGER,"
				+ "`week_part`	INTEGER,"
				+ "`year_part`	INTEGER,"
				+ "`stress_level`	INTEGER,"
				+ "`date`	TEXT"
				+ ");";
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
