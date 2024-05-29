package com.taiex.stock.aop;

import com.taiex.stock.utils.GlobeLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect extends GlobeLogger {

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void applicationPackagePointcut() {
    }


    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();
        StringBuilder parameterString = new StringBuilder();

        for (int i = 0; i < parameterNames.length; i++) {
            String parameterValueString;
            if (parameterValues[i] != null && parameterNames[i] instanceof String && ((String) parameterValues[i]).length() > 100) {
                parameterValueString = "parameter to long ...";
            } else {
                parameterValueString = parameterValues[i] != null ? parameterValues[i].toString() : "Parameter is empty";
            }
            parameterString.append(parameterNames[i]).append("->").append(parameterValueString);
            if (i < parameterNames.length - 1) {
                parameterString.append(", ");
            }
        }
        logger.info("{}.{}() params:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), parameterString);
    }
}
