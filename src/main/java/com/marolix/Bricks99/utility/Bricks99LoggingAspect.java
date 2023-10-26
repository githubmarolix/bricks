package com.marolix.Bricks99.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.marolix.Bricks99.exception.Bricks99Exception;

@Aspect
@Component
public class Bricks99LoggingAspect {
	public static final Log LOGGER=LogFactory.getLog(Bricks99LoggingAspect.class);
	
	
	@AfterThrowing(pointcut = "execution(* com.marolix.Bricks99.service.*Impl.*(..))",throwing = "exception")
	public void logServiceException(Bricks99Exception exception) {
		LOGGER.error(exception.getMessage(),exception);
	}

}
