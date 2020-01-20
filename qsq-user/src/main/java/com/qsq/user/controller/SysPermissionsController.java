package com.qsq.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.auth.constants.ConstantsSecurity;
import com.qsq.common.auth.model.UserInfo;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.enums.ResultEnum;
import com.qsq.common.model.ResultResponse;
import com.qsq.user.dto.PermissionListRequestDTO;
import com.qsq.user.dto.PermissionListResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.qsq.user.po.SysPermissions;
import com.qsq.user.service.SysPermissionsService;
import com.qsq.common.model.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
        Page<PermissionListResponseDTO> page = super.converterDTOToPageInit(requestDTO);
        List<PermissionListResponseDTO> permissions = service.getPermissionList(page, requestDTO);
        return ResultResponse.successPage(page, permissions);
    }

    @PostMapping("/")
    @CheckAuth("hasRole('admin')")
    public ResultResponse insert(@Validated @RequestBody SysPermissions permissions, HttpServletRequest request) {
        QueryWrapper<SysPermissions> query = new QueryWrapper<>();
        query.eq("permissions_name", permissions.getPermissionsName())
                .or()
                .eq("request_url", permissions.getRequestUrl()).eq("method", permissions.getMethod());
        SysPermissions sysPermissions = service.getOne(query);
        if (sysPermissions != null) {
            throw ExceptionEnum.ALREADY_EXIST_PERMISSIONS.getException();
        }
        int seqNum = service.count() + 1;
        permissions.setCreateTime(new Date());
        UserInfo userInfo = (UserInfo) request.getAttribute(ConstantsSecurity.SECURITY_REQ_ATTR_USER);
        permissions.setCreateUser(userInfo.getUserId());
        permissions.setSeqNumber(seqNum);
        service.save(permissions);
        return ResultResponse.returnByEnum(ResultEnum.POJO_INSERT_SUCCESS);
    }

    @PutMapping("/")
    @CheckAuth("hasRole('admin')")
    public ResultResponse update(@Validated @RequestBody SysPermissions permissions, HttpServletRequest request) {
        QueryWrapper<SysPermissions> query = new QueryWrapper<>();
        query.eq("permissions_name", permissions.getPermissionsName())
                .or()
                .eq("request_url", permissions.getRequestUrl()).eq("method", permissions.getMethod());
        SysPermissions sysPermissions = service.getOne(query);
        if (sysPermissions != null && sysPermissions.getPermissionsId().compareTo(permissions.getPermissionsId()) != 0) {
            throw ExceptionEnum.ALREADY_EXIST_PERMISSIONS.getException();
        }
        permissions.setUpdateTime(new Date());
        UserInfo userInfo = (UserInfo) request.getAttribute(ConstantsSecurity.SECURITY_REQ_ATTR_USER);
        permissions.setUpdateUser(userInfo.getUserId());
        service.updateById(permissions);
        return ResultResponse.returnByEnum(ResultEnum.POJO_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @CheckAuth("hasRole('admin')")
    public ResultResponse delete(@PathVariable("id") Integer id) {
        // 会自动逻辑删除
        service.removeById(id);
        return ResultResponse.returnByEnum(ResultEnum.POJO_DELETE_SUCCESS);
    }

    @GetMapping("/{id}")
    @CheckAuth("hasRole('admin')")
    public ResultResponse get(@PathVariable("id") Integer id) {
        return ResultResponse.success(service.getById(id));
    }

}

