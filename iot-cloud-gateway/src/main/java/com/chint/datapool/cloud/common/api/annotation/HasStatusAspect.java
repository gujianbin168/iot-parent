package com.chint.datapool.cloud.common.api.annotation;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.chint.datapool.cloud.authority.User;
import com.chint.datapool.cloud.common.base.BaseRequest;
import com.chint.datapool.cloud.common.constant.Constants;
import com.chint.datapool.cloud.common.exception.ChintApiException;


@Aspect
@Component
public class HasStatusAspect {

	private static Logger logger = LoggerFactory.getLogger(HasStatusAspect.class);
	@Before(value = "@annotation(com.chint.datapool.cloud.common.api.annotation.HasStatus)") 
	public void doBefore(JoinPoint  joinPoint) throws Exception{
		logger.info("==========执行HasStatusAspect前置通知===============");
	 			try {
					//拦截的实体类  
					Object target = joinPoint.getTarget();  
					//拦截的方法名称  
					String methodName = joinPoint.getSignature().getName();  
					//拦截的方法参数  
					Object[] args = joinPoint.getArgs();  
					//拦截的放参数类型  
					final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
					Class[] parameterTypes = signature.getMethod().getParameterTypes();  
					  
					Method method = target.getClass().getMethod(methodName, parameterTypes);  
					
	 				Object[] arguments = joinPoint.getArgs(); 

					if(arguments!=null && arguments.length>0 && method.isAnnotationPresent(HasStatus.class)) {
						HasStatus operationType = method.getAnnotation(HasStatus.class);
						for(Object param:arguments) {
							if(param instanceof BaseRequest) {
								User user=((BaseRequest) param).getUserInfo();

								String[] status=operationType.status();
//								if(checkStatus(user,status)) {
//									return;
//								}
							}
	 					}
						throw new ChintApiException(Constants.ResultCode.BUSS_ERR,operationType.errorshow());
					}
				} catch (Throwable e) {
					logger.error(e.getMessage(),e);
					throw e;
				}
	
		} 
		
//		private boolean checkStatus(User user,String[] status) {
//			List<String> userStatus=user.getStatus();
//			if(userStatus!=null && userStatus.size()==4 
//					&& status!=null && status.length==4) 
//			{
//
//				for(int i=0;i<4;i++) {
//					if(status[i].equals("*")) {
//						continue;
//					} else {
//						if(status[i].startsWith("!")) {
//							if(status[i].substring(1).equals(userStatus.get(i))) {
//								logger.info("==========执行HasStatusAspect前置通知false==============="+JSON.toJSONString(status)+" compaile to "+JSON.toJSONString(userStatus));
//								return false;
//							}
//						}else if(!status[i].equals(userStatus.get(i))) {
//							logger.info("==========执行HasStatusAspect前置通知false==============="+JSON.toJSONString(status)+" compaile to "+JSON.toJSONString(userStatus));
//							return false;
//						}
//					}
//				}
//			}
//			return true;
//	 	}
}
