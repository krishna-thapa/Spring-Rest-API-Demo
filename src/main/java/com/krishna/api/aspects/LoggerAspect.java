package com.krishna.api.aspects;

import com.krishna.api.modle.ActionType;
import com.krishna.api.modle.ApiResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Aspect
@Component
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("@annotation(inspectApi)")
    public void annotationPointCutDefinition(InspectApi inspectApi){
    }

    @Pointcut("execution(* *(..))")
    public void atExecution(){}

    @Before("annotationPointCutDefinition(inspectApi) && atExecution()")
    public void beforeLoggerAdvice(JoinPoint joinPoint, InspectApi inspectApi) {

        //System.out.println("Krishna"+inspectApi.priority()+" "+inspectApi.action());
        LOGGER.info(inspectApi.priority() + ": "+ inspectApi.action());
    }

    @AfterReturning(pointcut = "annotationPointCutDefinition(inspectApi) && atExecution()", returning = "result")
    public void afterReturnAdvice(JoinPoint joinPoint, InspectApi inspectApi, Object result){

        LOGGER.info("******* logs after the return of the call methods *******");
        Optional<ApiResponse> response = Optional.ofNullable((ApiResponse) result);
        //ApiResponse response = (ApiResponse) result;
        if(response.get().getResponseCode() != null) {
            if (response.get().getResponseCode().intValue() >= 200 && response.get().getResponseCode().intValue() < 300) {
                LOGGER.info(inspectApi.priority() + ": " + "Success on completing method call");
            }else{
                LOGGER.warn(ActionType.ERROR + ": " + response.get().getDescription());
            }
        }else{
            LOGGER.warn(ActionType.ALERT + ": " + "ApiResponse Code is null!");
        }
    }
}
