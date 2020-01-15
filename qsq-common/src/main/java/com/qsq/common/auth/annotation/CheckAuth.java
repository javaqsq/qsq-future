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
     *  anon()  匿名即可访问
     *  hasLogin() 登录才能有权限查看
     *  hasRole('') 拥有指定角色才能访问
     *  hasAllRoles('','') 拥有所有指定角色才能访问
     *  hasAnyRoles('','') 拥有指定角色之一即可访问
     * @return
     */
    String value();

}
