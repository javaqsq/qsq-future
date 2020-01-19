package com.qsq.user.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.qsq.user.po.SysUserRole;
import com.qsq.user.service.SysUserRoleService;
import org.springframework.web.bind.annotation.RestController;
import com.qsq.common.model.BaseController;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author qsq
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/user/sysUserRole")
public class SysUserRoleController extends BaseController<SysUserRoleService,SysUserRole> {

}

