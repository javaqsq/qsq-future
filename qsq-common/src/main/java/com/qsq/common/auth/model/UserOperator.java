package com.qsq.common.auth.model;

import cn.hutool.json.JSONUtil;
import com.qsq.common.auth.enums.AuthSecurityEnum;
import com.qsq.common.exception.AuthSecurityException;
import com.qsq.common.auth.constants.ConstantsSecurity;
import com.qsq.common.jwt.JwtOperator;
import com.qsq.common.uitl.ClassUtils;
import com.qsq.common.uitl.RedisUtils;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author QSQ
 * @create 2020/1/6 21:54
 * No, again
 * 〈用户操作工具〉
 */
@Slf4j
@AllArgsConstructor
@Component
public class UserOperator {

    private static final String SECURITY_REQ_ATTR_USER = "security-user";
    /**
     * 用构造方法可以实现自动注入
     */
    private JwtOperator jwtOperator;
    private static final int SEVEN = 7;
    private RedisUtils redisUtils;

    /**
     * 获取当前登录用户信息
     * 为什么要保存在attribute , 可以让会话中省点操作
     *
     * @return 用户信息
     */
    public UserInfo getUser() {
        try {
            HttpServletRequest request = getRequest();
            String token = getTokenFromRequest(request);
            Object userInReq = request.getAttribute(SECURITY_REQ_ATTR_USER);
            if (userInReq != null) {
                return (UserInfo) userInReq;
            }
            // 会实现检验token的版本
            UserInfo userInfo = getUserFromToken(token);
            request.setAttribute(SECURITY_REQ_ATTR_USER, userInfo);
            return userInfo;
        } catch (Exception e) {
            log.info("发生异常", e);
            throw new AuthSecurityException(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }
    }


    /**
     * 解析token，获得用户信息
     *
     * @param token token
     * @return 用户信息
     */
    @SuppressWarnings("unchecked")
    public UserInfo getUserFromToken(String token) {
        Boolean isValid = jwtOperator.validateToken(token);
        if (!isValid) {
            throw AuthSecurityEnum.TOKEN_EXPIRED.getException();
        }
        Claims claimsMap = jwtOperator.getClaimsFromToken(token);
        UserInfo jwtUserInfo = new UserInfo();
        try {
            Field[] fields = jwtUserInfo.getClass().getDeclaredFields();
            Arrays.stream(fields).forEach(field -> ClassUtils.fillFieldValue(jwtUserInfo, field, claimsMap.get(field.getName())));
        } catch (Exception e) {
            log.error("设置userInfo信息时反射异常", e);
            throw AuthSecurityEnum.REFLECT_TOKEN_ERROR.getException();
        }
        String jsonString = redisUtils.get(ConstantsSecurity.TOKEN_PREFIX + jwtUserInfo.getUsername());
        if (StringUtils.isEmpty(jsonString)) {
            throw AuthSecurityEnum.TOKEN_EXPIRED.getException();
        }
        RedisUserInfo redisUserInfo = JSONUtil.toBean(jsonString, RedisUserInfo.class);
        if (!StringUtils.equals(jwtUserInfo.getVersion(), redisUserInfo.getVersion())) {
            throw AuthSecurityEnum.REDIS_JWT_NOT_SAME.getException();
        }
        return jwtUserInfo;
    }

    /**
     * 从request中获取token
     *
     * @param request 请求
     * @return token
     */
    private String getTokenFromRequest(HttpServletRequest request) {

        String header = request.getHeader(ConstantsSecurity.AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(header)) {
            throw AuthSecurityEnum.MISS_AUTHORIZATION_HEADER.getException();
        }
        if (!header.startsWith(ConstantsSecurity.BEARER)) {
            throw AuthSecurityEnum.MISS_BEARER_HEADER.getException();
        }
        if (header.length() <= SEVEN) {
            throw AuthSecurityEnum.TOKEN_LENGTH_ERROR.getException();
        }
        return header.substring(SEVEN);
    }

    /**
     * 获取request
     *
     * @return request
     */
    private static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if ((requestAttributes == null)) {
            throw new AuthSecurityException(HttpStatus.UNAUTHORIZED.value(), "requestAttributes为null");
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }


}