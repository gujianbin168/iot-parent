package com.chint.datapool.cloud.common.exception;

public enum ExceptionEnum {
    TIMED_ACCESS_DENIED(1060001,"失败"),  
    
    
    APPROVE_ACCESS_DENIED(1010001,"失败"), 
    ;  
	
    private Integer code;  
    private String msg;  
      
    ExceptionEnum(Integer code,String msg) {  
    	
        this.code = code;  
        this.msg = msg;  
    }  
  
    public Integer getCode() {  
        return code;  
    }  
  
    public String getMsg() {  
        return msg;  
    } 
}
