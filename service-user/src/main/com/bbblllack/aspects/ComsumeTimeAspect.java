package com.bbblllack.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ComsumeTimeAspect {

    // 定义切点，匹配标注了 @MyCustomAnnotation 注解的方法
    @Pointcut("@annotation(com.bbblllack.annotations.ConsumeTime)")
    public void customAnnotationMethod() {}

    // 环绕通知：拦截所有带有 @MyCustomAnnotation 注解的方法
    @Around("customAnnotationMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 在方法执行之前
        long startTime = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 在方法执行之后
        log.info("Method: {}, Consume: {}ms", joinPoint.getSignature(), System.currentTimeMillis() - startTime);

        return result;
    }

}
