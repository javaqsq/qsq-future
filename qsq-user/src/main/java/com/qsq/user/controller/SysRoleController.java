package com.qsq.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsq.common.auth.annotation.CheckAuth;
import com.qsq.common.auth.constants.ConstantsSecurity;
import com.qsq.common.auth.model.UserInfo;
import com.qsq.common.auth.model.UserOperator;
import com.qsq.common.enums.ResultEnum;
import com.qsq.common.model.ResultResponse;
import com.qsq.user.dto.RoleInfoResponse;
import com.qsq.user.dto.RoleOperatorRequestDTO;
import com.qsq.user.dto.RoleListRequestDTO;
import com.qsq.user.dto.RoleListResponseDTO;
import com.qsq.user.po.SysPermissions;
import com.qsq.user.service.SysPermissionsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.qsq.user.po.SysRole;
import com.qsq.user.service.SysRoleService;
import com.qsq.common.model.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private SysPermissionsService permissionsService;

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


    /**
     * 列表
     *
     * @param requestDTO
     * @return
     */
    @PostMapping("/list")
    @CheckAuth("hasRole('admin')")
    public ResultResponse roleList(@RequestBody RoleListRequestDTO requestDTO) {
        Page<RoleListResponseDTO> page = super.converterDTOToPageInit(requestDTO);
        List<RoleListResponseDTO> responseDTOS = service.getRoleList(page, requestDTO);
        return ResultResponse.successPage(page, responseDTOS);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @CheckAuth("hasRole('admin')")
    public ResultResponse get(@PathVariable("id") Integer id) {
        SysRole role = service.getById(id);
        List<SysPermissions> permissions = permissionsService.getPermissionListByRoleId(id);
        return ResultResponse.success(RoleInfoResponse.builder()
                .permissions(permissions)
                .role(role)
                .build());
    }

    /**
     * 新增
     *
     * @param requestDTO
     * @param request
     * @return
     */
    @PostMapping("/")
    @CheckAuth("hasRole('admin')")
    public ResultResponse insert(@Validated @RequestBody RoleOperatorRequestDTO requestDTO, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getAttribute(ConstantsSecurity.SECURITY_REQ_ATTR_USER);
        service.insertRoleInfo(requestDTO, userInfo);
        return ResultResponse.returnByEnum(ResultEnum.POJO_INSERT_SUCCESS);
    }

    /**
     * 更新
     *
     * @param requestDTO
     * @param request
     * @return
     */
    @PutMapping("/")
    @CheckAuth("hasRole('admin')")
    public ResultResponse update(@Validated @RequestBody RoleOperatorRequestDTO requestDTO, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getAttribute(ConstantsSecurity.SECURITY_REQ_ATTR_USER);
        service.updateRoleInfo(requestDTO, userInfo);
        return ResultResponse.returnByEnum(ResultEnum.POJO_UPDATE_SUCCESS);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @CheckAuth("hasRole('admin')")
    public ResultResponse delete(@PathVariable("id") Integer id) {
        service.removeById(id);
        return ResultResponse.returnByEnum(ResultEnum.POJO_DELETE_SUCCESS);
    }


}

