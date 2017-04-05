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
    public void MetricMethod() { }

    /*@Before("MethodCheck()")
    public void beforeInvoke(){
        logger.warn("Before AOP method invoked!");
    }

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

    /*@After("MetricMethod()")
    public void afterInvoke(ProceedingJoinPoint joinPoint){
        counterService.increment(joinPoint.getSignature().toShortString());
    }*/

    @After("MetricMethod()")
    public void afterInvoke(JoinPoint joinPoint){
        counterService.increment(joinPoint.getSignature().toShortString());
    }

}
