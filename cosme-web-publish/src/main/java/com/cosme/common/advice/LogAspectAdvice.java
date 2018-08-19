package com.cosme.common.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 谢树泉
 */
@Component
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LogAspectAdvice {

    static final String FILTER_LIST = "monitor";

    @Before(value = "execution(* com.baturu.dms.*.controller.*.*(..)) or execution(* com.baturu.dms.*.service.*.*(..)) or execution(* com.baturu.dms.*.adapter.*.*(..))")
    public void log(JoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        String typeName = signature.getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();
        if (!name.contains(FILTER_LIST)) {
//            log.info("调用方法：{}.{}:{}", typeName, name, args);
        }
    }

//    @Around(value = "execution(* com.baturu.dms.*.service.*.*(..))")
//    public Object serviceCatInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
//        Signature signature = joinPoint.getSignature();
//        String className = signature.getDeclaringTypeName();
//        String methodName = signature.getName();
//        String dataSourceKey = DataSourceContextHolder.getDataSourceKey();
//        Transaction transaction = Cat.newTransaction("Call", "(" + dataSourceKey + ") " + className + "." + methodName);
//        Object proceed = null;
//        try {
//            proceed = joinPoint.proceed();
//            transaction.setStatus(Transaction.SUCCESS);
//            return proceed;
//        } catch (Exception e) {
//            Cat.logError(e);
//            transaction.setStatus(e);
//        } finally {
//            transaction.complete();
//            return proceed;
//        }
//    }
}
