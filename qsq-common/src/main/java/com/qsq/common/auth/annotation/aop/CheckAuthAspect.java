package com.qsq.common.auth.annotation.aop;

import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.auth.annotation.el.AuthExpressionResult;
import com.qsq.common.auth.annotation.el.CheckAuthExpressionRoot;
import com.qsq.common.exception.AuthSecurityException;
import com.qsq.common.exception.BusinessRuntimeException;
import com.qsq.common.uitl.SpringElCheckUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author QSQ
 * @create 2020/1/7 14:43
 * No, again
 * 〈认证授权切面〉
 */
@Aspect
@AllArgsConstructor
@Slf4j
@Component
public class CheckAuthAspect {

    @Autowired
    private CheckAuthExpressionRoot checkAuthExpressionRoot;

//    @Around("execution(public * com.qsq..*..*Controller.*(..))")
//    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
//
//
//        return joinPoint.proceed();
//    }


    /**
     * 这种写法代表只要写了这总注解的方法都是会进入这个aop中
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.qsq.common.auth.annotation.CheckAuth)")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(CheckAuth.class)) {
            CheckAuth preAuthorize = method.getAnnotation(CheckAuth.class);
            String expression = preAuthorize.value();
            AuthExpressionResult check = null;
            try {
                check = SpringElCheckUtil.check(
                        new StandardEvaluationContext(checkAuthExpressionRoot),
                        expression
                );
            } catch (Exception e) {
                log.error(" 解析表达式出现错误 ", e);
                throw new AuthSecurityException(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
            }
            if (!check.getFlag()) {
                throw new AuthSecurityException(HttpStatus.UNAUTHORIZED.value(), check.getMessage() + " 方法： " + method.getName());
            }
        }
        return joinPoint.proceed();
    }


}