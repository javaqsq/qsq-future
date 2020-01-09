package com.qsq.common.auth.annotation;

import java.lang.annotation.*;


/**
 * 用于认证、鉴权的注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CheckAuth {


    /**
     * EL表达式
     * 默认登录访问
     *
     * @return
     */
    String value() default "hasLogin()";

}
