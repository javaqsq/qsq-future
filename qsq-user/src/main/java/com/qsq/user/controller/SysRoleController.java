package com.qsq.user.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.model.ResultResponse;
import com.qsq.common.uitl.CollectionUtils;
import com.qsq.user.dto.RoleListRequestDTO;
import com.qsq.user.dto.RoleListResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.qsq.user.po.SysRole;
import com.qsq.user.service.SysRoleService;
import com.qsq.common.model.BaseController;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author qsq
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/user/sysRole")
public class SysRoleController extends BaseController<SysRoleService, SysRole> {

    /**
     * 权限列表下拉框
     *
     * @return
     */
    @PostMapping("/roleList")
    @CheckAuth("hasRole('admin')")
    public ResultResponse roleList(@RequestParam(value = "roleName", required = false) String roleName) {
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.eq(StringUtils.isNotEmpty(roleName), "roleName", roleName);
        return ResultResponse.success(service.list(query));
    }


    @PostMapping("/list")
    @CheckAuth("hasRole('admin')")
    public ResultResponse roleList(@RequestBody RoleListRequestDTO requestDTO) {
        Page<RoleListResponseDTO> page = super.converterDTOToPageInit(requestDTO);
        List<RoleListResponseDTO> responseDTOS = service.getRoleList(page,requestDTO);
        return ResultResponse.success(responseDTOS);
    }


}

