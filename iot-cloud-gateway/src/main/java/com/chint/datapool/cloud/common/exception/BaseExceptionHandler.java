package com.chint.datapool.cloud.common.exception;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.chint.datapool.cloud.dto.BaseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestControllerAdvice
public class BaseExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

	/**
	 * 系统异常
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public BaseDTO exceptionHandler(HttpServletRequest req, Exception exception) throws Exception {
		exception.printStackTrace();
		logger.info("BaseExceptionHandler.exceptionHandler");
		BaseDTO result = new BaseDTO();
		result.setResultCode(BaseException.BASE_SYS_ERROR);
		result.setResultMessage("系统异常,请联系管理员!");
		logger.error("========[code:"+BaseException.BASE_SYS_ERROR+"]系统异常!======", exception);
		return result;
	}
	
	
	/**
	 * 通用异常
	 */
	@ExceptionHandler(value = { BaseException.class })
	@ResponseBody
	public BaseDTO baseExceptionHandler(HttpServletRequest req, Exception exception) throws Exception {
		exception.printStackTrace();
		logger.info("BaseExceptionHandler.baseExceptionHandler");
		BaseDTO result = new BaseDTO();
		BaseException baseException = (BaseException) exception;
		result.setResultCode(baseException.getCode());
		result.setResultMessage(baseException.getMessage());
		logger.error("========[code:"+baseException.getCode()+"]业务异常!======", exception);
		return result;
	}

	
	/**
	 * json处理异常
	 */
	@ExceptionHandler(value = { JsonProcessingException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public BaseDTO jsonProcessingExceptionHandler(JsonProcessingException exception) throws Exception {
		exception.printStackTrace();
		logger.info("BaseExceptionHandler.jsonProcessingExceptionHandler");
		BaseDTO result = new BaseDTO();
		result.setResultCode(BaseException.BASE_JSON_PROCESS_ERROR);
		result.setResultMessage(exception.getMessage());
		result.setResultMessage("json 解析异常");
		logger.error("========[code:"+BaseException.BASE_JSON_PROCESS_ERROR+"] json解析异常!======", exception);
		return result;
	}



	/**
	 * sql异常
	 */
	@ExceptionHandler(value = SQLException.class)
	@ResponseBody
	public BaseDTO SqlErrorHandler(HttpServletRequest req, SQLException exception) throws Exception {
		exception.printStackTrace();
		logger.info("BaseExceptionHandler.SqlErrorHandler");
		BaseDTO result = new BaseDTO();
		result.setResultCode(BaseException.BASE_SQL_ERROR);
		result.setResultMessage("系统异常,请联系管理员!");
		logger.error("========[code:"+BaseException.BASE_SQL_ERROR+"] Sql异常!======", exception);
		return result;
	}

	/**
	 * mybatis异常
	 */
	@ExceptionHandler(value = MyBatisSystemException.class)
	@ResponseBody
	public BaseDTO TooManyResultsErrorHandler(HttpServletRequest req, MyBatisSystemException exception) throws Exception {
		exception.printStackTrace();
		logger.info("BaseExceptionHandler.TooManyResultsErrorHandler");
		BaseDTO result = new BaseDTO();
		Throwable error = exception.getCause();
		result.setResultCode(BaseException.BASE_SQL_ERROR);
		result.setResultMessage("Sql异常,请联系管理员!");
		if (error instanceof TooManyResultsException) {
			result.setResultCode(BaseException.BASE_MYBATIS_ERROR);
			result.setResultMessage("业务数据不整合，请确认业务是否正确！");
		}
		logger.error("========[code:"+BaseException.BASE_MYBATIS_ERROR+"]mybatis异常!======", exception);
		return result;
	}
}
