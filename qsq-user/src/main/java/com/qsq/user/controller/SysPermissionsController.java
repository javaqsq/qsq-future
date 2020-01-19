package com.qsq.user.controller;


import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.model.ResultResponse;
import com.qsq.user.dto.PermissionListRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qsq.user.po.SysPermissions;
import com.qsq.user.service.SysPermissionsService;
import org.springframework.web.bind.annotation.RestController;
import com.qsq.common.model.BaseController;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author qsq
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/user/sysPermissions")
public class SysPermissionsController extends BaseController<SysPermissionsService, SysPermissions> {

    @PostMapping("/list")
    @CheckAuth("hasRole('admin')")
    public ResultResponse list(@RequestBody PermissionListRequestDTO requestDTO) {


        return ResultResponse.success();
    }

}

