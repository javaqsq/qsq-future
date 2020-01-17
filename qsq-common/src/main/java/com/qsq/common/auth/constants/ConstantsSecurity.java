package com.qsq.common.auth.constants;

/**
 * 常量
 *
 * @author qsq
 */
public interface ConstantsSecurity {
    /**
     * 请求头
     */
    String AUTHORIZATION_HEADER = "Authorization";

    /**
     * Bearer header
     */
    String BEARER = "Bearer ";


    int EXPIRE_TIME = 1209600;

    /**
     * redis中保存的头部 + username
     */
    String TOKEN_PREFIX = "login:token-";

    /**
     * 保存在会话中的key
     */
    String SECURITY_REQ_ATTR_USER = "security-user";
}
