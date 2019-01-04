package com.chint.datapool.cloud.common.base;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import com.chint.datapool.cloud.common.constant.Constants;
import com.chint.datapool.cloud.common.exception.ChintAplException;

public class BaseController {

	/**
	 * 正常的返回值
	 * @param outPutBean
	 */
    protected void setSuccess(BaseResponse outPutBean){
    	outPutBean.setCode(Constants.ResultCode.SUCCESS);
    }

    /**
     * 异常返回值
     * @param outPutBean
     * @param e
     */
    protected void setFailed(BaseResponse outPutBean, ChintAplException e){
    	outPutBean.setCode(Constants.ResultCode.BUSS_ERR);
    	outPutBean.setMsg(e.getMessage());
    }

    /**
     * 处理参数异常
     * @param bindingResult
     */
    protected void paramaterVidation(BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            StringBuilder errorMesssage = new StringBuilder("参数异常:");
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMesssage.append(fieldError.getField());
                errorMesssage.append(":" );
                errorMesssage.append(fieldError.getDefaultMessage() );
                errorMesssage.append("; ");
            }
            throw new ChintAplException(Constants.ResultCode.PARAM_ERR,errorMesssage.toString());
        }
    }
}
