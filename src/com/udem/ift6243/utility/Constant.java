package com.udem.ift6243.utility;

public final class Constant 
{
	/// Data source
	// Production URL
//	public static String SOURCE_DATA_EDA = "http://192.168.0.51:8888/ift6243/data.eda";
	// Development URL
	public static String SOURCE_DATA_EDA = "http://paul-molins.fr/ift6243/data.eda";
	
	/// User profile
	public static Integer GENDER_FEMALE = Integer.valueOf(1);
	public static Integer GENDER_MALE = Integer.valueOf(2);
	
	public static Integer AGE_0_9 = Integer.valueOf(1);
	public static Integer AGE_10_19 = Integer.valueOf(2);
	public static Integer AGE_20_29 = Integer.valueOf(3);
	public static Integer AGE_30_39 = Integer.valueOf(4);
	public static Integer AGE_40_49 = Integer.valueOf(5);
	public static Integer AGE_50_59 = Integer.valueOf(6);
	public static Integer AGE_60_69 = Integer.valueOf(7);
	public static Integer AGE_70_79 = Integer.valueOf(8);
	public static Integer AGE_80_89 = Integer.valueOf(9);
	public static Integer AGE_90_99 = Integer.valueOf(10);
	public static Integer AGE_100 = Integer.valueOf(11);
	
	// Marital Status
	public static Integer SINGLE = Integer.valueOf(1);
	public static Integer MARRIED = Integer.valueOf(2);
	public static Integer DIVORCED = Integer.valueOf(3);
	
	// Professionnal Status
	public static Integer CAT_1 = Integer.valueOf(1);
	public static Integer CAT_2 = Integer.valueOf(2);
	public static Integer CAT_3 = Integer.valueOf(3);
	public static Integer CAT_4 = Integer.valueOf(4);
	public static Integer CAT_5 = Integer.valueOf(5);
	public static Integer CAT_6 = Integer.valueOf(6);
	public static Integer CAT_7 = Integer.valueOf(7);
	public static Integer CAT_8 = Integer.valueOf(8);
	public static Integer CAT_9 = Integer.valueOf(9);
	
	// Expression
	public static Integer READING = Integer.valueOf(1);
	public static Integer WRITING = Integer.valueOf(2);
	public static Integer DRAWING = Integer.valueOf(3);
	public static Integer SINGING = Integer.valueOf(4);
	public static Integer DANCING = Integer.valueOf(5);
	
	// Solution
	public static Integer SOLUTION_CATEGORY_SPORT = Integer.valueOf(1);
	public static Integer SOLUTION_CATEGORY_ACTION = Integer.valueOf(2);
	public static Integer SOLUTION_CATEGORY_MEDICAL = Integer.valueOf(3);
	public static Integer SOLUTION_CATEGORY_EXPRESSION = Integer.valueOf(4);
	public static Integer SOLUTION_CATEGORY_MULTIMEDIA = Integer.valueOf(5);
	
	/// Stress
	public static Integer STRESS_LEVEL_NEGATIVE_OR_CONSTANT = Integer.valueOf(1);
	public static Integer STRESS_LEVEL_LOW = Integer.valueOf(2);
	public static Integer STRESS_LEVEL_HIGH = Integer.valueOf(3);
	
	public static double STRESS_VARIATION_WARNING = 1.5;
	public static double STRESS_VARIATION_ALERT = 3;
}
