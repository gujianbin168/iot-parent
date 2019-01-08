package com.chint.datapool.cloud.common.exception;

public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 770716188662234652L;
	//异常错误码 
	public final static String BASE_SYS_ERROR = "1000001";
	public final static String BASE_JSON_PROCESS_ERROR = "1000002";
	public final static String BASE_SQL_ERROR = "1000003";
	public final static String BASE_MYBATIS_ERROR = "1000004";
 	public final static String BASE_COMMON_SUCCESS = "1000000";

	
	
	private String code;

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
