package org.raindrop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.raindrop.utils.SpelNameUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectDemo {
    private SpelNameUtils spelNameUtils = new SpelNameUtils();

//    @Before("@annotation(myAspect)")
//    public void before(JoinPoint joinPoint, MyAspect myAspect){
//        log.info("before");
//    }

    @Around("@annotation(myAspect)")
    public Object around(ProceedingJoinPoint joinPoint, MyAspect myAspect) throws Throwable {
        System.out.println("around");
        System.out.println(spelNameUtils.getValue(myAspect.name(), joinPoint));;
        return joinPoint.proceed();
    }

//    @AfterReturning(value = "@annotation(org.raindrop.aspect.MyAspect)")
//    public void afterReturn(JoinPoint joinPoint){
//        log.info("afterReturn");
//    }
//
//    @AfterThrowing(value = "@annotation(org.raindrop.aspect.MyAspect)")
//    public void afterThrowing(JoinPoint joinPoint){
//        log.info("afterThrowing");
//    }
}
