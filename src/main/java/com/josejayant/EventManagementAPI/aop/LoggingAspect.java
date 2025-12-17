package com.josejayant.EventManagementAPI.aop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Before("execution(* com.josejayant.EventManagementAPI.controllers.*.*(..))")
    public void logBefore(JoinPoint joinPoint){

        // timestamp
        String timestamp = LocalDateTime.now().format(formatter);

        // Class Name and Method Name
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        // Arguments
        Object[] args = joinPoint.getArgs();
        StringBuilder argsString = new StringBuilder();
        for (Object arg : args){
            argsString.append(arg).append(", ");

        }
        if(args.length > 0){
            argsString.setLength(argsString.length()-2); //remove last two characters which is comma and space
        }

        logger.info("[{}] {}.{}({})", timestamp, className, methodName, argsString.toString());

    }

}



