package com.chint.datapool.cloud.common.api.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.chint.datapool.cloud.authority.MRoleEntity;
import com.chint.datapool.cloud.authority.User;
import com.chint.datapool.cloud.common.base.BaseRequest;

@Aspect
@Component
public class HasRoleAspect {

	private static Logger logger = LoggerFactory.getLogger(HasRoleAspect.class);
	@Before(value = "@annotation(com.chint.datapool.cloud.common.api.annotation.HasRoles)") 
	public void doBefore(JoinPoint  joinPoint) throws Exception{
		logger.info("==========执行HasRoleAspect前置通知===============");
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

					if(arguments!=null && arguments.length>0 && method.isAnnotationPresent(HasRoles.class)) {
						for(Object param:arguments) {
							if(param instanceof BaseRequest) {
								User user=((BaseRequest) param).getUserInfo();
								HasRoles operationType = method.getAnnotation(HasRoles.class);
								String[] roles=operationType.value();
								if(checkRole(user,roles)) {
									return;
								}
							}
	 					}
						throw new Exception("no auth method");
					}
				} catch (Throwable e) {
					logger.error(e.getMessage(),e);
					throw e;
				}
	
		} 
		
		private boolean checkRole(User user,String[] roles) {
			List<MRoleEntity> userRoles=user.getRole();
			List<String> paramRoles=Arrays.asList(roles);
			if(userRoles!=null && userRoles.size()>0 && roles!=null && roles.length>0) {
				for(MRoleEntity one:userRoles) {
					if(paramRoles.contains(one.getRoleName())) {
						return true;
					}
				}
			}
			return false;
	 	}
}
