package com.coupon.web.exceptions.mappers;

public class ErrorCode {
	
	// 400
	public static final String ILLGEL_PASSWORD 				= "406-1";
	public static final String COMPANY_NAME_EXISTS 			= "406-2";
	public static final String CUSTOMER_NAME_EXISTS 		= "406-3";
	public static final String EMAIL_EXISTS 				= "406-4";
	public static final String COUPON_DONT_EXISTS 			= "406-5";
	public static final String COMPANY_DONT_EXISTS 			= "406-6";
	public static final String CUSTOMER_DONT_EXISTS 		= "406-7";
	public static final String COMPANY_COUPON_DONT_EXISTS 	= "406-8";
	public static final String ID_MUST_B_NUM 				= "406-9";
	public static final String COUPON_PURCHASED 			= "406-10";
	public static final String OUT_OF_STOCK 				= "406-11";
	public static final String OUT_OF_DATE	 				= "406-11";
	public static final String COUPON_TITLE_EXISTS	 		= "406-12";
	public static final String ENUM_DOES_NOT_EXISTS	 		= "406-13";
	
	// 500
	public static final String UNEXPECTED 					= "500-0";
	public static final String ADMIN_UNEXPECTED 			= "500-1";
	public static final String CUSTOMER_UNEXPECTED 			= "500-2";
	public static final String COMPANY_UNEXPECTED 			= "500-3";
	
	
	
	
}
