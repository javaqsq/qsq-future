package com.qsq.common.auth.model;

import com.qsq.common.exception.AuthSecurityException;
import com.qsq.common.auth.constants.ConstantsSecurity;
import com.qsq.common.jwt.JwtOperator;
import com.qsq.common.uitl.ClassUtils;
import com.qsq.common.uitl.CollectionUtils;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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


    /**
     * 获取当前登录用户信息
     *
     * @return 用户信息
     */
    public UserInfo getUser() {
        try {
            HttpServletRequest request = getRequest();
            String token = getTokenFromRequest(request);
            Boolean isValid = jwtOperator.validateToken(token);
            if (!isValid) {
                return null;
            }
            Object userInReq = request.getAttribute(SECURITY_REQ_ATTR_USER);
            if (userInReq != null) {
                return (UserInfo) userInReq;
            }
            UserInfo user = getUserFromToken(request);
            request.setAttribute(SECURITY_REQ_ATTR_USER, user);
            return user;
        } catch (Exception e) {
            log.info("发生异常", e);
            throw new AuthSecurityException(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }
    }


    /**
     * 从token中获取当前用户的信息
     *
     * @param request
     * @return
     */
    public UserInfo getUserFromToken(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        Claims claimsMap = jwtOperator.getClaimsFromToken(token);
        UserInfo userInfo = new UserInfo();
        try {
            Field[] fields = userInfo.getClass().getDeclaredFields();
            Arrays.stream(fields).forEach(field -> ClassUtils.fillFieldValue(userInfo, field, claimsMap.get(field.getName())));
        } catch (Exception e) {
            log.error("设置userInfo信息时反射异常", e);
            throw new AuthSecurityException(HttpStatus.UNAUTHORIZED.value(), "设置token反射异常");
        }
        return userInfo;
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
            throw new AuthSecurityException(HttpStatus.UNAUTHORIZED.value(), "没有找到名为Authorization的header");
        }
        if (!header.startsWith(ConstantsSecurity.BEARER)) {
            throw new AuthSecurityException(HttpStatus.UNAUTHORIZED.value(), "token必须以'Bearer '开头");
        }
        if (header.length() <= SEVEN) {
            throw new AuthSecurityException(HttpStatus.UNAUTHORIZED.value(), "token非法，长度 <= 7");
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