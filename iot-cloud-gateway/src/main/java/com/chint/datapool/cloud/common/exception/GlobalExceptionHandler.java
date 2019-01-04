package com.chint.datapool.cloud.common.exception;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.chint.datapool.cloud.common.base.BaseResponse;
import com.chint.datapool.cloud.common.constant.Constants;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 捕捉自定义异常
	 * 
	 * @param req
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = { NewBaseException.class })
	@ResponseBody
	public BaseResponse dealerAplExceptionHandler(HttpServletRequest req, Exception exception) throws Exception {
		exception.printStackTrace();
		BaseResponse outPutBean = new BaseResponse();
		NewBaseException aplException = (NewBaseException) exception;
		outPutBean.setCode(String.valueOf(aplException.getCode()));
		outPutBean.setMsg(aplException.getMessage());
		return outPutBean;
	}

	/**
	 * 捕捉自定义异常
	 * 
	 * @param req
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = ChintAplException.class)
	@ResponseBody
	public BaseResponse ChintApiExceptionHandler(HttpServletRequest req, Exception exception) throws Exception {
		exception.printStackTrace();
		BaseResponse outPutBean = new BaseResponse();
		ChintAplException chintAplException = (ChintAplException) exception;
		outPutBean.setCode(chintAplException.getErrorCode());
		outPutBean.setMsg(chintAplException.getMessage());
		return outPutBean;
	}

	/**
	 * 捕捉系统异常
	 * 
	 * @param req
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public BaseResponse defaultErrorHandler(HttpServletRequest req, Exception exception) throws Exception {
		exception.printStackTrace();
		BaseResponse outPutBean = new BaseResponse();
		outPutBean.setCode(Constants.ResultCode.SYS_ERR);
		outPutBean.setMsg("系统异常,请联系管理员!");
		LOGGER.error("========[code:205] 系统异常,请联系管理员!======", exception);
		return outPutBean;
	}

	/**
	 * 捕捉系统异常
	 * 
	 * @param req
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = SQLException.class)
	@ResponseBody
	public BaseResponse SqlErrorHandler(HttpServletRequest req, SQLException exception) throws Exception {
		exception.printStackTrace();
		BaseResponse outPutBean = new BaseResponse();
		outPutBean.setCode(Constants.ResultCode.SYS_ERR);
		outPutBean.setMsg("系统异常,请联系管理员!");
		LOGGER.error("========[code:205] Sql异常,请联系管理员!======", exception);
		return outPutBean;
	}
}
