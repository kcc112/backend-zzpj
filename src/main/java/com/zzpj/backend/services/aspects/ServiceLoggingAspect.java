package com.zzpj.backend.services.aspects;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class ServiceLoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(ServiceLoggingAspect.class);

    @Around("execution(* com.zzpj.backend.services..*(..))")
    public Object profileAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();

        final String className = signature.getDeclaringType().getSimpleName();
        final String methodName = signature.getName();
        final StopWatch stopWatch = new StopWatch();

        LOGGER.log(Level.INFO, () -> "Starting execution of " + className + "." + methodName);
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        LOGGER.log( Level.INFO, () -> "Finished execution of " + className + "." + methodName);
        LOGGER.log(Level.DEBUG, () -> "Total execution time :: " + className + "." + methodName + " :: " + stopWatch.getLastTaskTimeMillis() + " ms");
        return result;
    }
}
