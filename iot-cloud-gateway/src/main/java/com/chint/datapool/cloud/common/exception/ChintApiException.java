package com.chint.datapool.cloud.common.exception;


public class ChintApiException extends BaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8080414991091331855L;
	public final static String API_SUCCESS = "1010000";
	public final static String API_USER_NOT_FOUND = "1010001";
	public final static String API_AUTH_ERR = "1010002";
 
	
	private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 设定指定的Error
     * @param errorCode
     * @param message
     *
     */
    public ChintApiException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
