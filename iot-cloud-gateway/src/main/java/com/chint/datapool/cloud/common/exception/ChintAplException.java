package com.chint.datapool.cloud.common.exception;

import com.chint.datapool.cloud.common.constant.Constants;


public class ChintAplException extends BaseException {


    /**
	 * 
	 */
	private static final long serialVersionUID = -7985515024842175509L;
	/**错误CODE*/
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
    public ChintAplException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 默认系统异常 204
     * @param message
     */
    public ChintAplException(String message) {
        super(message);
        this.errorCode = Constants.ResultCode.BUSS_ERR;
    }
}
