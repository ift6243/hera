package com.udem.ift6243.sql.schema;

public final class UserSchema
{
	public static final String TABLE_NAME = "user";
	
	public static final String TABLE_COL_ID = "id";
	public static final String TABLE_COL_LAST_NAME = "last_name";
	public static final String TABLE_COL_FIRST_NAME = "first_name";
	public static final String TABLE_COL_GENDER = "gender_id";
	public static final String TABLE_COL_AGE = "age_id";
	public static final String TABLE_COL_MARITAL_STATUS = "marital_status_id";
	public static final String TABLE_COL_SOCIOPROFESSIONAL_CATEGORY = "socioprofessional_category_id";
	public static final String TABLE_COL_SPORT = "sport";
	public static final String TABLE_COL_MEDITATION = "meditation";
	public static final String TABLE_COL_EXPRESSION = "expression";
	
	public static String getCreateTableSchema()
	{
		return "CREATE TABLE `" + TABLE_NAME + "` ("
				+ "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "`last_name`	TEXT,"
				+ "`first_name`	TEXT,"
				+ "`gender_id`	INTEGER,"
				+ "`age_id`	INTEGER,"
				+ "`marital_status_id`	INTEGER,"
				+ "`socioprofessional_category_id`	INTEGER,"
				+ "`sport`	INTEGER,"
				+ "`meditation`	INTEGER,"
				+ "`expression`	INTEGER"
				+ ");";
	}
	
	public static String getDropTableSchema()
	{
		return "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
	}
}
