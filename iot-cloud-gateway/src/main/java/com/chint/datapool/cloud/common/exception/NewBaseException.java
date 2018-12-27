package com.chint.datapool.cloud.common.exception;

public class NewBaseException  extends  Exception{
	public final static int COMMON=1000000;
	protected final static int APPROVE=1010000;
	protected final static int CUSTOMER=1020000;
	protected final static int INTERFACE=1030000;
	protected final static int OPERATIONS=1040000;
	protected final static int DEALER=1050000;
	protected final static int TIMED=1060000;
	protected final static int MAIN=1070000;
	protected final static int MALL=1080000;
    /**
	 * 
	 */
	private static final long serialVersionUID = -3147797027971132314L;
    private Integer code;  
    
    public NewBaseException(Integer code,String message) {
    	super(message);
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
}
