package com.qsq.test.controller;


import com.qsq.common.model.ResultResponse;
import com.qsq.test.dto.LoginRequestDTO;
import com.qsq.test.po.SysUser;
import com.qsq.test.service.SysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.qsq.common.model.BaseController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author qsq
 * @since 2019-12-29
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController<SysUserService, SysUser> {

    @PostMapping("/login")
    public ResultResponse login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResultResponse.success(this.service.loginByUsernameAndPassword(loginRequestDTO));
    }

}

