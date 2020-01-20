package com.qsq.common.auth.annotation.el;

import com.qsq.common.auth.model.UserInfo;
import com.qsq.common.auth.model.UserOperator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author QSQ
 * @create 2020/1/7 14:44
 * No, again
 * 〈表达式解析〉
 */
@Slf4j
@AllArgsConstructor
@Component
@SuppressWarnings({"WeakerAccess", "unused"})
public class CheckAuthExpressionRoot {

    private final static String SUPER_MANAGER = "admin";
    private final UserOperator userOperator;


    /**
     * 匿名即可访问
     * 不需要任何参数
     *
     * @return true
     */
    public AuthExpressionResult anon() {
        return AuthExpressionResult.success();
    }

    /**
     * 登录才能访问
     *
     * @return 如已登录，则返回true
     */
    public AuthExpressionResult hasLogin() {
        return (userOperator.getUser() != null) ? AuthExpressionResult.success() : AuthExpressionResult.fail(" 请登录后访问 ");
    }

    /**
     * 拥有指定角色才能访问
     *
     * @param role 角色
     * @return 如果拥有指定角色，则返回true
     */
    public AuthExpressionResult hasRole(String role) {
        return hasAnyRoles(role);
    }

    /**
     * 拥有所有指定角色才能访问
     *
     * @param roles 角色
     * @return 如果拥有roles所有角色，则返回true
     */
    public AuthExpressionResult hasAllRoles(String... roles) {
        UserInfo user = userOperator.getUser();
        if (user == null) {
            return AuthExpressionResult.fail(" 请登录后访问 ");
        }

        List<String> userRoles = user.getRoles();
        if (CollectionUtils.isEmpty(userRoles)) {
            return AuthExpressionResult.fail(" 用户没有角色数据 ");
        }
        List<String> roleList = Arrays.asList(roles);
        if (roleList.contains(SUPER_MANAGER)) {
            return AuthExpressionResult.success();
        }
        return userRoles.containsAll(roleList) ? AuthExpressionResult.success() : AuthExpressionResult.fail(" 用户没有角色数据 ");
    }

    /**
     * 拥有指定角色之一即可访问
     *
     * @param roles 角色
     * @return 如果拥有roles元素之一，则返回true
     */
    public AuthExpressionResult hasAnyRoles(String... roles) {
        UserInfo user = userOperator.getUser();
        if (user == null) {
            return AuthExpressionResult.fail("  请登录后访问  ");
        }
        List<String> userRoles = user.getRoles();
        List<String> roleList = Arrays.asList(roles);
        if (CollectionUtils.isEmpty(userRoles)) {
            return AuthExpressionResult.fail(" 用户没有角色数据 ");
        }
        if (roleList.contains(SUPER_MANAGER)) {
            return AuthExpressionResult.success();
        }
        boolean checkResult = userRoles.stream()
                .anyMatch(roleList::contains);
        if (!checkResult) {
            log.warn("角色不匹配，userRolesFromToken = {}, roles = {}", userRoles, roleList);
        }
        return checkResult ? AuthExpressionResult.success() : AuthExpressionResult.fail(" 用户没有权限 ");
    }


}