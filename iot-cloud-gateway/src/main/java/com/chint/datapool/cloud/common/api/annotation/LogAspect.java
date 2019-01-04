package com.chint.datapool.cloud.common.api.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.chint.datapool.cloud.common.base.BaseRequest;
import com.chint.datapool.cloud.common.util.JsonUtil;


@Aspect
@Component
public class LogAspect {

	private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
//	@Pointcut("execution(public * com.chint.datapool.cloud.controller.*.*(..))")
//	public void controllerLog(){}
//	@Pointcut("execution(public * com.chint.datapool.cloud.service.impl.*.*(..))")
//	public void serviceLog(){}
	
	//||execution(public * com.chint.datapool.cloud.common.exception.ExceptionHandler.*(..))
	/**
	  * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
	  * @return 
	 * @throws Throwable 
	  */
	@Around(value="execution(public * com.chint.datapool.cloud.controller.*.*(..))")
	public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable{
		Object result = null;
		try {
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs(); 
			//拦截的实体类  
			Object target = joinPoint.getTarget(); 
			if(arguments!=null && arguments.length>0) {
				for(Object param:arguments) {
					if(param instanceof BaseRequest) {
						logger.info("enter "+target+" "+methodName+" with param="+JsonUtil.toJSONString(param));
					}
				}
			}
			//执行目标方法
			result = joinPoint.proceed();
			
//			logger.info("response={}",JSONObject.toJSONString(result));
//			logger.info("response={}",CanJsonUtil.toJSONString(result));
		} catch (Throwable e) {
			logger.error("========异常!======", e);
			throw e;
		}
		 
		return result;
	}
	/*@Around(value="execution(public * com.chint.datapool.cloud.service.impl.*.*(..))")
	public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable{
		Object result = null;
		try {
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs(); 
			//拦截的实体类  
			Object target = joinPoint.getTarget(); 
			if(arguments!=null && arguments.length>0) {
				for(Object param:arguments) {
					logger.info("enter "+target+" "+methodName+" with param="+JsonUtil.toJSONString(param));
				}
			}
			//执行目标方法
			result = joinPoint.proceed();
			
//			logger.info("response={}",JSONObject.toJSONString(result));
			//logger.info("response={}",JsonUtil.toJSONString(result));
		} catch (Throwable e) {
			logger.error("========异常!======", e);
			throw e;
		}
		
		return result;
	}*/
	
	/*@Before("controllerLog()") 
	public void doBefore(JoinPoint  joinPoint) throws Exception{
		logger.info("==========执行LogAspect前置通知===============");
		try {
			//拦截的实体类  
			Object target = joinPoint.getTarget();  
			//拦截的方法名称  
			String methodName = joinPoint.getSignature().getName();  
			//拦截的方法参数  
			Object[] argsa = joinPoint.getArgs();  
			//拦截的放参数类型  
			final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Class[] parameterTypes = signature.getMethod().getParameterTypes();  
			  
			Method method = target.getClass().getMethod(methodName, parameterTypes);  
			
			Object[] arguments = joinPoint.getArgs(); 

			
			if(arguments!=null && arguments.length>0) {
				for(Object param:arguments) {
					logger.info("enter "+target+" "+methodName+" with param="+JsonUtil.toJSONString(param));
				}
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
	
	} */
	/*
//	@After("log()")
//	public void doAfter(){
//		logger.info("doAfter================");
//	}
	
	@AfterReturning(returning="obj",pointcut="log()")
	public void doAfter(Object obj){
		logger.info("==========执行LogAspect后置返回通知===============");
		try {
			//		logger.info("response={}",CanJsonUtil.toJSONString(obj));
			logger.info("response={}",JSONObject.toJSONString(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@AfterThrowing(value = "log()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
		logger.info("==========执行LogAspect异常通知===============");
		try {
			logger.error("========异常!======", exception);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }*/	
	
	  
	
}
