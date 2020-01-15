package com.qsq.common.auth.enums;

import com.qsq.common.exception.AuthSecurityException;
import com.qsq.common.exception.BusinessRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qsq
 */
@AllArgsConstructor
@Getter
public enum AuthSecurityEnum {

    /**
     * 认证相关的枚举
     */
    REDIS_JWT_NOT_SAME(500, "token版本过期,已失效,重新登录"),
    REFLECT_TOKEN_ERROR(500, "设置jwtToken反射异常"),
    TOKEN_EXPIRED(500, "jwtToken过期"),
    USERNAME_ALREADY_EXIST(500, "用户名已经存在"),
    MOBILE_ALREADY_EXIST(500, "手机号已经存在"),
    MISS_AUTHORIZATION_HEADER(500, "没有找到名为Authorization的header"),
    MISS_BEARER_HEADER(500, "token必须以'Bearer '开头"),
    TOKEN_LENGTH_ERROR(500, "token非法，长度 <= 7"),

    ;


    private int code;
    private String value;


    public AuthSecurityException getException() {
        return new AuthSecurityException(this.getCode(), this.getValue());
    }

}
