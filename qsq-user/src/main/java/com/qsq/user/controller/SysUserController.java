package com.qsq.user.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.qsq.user.po.SysUser;
import com.qsq.user.service.SysUserService;
import org.springframework.web.bind.annotation.RestController;
import com.qsq.common.model.BaseController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author qsq
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController<SysUserService,SysUser> {

}

