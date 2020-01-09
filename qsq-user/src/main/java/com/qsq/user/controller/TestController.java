package com.qsq.user.controller;

import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.model.ResultResponse;
import com.qsq.user.po.SysUser;
import com.qsq.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author QSQ
 * @create 2020/1/4 11:30
 * No, again
 * 〈〉
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/get/{id}")
    public ResultResponse getUser(@PathVariable("id") Integer id) {
        log.info("id : {}", id);
        SysUser sysUser = sysUserService.getById(id);
        if (id == 1) {
            throw ExceptionEnum.LOGIN_USER_NOT_EXIST.getException();
        }
        return ResultResponse.success(sysUser);
    }

}