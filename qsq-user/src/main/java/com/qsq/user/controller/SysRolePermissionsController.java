package com.qsq.user.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.qsq.user.po.SysRolePermissions;
import com.qsq.user.service.SysRolePermissionsService;
import org.springframework.web.bind.annotation.RestController;
import com.qsq.common.model.BaseController;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @author qsq
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/user/sysRolePermissions")
public class SysRolePermissionsController extends BaseController<SysRolePermissionsService,SysRolePermissions> {

}

