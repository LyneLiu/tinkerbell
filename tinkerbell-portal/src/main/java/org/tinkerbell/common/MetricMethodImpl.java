package org.tinkerbell.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Component;

/**
 * Created by nn_liu on 2017/2/6.
 */

@Aspect
@Component
public class MetricMethodImpl {

    private static Logger logger = LoggerFactory.getLogger(MetricMethodImpl.class);

    @Autowired
    private CounterService counterService;

    @Pointcut("@annotation(MetricMethod)")
    public void metricMethod() { }

    @Pointcut("execution(public * org.tinkerbell.controller.*.*(..))")
    public void controllerMetric(){
    }

    /*
    // ProceedingJoinPoint is only supported for around advice
    @Around("MethodCheck()")
    public void aroundInvoke(ProceedingJoinPoint joinPoint){

        try {
            logger.warn("Around AOP method invoked 1!");
            joinPoint.proceed();
            logger.warn("Around AOP method invoked 2!");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }*/

    @Before("controllerMetric()")
    public void beforInvoke(JoinPoint joinPoint){
        logger.info("calling...:"+joinPoint.getSignature().getName());
    }

    @After("metricMethod()")
    public void afterInvoke(JoinPoint joinPoint){
        counterService.increment(joinPoint.getSignature().getName());
    }

}
